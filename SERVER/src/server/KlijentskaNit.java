/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import controller.ServerController;
import domen.Lekar;
import domen.ODObjekat;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import komunikacija.Odgovor;
import komunikacija.Operacija;
import static komunikacija.Operacija.PRIJAVA;
import komunikacija.Zahtev;

/**
 *
 * @author Aleksandar Čeganjac
 */
public class KlijentskaNit extends Thread {

    private final Socket socket;
    private final int redniBrPovezanog;
    private ObjectInputStream ulazni;
    private ObjectOutputStream izlazni;

    public KlijentskaNit(Socket socket, int redniBrPovezanog) {
        this.socket = socket;
        this.redniBrPovezanog = redniBrPovezanog;
    }

    @Override
    public void run() {
        try {
            izlazni = new ObjectOutputStream(socket.getOutputStream());
            ulazni = new ObjectInputStream(socket.getInputStream());

            while (true) {
                Odgovor odgovor = new Odgovor(); // reset za svaki zahtev

                try {
                    Zahtev zahtev = (Zahtev) ulazni.readObject();
                    // rastavljenje zahteva
                    Object objekat = zahtev.getObjekat();
                    Operacija operacija = zahtev.getOperacija();

                    switch (operacija) {
                        case PRIJAVA:
                            // šalje ka ServerController
                            Lekar lekar = ServerController.
                                    vratiInstancu().prijava((Lekar) objekat);

                            // nakon što dobije neki odgovor od ServerController
                            odgovor.setRezultat(lekar);
                            break;
                        case KREIRAJ:
                            // šalje ka ServerController
                            ODObjekat rezultat = (ODObjekat) ServerController.
                                    vratiInstancu().kreiraj((ODObjekat) objekat);
                            // nakon što dobije neki odgovor od ServerController
                            odgovor.setRezultat(rezultat);
                            break;
                    }

                } catch (Exception ex) {
                    odgovor.setIzuzetak(ex);
                }

                // OBAVEZNO pošalji odgovor, čak i ako je bila greška
                izlazni.writeObject(odgovor);
                izlazni.flush();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
