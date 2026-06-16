/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import domen.ODObjekat;
import java.sql.*;
import java.util.List;

/**
 *
 * @author Korisnik
 */
public class DbBroker {

    private final Konekcija konekcija;

    public DbBroker(Konekcija konekcija) {
        this.konekcija = konekcija;
    }
    

    public int insert(ODObjekat ado) throws Exception {

        int id = -1;

        String upit = "INSERT INTO " + ado.tableName() + " "
                + ado.insertColumns() + " VALUES(" + ado.insertValues() + ")";

        System.out.println(upit);
        Statement s = konekcija.getKonekcija().createStatement();
        s.executeUpdate(upit, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = s.getGeneratedKeys();

        if (rs.next()) {
            id = rs.getInt(1);
        }
        rs.close();
        s.close();

        return id;
    }

    public List<ODObjekat> selectList(ODObjekat ado) throws Exception {

        String upit = "SELECT * FROM " + ado.tableName() + " " + ado.alies()
                + " " + ado.textJoin() + " " + ado.conditionForSelect();
        System.out.println(upit);
        Statement s =konekcija.getKonekcija().createStatement();
        ResultSet rs = s.executeQuery(upit);
        return ado.getList(rs);

    }

    public ODObjekat selectObject(ODObjekat ado) throws Exception {

        String upit = " SELECT * FROM " + ado.tableName() + " " + ado.alies() + " "
                + ado.textJoin() + " " + " " + ado.getCondition();
        System.out.println(upit);
        Statement s =konekcija.getKonekcija().createStatement();
        ResultSet rs = s.executeQuery(upit);
        List<ODObjekat> lista = ado.getList(rs);
        if (lista.isEmpty()) {
            return null;
        } else {
            return lista.get(0);
        }
    }

    public int update(ODObjekat ado) throws Exception {

        String upit = "UPDATE " + ado.tableName() + " SET "
                + ado.updateValues() + " WHERE " + ado.requiredCondition();
        System.out.println(upit);
        Statement s =konekcija.getKonekcija().createStatement();
        int affectedRows = s.executeUpdate(upit);
        return affectedRows;

    }

    public int delete(ODObjekat ado) throws Exception {

        String upit = "DELETE FROM " + ado.tableName() + " WHERE " + ado.requiredCondition();
        System.out.println(upit);
        Statement s =konekcija.getKonekcija().createStatement();
        int affectedRows = s.executeUpdate(upit);
        return affectedRows;

    }

}
