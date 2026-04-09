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
public class KrvnaGrupa implements ODObjekat {

    private int idKrvnaGrupa;
    private String aboTip;
    private String rhFaktor;

    public KrvnaGrupa() {
    }

    public KrvnaGrupa(int idKrvnaGrupa, String aboTip, String rhFaktor) {
        this.idKrvnaGrupa = idKrvnaGrupa;
        this.aboTip = aboTip;
        this.rhFaktor = rhFaktor;
    }

    public int getIdKrvnaGrupa() {
        return idKrvnaGrupa;
    }

    public void setIdKrvnaGrupa(int idKrvnaGrupa) {
        this.idKrvnaGrupa = idKrvnaGrupa;
    }

    public String getAboTip() {
        return aboTip;
    }

    public void setAboTip(String aboTip) {
        this.aboTip = aboTip;
    }

    public String getRhFaktor() {
        return rhFaktor;
    }

    public void setRhFaktor(String rhFaktor) {
        this.rhFaktor = rhFaktor;
    }

    @Override
    public String vratiVrednostiAtributa() {
        return idKrvnaGrupa + ", '"
                + aboTip + "', '"
                + rhFaktor + "'";
    }

    @Override
    public String vratiImeTabele() {
        return "krvna_grupa";
    }

    @Override
    public String vratiUslov() {
        return "id_krvna_grupa =" + idKrvnaGrupa;
    }

    @Override
    public String vratiZaUpdate() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void postaviId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // bitan za cmb
    @Override
    public String toString() {
        return aboTip + " " + rhFaktor;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof KrvnaGrupa)) {
            return false;
        }
        KrvnaGrupa other = (KrvnaGrupa) obj;
        return this.idKrvnaGrupa == other.idKrvnaGrupa;
    }

    @Override
    public String vratiNaziveAtributa() {
        return "aboTip, rhFaktor";
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
