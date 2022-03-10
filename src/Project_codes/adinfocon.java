
package Project_codes;

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
import java.util.ResourceBundle;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;


public class adinfocon  implements Initializable{
     private Scene scene;
    private Parent root;
    private Stage stage;
    @FXML
    private TableColumn<infoclass, String> ac;

    @FXML
    private TableView<infoclass> allinfo;

    @FXML
    private TableColumn<infoclass, String> amu;

    @FXML
    private TableColumn<infoclass, String> em;

    @FXML
    private TableColumn<infoclass, String> nam;

    @FXML
    private TableColumn<infoclass, String> num;
    @FXML
    private TextField text;
    @FXML
    private Button button;
     public static String s1;
  public static String s2;
 
  public static String s4;
  public static String s12;
  public static String s6;


    @FXML
    void deposite(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("deposite.fxml"));
        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
         scene = new Scene(root);
         stage.setScene(scene);
         stage.show();

    }

    @FXML
    void logout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("adlog.fxml"));
        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
         scene = new Scene(root);
         stage.setScene(scene);
         stage.show();

    }
ObservableList<infoclass> oblist=FXCollections.observableArrayList();
    @FXML
    void serch(ActionEvent event) throws ClassNotFoundException, SQLException {
        Window owner = button.getScene().getWindow();
        Connection con= null;
        Class.forName("com.mysql.cj.jdbc.Driver");
    
      con = DriverManager.getConnection(DB_URL,USER,PASS);
      String query = "Select * from save where accountnum=?";
      PreparedStatement pst = con.prepareStatement(query);
      pst.setString(1, text.getText());
     
      ResultSet rs = pst.executeQuery(); 
      
      if(rs.next()){
          s1=rs.getString(1);
          s2=rs.getString(2);
          s12=rs.getString(4);
          s4=rs.getString(5);
          s6=rs.getString(6);
          
           
          
         
          showAlert(Alert.AlertType.INFORMATION, owner, "Found",
                "Name: "+s1+" Email: "+s2+" Number: "+s12+" AccountNumber: "+s4+" Amount: "+s6);
          text.setText("");
          
      }
      else{
          showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "No info Found");
      }
       
        
        

    }
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Connection conn = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
    
      conn = DriverManager.getConnection(DB_URL,USER,PASS);
       ResultSet rs =conn.createStatement().executeQuery("select * from save");
       while(rs.next()){
           oblist.add(new infoclass(rs.getString("name"),rs.getString("email"),rs.getString("number"),rs.getString("accountnum"),rs.getString("amount")));
       }
       conn.close();
       rs.close();

        }
        catch(Exception e){
            
            
        }
  
            
        nam.setCellValueFactory(new PropertyValueFactory<>("name1") );
        em.setCellValueFactory(new PropertyValueFactory<>("email1") );
        num.setCellValueFactory(new PropertyValueFactory<>("number1") );
        ac.setCellValueFactory(new PropertyValueFactory<>("acc1") );
        amu.setCellValueFactory(new PropertyValueFactory<>("amount1") );
        allinfo.setItems(oblist);
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
