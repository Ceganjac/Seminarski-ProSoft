/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Aleksandar Čeganjac
 */
package so.pregled;

import domen.ODObjekat;
import domen.Pregled;
import so.AbstractSO;

public class VratiPregledPoIdSO extends AbstractSO {

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

        ODObjekat od = (ODObjekat) dbb.vratiPregledeUslov((Pregled) obj);

        if (od == null) {
            throw new Exception("Pregled ne postoji.");
        }

        pregled = (Pregled) od;
        pregled.setStavke(dbb.vratiStavkeUslov(pregled));
    }

    public Pregled getPregled() {
        return pregled;
    }
}
