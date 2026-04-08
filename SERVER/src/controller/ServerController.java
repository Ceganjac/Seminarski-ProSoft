/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import db.DbBroker;
import domen.Lekar;
import domen.ODObjekat;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Aleksandar Čeganjac
 */
public class ServerController {

    private static ServerController instanca;
    DbBroker db;
    String port;
    String username;
    String password;

    public ServerController() {
        try {
            FileReader fr = new FileReader("konfiguracija.txt");
            BufferedReader bf = new BufferedReader(fr);
            port = bf.readLine();
            username = bf.readLine();
            password = bf.readLine();
        } catch (IOException ex) {
        }
    }

    public static ServerController vratiInstancu() {
        if (instanca == null) {
            instanca = new ServerController();
        }
        return instanca;
    }

    public Lekar prijava(Lekar ulazniLekar) throws Exception {

        db = new DbBroker(port, username, password);
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

    public ODObjekat kreiraj(ODObjekat objekat) throws SQLException {
        db = new DbBroker(port, username, password);
        ODObjekat izlazniObjekat;
        try {
            db.connect();
            izlazniObjekat = db.kreiraj(objekat);
            db.commit();
            return izlazniObjekat;
        } catch (SQLException ex) {
            db.rollback();
            throw ex; // Prosledi grešku dalje
        } finally {
            db.disconnect();
        }
    }

    public List<ODObjekat> vratiUslov(ODObjekat objekat) throws Exception {
        db = new DbBroker(port, username, password);
        List<ODObjekat> izlazniObjekti;
        try {
            db.connect();
            izlazniObjekti = db.vratiPoUslovu(objekat);
            db.commit();
            return izlazniObjekti;
        } catch (SQLException ex) {
            db.rollback();
            throw ex; // Prosledi grešku dalje
        } finally {
            db.disconnect();
        }
    }

}
