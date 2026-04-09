/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
        String datumZavrsetka = (datumVremeZavrsetka == null) ? "NULL"
                : "'" + datumVremeZavrsetka.toString().replace("T", " ") + "'";
        String datumKontrole = (datumVremeKontrole == null) ? "NULL"
                : "'" + datumVremeKontrole.toString().replace("T", " ") + "'";
        String terapijaStr = (terapija == null) ? "NULL" : "'" + terapija + "'";

        return datumZavrsetka + ", "
                + datumKontrole + ", "
                + ukupnoVremeTrajanja + ", "
                + terapijaStr + ", "
                + lekar.getIdLekar() + ", "
                + pacijent.getIdPacijent();
    }

    @Override
    public String vratiImeTabele() {
        return "pregled";
    }

    public String vratiNazivId() {
        return "id_pregled";
    }

    @Override

    public String vratiUslov() {
        String uslov = "1=1";

        if (lekar != null) {
            uslov += " AND id_lekar = " + lekar.getIdLekar();
        }
        if (pacijent != null) {
            uslov += " AND id_pacijent = " + pacijent.getIdPacijent();
        }

        return uslov;
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
    public String vratiNaziveAtributa() {
        return "datum_vreme_zavrsetka, datum_vreme_kontrole, ukupno_vreme_trajanja, "
                + "terapija, id_lekar, id_pacijent";

    }

    @Override
    public String vratiVrednostId() {
        return "" + idPregled;
    }

}
