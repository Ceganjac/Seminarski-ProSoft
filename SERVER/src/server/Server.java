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

    public static Server vratiInstancu() {
        if (instanca == null) {
            instanca = new Server();
        }
        return instanca;
    }

    public void pokreniServer() throws IOException {

        serverSocket = new ServerSocket(9000);
        System.out.println("Server pokrenut...");

        while (!serverSocket.isClosed()) {
            try {
                Socket socket = serverSocket.accept();

                // za svakog klijenta nova nit
                KlijentskaNit nit = new KlijentskaNit(socket, brojPovezanih);
                nit.start();

            } catch (IOException e) {

                // ako je zatvoren server → normalno gašenje
                if (serverSocket.isClosed()) {
                    System.out.println("Server zaustavljen.");
                    break;
                }

                // neka druga greška
                e.printStackTrace();
            }
        }
    }

    public void zaustaviServer() throws IOException {
        signal = false;
        serverSocket.close();
        System.out.println("Server je zaistavljen");

    }

}
