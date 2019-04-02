/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr2lab4.projekt.Entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author marcinrosol
 */
@Entity
@Table(name="pracownik")
public class Pracownik{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    
    private int id;
    
    private String imie;
    
    private String nazwisko;
    
    private String stanowisko;
    
    private String login;
    
    private String haslo;
    
    public Pracownik(){
        
    }

    public Pracownik(String imie, String nazwisko, String stanowisko, String login, String haslo) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.stanowisko = stanowisko;
        this.login = login;
        this.haslo = haslo;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
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
