/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Aleksandar Čeganjac
 */
public class Dijagnoza implements ODObjekat {

    private int idDijagnoza;
    private String sifra;
    private String latinskiNaziv;
    private String srpskiNaziv;

    public Dijagnoza() {
    }

    public Dijagnoza(int idDijagnoza, String sifra, String latinskiNaziv, String srpskiNaziv) {
        this.idDijagnoza = idDijagnoza;
        this.sifra = sifra;
        this.latinskiNaziv = latinskiNaziv;
        this.srpskiNaziv = srpskiNaziv;
    }

    public int getIdDijagnoza() {
        return idDijagnoza;
    }

    public void setIdDijagnoza(int idDijagnoza) {
        this.idDijagnoza = idDijagnoza;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public String getLatinskiNaziv() {
        return latinskiNaziv;
    }

    public void setLatinskiNaziv(String latinskiNaziv) {
        this.latinskiNaziv = latinskiNaziv;
    }

    public String getSrpskiNaziv() {
        return srpskiNaziv;
    }

    public void setSrpskiNaziv(String srpskiNaziv) {
        this.srpskiNaziv = srpskiNaziv;
    }

    @Override
    public String vratiVrednostiAtributa() {
        return idDijagnoza + ", '"
                + sifra + "', '"
                + latinskiNaziv + "', '"
                + srpskiNaziv + "'";
    }

    @Override
    public String vratiImeTabele() {
        return "dijagnoza";
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

    @Override
    public String vratiNaziveAtributa() {
        return "sifra, latinskiNaziv, srpskiNaziv";
    }

    @Override
    public String vratiNazivId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
