/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package domen;

import java.time.Duration;

/**
 *
 * @author Aleksandar Čeganjac
 */
public class StavkaPregleda {
    
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
    
    
    
    

}
