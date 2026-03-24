/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package komunikacija;

/**
 *
 * @author Aleksandar Čeganjac
 */
public class Odgovor {
    
    Object rezultat;
    Exception izuzetak;

    public Odgovor() {
    }

    public Odgovor(Object rezultat, Exception izuzetak) {
        this.rezultat = rezultat;
        this.izuzetak = izuzetak;
    }

    public Object getRezultat() {
        return rezultat;
    }

    public void setRezultat(Object rezultat) {
        this.rezultat = rezultat;
    }

    public Exception getIzuzetak() {
        return izuzetak;
    }

    public void setIzuzetak(Exception izuzetak) {
        this.izuzetak = izuzetak;
    }

    @Override
    public String toString() {
        return "Odgovor{" + "rezultat=" + rezultat + ", izuzetak=" + izuzetak + '}';
    }
    
    

}
