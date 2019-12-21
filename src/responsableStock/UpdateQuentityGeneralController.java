package responsableStock;

import responsableComercial.StatisticPlatformController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import static responsableComercial.ProduitPlatformController.notification;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import validation.validation;
import vo.quantityVo;

/**
 * FXML Controller class
 *
 * @author Mido
 */
public class UpdateQuentityGeneralController implements Initializable {

    @FXML
    private AnchorPane updateQuantityGeneral;

    @FXML
    private JFXComboBox comboProd;

    @FXML
    private JFXTextField qMofR;

    @FXML
    private JFXTextField qMofS;

    @FXML
    private JFXTextField qMax;

    @FXML
    private JFXButton updateBtn;
    
    
    @FXML
    private void updateQG() throws IOException{
        
        boolean qMoSE = validation.isEmpty(qMofS.getText());
        boolean qMoRE = validation.isEmpty(qMofR.getText());
        boolean qME = validation.isEmpty(qMax.getText());
        
        boolean qMoS = validation.isNumber(qMofS.getText());
        boolean qMoR = validation.isNumber(qMofR.getText());
        boolean qM = validation.isNumber(qMax.getText());
        
        if(comboProd.getItems().equals("Products")){
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("WARNING");
            a.setHeaderText("!!");
            a.setContentText("Product is Empty...");
            
            a.showAndWait();
            return;
        }
        
        if(qMoSE == true){
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("WARNING");
            a.setHeaderText("!!");
            a.setContentText("Quntity Min Of Stock is Empty...");
            
            a.showAndWait();
            return;
        }
        if(qMoS == true){
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("WARNING");
            a.setHeaderText("!!");
            a.setContentText("Enter Number In Quntity Min Of Stock !!");
            
            a.showAndWait();
            return;
        }
        if(qMoRE == true){
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("WARNING");
            a.setHeaderText("!!");
            a.setContentText("Quntity Min Of Rayon is Empty...");
            
            a.showAndWait();
            return;
        }
        if(qMoR == true){
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("WARNING");
            a.setHeaderText("!!");
            a.setContentText("Enter Number In Quntity Min Of Rayon !!");
            
            a.showAndWait();
            return;
        }
        
        if(qME == true){
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("WARNING");
            a.setHeaderText("!!");
            a.setContentText("Quntity Max is Empty...");
            
            a.showAndWait();
            return;
        }
        if(qM == true){
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("WARNING");
            a.setHeaderText("!!");
            a.setContentText("Enter Number In Quntity Max !!");
            
            a.showAndWait();
            return;
        }
        
         try{
            PreparedStatement ps = null;
            ResultSet rs = null;
            Connection con;
            
            String sql = "SELECT id FROM produits WHERE nom = '"
                    +comboProd.getSelectionModel().getSelectedItem()+"'";
            con = dao.Dao.getConnection();
            ps = con.prepareStatement(sql);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                
               int qs = Integer.parseInt(qMofS.getText());
               int qr = Integer.parseInt(qMofR.getText());
               int qm = Integer.parseInt(qMax.getText());
               
                quantityVo vo = new quantityVo();
                
                vo.setId(HomeStockPlatformController.isSelect);
                vo.setIdProduct(rs.getInt(1));
                vo.setQuantMax(qm);
                vo.setQuantMinRayon(qr);
                vo.setQuantMinStock(qs);
                
                int insert = dao.quantityDao.getInstence().updateQuantityGeneral(vo);
                
                if(insert > 0){
                    Image img = new Image("/img/chek.png");
                         notification = notification.create().title("Insert General Quantity").
                                text("Save Successfully").
                                graphic(new ImageView(img)).
                                hideAfter(Duration.seconds(2)).
                                position(Pos.CENTER).darkStyle();
                        notification.show();
                }else{
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setTitle("ERROR");
                    a.setHeaderText("!!");
                    a.setContentText("ERROR to save data !!");
                    
                    a.showAndWait();
                }
               
            }
            
        }catch(ClassNotFoundException|SQLException e){
            
        }
        
        
        
         Stage stage =null;
            Parent root = null;
            
        
                stage = (Stage) updateBtn.getScene().getWindow();
                root = FXMLLoader.load(getClass().getResource("/javaFXML/homeResStock.fxml"));
                
           
            
                Scene s = new Scene(root);
                stage.setTitle("Stock Responsible");
                stage.setScene(s);
                //s.getStylesheets().add(getClass().getResource("/styleCss/homeAdmin.css").toExternalForm());
                stage.show();
                stage.setResizable(false);
        
        
    }
    
    ObservableList<String> listCategory = FXCollections.observableArrayList();
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        comboProd.setValue(HomeStockPlatformController.isSelected.getProductGQ());
        qMax.setText(String.valueOf(HomeStockPlatformController.isSelected.getQuantMax()));
        qMofR.setText(String.valueOf(HomeStockPlatformController.isSelected.getQuantMinRayon()));
        qMofS.setText(String.valueOf(HomeStockPlatformController.isSelected.getQuantMinStock()));
        
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;
            Connection con = null;

            String sql = "SELECT nom FROM produits";
            con = dao.Dao.getConnection();
            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()) {

                listCategory.add(rs.getString(1));
                comboProd.setItems(listCategory);
     

            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(StatisticPlatformController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
