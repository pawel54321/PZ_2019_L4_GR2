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
		System.out.println(MainApp.instance.appCfg.user.toString());
		refreshTable();

	}

	@FXML
	void gotoweZadanie(ActionEvent event) {

	}

	private void refreshTable() {
		try {
			columnID.setCellValueFactory(new PropertyValueFactory<Zadanie, Integer>("id"));
			columnTresc.setCellValueFactory(new PropertyValueFactory<Zadanie, String>("tresc"));
			columnTytul.setCellValueFactory(new PropertyValueFactory<Zadanie, String>("tytul"));

			// przypiszId.setCellValueFactory(new PropertyValueFactory<Zadanie,
			// Integer>("id"));
			// PrzypiszTableTresc.setCellValueFactory(new PropertyValueFactory<Zadanie,
			// String>("tresc"));
			// przypiszTableTytul.setCellValueFactory(new PropertyValueFactory<Zadanie,
			// String>("tytul"));

			ObservableList<Zadanie> tempList = FXCollections.observableArrayList();

			for (Zadanie z : MainApp.instance.appCfg.listaZadan) {
				if (z.getAktywne() == 1 
						&& z.getPracownik().toString().equalsIgnoreCase(MainApp.instance.appCfg.user.toString())) {
					System.out.println(z.toString());
					tempList.add(z);
				}
			}

			tableVew.setItems((ObservableList<Zadanie>) tempList);
			// przypiszTableView.setItems((ObservableList<Zadanie>) tempList);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

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
