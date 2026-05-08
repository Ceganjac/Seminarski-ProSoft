/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import db.DbBroker;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Aleksandar Čeganjac
 */
public abstract class AbstractSO {

    static String brojPorta;
    static String korisnickoIme;
    static String lozinka;

    private void procitajKonfig() {
        try {
            // kreiramo Properties objekat
            Properties props = new Properties();
            
            // učitavanje fajla
            FileReader fr = new FileReader("src/db/konfiguracija.properties");
            props.load(fr);
            
            brojPorta = props.getProperty("brojPorta");
            korisnickoIme = props.getProperty("korisnickoIme");
            lozinka = props.getProperty("lozinka");

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    protected static DbBroker dbb = new DbBroker(brojPorta, korisnickoIme, lozinka);

    public void execute(Object obj) throws Exception {
        try {
            precondition(obj);
            startTransaction();
            executeOperation(obj);
            comitTransaction();
            System.out.println("Uspesno izvrsena operacija");
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("Greska kod izvrsavanja operacije");
            rollbackTransaction();
        } finally {
            disconnect();
        }
    }

    protected abstract void precondition(Object obj) throws Exception;

    protected abstract void executeOperation(Object obj) throws Exception;

    private void startTransaction() throws Exception {
        procitajKonfig();
        dbb.connect();
    }

    private void disconnect() throws Exception {
        dbb.disconnect();
    }

    protected void comitTransaction() throws Exception {
        dbb.commit();
    }

    protected void rollbackTransaction() throws Exception {
        dbb.rollback();
    }
}
