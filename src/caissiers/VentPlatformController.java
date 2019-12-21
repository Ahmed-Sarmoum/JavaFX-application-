package caissiers;

import login.LoginController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXSnackbar;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.ENTER;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import login.UsersLoginController;
import org.controlsfx.control.Notifications;
import validation.validation;
import vo.listVentVo;

/**
 * FXML Controller class
 *
 * @author Mido
 */
public class VentPlatformController implements Initializable {

    public static String nameCategFact;
    public static float totalFact;
    public static String productVent;
    public static int quantityFact;
    public static String name;

    public static ObservableList<String> namep = FXCollections.observableArrayList();
    public static ObservableList<Float> priceProd = FXCollections.observableArrayList();
    public static ObservableList<Integer> quantProd = FXCollections.observableArrayList();
    public static ObservableList<Float> totalProdVent = FXCollections.observableArrayList();

    @FXML
    private AnchorPane ventPlatform;
    @FXML
    private TextArea listProd;

    @FXML
    private JFXListView<String> listProdect;

    @FXML
    private Text nameCateg;

    @FXML
    private Label quantP;

    @FXML
    private JFXTextField price;

    @FXML
    private JFXTextField quantity;

    @FXML
    private Label total;

    @FXML
    private JFXButton validate;
    private JFXSnackbar toast;

    float prix;
    Notifications n;

    ObservableList<String> list = FXCollections.observableArrayList();
    public static ObservableList<String> listVent = FXCollections.observableArrayList();

    @FXML
    private void toggel() {

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = dao.Dao.getConnection();

            name = listProdect.getSelectionModel().getSelectedItem();

            String sql = "SELECT quntité,prix FROM produits WHERE nom = '"
                    + name + "'";

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                quantity.setText("");

                prix = rs.getFloat(2);

                quantP.setText(String.valueOf(rs.getInt(1)));
                price.setText(String.valueOf(rs.getInt(2)) + "  DA");
            }

        } catch (ClassNotFoundException | SQLException e) {

        }

        if (listProdect.isExpanded()) {
            listProdect.setExpanded(false);
        } else {
            listProdect.setExpanded(true);
            listProdect.depthProperty().set(1);
            // listProdect.getSelectionModel().getSelectedItems();
        }
    }

    @FXML
    private void reset() {

        quantP.setText("");
        quantity.clear();
        total.setText("0.0");
        price.clear();
        listProd.clear();
    }

    @FXML
    private void plus() {

        boolean qE = validation.isEmpty(quantity.getText());
        boolean qN = validation.isNumber(quantity.getText());

        if (qE == true) {

            toast.show("Quantity Field is Empty !!", 3000);
            return;
        }
        if (qN == true) {

            toast.show("Quantity Field is Not Number !!", 3000);
            return;
        }

        float f = Float.parseFloat(total.getText()) + prix * Integer.parseInt(quantity.getText());

        total.setText(String.valueOf(f));

        totalFact = f;
        quantityFact = Integer.parseInt(quantity.getText());

        PreparedStatement psq, ps = null, ps1 = null;
        ResultSet rsq, rs = null;
        Connection con = null;

        try {
            con = dao.Dao.getConnection();
            String sqlQ = "SELECT quntité FROM produits WHERE nom = '" + name + "'";
            psq = con.prepareStatement(sqlQ);
            rsq = psq.executeQuery();

            while (rsq.next()) {

                if (rsq.getInt(1) - Integer.parseInt(quantity.getText()) < 0) {

                    Image img = new Image("/img/remove.png");
                    n = n.create().title("WORNING").
                            text("Sorry,you can't selling " + name + " product!!,\ncaused by : Quantity On Rayon is Few").
                            graphic(new ImageView(img)).
                            hideAfter(Duration.seconds(8)).
                            position(Pos.TOP_CENTER).darkStyle();
                    n.show();
                } else {

                    String sql = "UPDATE produits SET quntité = quntité - "
                            + Integer.parseInt(quantity.getText()) + " WHERE nom = '" + name + "' ";

                    ps = con.prepareCall(sql);
                    ps.executeUpdate();

                    String sql1 = "SELECT id,prixInitial FROM produits WHERE nom = '" + name + "'";

                    ps1 = con.prepareStatement(sql1);
                    rs = ps1.executeQuery();

                    while (rs.next()) {
                        //Calcule Revenue  
                        float t = totalFact - rs.getFloat(2) * Float.parseFloat(quantity.getText());

                        listProd.setText(listProd.getText() + "\n" + name + "         "
                                + quantity.getText() + "          " + prix * Integer.parseInt(quantity.getText()));

                        namep.add(name);
                        quantProd.add(Integer.parseInt(quantity.getText()));
                        priceProd.add(prix * Integer.parseInt(quantity.getText()));
                        System.out.println(t);

                        listVentVo ventVo = new listVentVo();

                        ventVo.setQuantity(Integer.parseInt(quantity.getText()));
                        ventVo.setPrice(totalFact);
                        ventVo.setRevenue(t);
                        ventVo.setIdProduit(rs.getInt(1));
                        ventVo.setIdUser(UsersLoginController.id);

                        int isInsert = dao.listVentDao.getInstance().addVentP(ventVo);

                        if (isInsert > 0) {

                            Image img = new Image("/img/chek.png");
                            notification = notification.create().title("Insert Products list").
                                    text("Save Successfully").
                                    graphic(new ImageView(img)).
                                    hideAfter(Duration.seconds(2)).
                                    position(Pos.TOP_CENTER).darkStyle();
                            notification.show();
                        } else {
                            Alert a = new Alert(Alert.AlertType.ERROR);
                            a.setTitle("ERROR");
                            a.setHeaderText("!!");
                            a.setContentText("ERROR to save data !!");

                            a.showAndWait();
                        }

                    }
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {

            System.out.println(ex.getMessage());
        }
        listVent.add(quantity.getText() + "  " + name);

        quantity.clear();
        price.clear();

//        quantity.setText("");
//        price.setText("");
//        quantP.setText("");
    }

    @FXML
    private void validate() {

        totalProdVent.add(totalFact);

        Stage stage = new Stage();
        Parent root = null;

        try {

            stage = (Stage) validate.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/caissiers/homeCashier.fxml"));

        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Scene s = new Scene(root);
        stage.setTitle("Cashier");
        stage.setScene(s);
        s.getStylesheets().add(getClass().getResource("/styleCss/chat.css").toExternalForm());
        stage.show();
        stage.setResizable(false);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        toast = new JFXSnackbar(ventPlatform);

        ventPlatform.setOnKeyPressed(event -> { // Add Key Listener, if i click to the Enter Button Call Login() method
            if (event.getCode().equals(ENTER)) {

                plus();

            }
        });

        try {
            PreparedStatement ps = null, ps1 = null;
            ResultSet rs = null, rs1 = null;
            Connection con = null;

            nameCategFact = DashboardPlatformController.l;
            String sql = "SELECT id FROM catégories WHERE nom = '" + DashboardPlatformController.l + "'";
            con = dao.Dao.getConnection();

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                String sql1 = "SELECT nom,prix,quntité FROM produits WHERE idCatég  = " + rs.getInt(1);
                ps1 = con.prepareStatement(sql1);

                rs1 = ps1.executeQuery();

                while (rs1.next()) {

                    list.add(rs1.getString(1));

                }
            }
            listProdect.setItems(list);
            nameCateg.setText(DashboardPlatformController.l);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VentPlatformController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
