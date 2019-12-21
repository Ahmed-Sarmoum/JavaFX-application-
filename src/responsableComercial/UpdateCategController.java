package responsableComercial;

import login.LoginController;
import allFunction.function;
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
public class UpdateCategController implements Initializable {

    
    @FXML
    private JFXTextField categName;

    @FXML
    private JFXTextField chefName;

    @FXML
    private JFXComboBox<String> status;

    @FXML
    private JFXTextField id;
    
    Notifications notification;

    @FXML
    void getData(ActionEvent event) throws SQLException {

        function f = new function();
        f.validationForGetData(id);//fuction for valid category ID field ...
     
        
        int nId = Integer.parseInt(id.getText());
        
        categVo vo = categoryDao.getInstence().getData(nId);
        
        if(vo == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("!!");
            alert.setContentText("ID Not Existe .. :(");
            
            alert.showAndWait();
            reset();
        }else{
            
            categName.setText(vo.getNameCategory());
            status.setValue(vo.getStatus());
            chefName.setText(vo.getChefSection());
            
        }
    }

    @FXML
    void updateCategory(ActionEvent event) throws SQLException {

        function f = new function();
        f.validationCategory(id, categName, status, chefName);//fuction for valid category field ...
     
        int idU =Integer.parseInt(id.getText());
        String nameC = categName.getText();
        String stat = status.getSelectionModel().getSelectedItem();
        String chefC = chefName.getText();
        
        categVo vo = new categVo();
        
        vo.setId(idU);
        vo.setNameCategory(nameC);
        vo.setIdRespos(UsersLoginController.id);
        vo.setStatus(stat);
        vo.setChefSection(chefC);
        
        int isUpdate = categoryDao.getInstence().updateCategory(vo);
        
        if(isUpdate > 0){
            Image img =new Image("/img/chek.png");
                notification =notification.create().title("Update").
                    text("Update SuccessFully").
                    graphic(new ImageView(img)).
                    hideAfter(Duration.seconds(2)).
                    position(Pos.CENTER).darkStyle();
                    notification.show();
                    reset();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Data update");
            alert.setHeaderText("ERROR Dialog");
            alert.setContentText("Sorry!unable to update record");

            alert.showAndWait();
        }
    }
    
    public void reset(){
        id.setText("");
        categName.setText("");
        status.setValue("");
        chefName.setText("");
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

