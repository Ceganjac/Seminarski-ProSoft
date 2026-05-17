/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Aleksandar Čeganjac
 */
package so;

import domen.ODObjekat;
import domen.Pregled;
import java.util.ArrayList;

import java.util.List;

public class VratiSvePregledeSO extends AbstractSO {

    private List<Pregled> pregledi;

    @Override
    protected void precondition(Object obj) throws Exception {
        if (obj == null) {
            throw new Exception("Objekat je null.");
        }
    }

    @Override
    protected void executeOperation(Object obj) throws Exception {

        List<ODObjekat> lista = dbb.vratiSve(new Pregled());

        pregledi = new ArrayList<>();

        for (ODObjekat od : lista) {
            pregledi.add((Pregled) od);
        }
    }

    public List<Pregled> getPregledi() {
        return pregledi;
    }
}
