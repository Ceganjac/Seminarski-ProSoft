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
    
    Object objekat;
    Operacija operacija;

    public Zahtev() {
    }

    public Zahtev(Object objekat, Operacija operacija) {
        this.objekat = objekat;
        this.operacija = operacija;
    }

    public Object getObjekat() {
        return objekat;
    }

    public void setObjekat(Object objekat) {
        this.objekat = objekat;
    }

    public Operacija getOperacija() {
        return operacija;
    }

    public void setOperacija(Operacija operacija) {
        this.operacija = operacija;
    }

    @Override
    public String toString() {
        return "Zahtev{" + "objekat=" + objekat + ", operacija=" + operacija + '}';
    }
    
    

}
