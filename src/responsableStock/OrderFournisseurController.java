package responsableStock;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.ENTER;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.Duration;
import login.UsersLoginController;
import org.controlsfx.control.Notifications;
import vo.commandeVo;

public class OrderFournisseurController implements Initializable {

    JFXSnackbar toast;

    @FXML
    private StackPane fourniserPlatform;
    
    @FXML
    private AnchorPane commandePlatform;

    @FXML
    private StackPane contactFourniseur;

    @FXML
    private JFXComboBox<String> comboProvider;

    @FXML
    private JFXComboBox<String> comboCategory;

    @FXML
    private JFXComboBox<String> comboProduct;

    @FXML
    private JFXButton plusBtn;

    @FXML
    private JFXTextField quantity;

    @FXML
    private JFXButton addBtn;

    @FXML
    private TextArea listProduct;

    @FXML
    private VBox container;

    @FXML
    private ScrollPane scroll;

    @FXML
    private Text nameProvider;

    Notifications notification;

    ObservableList<String> listProvider = FXCollections.observableArrayList();
    ObservableList<String> listCategory = FXCollections.observableArrayList();
    ObservableList<String> listProd = FXCollections.observableArrayList();

    ObservableList<String> nameFL = FXCollections.observableArrayList();
    ObservableList<Timestamp> date = FXCollections.observableArrayList();
    ObservableList<Integer> idComand = FXCollections.observableArrayList();
    
  
    @FXML
    void addOrderBtn(MouseEvent event) throws SQLException, IOException {

        if (comboProvider.getSelectionModel().isEmpty() || comboCategory.getSelectionModel().isEmpty()
                || comboProduct.getSelectionModel().isEmpty() || listProduct.getText().isEmpty()) {
            toast.show("The Feilds is Empty..!", 3000);
            return;
        } else {

            commandeVo com = new commandeVo();

            com.setIdUser(UsersLoginController.id);
            com.setIdProvider(Integer.parseInt(comboProvider.getSelectionModel().getSelectedItem()));
            com.setListProduct(listProduct.getText());

            int insert = dao.commandeDao.getInstance().insertCommande(com);

            if (insert > 0) {
                Image image = new Image("/img/chek.png");
                notification = notification.create().title("Insert Informations ").
                        text("save Successfully").
                        graphic(new ImageView(image)).
                        hideAfter(Duration.seconds(3)).
                        position(Pos.TOP_CENTER).darkStyle();
                notification.show();
            }

            Line ll = new Line();
            ll.setStartX(0);
            ll.setEndX(320);

            HBox hb = new HBox();
            hb.setPrefWidth(310);
            hb.setPrefHeight(88);
            hb.setSpacing(15);
            hb.getStyleClass().add("color-container");

            Text id = new Text("  id  ");
            id.getStyleClass().add("text-id");

            Line l = new Line();
            l.setStartX(0);
            l.setStartY(0);
            l.setEndX(0);
            l.setEndY(70);

            VBox vb = new VBox();
            vb.setPrefWidth(240);
            vb.setPrefHeight(73);
            vb.setSpacing(8);
            Text name = new Text("To " + nameProvider.getText());
            name.getStyleClass().add("text-name");

            Text date = new Text(String.valueOf(LocalDateTime.now()));
            date.getStyleClass().add("text-date");

            vb.getChildren().addAll(name, date);

            Text click = new Text("Click to show order");
            click.getStyleClass().add("text-click");
            click.setWrappingWidth(35);

            hb.getChildren().addAll(id, vb, click);

            container.getChildren().addAll(hb, ll);

            scroll.vvalueProperty().bind(container.heightProperty());

            listProduct.clear();
            listCategory.clear();
            quantity.clear();
            comboProduct.setDisable(true);
            quantity.setDisable(true);
        }
    }

    @FXML
    void getCatgery(ActionEvent event) {

        listProduct.clear();
        listCategory.clear();
        quantity.clear();
        comboProduct.setDisable(true);
        quantity.setDisable(true);

        Connection con;
        PreparedStatement ps, ps1;
        ResultSet rs, rs1;

        try {
            con = dao.Dao.getConnection();
            String sql = "SELECT idCategory,firstname ,lastname FROM fournisseur WHERE id = " + comboProvider.getSelectionModel().getSelectedItem();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                nameProvider.setText(rs.getString(2) + " " + rs.getString(3));

                String sql1 = "SELECT nom FROM catégories WHERE id = " + rs.getInt(1);
                ps1 = con.prepareStatement(sql1);
                rs1 = ps1.executeQuery();

                while (rs1.next()) {
                    listCategory.add(rs1.getString(1));
                }
                comboCategory.setDisable(false);
                comboCategory.setItems(listCategory);
            }

        } catch (SQLException | ClassNotFoundException e) {

        }
    }

    @FXML
    void getProduct(ActionEvent event) {

        listProd.clear();

        Connection con;
        PreparedStatement ps, ps1;
        ResultSet rs, rs1;

        try {
            con = dao.Dao.getConnection();
            String sql = "SELECT id FROM catégories WHERE nom = '" + comboCategory.getSelectionModel().getSelectedItem() + "'";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                String sql1 = "SELECT nom FROM produits WHERE idCatég = " + rs.getInt(1);
                ps1 = con.prepareStatement(sql1);
                rs1 = ps1.executeQuery();

                while (rs1.next()) {
                    listProd.add(rs1.getString(1));
                }
                comboProduct.setDisable(false);
                quantity.setDisable(false);
                comboProduct.setItems(listProd);
            }

        } catch (SQLException | ClassNotFoundException e) {

        }
    }

    @FXML
    void plusBtn() {

        if (quantity.getText().isEmpty() || comboProduct.getValue().isEmpty()) {

            toast.show("Product OR/AND Quantity is Empty..!", 3000);
            return;
        }
        boolean isNum = validation.validation.isNumber(quantity.getText());

        if (isNum == true) {
            toast.show("Value in Quntitiy is not number ..!!", 3000);
            return;
        }

        listProduct.setText(listProduct.getText() + "- " + comboProduct.getSelectionModel().getSelectedItem()
                + "             " + quantity.getText()+"\n");
        comboProduct.setValue("");
        quantity.clear();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        contactFourniseur.setOnKeyPressed(event -> { // Add Key Listener, if i click to the Enter Button Call Login() method
            if (event.getCode().equals(ENTER)) {
            
                    plusBtn();
            
            }
        });
        
        toast = new JFXSnackbar(contactFourniseur);

        try {

            Connection con;
            PreparedStatement ps;
            ResultSet rs;
            con = dao.Dao.getConnection();
            String sql = "SELECT id FROM fournisseur ";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                listProvider.add(rs.getString(1));
            }
            comboProvider.setItems(listProvider);

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());

        }

        Connection con;
        PreparedStatement ps, ps1, ps2;
        ResultSet rs, rs1, rs2;

        try {
            con = dao.Dao.getConnection();
            String sql = "SELECT id,idFournisseur,date FROM commande";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                String sql1 = "SELECT firstname,lastname FROM fournisseur WHERE id = " + rs.getInt(2);
                ps1 = con.prepareStatement(sql1);
                rs1 = ps1.executeQuery();

                while (rs1.next()) {
                    nameFL.add(rs1.getString(1) + " " + rs1.getString(2));
                    date.add(rs.getTimestamp(3));
                    idComand.add(rs.getInt(1));

                }
            }

            for (int i = 0; i < idComand.size(); i++) {
                Line ll = new Line();
                ll.setStartX(0);
                ll.setEndX(320);

                HBox hb = new HBox();
                hb.setPrefWidth(310);
                hb.setPrefHeight(88);
                hb.setSpacing(20);
                hb.getStyleClass().add("color-container");

                Text id = new Text(" "+String.valueOf(idComand.get(i))+"  ");
                id.getStyleClass().add("text-id");

                Line l = new Line();
                l.setStartX(0);
                l.setStartY(0);
                l.setEndX(0);
                l.setEndY(70);

                VBox vb = new VBox();
                vb.setPrefWidth(240);
                vb.setPrefHeight(73);
                vb.setSpacing(8);
                Text name = new Text("To " + nameFL.get(i));
                name.getStyleClass().add("text-name");

                Text date = new Text(String.valueOf(this.date.get(i)));
                date.getStyleClass().add("text-date");

                vb.getChildren().addAll(name, date);

                Text click = new Text("Click to show order");
                click.getStyleClass().add("text-click");
                click.setWrappingWidth(35);

                hb.getChildren().addAll(id,  vb, click);

                container.getChildren().addAll(hb, ll);

                scroll.vvalueProperty().bind(container.heightProperty());

                hb.setOnMouseClicked((event) -> {

                    PreparedStatement ps0,ps00,ps000;
                    ResultSet rs0,rs00,rs000;
                
                    try {
                    
                    String sql0 = "SELECT idFournisseur,listProduct,date,id FROM commande WHERE id ="+id.getText();
                    ps0 = con.prepareStatement(sql0);
                    rs0 = ps0.executeQuery();
                    
                    while(rs0.next()){
                        String sql00 = "SELECT firstname,lastname,idCategory,cridé FROM fournisseur WHERE id = "+rs0.getInt(1);
                        ps00 = con.prepareStatement(sql00);
                        rs00 = ps00.executeQuery();
                        
                        while(rs00.next()){
                            String sql000 = "SELECT nom FROM catégories WHERE id = "+rs00.getInt(3);
                            ps000 = con.prepareStatement(sql000);
                            rs000 = ps000.executeQuery();
                            
                            while(rs000.next()){
                                
                                CommandeController.Categ = rs000.getString(1);
                                CommandeController.creditCom = rs00.getDouble(4);
                                CommandeController.dat = rs0.getDate(3);
                                CommandeController.tim = rs0.getTime(3);
                                CommandeController.firstN = rs00.getString(1);
                                CommandeController.lastN = rs00.getString(2);
                                CommandeController.idCom = rs0.getInt(4);
                                CommandeController.listP = rs0.getString(2);
                            }
                        }
                    
                    }
                    
                    
                    
                        
                        commandePlatform = FXMLLoader.load(getClass().getResource("/responsableStock/commande.fxml"));
                    
                    } catch (SQLException|IOException ex) {
                        Logger.getLogger(OrderFournisseurController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    new JFXDialog(contactFourniseur, commandePlatform, JFXDialog.DialogTransition.BOTTOM).show();

                });

            }
        } catch (SQLException | ClassNotFoundException e) {

        }
    }

}
