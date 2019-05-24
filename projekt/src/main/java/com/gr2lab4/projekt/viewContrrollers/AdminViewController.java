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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sound.midi.Soundbank;

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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;


public class AdminViewController {

	@FXML
	private TextField tytulZadaniaDodaj;

	@FXML
	private TextArea trescZadaniaDodaj;

//--------
	@FXML
	private TableColumn<Zadanie, String> TableColumnTresc, tableColumnTytul;

	@FXML
	private TableColumn<Zadanie, Integer> tableColumnID;
	@FXML
	private TableView<Zadanie> tableView;

	@FXML
	private ChoiceBox<Pracownik> przypiszChoiceBox;

	// --- przypisz section
	@FXML
	private TableView<Zadanie> przypiszTableView;

	@FXML
	private TableColumn<Zadanie, String> PrzypiszTableTresc, przypiszTableTytul;

	@FXML
	private TableColumn<Zadanie, Integer> przypiszId;

	// ---------
	@FXML
	private TableView<Zadanie> tableWykonane;

	@FXML
	private TableColumn<Zadanie, Integer> IDWykonane;

	@FXML
	private TableColumn<Zadanie, Date> dodWykonane, ukoWyknane; // date

	@FXML
	private TableColumn<Zadanie, String> tytulWykonane, trescWykonane, pracownikWykonane;

	// ---

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
//----- raport

	@FXML
	private ChoiceBox<Pracownik> choiceBoxRaport;

	// ------

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

		refreshTable();

		// prypisanie pracownikow do boxow
		for (Pracownik p : MainApp.instance.appCfg.pracownicy) {
			if (p.getStanowisko().equalsIgnoreCase("pracownik")) {
				przypiszChoiceBox.getItems().add(p);
				choiceBoxRaport.getItems().add(p);
			}
		}

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
		// MainApp.instance.appCfg.listaZadan = null;

	}

	// --------------
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

				for (int i = 0; i < MainApp.instance.appCfg.listaZadan.size(); i++) {
					if (MainApp.instance.appCfg.listaZadan.get(i).getId() == tempEditZad.getId()) {
						MainApp.instance.appCfg.listaZadan.get(i).setTresc(editTextArea.getText());
						MainApp.instance.appCfg.listaZadan.get(i).setTytul(editTextField.getText());
					}
				}
				editTextArea.setText("");
				editTextField.setText("");
				tempEditZad = new Zadanie();

				editTextArea.setEditable(false);
				editTextArea.setDisable(true);
				editTextField.setEditable(false);
				editTextField.setDisable(true);
				zapiszEditButton.setDisable(true);

				refreshTable();
			}
		}
	}
	// -------

	@FXML
	private void deleteZadanie(ActionEvent event) {

		try {

			if (tableView.getSelectionModel().getSelectedIndex() >= 0) {
				for (int id = 0; id < MainApp.instance.appCfg.listaZadan.size(); id++) {
					if (MainApp.instance.appCfg.listaZadan.get(id).getId() == tableView.getSelectionModel()
							.getSelectedItem().getId()) {

						MainApp.instance.zadanieDAO.delete(MainApp.instance.appCfg.listaZadan.get(id));
						MainApp.instance.appCfg.listaZadan.remove(id);
						break;

					}
				}

				refreshTable();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@FXML
	private void updateZadanie(ActionEvent event) {

		Zadanie zad = tableView.getSelectionModel().getSelectedItem();
		// ustawic do obiektu zad nowe wartosci

		// wrzucmay do bazy
		MainApp.instance.zadanieDAO.updateZadanie(zad);

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

				Zadanie zadanie = new Zadanie();

				zadanie.setAktywne(1);
				zadanie.setData_rozp(new Date());
				zadanie.setData_ukon(null);
				zadanie.setTresc(tresc);
				zadanie.setTytul(tytul);

				MainApp.instance.zadanieDAO.save(zadanie);

				MainApp.instance.appCfg.listaZadan.add(zadanie);

				refreshTable();

				tytulZadaniaDodaj.setText(null);
				trescZadaniaDodaj.setText(null);
				// activateDateDodaj.setDayCellFactory(null);

				showAlertWithoutHeaderText("Zadanie zostało dodane poprawnie!");

			}
		}

	}

	@FXML
	private void generateRaport1(ActionEvent event) throws IOException { // raport za miesiac
//TODO:
		System.out.println("raport1");
	
		com.gr2lab4.projekt.viewContrrollers.Raport.generatePDF("results/Raport.pdf");

	}

	@FXML
	private void generateRaport2(ActionEvent event) { // raport na pacownika
//TODO:
		System.out.println("raport2");
		if(!choiceBoxRaport.getSelectionModel().isEmpty()) {
			
		}else {
			showAlertWithoutHeaderText("Zaznacz pracownika");
		}
	}

	@FXML
	private void generateRaport3(ActionEvent event) { // raport ukonczonych zadan w tym miesiacu
//TODO:
		System.out.println("raport3");

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

	@FXML
	void przypiszAction(ActionEvent event) {
		try {
			Pracownik tempPrac = przypiszChoiceBox.getSelectionModel().getSelectedItem();
			Zadanie tempZad = przypiszTableView.getSelectionModel().getSelectedItem();

			tempPrac.addZadania(tempZad);
			pracownikDAO.update(tempPrac);

			// Przypisujemy do obiektu w liscie pracownika
			for (int i = 0; i < MainApp.instance.appCfg.listaZadan.size(); i++) {
				if (MainApp.instance.appCfg.listaZadan.get(i).getId() == tempZad.getId()) {
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

	private void refreshTable() {
		tableColumnID.setCellValueFactory(new PropertyValueFactory<Zadanie, Integer>("id"));
		TableColumnTresc.setCellValueFactory(new PropertyValueFactory<Zadanie, String>("tresc"));
		tableColumnTytul.setCellValueFactory(new PropertyValueFactory<Zadanie, String>("tytul"));

		przypiszId.setCellValueFactory(new PropertyValueFactory<Zadanie, Integer>("id"));
		PrzypiszTableTresc.setCellValueFactory(new PropertyValueFactory<Zadanie, String>("tresc"));
		przypiszTableTytul.setCellValueFactory(new PropertyValueFactory<Zadanie, String>("tytul"));

		editColumnID.setCellValueFactory(new PropertyValueFactory<Zadanie, Integer>("id"));
		editColumnTresc.setCellValueFactory(new PropertyValueFactory<Zadanie, String>("tresc"));
		editColumnTytul.setCellValueFactory(new PropertyValueFactory<Zadanie, String>("tytul"));

		IDWykonane.setCellValueFactory(new PropertyValueFactory<Zadanie, Integer>("id"));
		tytulWykonane.setCellValueFactory(new PropertyValueFactory<Zadanie, String>("tytul"));
		trescWykonane.setCellValueFactory(new PropertyValueFactory<Zadanie, String>("tresc"));
		dodWykonane.setCellValueFactory(new PropertyValueFactory<Zadanie, Date>("data_rozp"));
		ukoWyknane.setCellValueFactory(new PropertyValueFactory<Zadanie, Date>("data_ukon"));
		pracownikWykonane.setCellValueFactory(new PropertyValueFactory<Zadanie, String>("pracownik"));

		ObservableList<Zadanie> tempList = FXCollections.observableArrayList();

		for (Zadanie z : MainApp.instance.appCfg.listaZadan) {
			if (z.getAktywne() == 1 && z.getPracownik() == null) {
				tempList.add(z);
			}
		}

		tableView.setItems((ObservableList<Zadanie>) tempList);
		przypiszTableView.setItems((ObservableList<Zadanie>) tempList);
		editTableView.setItems((ObservableList<Zadanie>) tempList);

		for (Zadanie z : MainApp.instance.appCfg.listaZadan) {
			if (z.getPracownik() != null && z.getAktywne() == 0) {
				tempList.add(z);
			}
		}
		tableWykonane.setItems((ObservableList<Zadanie>) tempList);

	}

	private void refreshPermission() {
	}
}
