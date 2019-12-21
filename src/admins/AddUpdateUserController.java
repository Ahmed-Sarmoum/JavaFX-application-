package admins;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.ENTER;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import login.LoginController;
import org.controlsfx.control.Notifications;
import validation.validation;
import vo.userVo;

/**
 * FXML Controller class
 *
 * @author Mido
 */
public class AddUpdateUserController implements Initializable {

    public static Image image1;

    @FXML
    private Label age1;
    @FXML
    private AnchorPane inserPane;
    @FXML
    private JFXTextField firstnameT;

    @FXML
    private JFXTextField lastnameT;

    @FXML
    private JFXTextField usernameT;

    @FXML
    private JFXTextField passwordT;

    @FXML
    private JFXTextField phone;
    @FXML
    private Label typeed;
    @FXML
    private JFXTextField adress;

    @FXML
    private JFXDatePicker age;

    @FXML
    private JFXTextField Image;
    @FXML
    private JFXButton insert;
    private JFXSnackbar toastErrorMsg; // Error Message
    public static File outputFile;

    @FXML
    ImageView imageCan;

    Notifications notification;

    private static int compt = 1;

    @FXML
    private JFXComboBox<String> combo;

    @FXML
    void insert() throws SQLException {

//        System.out.println(imageCan.getImage().toString());
//        boolean isFE = validation.isEmpty(firstnameT.getText());
//        boolean isFT = validation.isText(firstnameT.getText());
//
//        if (isFE == true) {
//            toastErrorMsg.show("firstname Is Empty!!", 1500);
//            return;
//        }
//
//        if (isFT == true) {
//            toastErrorMsg.show("firstname Is not text!!", 1500);
//            return;
//        }
//
//        boolean isLE = validation.isEmpty(lastnameT.getText());
//        boolean isLT = validation.isText(lastnameT.getText());
//
//        if (isLE == true) {
//            toastErrorMsg.show("Lastname Is Empty!!", 1500);
//            return;
//        }
//
//        if (isLT == true) {
//            toastErrorMsg.show("lastname Is not text!!", 1500);
//            return;
//        }
//
//        boolean isUE = validation.isEmpty(usernameT.getText());
//        //boolean isUT = validation.isText(usernameT.getText());
//
//        if (isUE == true) {
//            toastErrorMsg.show("Username Is Empty!!", 1500);
//            return;
//        }
//
//        boolean isPE = validation.isEmpty(passwordT.getText());
//        // boolean isFT = validation.isText(firstnameT.getText());
//
//        if (isPE == true) {
//            toastErrorMsg.show("Password Is Empty!!", 1500);
//            return;
//        }
//        boolean isPHE = validation.isEmpty(phone.getText());
//        // boolean isFT = validation.isText(firstnameT.getText());
//
//        if (isPHE == true) {
//            toastErrorMsg.show("Phone Is Empty!!", 1500);
//            return;
//        }
//        boolean isAE = validation.isEmpty(adress.getText());
//        // boolean isFT = validation.isText(firstnameT.getText());
//
//        if (isAE == true) {
//            toastErrorMsg.show("Adress Is Empty!!", 1500);
//            return;
//        }
//        
//        boolean isAG = validation.isEmpty(age1.getText());
//        // boolean isFT = validation.isText(firstnameT.getText());
//
//        if (isAG == true) {
//            toastErrorMsg.show("Age Is Empty!!", 1500);
//            return;
//        }
//
//        boolean isTE = validation.isEmpty(typeed.getText());
//        // boolean isFT = validation.isText(firstnameT.getText());
//
//        if (isTE == true) {
//            toastErrorMsg.show("Type Is Empty!!", 1500);
//            return;
//        }
//        if (imageCan.getImage() == null) {
//            toastErrorMsg.show("Image Is Empty!!", 1500);
//            return;
//        }
       

        if (!firstnameT.getText().trim().toLowerCase().matches("[a-z]{3,}")) {
            firstnameT.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
            toastErrorMsg.show("First name error !", 1500);
            firstnameT.requestFocus();
            return;
        } else {
            firstnameT.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        }
        if (!lastnameT.getText().trim().toLowerCase().matches("[a-z]{3,}")) {
            lastnameT.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
            toastErrorMsg.show("Last name error !", 1500);
            lastnameT.setFocusTraversable(true);
            return;
        } else {
            lastnameT.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        }
        if (!usernameT.getText().trim().toLowerCase().matches("[a-z0-9_]{4,}")) {
            usernameT.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
            toastErrorMsg.show("Username error !", 1500);
            usernameT.setFocusTraversable(true);
            return;
        } else {
            usernameT.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        }
        if (passwordT.getText().length() < 8) {
            passwordT.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
            toastErrorMsg.show("Password error ! ,\nTapped 8 character ...", 2500);
            return;
        } else {
            passwordT.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

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
             combo.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
            toastErrorMsg.show("Type error!!", 1500);
            return;
        } else {
            combo.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        }
         if (!adress.getText().trim().toLowerCase().matches("[a-z0-9_ ]{4,}")) {
            adress.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
            toastErrorMsg.show("Adress error !", 1500);
            return;
        } else {
            adress.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        }
                 boolean isAG = validation.isEmpty(age1.getText());
        // boolean isFT = validation.isText(firstnameT.getText());

        if (isAG == true) {
            age.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
            toastErrorMsg.show("Age Is Empty!!", 1500);
            return;
        }else {
            age.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        }

        String firstN = firstnameT.getText();
        String lastN = lastnameT.getText();
        String userN = usernameT.getText();
        String pass = passwordT.getText();
        String ph = phone.getText();
        String adr = adress.getText();
        int Age = LocalDate.now().getYear() - age.getValue().getYear();
        String image = phone.getText();
        String list = combo.getSelectionModel().getSelectedItem();

        userVo vo = new userVo();

        vo.setFirstName(firstN);
        vo.setLastName(lastN);
        vo.setUsername(userN);
        vo.setPassword(pass);
        vo.setPhone(ph);
        vo.setAdress(adr);
        vo.setAge(Age);
        vo.setType(list);
        vo.setImage(image);

        System.out.println("1");

        int status = dao.usersDao.getInstance().insert(vo);
        System.out.println("2");

        if (status > 0) {

            Image img = new Image("/img/chek.png");
            notification = notification.create().title("Insert Admin").
                    text("Save Successfully").
                    graphic(new ImageView(img)).
                    hideAfter(Duration.seconds(5)).
                    position(Pos.CENTER).darkStyle();
            notification.show();

            System.out.println("3");

            saveToFile(image1);//ajouter l'image dans le docier "imgUsers"
            System.out.println("4");
            reset();
            System.out.println("5");

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Data insert");
            alert.setHeaderText("ERROR Dialog");
            alert.setContentText("Sorry!unable to save record");

            alert.showAndWait();

        }

        //try {
            System.out.println("5+");
          //  Stage stage = (Stage) insert.getScene().getWindow();
            System.out.println("5++");
           // Parent root = FXMLLoader.load(getClass().getResource("/admins/homeAdmin.fxml"));

            System.out.println("6");

            //Scene s = new Scene(root);

            System.out.println("7");

            //stage.setScene(s);
            System.out.println("8");

            //s.getStylesheets().add(getClass().getResource("/styleCss/chat.css").toExternalForm());
            System.out.println("9");
            //stage.show();
            System.out.println("10");

            //stage.setResizable(false);
            System.out.println("11");
//
//        } catch (IOException ex) {
//            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    void reset() {

        firstnameT.clear();
        lastnameT.clear();
        usernameT.clear();
        passwordT.clear();
        phone.clear();
        adress.clear();
        age.setValue(null);
        combo.setValue("");
        imageCan.setImage(null);
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
            imageCan.setImage(image1);

        } catch (IOException ex) {
            System.out.println("please choose images");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        firstnameT.requestFocus();
        inserPane.setOnKeyPressed(event -> { // Add Key Listener, if i click to the Enter Button Call btnLogin() method
            if (event.getCode().equals(ENTER)) {
                try {
                    insert();
                } catch (SQLException ex) {
                    Logger.getLogger(AddUpdateUserController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        age.setOnAction(event -> {

            age1.setText(age.getValue().toString());

        });
        combo.setOnAction(event -> {

            typeed.setText(combo.getSelectionModel().getSelectedItem());

        });

        String t1 = "Admin";
        String t2 = "Commercial responsible";
        String t3 = "Stock responsible";
        String t5 = "Cashier";
        ObservableList<String> type = FXCollections.observableArrayList();
        type.addAll(t1, t2, t3, t5);
        combo.setItems(type);

        toastErrorMsg = new JFXSnackbar(inserPane);
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
