/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Lekar;
import domen.ODObjekat;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import komunikacija.Odgovor;
import komunikacija.Operacija;
import komunikacija.Zahtev;

/**
 *
 * @author Aleksandar Čeganjac
 */
public class GuiController {

    private static GuiController instanca;
    private final Socket soket;
    private final ObjectOutputStream izlazniTok;
    private final ObjectInputStream ulazniTok;

    // konstruktor
    public GuiController() throws IOException {
        soket = new Socket("localhost", 9000);
        izlazniTok = new ObjectOutputStream(soket.getOutputStream());
        ulazniTok = new ObjectInputStream(soket.getInputStream());
    }

    // obezbeđuje singleton
    public static GuiController vratiInstancu() throws IOException {
        if (instanca == null) {
            instanca = new GuiController();
        }
        return instanca;
    }

    public Lekar prijava(Lekar lekar) throws Exception {
        
        // kreiranje i slanje zahteva
        Zahtev zahtev = new Zahtev(lekar, Operacija.PRIJAVA);
        izlazniTok.writeObject(zahtev);
        izlazniTok.flush();

        // uzimam odgovor od servera
        Odgovor odgovor = (Odgovor) ulazniTok.readObject();
        if (odgovor.getIzuzetak() == null) {
            return (Lekar) odgovor.getRezultat();
        } else {
            throw odgovor.getIzuzetak();
        }

    }
    
    public Object kreiraj(ODObjekat objekat) throws Exception{
        // kreiranje i slanje zahteva
        Zahtev zahtev = new Zahtev(objekat, Operacija.KREIRAJ);
        izlazniTok.writeObject(zahtev);
        izlazniTok.flush();
        
        // uzimamo odgovor od servera
        Odgovor odgovor = (Odgovor) ulazniTok.readObject();
        if (odgovor.getIzuzetak() == null) {
            return (ODObjekat) odgovor.getRezultat();
        } else {
            throw odgovor.getIzuzetak();
        }
    
    }

}
