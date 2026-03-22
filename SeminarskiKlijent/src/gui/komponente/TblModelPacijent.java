/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.komponente;

import domen.Pacijent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Aleksandar Čeganjac
 */
public class TblModelPacijent extends AbstractTableModel {

    List<Pacijent> pacijenti;
    private String[] columnNames = {"Ид пацијента", "Име", "Презиме", "Пол", "Датум рођења",
        "Место рођења", "Мејл", "Крвна група"};

    public TblModelPacijent(List<Pacijent> pacijenti) {
        this.pacijenti = pacijenti;
    }

    @Override
    public int getRowCount() {
        return pacijenti.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Pacijent pacijent = pacijenti.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return pacijent.getIdPacijent();
            case 1:
                return pacijent.getIme();
            case 2:
                return pacijent.getPrezime();
            case 3:
                return pacijent.getPol();
            case 4:
                return pacijent.getDatumRodjenja();
            case 5:
                return pacijent.getMestoRodjenja();
            case 6:
                return pacijent.getMejl();
            case 7:
                return pacijent.getKrvnaGrupa().vratiKrvnuGrupu();
            default:
                return "n/v";
        }

    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public Pacijent getPacijent(int rowIndex) {
        return pacijenti.get(rowIndex);
    }

}
