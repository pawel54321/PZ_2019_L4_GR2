/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr2lab4.projekt.viewContrrollers;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.hibernate.PropertyValueException;

import com.gr2lab4.projekt.MainApp;
import com.gr2lab4.projekt.Entities.Pracownik;
import com.gr2lab4.projekt.Entities.Zadanie;
import com.gr2lab4.projekt.Entities.Dao.PracownikDAO;
import com.gr2lab4.projekt.Entities.Dao.ZadanieDAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author marcin
 */
public class ManagerViewController {

	// ---- Kontroler dla tabeli zadanie przypisz
	@FXML
	private TableColumn<Zadanie, String> columnTresc;

	@FXML
	private TableColumn<Zadanie, Integer> columnID;

	@FXML
	private TableColumn<Zadanie, String> columnTytul;

	@FXML
	private TableView<Zadanie> tableView;

	// ---
	
    @FXML
    private ChoiceBox<Pracownik> comboBox;
    
    // --- tabela zadan wykonanych 
    
    @FXML
    private TableView<Zadanie> tableViewWykonane;
    
    @FXML
    private TableColumn<Zadanie, Date> columnDataZakWykonane;
    
    @FXML
    private TableColumn<Zadanie, Integer> columnIDWykonane;
    
    @FXML
    private TableColumn<Zadanie, String>  columnTrescWykonane;
    
    @FXML
    private TableColumn<Zadanie, Pracownik>  columnPracownikWykonane;
    
    @FXML
    private TableColumn<Zadanie, Date>  columnDataUtWykonane;
    
    @FXML
    private TableColumn<Zadanie, String>  columnTytulWykonane;
    
    // ---
    
    
	private PracownikDAO pracownikDAO = new PracownikDAO();
	private ZadanieDAO zadanieDAO = new ZadanieDAO();

	public void initialize() {
		List<Zadanie> resultList = zadanieDAO.findAll();

		if (resultList.isEmpty() || resultList.size() == 0) {
			System.out.println("lista zadan jest pusta ");
		} else {
			MainApp.instance.appCfg.listaZadan.clear();
			for (Zadanie zad : resultList) { // dodawanie do observableList
				MainApp.instance.appCfg.listaZadan.add(zad);
			}

		}
		// pobranie pracownikow do combo boxa
		for (Pracownik p : MainApp.instance.appCfg.pracownicy) {
			if (p.getStanowisko().equalsIgnoreCase("pracownik")) {
				comboBox.getItems().add(p);
			}
		}
		// wczytanie danych do tabelki
		refreshTable();
		refreshTableWykonane();
	}

	@FXML
	void przypiszZadanie(ActionEvent event) {
		try {
			Pracownik tempPrac = comboBox.getSelectionModel().getSelectedItem();
			Zadanie tempZad = tableView.getSelectionModel().getSelectedItem();

			tempPrac.addZadania(tempZad);
			pracownikDAO.update(tempPrac);
			
			
			// Przypisujemy do obiektu w liscie pracownika
			for(int i = 0; i < MainApp.instance.appCfg.listaZadan.size(); i++) {
				if(MainApp.instance.appCfg.listaZadan.get(i).getId() == tempZad.getId()) {
					MainApp.instance.appCfg.listaZadan.get(i).setPracownik(tempPrac);
				}
			}
			
			
			showAlertWithoutHeaderText("Zadanie przypisane poprawnie.");
			refreshTable();
			
		} catch (Exception e) {
			showAlertWithoutHeaderText("Błąd podczas przypisania zadania.");
			System.out.println(e.getMessage());
		}
	}

	@FXML
	void logoutManager(ActionEvent e) throws IOException {
		Parent view2 = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
		Scene scene2 = new Scene(view2);
		Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
		window.setScene(scene2);
		window.show();
		// ustawic defaulotwe dane w configu aplikacji

		MainApp.instance.appCfg.setUser(null);

	}

	private void refreshTable() {
		columnID.setCellValueFactory(new PropertyValueFactory<Zadanie, Integer>("id"));
		columnTresc.setCellValueFactory(new PropertyValueFactory<Zadanie, String>("tresc"));
		columnTytul.setCellValueFactory(new PropertyValueFactory<Zadanie, String>("tytul"));


		ObservableList<Zadanie> tempList = FXCollections.observableArrayList();

		for (Zadanie z : MainApp.instance.appCfg.listaZadan) {
			if (z.getAktywne() == 1 && z.getPracownik() == null) {
				tempList.add(z);
			}
		}

		tableView.setItems((ObservableList<Zadanie>) tempList);
	}

	private void refreshTableWykonane() {
		columnIDWykonane.setCellValueFactory(new PropertyValueFactory<Zadanie, Integer>("id"));
		columnTrescWykonane.setCellValueFactory(new PropertyValueFactory<Zadanie, String>("tresc"));
		columnTytulWykonane.setCellValueFactory(new PropertyValueFactory<Zadanie, String>("tytul"));
		columnPracownikWykonane.setCellValueFactory(new PropertyValueFactory<Zadanie, Pracownik>("pracownik"));
		columnDataUtWykonane.setCellValueFactory(new PropertyValueFactory<Zadanie, Date>("data_rozp"));
		columnDataZakWykonane.setCellValueFactory(new PropertyValueFactory<Zadanie, Date>("data_ukon"));
		
		ObservableList<Zadanie> tempList = FXCollections.observableArrayList();

		for (Zadanie z : MainApp.instance.appCfg.listaZadan) {
			if (z.getAktywne() == 0 && z.getPracownik() != null) {
				tempList.add(z);
			}
		}

		tableView.setItems((ObservableList<Zadanie>) tempList);
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
