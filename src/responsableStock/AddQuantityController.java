package responsableStock;

import responsableComercial.StatisticPlatformController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
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
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
public class AddQuantityController implements Initializable {

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
    private void addQuantity(){
        
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
                
                vo.setIdProduct(rs.getInt(1));
                vo.setQuantity(qs);
                vo.setIdUser(UsersLoginController.id);
                vo.setDateD(dateD.getValue().toString());
                vo.setDateF(dateF.getValue().toString());
                
                v.setId(rs.getInt(1));
                v.setQuntInRay(qr);
                
                int insert = dao.quantityDao.getInstence().addQuantity(vo);
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
        
    
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PreparedStatement ps ,ps1;
            ResultSet rs ,rs1;
            Connection con = null;
         try {
            con = dao.Dao.getConnection();

            String sql0 = "SELECT id FROM catégories WHERE status = 'Active'";
            ps1 = con.prepareStatement(sql0);
            rs1 = ps1.executeQuery();
            while(rs1.next()){
            String sql = "SELECT nom FROM produits WHERE idCatég = "+rs1.getInt(1);
            
            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()) {

                listCategory.add(rs.getString(1));
                comboProd.setItems(listCategory);
     

            }
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(StatisticPlatformController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
