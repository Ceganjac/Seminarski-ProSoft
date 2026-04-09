/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import domen.Lekar;
import java.sql.*;
import domen.ODObjekat;
import domen.Pacijent;
import domen.Pregled;
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

    public Lekar prijava(Lekar lekarUlazni) throws SQLException {

        String upit = "SELECT * FROM lekar WHERE korisnicko_ime = ? AND lozinka = ?";
        PreparedStatement ps = konekcija.prepareStatement(upit);
        ps.setString(1, lekarUlazni.getKorisnickoIme());
        ps.setString(2, lekarUlazni.getLozinka());
        ResultSet rs = ps.executeQuery();

        Lekar pronadjen = null;
        if (rs.next()) {
            pronadjen = new Lekar();
            pronadjen.setIdLekar(rs.getInt("id_lekar"));
            pronadjen.setIme(rs.getString("ime"));
            pronadjen.setPrezime(rs.getString("prezime"));
            pronadjen.setKorisnickoIme(rs.getString("korisnicko_ime"));
        }

        rs.close();
        ps.close();
        return pronadjen; // vraća null ako korisnik ne postoji
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

    public List<ODObjekat> vratiPoUslovu(ODObjekat odo) throws SQLException, Exception {
        connect();
        List<ODObjekat> objekti = new ArrayList<>();

        String upit = "SELECT * FROM " + odo.vratiImeTabele() + " WHERE " + odo.vratiUslov();
        Statement st = konekcija.createStatement();
        ResultSet rs = st.executeQuery(upit);

        while (rs.next()) {
            Pregled pregled = new Pregled();
            pregled.setIdPregled(rs.getInt("id_pregled"));

            Timestamp tsZavrsetak = rs.getTimestamp("datum_vreme_zavrsetka");
            if (tsZavrsetak != null) {
                pregled.setDatumVremeZavrsetka(tsZavrsetak.toLocalDateTime());
            }

            Timestamp tsKontrola = rs.getTimestamp("datum_vreme_kontrole");
            if (tsKontrola != null) {
                pregled.setDatumVremeKontrole(tsKontrola.toLocalDateTime());
            }

            pregled.setUkupnoVremeTrajanja(rs.getFloat("ukupno_vreme_trajanja"));
            pregled.setTerapija(rs.getString("terapija"));

            // --- Pacijent ---
            int idPacijent = rs.getInt("id_pacijent");
            Pacijent pacijent = new Pacijent();
            pacijent.setIdPacijent(idPacijent);
            Pacijent pacijentRez = (Pacijent) vratiPoId(pacijent);
            pregled.setPacijent(pacijentRez);

            // --- Lekar ---
            int idLekar = rs.getInt("id_lekar");
            Lekar lekar = new Lekar();
            lekar.setIdLekar(idLekar);  // ovde je bila greška u originalu
            Lekar lekarRez = (Lekar) vratiPoId(lekar);
            pregled.setLekar(lekarRez);

            // dodavanje u listu
            objekti.add(pregled);
        }

        return objekti;
    }

    public ODObjekat vratiPoId(ODObjekat odo) throws SQLException, Exception {
        String upit = "SELECT * FROM " + odo.vratiImeTabele()
                + " WHERE " + odo.vratiNazivId() + " = " + odo.vratiVrednostId();

        Statement st = konekcija.createStatement();
        ResultSet rs = st.executeQuery(upit);

        ODObjekat rezultat = null;

        if (rs.next()) {
            if (odo instanceof Lekar) {
                Lekar lekar = new Lekar();
                lekar.setIdLekar(rs.getInt("id_lekar"));
                lekar.setIme(rs.getString("ime"));
                lekar.setPrezime(rs.getString("prezime"));
                // po potrebi dodaj ostale atribute
                rezultat = lekar;
            } else if (odo instanceof Pacijent) {
                Pacijent pacijent = new Pacijent();
                pacijent.setIdPacijent(rs.getInt("id_pacijent"));
                pacijent.setIme(rs.getString("ime"));
                pacijent.setPrezime(rs.getString("prezime"));
                // po potrebi dodaj ostale atribute
                rezultat = pacijent;
            }
        }

        return rezultat;
    }

}
