/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package komunikacija;

import java.io.Serializable;

/**
 *
 * @author Aleksandar Čeganjac
 */
public enum Operacija implements Serializable {

    KREIRAJ_PREGLED,
    PROMENI_PREGLED,
    PRETRAZI_PREGLED,
    
    VRATI_LISTU_PREGLED,
    VRATI_LISTU_SVI_LEKAR,
    VRATI_LISTU_SVI_PACIJENT,
    VRATI_LISTU_SVI_DIJAGNOZA,
    
    KREIRAJ_PACIJENT,
    PROMENI_PACIJENT,
    PRETRAZI_PACIJENT,
    OBRISI_PACIJENT,
    
    VRATI_LISTU_PACIJENT,
    VRATI_LISTU_SVI_KRVNA_GRUPA,
    PRIJAVI_LEKAR,
    UBACI_SPECIJALIZACIJA,
    PROMENI_SPECIJALIZACIJA

}
