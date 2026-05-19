/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import domen.Lekar;

/**
 *
 * @author Aleksandar Čeganjac
 */
public class PrijavaSO extends AbstractSO {

    private Lekar prijavljen;

    @Override
    protected void precondition(Object obj) throws Exception {
        if (obj == null) {
            throw new Exception("Objekat je NULL.");
        }
    }

    @Override
    public void executeOperation(Object obj) throws Exception {
        Lekar lekar = (Lekar) obj;
        prijavljen = (Lekar) dbb.vratiPoUslovu(lekar).get(0);
    }

    public Lekar getPrijavljen() {
        return prijavljen;
    }

}
