/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import domen.Dijagnoza;
import domen.KrvnaGrupa;
import domen.Lekar;
import java.sql.*;
import domen.ODObjekat;
import domen.Pacijent;
import domen.Pregled;
import domen.StavkaPregleda;
import domen.enumi.Pol;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aleksandar Čeganjac
 */
public class DbBroker {

    private final String port;
    private final String username;
    private final String password;
    private Connection konekcija;
    private static DbBroker instanca;

    public DbBroker(String port, String username, String password) {
        this.port = port;
        this.username = username;
        this.password = password;
    }

    public void connect() throws SQLException {
        String url = "jdbc:mysql://localhost:" + port + "/" + "seminarski";
        konekcija = DriverManager.getConnection(url, username, password);
        konekcija.setAutoCommit(false);

    }

    public void disconnect() throws SQLException {
        if (konekcija != null && !konekcija.isClosed()) {
            konekcija.close();
        }

    }

    public void commit() throws SQLException {
        if (konekcija != null) {
            konekcija.commit();
        }
    }

    public void rollback() throws SQLException {
        if (konekcija != null) {
            konekcija.rollback();
        }
    }

    public ODObjekat ubaci(ODObjekat odo) throws SQLException {

        String upit = "INSERT INTO " + odo.vratiImeTabele()
                + " VALUES (" + odo.vratiVrednostiAtributa() + ")";
        Statement st = konekcija.createStatement();
        st.executeUpdate(upit, Statement.RETURN_GENERATED_KEYS);

        ResultSet rs = st.getGeneratedKeys();
        if (rs.next()) {
            odo.postaviId(rs.getInt(1));
        }
        return odo;

    }

    public ODObjekat kreiraj(ODObjekat odo) throws SQLException { // kreiraj NE RADI kako treba

        String upit = "INSERT INTO " + odo.vratiImeTabele()
                + " (" + odo.vratiNaziveAtributa() + ") VALUES ("
                + odo.vratiVrednostiAtributa() + ")";
        Statement st = konekcija.createStatement();
        st.executeUpdate(upit, Statement.RETURN_GENERATED_KEYS);

        ResultSet rs = st.getGeneratedKeys();
        if (rs.next()) {
            odo.postaviId(rs.getInt(1));
        }
        return odo;

    }

    public List<ODObjekat> vratiSve(ODObjekat odo) throws Exception {

        List<ODObjekat> objekti;

        String upit = "SELECT * FROM " + odo.vratiImeTabele();

        Statement st = konekcija.createStatement();
        ResultSet rs = st.executeQuery(upit);

        objekti = odo.napraviListu(rs);

        return objekti;
    }

    ///////////////////////////////////////////////////////////////////////////
    
    // SPECIFIČNA ZA PREGLED
    public List<Pregled> vratiPregledeUslov(Pregled pregled) throws Exception {

        String upit
                = "SELECT p.*, "
                + "l.ime AS lekar_ime, l.prezime AS lekar_prezime, "
                + "pa.ime AS pacijent_ime, pa.prezime AS pacijent_prezime "
                + "FROM pregled p "
                + "JOIN lekar l ON p.id_lekar = l.id_lekar "
                + "JOIN pacijent pa ON p.id_pacijent = pa.id_pacijent "
                + "WHERE " + pregled.vratiUslov();

        Statement st = konekcija.createStatement();
        ResultSet rs = st.executeQuery(upit);

        List<Pregled> pregledi = new ArrayList<>();

        while (rs.next()) {

            Pregled pr = new Pregled();

            pr.setIdPregled(rs.getInt("id_pregled"));

            Timestamp tz = rs.getTimestamp("datum_vreme_zavrsetka");
            pr.setDatumVremeZavrsetka(tz != null ? tz.toLocalDateTime() : null);

            Date dk = rs.getDate("datum_kontrole");
            pr.setDatumKontrole(dk != null ? dk.toLocalDate() : null);

            Time tk = rs.getTime("vreme_kontrole");
            pr.setVremeKontrole(tk != null ? tk.toLocalTime() : null);

            // ukupno vreme trajanja
            int vremeTrajanja = rs.getInt("ukupno_vreme_trajanja");
            Duration d = Duration.ofMinutes((long) vremeTrajanja);
            pr.setUkupnoVremeTrajanja(d);
            
            pr.setTerapija(rs.getString("terapija"));

            // lekar
            Lekar l = new Lekar();
            l.setIdLekar(rs.getInt("id_lekar"));
            l.setIme(rs.getString("lekar_ime"));
            l.setPrezime(rs.getString("lekar_prezime"));

            // pacijent
            Pacijent p = new Pacijent();
            p.setIdPacijent(rs.getInt("id_pacijent"));
            p.setIme(rs.getString("pacijent_ime"));
            p.setPrezime(rs.getString("pacijent_prezime"));

            pr.setLekar(l);
            pr.setPacijent(p);

            pregledi.add(pr);
        }

        return pregledi;
    }

    // SPECIFIČNA ZA PACIJENTA
    public List<Pacijent> vratiPacijenteUslov(Pacijent p) throws Exception {

        String upit
                = "SELECT p.id_pacijent, p.ime, p.prezime, p.pol, p.datum_rodjenja, "
                + "p.mesto_rodjenja, p.mejl, p.id_krvna_grupa, "
                + "kg.abo_tip, kg.rh_faktor "
                + "FROM pacijent p "
                + "JOIN krvna_grupa kg ON p.id_krvna_grupa = kg.id_krvna_grupa "
                + "WHERE " + p.vratiUslov();

        Statement st = konekcija.createStatement();
        ResultSet rs = st.executeQuery(upit);

        List<Pacijent> pacijenti = new ArrayList();

        while (rs.next()) {

            Pacijent pacijent = new Pacijent();
            KrvnaGrupa kg = new KrvnaGrupa();

            // pacijent
            pacijent.setIdPacijent(rs.getInt("p.id_pacijent"));
            pacijent.setIme(rs.getString("p.ime"));
            pacijent.setPrezime(rs.getString("p.prezime"));
            pacijent.setPol(Pol.valueOf(rs.getString("pol")));
            pacijent.setDatumRodjenja(rs.getDate("p.datum_rodjenja").toLocalDate());
            pacijent.setMestoRodjenja(rs.getString("p.mesto_rodjenja"));
            pacijent.setMejl(rs.getString("p.mejl"));

            // krvna grupa
            kg.setIdKrvnaGrupa(rs.getInt("p.id_krvna_grupa"));
            kg.setAboTip(rs.getString("kg.abo_tip"));
            kg.setRhFaktor(rs.getString("kg.rh_faktor"));

            pacijent.setKrvnaGrupa(kg);
            pacijenti.add(pacijent);

        }

        return pacijenti;
    }

    // SPECIFIČNA ZA STAVKE
    public List<StavkaPregleda> vratiStavkeUslov(Pregled pregled) throws Exception {

        String upit = "SELECT * "
                + "FROM stavka_pregleda sp "
                + "JOIN dijagnoza d ON sp.id_dijagnoza = d.id_dijagnoza "
                + "WHERE sp.id_pregled = " + pregled.getIdPregled();

        Statement st = konekcija.createStatement();
        ResultSet rs = st.executeQuery(upit);

        List<StavkaPregleda> lista = new ArrayList<>();

        while (rs.next()) {

            StavkaPregleda sp = new StavkaPregleda();

            sp.setIdStavkaPregleda(rs.getInt("id_stavka_pregleda"));
            sp.setNaziv(rs.getString("naziv"));
            sp.setLekarskiNalaz(rs.getString("lekarski_nalaz"));
            sp.setVremeTrajanja(Duration.ofMinutes(rs.getInt("vreme_trajanja")));

            // pregled
            Pregled pr = new Pregled();
            pr.setIdPregled(rs.getInt("id_pregled"));
            sp.setPregled(pr);

            // dijagnoza
            Dijagnoza d = new Dijagnoza();
            d.setIdDijagnoza(rs.getInt("id_dijagnoza"));
            d.setSifra(rs.getString("sifra"));
            d.setSrpskiNaziv(rs.getString("srpski_naziv"));
            sp.setDijagnoza(d);

            lista.add(sp);
        }

        return lista;
    }

    ///////////////////////////////////////////////////////////////////////////
    
    public List<ODObjekat> vratiPoUslovu(ODObjekat odo) throws Exception {

        List<ODObjekat> objekti;

        String upit = "SELECT * FROM " + odo.vratiImeTabele()
                + " WHERE " + odo.vratiUslov();

        Statement st = konekcija.createStatement();
        ResultSet rs = st.executeQuery(upit);

        objekti = odo.napraviListu(rs);

        return objekti;
    }

    public ODObjekat vratiPoId(ODObjekat odo) throws Exception {

        String upit = "SELECT * FROM " + odo.vratiImeTabele()
                + " WHERE " + odo.vratiNazivId() + " = " + odo.vratiVrednostId();

        Statement st = konekcija.createStatement();
        ResultSet rs = st.executeQuery(upit);

        List<ODObjekat> lista = odo.napraviListu(rs);

        if (lista.isEmpty()) {
            return null;
        }

        return lista.get(0);
    }

    public void promeni(ODObjekat odo) throws SQLException {

        String upit = "UPDATE " + odo.vratiImeTabele()
                + " SET " + odo.vratiZaUpdate()
                + " WHERE " + odo.vratiNazivId() + " = " + odo.vratiVrednostId();
        Statement st = konekcija.createStatement();
        st.executeUpdate(upit);
    }

    public void obrisi(ODObjekat odo) throws SQLException {

        String upit = "DELETE FROM " + odo.vratiImeTabele()
                + " WHERE " + odo.vratiNazivId()
                + " = " + odo.vratiVrednostId();

        System.out.println(upit);

        Statement s = konekcija.createStatement();
        s.executeUpdate(upit);
    }

}
