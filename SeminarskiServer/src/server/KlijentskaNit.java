/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.net.Socket;

/**
 *
 * @author Aleksandar Čeganjac
 */
public class KlijentskaNit extends Thread {

    private Socket socket;
    private int redniBrPovezanog;

    public KlijentskaNit(Socket socket, int redniBrPovezanog) {
        this.socket = socket;
        this.redniBrPovezanog = redniBrPovezanog;
    }

    public void run() {
        
    }

}
