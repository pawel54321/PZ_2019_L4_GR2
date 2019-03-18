/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr2lab4.projekt;

import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author marcinrosol
 */
public class LoginController{
    
    @FXML
    private TextField LoginField;

    @FXML
    private Button RegisterButton;

    @FXML
    private PasswordField PasswordField;

    @FXML
    private Button LoginButton;
    
    @FXML
    void LoginAction(ActionEvent event){
        System.out.println("klik");
    }
}
