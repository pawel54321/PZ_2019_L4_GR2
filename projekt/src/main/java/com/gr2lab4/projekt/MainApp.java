package com.gr2lab4.projekt;

import com.gr2lab4.projekt.cfgs.AppCfg;
import com.gr2lab4.projekt.cfgs.DBLogger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {

    
    public static MainApp instance = null;
    
    
    public AppCfg appCfg; //to klasa ktora bedzie zmienne przechowywala
    public DBLogger dbLogger;
    
    @Override
    public void start(Stage stage) throws Exception {
        instance = this;
        appCfg = new AppCfg();
        dbLogger = new DBLogger();
        
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml")); // /fxml/Scene.fxml
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setTitle("Luigi e pomodoro");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
