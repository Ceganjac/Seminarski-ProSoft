/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import domen.enumi.Pol;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Aleksandar Čeganjac
 */
public class Lekar implements ODObjekat {

    private int idLekar;
    private String ime;
    private String prezime;
    private Pol pol;
    private LocalDate datumRodjenja;
    private String korisnickoIme;
    private String lozinka;

    public Lekar() {
    }

    public Lekar(int idLekar, String ime, String prezime, Pol pol, LocalDate datumRodjenja, String korisnickoIme, String lozinka) {
        this.idLekar = idLekar;
        this.ime = ime;
        this.prezime = prezime;
        this.pol = pol;
        this.datumRodjenja = datumRodjenja;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
    }

    public int getIdLekar() {
        return idLekar;
    }

    public void setIdLekar(int idLekar) {
        this.idLekar = idLekar;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Pol getPol() {
        return pol;
    }

    public void setPol(Pol pol) {
        this.pol = pol;
    }

    public LocalDate getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(LocalDate datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    // bitan za cmb
    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public String tableName() {
        return "lekar";
    }

    @Override
    public String alies() {
        return "lek";
    }

    @Override
    public String textJoin() {
        return "";
    }

    @Override
    public String insertColumns() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String insertValues() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String updateValues() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String requiredCondition() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
      public String vratiImePrezime() {
        return ime + " " + prezime;
    }


    @Override
    public String conditionForSelect() {

         List<String> filteri = new ArrayList<>();
        if (ime != null && !ime.isEmpty()) {
            filteri.add("lek.ime LIKE '%" + ime + "%'");
        }
        if (prezime != null && !prezime.isEmpty()) {
            filteri.add("lek.prezime LIKE '%" + prezime + "%'");
        }
        return !filteri.isEmpty() ? " WHERE " + String.join(" AND ", filteri) : "";
    }

    @Override
    public String getCondition() {
        return "WHERE lek.korisnicko_ime= '" + korisnickoIme + "' AND lek.lozinka= '" + lozinka +"'";
    }

    @Override
    public ArrayList<ODObjekat> getList(ResultSet rs) throws SQLException {
        ArrayList<ODObjekat> lekari = new ArrayList<>();

        while (rs.next()) {
            Lekar lekar = new Lekar();

            lekar.setIdLekar(rs.getInt("id_lekar"));
            lekar.setIme(rs.getString("ime"));
            lekar.setPrezime(rs.getString("prezime"));
            lekar.setPol(Pol.valueOf(rs.getString("pol")));

            java.sql.Date datum = rs.getDate("datum_rodjenja");
            lekar.setDatumRodjenja(datum != null ? datum.toLocalDate() : null);

            lekar.setKorisnickoIme(rs.getString("korisnicko_ime"));
            lekar.setLozinka(rs.getString("lozinka"));

            lekari.add(lekar);
        }

        return lekari;
    
    }

}
