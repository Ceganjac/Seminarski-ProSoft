/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package domen.enumi;

import java.io.Serializable;

/**
 *
 * @author Aleksandar Čeganjac
 */
public enum Pol implements Serializable {

    MUSKI, ZENSKI;

    @Override
    public String toString() {
        switch (this) {
            case MUSKI:
                return "МУШКИ";
            case ZENSKI:
                return "ЖЕНСКИ";
        }
        return super.toString();
    }
}
