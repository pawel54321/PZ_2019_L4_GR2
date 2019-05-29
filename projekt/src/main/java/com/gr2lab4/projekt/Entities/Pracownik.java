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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Obiekt reprezentujący pracownika w firmie. 
 * @author marcinrosol
 */
@Entity
@Table(name="Pracownik")
public class Pracownik{
    
    /**
     * zmienna przechowuje id pracownika
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    protected int id_pracownika;
    
    /**
     * zmienna przechowuje imie
     */
    @Column(name="imie")
    protected String imie;
    
    /**
     * zmienna przechowuje nazwisko
     */
    @Column(name="nazwisko")
    protected String nazwisko;
    
    /**
     * zmienna przechowuje stanowsisko
     */
    @Column(name="stanowisko")
    protected String stanowisko;
    
    /**
     * zmienna przechowuje login
     */
    @Column(name="login")
    protected String login;
    
    /**
     * zmienna przechowuje haslo
     */
    @Column(name="haslo")
    protected String haslo;
    
//    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
//            CascadeType.DETACH, CascadeType.REFRESH}) // nie usuwamy zadan jesli usuniemy pracownka
//    @JoinColumn(name="zadanie_id") 
    
    /**
     * zmienna przechowuje zadania
     */
    @OneToMany(mappedBy = "pracownik",
    		fetch = FetchType.EAGER,
    		cascade = CascadeType.ALL,
    		orphanRemoval = true)
    protected List<Zadanie> zadania;
    
    public Pracownik(){
        
    }

    /**
     * konstruktor klasy
     * @param imie parametr imie
     * @param nazwisko parametr nazwisko
     * @param stanowisko parametr stanowsisko
     * @param login parametr login 
     * @param haslo parametr haslo
     */
    public Pracownik(String imie, String nazwisko,
            String stanowisko, String login, String haslo) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.stanowisko = stanowisko;
        this.login = login;
        this.haslo = haslo;
    }

    public void addZadania(Zadanie tempZadanie){
    	if(zadania == null ) {
    		zadania = new ArrayList<>();
    	}
    	
        zadania.add(tempZadanie);
        tempZadanie.setPracownik(this);
        
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

	@Override
	public String toString() {
		return  id_pracownika+", "+imie+" "+nazwisko;
	}
    
    
       
}
