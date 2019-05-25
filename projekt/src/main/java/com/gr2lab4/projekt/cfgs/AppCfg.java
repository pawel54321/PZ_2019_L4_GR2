/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr2lab4.projekt.cfgs;

import com.gr2lab4.projekt.Entities.Pracownik;
import com.gr2lab4.projekt.Entities.Zadanie;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.collections.*;
/**
 * Klasa odpowiedzialna jest za przechowywanie danych w aplikacji.
 * @author marcinrosol
 * 
 */
public class AppCfg {
	
	
    public List<Pracownik> pracownicy;

    
    /**
     * Zmienna przechowuje obiekt zallogowanego pracownika.
     */
    public Pracownik user;

    public ObservableList<Zadanie> listaZadan;
    //private List<>
    
    public AppCfg() {
        this.pracownicy = new ArrayList<>();
        this.listaZadan = FXCollections.observableArrayList();
        
        this.user = new Pracownik();
    }

    public void setUser(Pracownik user) {
        this.user = user;
    }

    public Pracownik getUser() {
        return user;
    }


    public List getListaZadan() {
        return listaZadan;
    }
    
    

}
