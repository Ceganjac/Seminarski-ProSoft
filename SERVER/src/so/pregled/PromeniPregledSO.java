/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.pregled;

import domen.ODObjekat;
import domen.Pregled;
import domen.StavkaPregleda;
import java.util.ArrayList;
import java.util.List;
import so.AbstractSO;
import so.DML;
import static so.DML.DELETE;
import static so.DML.INSERT;
import static so.DML.UPDATE;

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

    private void izvrsiUpit(DML dml, StavkaPregleda item) throws Exception {
     
            switch (dml) {
                case INSERT:
                    dbBroker.insert(item);
                    break;
                case UPDATE:
                    dbBroker.update(item);
                    break;
                case DELETE:
                    dbBroker.delete(item);
                    break;
            }
          
        }

        @Override
        protected void executeOperation
        (Object obj) throws Exception {

            try{
            ODObjekat ado = (ODObjekat) obj;
            dbBroker.update(ado);
            Pregled pregledKlijent = (Pregled) obj;
            Pregled pregledBaza = new Pregled();
            pregledBaza.setIdPregled(pregledKlijent.getIdPregled());
            pregledBaza = (Pregled) dbBroker.selectObject(pregledBaza);
              if(pregledBaza==null){
          pregledBaza  = new Pregled();
          pregledBaza.setStavke(new ArrayList<>());
        }
            izvrsiUpdateStavki(pregledKlijent.getStavke(), pregledBaza.getStavke());
            }catch(Exception ex){
                ex.printStackTrace();
                throw ex;
            }

        }
    
      

    private void izvrsiUpdateStavki(List<StavkaPregleda> stavkeKlijent, List<StavkaPregleda> stavkeBaza) throws Exception {
        for (StavkaPregleda stavkaKlijent : stavkeKlijent) {
            if (!stavkeBaza.contains(stavkaKlijent)) {
                izvrsiUpit(DML.INSERT, stavkaKlijent);
            } else {
                izvrsiUpit(DML.UPDATE, stavkaKlijent);
            }
        }

        for (StavkaPregleda stavkaBaza : stavkeBaza) {
            if (!stavkeKlijent.contains(stavkaBaza)) {
                izvrsiUpit(DML.DELETE, stavkaBaza);
            }
        }
    }

    public Pregled getPregled() {
        return pregled;
    }
}
