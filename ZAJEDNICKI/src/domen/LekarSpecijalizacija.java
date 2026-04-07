/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

/**
 *
 * @author Aleksandar Čeganjac
 */
public class LekarSpecijalizacija implements ODObjekat {

    private Lekar lekar;
    private Specijalizacija specijalizacija;
    private LocalDate datumSticanja;
    private String institucijaSticanja;
    private Period trajanje;

    public LekarSpecijalizacija() {
    }

    public LekarSpecijalizacija(Lekar lekar, Specijalizacija specijalizacija, LocalDate datumSticanja, String institucijaSticanja, Period trajanje) {
        this.lekar = lekar;
        this.specijalizacija = specijalizacija;
        this.datumSticanja = datumSticanja;
        this.institucijaSticanja = institucijaSticanja;
        this.trajanje = trajanje;
    }

    public Lekar getLekar() {
        return lekar;
    }

    public void setLekar(Lekar lekar) {
        this.lekar = lekar;
    }

    public Specijalizacija getSpecijalizacija() {
        return specijalizacija;
    }

    public void setSpecijalizacija(Specijalizacija specijalizacija) {
        this.specijalizacija = specijalizacija;
    }

    public LocalDate getDatumSticanja() {
        return datumSticanja;
    }

    public void setDatumSticanja(LocalDate datumSticanja) {
        this.datumSticanja = datumSticanja;
    }

    public String getInstitucijaSticanja() {
        return institucijaSticanja;
    }

    public void setInstitucijaSticanja(String institucijaSticanja) {
        this.institucijaSticanja = institucijaSticanja;
    }

    public Period getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(Period trajanje) {
        this.trajanje = trajanje;
    }

    @Override
    public String vratiVrednostiAtributa() {
        return lekar.getIdLekar() + ", "
                + specijalizacija.getIdSpecijalizacija() + ", '"
                + datumSticanja + "', '"
                + institucijaSticanja + "', "
                + trajanje.getYears(); // ili months, zavisi kako čuvaš
    }

    @Override
    public String vratiImeTabele() {
        return "lekar_specijalizacija";
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

}
