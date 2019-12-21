package admins;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Mido
 */
public class DeleteUserController implements Initializable {

    Notifications notification;
    @FXML
    private JFXButton ref;
    public static File outputFile;
    
    @FXML
    private StackPane updatePlatform;
    @FXML
    private StackPane tabpan;

    @FXML
    void delete(ActionEvent event) throws SQLException {

        try {
            PreparedStatement ps = null;
            Connection con = null;
            ResultSet rs ;
            
            con = dao.Dao.getConnection();

            String sql = "SELECT etat FROM utilisateurs WHERE phone = "+HomeAdminController.compairPhone;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()){
                if(rs.getInt(1) == 0){
                
                  int stat = dao.usersDao.getInstance().deleteUsers(HomeAdminController.compairPhone);

            //Delete user image from the folder 'imgUsers'

                outputFile = new File("F:\\Neveau\\projet\\PFEProject\\src\\imgUsers\\" + HomeAdminController.compairimage);

                outputFile.delete();

                Image img = new Image("/img/chek.png");
                notification = notification.create().title("Delete").
                        text("Delete Successfully").
                        graphic(new ImageView(img)).
                        hideAfter(Duration.seconds(5)).
                        position(Pos.TOP_CENTER).darkStyle();
                notification.show();   
                } else {
                Image img = new Image("/img/remove.png");
                notification = notification.create().title("Delete").
                        text("Delete Not Successfully, \nthis user is already opened ...").
                        graphic(new ImageView(img)).
                        hideAfter(Duration.seconds(5)).
                        position(Pos.TOP_CENTER).darkStyle();
                notification.show();
            }

            }
           
            
        } catch (Exception ex) {
        }
    }
    
    @FXML
    void Update(ActionEvent event) throws IOException {

        Stage stage = new Stage();
        updatePlatform = FXMLLoader.load(getClass().getResource("/admins/updatePlatform.fxml"));
        
        Scene s = new Scene(updatePlatform);
        stage.setTitle("Update User");
        stage.setScene(s);
        s.getStylesheets().add(getClass().getResource("/styleCss/chat.css").toExternalForm());
        stage.setResizable(false);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
