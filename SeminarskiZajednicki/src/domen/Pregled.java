/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Aleksandar Čeganjac
 */
public class Pregled implements ODObjekat {

    private int idPregled;
    private LocalDateTime datumVremeZavrsetka;
    private LocalDateTime datumVremeKontrole;
    private float ukupnoVremeTrajanja;
    private String terapija;
    private Lekar lekar;
    private Pacijent pacijent;

    public Pregled() {
    }

    public Pregled(int idPregled, LocalDateTime datumVremeZavrsetka, LocalDateTime datumVremeKontrole, float ukupnoVremeTrajanja, String terapija, Lekar lekar, Pacijent pacijent) {
        this.idPregled = idPregled;
        this.datumVremeZavrsetka = datumVremeZavrsetka;
        this.datumVremeKontrole = datumVremeKontrole;
        this.ukupnoVremeTrajanja = ukupnoVremeTrajanja;
        this.terapija = terapija;
        this.lekar = lekar;
        this.pacijent = pacijent;
    }

    public int getIdPregled() {
        return idPregled;
    }

    public void setIdPregled(int idPregled) {
        this.idPregled = idPregled;
    }

    public LocalDateTime getDatumVremeZavrsetka() {
        return datumVremeZavrsetka;
    }

    public void setDatumVremeZavrsetka(LocalDateTime datumVremeZavrsetka) {
        this.datumVremeZavrsetka = datumVremeZavrsetka;
    }

    public LocalDateTime getDatumVremeKontrole() {
        return datumVremeKontrole;
    }

    public void setDatumVremeKontrole(LocalDateTime datumVremeKontrole) {
        this.datumVremeKontrole = datumVremeKontrole;
    }

    public float getUkupnoVremeTrajanja() {
        return ukupnoVremeTrajanja;
    }

    public void setUkupnoVremeTrajanja(float ukupnoVremeTrajanja) {
        this.ukupnoVremeTrajanja = ukupnoVremeTrajanja;
    }

    public String getTerapija() {
        return terapija;
    }

    public void setTerapija(String terapija) {
        this.terapija = terapija;
    }

    public Lekar getLekar() {
        return lekar;
    }

    public void setLekar(Lekar lekar) {
        this.lekar = lekar;
    }

    public Pacijent getPacijent() {
        return pacijent;
    }

    public void setPacijent(Pacijent pacijent) {
        this.pacijent = pacijent;
    }

    // METODE IZ INTERFEJSA
    @Override
    public String vratiVrednostiAtributa() {
        return idPregled + ", '"
                + datumVremeZavrsetka + "', '"
                + datumVremeKontrole + "', "
                + ukupnoVremeTrajanja + ", '"
                + terapija + "', "
                + lekar.getIdLekar() + ", "
                + pacijent.getIdPacijent();
    }

    @Override
    public String vratimImeTabele() {
        return "pregled";
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
