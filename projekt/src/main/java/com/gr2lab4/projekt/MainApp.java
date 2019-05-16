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

/*
 pobieramy sobie cala list epracownikow z bazy danych (malo bezpieczne ale trudno),
 nastepnie podczas logowania bedziemy przeszukiwali sobie liste pracowników czy
 istnieja taki obiekt ktory tam login i haslo bedzie sie zgadzalo,
 jak jest to loguje i sio. commitujemy zmiany w bazie na bierzaco
 */
public class MainApp extends Application {

	public static MainApp instance = null;

	public PracownikDAO pracownikDAO = new PracownikDAO();
	public ZadanieDAO zadanieDAO = new ZadanieDAO();

	public AppCfg appCfg; // to klasa ktora bedzie zmienne przechowywala

	@Override
	public void start(Stage stage) throws Exception {
		instance = this;
		appCfg = new AppCfg();

		try {
			List<Pracownik> resultList = pracownikDAO.findAll();

			if (resultList.isEmpty() || resultList.size() == 0) {
				System.out.println("lista pracowników jest pusta ");
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

		stage.setTitle("pomidoro branie");
		stage.setScene(scene);
		stage.show();

	}

	/**
	 * The main() method is ignored in correctly deployed JavaFX application. main()
	 * serves only as fallback in case the application can not be launched through
	 * deployment artifacts, e.g., in IDEs with limited FX support. NetBeans ignores
	 * main().
	 *
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
