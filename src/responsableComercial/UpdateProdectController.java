package responsableComercial;

import allFunction.function;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import dao.prodectDao;
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
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import vo.prodectVo;

/**
 * FXML Controller class
 *
 * @author Mido
 */
public class UpdateProdectController implements Initializable {

    int catég;
    int ray;
    
    @FXML
    private JFXComboBox<String> category;

    @FXML
    private JFXComboBox<String> rayon;

    @FXML
    private JFXTextField nameP;

    @FXML
    private JFXTextField priceP;
    
    @FXML
    private JFXTextField pricePI;

    @FXML
    private JFXTextField idP;

    @FXML
    private JFXTextArea descP;

    Notifications notification;

    int idC,idR;
    
    ObservableList<String> listCategory = FXCollections.observableArrayList();
    ObservableList<String> listRayon = FXCollections.observableArrayList();

    @FXML
    private void updateProdects(ActionEvent e) throws SQLException {


            function f = new function();
            
            f.validationForGetData(idP);
            f.validationProduct(nameP, priceP, pricePI, descP);//Fuction for Valide all field ....

        try {
            PreparedStatement ps = null, ps1 = null;
            ResultSet rs = null, rs1 = null;
            Connection con = null;

            con = dao.Dao.getConnection();

            
            //pour category ID selon le nom de catégory
            String sql = "SELECT id FROM catégories WHERE nom ='"
                    + category.getSelectionModel().getSelectedItem() + "'";
            
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {


                  ///convert le nom à ID de rayon
                String sql1 = "SELECT id FROM rayons WHERE nom = '"
                        + rayon.getSelectionModel().getSelectedItem() + "'";
                ps1 = con.prepareStatement(sql1);
                rs1 = ps1.executeQuery();

                while (rs1.next()) {
                    catég = rs.getInt(1);//le ID de catégory entré apré le convert
                    ray = rs1.getInt(1);//le ID de Rayon entré apré le convert 

                    
                    String n = nameP.getText();
float p = Float.parseFloat(priceP.getText());
float p1 = Float.parseFloat(pricePI.getText());
String des = descP.getText();

prodectVo vo = new prodectVo();//new Objet de Produit

vo.setId(Integer.parseInt(idP.getText()));
vo.setNameP(n);
vo.setDescription(des);
vo.setPriceP(p);
vo.setPricePInitial(p1);
vo.setCategoryInt(rs.getInt(1));
vo.setRayonInt(rs1.getInt(1));
        System.out.println(catég+"  "+ray);
//insert les information de produit à la base de donnée
int isUpdate = dao.prodectDao.getInstence().updateProdect(vo);


if (isUpdate > 0) {
    // afficher si l'Update success
    Image img = new Image("/img/chek.png");
    notification = notification.create().title("Update Prodect").
            text("Update Successfully").
            graphic(new ImageView(img)).
            hideAfter(Duration.seconds(2)).
            position(Pos.CENTER).darkStyle();
    notification.show();
    
    reset();//vider les champ
} else {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("ERROR");
    alert.setHeaderText("!!");
    alert.setContentText("ERROR To Update ...!!");
    
    alert.showAndWait();
    return;
}

                } }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AddProdectController.class.getName()).log(Level.SEVERE, null, ex);
       }
        
        


            }
       

    @FXML
    public void getDataProdect() throws SQLException {

        function f = new function();

        f.validationForGetData(idP);// Function for get data from database 

        int nId = Integer.parseInt(idP.getText());

        prodectVo vo = prodectDao.getInstence().getData(nId);

        idC = vo.getCategoryInt();
        idR = vo.getRayonInt();
        
        if (vo == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("!!");
            alert.setContentText("ID Not Existe .. :(");

            alert.showAndWait();

        } else {

            PreparedStatement ps2 = null, ps3 = null;
            ResultSet rs2 = null, rs3 = null;
            Connection con = null;

            try {

                con = dao.Dao.getConnection();

                //for get Category name 
                String sql2 = "SELECT nom FROM catégories WHERE id =" + vo.getCategoryInt();//vo.getCategoryInt() this category ID
                ps2 = con.prepareStatement(sql2);
                rs2 = ps2.executeQuery();

                while (rs2.next()) {

                    //for get Rayon name
                    String sql3 = "SELECT nom FROM rayons WHERE id =" + vo.getRayonInt();//vo.getRayonInt() this Rayon ID
                    ps3 = con.prepareStatement(sql3);
                    rs3 = ps3.executeQuery();

                    while (rs3.next()) {
                        
                        

                        nameP.setText(vo.getNameP());
                        descP.setText(vo.getDescription());
                        String d = String.valueOf(vo.getPriceP());
                        priceP.setText(d);
                        String d1 = String.valueOf(vo.getPricePInitial());
                        pricePI.setText(d1);
                        category.setValue(rs2.getString(1));
                        rayon.setValue(rs3.getString(1));

                    }
                }
            } catch (ClassNotFoundException | SQLException e) {

            }

        }
    }
    private void reset() {

        nameP.clear();
        priceP.clear();
        pricePI.clear();
        descP.clear();
        category.setValue("");
        rayon.setValue("");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        function f = new function();

        f.getRayonCateg(category, rayon);// function for get all rayon AND all category

    }

}
