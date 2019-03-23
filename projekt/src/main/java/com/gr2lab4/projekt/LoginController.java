/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr2lab4.projekt;

import com.gr2lab4.projekt.cfgs.AccountType;
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
            
            
            MainApp.instance.appCfg.imie = "admin";
            System.out.println("imie set");
            MainApp.instance.appCfg.nazwisko ="admin";
            System.out.println("nazwisko set");
            MainApp.instance.appCfg.setAccountType(AccountType.ADMIN);
            System.out.println("type set");
            
            MainApp.instance.dbLogger.setData();
            
            
            Parent view2 = FXMLLoader.load(getClass().getResource("/fxml/AdminView.fxml")); 
            Scene scene2 = new Scene(view2);
            Stage window = (Stage)((Node)e.getSource()).getScene().getWindow(); 
            window.setScene(scene2);  
            window.show();

            MainApp.instance.dbLogger.saveLog("logowanie do systemu");
            
        }else if((LoginField.getText().equals("manager")) && PasswordField.getText().equals("manager")){
            
            System.out.println("MANAGER");
            
            MainApp.instance.appCfg.imie = "manager";
            MainApp.instance.appCfg.nazwisko ="manager";
            MainApp.instance.appCfg.setAccountType(AccountType.MANAGER);
            
            Parent view2 = FXMLLoader.load(getClass().getResource("/fxml/ManagerView.fxml")); 
            Scene scene2 = new Scene(view2);
            Stage window = (Stage)((Node)e.getSource()).getScene().getWindow(); 
            window.setScene(scene2);  
            window.show();
            
           
    
            
        }else{ //obecnie to nie jest bezpieczne wiec tutaj potem trzeba bedzie dodac warunek ze ma prawa do pracownika a nie na pale logowac
            
            System.out.println("PRACOWNIK");
            
            MainApp.instance.appCfg.imie = "pracownik";
            MainApp.instance.appCfg.nazwisko ="pracownik";
            MainApp.instance.appCfg.setAccountType(AccountType.PRACOWNIK);
            
            Parent view2 = FXMLLoader.load(getClass().getResource("/fxml/PracownikView.fxml")); 
            Scene scene2 = new Scene(view2);
            Stage window = (Stage)((Node)e.getSource()).getScene().getWindow(); 
            window.setScene(scene2);  
            window.show();
            
           
            
        }
// to jest na sztywniutko logowanie do admina potem sie tam zmnieni
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
