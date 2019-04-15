/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr2lab4.projekt.Entities;


import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author marcinrosol
 */
@Entity
@Table(name="pracownicy")
public class Pracownik{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id_pracownika;
    
    @Column(name="imie")
    private String imie;
    
    @Column(name="nazwisko")
    private String nazwisko;
    
    @Column(name="stanowisko")
    private String stanowisko;
    
    @Column(name="login")
    private String login;
    
    @Column(name="haslo")
    private String haslo;
    
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH}) // nie usuwamy zadan jesli usuniemy pracownka
    private List<Zadanie> zadania;
    
    public Pracownik(){
        
    }

    public Pracownik(String imie, String nazwisko,
            String stanowisko, String login, String haslo) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.stanowisko = stanowisko;
        this.login = login;
        this.haslo = haslo;
    }

    public void add(Zadanie tempZadanie){
        if(zadania == null){
            zadania = new ArrayList<>();
        }
        
        zadania.add(tempZadanie);
        tempZadanie.setPracownicy(this);
    }
    
    public List<Zadanie> getZadania(){
      return zadania;  
    }
    
    public void setZadania(List<Zadanie> zadania){
        this.zadania = zadania;
    }
    
    public int getId_pracownika() {
	return id_pracownika;
    }

    public void setId_pracownika(int id_pracownika) {
	this.id_pracownika = id_pracownika;
    }
        
    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getStanowisko() {
        return stanowisko;
    }

    public String getLogin() {
        return login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public void setStanowisko(String stanowisko) {
        this.stanowisko = stanowisko;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }
    
    
       
}
