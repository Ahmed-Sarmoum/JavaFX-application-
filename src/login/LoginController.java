package login;

import allFunction.function;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Mido
 */
public class LoginController implements Initializable {

    public static String typeTxt;
    
    @FXML
    private JFXButton btnAdmin;

    @FXML
    private JFXButton btnResComercial;

    @FXML
    private JFXButton btnResStock;

    @FXML
        private JFXButton btnCaissier;
    @FXML
    private Label labelPresp;
    @FXML
    private VBox adminLogin;
    @FXML
    private StackPane root;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //System.out.println(btnAdmin.getText());
//        
//         try {
//             
//             adminLogin = FXMLLoader.load(getClass().getResource("usersLogin.fxml"));
//            
//             
//         } catch (IOException ex) {
//             Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
//         }
//        
         // annimation de button
        function f1 = new function();

        f1.transitionLogin(btnAdmin);
        f1.transitionLogin(btnResComercial);
        f1.transitionLogin(btnResStock);
        f1.transitionLogin(btnCaissier);
  
           
           /************************************************************************************/
     
    }    
   
    @FXML
    void exit(ActionEvent event) {

        Platform.exit();
    }
    
   @FXML
   private void showLoginAdmin(MouseEvent e) throws IOException{
       typeTxt = btnAdmin.getText();
       adminLogin = FXMLLoader.load(getClass().getResource("usersLogin.fxml"));
       new JFXDialog( root,adminLogin, JFXDialog.DialogTransition.CENTER).show();
       
   }
   @FXML
   private void showLoginResComercial(ActionEvent e) throws IOException{
       typeTxt = btnResComercial.getText();
       adminLogin = FXMLLoader.load(getClass().getResource("usersLogin.fxml"));
       new JFXDialog( root,adminLogin, JFXDialog.DialogTransition.CENTER).show();
   }
   @FXML
   private void showLoginResStock(ActionEvent e) throws IOException{
       typeTxt = btnResStock.getText();
       adminLogin = FXMLLoader.load(getClass().getResource("usersLogin.fxml"));
       new JFXDialog( root,adminLogin, JFXDialog.DialogTransition.CENTER).show();
   }
   @FXML
   private void showLoginCaissier(ActionEvent e) throws IOException{
       typeTxt = btnCaissier.getText();
       adminLogin = FXMLLoader.load(getClass().getResource("usersLogin.fxml"));
       new JFXDialog( root,adminLogin, JFXDialog.DialogTransition.CENTER).show();
   }
    
    
}
