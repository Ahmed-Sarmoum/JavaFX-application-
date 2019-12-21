package responsableStock;

import allFunction.function;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextArea;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import login.LoginController;
import login.UsersLoginController;
import org.controlsfx.control.Notifications;
import validation.validation;
import vo.providerVo;

/**
 * FXML Controller class
 *
 * @author Mido
 */
public class UpdateFournisseurController implements Initializable {

    @FXML
    private AnchorPane updateProvider;
    
    @FXML
    private JFXTextField firstname;

    @FXML
    private JFXTextField lastname;

    @FXML
    private JFXTextField ceidé;
    
    @FXML
    private JFXTextArea note;

    @FXML
    private JFXButton updateBtn;

    @FXML
    private JFXComboBox<String> category;

    @FXML
    private JFXTextField location;

    @FXML
    private JFXTextField phone;

    @FXML
    private JFXTextField email;

    Notifications notification;
    
    JFXSnackbar toast;
    
    @FXML
    void updateBtn(MouseEvent event) throws SQLException {

         function f = new function();

        boolean fnE = validation.isEmpty(firstname.getText());
        boolean lnE = validation.isEmpty(lastname.getText());
        boolean pnE = validation.isEmpty(phone.getText());
        boolean emE = validation.isEmpty(email.getText());
        boolean loE = validation.isEmpty(location.getText());
        boolean crE = validation.isEmpty(ceidé.getText());
//        boolean caE = validation.validation.isEmpty(category.getValue().toString());
        boolean noE = validation.isEmpty(note.getText());
        
        boolean crD = validation.isNumber(ceidé.getText());
        boolean fnT = validation.isText(firstname.getText());
        boolean lnT = validation.isText(lastname.getText());

        //      f.isEmp(caE, "Category", toast);
        
        f.isNumber(crD, "Credit", toast);
        f.isEmp(crE, "Credit", toast);
        f.isEmp(noE, "Note", toast);
        f.isEmp(emE, "E-Mail", toast);
        f.isEmp(loE, "Location", toast);
        f.isEmp(pnE, "N°Phone", toast);
        f.isNotText(lnT, "lastname", toast);
        f.isEmp(lnE, "Lastname", toast);
        f.isNotText(fnT, "Firstname", toast);
        f.isEmp(fnE, "Firstname", toast);

        providerVo v = new providerVo();

        v.setId(FourniseurController.itemSelected.getId());
        if(!firstname.getText().isEmpty())
          //  if(firstname.getText().matches("[a-zA-Z]"))
        v.setFirstname(firstname.getText());
        if(!lastname.getText().isEmpty())
        //    if(lastname.getText().matches("[a-zA-Z]"))
        v.setLastnaame(lastname.getText());
        if(!phone.getText().isEmpty())
        v.setPhone(phone.getText());
        if(!email.getText().isEmpty())
        v.setEmail(email.getText());
        if(!ceidé.getText().isEmpty())
        v.setCridé(Double.parseDouble(ceidé.getText()));
        if(!location.getText().isEmpty())
        v.setLocation(location.getText());
        if(!note.getText().isEmpty())
        v.setNote(note.getText());
        v.setIdUser(UsersLoginController.id);
        try {
            Connection con;
            PreparedStatement ps;
            ResultSet rs;

            con = dao.Dao.getConnection();

            String sql = "SELECT id FROM catégories WHERE nom = '"
                    + category.getSelectionModel().getSelectedItem() + "'";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                v.setIdCategory(rs.getInt(1));

            }

        } catch (SQLException | ClassNotFoundException e) {

        }

        int isUpdate = dao.providerDao.getInstance().updateProvider(v);

        if (isUpdate > 0) {

            Image img = new Image("/img/chek.png");
            notification = notification.create().title("Update Informations ").
                    text("Update Successfully").
                    graphic(new ImageView(img)).
                    hideAfter(Duration.seconds(3)).
                    position(Pos.CENTER).darkStyle();
            notification.show();

            clean(event);
          
        }else{
            System.out.println("ERROR");
        }
    }
    
    @FXML
    void clean(MouseEvent event) {

        firstname.clear();
        lastname.clear();
        phone.clear();
        email.clear();
        note.clear();
        location.clear();
        ceidé.clear();
        category.setValue("");
    }
    ObservableList<String> lisCateg = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        toast = new JFXSnackbar(updateProvider);
        
        
        Connection con;
        PreparedStatement ps1;
        ResultSet rs1;

        try {
            con = dao.Dao.getConnection();
            String sql1 = "SELECT nom FROM catégories";

            ps1 = con.prepareStatement(sql1);
            rs1 = ps1.executeQuery();

            while (rs1.next()) {
                lisCateg.add(rs1.getString(1));
            }
            category.setItems(lisCateg);

        } catch (SQLException | ClassNotFoundException e) {

        }

        
        
        
        firstname.setText(FourniseurController.itemSelected.getFirstname());
        lastname.setText(FourniseurController.itemSelected.getLastnaame());
        phone.setText(FourniseurController.itemSelected.getPhone());
        location.setText(FourniseurController.itemSelected.getLocation());
        category.setValue(FourniseurController.itemSelected.getCategory());
        email.setText(FourniseurController.itemSelected.getEmail());
        note.setText(FourniseurController.itemSelected.getNote());
        ceidé.setText(String.valueOf(FourniseurController.itemSelected.getCridé()));
                
    }    
    
}
