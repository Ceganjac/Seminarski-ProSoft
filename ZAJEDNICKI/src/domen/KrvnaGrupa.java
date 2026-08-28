/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    public String tableName() {
        return "krvna_grupa";
    }

    @Override
    public String alies() {
        return "krv";
    }

    @Override
    public String textJoin() {
        return "";
    }

    @Override
    public String insertColumns() {
        return "(abo_tip, rh_faktor)";
    }

    @Override
    public String insertValues() {
        return "'" + aboTip + "', '"  + rhFaktor + "'";
    }

    @Override
    public String updateValues() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String requiredCondition() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String conditionForSelect() {
 List<String> filteri = new ArrayList<>();
        if (aboTip != null) {
            filteri.add("krv.abo_tip LIKE '%" + aboTip + "%'");
        }
        if(rhFaktor != null)
            filteri.add("krv.rh_faktor LIKE '%" + rhFaktor + "%'");
        
        return !filteri.isEmpty() ? " WHERE " + String.join(" AND ", filteri) : "";    }

    @Override
    public String getCondition() {
        return "WHERE krv.id_krvna_grupa = " + idKrvnaGrupa;
    }

    @Override
    public ArrayList<ODObjekat> getList(ResultSet rs) throws SQLException {
           ArrayList<ODObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("krv.id_krvna_grupa");
            String abo = rs.getString("krv.abo_tip");
            String rh = rs.getString("krv.rh_faktor");

            KrvnaGrupa kg = new KrvnaGrupa(id, abo, rh);
            lista.add(kg);
        }

        return lista;
    
    }
}
