/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import db.DbBroker;
import domen.Lekar;
import domen.ODObjekat;
import domen.Pregled;
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

    // LEKAR
    public Lekar prijaviLekar(Lekar lekar) throws Exception {

        db = new DbBroker(port, username, password);
        Lekar lekarRez = null;

        try {

            db.connect();
            List<ODObjekat> lista = db.vratiPoUslovu(lekar);
            if (!lista.isEmpty()) {
                lekarRez = (Lekar) lista.get(0);
            }
            db.commit();

        } catch (SQLException ex) {
            db.rollback();
            throw ex;
        } finally {
            db.disconnect();
        }

        return lekarRez;
    }

    // PREGLED
    public Pregled kreirajPregled(Pregled pregled) throws Exception {

        db = new DbBroker(port, username, password);
        Pregled pregledRez = null;

        try {

            db.connect();
            pregledRez = (Pregled) db.kreiraj(pregled);
        } catch (SQLException ex) {
            db.rollback();
            throw ex;
        } finally {
            db.disconnect();
        }

        return pregledRez;
    }

}
