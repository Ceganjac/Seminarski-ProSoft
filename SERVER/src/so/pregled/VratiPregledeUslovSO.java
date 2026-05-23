/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.pregled;

import domen.Pregled;
import java.util.ArrayList;
import java.util.List;
import so.AbstractSO;

/**
 *
 * @author Aleksandar Čeganjac
 */
public class VratiPregledeUslovSO extends AbstractSO {

    private List<Pregled> pregledi = new ArrayList<>();

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

        pregledi = dbb.vratiPregledeUslov((Pregled) obj);

    }

    public List<Pregled> getPregledi() {
        return pregledi;
    }
}
