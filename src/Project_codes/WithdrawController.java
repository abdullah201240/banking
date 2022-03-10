/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Project_codes;

import static Project_codes.FXMLController.DB_URL;
import static Project_codes.FXMLController.PASS;
import static Project_codes.FXMLController.USER;
import static Project_codes.FXMLController.s7;
import static Project_codes.ForgetpassController.randomcode;
import static Project_codes.ForgetpassController.to;
import static Project_codes.LoginController.s2;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class WithdrawController extends LoginController implements Initializable {

    private Scene scene;
    private Parent root;
    private Stage stage;
    @FXML
    private TextField wamount;
    @FXML
    private PasswordField passwor;
     @FXML
    private Button blackbutton; 
      @FXML
    private TextField code;
      @FXML
    private Button sent;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
     @FXML
    private void tryhome(ActionEvent event) throws IOException{
          Parent root = FXMLLoader.load(getClass().getResource("homesection.fxml"));
        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
         scene = new Scene(root);
         stage.setScene(scene);
         stage.show();
        
    }
    
    @FXML
    void sent(ActionEvent event) throws NoSuchAlgorithmException, InvalidKeySpecException {
      String wrammount=wamount.getText();
        String wrpass=passwor.getText();
        String pk=wrammount;
        int w=Integer.parseInt(pk);
        int t=w;
        Window own=sent.getScene().getWindow();
       boolean matcheds = validatePassword(wrpass, sakib);
       if(matcheds){
           if(w<=s5){
           Random rand=new Random();
         randomcode=rand.nextInt(999999);
        String from="lsabankbd@gmail.com";
         to=s2;
        String host="localhost";
        
         String subject="Money Withdrowcode";
         String massage="Your Money Withdrowcode is "+randomcode;
         
         boolean so=false;
         Properties p= new Properties();
         p.put("mail.smtp.auth","true");
         p.put("mail.smtp.starttls.enable","true");
         p.put("mail.smtp.host","smtp.gmail.com");
         p.put("mail.smtp.port","587");
         Session sa= Session.getDefaultInstance(p,new javax.mail.Authenticator(){
             
             protected PasswordAuthentication getPasswordAuthentication(){
                 return new PasswordAuthentication("lsabankbd@gmail.com","lsabank1234");
                 
             }
             
         });
         try{
             MimeMessage m= new MimeMessage(sa);
             m.setFrom(from);
             m.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
             m.setSubject(subject);
           m.setText(massage);
           Transport.send(m);
             showAlert(Alert.AlertType.CONFIRMATION, own, "Email",
                "Code is sent");
           
             
         }
         catch(Exception e){
             e.printStackTrace();
             
         }
           
       }
           
       }
       
        
        
    }
    
    
    
     @FXML
    private void wtksubmit (ActionEvent event) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException, InvalidKeySpecException{
        Window own=blackbutton.getScene().getWindow();
        String wrammount=wamount.getText();
        String wrpass=passwor.getText();
        String pk=wrammount;
        int w=Integer.parseInt(pk);
        int t=w;
        String sq=code.getText();
        int cat=Integer.parseInt(sq);
        int z=randomcode;
        boolean matcheds = validatePassword(wrpass, sakib);
       if(matcheds){
            if(w<=s5){
                if(z==cat){
                              s5=s5-w;
                Connection conn = null;
  
    Class.forName("com.mysql.cj.jdbc.Driver");
    
      conn = DriverManager.getConnection(DB_URL,USER,PASS);
      String INSERT_QUERY = "update save set amount= ? where accountnum= ?";
               PreparedStatement ps = conn.prepareStatement(INSERT_QUERY);
               ps.setInt(1, s5);
               ps.setInt(2, s10);
             ps.executeUpdate();
             String INSERT = "update save set withdraw= ? where accountnum= ?";
               PreparedStatement psm = conn.prepareStatement(INSERT);
               psm.setInt(1, w);
               psm.setInt(2, s10);
             psm.executeUpdate();
             
     conn.close();
     psm.close();
    ps.close();
    wamount.setText("");
    
    passwor.setText("");
    
    String from="lsabankbd@gmail.com";
        String to=s2;
        String host="localhost";
        String subject="MONEY Writhdraw";
         String massage="Writhdraw Money "+t+"tk ";
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
    
    
    
    
    
    
    showAlert(Alert.AlertType.CONFIRMATION, own, "Money Withdraw",
                "Congratulation Money Withdraw Done ");
                
            }
            else{
                 showAlert(Alert.AlertType.WARNING, own, "Not done",
                "code not same");
            }
        }else{
           showAlert(Alert.AlertType.ERROR, own, "Form Error!",
                "Not enough money");
 
        }
                }
      
        else{
           showAlert(Alert.AlertType.ERROR, own, "Form Error!",
                "Password not match");
 
        }
    }
    
private static void showAlert(Alert.AlertType alertType, Window own, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(own);
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
   

  

