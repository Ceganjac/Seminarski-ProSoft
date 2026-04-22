/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.*;
import domen.ODObjekat;
import domen.Pacijent;
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
    public List<ODObjekat> vratiPoUslovuPregled(ODObjekat odo) throws Exception {

        List<ODObjekat> objekti;

        String upit
                = "SELECT p.*,"
                + " l.ime AS lekar_ime, l.prezime AS lekar_prezime,"
                + " pa.ime AS pacijent_ime, pa.prezime AS pacijent_prezime "
                + "FROM pregled p "
                + "JOIN lekar l ON p.id_lekar = l.id_lekar "
                + "JOIN pacijent pa ON p.id_pacijent = pa.id_pacijent "
                + "WHERE " + odo.vratiUslov();

        Statement st = konekcija.createStatement();
        ResultSet rs = st.executeQuery(upit);

        objekti = odo.napraviListu(rs);

        return objekti;
    }

    // SPECIFIČNA ZA PACIJENTA
    public List<ODObjekat> vratiPoUslovuPacijent(Pacijent p) throws Exception {

        List<ODObjekat> lista;

        String upit
                = "SELECT p.id_pacijent, p.ime, p.prezime, p.pol, p.datum_rodjenja, "
                + "p.mesto_rodjenja, p.mejl, p.id_krvna_grupa, "
                + "kg.abo_tip, kg.rh_faktor "
                + "FROM pacijent p "
                + "JOIN krvna_grupa kg ON p.id_krvna_grupa = kg.id_krvna_grupa "
                + "WHERE " + p.vratiUslov();

        Statement st = konekcija.createStatement();
        ResultSet rs = st.executeQuery(upit);

        lista = p.napraviListu(rs);

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
        s.close();
    }

}
