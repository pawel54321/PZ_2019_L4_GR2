package com.gr2lab4.projekt;

import com.gr2lab4.projekt.Entities.Pracownik;
import com.gr2lab4.projekt.Entities.Dao.PracownikDAO;
import com.gr2lab4.projekt.Entities.Dao.ZadanieDAO;
import com.gr2lab4.projekt.cfgs.AppCfg;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

/**
 * Date: 25.05.2019
 * Prosta aplikacja umożliwiajaca rozdawanie zadań pracownikom.
 * @author marcin
 * @version 1.0
 * 
 */

public class MainApp extends Application {

	public static MainApp instance = null;
	/**
	 * Klasa łącząca obiekt Pracownik z bazą danych.
	 */
	public PracownikDAO pracownikDAO = new PracownikDAO();
	/**
	 * Klasa łącząca obiekt Zadanie z bazą danych/
	 */
	public ZadanieDAO zadanieDAO = new ZadanieDAO();

	public AppCfg appCfg; 

	@Override
	public void start(Stage stage) throws Exception {
		instance = this;
		appCfg = new AppCfg();
		
		try {
			List<Pracownik> resultList = pracownikDAO.findAll();

			if (resultList.isEmpty() || resultList.size() == 0) {
				System.out.println("lista pracowników jest pusta ");
				Pracownik admin = new Pracownik("Admin","Admin","admin","admin","admin");
				Pracownik manager = new Pracownik("Manager","manager","manager","manager","manager");
				
				//manager
				pracownikDAO.save(admin);
				pracownikDAO.save(manager);
				resultList = pracownikDAO.findAll();
				appCfg.pracownicy = resultList;
			} else {
				appCfg.pracownicy = resultList;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// --

		Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));

		Scene scene = new Scene(root);
		scene.getStylesheets().add("/styles/Styles.css");

		stage.setTitle("pomidorobranie");
		stage.setScene(scene);
		stage.show();

	}
	
	@Override
	public void stop(){
	    System.out.println("Stage is closing");
	    System.exit(0);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
