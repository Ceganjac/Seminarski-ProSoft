/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aleksandar Čeganjac
 */
public class StavkaPregleda implements ODObjekat {

    private Pregled pregled;
    private int redni_broj_stavke;
    private String naziv;
    private String lekarskiNalaz;
    private Duration vremeTrajanja;
    private Dijagnoza dijagnoza;

    public StavkaPregleda() {
    }

    public StavkaPregleda(Pregled pregled, int idStavkaPregleda, String naziv,
            String lekarskiNalaz, Duration vremeTrajanja, Dijagnoza dijagnoza) {
        this.pregled = pregled;
        this.redni_broj_stavke = idStavkaPregleda;
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

    public int getRedni_broj_stavke() {
        return redni_broj_stavke;
    }

    public void setRedni_broj_stavke(int redni_broj_stavke) {
        this.redni_broj_stavke = redni_broj_stavke;
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
    public String tableName() {

        return "stavka_pregleda";
    }

    @Override
    public String alies() {
        return "sp";
    }

    @Override
    public String textJoin() {
        return "";
    }

    @Override
    public String insertColumns() {
        return "(id_pregled, naziv, lekarski_nalaz, vreme_trajanja, id_dijagnoza)";
    }

    @Override
    public String insertValues() {
        return pregled.getIdPregled() + ", '" + naziv + "', '" + lekarskiNalaz + "', " + vremeTrajanja.toMinutes() + ", " + dijagnoza.getIdDijagnoza();
    }

    @Override
    public String updateValues() {
        return "id_pregled = " + pregled.getIdPregled() + ", naziv = '" + naziv + "', lekarski_nalaz='" + lekarskiNalaz + "vreme_trajanja=" + vremeTrajanja.toMinutes() + ", id_dijagnoza=" + dijagnoza.getIdDijagnoza();
    }

    @Override
    public String requiredCondition() {
        return "id_pregled = " + pregled.getIdPregled() + " AND id_stavka_pregleda =  " + redni_broj_stavke;
    }

    @Override
    public String conditionForSelect() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getCondition() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<ODObjekat> getList(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
