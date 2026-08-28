/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.pacijent;

import domen.ODObjekat;
import domen.Pacijent;
import java.util.List;
import so.AbstractSO;

/**
 *
 * @author Aleksandar Čeganjac
 */
public class VratiPacijentaPoId extends AbstractSO {

    private Pacijent pacijent;

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

        pacijent = (Pacijent) dbBroker.selectObject((Pacijent)obj);
     
    }

    public Pacijent getPacijent() {
        return pacijent;
    }
}
