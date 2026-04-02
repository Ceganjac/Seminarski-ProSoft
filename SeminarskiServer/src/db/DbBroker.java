/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import domen.Lekar;
import java.sql.*;
import domen.ODObjekat;

/**
 *
 * @author Aleksandar Čeganjac
 */
public class DbBroker {

    private String port;
    private String username;
    private String password;
    private Connection konekcija;

    public DbBroker(String port, String username, String password) {
        this.port = port;
        this.username = username;
        this.password = password;
    }

    public void connect() throws SQLException {
        String url = "jdbc:mysql://localhost:" + port + "prosoft";
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

    public Lekar prijava(Lekar lekar) throws SQLException {
        String upit = "SELECT * FROM lekar WHERE lekar_id = ?";
        PreparedStatement ps = konekcija.prepareStatement(upit);
        konekcija.setAutoCommit(false);
        ps.setInt(1, lekar.getIdLekar());

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            lekar.setIdLekar(rs.getInt("id_lekar"));
            lekar.setIme(rs.getString("ime"));
            lekar.setPrezime(rs.getString("prezime"));
            lekar.setKorisnickoIme(rs.getString("korisnicko_ime"));
        }

        return lekar;
    }

    public ODObjekat ubaci(ODObjekat odo) throws SQLException {
        String upit = "INSERT INTO " + odo.vratimImeTabele()
                + " VALUES (" + odo.vratiVrednostiAtributa() + ")";
        Statement st = konekcija.createStatement();
        st.executeUpdate(upit, Statement.RETURN_GENERATED_KEYS);
        st.close();

        ResultSet rs = st.getGeneratedKeys();
        if (rs.next()) {
            odo.postaviId(rs.getInt(1));
        }
        return odo;

    }

}
