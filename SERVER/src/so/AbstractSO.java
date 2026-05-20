package so;

import db.DbBroker;

public abstract class AbstractSO {

    protected static DbBroker dbb;
    protected static String brojPorta;
    protected static String korisnickoIme;
    protected static String lozinka;

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

        ProcitajKonfig.procitaj();
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
