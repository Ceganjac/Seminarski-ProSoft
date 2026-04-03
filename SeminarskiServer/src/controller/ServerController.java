/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import db.DbBroker;
import domen.Lekar;
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

    public Lekar prijava(Lekar lekar) throws SQLException {
        DbBroker db = new DbBroker("3306", "root", "root");
        try {
            db.connect();
            lekar = db.prijava(lekar);
            db.commit();
            
            // vraćanje lekara ako ga ima u bazi
            return lekar;

        } catch (SQLException ex) {
            db.rollback();
            throw ex;
        }
        finally{
            db.disconnect();
        }

    }

}
