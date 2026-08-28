/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package domen;

/**
 *
 * @author Aleksandar Čeganjac
 */
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ODObjekat extends Serializable {

    public abstract String tableName();
    public abstract String alies();
    public abstract String textJoin();
    public abstract String insertColumns();
    public abstract String insertValues();
    public abstract String updateValues();
    public abstract String requiredCondition();
    public abstract String conditionForSelect();
    public abstract String getCondition();
    public abstract ArrayList<ODObjekat> getList(ResultSet rs) throws SQLException;
   
}
