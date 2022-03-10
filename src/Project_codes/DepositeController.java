
package Project_codes;

import static Project_codes.ForgetpassController.randomcode;
import static Project_codes.LoginController.DB_URL;
import static Project_codes.LoginController.PASS;
import static Project_codes.LoginController.USER;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class DepositeController implements Initializable {
     private Scene scene;
    private Parent root;
    private Stage stage;
     @FXML
    private Button button;

    @FXML
    private Button button1;

    @FXML
    private TextField dcm;

    @FXML
    private TextField dcm1;

    @FXML
    private TextField dm;
    public static int acccnums;
    public static int acccnumsa;
    public static String emm;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
     @FXML
    private void home(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("adhome.fxml"));
        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
         scene = new Scene(root);
         stage.setScene(scene);
         stage.show(); 
         
    }
     @FXML
    void send(ActionEvent event) throws ClassNotFoundException, SQLException {
        Connection con = null;
  
    Class.forName("com.mysql.cj.jdbc.Driver");
    
      con = DriverManager.getConnection(DB_URL,USER,PASS);
      String query = "Select * from save where accountnum=?";
      PreparedStatement pst = con.prepareStatement(query);
      pst.setString(1, dcm.getText());
     
      ResultSet rs = pst.executeQuery(); 
      
      if(rs.next()){
          acccnums=rs.getInt(6);
          acccnumsa=rs.getInt(5);
          emm=rs.getString(2);
          
          Random rand=new Random();
         randomcode=rand.nextInt(999999);
          String from="lsabankbd@gmail.com";
        String to=emm;
        String host="localhost";
        String subject="Deposite Code";
         String massage="Your Deposite code is "+randomcode;
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
              Window owner = button1.getScene().getWindow();
              showAlert(Alert.AlertType.CONFIRMATION, owner, "Code",
                "Email is send ");
              
          
      }
      else{
          Window owner = button1.getScene().getWindow();
              showAlert(Alert.AlertType.ERROR, owner, "Not Found",
                "Account is not found ");
      }
      
        
        

    }
    @FXML
    private void submit(ActionEvent event) throws ClassNotFoundException, SQLException {
        Window owner = button.getScene().getWindow();
        String ammount=dm.getText();
            int oh=Integer.parseInt(ammount);
            String sq=dcm1.getText();
        int cat=Integer.parseInt(sq);
        int z=randomcode;
            if(z==cat){
               int za=acccnums+oh;
                
                Connection conn = null;
  
    Class.forName("com.mysql.cj.jdbc.Driver");
    
      conn = DriverManager.getConnection(DB_URL,USER,PASS);
String spl = "update save set amount= ? where accountnum= ?";
               PreparedStatement ps = conn.prepareStatement(spl);
               ps.setInt(1, za);
               ps.setInt(2, acccnumsa);
             ps.executeUpdate();
              String in = "update save set deposite= ? where accountnum= ?";
               PreparedStatement psi = conn.prepareStatement(in);
               psi.setInt(1, oh);
               psi.setInt(2, acccnumsa);
             psi.executeUpdate();
             conn.close();
     psi.close();
    ps.close();
    
    dm.setText("");
             showAlert(Alert.AlertType.CONFIRMATION, owner, "Money Deposite",
                "Congratulation Money Deposite Done ");
    
    
    String from="lsabankbd@gmail.com";
        String to=emm;
        String host="localhost";
        String subject="MONEY Deposite";
         String massage="Deposite Money "+oh+"tk ";
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
    
                
            }
            
    
    else{
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
