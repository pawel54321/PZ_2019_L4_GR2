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
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * Kontroller dla managera
 * 
 * @author marcin
 */
public class ManagerViewController {

	// ---- Kontroler dla tabeli zadanie przypisz

	@FXML
	private TableView<Zadanie> tablePrzypiszZadania;

	@FXML
	private TableColumn<Zadanie, Integer> tablePrzypiszID;

	@FXML
	private TableColumn<Zadanie, String> tablePrzypiszTytul;

	@FXML
	private TableColumn<Zadanie, String> tablePrzypiszTresc;
	// ---

	@FXML
	private ChoiceBox<Pracownik> comboBox;

	// --- tabela zadan wykonanych

	@FXML
	private TextField podajimiee;

	@FXML
	private TableView<Zadanie> tableViewWykonane;

	@FXML
	private TableColumn<Zadanie, Date> columnDataZakWykonane;

	@FXML
	private TableColumn<Zadanie, Integer> columnIDWykonane;

	@FXML
	private TableColumn<Zadanie, String> columnTrescWykonane;

	@FXML
	private TableColumn<Zadanie, Pracownik> columnPracownikWykonane;

	@FXML
	private TableColumn<Zadanie, Date> columnDataUtWykonane;

	@FXML
	private TableColumn<Zadanie, String> columnTytulWykonane;

	// ---
	@FXML
	private ChoiceBox<Pracownik> choiceBoxRaport1;
	// --

	@FXML
	private DatePicker data1;
	@FXML
	private DatePicker data1drugi;

	@FXML
	private PasswordField noweHaslo1;

	@FXML
	private PasswordField noweHaslo2;

	@FXML
	private PasswordField stareHasllo;
	// --
	// TABELKA ROZPISANE ZADANIA
	@FXML
	private TableView<Zadanie> tableRozpisaneZadania;

	@FXML
	private TableColumn<Zadanie, Integer> tableRozpisaneID;

	@FXML
	private TableColumn<Zadanie, String> tableRozpisaneTytul;

	@FXML
	private TableColumn<Zadanie, String> tableRozpisaneTresc;

	@FXML
	private TableColumn<Zadanie, Date> tableRozpisaneDataDod;

	@FXML
	private TableColumn<Zadanie, String> tableDataDodaniaPracownik;
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
		try {
			for (Pracownik p : MainApp.instance.pracownikDAO.findAll()) {
				if (p.getStanowisko().equalsIgnoreCase("pracownik")) {
					comboBox.getItems().add(p);
					choiceBoxRaport1.getItems().add(p);

				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage() + " choiceBoxRaport1");
		}

		// wczytanie danych do tabelki
		refreshTablePrzypisz();
		refreshTableWykonane();
		refreshPrzypisaneZadania();

	}

	/**
	 * Metoda przypisuje obiekt Zadanie do obiektu Pracownik i zapisuje do bazy
	 * danych.
	 * 
	 * @param event
	 */
	@FXML
	void przypiszZadanie(ActionEvent event) {
		try {
			Pracownik tempPrac = comboBox.getSelectionModel().getSelectedItem();
			Zadanie tempZad = tablePrzypiszZadania.getSelectionModel().getSelectedItem();

			tempZad.setStatus("aktywne");
			tempPrac.addZadania(tempZad);
			pracownikDAO.update(tempPrac);

			// MainApp.instance.appCfg.listaZadan = (ObservableList<Zadanie>)
			// zadanieDAO.findAll();

			showAlertWithoutHeaderText("Zadanie przypisane poprawnie.");
			refreshTablePrzypisz();
			refreshPrzypisaneZadania();

		} catch (Exception e) {
			showAlertWithoutHeaderText("Błąd podczas przypisania zadania.");
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Metoda wylogowywuje użytkownika.
	 * 
	 * @param e
	 * @throws IOException
	 */
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
	 * Metoda odswieza dane w panelach.
	 */
	private void refreshTablePrzypisz() {
		tablePrzypiszZadania.getItems().clear();
		tablePrzypiszID.setCellValueFactory(new PropertyValueFactory<Zadanie, Integer>("id"));
		tablePrzypiszTytul.setCellValueFactory(new PropertyValueFactory<Zadanie, String>("tytul"));
		tablePrzypiszTresc.setCellValueFactory(new PropertyValueFactory<Zadanie, String>("tresc"));

		ObservableList<Zadanie> tempList = FXCollections.observableArrayList();

		for (Zadanie z : zadanieDAO.findAll()) {
			if (z.getStatus().equalsIgnoreCase("nowe")) {
				tempList.add(z);
			}
		}

		tablePrzypiszZadania.getItems().setAll(tempList);
		tablePrzypiszZadania.refresh();
		tempList.clear();
	}

	/**
	 * Metoda odswieza dane w tabeli wykonanych zadan.
	 */
	private void refreshTableWykonane() {

		tableViewWykonane.getItems().clear();
		columnIDWykonane.setCellValueFactory(new PropertyValueFactory<Zadanie, Integer>("id"));
		columnTrescWykonane.setCellValueFactory(new PropertyValueFactory<Zadanie, String>("tresc"));
		columnTytulWykonane.setCellValueFactory(new PropertyValueFactory<Zadanie, String>("tytul"));
		columnPracownikWykonane.setCellValueFactory(new PropertyValueFactory<Zadanie, Pracownik>("pracownik"));
		columnDataUtWykonane.setCellValueFactory(new PropertyValueFactory<Zadanie, Date>("data_rozp"));
		columnDataZakWykonane.setCellValueFactory(new PropertyValueFactory<Zadanie, Date>("data_ukon"));

		ObservableList<Zadanie> tempList2 = FXCollections.observableArrayList();

		for (Zadanie z : zadanieDAO.findAll()) {
			if (z.getStatus().equalsIgnoreCase("ukonczone")) {
				tempList2.add(z);
			}
		}

		tableViewWykonane.getItems().setAll(tempList2);
		tableViewWykonane.refresh();
		tempList2.clear();

	}

	/**
	 * metoda przypisuje zadania przypisane do tabeli
	 */
	private void refreshPrzypisaneZadania() {
		try {
			tableRozpisaneZadania.getItems().clear();

			tableRozpisaneID.setCellValueFactory(new PropertyValueFactory<Zadanie, Integer>("id"));
			tableRozpisaneTytul.setCellValueFactory(new PropertyValueFactory<Zadanie, String>("tytul"));
			tableRozpisaneTresc.setCellValueFactory(new PropertyValueFactory<Zadanie, String>("tresc"));
			tableRozpisaneDataDod.setCellValueFactory(new PropertyValueFactory<Zadanie, Date>("data_rozp"));
			tableDataDodaniaPracownik.setCellValueFactory(new PropertyValueFactory<Zadanie, String>("pracownik"));

			ObservableList<Zadanie> tempList = FXCollections.observableArrayList();

			tempList.clear();

			for (Zadanie z : zadanieDAO.findAll()) {
				if (z.getStatus().equalsIgnoreCase("aktywne")) {
					tempList.add(z);
				}
			}

			tableRozpisaneZadania.getItems().setAll(tempList);
			tableRozpisaneZadania.refresh();

			tempList.clear();

		} catch (Exception e) {
			System.out.println(e.getMessage() + " error wstawiania danych do tabeli1");
		}
	}

	/**
	 * Metoda generujaca raport dla wszystkich zadan w danym miesiacu
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	private void generateRaport1(ActionEvent event) throws IOException { // raport za miesiac (Raport dotyczacy
																			// wszystkich zadan w danym miesiacu)
//TODO:
		System.out.println("raport1");

		if (data1.getValue() != null && data1drugi.getValue() != null) {

			if (data1.getValue().isAfter(data1drugi.getValue()))// || data1drugi.getValue().isBefore(data1.getValue()) )
			{
				showAlertWithoutHeaderText("Niepoprawny przedział!");
			}

			else {
				com.gr2lab4.projekt.viewContrrollers.Raport1.generatePDF("results/Raport1.pdf", data1, data1drugi);
			}
		} else {
			showAlertWithoutHeaderText("Zaznacz date");
		}
	}

	/**
	 * Metoda generujaca raport dla danego pracownika.
	 * 
	 * @param event
	 */
	@FXML
	private void generateRaport2(ActionEvent event) { // raport na pracownika
//TODO:
		System.out.println("raport2");

		if (!choiceBoxRaport1.getSelectionModel().isEmpty()) {

			com.gr2lab4.projekt.viewContrrollers.Raport2.generatePDF("results/Raport2.pdf", choiceBoxRaport1);

		} else {
			showAlertWithoutHeaderText("Zaznacz pracownika");
		}
	}

	/**
	 * Metoda generujaca raport ukonczonych zadan w danym miesiacu.
	 * 
	 * @param event
	 */
	@FXML
	private void generateRaport3(ActionEvent event) { // raport ukonczonych zadan w tym miesiacu (Raport dotyczacy zadan
														// jakie zostaly ukonczone w danym miesiacu)

		System.out.println("raport3");
		String imiee = podajimiee.getText().toString();

		if (!imiee.isEmpty()) {
			boolean flaga = false;

			for (Zadanie z : zadanieDAO.findAll()) {
				if (z.getPracownik() != null) {
					if (z.getPracownik().getImie().equals(imiee)) {
						flaga = true;
					}
				}
			}

			if (flaga == true) {
				com.gr2lab4.projekt.viewContrrollers.Raport3.generatePDF("results/Raport3.pdf", imiee);
			} else {
				showAlertWithoutHeaderText("Nie znaleziono żadnego pracownika");
			}
		} else {
			showAlertWithoutHeaderText("Nie podano imienia");
		}

		// if (input.getValue() != null) {
		// com.gr2lab4.projekt.viewContrrollers.Raport3.generatePDF("results/Raport3.pdf",
		// data2, data2drugi);
		// } else {
		// showAlertWithoutHeaderText("Zaznacz date");
		// }

	}

	@FXML
	private void wyszukajimiee(ActionEvent event) {

		System.out.println("wyszukaj");

		String imiee = podajimiee.getText().toString();

		if (!imiee.isEmpty()) {
			boolean flaga = false;

			for (Zadanie z : zadanieDAO.findAll()) {
				if (z.getPracownik() != null) {
					if (z.getPracownik().getImie().equals(imiee)) {
						flaga = true;
					}
				}
			}

			if (flaga == true) {
				showAlertWithoutHeaderText("Znaleziono!");
			} else {
				showAlertWithoutHeaderText("Nie znaleziono żadnego pracownika");
			}
		} else {
			showAlertWithoutHeaderText("Nie podano imienia");
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
