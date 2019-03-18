/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr2lab4.projekt;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
    
}
