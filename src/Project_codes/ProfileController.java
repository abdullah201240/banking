/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Project_codes;

import static Project_codes.HomesectionController.DB_URL;
import static Project_codes.HomesectionController.PASS;
import static Project_codes.HomesectionController.USER;
import static Project_codes.HomesectionController.b;
import static Project_codes.HomesectionController.imagepath;
import static Project_codes.LoginController.s10;
import static Project_codes.LoginController.s2;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class ProfileController extends LoginController  implements Initializable {
 @FXML
    private TextField name;
  @FXML
    private TextField acno;
  @FXML
    private TextField email;
   @FXML
    private TextField phone;
    @FXML
    private TextField address;

    @FXML
    private TextField bdate;
    
     @FXML
    private TextField gender;
        @FXML
    private TextField nnation;
         
           @FXML
    private Circle ppset;
           @FXML
    private Button button12;

    

    @FXML
    private TextField  regi;
    
    private Scene scene;
    private Parent root;
    private Stage stage;
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        name.setText(s1);
        name.setEditable(false);
        
        
        acno.setText(s4);
        acno.setEditable(false);
        email.setText(s2);
        email.setEditable(false);
        phone.setText(s12);
        phone.setEditable(false);
        nnation.setText(s30);
        nnation.setEditable(false);
        gender.setText(s31);
        gender.setEditable(false);
        regi.setText(s32);
        regi.setEditable(false);
        bdate.setText(s33);
        bdate.setEditable(false);
        address.setText(s34);
        address.setEditable(false);
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
                Image images = new Image(in);
        ppset.setFill(new ImagePattern(images));
          
            
        }
        


                
   
          
      }
        
      
  

catch(Exception e) {
  
}
     
        
        
    }  
    @FXML
    private void home(ActionEvent event) throws IOException{
         Parent root = FXMLLoader.load(getClass().getResource("homesection.fxml"));
        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
         scene = new Scene(root);
         stage.setScene(scene);
         stage.show();
    }
     @FXML
    void save(ActionEvent event) throws FileNotFoundException, ClassNotFoundException, SQLException {
        Window owner = button12.getScene().getWindow();
        InputStream in = new FileInputStream(imagepath);
         
        Connection co = null;
         Class.forName("com.mysql.cj.jdbc.Driver");
    
      co = DriverManager.getConnection(DB_URL,USER,PASS);
       String splz = "update save set profilepic= ? where accountnum= ?";
               PreparedStatement psz = co.prepareStatement(splz);
               psz.setBlob(1, in);
               psz.setInt(2, s10);
             psz.executeUpdate();
             psz.close();
             co.close();
             showAlert(Alert.AlertType.CONFIRMATION, owner, "Done",
                "Profile pic save sucessfully");


    }

    @FXML
    void set(ActionEvent event) {
         FileChooser chooser = new FileChooser();
    chooser.setTitle("Open File");
    File file = chooser.showOpenDialog(new Stage());
    if(file != null) {
         imagepath = file.getPath();
        
        Image images = new Image(imagepath);
         ppset.setFill(new ImagePattern(images));
        
        
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
