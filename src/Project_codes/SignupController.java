
package Project_codes;
import static Project_codes.LoginController.s2;
import java.io.*;
import java.sql.Connection;
import java.sql.*;
import java.sql.DriverManager;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
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


public class SignupController implements Initializable {

    static final String DB_URL = "jdbc:mysql://localhost:3306/sakib";
   static final String USER = "root";
   static final String PASS = "";
    
    private Scene scene;
    private Parent root;
    private Stage stage;
    
    @FXML
    private TextField name;
    @FXML
    private TextField number;
     @FXML
    private PasswordField pass;
    @FXML
    private TextField email;
    
     public static  String s1;
    public static  String s;
    
    
    @FXML
    private Button button;
    @FXML
    private DatePicker date;
    @FXML
    private TextField nationalist;
      @FXML
    private TextField Addresas;

    
     @FXML
    private ComboBox use;

    @FXML
    void gender(ActionEvent event) {
      s=use.getSelectionModel().getSelectedItem().toString();
        
         
        
    }
   
@FXML
    private ComboBox use1;

    @FXML
    void Religion(ActionEvent event) {
s1=use1.getSelectionModel().getSelectedItem().toString();
        
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list=FXCollections.observableArrayList("MALE","FEMALE","Others");
    use.setItems(list);
    ObservableList<String> list1=FXCollections.observableArrayList("Muslim","Hinduism","Buddhism","Christian");
    use1.setItems(list1);
    }    

    @FXML
    private void switchtologin(ActionEvent event) throws IOException, ClassNotFoundException, SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
        
        String na=name.getText();
        String em=email.getText();
        String password=pass.getText();
         String sa 
        = generateStorngPasswordHash(password);
          
         
        
        String num =number.getText();
       
        
        
        LocalDate sw=date.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
String formatted = sw.format(formatter);
        
        
        
        
        String add=Addresas.getText();
        String nation=nationalist.getText();
        
        
        
        
        
        
        
        
        
        
        
        
        Window owner = button.getScene().getWindow();
        if (name.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter your name");
            return;
        }
        
        
        if (pass.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter a password");
            return;
        }
        if (number.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter a number");
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
          showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "This email is already used");
           return;
      }
      
        
        Random rand = new Random();
        int ran = rand.nextInt(1000);
        int ac=0;
        
       
        String INSERT_QUERY = "INSERT INTO save (name, email, password,number,accountnum,amount, dateofbath,address,Nation,Gender,Religions) VALUES (?, ?, ?, ?,?,?,?,?,?,?,?)";
         
       
         PreparedStatement ps = conn.prepareStatement(INSERT_QUERY);
            ps.setString(1, na);
            ps.setString(2, em);
            ps.setString(3, sa);
            ps.setString(4, num);
                       
            ps.setInt(5, ran);
            ps.setInt(6,ac );
            ps.setString(7, formatted);
            ps.setString(8, add);
            ps.setString(9, nation);
            ps.setString(10, s);
            ps.setString(11, s1);

            
           
            ps.executeUpdate();
        
        
        
        
        
        
        
        
         conn.close();
          String from="lsabankbd@gmail.com";
        String to=email.getText();
        String host="localhost";
        String subject="Account Created";
         String massage="Welcome Your Account Is Successfully Created";
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
    
         
         
         
         
         showAlert(Alert.AlertType.CONFIRMATION, owner, "Account Done",
                "Congratulation Your Account is succefully done Now you can Login");
         
         name.setText("");
         email.setText("");
         pass.setText("");
         number.setText("");
         Addresas.setText("");
         nationalist.setText("");
        
       
         
         
         
    
        
    }
    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    @FXML
    private void login(ActionEvent event) throws IOException {
        
         Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
         scene = new Scene(root);
         stage.setScene(scene);
         stage.show();
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
