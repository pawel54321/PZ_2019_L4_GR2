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

	public void initialize() {
		//TODO: metoda inicjalizujaca
		/**
		 * zaladowac obiekty do tabelek
		 *  itp
		 */
		
	}
	
	@FXML
	void logoutAdmin(ActionEvent e) throws IOException {

		// trzeba bedzie zapisac wszystko do bazy danych
		// przelaczyc go do panelu logowania

		Parent view2 = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
		Scene scene2 = new Scene(view2);
		Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
		window.setScene(scene2);
		window.show();
		// ustawic defaulotwe dane w configu aplikacji

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
		// Date data = activateDateDodaj;

		if (tytul.isEmpty()) {
			showAlertWithoutHeaderText("Wpisz tytuł zadania");
		} else {
			if (tresc.isEmpty()) {
				showAlertWithoutHeaderText("Wpisz tresc zadania");
			} else {
				EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pomidory");
				EntityManager entityManager = entityManagerFactory.createEntityManager();

				Zadanie zadanie = new Zadanie();

				zadanie.setAktywne(1);
				zadanie.setData_rozp(new Date());
				zadanie.setData_ukon(new Date());
				zadanie.setTresc(tresc);
				zadanie.setTytul(tytul);
				
				entityManager.getTransaction().begin();

				entityManager.persist(zadanie);

				entityManager.getTransaction().commit();

				entityManager.close();
				entityManagerFactory.close();
				
				tytulZadaniaDodaj.setText(null);
				trescZadaniaDodaj.setText(null);
				activateDateDodaj.setDayCellFactory(null);
				
				showAlertWithoutHeaderText("Zadanie zostało dodane poprawnie!");
			}
		}

	}

	@FXML
	private void generateRaport(ActionEvent event) {
		// TODO: jakis raport bedzie generowal sie do pdfa

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
