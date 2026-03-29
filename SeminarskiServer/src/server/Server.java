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
    private boolean signal = true;
    private ServerSocket serverSocket;
    private static Server instanca;
    
    public static Server vratiInstancu(){
        if(instanca == null) instanca = new Server();
        return instanca;
    }
    
    public void pokreniServer() throws IOException {

        serverSocket = new ServerSocket(9000);
        System.out.println("Server je podignut ...");

        while (signal) {
            Socket socket = serverSocket.accept();
            brojPovezanih++;
            System.out.println("Klijent broj" + brojPovezanih
                    + ". se povezao ...");

            // kreiranje niti
            KlijentskaNit nit = new KlijentskaNit(socket, brojPovezanih);
            nit.start();
        }

    }

    public void zaustaviServer() throws IOException {
        signal = false;
        serverSocket.close();
        System.out.println("Server je zaistavljen");

    }

}
