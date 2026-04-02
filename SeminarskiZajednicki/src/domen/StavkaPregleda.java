/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.time.Duration;
import java.util.List;

/**
 *
 * @author Aleksandar Čeganjac
 */
public class StavkaPregleda implements ODObjekat {

    private Pregled pregled;
    private int idStavkaPregleda;
    private String naziv;
    private String lekarskiNalaz;
    private Duration vremeTrajanja;
    private Dijagnoza dijagnoza;

    public StavkaPregleda() {
    }

    public StavkaPregleda(Pregled pregled, int idStavkaPregleda, String naziv, String lekarskiNalaz, Duration vremeTrajanja, Dijagnoza dijagnoza) {
        this.pregled = pregled;
        this.idStavkaPregleda = idStavkaPregleda;
        this.naziv = naziv;
        this.lekarskiNalaz = lekarskiNalaz;
        this.vremeTrajanja = vremeTrajanja;
        this.dijagnoza = dijagnoza;
    }

    public Pregled getPregled() {
        return pregled;
    }

    public void setPregled(Pregled pregled) {
        this.pregled = pregled;
    }

    public int getIdStavkaPregleda() {
        return idStavkaPregleda;
    }

    public void setIdStavkaPregleda(int idStavkaPregleda) {
        this.idStavkaPregleda = idStavkaPregleda;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getLekarskiNalaz() {
        return lekarskiNalaz;
    }

    public void setLekarskiNalaz(String lekarskiNalaz) {
        this.lekarskiNalaz = lekarskiNalaz;
    }

    public Duration getVremeTrajanja() {
        return vremeTrajanja;
    }

    public void setVremeTrajanja(Duration vremeTrajanja) {
        this.vremeTrajanja = vremeTrajanja;
    }

    public Dijagnoza getDijagnoza() {
        return dijagnoza;
    }

    public void setDijagnoza(Dijagnoza dijagnoza) {
        this.dijagnoza = dijagnoza;
    }

    @Override
    public String vratiVrednostiAtributa() {
        return pregled.getIdPregled() + ", "
                + idStavkaPregleda + ", '"
                + naziv + "', '"
                + lekarskiNalaz + "', "
                + vremeTrajanja.toMinutes() + ", "
                + // ili seconds, zavisi kako čuvaš u bazi
                dijagnoza.getIdDijagnoza();
    }

    @Override
    public String vratimImeTabele() {
        return "stavka_pregleda";
    }

    @Override
    public String vratiUslov() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiZaUpdate() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void postaviId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ODObjekat> napraviListu(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
