/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controller;

import db.DbBroker;
import domen.Lekar;

/**
 *
 * @author Aleksandar Čeganjac
 */
public class ServerController {
    
    private static ServerController instanca;
    
    public static ServerController vratiInstancu(){
        if(instanca == null) instanca = new ServerController();
        return instanca;
    }
    
    public Lekar prijava(Lekar lekar){
        
        DbBroker db = new DbBroker("3306","root","root");
        
    
        return new Lekar();
        
    }

}
