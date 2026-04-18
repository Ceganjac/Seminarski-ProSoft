package controller;

import domen.Dijagnoza;
import domen.KrvnaGrupa;
import domen.Lekar;
import domen.Pacijent;
import domen.Pregled;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import komunikacija.Odgovor;
import komunikacija.Operacija;
import komunikacija.Zahtev;

/**
 * * * @author Aleksandar Čeganjac
 */
public class GuiController {

    private static GuiController instanca;
    private final Socket soket;
    private final ObjectOutputStream izlazniTok;
    private final ObjectInputStream ulazniTok;

    //konstruktor
    public GuiController() throws IOException {
        soket = new Socket("localhost", 9000);
        izlazniTok = new ObjectOutputStream(soket.getOutputStream());
        ulazniTok = new ObjectInputStream(soket.getInputStream());
    }
    
    //obezbeđuje singleton
    public static GuiController vratiInstancu() throws IOException {
        if (instanca == null) {
            instanca = new GuiController();
        }
        return instanca;
    }
// ================= LEKAR =================
    public Lekar prijaviLekara(Lekar lekar) throws Exception {
        Zahtev zahtev = new Zahtev(lekar, Operacija.PRIJAVI_LEKARA);
        izlazniTok.writeObject(zahtev);
        izlazniTok.flush();

        Odgovor odgovor = (Odgovor) ulazniTok.readObject();
        if (odgovor.getIzuzetak() == null) {
            return (Lekar) odgovor.getRezultat();
        }
        throw odgovor.getIzuzetak();
    }

    public List<Lekar> vratiSveLekare() throws Exception {
        Zahtev zahtev = new Zahtev(null, Operacija.VRATI_SVE_LEKARE);
        izlazniTok.writeObject(zahtev);
        izlazniTok.flush();

        Odgovor odgovor = (Odgovor) ulazniTok.readObject();
        if (odgovor.getIzuzetak() == null) {
            return (List<Lekar>) odgovor.getRezultat();
        }
        throw odgovor.getIzuzetak();
    }

// ================= PREGLED =================
    public Pregled kreirajPregled(Pregled pregled) throws Exception {
        Zahtev zahtev = new Zahtev(pregled, Operacija.KREIRAJ_PREGLED);
        izlazniTok.writeObject(zahtev);
        izlazniTok.flush();

        Odgovor odgovor = (Odgovor) ulazniTok.readObject();
        if (odgovor.getIzuzetak() == null) {
            return (Pregled) odgovor.getRezultat();
        }
        throw odgovor.getIzuzetak();
    }

    public void promeniPregled(Pregled pregled) throws Exception {
        Zahtev zahtev = new Zahtev(pregled, Operacija.PROMENI_PREGLED);
        izlazniTok.writeObject(zahtev);
        izlazniTok.flush();

        Odgovor odgovor = (Odgovor) ulazniTok.readObject();
        if (odgovor.getIzuzetak() != null) {
            throw odgovor.getIzuzetak();
        }
    }

    public Pregled vratiPregledPoId(Pregled pregled) throws Exception {
        Zahtev zahtev = new Zahtev(pregled, Operacija.VRATI_PREGLED_PO_ID);
        izlazniTok.writeObject(zahtev);
        izlazniTok.flush();

        Odgovor odgovor = (Odgovor) ulazniTok.readObject();
        if (odgovor.getIzuzetak() == null) {
            return (Pregled) odgovor.getRezultat();
        }
        throw odgovor.getIzuzetak();
    }

    public List<Pregled> vratiSvePreglede() throws Exception {
        Zahtev zahtev = new Zahtev(null, Operacija.VRATI_SVE_PREGLEDE);
        izlazniTok.writeObject(zahtev);
        izlazniTok.flush();

        Odgovor odgovor = (Odgovor) ulazniTok.readObject();
        if (odgovor.getIzuzetak() == null) {
            return (List<Pregled>) odgovor.getRezultat();
        }
        throw odgovor.getIzuzetak();
    }

    public List<Pregled> pretraziPregled(Pregled pregled) throws Exception {
        Zahtev zahtev = new Zahtev(pregled, Operacija.PRETRAZI_PREGLEDE);
        izlazniTok.writeObject(zahtev);
        izlazniTok.flush();

        Odgovor odgovor = (Odgovor) ulazniTok.readObject();
        if (odgovor.getIzuzetak() == null) {
            return (List<Pregled>) odgovor.getRezultat();
        }
        throw odgovor.getIzuzetak();
    }

// ================= PACIJENT =================
    public void kreirajPacijenta(Pacijent pacijent) throws Exception {
        Zahtev zahtev = new Zahtev(pacijent, Operacija.KREIRAJ_PACIJENTA);
        izlazniTok.writeObject(zahtev);
        izlazniTok.flush();

        Odgovor odgovor = (Odgovor) ulazniTok.readObject();
        if (odgovor.getIzuzetak() != null) {
            throw odgovor.getIzuzetak();
        }
    }

    public void promeniPacijenta(Pacijent pacijent) throws Exception {
        Zahtev zahtev = new Zahtev(pacijent, Operacija.PROMENI_PACIJENTA);
        izlazniTok.writeObject(zahtev);
        izlazniTok.flush();

        Odgovor odgovor = (Odgovor) ulazniTok.readObject();
        if (odgovor.getIzuzetak() != null) {
            throw odgovor.getIzuzetak();
        }
    }

    public Pacijent vratiPacijentaPoId(Pacijent pacijent) throws Exception {
        Zahtev zahtev = new Zahtev(pacijent, Operacija.VRATI_PACIJENTA_PO_ID);
        izlazniTok.writeObject(zahtev);
        izlazniTok.flush();

        Odgovor odgovor = (Odgovor) ulazniTok.readObject();
        if (odgovor.getIzuzetak() == null) {
            return (Pacijent) odgovor.getRezultat();
        }
        throw odgovor.getIzuzetak();
    }

    public List<Pacijent> pretraziPacijente(Pacijent kriterijum) throws Exception {
        Zahtev zahtev = new Zahtev(kriterijum, Operacija.PRETRAZI_PACIJENTE);
        izlazniTok.writeObject(zahtev);
        izlazniTok.flush();

        Odgovor odgovor = (Odgovor) ulazniTok.readObject();
        if (odgovor.getIzuzetak() == null) {
            return (List<Pacijent>) odgovor.getRezultat();
        }
        throw odgovor.getIzuzetak();
    }

    public void obrisiPacijenta(Pacijent pacijent) throws Exception {
        Zahtev zahtev = new Zahtev(pacijent, Operacija.OBRISI_PACIJENTA);
        izlazniTok.writeObject(zahtev);
        izlazniTok.flush();

        Odgovor odgovor = (Odgovor) ulazniTok.readObject();
        if (odgovor.getIzuzetak() != null) {
            throw odgovor.getIzuzetak();
        }
    }

    public List<Pacijent> vratiSvePacijente() throws Exception {
        Zahtev zahtev = new Zahtev(null, Operacija.VRATI_SVE_PACIJENTE);
        izlazniTok.writeObject(zahtev);
        izlazniTok.flush();

        Odgovor odgovor = (Odgovor) ulazniTok.readObject();
        if (odgovor.getIzuzetak() == null) {
            return (List<Pacijent>) odgovor.getRezultat();
        }
        throw odgovor.getIzuzetak();
    }

// ================= DIJAGNOZA =================
    public List<Dijagnoza> vratiSveDijagnoze() throws Exception {
        Zahtev zahtev = new Zahtev(null, Operacija.VRATI_SVE_DIJAGNOZE);
        izlazniTok.writeObject(zahtev);
        izlazniTok.flush();

        Odgovor odgovor = (Odgovor) ulazniTok.readObject();
        if (odgovor.getIzuzetak() == null) {
            return (List<Dijagnoza>) odgovor.getRezultat();
        }
        throw odgovor.getIzuzetak();
    }

// ================= KRVNA GRUPA =================
    public List<KrvnaGrupa> vratiSveKrvneGrupe() throws Exception {
        Zahtev zahtev = new Zahtev(null, Operacija.VRATI_SVE_KRVNE_GRUPE);
        izlazniTok.writeObject(zahtev);
        izlazniTok.flush();

        Odgovor odgovor = (Odgovor) ulazniTok.readObject();
        if (odgovor.getIzuzetak() == null) {
            return (List<KrvnaGrupa>) odgovor.getRezultat();
        }
        throw odgovor.getIzuzetak();
    }

// ================= SPECIJALIZACIJA =================
    public void ubaciSpecijalizaciju(Object spec) throws Exception {
        Zahtev zahtev = new Zahtev(spec, Operacija.UBACI_SPECIJALIZACIJU);
        izlazniTok.writeObject(zahtev);
        izlazniTok.flush();

        Odgovor odgovor = (Odgovor) ulazniTok.readObject();
        if (odgovor.getIzuzetak() != null) {
            throw odgovor.getIzuzetak();
        }

    }
}
