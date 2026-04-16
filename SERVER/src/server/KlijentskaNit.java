/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import controller.ServerController;
import domen.Lekar;
import domen.ODObjekat;
import domen.Pregled;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import komunikacija.Odgovor;
import komunikacija.Operacija;
import static komunikacija.Operacija.KREIRAJ_PREGLED;
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
                    Object domenskiObjekat = zahtev.getObjekat();
                    Operacija operacija = zahtev.getOperacija();
                    ODObjekat rezultat;

                    switch (operacija) {

                        case PRIJAVI_LEKAR:
                            // šalje ka ServerController
                            Lekar lekar = ServerController.vratiInstancu().
                                    prijaviLekar((Lekar) domenskiObjekat);
                            // upisuju domenski domenskiObjekat u odgovor
                            odgovor.setRezultat(lekar);
                            break;
                        case KREIRAJ_PREGLED:
                            Pregled pregledKreiraj = ServerController.vratiInstancu().
                                    kreirajPregled((Pregled) domenskiObjekat);
                            odgovor.setRezultat(pregledKreiraj);
                            break;

                        case PROMENI_PREGLED:
                            Pregled pregledPromeni = ServerController.vratiInstancu().
                                    kreirajPregled((Pregled) domenskiObjekat);
                            odgovor.setRezultat(pregledPromeni);
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
