/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr2lab4.projekt.cfgs;

import com.gr2lab4.projekt.Entities.Zadanie;
import java.util.List;

/**
 *
 * @author marcinrosol
 * 
 * wszystkie zmienne jakie chcemy przetrzymywac globalnie to tutaj beda 
 * 
 */
public class AppCfg {
    
    //stanowisko
    private AccountType typKonta; // potrzebne do ustalenia jaki ktos ma typ konta i potem odpalenie odpowiedniego widoku i do zabezpieczenia w razie w
    
    public String imie;
    public String nazwisko;
    
    private List<Zadanie> listaZadan; // lista zadan przypisania do konta
    
    
    public void setAccountType(AccountType type){
        typKonta = type;
    }
    
    public AccountType getAccountType(){
        return typKonta;
    }
    
    public List getListaZadan(){
        return listaZadan;
    }
    
    
    
    
}
