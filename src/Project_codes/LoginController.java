
package Project_codes;

import static com.sun.javafx.scene.control.skin.FXVK.Type.EMAIL;
import java.sql.Connection;
import java.sql.*;
import java.io.*;
import java.sql.DriverManager;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.math.BigInteger;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Properties;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.stage.Window;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class LoginController implements Initializable {

    static final String DB_URL = "jdbc:mysql://localhost:3306/sakib";
   static final String USER = "root";
   static final String PASS = "";
  public static String s1;
  public static String s2;
  public static String s3;
  public static String s4;
  public static String s12;
  public static String s20;
  public static String s30;
  public static String s31;
  public static String s32;
  public static String s33;
  public static String s34;
  public static String s35;
  public static String sakib="sakib123";
  public static boolean matched;
 
  
  public static int s5;
  public static int s10;
  public static int s11;
  public static int s15;
  public static int s18;
  public static int s100;
  public static int s200;
  public static int s300;
  public static int s39;
  
  
    
    private Scene scene;
    private Parent root;
    private Stage stage;
    @FXML
    private TextField email;
    @FXML
    private PasswordField pass;
    @FXML
    private TextField out;
     @FXML
    private Button button;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void switchTosignup(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("signup.fxml"));
        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
         scene = new Scene(root);
         stage.setScene(scene);
         stage.show(); 
        
    }

    @FXML
    private void switchToforgetpass(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("forgetpass.fxml"));
        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
         scene = new Scene(root);
         stage.setScene(scene);
         stage.show(); 
        
    }

    @FXML
    private void switchtologin(ActionEvent event) throws FileNotFoundException, ClassNotFoundException, SQLException, IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        
       
        
        String em=email.getText();
      
       String pas=pass.getText();
        Window owner = button.getScene().getWindow();
       
      if (email.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter your email id");
            return;
        }
      if (pass.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter a password");
            return;
        }
      
       
      
      Connection conn = null;
  
    Class.forName("com.mysql.cj.jdbc.Driver");
    
      conn = DriverManager.getConnection(DB_URL,USER,PASS);
      
      
      
      String query = "Select * from save where email=?";
      PreparedStatement pst = conn.prepareStatement(query);
      pst.setString(1, email.getText());
     
      ResultSet rs = pst.executeQuery(); 
      if(rs.next()){
          sakib=rs.getString(3);
          s1=rs.getString(1);
          s39=rs.getInt(15);
           s2=rs.getString(2);
           
           s3=rs.getString(3);
           s12=rs.getString(4);
           s4=rs.getString(5);
           s30=rs.getString(11);
           s31=rs.getString(12);
           s32=rs.getString(13);
           s33=rs.getString(14);
           s34=rs.getString(10);
           
           s10=rs.getInt(5);
          
           
           s5=rs.getInt(6);
           s20=rs.getString(6);
           s15=rs.getInt(7);
           s11=rs.getInt(8);
           
           
           s18=rs.getInt(9);
      }
      else{
          showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter Correct Email");

      }
        
       matched = validatePassword(pas, sakib);
        
      if(matched){
          if(s39!=1){
               Parent root = FXMLLoader.load(getClass().getResource("homesection.fxml"));
        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
         scene = new Scene(root);
         stage.setScene(scene);
         stage.show();
         String from="lsabankbd@gmail.com";
        String to=s2;
        String host="localhost";
        String subject="Login";
         String massage="Your Account is Currently login";
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
               int x=1;
              String spl = "update save set status= ? where accountnum= ?";
               PreparedStatement ps = conn.prepareStatement(spl);
               ps.setInt(1, x);
               ps.setInt(2, s10);
             ps.executeUpdate();
             ps.close();
              
          }
          else {
         showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Account is Login Try in later");
      }
      }
      else{
        showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter Correct Password");
  
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
     private static boolean validatePassword(String originalPassword, String storedPassword) 
    throws NoSuchAlgorithmException, InvalidKeySpecException
{
    String[] parts = storedPassword.split(":");
    int iterations = Integer.parseInt(parts[0]);

    byte[] salt = fromHex(parts[1]);
    byte[] hash = fromHex(parts[2]);

    PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), 
        salt, iterations, hash.length * 8);
    SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
    byte[] testHash = skf.generateSecret(spec).getEncoded();

    int diff = hash.length ^ testHash.length;
    for(int i = 0; i < hash.length && i < testHash.length; i++)
    {
        diff |= hash[i] ^ testHash[i];
    }
    return diff == 0;
}
private static byte[] fromHex(String hex) throws NoSuchAlgorithmException
{
    byte[] bytes = new byte[hex.length() / 2];
    for(int i = 0; i < bytes.length ;i++)
    {
        bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
    }
    return bytes;
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

     
    


