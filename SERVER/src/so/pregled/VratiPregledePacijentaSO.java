package so.pregled;

import domen.Pacijent;
import domen.Pregled;
import so.AbstractSO;
import java.util.List;

public class VratiPregledePacijentaSO extends AbstractSO {

    private List<Pregled> pregledi;

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

        Pacijent pacijent = (Pacijent) obj;
        pregledi = dbBroker.vratiPregledePacijenta(pacijent);
    }

    public List<Pregled> getPregledi() {
        return pregledi;
    }
}