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
                            List<Pregled> pregledi = ServerController.vratiInstancu()
                                    .vratiSvePreglede();
                            odgovor.setRezultat(pregledi);
                            break;

                        case VRATI_PREGLEDE_USLOV:
                            List<Pregled> pretragaPregleda = ServerController.vratiInstancu()
                                    .vratiPregledPoUslovu((Pregled) domenskiObjekat);
                            odgovor.setRezultat(pretragaPregleda);
                            break;

                        // ================= PACIJENT =================
                        case KREIRAJ_PACIJENTA:
                            Pacijent pacijent = ServerController.vratiInstancu().
                                    kreirajPacijenta((Pacijent) domenskiObjekat);
                            break;

                        case PROMENI_PACIJENTA:
                            ServerController.vratiInstancu()
                                    .promeniPacijenta((Pacijent) domenskiObjekat);
                            break;
                        case VRATI_PACIJENTA_PO_ID:
                            Pacijent pacijentPoId = ServerController.vratiInstancu()
                                    .vratiPacijentaPoId((Pacijent) domenskiObjekat);
                            odgovor.setRezultat(pacijentPoId);
                            break;

                        case VRATI_PACIJENTE_USLOV:
                            List<Pacijent> pacijentiUslov = ServerController.vratiInstancu()
                                    .vratiPacijentePoUslovu((Pacijent) domenskiObjekat);
                            odgovor.setRezultat(pacijentiUslov);
                            break;

                        case OBRISI_PACIJENTA:
                            ServerController.vratiInstancu()
                                    .obrisiPacijenta((Pacijent) domenskiObjekat);
                            break;
                        case VRATI_SVE_PACIJENTE:
                            List<Pacijent> pacijenti = ServerController.
                                    vratiInstancu().vratiSvePacijente();
                            odgovor.setRezultat(pacijenti);
                            break;

                        // ================= DIJAGNOZA =================
                        case VRATI_SVE_DIJAGNOZE:
                            List<Dijagnoza> dijagnoze = ServerController.
                                    vratiInstancu().vratiSveDijagnoze();
                            odgovor.setRezultat(dijagnoze);
                            break;

                        // ================= KRVNA GRUPA =================
                        case VRATI_SVE_KRVNE_GRUPE:
                            List<KrvnaGrupa> krvneGrupe = ServerController.
                                    vratiInstancu().vratiSveKGrupe();
                            odgovor.setRezultat(krvneGrupe);

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
