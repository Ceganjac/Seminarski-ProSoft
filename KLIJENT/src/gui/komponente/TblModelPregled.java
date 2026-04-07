/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.komponente;

import domen.Pregled;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Aleksandar Čeganjac
 */
public class TblModelPregled extends AbstractTableModel {

    private final List<Pregled> pregledi;
    private final String[] columnNames = {"ИД прегледа", "Датум завршетка", "Датум контроле", "Укупно трајање", "Терапија", "Лекар", "Пацијент"};

    public TblModelPregled(List<Pregled> pregledi) {
        this.pregledi = pregledi;
    }

    @Override
    public int getRowCount() {
        return pregledi.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Pregled pregled = pregledi.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return pregled.getIdPregled();
            case 1:
                return pregled.getDatumVremeZavrsetka();
            case 2:
                return pregled.getDatumVremeKontrole();
            case 3:
                return pregled.getUkupnoVremeTrajanja();
            case 4:
                return pregled.getTerapija();
            case 5:
                return pregled.getLekar().vrartiImePrezime();
            case 6:
                return pregled.getPacijent().vratiImePrezime();
            default:
                return "н/в";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public Pregled getPregled(int rowIndex) {
        return pregledi.get(rowIndex);
    }
}
