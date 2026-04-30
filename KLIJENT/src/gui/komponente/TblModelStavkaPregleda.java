/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.komponente;

import domen.StavkaPregleda;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TblModelStavkaPregleda extends AbstractTableModel {

    private final List<StavkaPregleda> stavke;
    private final String[] columnNames = {"Назив", "Лекарски налаз", "Трајање (мин)", "Дијагноза"};

    public TblModelStavkaPregleda(List<StavkaPregleda> stavke) {
        this.stavke = stavke;
    }

    @Override
    public int getRowCount() {
        return stavke.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StavkaPregleda stavka = stavke.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return stavka.getNaziv();
            case 1:
                return stavka.getLekarskiNalaz();
            case 2:
                return stavka.getVremeTrajanja().toMinutes();
            case 3:
                return stavka.getDijagnoza().getSrpskiNaziv();
            default:
                return "н/в";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public StavkaPregleda getStavka(int rowIndex) {
        return stavke.get(rowIndex);
    }

    public List<StavkaPregleda> getStavke() {
        return stavke;
    }

    public void dodajStavku(StavkaPregleda stavka) {
        stavke.add(stavka);
        int poslednjiRed = stavke.size() - 1;
        fireTableRowsInserted(poslednjiRed, poslednjiRed);
    }

    public void izbrisiStavku(int rowIndex) {
        stavke.remove(rowIndex);
        int poslednjiRed = stavke.size() - 1;
        fireTableRowsInserted(poslednjiRed, poslednjiRed);
    }

}
