/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr2lab4.projekt;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author marcinrosol
 */
public class RegisterController {
    @FXML
    private TextField RegisterName; //Pole rejestracji imię

    @FXML
    private TextField RegisterLoginName; //pole rejestracji login

    @FXML
    private TextField RegisterSurrName; //pole rejestracji nazwisko 

    @FXML
    private PasswordField RegisterPasswd; // pole rejestracji haslo
    
    @FXML
    private PasswordField RegisterRePasswd; // pole rejestracji powt. haslo
    
    @FXML
    private Button RegisterButtonRG; //pole rejestracji przycisk zarejestruj
   
    @FXML
    void registerClick(ActionEvent e) throws IOException {
        
        //TODO
        //zczytaj wartości
        
        //jak istnieje takie cos juz w bd to elo
        
        //jak nie to wstaw do bazy danych
        
        //a tu przeniesie do logowania
        Parent view2 = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml")); 
        Scene scene2 = new Scene(view2);
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow(); 
        window.setScene(scene2);  
        window.show();
    }
    
}
