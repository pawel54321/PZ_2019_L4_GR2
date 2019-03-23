/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr2lab4.projekt.cfgs;

import com.gr2lab4.projekt.MainApp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author marcinrosol
 */
public class DBLogger {
    
    private String imie,nazwisko,stanowisko;
    
    public DBLogger(){
        
    }
    
    public void setData(){
        this.stanowisko = MainApp.instance.appCfg.getAccountType().toString();
        this.imie = MainApp.instance.appCfg.imie;
        this.nazwisko = MainApp.instance.appCfg.nazwisko;
    }
    
    public void saveLog(String tresc){ //zapis logów do bazy danych
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        //malo wydajne bo robimy obiekt w funkcji caly czas ale nei znam innego sposobu na aktualny czas
        String czas = dateFormat.format(cal.getTime()).toString();
        
        System.out.println(imie +" "+nazwisko+ " "+stanowisko+" | "+tresc+" | "+ czas);
    }
    
    public void registerAccToDb(){
        //albo to tutaj albo wywalic 
    }
    
    public void getLog(Date dateFirst, Date dateSecond){
        //tworzenie pliku z ogami dla wybranej daty miedzy dateFirst a dateSecond
    }
}
