/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr2lab4.projekt;

import java.awt.event.WindowEvent;
import java.io.IOException;

import com.gr2lab4.projekt.Entities.Pracownik;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Kontroller dla logowania
 * @author marcinrosol
 */
public class LoginController {


    @FXML
    private TextField LoginField;

    @FXML
    private Button RegisterButton;

    @FXML
    private PasswordField PasswordField;

    /**
     * Metoda sprawdza czy wprowadzone dane istnieją w bazie danych i loguje uzytkownika do aplikacji.
     * @throws IOException
     */
    @FXML
    void LoginAction(ActionEvent e) throws IOException {
        String login = LoginField.getText().toString();
        String passwd = PasswordField.getText().toString();
        String typ = "";
        
        System.out.println(login + " " + passwd);

        if (MainApp.instance.appCfg.pracownicy.size() == 0) {
            System.out.println("brak listy");
            showAlertWithoutHeaderText("Brak danych logowania w bazie.");
        } else {
            for (Pracownik tempPracownik : MainApp.instance.appCfg.pracownicy) { // przeszukujemy liste w poszukiwaniu odpowiednich danych do logowania
                if ((tempPracownik.getLogin().equals(login)) &&
                		(tempPracownik.getHaslo().equals(passwd))) {

                    MainApp.instance.appCfg.setUser(tempPracownik); // ustawiamy w cfg obiekt usera
                    
                    typ = tempPracownik.getStanowisko().toString().toLowerCase();
                    
              

                    break;
                }
            }
            
            Parent view2;
            Scene scene2;
            Stage window;
            
            switch (typ) {  // wybieramy odpowiednie okno dla danego pracownika
           
            
			case "admin":
				 //System.out.println("ZALOGOWANO admin");
		          view2 = FXMLLoader.load(getClass()
		                 .getResource("/fxml/AdminView.fxml")); 
		                  scene2 = new Scene(view2);
		                 window = (Stage)((Node)e.getSource()).getScene().getWindow(); 
		                 window.setScene(scene2);  
		                 window.show();
				break;
			case "manager":
				
				 //System.out.println("ZALOGOWANO manager");
				 
		          view2 = FXMLLoader.load(getClass()
			                 .getResource("/fxml/ManagerView.fxml")); 
			                  scene2 = new Scene(view2);
			                 window = (Stage)((Node)e.getSource()).getScene().getWindow(); 
			                 window.setScene(scene2);  
			                 window.show();
				break;
			case "pracownik":
				 //System.out.println("ZALOGOWANO pracownik");
				 
		          view2 = FXMLLoader.load(getClass()
			                 .getResource("/fxml/PracownikView.fxml")); 
			                  scene2 = new Scene(view2);
			                 window = (Stage)((Node)e.getSource()).getScene().getWindow(); 
			                 window.setScene(scene2);  
			                 window.show();
			                 
				break;
				
				default:
					System.out.println("default error");
					break;
			}
        }
        
        
    }

    /**
     * Metoda przenosi użytkownika do formularza rejestracji.
     * @param e
     * @throws IOException
     */
    @FXML
    private void RegisterAction(ActionEvent e) throws IOException {

        Parent view2 = FXMLLoader.load(getClass()
                .getResource("/fxml/register.fxml"));
        Scene scene2 = new Scene(view2);
        Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.show();

        System.out.println("register");
    }
    
    /**
     * Metoda wyświetlająca nam komunikat.
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

