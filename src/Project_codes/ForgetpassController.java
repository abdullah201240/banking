
package Project_codes;

import java.io.*;
import java.util.*;
import javax.mail.*;
import javax.activation.*;
import javax.mail.internet.*;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.*;
import java.sql.DriverManager;
import java.io.IOException;
import java.net.URL;
import java.security.Security;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import static javax.management.remote.JMXConnectorFactory.connect;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class ForgetpassController implements Initializable {
     
    
   static final String DB_URL = "jdbc:mysql://localhost:3306/sakib";
   static final String USER = "root";
   static final String PASS = "";
   static String to;
    @FXML
    private TextField text;
    private Scene scene;
    private Parent root;
    private Stage stage;
    @FXML
    private Button button;
    public static int randomcode; 
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void switchToScene2(ActionEvent event) throws IOException,ClassNotFoundException, SQLException, Exception {
        
        
         Window owner = button.getScene().getWindow();
         
         Random rand=new Random();
         randomcode=rand.nextInt(999999);
        String from="lsabankbd@gmail.com";
         to=text.getText();
        String host="localhost";
        
         String subject="Reset Code";
         String massage="Your reset code is"+randomcode;
         
         boolean so=false;
         Properties p= new Properties();
         p.put("mail.smtp.auth","true");
         p.put("mail.smtp.starttls.enable","true");
         p.put("mail.smtp.host","smtp.gmail.com");
         p.put("mail.smtp.port","587");
         Session s= Session.getDefaultInstance(p,new javax.mail.Authenticator(){
             
             protected PasswordAuthentication getPasswordAuthentication(){
                 return new PasswordAuthentication("lsabankbd@gmail.com","lsabank1234");
                 
             }
             
         });
         try{
             MimeMessage m= new MimeMessage(s);
             m.setFrom(from);
             m.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
             m.setSubject(subject);
           m.setText(massage);
           Transport.send(m);
             showAlert(Alert.AlertType.CONFIRMATION, owner, "Email",
                "Code is sent");
           
             
         }
         catch(Exception e){
             e.printStackTrace();
             
         }
         
         
      Connection conn = null;
  
    Class.forName("com.mysql.cj.jdbc.Driver");
    
      conn = DriverManager.getConnection(DB_URL,USER,PASS);
        
        
       
        String query = "Select * from save where email=?";
      PreparedStatement pst = conn.prepareStatement(query);
      pst.setString(1, text.getText());
       ResultSet rs = pst.executeQuery(); 
      if(rs.next()){
          
            
          
          
         Parent ro = FXMLLoader.load(getClass().getResource("pincode.fxml"));
        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
         scene = new Scene(ro);
         stage.setScene(scene);
         stage.show();
         
      }
      else {
          showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter Correct email");
      }
    conn.close();
    pst.close();
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
