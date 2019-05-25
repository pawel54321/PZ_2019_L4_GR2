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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
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
		//System.out.println(MainApp.instance.appCfg.user.toString());
		refreshTable();

	}

	/**
	 * Metoda zmieniajaca status obiektu Zadanie na ukonczone.
	 * @param event
	 */
	@FXML
	void gotoweZadanie(ActionEvent event) {
		Zadanie zaznaczone = tableVew.getSelectionModel().getSelectedItem();
		zaznaczone.setAktywne(0);
		zaznaczone.setData_ukon(new Date());
		int id = zaznaczone.getId();
		
		zadanieDAO.update(zaznaczone);
		
		for(int i = 0; i < MainApp.instance.appCfg.listaZadan.size(); i++) {
			if(MainApp.instance.appCfg.listaZadan.get(i).getId() == id) {
				MainApp.instance.appCfg.listaZadan.get(i).setAktywne(0);
				MainApp.instance.appCfg.listaZadan.get(i).setData_ukon(new Date());
			}
		}
		
		refreshTable();
	}

	/**
	 * Metoda odswieza dane w tabelkach.
	 */
	private void refreshTable() {
		try {
			columnID.setCellValueFactory(new PropertyValueFactory<Zadanie, Integer>("id"));
			columnTresc.setCellValueFactory(new PropertyValueFactory<Zadanie, String>("tresc"));
			columnTytul.setCellValueFactory(new PropertyValueFactory<Zadanie, String>("tytul"));

			IDUkonczone.setCellValueFactory(new PropertyValueFactory<Zadanie, Integer>("id"));
			TrescUkonczone.setCellValueFactory(new PropertyValueFactory<Zadanie, String>("tresc"));
			TytulUkonczone.setCellValueFactory(new PropertyValueFactory<Zadanie, String>("tytul"));


			ObservableList<Zadanie> tempList = FXCollections.observableArrayList();
			ObservableList<Zadanie> tempList2 = FXCollections.observableArrayList();

			for (Zadanie z : MainApp.instance.appCfg.listaZadan) {
				if (z.getAktywne() == 1 
						&& z.getPracownik().toString().equalsIgnoreCase(MainApp.instance.appCfg.user.toString())) {
					//System.out.println(z.toString());
					tempList.add(z);
				}
				
				if(z.getAktywne() == 0 
						&& z.getPracownik().toString().equalsIgnoreCase(MainApp.instance.appCfg.user.toString())) {
					
					tempList2.add(z);
				}
			}

			tableVew.setItems((ObservableList<Zadanie>) tempList);
			tableUkonczoneView.setItems((ObservableList<Zadanie>) tempList2);
			// przypiszTableView.setItems((ObservableList<Zadanie>) tempList);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Metoda wylogowywuje użytkownika.
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

}
