/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import domen.Lekar;
import domen.ODObjekat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aleksandar Čeganjac
 */
public class VratiSveLekareSO extends AbstractSO {

    private List<Lekar> lekari = new ArrayList();

    @Override
    protected void precondition(Object obj) throws Exception {
        if (obj == null) {
            throw new Exception("Objekat je null.");
        }
    }

    @Override
    protected void executeOperation(Object obj) throws Exception {

         ODObjekat ado = (ODObjekat) obj;
        List<ODObjekat> lista = dbBroker.selectList(ado);
        lekari = (ArrayList<Lekar>)(ArrayList<?>)lista;
        
    }

    public List<Lekar> getLekari() {
        return lekari;
    }
}
