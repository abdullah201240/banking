
package Project_codes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class deposite extends Application {
   public static void main(String[] args) {
        
        launch(args);
        
    }

    public void start(Stage stage) throws Exception {
        
        
        Parent v1 = FXMLLoader.load(getClass().getResource("deposite.fxml"));
        
        Scene s1 = new Scene(v1);
        stage.setScene(s1);
        
        stage.setTitle("LSA Bank");
        
        
        stage.show();
    }
    
    
    
}

