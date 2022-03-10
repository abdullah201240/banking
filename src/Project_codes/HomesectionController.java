
package Project_codes;

import static Project_codes.LoginController.DB_URL;
import static Project_codes.LoginController.PASS;
import static Project_codes.LoginController.USER;

import static Project_codes.LoginController.s10;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.imageio.ImageIO;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class HomesectionController extends LoginController implements Initializable {
 private Scene scene;
    private Parent root;
    private Stage stage;
    @FXML
    private Label wel;
    @FXML
    private Label show;
    @FXML
    private TextArea history;
    @FXML
    
    private Image images;
    @FXML
    private Button button12;
    static final String DB_URL = "jdbc:mysql://localhost:3306/sakib";
   static final String USER = "root";
   static final String PASS = "";
   static String imagepath;
   public static InputStream input;
   public static Blob b;
    @FXML
    private Label accnoo;
    @FXML
    private Circle circel;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
        wel.setText("Hello!  "+s1);
        show.setText(s5+" tk");
          accnoo.setText("Account Number: "+s4);
        String s=String.valueOf(s11);  
        String a=String.valueOf(s15);  
        String k=String.valueOf(s18);  
        history.appendText("Withdraw "+s+"\n"+"Transfer "+k+"\n"+"Deposite "+a);
        history.setEditable(false);
        
        
        try {
            
            Connection conn = null;
  
    Class.forName("com.mysql.cj.jdbc.Driver");
    
      conn = DriverManager.getConnection(DB_URL,USER,PASS);
      
      
      
      String query = "Select * from save where email=?";
      PreparedStatement pst = conn.prepareStatement(query);
      pst.setString(1, s2);
     
      ResultSet rs = pst.executeQuery(); 
      if(rs.next()){
          
            b=rs.getBlob(16);
            InputStream in = b.getBinaryStream();
            images = new Image(in);
            
        
        circel.setFill(new ImagePattern(images));
          
            
        }
        


                
   
          
      }
        
      
  

catch(Exception e) {
  
}
         
        
      
                       
                                                
                   
     

                    
                  
                    
                    
                    
                    
    

        
    }    

    @FXML
    private void gotowrithdrow(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("withdraw.fxml"));
        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
         scene = new Scene(root);
         stage.setScene(scene);
         stage.show();
        
        
    }
    @FXML
    private void gotoTransfore(ActionEvent event) throws IOException{
         Parent root = FXMLLoader.load(getClass().getResource("transfer.fxml"));
        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
         scene = new Scene(root);
         stage.setScene(scene);
         stage.show();
    }
    @FXML
    private void logout(ActionEvent event) throws IOException, ClassNotFoundException, SQLException{
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
         scene = new Scene(root);
         stage.setScene(scene);
         stage.show();
         
         String from="lsabankbd@gmail.com";
        String to=s2;
        String host="localhost";
        String subject="Logout";
         String massage="Your Account is now logout";
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
          Connection conn = null;
         Class.forName("com.mysql.cj.jdbc.Driver");
    
      conn = DriverManager.getConnection(DB_URL,USER,PASS);
          int x=0;
              String spl = "update save set status= ? where accountnum= ?";
               PreparedStatement ps = conn.prepareStatement(spl);
               ps.setInt(1, x);
               ps.setInt(2, s10);
             ps.executeUpdate();
             ps.close();





         
         
         
         
         
    }
    
    @FXML
     private void gotoprofile(ActionEvent event) throws IOException{
         Parent root = FXMLLoader.load(getClass().getResource("profile.fxml"));
        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
         scene = new Scene(root);
         stage.setScene(scene);
         stage.show();
         
     }
     @FXML
     private void deposite(ActionEvent event) throws IOException{
         Parent root = FXMLLoader.load(getClass().getResource("deposite.fxml"));
        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
         scene = new Scene(root);
         stage.setScene(scene);
         stage.show();
     }
      @FXML
    void massage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Admin.fxml"));
        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
         scene = new Scene(root);
         stage.setScene(scene);
         stage.show();
          
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
