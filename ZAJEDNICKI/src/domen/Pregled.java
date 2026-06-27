/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import domen.enumi.Pol;
import java.sql.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Aleksandar Čeganjac
 */
public class Pregled implements ODObjekat {

    private int idPregled;

    private LocalDateTime datumVremeZavrsetka;
    private LocalDate datumKontrole;
    private LocalTime vremeKontrole;
    private Duration ukupnoVremeTrajanja;
    private String terapija;

    private Lekar lekar;
    private Pacijent pacijent;
    private List<StavkaPregleda> stavke = new ArrayList<>();

    public Pregled() {
    }

    public Pregled(int idPregled, LocalDateTime datumVremeZavrsetka, LocalDate datumKontrole, LocalTime vremeKontrole,
            Duration ukupnoVremeTrajanja, String terapija, Lekar lekar, Pacijent pacijent) {

        this.idPregled = idPregled;
        this.datumVremeZavrsetka = datumVremeZavrsetka;
        this.datumKontrole = datumKontrole;
        this.vremeKontrole = vremeKontrole;
        this.ukupnoVremeTrajanja = ukupnoVremeTrajanja;
        this.terapija = terapija;
        this.lekar = lekar;
        this.pacijent = pacijent;
    }

    // GET / SET
    public int getIdPregled() {
        return idPregled;
    }

    public void setIdPregled(int idPregled) {
        this.idPregled = idPregled;
    }

    public LocalDateTime getDatumVremeZavrsetka() {
        return datumVremeZavrsetka;
    }

    public void setDatumVremeZavrsetka(LocalDateTime datumVremeZavrsetka) {
        this.datumVremeZavrsetka = datumVremeZavrsetka;
    }

    public LocalDate getDatumKontrole() {
        return datumKontrole;
    }

    public void setDatumKontrole(LocalDate datumKontrole) {
        this.datumKontrole = datumKontrole;
    }

    public LocalTime getVremeKontrole() {
        return vremeKontrole;
    }

    public void setVremeKontrole(LocalTime vremeKontrole) {
        this.vremeKontrole = vremeKontrole;
    }

    public Duration getUkupnoVremeTrajanja() {
        return ukupnoVremeTrajanja;
    }

    public void setUkupnoVremeTrajanja(Duration ukupnoVremeTrajanja) {
        this.ukupnoVremeTrajanja = ukupnoVremeTrajanja;
    }

    public String getTerapija() {
        return terapija;
    }

    public void setTerapija(String terapija) {
        this.terapija = terapija;
    }

    public Lekar getLekar() {
        return lekar;
    }

    public void setLekar(Lekar lekar) {
        this.lekar = lekar;
    }

    public Pacijent getPacijent() {
        return pacijent;
    }

    public void setPacijent(Pacijent pacijent) {
        this.pacijent = pacijent;
    }

    public List<StavkaPregleda> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaPregleda> stavke) {
        this.stavke = stavke;
    }

    @Override
    public String tableName() {
        return "pregled";
    }

    @Override
    public String alies() {
        return "pr";
    }

    @Override
    public String textJoin() {

        return "JOIN lekar lek ON lek.id_lekar=pr.id_lekar\n"
                + " JOIN pacijent pac ON pac.id_pacijent=pr.id_pacijent\n"
                + " JOIN krvna_grupa krv ON krv.id_krvna_grupa=pac.id_krvna_grupa\n"
                + " JOIN stavka_pregleda sp ON sp.id_pregled=pr.id_pregled\n"
                + " JOIN dijagnoza dij ON dij.id_dijagnoza=sp.id_dijagnoza";
    }

    @Override
    public String insertColumns() {

        return "(datum_vreme_zavrsetka, datum_kontrole, vreme_kontrole,ukupno_vreme_trajanja, terapija, id_lekar, id_pacijent)";
    }

    @Override
    public String insertValues() {
        return "'" + datumVremeZavrsetka.toString().replace("T", " ") + "', '" + datumKontrole.toString() + "', '" + vremeKontrole.toString() + "', " + ukupnoVremeTrajanja.toMinutes() + ", '" + terapija + "'," + lekar.getIdLekar() + ", " + pacijent.getIdPacijent();
    }

    @Override
    public String updateValues() {
        return "datum_vreme_zavrsetka = '" + datumVremeZavrsetka.toString().replace("T", " ") + "', datum_kontrole='" + datumKontrole.toString() + "',vreme_kontrole='" + vremeKontrole.toString() + "', terapija='" + terapija + "', id_lekar=" + lekar.getIdLekar() + ", id_pacijent=" + pacijent.getIdPacijent();
    }

    @Override
    public String requiredCondition() {
        return "id_pregled = " + idPregled;

    }

    @Override
    public String conditionForSelect() {
        List<String> filteri = new ArrayList<>();
        String dijagnozaFilter = "";
        if (pacijent != null) {
            filteri.add("pac.id_pacijent = " + pacijent.getIdPacijent());
        }

        if (lekar != null) {

            filteri.add("lek.id_lekar = " + lekar.getIdLekar());
        }
        if (!stavke.isEmpty() && stavke.size() == 1) {
            dijagnozaFilter = "WHERE  pr.id_pregled IN (\n"
                    + "    SELECT pr2.id_pregled\n"
                    + "    FROM pregled pr2\n"
                    + "    JOIN stavka_pregleda sp2 ON sp2.id_pregled = pr2.id_pregled\n"
                    + "    JOIN dijagnoza dij2 ON dij2.id_dijagnoza = sp2.id_dijagnoza \n"
                    + vratiDokumentKriterijumSifarnik1(stavke.get(0).getDijagnoza()) + ")";
            dijagnozaFilter = !dijagnozaFilter.isEmpty() ? dijagnozaFilter.substring(6) : "";
            if (!dijagnozaFilter.isEmpty()) {
                filteri.add(dijagnozaFilter);
            }

        }

        if (idPregled != 0) {
            filteri.add("pr.id_pregled = " + idPregled);
        }
        return !filteri.isEmpty() ? " WHERE " + String.join(" AND ", filteri) : "";
    }

    @Override
    public String getCondition() {
        return "WHERE pr.id_pregled = " + idPregled;
    }

    @Override
    public ArrayList<ODObjekat> getList(ResultSet rs) throws SQLException {
        ArrayList<ODObjekat> lista = new ArrayList<>();
        Map<Integer, Pregled> objekti = new HashMap<>();

        while (rs.next()) {

            int idDokumenta = rs.getInt("pr.id_pregled");
            Pregled pregled = objekti.get(idDokumenta);

            if (pregled == null) {
                LocalDateTime datumVremeZavrsetka = rs.getTimestamp("pr.datum_vreme_zavrsetka") != null ? rs.getTimestamp("pr.datum_vreme_zavrsetka").toLocalDateTime() : null;
                LocalDate datumKontrole = rs.getDate("pr.datum_kontrole") != null ? rs.getDate("pr.datum_kontrole").toLocalDate() : null;
                LocalTime vremeKontrole = rs.getTime("pr.vreme_kontrole") != null ? rs.getTime("pr.vreme_kontrole").toLocalTime() : null;
                int minuti = rs.getInt("ukupno_vreme_trajanja");
                Duration ukupnoVremeTrajanja = Duration.ofMinutes((long) minuti);
                String terapija = rs.getString("terapija");

                Lekar lekar = new Lekar();
                lekar.setIdLekar(rs.getInt("lek.id_lekar"));
                lekar.setIme(rs.getString("lek.ime"));
                lekar.setPrezime(rs.getString("lek.prezime"));
                lekar.setPol(Pol.valueOf(rs.getString("lek.pol")));
                java.sql.Date datumRodjenjaLekara = rs.getDate("lek.datum_rodjenja");
                lekar.setDatumRodjenja(datumRodjenjaLekara != null ? datumRodjenjaLekara.toLocalDate() : null);
                lekar.setKorisnickoIme(rs.getString("lek.korisnicko_ime"));
                lekar.setLozinka(rs.getString("lek.lozinka"));

                int idKrvneGrupe = rs.getInt("krv.id_krvna_grupa");
                String abo = rs.getString("krv.abo_tip");
                String rh = rs.getString("krv.rh_faktor");

                KrvnaGrupa kg = new KrvnaGrupa(idKrvneGrupe, abo, rh);

                Pacijent pacijent = new Pacijent();

                pacijent.setIdPacijent(rs.getInt("pac.id_pacijent"));
                pacijent.setIme(rs.getString("pac.ime"));
                pacijent.setPrezime(rs.getString("pac.prezime"));

                String polStr = rs.getString("pac.pol");
                pacijent.setPol(polStr != null ? Pol.valueOf(polStr) : null);

                java.sql.Date datumRodjPacijenta = rs.getDate("pac.datum_rodjenja");
                pacijent.setDatumRodjenja(datumRodjPacijenta != null ? datumRodjPacijenta.toLocalDate() : null);

                pacijent.setMestoRodjenja(rs.getString("pac.mesto_rodjenja"));
                pacijent.setMejl(rs.getString("pac.mejl"));
                pacijent.setKrvnaGrupa(kg);
                Pregled noviPregled = new Pregled(idDokumenta, datumVremeZavrsetka, datumKontrole, vremeKontrole, ukupnoVremeTrajanja, terapija, lekar, pacijent);
                objekti.put(idDokumenta, noviPregled);
                pregled = noviPregled;
            }
            pregled.setIdPregled(idDokumenta);

            int redni_broj_stavke = rs.getInt("sp.id_stavka_pregleda");
            Dijagnoza dijagnoza = new Dijagnoza();

            dijagnoza.setIdDijagnoza(rs.getInt("dij.id_dijagnoza"));
            dijagnoza.setSifra(rs.getString("dij.sifra"));
            dijagnoza.setLatinskiNaziv(rs.getString("dij.latinski_naziv"));
            dijagnoza.setSrpskiNaziv(rs.getString("dij.srpski_naziv"));

            String nazivStavke = rs.getString("sp.naziv");
            String lekarskiNalaz = rs.getString("sp.lekarski_nalaz");
            int vremeTrajanja = rs.getInt("sp.vreme_trajanja");

            StavkaPregleda stavkaPregleda = new StavkaPregleda(pregled, redni_broj_stavke, nazivStavke, lekarskiNalaz, Duration.ofMinutes((long) vremeTrajanja), dijagnoza);
            pregled.getStavke().add(stavkaPregleda);
        }
        rs.close();
        lista.addAll(objekti.values());
        return lista;
    }

    private static String vratiDokumentKriterijumSifarnik1(Dijagnoza dijagnoza) {
        List<String> filteri = new ArrayList<>();
        if (dijagnoza.getIdDijagnoza() != 0) {
            filteri.add("dij2.id_dijagnoza =" + dijagnoza.getIdDijagnoza());
        }

        return !filteri.isEmpty() ? " WHERE " + String.join(" AND ", filteri) : "";
    }

    @Override
    public String toString() {
        return "ID pregleda: " + idPregled + ", Terapija:" + terapija;
    }

}
