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
import javafx.stage.Stage;

/**
 *
 * @author mirza
 */
public class signup extends Application {
    public static void main(String[] args) {
        
        launch(args);
        
    }

    public void start(Stage stage) throws Exception {
        
        
        Parent v1 = FXMLLoader.load(getClass().getResource("signup.fxml"));
        
        Scene s1 = new Scene(v1);
        stage.setScene(s1);
        
        stage.setTitle("LSA Bank");
        
        
        stage.show();
    }
}
