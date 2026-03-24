/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import gui.GlavniFrejm;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Aleksandar Čeganjac
 */
public class MainClass {

    public static void main(String args[]) {
        
        GlavniFrejm frejm = new GlavniFrejm();
        frejm.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frejm.setLocationRelativeTo(null);
        frejm.setVisible(true);
        

    }

}
