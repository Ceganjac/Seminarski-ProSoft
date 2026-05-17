/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import domen.Specijalizacija;

/**
 *
 * @author Aleksandar Čeganjac
 */

public class UbaciSpecijalizacijuSO extends AbstractSO {

    @Override
    protected void precondition(Object obj) throws Exception {

        if (obj == null) {
            throw new Exception("Objekat je NULL.");
        }

        if (!(obj instanceof Specijalizacija)) {
            throw new Exception("Objekat nije specijalizacija.");
        }
    }

    @Override
   protected void executeOperation(Object obj) throws Exception {

        dbb.ubaci((Specijalizacija) obj);
    }
}
