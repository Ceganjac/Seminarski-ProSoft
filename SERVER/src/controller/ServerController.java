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
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import so.*;
import so.pregled.KreirajPregledSO;
import so.pregled.PromeniPregledSO;
import so.pregled.VratiPregledePacijentaSO;

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

        return so.getPrijavljen();

    }

    public List<Lekar> vratiSveLekare(Lekar lekar) throws Exception {

        VratiSveLekareSO so = new VratiSveLekareSO();
        so.execute(lekar);

        return so.getLekari();
    }

    // ================= PREGLED =================
    public Pregled kreirajPregled(Pregled pregled) throws Exception {

        try{
            KreirajPregledSO so = new KreirajPregledSO();
        so.execute(pregled);
        return so.getPregled();
        }catch(Exception ex){
            ex.printStackTrace();
            throw ex;
        }
        
    }

    public void promeniPregled(Pregled pregled) throws Exception {

        PromeniPregledSO so = new PromeniPregledSO();
        so.execute(pregled);

    }

    public List<Pregled> vratiPregledeUslov(Pregled pregled) throws Exception {

        VratiPregledeUslovSO so = new VratiPregledeUslovSO();
        so.execute(pregled);

        return so.getPregledi();

    }

    public List<Pregled> vratiSvePreglede() throws Exception {

        VratiSvePregledeSO so = new VratiSvePregledeSO();
        so.execute(new Pregled());
        return so.getPregledi();
    }

    public Pregled vratiPregledPoId(Pregled pregled) throws Exception {

        VratiPregledPoIdSO so = new VratiPregledPoIdSO();
        so.execute(pregled);
        return so.getPregled();

    }


    // ================= PACIJENT =================
    public Pacijent kreirajPacijenta(Pacijent pacijent) throws Exception {

        try{
             KreirajPacijentaSO so = new KreirajPacijentaSO();
        so.execute(pacijent);
        return so.getPacijent();
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
       
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
        so.execute(new Dijagnoza());
        return so.getDijagnoze();
    }

    // ================= SPECIJALIZACIJA =================
    public void ubaciSpecijalizaciju(Specijalizacija spec) throws Exception {
        UbaciSpecijalizacijuSO so = new UbaciSpecijalizacijuSO();
        so.execute(spec);
    }
    
    //////////////////////////////////////////////////////////
     public List<Pregled> vratiPregledePacijenta(Pacijent pacijent) throws Exception {
         
         VratiPregledePacijentaSO so = new VratiPregledePacijentaSO();
         so.execute(pacijent);
        return (List<Pregled>) so.getPregledi();
    }
}
