
package login;

import admins.hashClass;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import static login.UsersLoginController.img;

/**
 * FXML Controller class
 *
 * @author Mido
 */
public class AdminLoginLikeCashierController implements Initializable {

      @FXML
    private Circle imgUser;

    @FXML
    private Text typetxt;

    @FXML
    private JFXTextField txtusername;

    @FXML
    private JFXPasswordField txtpassword;

    @FXML
    private Label lblmsgE;

    @FXML
    private JFXButton btnLogin;

    @FXML
    void filterImage(KeyEvent event) {

         Connection con = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
  
        try{
            
            con = dao.Dao.getConnection();
            String sql = "SELECT image FROM utilisateurs WHERE username = '"+txtusername.getText()+""
                    + "' AND password = '"+hashClass.hashing(txtpassword.getText())+"'"
                    + "AND type = '"+typetxt.getText()+"'";
            ps = con.prepareStatement(sql);
            
            rs = ps.executeQuery();
            
            if(rs.next()){
                Image image = new Image("/imgUsers/"+rs.getString(1));
                imgUser.setFill(new ImagePattern(image));
                img = rs.getString(1);//pour garder l'image dans un variable static
            }else{
                Image image = new Image("/img/bgnn");
                imgUser.setFill(new ImagePattern(image));
            }
            
        }catch(SQLException|ClassNotFoundException e){
            
        
        }
    }

    @FXML
    void login(ActionEvent event) throws ClassNotFoundException, SQLException {

        PreparedStatement ps = null,ps1 = null;
        Connection con = null;
        ResultSet rs = null;
        try{
      
        String sql = "SELECT * FROM utilisateurs WHERE type = '"+typetxt.getText()+"' AND "
                + " username = '"+txtusername.getText()+
                "' AND password = '"+hashClass.hashing(txtpassword.getText())+"';";
        
        con = dao.Dao.getConnection();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        
        
        
        if(rs.next()){
            
            UsersLoginController.type = rs.getString("type");
            UsersLoginController.id = rs.getInt("id");
            //id1 =id;
            UsersLoginController.firstName = rs.getString("firstname");
            UsersLoginController.lastName = rs.getString("lastname");
       
         if(rs.getInt("etat") == 1){//pour login une foi
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("WARNING");
            a.setHeaderText("!!");
            a.setContentText("You are already Conected ..!! ");
            
            a.showAndWait();
        }else{
            
            // un seul utilisateur peut avoir login selon le nombre etat = 1
            String sql1 = "UPDATE utilisateurs SET etat = "+1+" WHERE id = "+UsersLoginController.id;
                ps1 = con.prepareStatement(sql1);
                
                ps1.executeUpdate();
            
              Stage  stage = new Stage();
               Parent root = FXMLLoader.load(getClass().getResource("/caissiers/homeCashier.fxml"));
      
                Scene s = new Scene(root);
                stage.setTitle("Cachier Interfac");
                stage.setScene(s);
                stage.centerOnScreen();
                //stage.setX(120);
                //stage.setY(50);
                s.getStylesheets().add(getClass().getResource("/styleCss/chat.css").toExternalForm());
                stage.show();
                //stage.setResizable(false);
        
        }
        }else{
            lblmsgE.setText("Username and/or Password Wrong..!!");
        }
            
        }catch(SQLException e){
            
            lblmsgE.setText("Connection Failed ..:(");
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ps.close();
            rs.close();
            con.close();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        typetxt.setText("Cashier");
         Image image = new Image("/img/bgnn");
                imgUser.setFill(new ImagePattern(image));
    }    
    
}
