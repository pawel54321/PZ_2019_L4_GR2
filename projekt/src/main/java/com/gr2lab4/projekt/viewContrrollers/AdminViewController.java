/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr2lab4.projekt.viewContrrollers;

import com.gr2lab4.projekt.MainApp;
import com.gr2lab4.projekt.Entities.Pracownik;
import com.gr2lab4.projekt.Entities.Zadanie;
import com.gr2lab4.projekt.Entities.Dao.PracownikDAO;
import com.gr2lab4.projekt.Entities.Dao.ZadanieDAO;
import com.gr2lab4.projekt.cfgs.AppCfg;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import javafx.scene.control.DatePicker;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.RadioButton;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;

/**
 * Klasa odpowiedziallna za kontroller dla administratora.
 * 
 * @author marcin
 *
 */
public class AdminViewController {

	@FXML
	private TextField tytulZadaniaDodaj;

	@FXML
	private TextArea trescZadaniaDodaj;

	// -------- Manager zadan, tabelka i usuniecie

	// TODO: realizacja,

	/**
	 * Choice box zawierajacy obiekty pracowników do przypisania zadania.
	 */
	@FXML
	private ChoiceBox<Pracownik> przypiszChoiceBox;

	// --- TABELA PRZYPISUJACA ZADANIE
	
	@FXML
	private TableView<Zadanie> tableConnectPracownik;

	@FXML
	private TableColumn<Zadanie, Integer> tableConnectID;
	
	@FXML
	private TableColumn<Zadanie, String> tableConnectTytul;
	
	@FXML
	private TableColumn<Zadanie ,String> tableConcestTresc;
	// ---------
	@FXML
	private TableView<Zadanie> tableWykonane;

	@FXML
	private TableColumn<Zadanie, Integer> IDWykonane;

	@FXML
	private TableColumn<Zadanie, Date> dodWykonane, ukoWyknane; // date

	@FXML
	private TableColumn<Zadanie, String> tytulWykonane, trescWykonane, pracownikWykonane;

	// --- TABELA EDYTUJACA

	@FXML
	private TableView<Zadanie> editTableView;

	@FXML
	private TableColumn<Zadanie, Integer> editColumnID;

	@FXML
	private TableColumn<Zadanie, String> editColumnTytul;

	@FXML
	private TableColumn<Zadanie, String> editColumnTresc;

	@FXML
	private TextField editTextField;

	@FXML
	private TextArea editTextArea;

	@FXML
	private Button zapiszEditButton;
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
	
	// ----- raport
	

	@FXML
	private ChoiceBox<Pracownik> choiceBoxRaport;
	
	//@FXML
	//private ChoiceBox<String> choiceBoxRaportdata;
	
	//@FXML
	//private ChoiceBox<String> choiceBoxRaportdata2;
	
	@FXML
	private DatePicker data1;
	@FXML
	private DatePicker data1drugi;
	
	
	@FXML
	private DatePicker data2;
	@FXML
	private DatePicker data2drugi;

	
	// ------ Tabelka dostepne zadania

	@FXML
	private TableView<Zadanie> tableDostepneZadania;

	@FXML
	private TableColumn<Zadanie, Integer> dostepneZadaniaID;

	@FXML
	private TableColumn<Zadanie, String> dostepneZadaniaTytul;

	@FXML
	private TableColumn<Zadanie, String> dostepneZadaniaTresc;
	// -----

	private PracownikDAO pracownikDAO = new PracownikDAO();
	private ZadanieDAO zadanieDAO = new ZadanieDAO();
	private Zadanie tempEditZad = new Zadanie();

	private String perm = "";

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

		// wrzucic wartosci do tabeli w oknie

		refreshEdytujZadanie();
		refreshTableDostepneZadania();
		refreshTableWykonaneZadania();
		refreshPrzypiszZadanie();
		refreshRozpisaneZadania();
		
		// prypisanie pracownikow do boxow
		for (Pracownik p : pracownikDAO.findAll()) {
			if (p.getStanowisko().equalsIgnoreCase("pracownik")) {
				przypiszChoiceBox.getItems().add(p);
				choiceBoxRaport.getItems().add(p);
			}
		}
		
		
//		choiceBoxRaportdata.getItems().add("Styczen");
//		choiceBoxRaportdata.getItems().add("Luty");
//		choiceBoxRaportdata.getItems().add("Marzec");
//		choiceBoxRaportdata.getItems().add("Kwiecien");
//		choiceBoxRaportdata.getItems().add("Maj");
//		choiceBoxRaportdata.getItems().add("Czerwiec");
//		choiceBoxRaportdata.getItems().add("Lipiec");
//		choiceBoxRaportdata.getItems().add("Sierpien");
//		choiceBoxRaportdata.getItems().add("Wrzesien");	
//		choiceBoxRaportdata.getItems().add("Pazdziernik");
//		choiceBoxRaportdata.getItems().add("Listopad");
//		choiceBoxRaportdata.getItems().add("Grudzien");
//		
//		choiceBoxRaportdata2.getItems().add("Styczen");
//		choiceBoxRaportdata2.getItems().add("Luty");
//		choiceBoxRaportdata2.getItems().add("Marzec");
//		choiceBoxRaportdata2.getItems().add("Kwiecien");
//		choiceBoxRaportdata2.getItems().add("Maj");
//		choiceBoxRaportdata2.getItems().add("Czerwiec");
//		choiceBoxRaportdata2.getItems().add("Lipiec");
//		choiceBoxRaportdata2.getItems().add("Sierpien");
//		choiceBoxRaportdata2.getItems().add("Wrzesien");	
//		choiceBoxRaportdata2.getItems().add("Pazdziernik");
//		choiceBoxRaportdata2.getItems().add("Listopad");
//		choiceBoxRaportdata2.getItems().add("Grudzien");
//		
/*
		 data1.setConverter(
			        new StringConverter<LocalDate>() {
			          final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("YYYY-MM");

			          @Override
			          public String toString(LocalDate date) {
			            return (date != null) ? dateFormatter.format(date) : "";
			          }

			          @Override
			          public LocalDate fromString(String string) {
			            return (string != null && !string.isEmpty())
			                ? LocalDate.parse(string, dateFormatter)
			                : null;
			          }
			        });
		 
		 
		 
		 
		 data2.setConverter(
			        new StringConverter<LocalDate>() {
			          final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("YYYY-MM");

			          @Override
			          public String toString(LocalDate date) {
			            return (date != null) ? dateFormatter.format(date) : "";
			          }

			          @Override
			          public LocalDate fromString(String string) {
			            return (string != null && !string.isEmpty())
			                ? LocalDate.parse(string, dateFormatter)
			                : null;
			          }
			        });
	
		 */
		 
	}
	
	
	
	

	/**
	 * Metoda wylogowywuje użytkownika.
	 * 
	 * @param e
	 * @throws IOException
	 */
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
		// MainApp.instance.appCfg.listaZadan = null;

	}

	// --------------
	/**
	 * Metoda pobierająca dane do edycji obiektu.
	 * 
	 * @param event
	 */
	@FXML
	void edycjaZaznacz(ActionEvent event) {
		if (!editTableView.getSelectionModel().isEmpty()) {
			tempEditZad = editTableView.getSelectionModel().getSelectedItem();
			editTextField.setText(tempEditZad.getTytul()); // tytul
			editTextArea.setText(tempEditZad.getTresc()); // tresc
			editTextArea.setEditable(true);
			editTextArea.setDisable(false);
			editTextField.setEditable(true);
			editTextField.setDisable(false);
			zapiszEditButton.setDisable(false);
		} else {
			showAlertWithoutHeaderText("Zaznacz obiekt z tabelki!");
		}

	}

	/**
	 * Metoda zapisująca zedytowany obiekt do bazy danych.
	 * 
	 * @param event
	 */
	@FXML
	void zapiszEditedZadanie(ActionEvent event) {
		if (editTextField.getText().isEmpty()) {
			showAlertWithoutHeaderText("Pole z tytulem jest puste!");
		} else {
			if (editTextArea.getText().isEmpty()) {
				showAlertWithoutHeaderText("Pole z trescia jest puste!");
			} else {

				tempEditZad.setTytul(editTextField.getText());
				tempEditZad.setTresc(editTextArea.getText());
				zadanieDAO.update(tempEditZad);

				//MainApp.instance.appCfg.listaZadan = (ObservableList<Zadanie>) zadanieDAO.findAll();

				editTextArea.setText("");
				editTextField.setText("");
				tempEditZad = new Zadanie();

				editTextArea.setEditable(false);
				editTextArea.setDisable(true);
				editTextField.setEditable(false);
				editTextField.setDisable(true);
				zapiszEditButton.setDisable(true);

				refreshEdytujZadanie();
				refreshTableDostepneZadania();
				refreshPrzypiszZadanie();
				
			}
		}
	}
	// -------

	/**
	 * Metoda usuwająca obiekt Zadanie z bazy danych.
	 * 
	 * @param event
	 */
	@FXML
	private void deleteZadanie(ActionEvent event) {

		try {

			if (tableDostepneZadania.getSelectionModel().getSelectedIndex() >= 0) {

				Zadanie tmpZadanie = tableDostepneZadania.getSelectionModel().getSelectedItem();
				tmpZadanie.setStatus("usuniete");

				zadanieDAO.update(tmpZadanie);
				//MainApp.instance.appCfg.listaZadan = (ObservableList<Zadanie>) zadanieDAO.findAll();

				refreshTableDostepneZadania();
				refreshEdytujZadanie();
				refreshPrzypiszZadanie();
				refreshTableWykonaneZadania();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage() + " | exception in deleteZadania");
		}
	}

	/**
	 * Metoda dodajaca obiekt Zadanie do bazy danych.
	 * 
	 * @param event
	 */
	@FXML
	private void addZadanie(ActionEvent event) {
		try {

		} catch (Exception e) {
			System.out.println(e.getMessage() + " dodawanie zadania exception");
		}
		String tytul = tytulZadaniaDodaj.getText().toString();
		String tresc = trescZadaniaDodaj.getText().toString();

		if (tytul.isEmpty()) {
			showAlertWithoutHeaderText("Wpisz tytuł zadania");
		} else {
			if (tresc.isEmpty()) {
				showAlertWithoutHeaderText("Wpisz tresc zadania");
			} else {

				Zadanie zadanie = new Zadanie();

				zadanie.setStatus("nowe");
				zadanie.setData_rozp(new Date());
				zadanie.setData_ukon(null);
				zadanie.setTresc(tresc);
				zadanie.setTytul(tytul);

				MainApp.instance.zadanieDAO.save(zadanie);

				// MainApp.instance.appCfg.listaZadan = (ObservableList<Zadanie>)
				// zadanieDAO.findAll();

				refreshTableDostepneZadania();
				refreshEdytujZadanie();
				refreshPrzypiszZadanie();
				refreshRozpisaneZadania();
				refreshTableWykonaneZadania();
				
				tytulZadaniaDodaj.setText(null);
				trescZadaniaDodaj.setText(null);
				// activateDateDodaj.setDayCellFactory(null);

				showAlertWithoutHeaderText("Zadanie zostało dodane poprawnie!");

			}
		}

	}

	/**
	 * metoda odswieza wszystkie tabelki w panelu administratora.
	 * 
	 * @param event
	 */
	@FXML
	void odswiezAction(ActionEvent event) {
		refreshEdytujZadanie();
		refreshTableDostepneZadania();
		refreshTableWykonaneZadania();
		refreshPrzypiszZadanie();
		refreshRozpisaneZadania();
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

	// -----------------

	/**
	 * Metoda przypisuje obiektowi Pracownik obiekt Zadaanie i zapisuje do bazy
	 * danych.
	 * 
	 * @param event
	 */
	@FXML
	void przypiszAction(ActionEvent event) {
		try {
			Pracownik tempPrac = przypiszChoiceBox.getSelectionModel().getSelectedItem();
			Zadanie tempZad = tableConnectPracownik.getSelectionModel().getSelectedItem();

			tempZad.setStatus("aktywne");
			tempPrac.addZadania(tempZad);
			pracownikDAO.update(tempPrac);

			//MainApp.instance.appCfg.listaZadan = (ObservableList<Zadanie>) zadanieDAO.findAll();

			showAlertWithoutHeaderText("Zadanie przypisane poprawnie.");
			refreshTableDostepneZadania();
			refreshEdytujZadanie();
			refreshPrzypiszZadanie();
			refreshRozpisaneZadania();

		} catch (Exception e) {
			showAlertWithoutHeaderText("Błąd podczas przypisania zadania.");
			System.out.println(e.getMessage());
		}
	}

//	
//	 REFRESH TABEL	
//	

	/**
	 * Metoda wczytujaca dane do tabeli z dostepnymi zadaniami
	 */
	private void refreshTableDostepneZadania() {

		try {
			
			tableDostepneZadania.getItems().clear();
			
			dostepneZadaniaID.setCellValueFactory(new PropertyValueFactory<Zadanie, Integer>("id"));
			dostepneZadaniaTytul.setCellValueFactory(new PropertyValueFactory<Zadanie, String>("tytul"));
			dostepneZadaniaTresc.setCellValueFactory(new PropertyValueFactory<Zadanie, String>("tresc"));

			ObservableList<Zadanie> tempList = FXCollections.observableArrayList();

			for (Zadanie z : zadanieDAO.findAll()) {
				if (z.getStatus().equalsIgnoreCase("nowe")) {
					tempList.add(z);
				}
			}

			tableDostepneZadania.getItems().addAll(tempList);
			tableDostepneZadania.refresh();
			
			
			
			tempList.clear();
			
		}catch (Exception e) {
			System.out.println(e.getMessage() + " error data loading");
		}

	}

	/**
	 * metoda wczytujaca zadania do tabeli edytujacej
	 */
	private void refreshEdytujZadanie() {
		try {
			editTableView.getItems().clear();
			
			editColumnID.setCellValueFactory(new PropertyValueFactory<Zadanie, Integer>("id"));
			editColumnTytul.setCellValueFactory(new PropertyValueFactory<Zadanie, String>("tytul"));
			editColumnTresc.setCellValueFactory(new PropertyValueFactory<Zadanie, String>("tresc"));

			ObservableList<Zadanie> tempList = FXCollections.observableArrayList();
			tempList.clear();

			for (Zadanie z : zadanieDAO.findAll()) {
				if (z.getStatus().equalsIgnoreCase("nowe")) {
					tempList.add(z);
				}
			}

			editTableView.getItems().setAll(tempList);
			editTableView.refresh();

			tempList.clear();
		} catch (Exception e) {
			System.out.println(e.getMessage() + " load data to table error");
		}
	}

	/**
	 * metoda wczytuje dane do tabeli z wykonanymi zadaniami
	 */
	private void refreshTableWykonaneZadania() {

		try {
			tableWykonane.getItems().clear();
			
			IDWykonane.setCellValueFactory(new PropertyValueFactory<Zadanie, Integer>("id"));
			tytulWykonane.setCellValueFactory(new PropertyValueFactory<Zadanie, String>("tytul"));
			trescWykonane.setCellValueFactory(new PropertyValueFactory<Zadanie, String>("tresc"));
			dodWykonane.setCellValueFactory(new PropertyValueFactory<Zadanie, Date>("data_rozp"));
			ukoWyknane.setCellValueFactory(new PropertyValueFactory<Zadanie, Date>("data_ukon"));
			pracownikWykonane.setCellValueFactory(new PropertyValueFactory<Zadanie, String>("pracownik"));

			ObservableList<Zadanie> tempList = FXCollections.observableArrayList();

			tempList.clear();

			for (Zadanie z : zadanieDAO.findAll()) {
				if (z.getStatus().equalsIgnoreCase("ukonczone")) {
					tempList.add(z);
				}
			}
			
			tableWykonane.getItems().setAll(tempList);
			tableWykonane.refresh();
			
			tempList.clear();
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage() + " error wstawiania danych do tabeli1");
		}
	}

	/**
	 *Metoda przypisuje dane do tabeli z przypisaniem zadan 
	 */
	private void refreshPrzypiszZadanie() {
		try {
			tableConnectPracownik.getItems().clear();
			
			tableConnectID.setCellValueFactory(new PropertyValueFactory<Zadanie, Integer>("id"));
			tableConnectTytul.setCellValueFactory(new PropertyValueFactory<Zadanie, String>("tytul"));
			tableConcestTresc.setCellValueFactory(new PropertyValueFactory<Zadanie, String>("tresc"));
			
			ObservableList<Zadanie> tempList = FXCollections.observableArrayList();
			
			for (Zadanie z : zadanieDAO.findAll()) {
				if (z.getStatus().equalsIgnoreCase("nowe")) {
					tempList.add(z);
				}
			}
			
			tableConnectPracownik.getItems().addAll(tempList);
			tableConnectPracownik.refresh();
			tempList.clear();
			
		}catch (Exception e) {
			System.out.println(e.getMessage() + " error data loading");
		}
	}

	/**
	 *Metoda dodajaca dane do tabelki rozpisanych zadan 
	 */
	private void refreshRozpisaneZadania() {
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
	
//	
//	----- raporty
//	
//	

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
		
		if (data1.getValue() != null) {
		com.gr2lab4.projekt.viewContrrollers.Raport1.generatePDF("results/Raport1.pdf", data1, data1drugi);
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
		 
		if (!choiceBoxRaport.getSelectionModel().isEmpty()) {

			com.gr2lab4.projekt.viewContrrollers.Raport2.generatePDF("results/Raport2.pdf",choiceBoxRaport);

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
		
		if (data2.getValue() != null) {
	//	com.gr2lab4.projekt.viewContrrollers.Raport3.generatePDF("results/Raport3.pdf", data2, data2drugi);
	} else {
		showAlertWithoutHeaderText("Zaznacz date");
	}

	}

}
