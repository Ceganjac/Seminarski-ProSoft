/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import domen.ODObjekat;
import domen.Pacijent;
import domen.Pregled;
import java.sql.*;
import java.util.ArrayList;
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
    

    public int insert(ODObjekat odo) throws Exception {

        int id = -1;

        String upit = "INSERT INTO " + odo.tableName() + " "
                + odo.insertColumns() + " VALUES(" + odo.insertValues() + ")";

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

    public List<ODObjekat> selectList(ODObjekat odo) throws Exception {
        // šta radi odo.alies()
        String upit = "SELECT * FROM " + odo.tableName() + " " + odo.alies()
                + " " + odo.textJoin() + " " + odo.conditionForSelect();
        System.out.println(upit);
        Statement s =konekcija.getKonekcija().createStatement();
        ResultSet rs = s.executeQuery(upit);
        return odo.getList(rs);

    }

    public ODObjekat selectObject(ODObjekat odo) throws Exception {
        // 
        String upit = " SELECT * FROM " + odo.tableName() + " " + odo.alies() + " "
                + odo.textJoin() + " " + " " + odo.getCondition();
        System.out.println(upit);
        Statement s =konekcija.getKonekcija().createStatement();
        ResultSet rs = s.executeQuery(upit);
        List<ODObjekat> lista = odo.getList(rs);
        if (lista.isEmpty()) {
            return null;
        } else {
            return lista.get(0);
        }
    }

    public int update(ODObjekat odo) throws Exception {

        String upit = "UPDATE " + odo.tableName() + " SET "
                + odo.updateValues() + " WHERE " + odo.requiredCondition();
        System.out.println(upit);
        Statement s =konekcija.getKonekcija().createStatement();
        int affectedRows = s.executeUpdate(upit);
        return affectedRows;

    }

    public int delete(ODObjekat odo) throws Exception {

        String upit = "DELETE FROM " + odo.tableName() + " WHERE " + odo.requiredCondition();
        System.out.println(upit);
        Statement s =konekcija.getKonekcija().createStatement();
        int affectedRows = s.executeUpdate(upit);
        return affectedRows;

    }
    
    public List<Pregled> vratiPregledePacijenta(Pacijent pacijent) throws Exception {

        String upit = "SELECT * FROM pregled WHERE id_pacijent = ?";
        PreparedStatement ps = konekcija.getKonekcija().prepareStatement(upit);
        ps.setInt(1, pacijent.getIdPacijent());
        ResultSet rs = ps.executeQuery();
        
        List<Pregled> pregledi = new ArrayList();
        
        while(rs.next()){
           Pregled p = new Pregled();
           p.setIdPregled(rs.getInt("id_pregled"));
           p.setTerapija(rs.getString("terapija"));
           pregledi.add(p);
        }
        
        return pregledi;
    }
    
    

}
