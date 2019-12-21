package responsableComercial;

import login.LoginController;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import login.UsersLoginController;
import org.controlsfx.control.Notifications;
import validation.validation;
import vo.rayonVo;

/**
 * FXML Controller class
 *
 * @author Mido
 */
public class AddRayonController implements Initializable {

     @FXML
    private JFXTextField rayonName;

    @FXML
    private JFXTextField resName;


    Notifications notification;
    
    
    
    @FXML
    void addRayon(ActionEvent event) throws SQLException {

        boolean isE = validation.isEmpty(rayonName.getText());
        
        
         if(isE == true){
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("!!");
            alert.setContentText("Rayon name is Empty...!!");
            
            alert.showAndWait();
            return;
        }
        
    
       
        boolean isCefT = validation.isText(resName.getText());
        boolean isChefE = validation.isEmpty(resName.getText());
        
        if(isCefT == true){
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("!!");
            alert.setContentText("Responsible name is Empty...!!");
            
            alert.showAndWait();
            return;
        }
        
        if(isChefE == true){
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("!!");
            alert.setContentText("the value in Responsible name is not text ..!!");
            
            alert.showAndWait();
            return;
        }
        
        
        
        String rayonN = String.valueOf(rayonName.getText());
        String chefS = String.valueOf(resName.getText());
        
        rayonVo vo = new rayonVo();
        
        vo.setNom(rayonN);
        vo.setIdResC(UsersLoginController.id);
        vo.setNomRes(chefS);
        
        
        int isInsert = dao.rayonDao.getInstence().addRayon(vo);
        
        if(isInsert > 0){
            
            Image img = new Image("/img/chek.png");
            notification = notification.create().title("Insert New Category").
                    text("Save Successfully").
                    graphic(new ImageView(img)).
                    hideAfter(Duration.seconds(2)).
                    position(Pos.CENTER).darkStyle();
            notification.show();
            
            reset();
        }
    }
    
    public void reset(){
        rayonName.clear();
        resName.clear();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    
    }    
    
}









