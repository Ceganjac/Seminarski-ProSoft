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
    private final String[] columnNames = {"ИД ставке", "Назив", "Лекарски налаз", "Трајање (мин)", "Дијагноза"};

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
                return stavka.getIdStavkaPregleda();
            case 1:
                return stavka.getNaziv();
            case 2:
                return stavka.getLekarskiNalaz();
            case 3:
                return stavka.getVremeTrajanja().toMinutes(); // prikaz u minutima
            case 4:
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

}
