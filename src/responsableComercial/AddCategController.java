package responsableComercial;

import login.LoginController;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dao.categoryDao;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import vo.categVo;

/**
 * FXML Controller class
 *
 * @author Mido
 */
public class AddCategController implements Initializable {

     @FXML
    private JFXTextField categName;

    @FXML
    private JFXTextField chefName;

    @FXML
    private JFXComboBox<String> status;

    Notifications notification;
    
    
    
    @FXML
    void addCategory(ActionEvent event) throws SQLException {

        boolean isT = validation.isText(categName.getText());
        boolean isE = validation.isEmpty(categName.getText());
        
        
         if(isE == true){
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("!!");
            alert.setContentText("Category name is Empty...!!");
            
            alert.showAndWait();
            return;
        }
        
        if(isT == true){
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("!!");
            alert.setContentText("the value in category name is not text ..!!");
            
            alert.showAndWait();
            return;
        }
        
        if(status.getSelectionModel().getSelectedItem().equals("")){
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("!!");
            alert.setContentText("Status is Not Selected...!!");
            
            alert.showAndWait();
            return;
        }
       
        boolean isCefT = validation.isText(chefName.getText());
        boolean isChefE = validation.isEmpty(chefName.getText());
        
        if(isCefT == true){
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("!!");
            alert.setContentText("Chef Section name is Empty...!!");
            
            alert.showAndWait();
            return;
        }
        
        if(isChefE == true){
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("!!");
            alert.setContentText("the value in Chef Section name is not text ..!!");
            
            alert.showAndWait();
            return;
        }
        
        
        
        String categN = String.valueOf(categName.getText());
        String Stat = status.getSelectionModel().getSelectedItem();
        String chefS = String.valueOf(chefName.getText());
        
        categVo vo = new categVo();
        
        vo.setNameCategory(categN);
        vo.setIdRespos(UsersLoginController.id);
        vo.setStatus(status.getSelectionModel().getSelectedItem());
        vo.setChefSection(chefS);
        
        
        int isInsert = categoryDao.getInstence().addCategory(vo);
        
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
        categName.clear();
        chefName.clear();
        status.setValue("");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        
        String t1 = "Active";
        String t2 = "Non Active";
        ObservableList<String> type = FXCollections.observableArrayList();
        type.addAll(t1, t2);
        status.setItems(type);
    }    
    
}









