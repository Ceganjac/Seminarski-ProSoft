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
    List<StavkaPregleda> stavke;

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

    public List<StavkaPregleda> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaPregleda> stavke) {
        this.stavke = stavke;
    }

    // METODE IZ INTERFEJSA
    @Override
    public String vratiVrednostiAtributa() {
        String datumZavrsetka = (datumVremeZavrsetka == null) ? "NULL"
                : "'" + datumVremeZavrsetka.toString().replace("T", " ") + "'";
        String datumKontrole = (datumVremeKontrole == null) ? "NULL"
                : "'" + datumVremeKontrole.toString().replace("T", " ") + "'";
        String terapijaStr = (terapija == null) ? "NULL" : "'" + terapija + "'";

        String lekarId = (lekar == null) ? "NULL" : String.valueOf(lekar.getIdLekar());
        String pacijentId = (pacijent == null) ? "NULL" : String.valueOf(pacijent.getIdPacijent());

        return datumZavrsetka + ", "
                + datumKontrole + ", "
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

        String datumZavrsetka = (datumVremeZavrsetka == null) ? "NULL"
                : "'" + datumVremeZavrsetka.toString().replace("T", " ") + "'";

        String datumKontrole = (datumVremeKontrole == null) ? "NULL"
                : "'" + datumVremeKontrole.toString().replace("T", " ") + "'";

        String terapijaStr = (terapija == null) ? "NULL" : "'" + terapija + "'";

        String lekarId = (lekar == null) ? "NULL" : String.valueOf(lekar.getIdLekar());
        String pacijentId = (pacijent == null) ? "NULL" : String.valueOf(pacijent.getIdPacijent());

        return "datum_vreme_zavrsetka = " + datumZavrsetka + ", "
                + "datum_vreme_kontrole = " + datumKontrole + ", "
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
    public String vratiNaziveAtributa() {
        return "datum_vreme_zavrsetka, datum_vreme_kontrole, ukupno_vreme_trajanja, "
                + "terapija, id_lekar, id_pacijent";

    }

    @Override
    public String vratiVrednostId() {
        return "" + idPregled;
    }

    @Override
    public List<ODObjekat> napraviListu(ResultSet rs) throws Exception {
        List<ODObjekat> lista = new ArrayList<>();

        while (rs.next()) {

            Pregled pr = new Pregled();

            pr.setIdPregled(rs.getInt("id_pregled"));

            Timestamp tz = rs.getTimestamp("datum_vreme_zavrsetka");
            pr.setDatumVremeZavrsetka(tz != null ? tz.toLocalDateTime() : null);

            Timestamp tk = rs.getTimestamp("datum_vreme_kontrole");
            pr.setDatumVremeKontrole(tk != null ? tk.toLocalDateTime() : null);

            pr.setUkupnoVremeTrajanja(rs.getFloat("ukupno_vreme_trajanja"));
            pr.setTerapija(rs.getString("terapija"));

            // --- samo ID za povezane objekte ---
            Lekar l = new Lekar();
            l.setIdLekar(rs.getInt("id_lekar"));

            Pacijent p = new Pacijent();
            p.setIdPacijent(rs.getInt("id_pacijent"));

            pr.setLekar(l);
            pr.setPacijent(p);

            lista.add(pr);
        }

        return lista;
    }

}
