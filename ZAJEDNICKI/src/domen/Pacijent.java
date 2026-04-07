/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import domen.enumi.Pol;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Aleksandar Čeganjac
 */
public class Pacijent implements ODObjekat {

    private int idPacijent;
    private String ime;
    private String prezime;
    private Pol pol;
    private LocalDate datumRodjenja;
    private String mestoRodjenja;
    private String mejl;
    KrvnaGrupa krvnaGrupa;

    public Pacijent() {
    }

    public Pacijent(int idPacijent, String ime, String prezime, Pol pol, LocalDate datumRodjenja, String mestoRodjenja, String mejl, KrvnaGrupa krvnaGrupa) {
        this.idPacijent = idPacijent;
        this.ime = ime;
        this.prezime = prezime;
        this.pol = pol;
        this.datumRodjenja = datumRodjenja;
        this.mestoRodjenja = mestoRodjenja;
        this.mejl = mejl;
        this.krvnaGrupa = krvnaGrupa;
    }

    public int getIdPacijent() {
        return idPacijent;
    }

    public void setIdPacijent(int idPacijent) {
        this.idPacijent = idPacijent;
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

    public String getMestoRodjenja() {
        return mestoRodjenja;
    }

    public void setMestoRodjenja(String mestoRodjenja) {
        this.mestoRodjenja = mestoRodjenja;
    }

    public String getMejl() {
        return mejl;
    }

    public void setMejl(String mejl) {
        this.mejl = mejl;
    }

    public KrvnaGrupa getKrvnaGrupa() {
        return krvnaGrupa;
    }

    public void setKrvnaGrupa(KrvnaGrupa krvnaGrupa) {
        this.krvnaGrupa = krvnaGrupa;
    }

    // -------------------------------------------------- //
    @Override
    public String vratiVrednostiAtributa() {
        return  " '"
                + ime + "', '"
                + prezime + "', '"
                + pol + "', '"
                + datumRodjenja + "', '"
                + mestoRodjenja + "', '"
                + mejl + "', "
                + krvnaGrupa.getIdKrvnaGrupa();
    }

    @Override
    public String vratiImeTabele() {
        return "pacijent";
    }

    @Override
    public String vratiUslov() {
        return "id_pacijenta = " + idPacijent;
    }

    @Override
    public String vratiZaUpdate() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void postaviId(int idPacijenta) {
        this.idPacijent = idPacijenta;
    }
    // -------------------------------------------------- //

    // vrati ime i prezime
    public String vratiImePrezime() {
        return ime + " " + prezime;
    }

    // bitan za cmb
    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public List<ODObjekat> napraviListu(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
