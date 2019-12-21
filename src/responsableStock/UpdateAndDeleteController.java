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
import javafx.event.ActionEvent;
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
import vo.prodectVo;

/**
 * FXML Controller class
 *
 * @author Mido
 */
public class UpdateAndDeleteController implements Initializable {

    @FXML
    private JFXButton deleteBtn;
    
     @FXML
    private StackPane homePane;
    
     @FXML
    private StackPane upDePlatform;
     
    @FXML
    private AnchorPane updateQuantityGeneral;
    
     @FXML
    private void updateQG(){
        
        try {
            updateQuantityGeneral = FXMLLoader.load(getClass().getResource("/responsableStock/updateQuentityGeneral.fxml"));

            new JFXDialog(upDePlatform, updateQuantityGeneral, JFXDialog.DialogTransition.TOP).show();
            
        } catch (IOException ex) {
            Logger.getLogger(UpdateQuentityGeneralController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @FXML
    private void deleteGeneralQuantity() {

        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Exit");
        a.setHeaderText("!!");
        a.setContentText("are you shur?");

        a.setOnCloseRequest(e -> {
            ButtonType res = a.getResult();
            if (res == ButtonType.OK) {
               

                try {
                    int isDelete = dao.quantityDao.getInstence().deleteGeneralQuantity
                                            (HomeStockPlatformController.isSelected);
      
                     
                } catch (SQLException ex) {
                    Logger.getLogger(UpdateAndDeleteController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                        // afficher si l'Delete success
                        Image img = new Image("/img/chek.png");
                        notification = notification.create().title("Delete Prodect").
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
