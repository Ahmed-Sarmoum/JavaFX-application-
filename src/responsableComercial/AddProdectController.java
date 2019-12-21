package responsableComercial;

import allFunction.function;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import org.controlsfx.control.Notifications;
import vo.prodectVo;

/**
 * FXML Controller class
 *
 * @author Mido
 */
public class AddProdectController implements Initializable {

    @FXML
    private JFXComboBox<String> category;

    @FXML
    private JFXComboBox<String> rayon;

    @FXML
    private JFXTextField nameP;

    @FXML
    private JFXTextField priceP;
    
    @FXML
    private JFXTextField pricePInit;

    @FXML
    private JFXTextArea descP;

    Notifications notification;

    ObservableList<String> listCategory = FXCollections.observableArrayList();
    ObservableList<String> listRayon = FXCollections.observableArrayList();

    @FXML
    private void addProdect(ActionEvent e) {
        
        function f = new function();
        
        f.validationProduct(nameP, priceP, pricePInit, descP);//Fuction for Valide all field ....

        try {
            PreparedStatement ps = null, ps1 = null;
            ResultSet rs = null, rs1 = null;
            Connection con = null;

            //pour category ID selon le nom de catégory
            String sql = "SELECT id FROM catégories WHERE nom ='"
                    + category.getSelectionModel().getSelectedItem() + "'";
            con = dao.Dao.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                int catég = rs.getInt(1);//le ID de catégory entré apré le convert 

                //convert le nom à ID de rayon
                String sql1 = "SELECT id FROM rayons WHERE nom = '"
                        + rayon.getSelectionModel().getSelectedItem() + "'";
                ps1 = con.prepareStatement(sql1);
                rs1 = ps1.executeQuery();

                while (rs1.next()) {

                    int rayon = rs1.getInt(1);//le ID de Rayon entré apré le convert 

                    String n = nameP.getText();
                    float p = Float.parseFloat(priceP.getText());
                    String n1 = nameP.getText();
                    float p1 = Float.parseFloat(pricePInit.getText());
                    String des = descP.getText();

                    prodectVo vo = new prodectVo();//new Objet de Produit

                    vo.setNameP(n);
                    vo.setCategoryInt(catég);
                    vo.setDescription(des);
                    vo.setPriceP(p);
                    vo.setPricePInitial(p1);
                    vo.setRayonInt(rayon);
                    //insert les information de produit à la base de donnée
                    int isInsert = dao.prodectDao.getInstence().addProdect(vo);

                    if (isInsert > 0) {
                        // afficher si l'insert success
                        Image img = new Image("/img/chek.png");
                        notification = notification.create().title("Insert New Prodect").
                                text("Save Successfully").
                                graphic(new ImageView(img)).
                                hideAfter(Duration.seconds(2)).
                                position(Pos.CENTER).darkStyle();
                        notification.show();

                        reset();//vider les champ
                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("ERROR");
                        alert.setHeaderText("!!");
                        alert.setContentText("ERROR To Insert ...!!");

                        alert.showAndWait();
                        return;
                    }

                }
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AddProdectController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    

    

    private void reset() {

        nameP.clear();
        priceP.clear();
        pricePInit.clear();
        descP.clear();
        category.setValue("");
        rayon.setValue("");
    }
    
    @FXML
    public void validPrice(){
        
        priceP.textProperty().addListener((observable, oldValue, newValue) -> {
         
            if(!newValue.matches("[0-9]")){
                
                priceP.setStyle("-jfx-focus-color : red ; ");
//            }else{
                priceP.setStyle("-jfx-focus-color : blue ; ");

            }
            
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

       function f = new function(); 
        
        f.getRayonCateg(category, rayon);// function for get all rayon AND all category
    }

}
