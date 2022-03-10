
package Project_codes;


import java.io.IOException;
import java.util.Properties;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Control extends LoginController {
    @FXML
    private Button button;
    @FXML
    private Button button1;

    @FXML
    private TextArea mass;
    private Scene scene;
    private Parent root;
    private Stage stage;
    @FXML
    void home(ActionEvent event) throws IOException {
        
        
        Parent root = FXMLLoader.load(getClass().getResource("homesection.fxml"));
        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
         scene = new Scene(root);
         stage.setScene(scene);
         stage.show();

    }

    @FXML
    void massag(ActionEvent event) {
         Window owner = button.getScene().getWindow();
         if (mass.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter your Massage");
            return;
        }
       String from="lsabankbd@gmail.com";
        String to="lsabankbd@gmail.com";
        String host="localhost";
        String subject="Name: "+s1+"  Account Number: "+s4;
         String massage="Name: "+s1+"  Account Number: "+s4+" Email: "+s2+"\n"+mass.getText();
         boolean so=false;
         Properties p= new Properties();
         p.put("mail.smtp.auth","true");
         p.put("mail.smtp.starttls.enable","true");
         p.put("mail.smtp.host","smtp.gmail.com");
         p.put("mail.smtp.port","587");
         
             Session sak= Session.getDefaultInstance(p,new javax.mail.Authenticator(){
             
             protected PasswordAuthentication getPasswordAuthentication(){
                 return new PasswordAuthentication("lsabankbd@gmail.com","lsabank1234");
                 
             }
             
         });  
              try{
             MimeMessage m= new MimeMessage(sak);
             m.setFrom(from);
             m.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
             m.setSubject(subject);
           m.setText(massage);
           Transport.send(m);
             
           
             
         }
         catch(Exception e){
             e.printStackTrace();
             
         }
              showAlert(Alert.AlertType.CONFIRMATION, owner, "Done",
                "Message sent was successful. Answers can be found in your email");
              
mass.setText("");
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

