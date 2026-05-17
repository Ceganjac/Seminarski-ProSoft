/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import domen.KrvnaGrupa;
import domen.ODObjekat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aleksandar Čeganjac
 */

public class VratiSveKrvneGrupeSO extends AbstractSO {

    private List<KrvnaGrupa> krvneGrupe;

    @Override
    protected void precondition(Object obj) throws Exception {
        if (obj == null) {
            throw new Exception("Objekat je NULL.");
        }
    }

    @Override
    protected void executeOperation(Object obj) throws Exception {

        List<ODObjekat> lista = dbb.vratiSve(new KrvnaGrupa());

        krvneGrupe = new ArrayList<>();

        for (ODObjekat od : lista) {
            krvneGrupe.add((KrvnaGrupa) od);
        }
    }

    public List<KrvnaGrupa> getKrvneGrupe() {
        return krvneGrupe;
    }
}
