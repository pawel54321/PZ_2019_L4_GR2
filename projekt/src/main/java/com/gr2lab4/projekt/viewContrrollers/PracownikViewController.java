/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr2lab4.projekt.viewContrrollers;

import com.gr2lab4.projekt.MainApp;
import com.gr2lab4.projekt.Entities.Zadanie;
import com.gr2lab4.projekt.Entities.Dao.PracownikDAO;
import com.gr2lab4.projekt.Entities.Dao.ZadanieDAO;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javafx.scene.control.PasswordField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Kontroller dla pracownika
 * 
 * @author marcin
 */
public class PracownikViewController {

	// tabela zadania nie ukonczone

	@FXML
	private TableColumn<Zadanie, String> columnTresc;

	@FXML
	private TableColumn<Zadanie, Integer> columnID;

	@FXML
	private TableColumn<Zadanie, String> columnTytul;

	@FXML
	private TableView<Zadanie> tableVew;

	// tabela ukonczonych

	@FXML
	private TableView<Zadanie> tableUkonczoneView;

	@FXML
	private TableColumn<Zadanie, String> TrescUkonczone;

	@FXML
	private TableColumn<Zadanie, String> TytulUkonczone;

	@FXML
	private TableColumn<Zadanie, Integer> IDUkonczone;

	@FXML
	private PasswordField noweHaslo1;

	@FXML
	private PasswordField noweHaslo2;

	@FXML
	private PasswordField stareHasllo;
	// -------
	private PracownikDAO pracownikDAO = new PracownikDAO();
	private ZadanieDAO zadanieDAO = new ZadanieDAO();

	public void initialize() {
		List<Zadanie> resultList = zadanieDAO.findAll();

		if (resultList.isEmpty() || resultList.size() == 0) {
			System.out.println("lista zadan jest pusta ");
			// MainApp.instance.appCfg.listaZadan = null;
		} else {
			MainApp.instance.appCfg.listaZadan.clear();
			for (Zadanie zad : resultList) { // dodawanie do observableList
				MainApp.instance.appCfg.listaZadan.add(zad);
			}

		}

		// -------
		// System.out.println(MainApp.instance.appCfg.user.toString());
		refreshTable();

	}

	/**
	 * Metoda zmieniajaca status obiektu Zadanie na ukonczone.
	 * 
	 * @param event
	 */
	@FXML
	void gotoweZadanie(ActionEvent event) {
		Zadanie zaznaczone = tableVew.getSelectionModel().getSelectedItem();
		zaznaczone.setStatus("ukonczone");
		zaznaczone.setData_ukon(new Date());
		int id = zaznaczone.getId();

		zadanieDAO.update(zaznaczone);
		//MainApp.instance.appCfg.listaZadan = (ObservableList<Zadanie>) zadanieDAO.findAll();

		refreshTable();
	}

	/**
	 * Metoda odswieza dane w tabelkach.
	 */
	private void refreshTable() {
		try {
			tableVew.getItems().clear();
			tableUkonczoneView.getItems().clear();
			
			columnID.setCellValueFactory(new PropertyValueFactory<Zadanie, Integer>("id"));
			columnTresc.setCellValueFactory(new PropertyValueFactory<Zadanie, String>("tresc"));
			columnTytul.setCellValueFactory(new PropertyValueFactory<Zadanie, String>("tytul"));

			IDUkonczone.setCellValueFactory(new PropertyValueFactory<Zadanie, Integer>("id"));
			TrescUkonczone.setCellValueFactory(new PropertyValueFactory<Zadanie, String>("tresc"));
			TytulUkonczone.setCellValueFactory(new PropertyValueFactory<Zadanie, String>("tytul"));

			ObservableList<Zadanie> tempList = FXCollections.observableArrayList();
			ObservableList<Zadanie> tempList2 = FXCollections.observableArrayList();

			for (Zadanie z : zadanieDAO.findAll()) {
				if (z.getStatus().equalsIgnoreCase("aktywne")
						&& z.getPracownik().toString().equalsIgnoreCase(MainApp.instance.appCfg.user.toString())) {
					// System.out.println(z.toString());
					tempList.add(z);
				}

				if (z.getStatus().equalsIgnoreCase("ukonczone")
						&& z.getPracownik().toString().equalsIgnoreCase(MainApp.instance.appCfg.user.toString())) {

					tempList2.add(z);
				}
			}

			tableVew.getItems().setAll(tempList);
			tableUkonczoneView.getItems().setAll(tempList2);
			tableVew.refresh();
			tableUkonczoneView.refresh();
			
			tempList.clear();
			tempList2.clear();

		} catch (Exception e) {
			System.out.println(e.getMessage() + " | Tabela jest pusta");
		}
	}

	/**
	 * Metoda wylogowywuje użytkownika.
	 * 
	 * @param e
	 * @throws IOException
	 */
	@FXML
	void logoutPracownik(ActionEvent e) throws IOException {

		// trzeba bedzie zapisac wszystko do bazy danych
		// przelaczyc go do panelu logowania

		Parent view2 = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
		Scene scene2 = new Scene(view2);
		Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
		window.setScene(scene2);
		window.show();
		// ustawic defaulotwe dane w configu aplikacji

		MainApp.instance.appCfg.setUser(null); // null
		// MainApp.instance.appCfg.listaZadan = null;

	}

	/**
	 * Metoda zmieniajaca hasło uzytkownika w bazie danych.
	 * 
	 * @param e
	 */
	@FXML
	void onChangePasswd(ActionEvent e) {

		if (!(stareHasllo.getText().isEmpty() && noweHaslo1.getText().isEmpty() && noweHaslo2.getText().isEmpty())) {
			if (stareHasllo.getText().toString().equals(MainApp.instance.appCfg.user.getHaslo())) {
				if (noweHaslo1.getText().toString().equals(noweHaslo2.getText().toString())) {
					MainApp.instance.appCfg.user.setHaslo(noweHaslo1.getText());
					pracownikDAO.update(MainApp.instance.appCfg.user);
					showAlertWithoutHeaderText("Hasło zaktualizowane!");
					stareHasllo.setText("");
					noweHaslo1.setText("");
					noweHaslo2.setText("");
				} else {
					showAlertWithoutHeaderText("Hasła nie są zgodne");
				}
			} else {
				showAlertWithoutHeaderText("Wpisane stare hasło nie jest poprawne!");
			}
		} else {
			showAlertWithoutHeaderText("Nie wpisales zadnego hasla!");
		}

	}

	/**
	 * Metoda wyświetlająca nam komunikat.
	 * 
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
