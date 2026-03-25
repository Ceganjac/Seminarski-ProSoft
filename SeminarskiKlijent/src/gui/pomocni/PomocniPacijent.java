/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.pomocni;

import domen.KrvnaGrupa;
import domen.Pacijent;
import domen.enumi.Pol;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aleksandar Čeganjac
 */
public class PomocniPacijent {

    public static List<Pacijent> vratiPacijente() {
        // Kreiranje krvnih grupa
        KrvnaGrupa kg1 = new KrvnaGrupa(1, "A", "+");
        KrvnaGrupa kg2 = new KrvnaGrupa(2, "O", "-");
        KrvnaGrupa kg3 = new KrvnaGrupa(3, "B", "+");
        KrvnaGrupa kg4 = new KrvnaGrupa(4, "AB", "+");

        // Kreiranje pacijenata
        Pacijent p1 = new Pacijent(1, "Александар", "Чегањац", Pol.MUSKI,
                java.time.LocalDate.of(2005, 3, 22), "Београд", "aleksandar@example.com", kg1);

        Pacijent p2 = new Pacijent(2, "Ана", "Јовановић", Pol.ZENSKI,
                java.time.LocalDate.of(2006, 7, 10), "Нови Сад", "ana@example.com", kg2);

        Pacijent p3 = new Pacijent(3, "Марко", "Петровић", Pol.MUSKI,
                java.time.LocalDate.of(2004, 11, 5), "Ниш", "marko@example.com", kg3);

        Pacijent p4 = new Pacijent(4, "Јована", "Костић", Pol.ZENSKI,
                java.time.LocalDate.of(2005, 1, 18), "Крагујевац", "jovana@example.com", kg4);

        List<Pacijent> pacijenti = new ArrayList();
        pacijenti.add(p1);
        pacijenti.add(p2);
        pacijenti.add(p3);
        pacijenti.add(p4);

        return pacijenti;

    }

}
