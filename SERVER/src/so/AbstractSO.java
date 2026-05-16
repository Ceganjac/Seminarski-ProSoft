package so;

import db.DbBroker;
import java.io.FileReader;
import java.util.Properties;

public abstract class AbstractSO {

    protected static DbBroker dbb;
    protected static String brojPorta;
    protected static String korisnickoIme;
    protected static String lozinka;

    private void procitajKonfig() {
        try {
            Properties props = new Properties();
            FileReader fr = new FileReader("db/konfiguracija.properties");
            props.load(fr);

            brojPorta = props.getProperty("brojPorta");
            korisnickoIme = props.getProperty("korisnickoIme");
            lozinka = props.getProperty("lozinka");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void execute(Object obj) throws Exception {
        try {
            precondition(obj);
            startTransaction();
            executeOperation(obj);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        } finally {
            disconnect();
        }
    }

    private void startTransaction() throws Exception {
        procitajKonfig();

        dbb = new DbBroker(brojPorta, korisnickoIme, lozinka);
        dbb.connect();
    }

    private void disconnect() throws Exception {
        dbb.disconnect();
    }

    protected void commitTransaction() throws Exception {
        dbb.commit();
    }

    protected void rollbackTransaction() throws Exception {
        dbb.rollback();
    }

    protected abstract void precondition(Object obj) throws Exception;

    protected abstract void executeOperation(Object obj) throws Exception;
}
