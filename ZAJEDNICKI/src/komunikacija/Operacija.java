package komunikacija;

import java.io.Serializable;

public enum Operacija implements Serializable {

    // PREGLED
    KREIRAJ_PREGLED,
    PROMENI_PREGLED,
    VRATI_PREGLED_PO_ID,
    VRATI_SVE_PREGLEDE,
    PRETRAZI_PREGLEDE,  // vraća preglede po uslovu
    
    // LEKAR
    PRIJAVI_LEKARA,
    VRATI_SVE_LEKARE,
    
    // PACIJENT
    KREIRAJ_PACIJENTA,
    PROMENI_PACIJENTA,
    VRATI_PACIJENTA_PO_ID,
    PRETRAZI_PACIJENTE,  // vraća pacijente po uslovu
    OBRISI_PACIJENTA,
    VRATI_SVE_PACIJENTE,
    
    // DIJAGNOZA
    VRATI_SVE_DIJAGNOZE,
    
    // KRVNA GRUPA
    VRATI_SVE_KRVNE_GRUPE,
    
    // SPECIJALIZACIJA
    UBACI_SPECIJALIZACIJU,
    PROMENI_SPECIJALIZACIJU
}
