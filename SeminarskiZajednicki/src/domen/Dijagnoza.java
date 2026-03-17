/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package domen;

/**
 *
 * @author Aleksandar Čeganjac
 */
public class Dijagnoza {
    
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
    
    

}
