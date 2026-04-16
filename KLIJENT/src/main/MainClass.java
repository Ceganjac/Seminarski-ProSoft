/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import gui.forme.PrijavaDialog;
import javax.swing.JDialog;
import javax.swing.SwingUtilities;
import gui.forme.PrijavaDialog;

/**
 *
 * @author Aleksandar Čeganjac
 */
public class MainClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            PrijavaDialog dialog = new PrijavaDialog(null, true);
            dialog.setLocationRelativeTo(null);
            dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
            dialog.setVisible(true);
            
        });

    }

}
