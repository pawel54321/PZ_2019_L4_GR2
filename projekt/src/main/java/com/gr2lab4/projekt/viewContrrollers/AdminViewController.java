/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr2lab4.projekt.viewContrrollers;

import com.gr2lab4.projekt.MainApp;
import com.gr2lab4.projekt.Entities.Pracownik;
import com.gr2lab4.projekt.Entities.Zadanie;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
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
	private TableColumn<Zadanie, String> TableColumnTresc;

	@FXML
	private TableColumn<Zadanie, String> tableColumnTytul;

	@FXML
	private TableColumn<Zadanie, Integer> tableColumnID;
	@FXML
	private TableView<Zadanie> tableView;

	/**
	 * 
	 * zrobic liste dla aktywnych i nie przypisanych zadan i dodac do tabeli aktywne
	 * nie przypisane
	 * 
	 */

	public void initialize() {
		// TODO: metoda inicjalizujaca

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pomidory");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		//entityManager.getTransaction().begin();

		List<Zadanie> resultList = entityManager.createQuery("select z from Zadanie z", Zadanie.class).getResultList();

		if (resultList.isEmpty() || resultList.size() == 0) {
			System.out.println("lista zadan jest pusta ");
			MainApp.instance.appCfg.listaZadan = null;
		} else {

			for (Zadanie zad : resultList) { // dodawanie do observableList
				MainApp.instance.appCfg.listaZadan.add(zad);
			}

		}

		entityManager.close();
		entityManagerFactory.close();

		System.out.println("pobrano z bazy");
		// wrzucic wartosci do tabeli w oknie

		refreshTable();

		System.out.println("Dodanie zadan do tabelki");
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
		//MainApp.instance.appCfg.listaZadan = null;

	}


	@FXML
	private void deleteZadanie(ActionEvent event) {
		if (tableView.getSelectionModel().getSelectedIndex() >= 0) {
			// tableView.getSelectionModel().getSelectedItem().setAktywne(0);;
			for(int id=0; id<MainApp.instance.appCfg.listaZadan.size();id++) {
				if(MainApp.instance.appCfg.listaZadan.get(id).getId() == tableView.getSelectionModel().getSelectedItem().getId()) {
					MainApp.instance.appCfg.listaZadan.get(id).setAktywne(0);
					break;
				}
			}
			
			EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pomidory");
			EntityManager entityManager = entityManagerFactory.createEntityManager();
			entityManager.getTransaction().begin();

			Zadanie zad = entityManager.find(Zadanie.class, tableView.getSelectionModel().getSelectedItem().getId());
			zad.setAktywne(0);

			entityManager.getTransaction().commit();
			entityManager.close();
			entityManagerFactory.close();

			// System.out.println(tempZad.toString());

			refreshTable();
		}
	}

	@FXML
	private void updateZadanie(ActionEvent event) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pomidory");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		Zadanie zad = entityManager.find(Zadanie.class, tableView.getSelectionModel().getSelectedItem().getId());
		// tutaj pozmieniac tresci

		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();

		// System.out.println(tempZad.toString());

		refreshTable();
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

				MainApp.instance.appCfg.listaZadan.add(zadanie);

				refreshTable();

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
	

    @FXML
    void odswiezAction(ActionEvent event) {
    	refreshTable();
    	System.out.println("refresh");
    }

	private void showAlertWithoutHeaderText(String text) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Alert");

		// Header Text: null
		alert.setHeaderText(null);
		alert.setContentText(text);

		alert.showAndWait();
	}
	

	private void refreshTable() {
		tableColumnID.setCellValueFactory(new PropertyValueFactory<Zadanie, Integer>("id"));
		TableColumnTresc.setCellValueFactory(new PropertyValueFactory<Zadanie, String>("tresc"));
		tableColumnTytul.setCellValueFactory(new PropertyValueFactory<Zadanie, String>("tytul"));
		ObservableList<Zadanie> tempList = FXCollections.observableArrayList();
		
		for(Zadanie z : MainApp.instance.appCfg.listaZadan) {
			if(z.getAktywne() == 1) {
				tempList.add(z);
			}
		}
		
		tableView.setItems((ObservableList<Zadanie>) tempList);
	}
}
