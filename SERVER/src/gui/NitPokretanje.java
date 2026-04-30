/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import server.Server;

/**
 *
 * @author Aleksandar Čeganjac
 */
public class NitPokretanje implements Runnable {

    JLabel lblStatus;
    JFrame parent;

    public NitPokretanje(JFrame parent, JLabel lblStatus) {
        this.parent = parent;
        this.lblStatus = lblStatus;
    }

    @Override
    public void run() {
        try {
          
            Server.vratiInstancu().pokreniServer(lblStatus);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(parent,
                    "Грешка приликом покретања сервера !", "ГРЕШКА", JOptionPane.ERROR_MESSAGE
            );

        }

    }
}
