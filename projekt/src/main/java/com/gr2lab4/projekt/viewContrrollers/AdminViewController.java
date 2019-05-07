/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr2lab4.projekt.viewContrrollers;

import com.gr2lab4.projekt.MainApp;
import com.gr2lab4.projekt.Entities.Pracownik;
import com.gr2lab4.projekt.Entities.Zadanie;
import java.io.IOException;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

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
    void logoutAdmin(ActionEvent e) throws IOException {

        //trzeba bedzie zapisac wszystko do bazy danych
        //przelaczyc go do panelu logowania
        

        Parent view2 = FXMLLoader.load(getClass()
                .getResource("/fxml/Scene.fxml"));
        Scene scene2 = new Scene(view2);
        Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.show();
        //ustawic defaulotwe dane w configu aplikacji 
        
        MainApp.instance.appCfg.setUser(null); // null 
        
    }

    @FXML
    private void deleteZadanie(ActionEvent event) {

        
    }

    @FXML
    private void updateZadanie(ActionEvent event) {

        
    }

    @FXML
    private void addZadanie(ActionEvent event) {
    	String tytul = tytulZadaniaDodaj.getText().toString();
    	String tresc = trescZadaniaDodaj.getText().toString();
    	//Date data = activateDateDodaj;
    	
    	if(tytul.isEmpty()) showAlertWithoutHeaderText("Wpisz tytuł zadania");
    	if(tresc.isEmpty()) showAlertWithoutHeaderText("Wpisz tresc zadania");
    	
    	/*
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pomidory");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Zadanie zadanie = new Zadanie();

		
		entityManager.getTransaction().begin();;
		
		entityManager.persist(zadanie);
		
		entityManager.getTransaction().commit();
		
		entityManager.close();
		entityManagerFactory.close(); 
		*/
    }

    @FXML
    private void generateRaport(ActionEvent event) {

       
    }
    
    private void showAlertWithoutHeaderText(String text) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Alert");
 
        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText(text);
 
        alert.showAndWait();
    }
}
