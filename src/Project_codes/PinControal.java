
package Project_codes;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;


public class PinControal extends ForgetpassController implements Initializable{
 private Scene scene;
    private Parent root;
    private Stage stage;
    @FXML
    private TextField pincode;
    @FXML
    private Button button; 
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }
     @FXML
     private void ping(ActionEvent event) throws IOException{
         String pin=pincode.getText();
         int pin1=Integer.parseInt(pin);
        int s= randomcode;
        if(s==pin1){
            Parent root = FXMLLoader.load(getClass().getResource("newpass.fxml"));
        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
         scene = new Scene(root);
         stage.setScene(scene);
         stage.show();
            
            
        }
        else{
             Window owner = button.getScene().getWindow();
             showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Code not match");
            
        }
      
     }
      private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
    
}
