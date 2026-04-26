/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.time.Duration;
import java.util.ArrayList;
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

        if (pregled == null) {
            throw new RuntimeException("Pregled ne sme biti null u StavkaPregleda");
        }

        if (naziv == null || lekarskiNalaz == null) {
            throw new RuntimeException("Naziv ili lekarski nalaz su null");
        }

        if (vremeTrajanja == null) {
            throw new RuntimeException("Vreme trajanja je null");
        }

        if (dijagnoza == null) {
            throw new RuntimeException("Dijagnoza je null");
        }

        return pregled.getIdPregled() + ", "
                + idStavkaPregleda + ", '"
                + naziv + "', '"
                + lekarskiNalaz + "', "
                + vremeTrajanja.toMinutes() + ", "
                + dijagnoza.getIdDijagnoza();
    }

    @Override
    public String vratiImeTabele() {
        return "stavka_pregleda";
    }

    @Override
    public String vratiUslov() {
        String uslov = "1=1";
        if (pregled != null) {
            uslov += " AND id_pregled = " + pregled.getIdPregled();
        }
        return uslov;
    }

    @Override
    public String vratiZaUpdate() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void postaviId(int id) {
        this.idStavkaPregleda = id;
    }

    @Override
    public String vratiNaziveAtributa() {
        return "idPregled, naziv, lekarskiNalaz, vremeTrajanja, idDijagnoza";

    }

    @Override
    public String vratiNazivId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ODObjekat> napraviListu(ResultSet rs) throws Exception {

        List<ODObjekat> stavke = new ArrayList();

        while (rs.next()) {
            StavkaPregleda sp = new StavkaPregleda();
            sp.setIdStavkaPregleda(rs.getInt("id_stavka_pregleda"));
            sp.setNaziv(rs.getString("naziv"));
            sp.setLekarskiNalaz(rs.getString("lekarski_nalaz"));
            // vreme trajanja
            sp.setVremeTrajanja(Duration.ofMinutes(rs.getInt("vreme_trajanja")));
            // pregled
            Pregled pregled = new Pregled();
            pregled.setIdPregled(rs.getInt("id_pregled"));
            sp.setPregled(pregled);
            // dijagnoza
            Dijagnoza dijagnoza = new Dijagnoza();
            dijagnoza.setIdDijagnoza(rs.getInt("id_dijagnoza"));
            sp.setDijagnoza(dijagnoza);

            stavke.add(sp);
        }

        return stavke;
    }

}
