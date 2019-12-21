
package responsableStock;

import responsableComercial.StatisticPlatformController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.stage.Stage;
import javafx.util.Duration;
import login.UsersLoginController;
import validation.validation;
import vo.prodectVo;
import vo.stockVo;

/**
 * FXML Controller class
 *
 * @author Mido
 */
public class UpdateQuentityController implements Initializable {

    @FXML
    private JFXButton addBtn;
    
    @FXML
    private JFXTextField rq;

    @FXML
    private JFXTextField sq;
    
    @FXML
    private JFXComboBox comboProd;
    
     @FXML
    private JFXDatePicker dateD;

    @FXML
    private JFXDatePicker dateF;
    
    ObservableList<String> listCategory = FXCollections.observableArrayList();
    
    
    @FXML
    private void updateQ() throws IOException{
        
        boolean qSE = validation.isEmpty(sq.getText());
        boolean qRE = validation.isEmpty(rq.getText());
        
        boolean qS = validation.isNumber(sq.getText());
        boolean qR = validation.isNumber(rq.getText());
        
        if(qSE == true){
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("WARNING");
            a.setHeaderText("!!");
            a.setContentText("Stock Quntity is Empty...");
            
            a.showAndWait();
            return;
        }
        if(qS == true){
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("WARNING");
            a.setHeaderText("!!");
            a.setContentText("Enter Number In Stock Quntity  !!");
            
            a.showAndWait();
            return;
        }
        
        if(qRE == true){
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("WARNING");
            a.setHeaderText("!!");
            a.setContentText("Rayon Quntity is Empty...");
            
            a.showAndWait();
            return;
        }
        if(qR == true){
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("WARNING");
            a.setHeaderText("!!");
            a.setContentText("Enter Number In Rayon Quntity  !!");
            
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
                
               int qs = Integer.parseInt(sq.getText());
               int qr = Integer.parseInt(rq.getText());
               
                stockVo vo = new stockVo();
                prodectVo v = new prodectVo();
                vo.setId(HomeStockPlatformController.isSelect1);
                vo.setIdProduct(rs.getInt(1));
                vo.setQuantity(qs);
                vo.setIdUser(UsersLoginController.id);
                
                v.setId(rs.getInt(1));
                v.setQuntInRay(qr);
                
                int insert = dao.quantityDao.getInstence().updateQuantity(vo);
                int insert2 = dao.quantityDao.getInstence().addQuantityRayonInProduct(v);
                if(insert > 0 && insert2 > 0){
                    
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
            
            
                stage = (Stage) addBtn.getScene().getWindow();
                root = FXMLLoader.load(getClass().getResource("/javaFXML/homeResStock.fxml"));
            
                Scene s = new Scene(root);
                stage.setTitle("Stock Responsible");
                stage.setScene(s);
                //s.getStylesheets().add(getClass().getResource("/styleCss/homeAdmin.css").toExternalForm());
                stage.show();
                stage.setResizable(false);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        comboProd.setValue(HomeStockPlatformController.isSelected2.getProdectQ());
        sq.setText(String.valueOf(HomeStockPlatformController.isSelected2.getQuantity()));
        rq.setText(String.valueOf(HomeStockPlatformController.isSelected2.getIdRayon()));
        dateD.setValue(LocalDate.parse(HomeStockPlatformController.isSelected2.getDateD()));
        dateF.setValue(LocalDate.parse(HomeStockPlatformController.isSelected2.getDateF()));
        
        
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
