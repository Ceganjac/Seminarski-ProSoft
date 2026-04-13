/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.pomocni;

import domen.Dijagnoza;
import domen.KrvnaGrupa;
import domen.Lekar;
import domen.Pacijent;
import domen.Pregled;
import domen.StavkaPregleda;
import domen.enumi.Pol;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aleksandar Čeganjac
 */
public class Pomocni {

    // Kreiranje krvnih grupa
    static KrvnaGrupa kg1 = new KrvnaGrupa(1, "A", "+");
    static KrvnaGrupa kg2 = new KrvnaGrupa(2, "O", "-");
    static KrvnaGrupa kg3 = new KrvnaGrupa(3, "B", "+");
    static KrvnaGrupa kg4 = new KrvnaGrupa(4, "AB", "+");

    // Kreiranje pacijenata
    static Pacijent p1 = new Pacijent(1, "Александар", "Чегањац", Pol.MUSKI,
            java.time.LocalDate.of(2005, 3, 22), "Београд", "aleksandar@example.com", kg1);

    static Pacijent p2 = new Pacijent(2, "Ана", "Јовановић", Pol.ZENSKI,
            java.time.LocalDate.of(2006, 7, 10), "Нови Сад", "ana@example.com", kg2);

    static Pacijent p3 = new Pacijent(3, "Марко", "Петровић", Pol.MUSKI,
            java.time.LocalDate.of(2004, 11, 5), "Ниш", "marko@example.com", kg3);

    static Pacijent p4 = new Pacijent(4, "Јована", "Костић", Pol.ZENSKI,
            java.time.LocalDate.of(2005, 1, 18), "Крагујевац", "jovana@example.com", kg4);

    // Kreiranje lekara
    static Lekar l1 = new Lekar(1, "Марко", "Марковић", Pol.MUSKI, LocalDate.of(1980, 5, 12), "marko", "123");
    static Lekar l2 = new Lekar(2, "Јелена", "Јовановић", Pol.ZENSKI, LocalDate.of(1985, 8, 22), "jelena", "123");
    static Lekar l3 = new Lekar(3, "Никола", "Николић", Pol.MUSKI, LocalDate.of(1978, 3, 3), "nikola", "123");
    static Lekar l4 = new Lekar(4, "Ана", "Анић", Pol.ZENSKI, LocalDate.of(1990, 11, 15), "ana", "123");

    // Kreiranje pregleda
    static Pregled pr1 = new Pregled(1,
            LocalDateTime.of(2025, 3, 1, 10, 0),
            LocalDateTime.of(2025, 3, 10, 10, 0),
            30,
            "Мировање и терапија",
            l1,
            p1);

    static Pregled pr2 = new Pregled(2,
            LocalDateTime.of(2025, 3, 2, 11, 0),
            LocalDateTime.of(2025, 3, 12, 11, 0),
            45,
            "Антибиотици",
            l2,
            p2);

    static Pregled pr3 = new Pregled(3,
            LocalDateTime.of(2025, 3, 3, 9, 30),
            LocalDateTime.of(2025, 3, 15, 9, 30),
            20,
            "Контрола притиска",
            l3,
            p3);

    static Pregled pr4 = new Pregled(4,
            LocalDateTime.of(2025, 3, 4, 13, 0),
            LocalDateTime.of(2025, 3, 18, 13, 0),
            60,
            "Физикална терапија",
            l4,
            p4);

    public static List<Pacijent> vratiPacijente() {

        List<Pacijent> pacijenti = new ArrayList();
        pacijenti.add(p1);
        pacijenti.add(p2);
        pacijenti.add(p3);
        pacijenti.add(p4);

        return pacijenti;

    }

    public static List<KrvnaGrupa> vratiKrvneGrupe() {

        // Kreiranje liste
        List<KrvnaGrupa> grupe = new ArrayList<>();
        grupe.add(kg1);
        grupe.add(kg2);
        grupe.add(kg3);
        grupe.add(kg4);

        return grupe;

    }

    public static List<Lekar> vratiLekare() {

        List<Lekar> lekari = new ArrayList<>();

        lekari.add(l1);
        lekari.add(l2);
        lekari.add(l3);
        lekari.add(l4);

        return lekari;

    }

    public static List<Pregled> vratiPreglede() {

        List<Pregled> pregledi = new ArrayList<>();
        pregledi.add(pr1);
        pregledi.add(pr2);
        pregledi.add(pr3);
        pregledi.add(pr4);

        return pregledi;

    }

    public static Dijagnoza d1 = new Dijagnoza(1, "J00", "Nasopharyngitis acuta", "Акутни назофарингитис");
    public static Dijagnoza d2 = new Dijagnoza(2, "J02", "Pharyngitis acuta", "Акутни фарингитис");
    public static Dijagnoza d3 = new Dijagnoza(3, "J18", "Pneumonia", "Упала плућа");
    public static Dijagnoza d4 = new Dijagnoza(4, "I10", "Hypertensio arterialis", "Повишен крвни притисак");
    public static Dijagnoza d5 = new Dijagnoza(5, "E11", "Diabetes mellitus typus 2", "Дијабетес тип 2");

    public static List<Dijagnoza> vratiDijagnoze() {
        
        List<Dijagnoza> dijagnoze = new ArrayList();
        dijagnoze.add(d1);
        dijagnoze.add(d2);
        dijagnoze.add(d3);
        dijagnoze.add(d4);
        dijagnoze.add(d5);

        return dijagnoze;
    }

}
