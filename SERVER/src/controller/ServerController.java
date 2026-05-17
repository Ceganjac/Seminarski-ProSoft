package controller;

import so.pacijent.VratiSvePacijenteSO;
import so.pregled.VratiPregledPoIdSO;
import so.pacijent.VratiPacijentaPoId;
import so.pacijent.KreirajPacijentaSO;
import so.pacijent.PromeniPacijentaSO;
import so.pacijent.ObrisiPacijentaSO;
import so.pacijent.VratiPacijenteUslovSO;
import so.pregled.VratiPregledeUslovSO;
import so.pregled.VratiSvePregledeSO;
import db.DbBroker;
import domen.Dijagnoza;
import domen.KrvnaGrupa;
import domen.Lekar;
import domen.Pacijent;
import domen.Pregled;
import domen.Specijalizacija;
import domen.StavkaPregleda;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import so.*;

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

        PrijavaSO so = new PrijavaSO();
        so.execute(lekar);

        return so.getUlogovani();

    }

    public List<Lekar> vratiSveLekare(Lekar lekar) throws Exception {

        VratiSveLekareSO so = new VratiSveLekareSO();
        so.execute(lekar);

        return so.getLekari();
    }

    // ================= PREGLED =================
    public Pregled kreirajPregled(Pregled pregled) throws Exception {

        KreirajPregledSO so = new KreirajPregledSO();
        //so.execute(pregled);
        return null;
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

        VratiPregledeUslovSO so = new VratiPregledeUslovSO();
        so.execute(pregled);

        return so.getPregledi();

    }

    public List<Pregled> vratiSvePreglede() throws Exception {

        VratiSvePregledeSO so = new VratiSvePregledeSO();
        so.execute(db);
        return so.getPregledi();
    }

    public Pregled vratiPregledPoId(Pregled pregled) throws Exception {

        VratiPregledPoIdSO so = new VratiPregledPoIdSO();
        so.execute(pregled);
        return so.getPregled();
        
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

        KreirajPacijentaSO so = new KreirajPacijentaSO();
        so.execute(pacijent);
        return so.getPacijent();
    }

    public void promeniPacijenta(Pacijent pacijent) throws Exception {

        PromeniPacijentaSO so = new PromeniPacijentaSO();
        so.execute(pacijent);
    }

    public Pacijent vratiPacijentaPoId(Pacijent pacijent) throws Exception {

        VratiPacijentaPoId so = new VratiPacijentaPoId();
        so.execute(pacijent);
        return so.getPacijent();
    }

    public List<Pacijent> vratiSvePacijente() throws Exception {

        VratiSvePacijenteSO so = new VratiSvePacijenteSO();
        so.execute(new Pacijent());
        return so.getPacijenti();
    }

    public List<Pacijent> vratiPacijenteUslov(Pacijent pacijent) throws Exception {

        VratiPacijenteUslovSO so = new VratiPacijenteUslovSO();
        so.execute(pacijent);
        return so.getPacijenti();

    }

    public void obrisiPacijenta(Pacijent pacijent) throws Exception {

        ObrisiPacijentaSO so = new ObrisiPacijentaSO();
        so.execute(pacijent);

    }

    // ================= KRVNA GRUPA =================
    public List<KrvnaGrupa> vratiSveKGrupe() throws Exception {

        VratiSveKrvneGrupeSO so = new VratiSveKrvneGrupeSO();
        so.execute(new KrvnaGrupa());
        return so.getKrvneGrupe();

    }

    // ================= DIJAGNOZA =================
    public List<Dijagnoza> vratiSveDijagnoze() throws Exception {
        
        VratiSveDijagnozeSO so = new VratiSveDijagnozeSO();
        so.execute(db);
        return so.getDijagnoze();
    }

    // ================= SPECIJALIZACIJA =================
    public void ubaciSpecijalizaciju(Specijalizacija spec) throws Exception {
        UbaciSpecijalizacijuSO so = new UbaciSpecijalizacijuSO();
        so.execute(new Specijalizacija());
    }

}
