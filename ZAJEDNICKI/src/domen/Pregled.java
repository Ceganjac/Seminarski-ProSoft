/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aleksandar Čeganjac
 */
public class Pregled implements ODObjekat {

    private int idPregled;

    private LocalDateTime datumVremeZavrsetka;
    private LocalDate datumKontrole;
    private LocalTime vremeKontrole;
    private Duration ukupnoVremeTrajanja;
    private String terapija;
    
    private Lekar lekar;
    private Pacijent pacijent;
    private List<StavkaPregleda> stavke;

    public Pregled() {
    }

    public Pregled(int idPregled, LocalDateTime datumVremeZavrsetka,
            LocalDate datumKontrole, LocalTime vremeKontrole,
            Duration ukupnoVremeTrajanja, String terapija,
            Lekar lekar, Pacijent pacijent,
            List<StavkaPregleda> stavke) {

        this.idPregled = idPregled;
        this.datumVremeZavrsetka = datumVremeZavrsetka;
        this.datumKontrole = datumKontrole;
        this.vremeKontrole = vremeKontrole;
        this.ukupnoVremeTrajanja = ukupnoVremeTrajanja;
        this.terapija = terapija;
        this.lekar = lekar;
        this.pacijent = pacijent;
        this.stavke = stavke;
    }

    // GET / SET
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

    public LocalDate getDatumKontrole() {
        return datumKontrole;
    }

    public void setDatumKontrole(LocalDate datumKontrole) {
        this.datumKontrole = datumKontrole;
    }

    public LocalTime getVremeKontrole() {
        return vremeKontrole;
    }

    public void setVremeKontrole(LocalTime vremeKontrole) {
        this.vremeKontrole = vremeKontrole;
    }

    public Duration getUkupnoVremeTrajanja() {
        return ukupnoVremeTrajanja;
    }

    public void setUkupnoVremeTrajanja(Duration ukupnoVremeTrajanja) {
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

    public List<StavkaPregleda> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaPregleda> stavke) {
        this.stavke = stavke;
    }

    // INSERT
    @Override
    public String vratiVrednostiAtributa() {

        String datumZavrsetka = (datumVremeZavrsetka == null) ? "NULL"
                : "'" + datumVremeZavrsetka.toString().replace("T", " ") + "'";

        String datumKontroleStr = (datumKontrole == null) ? "NULL"
                : "'" + datumKontrole.toString() + "'";

        String vremeKontroleStr = (vremeKontrole == null) ? "NULL"
                : "'" + vremeKontrole.toString() + "'";

        String terapijaStr = (terapija == null) ? "NULL" : "'" + terapija + "'";

        String lekarId = (lekar == null) ? "NULL" : String.valueOf(lekar.getIdLekar());
        String pacijentId = (pacijent == null) ? "NULL" : String.valueOf(pacijent.getIdPacijent());

        return datumZavrsetka + ", "
                + datumKontroleStr + ", "
                + vremeKontroleStr + ", "
                + ukupnoVremeTrajanja + ", "
                + terapijaStr + ", "
                + lekarId + ", "
                + pacijentId;
    }

    @Override
    public String vratiImeTabele() {
        return "pregled";
    }

    @Override
    public String vratiNazivId() {
        return "id_pregled";
    }

    @Override
    public String vratiNaziveAtributa() {
        return "datum_vreme_zavrsetka, datum_kontrole, vreme_kontrole, "
                + "ukupno_vreme_trajanja, terapija, id_lekar, id_pacijent";
    }

    // WHERE
    @Override
    public String vratiUslov() {
        String uslov = "1=1";

        if (lekar != null) {
            uslov += " AND p.id_lekar = " + lekar.getIdLekar();
        }
        if (pacijent != null) {
            uslov += " AND p.id_pacijent = " + pacijent.getIdPacijent();
        }
        if (idPregled != 0) {
            uslov += " AND p.id_pregled = " + idPregled;
        }

        return uslov;
    }

    // UPDATE
    @Override
    public String vratiZaUpdate() {

        String datumZavrsetka = (datumVremeZavrsetka == null) ? "NULL"
                : "'" + datumVremeZavrsetka.toString().replace("T", " ") + "'";

        String datumKontroleStr = (datumKontrole == null) ? "NULL"
                : "'" + datumKontrole.toString() + "'";

        String vremeKontroleStr = (vremeKontrole == null) ? "NULL"
                : "'" + vremeKontrole.toString() + "'";

        String terapijaStr = (terapija == null) ? "NULL" : "'" + terapija + "'";

        String lekarId = (lekar == null) ? "NULL" : String.valueOf(lekar.getIdLekar());
        String pacijentId = (pacijent == null) ? "NULL" : String.valueOf(pacijent.getIdPacijent());

        return "datum_vreme_zavrsetka = " + datumZavrsetka + ", "
                + "datum_kontrole = " + datumKontroleStr + ", "
                + "vreme_kontrole = " + vremeKontroleStr + ", "
                + "ukupno_vreme_trajanja = " + ukupnoVremeTrajanja + ", "
                + "terapija = " + terapijaStr + ", "
                + "id_lekar = " + lekarId + ", "
                + "id_pacijent = " + pacijentId;
    }

    @Override
    public void postaviId(int id) {
        this.idPregled = id;
    }

    @Override
    public String vratiVrednostId() {
        return "" + idPregled;
    }

    // RESULT SET
    @Override
    public List<ODObjekat> napraviListu(ResultSet rs) throws Exception {

        List<ODObjekat> lista = new ArrayList<>();

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
            int minuti = rs.getInt("ukupno_vreme_trajanja");
            Duration d = Duration.ofMinutes((long) minuti);
            pr.setUkupnoVremeTrajanja(d);
            
            pr.setTerapija(rs.getString("terapija"));

            Lekar l = new Lekar();
            l.setIdLekar(rs.getInt("id_lekar"));
            l.setIme(rs.getString("lekar_ime"));
            l.setPrezime(rs.getString("lekar_prezime"));

            Pacijent p = new Pacijent();
            p.setIdPacijent(rs.getInt("id_pacijent"));
            p.setIme(rs.getString("pacijent_ime"));
            p.setPrezime(rs.getString("pacijent_prezime"));

            pr.setLekar(l);
            pr.setPacijent(p);

            lista.add(pr);
        }

        return lista;
    }
}
