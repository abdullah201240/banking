
package Project_codes;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;


public class AdlogCon {
      @FXML
    private Button button;

    @FXML
    private PasswordField passw;

    @FXML
    private TextField user;
    private Scene scene;
    private Parent root;
    private Stage stage;

    @FXML
    void login(ActionEvent event) throws IOException {
        Window owner = button.getScene().getWindow();
        if (user.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter your email id");
            return;
        }
      if (passw.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter a password");
            return;
        }
      int a=Integer.parseInt(passw.getText());
      if(user.getText().equals("sakib") &&a==12345){
          Parent root = FXMLLoader.load(getClass().getResource("adhome.fxml"));
        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
         scene = new Scene(root);
         stage.setScene(scene);
         stage.show();
      }
      else{
           showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter correct user name and password");
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
