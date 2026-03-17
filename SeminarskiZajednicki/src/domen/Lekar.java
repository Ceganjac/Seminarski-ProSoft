/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package domen;

import domen.enumi.Pol;
import java.time.LocalDate;

/**
 *
 * @author Aleksandar Čeganjac
 */
public class Lekar {
    
    private int idLekar;
    private String ime;
    private String prezime;
    private Pol pol;
    private LocalDate datumRodjenja;
    private String korisnickoIme;
    private String lozinka;

    public Lekar() {
    }

    public Lekar(int idLekar, String ime, String prezime, Pol pol, LocalDate datumRodjenja, String korisnickoIme, String lozinka) {
        this.idLekar = idLekar;
        this.ime = ime;
        this.prezime = prezime;
        this.pol = pol;
        this.datumRodjenja = datumRodjenja;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
    }

    public int getIdLekar() {
        return idLekar;
    }

    public void setIdLekar(int idLekar) {
        this.idLekar = idLekar;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Pol getPol() {
        return pol;
    }

    public void setPol(Pol pol) {
        this.pol = pol;
    }

    public LocalDate getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(LocalDate datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }
    
    

}
