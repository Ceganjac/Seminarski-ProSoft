package so;

import db.DbBroker;
import db.Konekcija;

public abstract class AbstractSO {

    private Konekcija connection;
    protected DbBroker dbBroker;

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
        connection = new Konekcija();
        connection.connect();
        dbBroker = new DbBroker(connection);
    }

    private void disconnect() throws Exception {
        connection.disconnect();
    }

    protected void commitTransaction() throws Exception {
        connection.commit();
    }

    protected void rollbackTransaction() throws Exception {
        connection.rollback();
    }

    protected abstract void precondition(Object obj) throws Exception;

    protected abstract void executeOperation(Object obj) throws Exception;
}
