package admins;

import static admins.AddUpdateUserController.image1;
import static admins.AddUpdateUserController.outputFile;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import org.controlsfx.control.Notifications;
import validation.validation;
import vo.userVo;

/**
 * FXML Controller class
 *
 * @author Mido
 */
public class UpdatePlatformController implements Initializable {

    public static Image image1;
    
    @FXML
    private StackPane updatePlatform;

    @FXML
    private JFXTextField firstname;

    @FXML
    private Label typeed;
    
    @FXML
    private JFXTextField lastname;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXTextField password;

    @FXML
    private JFXTextField phone;

    @FXML
    private JFXTextField adress;

    @FXML
    private JFXComboBox<String> type;

    @FXML
    private ImageView imageView;
    
    private JFXSnackbar toastErrorMsg; 

    Notifications notification;
    @FXML
    void chooseImg(ActionEvent event) {

    }

    @FXML
    void update(ActionEvent event) throws SQLException {

        if (!firstname.getText().trim().toLowerCase().matches("[a-z]{3,}")) {
            firstname.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
            toastErrorMsg.show("First name error !", 1500);
            firstname.requestFocus();
            return;
        } else {
            firstname.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        }
        if (!lastname.getText().trim().toLowerCase().matches("[a-z]{3,}")) {
            lastname.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
            toastErrorMsg.show("Last name error !", 1500);
            lastname.setFocusTraversable(true);
            return;
        } else {
            lastname.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        }
        if (!username.getText().trim().toLowerCase().matches("[a-z0-9_]{4,}")) {
            username.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
            toastErrorMsg.show("Username error !", 1500);
            username.setFocusTraversable(true);
            return;
        } else {
            username.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        }
        if (password.getText().length() < 8) {
            password.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
            toastErrorMsg.show("Password error ! ,\nTapped 8 character ...", 2500);
            return;
        } else {
            password.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        }
        if (!phone.getText().trim().toLowerCase().matches("[0-9_]{4,}")) {
            phone.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
            toastErrorMsg.show("Phone is not number !", 1500);
            return;
        } else {
            phone.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        }
        
                boolean isTE = validation.isEmpty(typeed.getText());
        // boolean isFT = validation.isText(firstnameT.getText());

        if (isTE == true) {
             type.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
            toastErrorMsg.show("Type error!!", 1500);
            return;
        } else {
            type.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        }
         if (!adress.getText().trim().toLowerCase().matches("[a-z0-9_ ]{4,}")) {
            adress.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
            toastErrorMsg.show("Adress error !", 1500);
            return;
        } else {
            adress.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        }
        type.setOnAction(e -> {

            typeed.setText(type.getSelectionModel().getSelectedItem());

        });
        
        try{
        
            Connection c = dao.Dao.getConnection();
            PreparedStatement p = c.prepareStatement("SELECT id FROM utilisateurs WHERE phone = "+HomeAdminController.compairPhone);
            ResultSet r = p.executeQuery();
            
            if(r.next()){
            
         userVo v = new userVo();
         v.setId(r.getInt(1));
         v.setFirstName(firstname.getText());
         v.setLastName(lastname.getText());
         v.setUsername(username.getText());
         v.setPassword(password.getText());
         v.setPhone(phone.getText());
         v.setAdress(adress.getText());
         v.setType(type.getSelectionModel().getSelectedItem());
         v.setImage(phone.getText());
         
         
         int status = dao.usersDao.getInstance().updateUser(v);

        if (status > 0) {

            Image img = new Image("/img/chek.png");
            notification = notification.create().title("Update users").
                    text("Update Successfully").
                    graphic(new ImageView(img)).
                    hideAfter(Duration.seconds(5)).
                    position(Pos.CENTER).darkStyle();
            notification.show();

            saveToFile(image1);//ajouter l'image dans le docier "imgUsers"


        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Data insert");
            alert.setHeaderText("ERROR Dialog");
            alert.setContentText("Sorry! , unable to save record");

            alert.showAndWait();
        }
        }
        }catch(Exception e){
            
        }
    }
    
     @FXML
    private void LoadPhoto(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG
                = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters()
                .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);

        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);
        System.out.println(file);
        //        Content.getChildren().addAll(btnLoad,imageCan);

        BufferedImage bufferedImage = null;
        try {

            bufferedImage = ImageIO.read(file);
            image1 = SwingFXUtils.toFXImage(bufferedImage, null);
            imageView.setImage(image1);

        } catch (IOException ex) {
            System.out.println("please choose images");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        toastErrorMsg = new JFXSnackbar(updatePlatform);
        
          try {
            PreparedStatement ps;
            Connection con ;
            ResultSet rs ;
            
            con = dao.Dao.getConnection();

            String sql = "SELECT * FROM utilisateurs WHERE phone = "+HomeAdminController.compairPhone;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
           while(rs.next()){
                firstname.setText(rs.getString(2));
                lastname.setText(rs.getString(3));
                username.setText(rs.getString(4));
                //password.setText(hashClass.hashing(rs.getString(5)));
                phone.setText(rs.getString(6));
                adress.setText(rs.getString(7));
                type.setValue(rs.getString(9));
                Image img = new Image("/imgUsers/"+rs.getString(10));
                imageView.setImage(img);
                
                typeed.setText(type.getSelectionModel().getSelectedItem());
            }
          }catch(SQLException | ClassNotFoundException e){
              
          }
        
        String t1 = "Admin";
        String t2 = "Commercial responsible";
        String t3 = "Stock responsible";
        String t5 = "Cashier";
        ObservableList<String> type = FXCollections.observableArrayList();
        type.addAll(t1, t2, t3, t5);
        this.type.setItems(type);
    }    
    
    public void saveToFile(Image image) {
//       outputFile.canWrite();
        outputFile = new File("F:\\Neveau\\projet\\PFEProject\\src\\imgUsers\\" + phone.getText());

        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        try {
            ImageIO.write(bImage, "png", outputFile);
            //ImageIO.read(outputFile);
            //outputFile.canRead();

        } catch (IOException e) {
            System.out.println("passed error !");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        //outputFile.delete();

    }

    
}
