/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package so;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import static so.AbstractSO.brojPorta;
import static so.AbstractSO.korisnickoIme;
import static so.AbstractSO.lozinka;

/**
 *
 * @author Aleksandar Čeganjac
 */
public class ProcitajKonfig {
    
    public static void procitaj(){
    
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

}
