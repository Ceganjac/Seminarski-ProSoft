/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import domen.enumi.Pol;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aleksandar Čeganjac
 */
public class Pacijent implements ODObjekat {

    private int idPacijent;
    private String ime;
    private String prezime;
    private Pol pol;
    private LocalDate datumRodjenja;
    private String mestoRodjenja;
    private String mejl;
    KrvnaGrupa krvnaGrupa;

    public Pacijent() {
    }

    public Pacijent(int idPacijent, String ime, String prezime, Pol pol,
            LocalDate datumRodjenja, String mestoRodjenja, String mejl, KrvnaGrupa krvnaGrupa) {
        this.idPacijent = idPacijent;
        this.ime = ime;
        this.prezime = prezime;
        this.pol = pol;
        this.datumRodjenja = datumRodjenja;
        this.mestoRodjenja = mestoRodjenja;
        this.mejl = mejl;
        this.krvnaGrupa = krvnaGrupa;
    }

    public int getIdPacijent() {
        return idPacijent;
    }

    public void setIdPacijent(int idPacijent) {
        this.idPacijent = idPacijent;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Pol getPol() {
        return pol;
    }

    public void setPol(Pol pol) {
        this.pol = pol;
    }

    public LocalDate getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(LocalDate datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public String getMestoRodjenja() {
        return mestoRodjenja;
    }

    public void setMestoRodjenja(String mestoRodjenja) {
        this.mestoRodjenja = mestoRodjenja;
    }

    public String getMejl() {
        return mejl;
    }

    public void setMejl(String mejl) {
        this.mejl = mejl;
    }

    public KrvnaGrupa getKrvnaGrupa() {
        return krvnaGrupa;
    }

    public void setKrvnaGrupa(KrvnaGrupa krvnaGrupa) {
        this.krvnaGrupa = krvnaGrupa;
    }

    // -------------------------------------------------- //
    // vrati ime i prezime
    public String vratiImePrezime() {
        return ime + " " + prezime;
    }

    // bitan za cmb
    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public String tableName() {
        return "pacijent";
    }

    @Override
    public String alies() {
        return "pac";
    }

    @Override
    public String textJoin() {
        return " JOIN krvna_grupa krv ON (krv.id_krvna_grupa = pac.id_krvna_grupa)";
    }

    @Override
    public String insertColumns() {
        return "(ime, prezime, pol, datum_rodjenja, mesto_rodjenja, mejl, id_krvna_grupa)";
    }

    @Override
    public String insertValues() {
        return "'" + ime + "', '" + prezime + "', '" + pol.name() + "', '" + datumRodjenja + "', '" + mestoRodjenja + "', '" + mejl + "', " + krvnaGrupa.getIdKrvnaGrupa();
    }

    @Override
    public String updateValues() {
        return "ime = '" + ime + "', prezime= '" + prezime + "', pol='" + pol.name() + "', datum_rodjenja='" + datumRodjenja + "', mesto_rodjenja='" + mestoRodjenja + "', mejl='" + mejl + "', id_krvna_grupa = " + krvnaGrupa.getIdKrvnaGrupa();
    }

    @Override
    public String requiredCondition() {
        return "id_pacijent = " + idPacijent;
    }

    @Override
    public String conditionForSelect() {
        if (krvnaGrupa != null) {
            String sif2Condition = krvnaGrupa.conditionForSelect();
            if (!sif2Condition.isEmpty()) {
                return sif2Condition;
            }
        }
        List<String> filteri = new ArrayList<>();
        if (ime != null && !ime.isEmpty()) {
            filteri.add(" pac.ime LIKE '%" + ime + "%'");
        }
        if (prezime != null && !prezime.isEmpty()) {
            filteri.add(" pac.prezime LIKE '%" + prezime + "%'");
        }
        if (mejl != null && !mejl.isEmpty()) {
            filteri.add(" pac.mejl LIKE '%" + mejl + "%'");
        }
        if (pol != null) {
            filteri.add(" pac.pol LIKE '%" + pol.name() + "%'");
        }
        if (datumRodjenja != null) {
            filteri.add("pac.datum_rodjenja LIKE '%" + datumRodjenja + "%'");
        }
        if (mestoRodjenja != null) {
            filteri.add("pac.mesto_rodjenja LIKE '%" + mestoRodjenja + "%'");
        }

        return !filteri.isEmpty() ? " WHERE " + String.join(" AND ", filteri) : "";
    }

    @Override
    public String getCondition() {
        return "WHERE " + alies() + ".id_pacijent=" + idPacijent;
    }

    @Override
    public ArrayList<ODObjekat> getList(ResultSet rs) throws SQLException {
        ArrayList<ODObjekat> lista = new ArrayList<>();
        while (rs.next()) {

            Pacijent pacijent = new Pacijent();

            pacijent.setIdPacijent(rs.getInt("pac.id_pacijent"));
            pacijent.setIme(rs.getString("pac.ime"));
            pacijent.setPrezime(rs.getString("pac.prezime"));

            String polStr = rs.getString("pac.pol");
            pacijent.setPol(polStr != null ? Pol.valueOf(polStr) : null);

            java.sql.Date datum = rs.getDate("pac.datum_rodjenja");
            pacijent.setDatumRodjenja(datum != null ? datum.toLocalDate() : null);

            pacijent.setMestoRodjenja(rs.getString("pac.mesto_rodjenja"));
            pacijent.setMejl(rs.getString("pac.mejl"));
            
            KrvnaGrupa krvnaGrupa = new KrvnaGrupa(rs.getInt("krv.id_krvna_grupa"),rs.getString("krv.abo_tip"), rs.getString("krv.rh_faktor"));
            pacijent.setKrvnaGrupa(krvnaGrupa);
            lista.add(pacijent);
        }
        rs.close();
        return lista;

    }


    

}
