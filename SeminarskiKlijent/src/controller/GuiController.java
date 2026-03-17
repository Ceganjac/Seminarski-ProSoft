/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Aleksandar Čeganjac
 */
public class GuiController {

    private GuiController instanca;
    private Socket soket;
    private ObjectOutputStream izlazni;
    private ObjectInputStream ulazni;

    // konstruktor
    public GuiController() throws IOException {
        soket = new Socket("localhost", 9000);
        izlazni = new ObjectOutputStream(soket.getOutputStream());
        ulazni = new ObjectInputStream(soket.getInputStream());
    }

    // obezbeđuje singleton
    public GuiController getInstanca() throws IOException {
        if (instanca != null) {
            instanca = new GuiController();
        }
        return instanca;
    }

}
