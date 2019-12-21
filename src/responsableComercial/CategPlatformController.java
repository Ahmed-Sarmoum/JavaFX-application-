package responsableComercial;

import allFunction.function;
import login.LoginController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import vo.categVo;
import vo.rayonVo;

/**
 * FXML Controller class
 *
 * @author Mido
 */
public class CategPlatformController implements Initializable {

    
    
    @FXML
    private TableView<categVo> tabV;

    @FXML
    private TableColumn<categVo, Integer> idC;

    @FXML
    private TableColumn<categVo, String> nameC;

    @FXML
    private TableColumn<categVo, String> statusC;

    @FXML
    private TableColumn<categVo, String> chefC;
    
   @FXML
    private AnchorPane addCategPlatform;
    
    @FXML
    private AnchorPane updateCategPlatform;
    
    @FXML
    private AnchorPane helpPage;
    
    @FXML
    private StackPane stackCateg;
    
    @FXML
    private JFXButton refresh;
    
    Notifications notification;
    
    ObservableList<categVo> list = FXCollections.observableArrayList();
    
     @FXML
    private TableView<rayonVo> tabV1;

    @FXML
    private TableColumn<rayonVo, Integer> idR;

    @FXML
    private TableColumn<rayonVo, String> nameR;

    @FXML
    private TableColumn<rayonVo, String> nameRes;

    @FXML
    private AnchorPane addRayonPlatform;


    ObservableList<rayonVo> list1 = FXCollections.observableArrayList();

    //Method for Show Platform Add Category
    @FXML
    void addRayonBtn(ActionEvent e) throws IOException {

        addRayonPlatform = FXMLLoader.load(getClass().getResource("/responsableComercial/addRayon.fxml"));

        new JFXDialog(stackCateg, addRayonPlatform, JFXDialog.DialogTransition.TOP).show();
    }
    
    //Method for Show Platform Add Category
    @FXML
    void addCategoryBtn(ActionEvent e) throws IOException{
        
        addCategPlatform = FXMLLoader.load(getClass().getResource("/responsableComercial/addCateg.fxml"));
        
        new JFXDialog(stackCateg, addCategPlatform, JFXDialog.DialogTransition.TOP).show();
    }
    
    
    //Method for Show Platform Update Category
    @FXML
    void UpdateCategoryBtn(ActionEvent e) throws IOException{
        
        updateCategPlatform = FXMLLoader.load(getClass().getResource("/responsableComercial/updateCateg.fxml"));
        
        new JFXDialog(stackCateg, updateCategPlatform, JFXDialog.DialogTransition.TOP).show();
    }
    
    
    @FXML
    private void deleteRayon() throws SQLException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("!!");
        alert.setContentText("are you shur?");

        alert.setOnCloseRequest(e -> {
            ButtonType res = alert.getResult();
            if (res == ButtonType.OK) {

                try {
                    rayonVo isSelect = tabV1.getSelectionModel().getSelectedItem();

                    int isDelete = dao.rayonDao.getInstence().deleteRayon(isSelect);

                    if (isDelete != 0) {
                        Image img = new Image("/img/chek.png");
                        notification = notification.create().title("Delete Rayon").
                                text("Delete Successfully").
                                graphic(new ImageView(img)).
                                hideAfter(Duration.seconds(5)).
                                position(Pos.CENTER).darkStyle();
                        notification.show();
                        list1.clear();
                        dataTableRay();
                    } else {
                        Alert a = new Alert(Alert.AlertType.ERROR);
                        a.setTitle("ERROR");
                        a.setHeaderText("!!");
                        a.setContentText("Echec To Delete ... :(");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(FournisseurPlatformController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {

            }
        });

        alert.showAndWait();

    }
    
    //Help Method...
    @FXML
    void help(ActionEvent event) throws IOException {

        helpPage = FXMLLoader.load(getClass().getResource("/responsableComercial/helpCateg.fxml"));
        
        new JFXDialog(stackCateg, helpPage, JFXDialog.DialogTransition.LEFT).show();
    }
    
    
    @FXML
    void exit(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("!!");
        alert.setContentText("are you shur?");
        
        alert.setOnCloseRequest(e->{
            ButtonType res = alert.getResult();
            if(res == ButtonType.OK){
                function f = new function();
                try {
                    f.exit();
                } catch (SQLException ex) {
                    Logger.getLogger(CategPlatformController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                
            }
        });
        
        alert.showAndWait();
    }
    
    
    @FXML
    private void refresh(ActionEvent event) throws IOException {

        list.clear();
        list1.clear();
        
         dataTableCateg();
         dataTableRay();
    }
    
    @FXML
    private void deleteCategory() throws SQLException{
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("!!");
        alert.setContentText("are you shur?");
        
        alert.setOnCloseRequest(e->{
            ButtonType res = alert.getResult();
            if(res == ButtonType.OK){
                
                try {
                    categVo isSelect = tabV.getSelectionModel().getSelectedItem();
                    
                    int isDelete = dao.categoryDao.getInstence().deleteCategory(isSelect);
                    
                    if(isDelete != 0){
                        Image img = new Image("/img/chek.png");
                        notification = notification.create().title("Delete Admin").
                                text("Delete Successfully").
                                graphic(new ImageView(img)).
                                hideAfter(Duration.seconds(5)).
                                position(Pos.CENTER).darkStyle();
                        notification.show();
                        list.clear();
                        dataTableCateg();
                    }else{
                        Alert a = new Alert(Alert.AlertType.ERROR);
                        a.setTitle("ERROR");
                        a.setHeaderText("!!");
                        a.setContentText("Echec To Delete ... :(");
                    }       } catch (SQLException ex) {
                    Logger.getLogger(CategPlatformController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                
            }
        });
        
        alert.showAndWait();
       
        
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        dataTableCateg();
        dataTableRay();
          
    
    }  
    
    public void dataTableRay(){
        
         try {

            Connection con = dao.Dao.getConnection();
            String sql = "SELECT id,nom,nomR FROM rayons";

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                list1.add(new rayonVo(rs.getInt(1), rs.getString(2), rs.getString(3)));

            }

        } catch (SQLException | ClassNotFoundException e) {

        }

        idR.setCellValueFactory(new PropertyValueFactory<rayonVo, Integer>("id"));
        nameR.setCellValueFactory(new PropertyValueFactory<rayonVo, String>("nom"));
        nameRes.setCellValueFactory(new PropertyValueFactory<rayonVo, String>("nomRes"));

        tabV1.setItems(list1);
    }
    
    public void dataTableCateg(){

try{
            
            
                Connection con = dao.Dao.getConnection();
                String sql = "SELECT id,nom,status,chefS FROM catégories";
                
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                
                list.add(new categVo(rs.getInt(1), rs.getString(2)
                        , rs.getString(3), rs.getString(4)));
            }
            
        }catch(SQLException|ClassNotFoundException e){
            
        }
        
        idC.setCellValueFactory(new PropertyValueFactory<categVo,Integer>("id"));
        nameC.setCellValueFactory(new PropertyValueFactory<categVo,String>("nameCategory"));
        statusC.setCellValueFactory(new PropertyValueFactory<categVo,String>("status"));
        chefC.setCellValueFactory(new PropertyValueFactory<categVo,String>("ChefSection"));
        
        tabV.setItems(list);
}
}












