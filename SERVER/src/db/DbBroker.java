/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import domen.Lekar;
import java.sql.*;
import domen.ODObjekat;
import domen.Pregled;

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

    public ODObjekat kreiraj(ODObjekat odo) throws SQLException {

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

}
