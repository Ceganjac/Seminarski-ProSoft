/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Aleksandar Čeganjac
 */
public class Server {

    private int brojPovezanih = 0;
    boolean signal = true;
    ServerSocket serverSocket;

    public void pokreniServer() {

        try {
            serverSocket = new ServerSocket(9000);
            System.out.println("Server je podignut ...");

            while (signal) {
                Socket socket = serverSocket.accept();
                System.out.println("Klijent se povezao ...");
                brojPovezanih++;

                // kreiranje niti
                KlijentskaNit nit = new KlijentskaNit(socket, brojPovezanih);
                nit.start();
            }

        } catch (IOException ex) {
            System.getLogger(Server.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

    }

    public void zaustaviServer() {
        signal = false;
        try {
            serverSocket.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }

    }

}
