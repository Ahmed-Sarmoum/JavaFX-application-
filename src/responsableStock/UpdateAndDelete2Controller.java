package responsableStock;

import responsableStock.UpdateQuentityGeneralController;
import responsableStock.HomeStockPlatformController;
import login.LoginController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static responsableComercial.ProduitPlatformController.notification;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Mido
 */
public class UpdateAndDelete2Controller implements Initializable {

    @FXML
    private JFXButton deleteBtn;
    
     @FXML
    private StackPane homePane;
    
     @FXML
    private StackPane upDePlatform2;
     
    @FXML
    private AnchorPane updateQuantity;
    
     @FXML
    private void updateQ(){
        
        try {
            updateQuantity = FXMLLoader.load(getClass().getResource("/responsableStock/updateQuentity.fxml"));

            new JFXDialog(upDePlatform2, updateQuantity, JFXDialog.DialogTransition.TOP).show();
            
        } catch (IOException ex) {
            Logger.getLogger(UpdateQuentityGeneralController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @FXML
    private void deleteQuantity() {

        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Exit");
        a.setHeaderText("!!");
        a.setContentText("are you shur?");

        a.setOnCloseRequest(e -> {
            ButtonType res = a.getResult();
            if (res == ButtonType.OK) {
               

                try {
                    int isDelete = dao.quantityDao.getInstence().deleteQuantity
                                                (HomeStockPlatformController.isSelected2);
      
                     
                } catch (SQLException ex) {
                    Logger.getLogger(UpdateAndDeleteController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                        // afficher si l'Delete success
                        Image img = new Image("/img/chek.png");
                        notification = notification.create().title("Delete").
                                text("Delete Successfully").
                                graphic(new ImageView(img)).
                                hideAfter(Duration.seconds(2)).
                                position(Pos.BOTTOM_RIGHT).darkStyle();
                        notification.show();
                
            } else {

            }
        });

        a.showAndWait();
        
         
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
