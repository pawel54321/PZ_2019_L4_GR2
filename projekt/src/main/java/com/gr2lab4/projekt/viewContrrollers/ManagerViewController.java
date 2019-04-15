/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr2lab4.projekt.viewContrrollers;

import com.gr2lab4.projekt.MainApp;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author marcin
 */
public class ManagerViewController {
        @FXML
    private Button wylogujManager;

    @FXML
    void logoutManager(ActionEvent e) throws IOException {
                Parent view2 = FXMLLoader.load(getClass()
                .getResource("/fxml/Scene.fxml"));
        Scene scene2 = new Scene(view2);
        Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.show();
        //ustawic defaulotwe dane w configu aplikacji 
        
        MainApp.instance.appCfg.setUser(null);
        
    }
}
