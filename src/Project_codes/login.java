
package Project_codes;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class login extends Application {
    public static void main(String[] args) {
        
        launch(args);
        
    }

    public void start(Stage stage) throws Exception {
        
        
         Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        
        stage.setTitle("LSA Bank");
        
        
        stage.show();
    }
}
