package controller;

import db.DbBroker;
import domen.Dijagnoza;
import domen.KrvnaGrupa;
import domen.Lekar;
import domen.ODObjekat;
import domen.Pacijent;
import domen.Pregled;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServerController {

    private static ServerController instanca;
    DbBroker db;
    String port;
    String username;
    String password;

    public ServerController() {
        try {
            FileReader fr = new FileReader("konfiguracija.txt");
            BufferedReader bf = new BufferedReader(fr);
            port = bf.readLine();
            username = bf.readLine();
            password = bf.readLine();
        } catch (IOException ex) {
        }
    }

    public static ServerController vratiInstancu() {
        if (instanca == null) {
            instanca = new ServerController();
        }
        return instanca;
    }

    // ================= LEKAR =================
    public Lekar prijaviLekar(Lekar lekar) throws Exception {

        db = new DbBroker(port, username, password);
        Lekar lekarRez = null;

        try {
            db.connect();
            List<ODObjekat> lista = db.vratiPoUslovu(lekar);
            if (!lista.isEmpty()) {
                lekarRez = (Lekar) lista.get(0);
            }
            db.commit();
        } catch (SQLException ex) {
            db.rollback();
            throw ex;
        } finally {
            db.disconnect();
        }

        return lekarRez;
    }

    public List<Lekar> vratiSveLekare() throws Exception {

        List<Lekar> lista = new ArrayList<>();
        db = new DbBroker(port, username, password);

        try {
            db.connect();

            List<ODObjekat> rez = db.vratiSve(new Lekar());

            for (ODObjekat o : rez) {
                lista.add((Lekar) o);
            }

            db.commit();
            return lista;

        } catch (SQLException ex) {
            db.rollback();
            throw ex;
        } finally {
            db.disconnect();
        }
    }

    // ================= PREGLED =================
    public Pregled kreirajPregled(Pregled pregled) throws Exception {

        db = new DbBroker(port, username, password);
        Pregled pregledRez = null;

        try {
            db.connect();
            pregledRez = (Pregled) db.kreiraj(pregled);
            db.commit();
        } catch (SQLException ex) {
            db.rollback();
            throw ex;
        } finally {
            db.disconnect();
        }

        return pregledRez;
    }

    public void promeniPregled(Pregled pregled) throws Exception {

        db = new DbBroker(port, username, password);

        try {
            db.connect();
            db.promeni(pregled);
            db.commit();
        } catch (SQLException ex) {
            db.rollback();
            throw ex;
        } finally {
            db.disconnect();
        }
    }

    public List<Pregled> pretraziPreged(Pregled pregled) throws Exception {

        List<Pregled> pregledi = new ArrayList<>();
        db = new DbBroker(port, username, password);

        try {
            db.connect();

            List<ODObjekat> listaObjekata = db.vratiPoUslovu(pregled);

            for (ODObjekat odo : listaObjekata) {
                pregledi.add((Pregled) odo);
            }

            db.commit();
            return pregledi;

        } catch (SQLException ex) {
            db.rollback();
            throw ex;
        } finally {
            db.disconnect();
        }
    }

    public List<Pregled> vratiSvePreglede() throws Exception {

        List<Pregled> pregledi = new ArrayList<>();
        db = new DbBroker(port, username, password);

        try {
            db.connect();

            List<ODObjekat> lista = db.vratiSve(new Pregled());

            for (ODObjekat o : lista) {
                pregledi.add((Pregled) o);
            }

            db.commit();
            return pregledi;

        } catch (SQLException ex) {
            db.rollback();
            throw ex;
        } finally {
            db.disconnect();
        }
    }

    public Pregled vratiPregledPoId(Pregled pregled) throws Exception {

        db = new DbBroker(port, username, password);
        Pregled rezultat = null;

        try {
            db.connect();

            ODObjekat odo = db.vratiPoId(pregled);
            if (odo != null) {
                rezultat = (Pregled) odo;
            }

            db.commit();
            return rezultat;

        } catch (SQLException ex) {
            db.rollback();
            throw ex;
        } finally {
            db.disconnect();
        }
    }

    // ================= PACIJENT =================
    public List<Pacijent> vratiSvePacijente() throws Exception {

        db = new DbBroker(port, username, password);
        List<Pacijent> pacijenti = new ArrayList<>();

        try {
            db.connect();
            List<ODObjekat> lista = db.vratiSve(new Pacijent());
            for (ODObjekat odo : lista) {
                pacijenti.add((Pacijent) odo);
            }
            db.commit();
            return pacijenti;

        } catch (SQLException ex) {
            throw ex;
        } finally {
            db.disconnect();
        }
    }

    // ================= KRVNA GRUPA =================
    public List<KrvnaGrupa> vratiSveKGrupe() throws Exception {

        db = new DbBroker(port, username, password);
        List<KrvnaGrupa> kGrupe = new ArrayList();
        try {
            db.connect();
            List<ODObjekat> lista = db.vratiSve(new KrvnaGrupa());
            for (ODObjekat odo : lista) {
                kGrupe.add((KrvnaGrupa) odo);
            }
            db.commit();
            return kGrupe;

        } catch (SQLException ex) {
            throw ex;
        } finally {
            db.disconnect();
        }

    }

    // ================= DIJAGNOZA =================
    public List<Dijagnoza> vratiSveDijagnoze() throws Exception {

        List<Dijagnoza> dijagnoze = new ArrayList();
        db = new DbBroker(port, username, password);

        try {
            db.connect();
            List<ODObjekat> lista = db.vratiSve(new Dijagnoza());
            for (ODObjekat odo : lista) {
                dijagnoze.add((Dijagnoza) odo);
            }
            db.commit();
            return dijagnoze;

        } catch (SQLException ex) {
            System.getLogger(ServerController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

        return null;
    }
}
