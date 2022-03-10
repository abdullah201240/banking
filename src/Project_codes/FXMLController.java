
package Project_codes;

import static Project_codes.ForgetpassController.randomcode;
import static Project_codes.ForgetpassController.to;
import static Project_codes.LoginController.DB_URL;
import static Project_codes.LoginController.PASS;
import static Project_codes.LoginController.USER;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.application.Application;
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
import java.util.*;
import javafx.scene.control.PasswordField;
import javax.mail.*;
import javax.activation.*;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.mail.internet.*;


public class FXMLController  extends LoginController      implements Initializable {
 
    static final String DB_URL = "jdbc:mysql://localhost:3306/sakib";
   static final String USER = "root";
   static final String PASS = "";
   public static int s7;
   public static int s8;

    private Scene scene;
    private Parent root;
    private Stage stage;
    @FXML
    private TextField accounts;
    @FXML
    private TextField amount;
    @FXML
    private PasswordField password;
     @FXML
    private Button my;
      @FXML
    private TextField code;

    

    @FXML
    private Button ons;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

     @FXML
    private void homesection(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("homesection.fxml"));
        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
         scene = new Scene(root);
         stage.setScene(scene);
         stage.show();
         
        
    }
    
    @FXML
    void sentcode(ActionEvent event) throws NoSuchAlgorithmException, InvalidKeySpecException {
         Window on=ons.getScene().getWindow();
         String mypass=password.getText();
        String myammount=amount.getText();
        String myaccountnum=accounts.getText();
        String s=myammount;
        int i=Integer.parseInt(s); 
        int oi=i;
        boolean matcheds = validatePassword(mypass, sakib);
         if(matcheds){
             if(i<=s5){
                  Random rand=new Random();
         randomcode=rand.nextInt(999999);
        String from="lsabankbd@gmail.com";
         to=s2;
        String host="localhost";
        
         String subject="Money Transfercode";
         String massage="Your Money Transfercode is "+randomcode;
         
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
             showAlert(Alert.AlertType.CONFIRMATION, on, "Email",
                "Code is sent");
           
             
         }
         catch(Exception e){
             e.printStackTrace();
             
         }
         
             }
         }
        
        
        
        

    }
    @FXML
    private void paynow(ActionEvent event) throws ClassNotFoundException, SQLException{
        String mypass=password.getText();
        String myammount=amount.getText();
        String myaccountnum=accounts.getText();
        String s=myammount;
        int i=Integer.parseInt(s); 
        int oi=i;
        String ca=code.getText();
        int cat=Integer.parseInt(ca);
        int z=randomcode;

        
        Window on=my.getScene().getWindow();
         Connection conn = null;
  
    Class.forName("com.mysql.cj.jdbc.Driver");
    
      conn = DriverManager.getConnection(DB_URL,USER,PASS);
      
      String query = "Select * from save where accountnum=?";
      PreparedStatement pst = conn.prepareStatement(query);
      pst.setString(1,myaccountnum );
      
      ResultSet rs = pst.executeQuery(); 
      
              if(cat==z){
                           if(rs.next()){
                  s7=rs.getInt(5);
                   s8=rs.getInt(6);
                   i=i+s8;
                   String INSERT_QUERY = "update save set amount= ? where accountnum= ?";
               PreparedStatement ps = conn.prepareStatement(INSERT_QUERY);
                 ps.setInt(1, i);
                 ps.setInt(2, s7);
                  ps.executeUpdate();
              
    ps.close(); 
    s5=s5-oi;
     String sql="update save set amount= ? where accountnum= ?";
    PreparedStatement pv=conn.prepareStatement(sql);
    pv.setInt(1, s5);
    pv.setInt(2, s10);
     pv.executeUpdate();
     String sl="update save set transfer= ? where accountnum= ?";
    PreparedStatement pvm=conn.prepareStatement(sl);
    pvm.setInt(1, oi);
    pvm.setInt(2, s10);
    pvm.executeUpdate();
     
     
     
      conn.close();
      pvm.close();
    pv.close();
    String from="lsabankbd@gmail.com";
        String to=s2;
        String host="localhost";
        String subject="MONEY TRANSFARE";
         String massage="Money Transfare "+oi+"tk "+" to accountNumber "+myaccountnum;
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
    
    
    showAlert(Alert.AlertType.CONFIRMATION, on, "Money Transfore",
                "Congratulation Money Transfore ");
    password.setText("");
    accounts.setText("");
    amount.setText("");
}  else{
                 showAlert(Alert.AlertType.WARNING, on, "Account",
                "Account not Found "); 
                 password.setText("");
    accounts.setText("");
    amount.setText(""); 
              }
     
   
              }
              else{
                 showAlert(Alert.AlertType.WARNING, on, "Not Same",
                "Code not same "); 
                 password.setText("");
    accounts.setText("");
    amount.setText("");
              
              }
          }
          
          
      
      
        
    
    
    private static void showAlert(Alert.AlertType alertType, Window on, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(on);
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