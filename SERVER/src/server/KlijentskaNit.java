/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import controller.ServerController;
import domen.Dijagnoza;
import domen.KrvnaGrupa;
import domen.Lekar;
import domen.ODObjekat;
import domen.Pacijent;
import domen.Pregled;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
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
                    Object domenskiObjekat = zahtev.getDomenskiObjekat();
                    Operacija operacija = zahtev.getOperacija();
                    ODObjekat rezultat;

                    switch (operacija) {

                        // ================= LEKAR =================
                        case PRIJAVI_LEKARA:
                            Lekar lekar = ServerController.vratiInstancu()
                                    .prijaviLekar((Lekar) domenskiObjekat);
                            odgovor.setRezultat(lekar);
                            break;

                        case VRATI_SVE_LEKARE:
                            List<Lekar> lekari = ServerController.vratiInstancu()
                                    .vratiSveLekare();
                            odgovor.setRezultat(lekari);
                            break;

                        // ================= PREGLED =================
                        case KREIRAJ_PREGLED:
                            Pregled pregledKreiraj = ServerController.vratiInstancu()
                                    .kreirajPregled((Pregled) domenskiObjekat);
                            odgovor.setRezultat(pregledKreiraj);
                            break;

                        case PROMENI_PREGLED:
                            ServerController.vratiInstancu()
                                    .promeniPregled((Pregled) domenskiObjekat);
                            break;

                        case VRATI_PREGLED_PO_ID:
                            Pregled pregledPoId = ServerController.vratiInstancu()
                                    .vratiPregledPoId((Pregled) domenskiObjekat);
                            odgovor.setRezultat(pregledPoId);
                            break;

                        case VRATI_SVE_PREGLEDE:
                           
                            break;

                        case PRETRAZI_PREGLEDE:
                            List<Pregled> pretragaPregleda = ServerController.vratiInstancu()
                                    .pretraziPreged((Pregled) domenskiObjekat);
                            odgovor.setRezultat(pretragaPregleda);
                            break;

                        // ================= PACIJENT =================
                        case KREIRAJ_PACIJENTA:
                           
                            break;

                        case PROMENI_PACIJENTA:
                           
                            break;

                        case VRATI_PACIJENTA_PO_ID:
                            
                            break;

                        case PRETRAZI_PACIJENTE:
                            
                            break;

                        case OBRISI_PACIJENTA:
                           
                            break;

                        case VRATI_SVE_PACIJENTE:
                           
                            break;

                        // ================= DIJAGNOZA =================
                        case VRATI_SVE_DIJAGNOZE:
                           
                            break;

                        // ================= KRVNA GRUPA =================
                        case VRATI_SVE_KRVNE_GRUPE:
                           
                            break;

                        // ================= SPECIJALIZACIJA =================
                        case UBACI_SPECIJALIZACIJU:
                           
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
