/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import controller.ServerController;
import domen.Lekar;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import komunikacija.Odgovor;
import komunikacija.Operacija;
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
        Odgovor odgovor = new Odgovor();

        try {
            izlazni = new ObjectOutputStream(socket.getOutputStream());
            ulazni = new ObjectInputStream(socket.getInputStream());

            while (true) {

                Zahtev zahtev = (Zahtev) ulazni.readObject();

                Object objekat = zahtev.getObjekat();
                Operacija operacija = zahtev.getOperacija();

                odgovor = new Odgovor(); // reset za svaki zahtev

                switch (operacija) {
                    case PRIJAVA:
                        ServerController kontroler = ServerController.vratiInstancu();
                        Lekar lekar = kontroler.prijava((Lekar) objekat);
                        odgovor.setRezultat(lekar);
                        break;
                }

                izlazni.writeObject(odgovor); // OBAVEZNO slanje
            }

        } catch (IOException | ClassNotFoundException ex) {
            if (odgovor == null) {
                odgovor = new Odgovor();
            }
            odgovor.setIzuzetak(ex);
        }
    }
}
