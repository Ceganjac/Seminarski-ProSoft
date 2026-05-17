/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.pacijent;

import domen.ODObjekat;
import domen.Pacijent;
import java.util.ArrayList;
import java.util.List;
import so.AbstractSO;

/**
 *
 * @author Aleksandar Čeganjac
 */
public class VratiSvePacijenteSO extends AbstractSO {

    private List<Pacijent> pacijenti;

    @Override
    protected void precondition(Object obj) throws Exception {
        if (obj == null) {
            throw new Exception("Objekat je null.");
        }
    }

    @Override
    protected void executeOperation(Object obj) throws Exception {

        List<ODObjekat> lista = dbb.vratiSve(new Pacijent());

        pacijenti = new ArrayList<>();

        for (ODObjekat od : lista) {
            pacijenti.add((Pacijent) od);
        }
    }

    public List<Pacijent> getPacijenti() {
        return pacijenti;
    }
}
