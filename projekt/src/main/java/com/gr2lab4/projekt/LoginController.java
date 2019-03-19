/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr2lab4.projekt;

import java.io.IOException;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
public class LoginController{
    
    private String aL="admin", aP="admin";
    
    @FXML
    private TextField LoginField;

    @FXML
    private Button RegisterButton;

    @FXML
    private PasswordField PasswordField;

    @FXML
    private Button LoginButton;
    
    @FXML
    void LoginAction(ActionEvent e) throws IOException{
        if((LoginField.getText().equals(aL)) && PasswordField.getText().equals(aP)){
            System.out.println("ADMIN");
            Parent view2 = FXMLLoader.load(getClass().getResource("/fxml/AdminView.fxml")); 
            Scene scene2 = new Scene(view2);
            Stage window = (Stage)((Node)e.getSource()).getScene().getWindow(); 
            window.setScene(scene2);  
            window.show();
        } // to jest na sztywniutko logowanie do admina potem sie tam zmnieni
        //cały czas sztywniutko mordo
    //TODO
    
    //pobrac wartosci
    
    //sprawdzic jaki maja status w bd (admin manager, pracownik)
    
    //otworzyc odpowiednia dlla niego scene
    
    
    }
    
    @FXML
    private void RegisterAction(ActionEvent e) throws IOException{
        
        Parent view2 = FXMLLoader.load(getClass().getResource("/fxml/register.fxml")); 
        Scene scene2 = new Scene(view2);
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow(); 
        window.setScene(scene2);  
        window.show();
         
        System.out.println("register");
    }
}
