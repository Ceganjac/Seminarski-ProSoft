/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

/**
 *
 * @author Aleksandar Čeganjac
 */
public class KrvnaGrupa implements OpstiDomenskiObjekat {

    private int idKrvnaGrupa;
    private String aboTip;
    private String rhFaktor;

    public KrvnaGrupa() {
    }

    public KrvnaGrupa(int idKrvnaGrupa, String aboTip, String rhFaktor) {
        this.idKrvnaGrupa = idKrvnaGrupa;
        this.aboTip = aboTip;
        this.rhFaktor = rhFaktor;
    }

    public int getIdKrvnaGrupa() {
        return idKrvnaGrupa;
    }

    public void setIdKrvnaGrupa(int idKrvnaGrupa) {
        this.idKrvnaGrupa = idKrvnaGrupa;
    }

    public String getAboTip() {
        return aboTip;
    }

    public void setAboTip(String aboTip) {
        this.aboTip = aboTip;
    }

    public String getRhFaktor() {
        return rhFaktor;
    }

    public void setRhFaktor(String rhFaktor) {
        this.rhFaktor = rhFaktor;
    }

    @Override
    public String vratiVrednostiAtributa() {
        return idKrvnaGrupa + ", '"
                + aboTip + "', '"
                + rhFaktor + "'";
    }

    @Override
    public String vratimImeTabele() {
        return "krvna_grupa";
    }

}
