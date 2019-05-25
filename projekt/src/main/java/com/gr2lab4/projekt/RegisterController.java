/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr2lab4.projekt;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.gr2lab4.projekt.Entities.Pracownik;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author marcinrosol
 */
public class RegisterController {
	@FXML
	private TextField RegisterName; // Pole rejestracji imię

	@FXML
	private TextField RegisterLoginName; // pole rejestracji login

	@FXML
	private TextField RegisterSurrName; // pole rejestracji nazwisko

	@FXML
	private PasswordField RegisterPasswd; // pole rejestracji haslo

	@FXML
	private PasswordField RegisterRePasswd; // pole rejestracji powt. haslo

	@FXML
	private Button RegisterButtonRG; // pole rejestracji przycisk zarejestruj

	/**
	 * Metoda pobiera dane i dodaje uzytkownika do bazy danych.
	 * @param e
	 * @throws IOException
	 */
	@FXML
	void registerClick(ActionEvent e) throws IOException {

		String name = RegisterName.getText().toString();
		String login = RegisterLoginName.getText().toString();
		String surr = RegisterSurrName.getText().toString();
		String passwd = RegisterPasswd.getText().toString();
		String passwd2 = RegisterRePasswd.getText().toString();
		Boolean istnieje = false;

		/*
		 * gdyby istanily 2 instancje to istnieje mozliwosc wprowadzenia 2 uzytkownikpw
		 * do bazy i wtedy apka sie wysypie ale zakladamy ze nie bedzie tak
		 */
		if (!passwd.equals(passwd2)) {
			
			showAlertWithoutHeaderText("Podane hasła nie są takie same");
			
		} else {

			for (Pracownik tempPracownik : MainApp.instance.appCfg.pracownicy) {
				if (tempPracownik.getLogin().equals(login) || login.equalsIgnoreCase("")) {
					istnieje = true;
					break;
				}
			}

			if (istnieje == false) {

				EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pomidory");
				EntityManager entityManager = entityManagerFactory.createEntityManager();

				Pracownik pracownik = new Pracownik();
				pracownik.setHaslo(passwd);
				pracownik.setImie(name);
				pracownik.setNazwisko(surr);
				pracownik.setLogin(login);
				pracownik.setStanowisko("pracownik");

				entityManager.getTransaction().begin();;
				
				entityManager.persist(pracownik);
				MainApp.instance.appCfg.pracownicy.add(pracownik);
				
				entityManager.getTransaction().commit();
				
				entityManager.close();
				entityManagerFactory.close();
				
				//System.out.println("dodany uzytkownik do bazy danych");
				// wstawiamy do bazy danych
				
				Parent view2 = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
				Scene scene2 = new Scene(view2);
				Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
				window.setScene(scene2);
				window.show();
			}else {
				//System.out.println("uzytkownik o takim nicku juz isteniej");
				showAlertWithoutHeaderText("Uzytkownik o takiej nazwie juz istenieje");
			}

			
		}
	}
	
    /**
     * Metoda wyświetlająca nam komunikat.
     * @param text wartość wyświetlana w komunikacie.
     */
    private void showAlertWithoutHeaderText(String text) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Alert");
 
        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText(text);
 
        alert.showAndWait();
    }
}
