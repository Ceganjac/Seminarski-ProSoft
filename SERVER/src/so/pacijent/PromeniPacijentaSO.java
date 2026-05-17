/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.pacijent;

import domen.Pacijent;
import so.AbstractSO;

/**
 *
 * @author Aleksandar Čeganjac
 */
public class PromeniPacijentaSO extends AbstractSO {

    @Override
    protected void precondition(Object obj) throws Exception {
        if (obj == null) {
            throw new Exception("Objekat je null.");
        }

        if (!(obj instanceof Pacijent)) {
            throw new Exception("Objekat nije pacijent.");
        }
    }

    @Override
    protected void executeOperation(Object obj) throws Exception {

        dbb.promeni((Pacijent) obj);
    }

}
