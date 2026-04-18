/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package komunikacija;

import java.io.Serializable;

/**
 *
 * @author Aleksandar Čeganjac
 */
public class Zahtev implements Serializable{
    
    Object domenskiObjekat;
    Operacija operacija;

    public Zahtev() {
    }

    public Zahtev(Object objekat, Operacija operacija) {
        this.domenskiObjekat = objekat;
        this.operacija = operacija;
    }

    public Object getDomenskiObjekat() {
        return domenskiObjekat;
    }

    public void setDomenskiObjekat(Object domenskiObjekat) {
        this.domenskiObjekat = domenskiObjekat;
    }

    public Operacija getOperacija() {
        return operacija;
    }

    public void setOperacija(Operacija operacija) {
        this.operacija = operacija;
    }

    @Override
    public String toString() {
        return "Zahtev{" + "objekat=" + domenskiObjekat + ", operacija=" + operacija + '}';
    }
    
    

}
