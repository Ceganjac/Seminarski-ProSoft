/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package domen;

/**
 *
 * @author Aleksandar Čeganjac
 */
import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

public interface ODObjekat extends Serializable {

    // --- osnovne informacije o tabeli i identitetu ---
    String vratiImeTabele();

    String vratiNazivId();

    void postaviId(int id);

    // --- atributi i vrednosti za upite ---
    String vratiVrednostId();

    String vratiNaziveAtributa();

    String vratiVrednostiAtributa();

    String vratiZaUpdate();

    // --- uslovi za SELECT/UPDATE/DELETE ---
    String vratiUslov();

    // --- parsiranje rezultata iz baze ---
    List<ODObjekat> napraviListu(ResultSet rs) throws Exception;
}
