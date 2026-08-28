/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import db.DbBroker;
import db.Konekcija;
import domen.Specijalizacija;

/**
 *
 * @author Aleksandar Čeganjac
 */
public class UbaciSpecijalizacijuSO {

    Konekcija connection;
    DbBroker dbBroker;

    public UbaciSpecijalizacijuSO() {
        connection = new Konekcija();
        dbBroker = new DbBroker(connection);
    }

    protected void precondition(Object obj) throws Exception {

        if (obj == null) {
            throw new Exception("Objekat je NULL.");
        }

        if (!(obj instanceof Specijalizacija)) {
            throw new Exception("Objekat nije specijalizacija.");
        }
    }

    private void startTransaction() throws Exception {
        connection.connect();
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

    protected void executeOperation(Object obj) throws Exception {

        dbBroker.insert((Specijalizacija) obj);
    }
}
