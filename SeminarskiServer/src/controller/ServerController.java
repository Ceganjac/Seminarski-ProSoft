/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import db.DbBroker;
import domen.Lekar;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.SQLException;

/**
 *
 * @author Aleksandar Čeganjac
 */
public class ServerController {

    private static ServerController instanca;

    public static ServerController vratiInstancu() {
        if (instanca == null) {
            instanca = new ServerController();
        }
        return instanca;
    }

public Lekar prijava(Lekar ulazniLekar) throws Exception {
    
    try (BufferedReader bf = new BufferedReader(new FileReader("konfiguracija.txt"))) {
        String port = bf.readLine();
        String username = bf.readLine();
        String password = bf.readLine();

        DbBroker db = new DbBroker(port, username, password);
        Lekar izlazniLekar = null; // Inicijalno null
        try {
            db.connect();
            izlazniLekar = db.prijava(ulazniLekar);
            db.commit();
            return izlazniLekar; 
        } catch (SQLException ex) {
            db.rollback();
            throw ex; // Prosledi grešku dalje
        } finally {
            db.disconnect();
        }
    }
}
}
