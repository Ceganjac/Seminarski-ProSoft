package controller;

import db.DbBroker;
import domen.Dijagnoza;
import domen.KrvnaGrupa;
import domen.Lekar;
import domen.ODObjekat;
import domen.Pacijent;
import domen.Pregled;
import domen.Specijalizacija;
import domen.StavkaPregleda;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import so.PrijavaSO;

public class ServerController {

    private static ServerController instanca;
    DbBroker db;
    String port;
    String username;
    String password;

    public ServerController() {
        try {
            Properties prop = new Properties();
            FileInputStream fis = new FileInputStream("src/db/konfig.properties");
            prop.load(fis);

            port = prop.getProperty("brojPorta");
            username = prop.getProperty("korisnickoIme");
            System.out.println(username);
            password = prop.getProperty("lozinka");

        } catch (IOException ex) {
            ex.printStackTrace();
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

        PrijavaSO prijava = new PrijavaSO();
        prijava.execute(lekar);
        Lekar ulogovani = prijava.getUlogovani();
        return ulogovani;
        
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

            // 1. promena samog pregleda
            db.promeni(pregled);

            // 2. obriši sve stare stavke za taj pregled
            for (StavkaPregleda sp : db.vratiStavkeUslov(pregled)) {
                db.obrisi(sp);
            }

            // 3. ubaci sve nove stavke
            for (StavkaPregleda sp : pregled.getStavke()) {
                db.ubaci(sp);
            }
            db.commit();

        } catch (SQLException ex) {
            db.rollback();
            throw ex;
        } finally {
            db.disconnect();
        }
    }

    public List<Pregled> vratiPregledPoUslovu(Pregled pregled) throws Exception {

        db = new DbBroker(port, username, password);

        try {

            db.connect();
            List<Pregled> pregledi = db.vratiPregledeUslov(pregled);
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

    // ================= STAVKE PREGLEDA =================
    public List<StavkaPregleda> vratiStavkeUslov(Pregled pregled) throws Exception {

        db = new DbBroker(port, username, password);

        try {
            db.connect();
            List<StavkaPregleda> stavke = db.vratiStavkeUslov(pregled);
            db.commit();
            return stavke;

        } catch (SQLException ex) {
            db.rollback();
            throw ex;
        } finally {
            db.disconnect();
        }
    }

    public void promeniStavkuPregleda(StavkaPregleda sp) throws Exception {

        db = new DbBroker(port, username, password);

        try {
            db.connect();
            db.promeni(sp);
            db.commit();

        } catch (SQLException ex) {
            db.rollback();
            throw ex;
        } finally {
            db.disconnect();
        }
    }

    // ================= PACIJENT =================
    public Pacijent kreirajPacijenta(Pacijent pacijent) throws Exception {

        db = new DbBroker(port, username, password);
        Pacijent rezultat = null;

        try {
            db.connect();
            rezultat = (Pacijent) db.kreiraj(pacijent);
            db.commit();
            return rezultat;

        } catch (SQLException ex) {
            db.rollback();
            throw ex;
        } finally {
            db.disconnect();
        }
    }

    public void promeniPacijenta(Pacijent pacijent) throws Exception {

        db = new DbBroker(port, username, password);

        try {
            db.connect();
            db.promeni(pacijent);
            db.commit();

        } catch (SQLException ex) {
            db.rollback();
            throw ex;
        } finally {
            db.disconnect();
        }
    }

    public Pacijent vratiPacijentaPoId(Pacijent pacijent) throws Exception {

        db = new DbBroker(port, username, password);
        Pacijent rezultat = null;

        try {
            db.connect();

            ODObjekat odo = db.vratiPoId(pacijent);
            if (odo != null) {
                rezultat = (Pacijent) odo;
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

    public List<Pacijent> vratiPacijenteUslov(Pacijent pacijent) throws Exception {

        db = new DbBroker(port, username, password);

        try {
            db.connect();
            List<Pacijent> pacijenti = db.vratiPacijenteUslov(pacijent);
            db.commit();
            return pacijenti;

        } catch (SQLException ex) {
            db.rollback();
            throw ex;
        } finally {
            db.disconnect();
        }
    }

    public void obrisiPacijenta(Pacijent pacijent) throws Exception {

        db = new DbBroker(port, username, password);

        try {
            db.connect();
            db.obrisi(pacijent);
            db.commit();

        } catch (SQLException ex) {
            db.rollback();
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

    // ================= SPECIJALIZACIJA =================
    public void ubaciSpecijalizaciju(Specijalizacija spec) throws Exception {

        db = new DbBroker(port, username, password);
        try {
            db.connect();
            db.ubaci(spec);
            db.commit();

        } catch (SQLException ex) {
            throw ex;
        } finally {
            db.disconnect();
        }

    }

}
