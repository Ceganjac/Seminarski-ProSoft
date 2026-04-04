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

    // za INSERT: vraća vrednosti atributa u formatu "vrednost1, 'vrednost2', ..."
    String vratiVrednostiAtributa();

    // ime tabele u bazi
    String vratimImeTabele();

    // WHERE uslov (za SELECT ili UPDATE/DELETE)
    String vratiUslov();

    // za UPDATE: vraća kolona=vrednost
    String vratiZaUpdate();

    // postavljanje generisanog ID-a iz baze nakon INSERT-a
    void postaviId(int id);

    // parsira ResultSet u listu objekata ove klase (za SELECT)
    List<ODObjekat> napraviListu(ResultSet rs) throws Exception;
}
