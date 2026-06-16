/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Korisnik
 */
public class Konekcija {
    
    private String brojPorta;
    private String korisnickoIme;
    private String lozinka;
    private Connection konekcija;
    public Konekcija(){
        procitaj();
    }
    
    
    public void procitaj(){
    
         try {
            Properties props = new Properties();
            FileReader fr = new FileReader("src/db/konfig.properties");
            props.load(fr);

            brojPorta = props.getProperty("brojPorta");
            korisnickoIme = props.getProperty("korisnickoIme");
            lozinka = props.getProperty("lozinka");
            
            fr.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    
    }
    
     public void connect() throws SQLException {
        String url = "jdbc:mysql://localhost:" + brojPorta + "/ceganjac";
        konekcija = DriverManager.getConnection(url,korisnickoIme, lozinka);
        konekcija.setAutoCommit(false);

    }

    public void disconnect() throws SQLException {
        if (konekcija != null && !konekcija.isClosed()) {
            konekcija.close();
        }

    }
    
        public void commit() throws SQLException {
        if (konekcija != null) {
            konekcija.commit();
        }
    }

    public void rollback() throws SQLException {
        if (konekcija != null) {
            konekcija.rollback();
        }
    }

    public Connection getKonekcija() {
        return konekcija;
    }
    
    
    
}
