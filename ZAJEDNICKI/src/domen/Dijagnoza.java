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
    public String toString() {
        return sifra + "-" + srpskiNaziv;
    }

  
    @Override
    public String tableName() {
        return "dijagnoza";
    }

    @Override
    public String alies() {
        return "dij";
    }

    @Override
    public String textJoin() {
       return "";
    }

    @Override
    public String insertColumns() {
        return "(sifra, latinski_naziv, srpski_naziv)";
    }

    @Override
    public String insertValues() {
         return  "'"
                + sifra + "', '"
                + latinskiNaziv + "', '"
                + srpskiNaziv + "'";
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
        if (sifra != null) {
            filteri.add("dij.sifra LIKE '%" + sifra + "%'");
        }
        if (latinskiNaziv != null) {
            filteri.add("dij.latinski_naziv LIKE '%" + latinskiNaziv + "%'");
        }
        if (srpskiNaziv != null) {
            filteri.add("dij.srpski_naziv  LIKE '%" + srpskiNaziv + "%'");
        }
        return !filteri.isEmpty() ? " WHERE " + String.join(" AND ", filteri) : "";
    }

    @Override
    public String getCondition() {
       return "WHERE dij.id_dijagnoza = " + idDijagnoza;
    }

    @Override
    public ArrayList<ODObjekat> getList(ResultSet rs) throws SQLException {
       ArrayList<ODObjekat> lista = new ArrayList<>();

        while (rs.next()) {

            Dijagnoza d = new Dijagnoza();

            d.setIdDijagnoza(rs.getInt("dij.id_dijagnoza"));
            d.setSifra(rs.getString("dij.sifra"));
            d.setLatinskiNaziv(rs.getString("dij.latinski_naziv"));
            d.setSrpskiNaziv(rs.getString("dij.srpski_naziv"));

            lista.add(d);
        }

        return lista;
    }

}
