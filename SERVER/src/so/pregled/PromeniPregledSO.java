/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.pregled;

import domen.Pregled;
import domen.StavkaPregleda;
import java.util.List;
import so.AbstractSO;

/**
 *
 * @author Aleksandar Čeganjac
 */
public class PromeniPregledSO extends AbstractSO {

    private Pregled pregled;

    @Override
    protected void precondition(Object obj) throws Exception {
        if (obj == null) {
            throw new Exception("Objekat je null.");
        }

        if (!(obj instanceof Pregled)) {
            throw new Exception("Objekat nije pregled.");
        }
    }

    @Override
    protected void executeOperation(Object obj) throws Exception {

       //TODO
    }

    public Pregled getPregled() {
        return pregled;
    }
}
