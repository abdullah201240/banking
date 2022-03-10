
package Project_codes;

import static Project_codes.LoginController.DB_URL;
import static Project_codes.LoginController.PASS;
import static Project_codes.LoginController.USER;
import static Project_codes.LoginController.s10;
import static Project_codes.LoginController.s5;
import java.io.IOException;
import static java.lang.ProcessBuilder.Redirect.to;
import java.math.BigInteger;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;


public class NewpassControler extends ForgetpassController  implements Initializable{
    private Scene scene;
    private Parent root;
    private Stage stage;
    
    @FXML
    private PasswordField newpa;
     @FXML
     private PasswordField conpa;
      @FXML
    private Button done;  

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    @FXML
    private void submitdone(ActionEvent event) throws ClassNotFoundException, SQLException, IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        Window owner = done.getScene().getWindow();
        
        String a=newpa.getText();
         String sa 
        = generateStorngPasswordHash(a);
        String b=conpa.getText();
         
        if(a.equals(b)){
            Connection conn = null;
  
    Class.forName("com.mysql.cj.jdbc.Driver");
    
      conn = DriverManager.getConnection(DB_URL,USER,PASS);
      String INSERT_QUERY = "update save set password= ? where email= ?";
               PreparedStatement ps = conn.prepareStatement(INSERT_QUERY);
               ps.setString(1, sa);
               ps.setString(2, to);
             ps.executeUpdate();
             ps.close();
             conn.close();
              showAlert(Alert.AlertType.CONFIRMATION, owner, "Done",
                "Password is changed");
              newpa.setText("");
              conpa.setText("");
              Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
         scene = new Scene(root);
         stage.setScene(scene);
         stage.show();
             
        }
        else{
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Both Password are not same");
            newpa.setText("");
              conpa.setText("");
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
     private static String generateStorngPasswordHash(String password) 
    throws NoSuchAlgorithmException, InvalidKeySpecException
{
    int iterations = 1000;
    char[] chars = password.toCharArray();
    byte[] salt = getSalt();

    PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
    SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

    byte[] hash = skf.generateSecret(spec).getEncoded();
    return iterations + ":" + toHex(salt) + ":" + toHex(hash);
}
private static byte[] getSalt() throws NoSuchAlgorithmException
{
    SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
    byte[] salt = new byte[16];
    sr.nextBytes(salt);
    return salt;
}

private static String toHex(byte[] array) throws NoSuchAlgorithmException
{
    BigInteger bi = new BigInteger(1, array);
    String hex = bi.toString(16);
    
    int paddingLength = (array.length * 2) - hex.length();
    if(paddingLength > 0)
    {
        return String.format("%0"  +paddingLength + "d", 0) + hex;
    }else{
        return hex;
    }
}
    
    
    
}

