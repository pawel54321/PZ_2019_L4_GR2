/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr2lab4.projekt.cfgs;

import java.util.HashMap;

/**
 *
 * @author marcinrosol
 * 
 * wszystkie zmienne jakie chcemy przetrzymywac globalnie to tutaj beda 
 * 
 */
public class AppCfg {
    
    private AccountType typKonta;
    
    private String imie;
    private String nazwisko;
    
    private HashMap<String,Boolean> listaZadan; // nazwa , true/false (czy ukonczone czy nie)
    
    public void setAccountType(AccountType type){
        typKonta = type;
    }
    
    public AccountType getAccountType(){
        return typKonta;
    }
    
    
    
    
    
}
