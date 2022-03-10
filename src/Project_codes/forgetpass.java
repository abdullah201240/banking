/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project_codes;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 *
 * @author mirza
 */
public class forgetpass extends Application {
     public static void main(String[] args) {
        
        launch(args);
        
    }

    public void start(Stage stage) throws Exception {
        
        
        Parent root = FXMLLoader.load(getClass().getResource("forgetpass.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        
        stage.setTitle("LSA Bank");
        
        
        stage.show();
    }
    
}
