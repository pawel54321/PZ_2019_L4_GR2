/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr2lab4.projekt.Entities;

import java.util.Date;

/**
 *
 * @author marcinrosol
 */
public class Zadanie {
    
    private String tytul;
    
    private String nazwa;
    
    private Date dataRozpoczeia;
    
    private Date dataZakonczenia;
    
    private boolean aktywne;
    
    
    public Zadanie(){
        
    }

    public Zadanie(String tytul, String nazwa, Date dataRozpoczeia,Date dataZakonczenia, boolean aktywne) {
        this.tytul = tytul;
        this.nazwa = nazwa;
        this.dataRozpoczeia = dataRozpoczeia;
        this.aktywne = aktywne;
        this.dataZakonczenia = dataZakonczenia;
    }

    public String getTytul() {
        return tytul;
    }

    public String getNazwa() {
        return nazwa;
    }

    public Date getDataRozpoczeia() {
        return dataRozpoczeia;
    }

    public Date getDataZakonczenia() {
        return dataZakonczenia;
    }

    public boolean isAktywne() {
        return aktywne;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setDataRozpoczeia(Date dataRozpoczeia) {
        this.dataRozpoczeia = dataRozpoczeia;
    }

    public void setDataZakonczenia(Date dataZakonczenia) {
        this.dataZakonczenia = dataZakonczenia;
    }

    public void setAktywne(boolean aktywne) {
        this.aktywne = aktywne;
    }
    
    
    
    
    
}
