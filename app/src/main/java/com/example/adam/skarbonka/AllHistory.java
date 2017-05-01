package com.example.adam.skarbonka;

import java.util.Date;

/**
 * Created by Adam on 27.04.2017.
 */

public class AllHistory {
    private String Kwota;
    private String Opis;
    private String AktualnyStan;
    private String Data;
    private boolean czyPlus;

    public boolean isCzyPlus() {
        return czyPlus;
    }

    public void setCzyPlus(boolean czyPlus) {
        this.czyPlus = czyPlus;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    public String getKwota() {
        return Kwota;
    }

    public void setKwota(String kwota) {
        Kwota = kwota;
    }

    public String getOpis() {
        return Opis;
    }

    public void setOpis(String opis) {
        Opis = opis;
    }

    public String getAktualnyStan() {
        return AktualnyStan;
    }

    public void setAktualnyStan(String aktualnyStan) {
        AktualnyStan = aktualnyStan;
    }

    @Override
    public String toString() {
        if(czyPlus==true){
            return   Opis  +"\nWrzucono: " + Kwota + " zł \n"+Data.substring(0, getData().length()-3)+", Stan skarbonki: "+ AktualnyStan  + " zł";
        }else {
            return   Opis  +"\nWydano: " + Kwota + " zł \n"+Data.substring(0, getData().length()-3)+", Stan skarbonki: "+ AktualnyStan  + " zł";
        }
    }
}

