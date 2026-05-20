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
public class ZapamtiPregled extends AbstractSO {

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

        Pregled pregled = (Pregled) obj;

        // 1. promena pregleda
        dbb.promeni(pregled);

        // 3. ubacivanje novih stavki
        List<StavkaPregleda> stavke = pregled.getStavke();

        for (int i = 0; i < stavke.size(); i++) {
            stavke.get(i).setPregled(pregled);
            dbb.ubaci(stavke.get(i));
        }
    }

}
