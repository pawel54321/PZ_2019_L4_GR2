/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr2lab4.projekt.viewContrrollers;

import com.gr2lab4.projekt.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author marcin
 */
public class AdminViewController {
    @FXML
    private Button generujRaportButton;
    
    @FXML
    private TextField tytulZadaniaAktualizuj;
 
    @FXML
    private TextArea TrescZadaniaAktualizuj;

    @FXML
    private Button updateZadanie;
    
    @FXML
    private Button usunButton;

    @FXML
    private DatePicker dataOdRaport;

    @FXML
    private Button dodajZadanie;

    @FXML
    private ListView<?> panelZadan;

    @FXML
    private DatePicker dataAktywacjiAktualizuj;

    @FXML
    private DatePicker activateDateDodaj;

    @FXML
    private TextField tytulZadaniaDodaj;

    @FXML
    private TextArea trescZadaniaDodaj;

    @FXML
    private DatePicker dataDoRaport;
    
    @FXML
    private Button wylogujAdmin;
    
    @FXML
    void logoutAdmin(ActionEvent event) {
        
        //trzeba bedzie zapisac wszystko do bazy danych
        
        //przelaczyc go do panelu logowania
        
        //ustawic defaulotwe dane w configu aplikacji 
        
    }
    
    @FXML
    private void deleteZadanie(ActionEvent event) {
        
        MainApp.instance.dbLogger.saveLog("usuniecie zadania");
    }

    @FXML
    private void updateZadanie(ActionEvent event) {
        
        
        MainApp.instance.dbLogger.saveLog("aktalizacja zadania");
    }

    @FXML
    private void addZadanie(ActionEvent event) {

        MainApp.instance.dbLogger.saveLog("dodanie zadania");
    }

    @FXML
    private void generateRaport(ActionEvent event) {

        MainApp.instance.dbLogger.saveLog("generowanie raportu");
    }
}
