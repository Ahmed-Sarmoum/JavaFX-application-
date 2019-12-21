package admins;

import allFunction.function;
import auther.ShowImageController;
import static caissiers.MessagePlatformController.dialog;
import static caissiers.MessagePlatformController.getId;
import static caissiers.MessagePlatformController.urlImg;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import login.LoginController;
import login.UsersLoginController;
import org.controlsfx.control.Notifications;
import static responsableStock.FourniseurController.itemSelected;
import vo.chatVo;
import vo.listVentVo;
import vo.providerVo;
import vo.userVo;

public class HomeAdminController implements Initializable {

    int somme;
    CategoryAxis x;
    NumberAxis y;
    public static String compairPhone ;
    public static String compairimage ;
    private static String dialog1;

    @FXML
    private HBox profile;
    @FXML
    private VBox containner;

    @FXML
    private Label typeU;

    @FXML
    private AnchorPane showImage;

    @FXML
    private TableView<userVo> tableV;

    @FXML
    private ScrollPane scroll;

    @FXML
    private Circle imgUser4;

    @FXML
    private Text flName4;
    @FXML
    private Circle imgUser5;

    @FXML
    private Text flName5;

    @FXML
    private Circle imgUser6;

    @FXML
    private Text flName6;

    @FXML
    private TableView<listVentVo> tabView;

    @FXML
    private TableColumn<listVentVo, String> product;

    @FXML
    private TableColumn<listVentVo, Integer> quantity;

    @FXML
    private TableColumn<listVentVo, Float> price;

    @FXML
    private TableColumn<listVentVo, Float> revenue1;

    @FXML
    private TableColumn<listVentVo, Date> dateS;

    @FXML
    private TableColumn<listVentVo, String> cashier;

    @FXML
    private VBox adminLoginLikeCashier;
    @FXML
    private JFXTextField search1;

    @FXML
    private JFXComboBox<String> catégCombo;

    @FXML
    private JFXTextField search;
    @FXML
    private AnchorPane deleteUserPlatform;
    @FXML
    private HBox boxLogOut;

    @FXML
    private LineChart lineChart;

    @FXML
    private Label balance;

    @FXML
    private Label revenue;

    @FXML
    private Label credit;

    @FXML
    private Label pKind;

    @FXML
    private Circle imgUser;

    @FXML
    private Text flName;
    @FXML
    private Circle imgUser1;

    @FXML
    private Text flName1;
    @FXML
    private Circle imgUser2;
    @FXML
    private Rectangle imgFourniseur;
    @FXML
    private Text flName2;

    @FXML
    private StackPane tabpan;
    @FXML
    private AnchorPane helpPage;

    @FXML
    private AnchorPane inserPane;

    @FXML
    private AnchorPane showFacture;

    @FXML
    private StackPane showOrders;

    @FXML
    private JFXButton ref;

    @FXML
    private HBox profilPlatform;

    @FXML
    private JFXListView<HBox> listProfil;

    Notifications notification;

    ObservableList<userVo> data = FXCollections.observableArrayList();
    ObservableList<listVentVo> data1 = FXCollections.observableArrayList();

    @FXML
    private JFXTextArea msgEntred;

    @FXML
    private VBox messageBox;

    @FXML
    private ScrollPane scrollPaneMessageBox;
    @FXML
    private VBox container;
    @FXML
    private Text SendedName;

    @FXML
    private Label nameProvider;

    @FXML
    private Label email;

    @FXML
    private Label cridé;

    @FXML
    private Label phone;

    @FXML
    private Label nameCategory;

    @FXML
    private Label location;

    @FXML
    private TableView<providerVo> tabViewPro;

    @FXML
    private TableColumn<providerVo, Integer> idPro;

    @FXML
    private TableColumn<providerVo, String> firstnameT;

    @FXML
    private TableColumn<providerVo, String> lastnameT;

    @FXML
    private TableColumn<providerVo, String> categoryT;

    @FXML
    private TableColumn<providerVo, String> locationT;

    @FXML
    private TableColumn<providerVo, String> noteT;

    @FXML
    private TableColumn<providerVo, String> phoneT;

    @FXML
    private TableColumn<providerVo, String> emailT;

    @FXML
    private TableColumn<providerVo, Double> cridéT;

    @FXML
    private JFXTextField searchMsg;
    
    @FXML
    private JFXTextField searchProfile;

    @FXML
    private JFXTextField searchProv;

    ObservableList<providerVo> list = FXCollections.observableArrayList();

    @FXML //obtenir tout les infermations de fournisseur choisier dans la table
    private void getDataFromTable() {

        itemSelected = tabViewPro.getSelectionModel().getSelectedItem();

        nameCategory.setText(itemSelected.getCategory());
        location.setText(itemSelected.getLocation());
        nameProvider.setText(itemSelected.getFirstname() + " " + itemSelected.getLastnaame());
        phone.setText(itemSelected.getPhone());
        email.setText(itemSelected.getEmail());
        cridé.setText(String.valueOf(itemSelected.getCridé()));

    }

    @FXML //recherch dans le box d'utilisateurs de Message selon les alphabets entrer  
    private void searchMsg(KeyEvent event) {

        container.getChildren().clear();

        ObservableList<String> Fnames = FXCollections.observableArrayList();
        ObservableList<String> Lnames = FXCollections.observableArrayList();
        ObservableList<String> images = FXCollections.observableArrayList();
        ObservableList<Integer> etats = FXCollections.observableArrayList();
        ObservableList<Integer> count = FXCollections.observableArrayList();
        ObservableList<String> typeUs = FXCollections.observableArrayList();

        Connection co;
        PreparedStatement ps;
        ResultSet r;

        try {
            co = dao.Dao.getConnection();

            String sql = "SELECT firstname,lastname,etat,image,id,type FROM utilisateurs WHERE lastname LIKE '" + searchMsg.getText() + "%' OR firstname LIKE '" + searchMsg.getText() + "%'";
            ps = co.prepareStatement(sql);
            r = ps.executeQuery();

            while (r.next()) {
                typeUs.add(r.getString(6));
                count.add(r.getInt(5));
                Lnames.add(r.getString(1));
                Fnames.add(r.getString(2));
                etats.add(r.getInt(3));
                images.add(r.getString(4));

            }
            // System.out.println(count);

            for (int i = 0; i < count.size(); i++) {

                //System.out.println(Lnames.get(i));
                HBox hb = new HBox();
                hb.setPrefWidth(375);
                hb.setPrefHeight(75);
                hb.setBackground(new Background(new BackgroundFill(Color.valueOf("#242A31"), CornerRadii.EMPTY, Insets.EMPTY)));
                hb.getStyleClass().addAll("user-box");
                hb.setPadding(new Insets(10, 10, 0, 15));
                hb.setSpacing(10);

                Label id = new Label();
                id.setText(String.valueOf(count.get(i)));
                id.setVisible(false);

                Image imag = new Image("/imgUsers/" + images.get(i));
                Circle view = new Circle();
                view.setRadius(27);
                view.setFill(new ImagePattern(imag));

                VBox vb1 = new VBox();
                HBox hbvb = new HBox();
                Label l = new Label(Fnames.get(i) + " " + Lnames.get(i));
                l.getStyleClass().addAll("user-name");
                Label l1 = new Label(typeUs.get(i));
                l1.getStyleClass().addAll("user-name1");
                Circle circle = new Circle();
                Label msg;
                if (etats.get(i) == 1) {
                    circle.setRadius(5);
                    circle.setFill(new Color(0, 1, 0, 1));
                    msg = new Label("Connected");
                } else {
                    circle.setRadius(5);
                    circle.setFill(new Color(0, 1, 0, 0));
                    msg = new Label("Desconnected");
                }
                hbvb.getChildren().addAll(l, id);

                msg.getStyleClass().addAll("user-name");

                vb1.getChildren().addAll(hbvb, msg);

                VBox vb2 = new VBox();
                FontAwesomeIconView awesome = new FontAwesomeIconView();
                awesome.setGlyphName("ELLIPSIS_H");
                awesome.setSize("23");
                awesome.setFill(Color.DARKGRAY);

                vb2.getChildren().add(awesome);
//        
                hb.getChildren().addAll(circle, view, vb1, vb2);

                container.getChildren().add(hb);

                hb.setOnMouseClicked((MouseEvent e) -> {
                    getId = Integer.parseInt(id.getText());
                    SendedName.setText(l.getText());
                    typeU.setText(l1.getText());
                    messageBox.getChildren().clear();

                    Connection c;
                    PreparedStatement p1, p2;
                    ResultSet r1, r2;

                    try {
                        c = dao.Dao.getConnection();
                        //For get dialog from database 
                        String sql1 = "SELECT dialog,idExpéditeur FROM chats WHERE idExpéditeur = "
                                + UsersLoginController.id + " AND idDestinatair = " + id.getText() + " OR idExpéditeur = "
                                + id.getText() + " AND idDestinatair = " + UsersLoginController.id;
                        p1 = c.prepareStatement(sql1);
                        r1 = p1.executeQuery();
                        while (r1.next()) {

                            dialog = r1.getString(1);

                            //لجلب صورة المرسل
                            String sqlIm = "SELECT image FROM utilisateurs WHERE id = " + id.getText();
                            p2 = c.prepareStatement(sqlIm);
                            r2 = p2.executeQuery();

                            while (r2.next()) {

                                dialog1 = r1.getString(1).toLowerCase();

                                //كود لجلب المحادثات المستلمة
                                if (r1.getInt(2) == Integer.parseInt(id.getText())) {

                                    HBox hBo = new HBox();

                                    hBo.setPrefWidth(809);
                                    hBo.setMinHeight(hBo.USE_COMPUTED_SIZE);
                                    hBo.setAlignment(Pos.CENTER_LEFT);
                                    hBo.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
                                    hBo.setPadding(new Insets(0, 0, 0, 10));

                                    Image iii = new Image("/imgUsers/" + r2.getString(1));

                                    Circle imageSend = new Circle();
                                    imageSend.setRadius(23);
                                    imageSend.setFill(new ImagePattern(iii));

                                    TextArea content = new TextArea();
                                    content.getStyleClass().addAll("send-msg-area");
                                    content.setWrapText(true);

                                    content.setMaxSize(440, 55);
                                    content.setMinSize(89, 55);
                                    content.setPrefSize(r1.getString(1).length() * 12, r1.getString(1).length() + 30);
                                    content.setMinHeight(content.USE_PREF_SIZE);
                                    content.setEditable(false);

                                    //المحادثة القادمة يتم التاكد ان كانت صورة يتم تحويلها من نص الى شكل صورة وعرضها
                                    if (r1.getString(1).matches(".*.jpg") || dialog1.matches(".*.png")
                                            || dialog1.matches(".*.png") || dialog1.matches(".*.gif")
                                            || dialog1.matches(".*.jpeg")) {
                                        ImageView imageSendedd = new ImageView(r1.getString(1));
                                        imageSendedd.setFitWidth(100);
                                        imageSendedd.setFitHeight(100);

                                        hBo.getChildren().addAll(imageSend, imageSendedd);

                                        //لعرض المحتوى ان كان صورة
                                        imageSendedd.setOnMouseClicked((eve) -> {

                                            try {
//                                       
                                                ShowImageController.urlImage = imageSendedd.getImage();
                                                showImage = FXMLLoader.load(getClass().getResource("/auther/showImage.fxml"));
                                                new JFXDialog(tabpan, showImage, JFXDialog.DialogTransition.CENTER).show();

                                            } catch (IOException ex) {
                                                Logger.getLogger(caissiers.MessagePlatformController.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        });
                                    } else {
                                        //في حالة القادم نص وليس صورة
                                        content.setText(r1.getString(1));

                                        hBo.getChildren().addAll(imageSend, content);
                                    }

                                    hBo.setSpacing(19);
                                    messageBox.getChildren().add(hBo);

                                    scrollPaneMessageBox.vvalueProperty().bind(messageBox.heightProperty());

                                } else {
                                    // كود بالنسبة للمحادثات المرسلة
                                    HBox hBox = new HBox();

                                    hBox.setPrefWidth(809);
                                    hBox.setMinHeight(hBox.USE_COMPUTED_SIZE);
                                    hBox.setAlignment(Pos.CENTER_LEFT);
                                    hBox.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                                    hBox.setPadding(new Insets(0, 0, 0, 10));

                                    Image ii = new Image("/imgUsers/" + UsersLoginController.img);

                                    Circle imageSended = new Circle();
                                    imageSended.setRadius(23);
                                    imageSended.setFill(new ImagePattern(ii));

                                    TextArea contentArea = new TextArea();
                                    contentArea.getStyleClass().addAll("msg-src", "msg-area");
                                    contentArea.setWrapText(true);

                                    contentArea.setMaxSize(440, 55);
                                    contentArea.setMinSize(89, 55);
                                    contentArea.setPrefSize(r1.getString(1).length() * 12, r1.getString(1).length() + 30);
                                    contentArea.setMinHeight(contentArea.USE_PREF_SIZE);
                                    contentArea.setEditable(false);
                                    if (dialog1.matches(".*.jpg") || dialog1.matches(".*.png")
                                            || dialog1.matches(".*.png") || dialog1.matches(".*.gif")
                                            || dialog1.matches(".*.jpeg")) {
                                        ImageView imageSendedd = new ImageView(r1.getString(1));
                                        imageSendedd.setFitWidth(100);
                                        imageSendedd.setFitHeight(100);

                                        hBox.getChildren().addAll(imageSended, imageSendedd);

                                        //لعرض المحتوى ان كان صورة
                                        imageSendedd.setOnMouseClicked((ev) -> {

                                            try {
//                                           

                                                ShowImageController.urlImage = imageSendedd.getImage();
                                                showImage = FXMLLoader.load(getClass().getResource("/auther/showImage.fxml"));
                                                new JFXDialog(tabpan, showImage, JFXDialog.DialogTransition.CENTER).show();

                                            } catch (IOException ex) {
                                                Logger.getLogger(caissiers.MessagePlatformController.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        });

                                    } else {
                                        contentArea.setText(r1.getString(1));

                                        hBox.getChildren().addAll(imageSended, contentArea);
                                    }
                                    hBox.setSpacing(19);
                                    messageBox.getChildren().add(hBox);
                                    scrollPaneMessageBox.vvalueProperty().bind(messageBox.heightProperty());

                                }
                            }
                        }

                    } catch (SQLException | ClassNotFoundException ex) {

                    }
                });
            }

        } catch (SQLException | ClassNotFoundException z) {

        }

    }
    
    @FXML //recherch dans la table de fournisseur
    private void search(MouseEvent event) {

        searchProv.textProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {

                if (searchProv.textProperty().get().isEmpty()) {
                    tabViewPro.setItems(list);
                    return;
                }
                ObservableList<providerVo> items = FXCollections.observableArrayList();
                ObservableList<TableColumn<providerVo, ?>> column = tabViewPro.getColumns();

                for (int row = 0; row < list.size(); row++) {
                    for (int col = 0; col < column.size(); col++) {
                        TableColumn tabVar = column.get(col);
                        String cellData = tabVar.getCellData(list.get(row)).toString();
                        cellData = cellData.toLowerCase();

                        if (cellData.contains(searchProv.getText().toLowerCase()) && cellData.startsWith(searchProv.getText().toLowerCase())) {
                            items.add(list.get(row));
                            break;
                        }
                    }

                }
                tabViewPro.setItems(items);
            }
        });

    }

    public int count() {

        int count = 0;

        if (msgEntred.getText().length() == 40 || msgEntred.getText().length() > 40) {
            count = msgEntred.getText().length() + 30;
        }
        return count;
    }

    @FXML //envoyee un message 
    void sendMessage() throws SQLException {

        if (SendedName.getText().equals("")) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("ERROR");
            a.setHeaderText("!!");
            a.setContentText("ERROR, Choose receiver first..! ");

            a.showAndWait();

        } else {

            if (msgEntred.getText().matches("[ ]*")) {
                return;
            }

            String msg = msgEntred.getText();

            chatVo v = new chatVo();

            v.setIdE(UsersLoginController.id);
            v.setIdD(getId);
            v.setDialog(msg);

            int isInsert = dao.chatDao.getInstace().sendMsd(v);

            if (isInsert > 0) {
                Image img = new Image("/img/msg.png");
                notification = notification.create().title("Send Message To " + SendedName.getText()).
                        text("Send Successfully").
                        graphic(new ImageView(img)).
                        hideAfter(Duration.seconds(3)).
                        position(Pos.TOP_RIGHT).darkStyle();
                notification.show();
            }

            HBox hBox = new HBox();
            hBox.setPrefWidth(809);
            hBox.setMinHeight(hBox.USE_COMPUTED_SIZE);
            hBox.setAlignment(Pos.CENTER_LEFT);
            hBox.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            hBox.setPadding(new Insets(0, 0, 0, 10));

            Image i = new Image("/imgUsers/" + UsersLoginController.img);

            Circle imageSended = new Circle();
            imageSended.setRadius(23);
            imageSended.setFill(new ImagePattern(i));

            TextArea contentArea = new TextArea();
            contentArea.getStyleClass().addAll("msg-src", "msg-area");
            contentArea.setWrapText(true);

            contentArea.setMaxSize(440, 55);
            contentArea.setMinSize(89, 55);
            contentArea.setPrefSize(msgEntred.getText().length() * 12, count());
            contentArea.setMinHeight(contentArea.USE_PREF_SIZE);
            contentArea.setEditable(false);

            contentArea.setText(msgEntred.getText());

            hBox.getChildren().addAll(imageSended, contentArea);
            hBox.setSpacing(19);
            messageBox.getChildren().add(hBox);

            // clear Inbox Typed by the user
            msgEntred.setText("");
            // Move Scroll to the bottom
            scrollPaneMessageBox.vvalueProperty().bind(messageBox.heightProperty());
        }
    }

    @FXML // pour choisier et envoyee un image
    public void imageChooser() throws SQLException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Images (*.png, *.jpg, *.jpeg, *.gif)", "*.png", "*.jpg", "*.jpeg", "*.gif");
        fileChooser.getExtensionFilters().add(extFilter);
        File f = fileChooser.showOpenDialog(allFunction.function.root.getScene().getWindow());

        if (f == null) {
            return;
        }
        sendImage(f.toURI().toString());
        urlImg = f.toURI().toString();
        
        // Move to the bottom of the box (change to max value => ScrollPane)
        scrollPaneMessageBox.vvalueProperty().bind(messageBox.heightProperty());
        chatVo v = new chatVo();

        v.setIdE(UsersLoginController.id);
        v.setIdD(getId);
        v.setDialog(f.toURI().toString());

        int isInsert = dao.chatDao.getInstace().sendMsd(v);

        if (isInsert > 0) {
            Image img = new Image("/img/msg.png");
            notification = notification.create().title("Send Message To " + SendedName.getText()).
                    text("Send Successfully").
                    graphic(new ImageView(img)).
                    hideAfter(Duration.seconds(3)).
                    position(Pos.TOP_RIGHT).darkStyle();
            notification.show();
        }
        System.out.println("Reading complete.");

    }

    @FXML
    public void fileChooser() { //pour choser un file
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Files (*.*)", "*.*");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(allFunction.function.root.getScene().getWindow());
        System.out.println(file);
        
    }

    // Methode utiliser pour envoyee les images dans les messages en cas le URL String
    public void sendImage(String imageSelectedUrl) {
        HBox hBox = new HBox();
        hBox.setPrefSize(809, 79);
        hBox.setMinHeight(79);
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        hBox.setPadding(new Insets(0, 0, 0, 10));

        Image i = new Image("/imgUsers/" + UsersLoginController.img);

        Circle iconUser = new Circle();
        iconUser.setRadius(23);
        iconUser.setFill(new ImagePattern(i));

        ImageView imageSended = new ImageView(imageSelectedUrl);
        imageSended.setFitWidth(100);
        imageSended.setFitHeight(100);

        hBox.getChildren().addAll(iconUser, imageSended);
        hBox.setSpacing(19);
        messageBox.getChildren().add(hBox);
    }

    // Methode utiliser pour envoyee les images dans les messages
    public void sendImage(ImageView imageViewSelected) {
        HBox hBox = new HBox();
        hBox.setPrefSize(809, 79);
        hBox.setMinHeight(79);
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        hBox.setPadding(new Insets(0, 0, 0, 10));

        Image i = new Image("/imgUsers/" + UsersLoginController.img);

        Circle iconUser = new Circle();
        iconUser.setRadius(23);
        iconUser.setFill(new ImagePattern(i));

        ImageView imageSended = new ImageView(imageViewSelected.getImage());
        imageSended.setFitWidth(100);
        imageSended.setFitHeight(100);

        hBox.getChildren().addAll(iconUser, imageSended);
        hBox.setSpacing(19);
        messageBox.getChildren().add(hBox);
    }

    @FXML
    void login(ActionEvent event) throws IOException {
        LoginController.typeTxt = "Cashier";
        adminLoginLikeCashier = FXMLLoader.load(getClass().getResource("/login/adminLoginLikeCashier.fxml"));

        new JFXDialog(tabpan, adminLoginLikeCashier, JFXDialog.DialogTransition.CENTER).show();

    }

    @FXML
    void showOrders(ActionEvent event) throws IOException {
        showOrders = FXMLLoader.load(getClass().getResource("/admins/showOrders.fxml"));

        new JFXDialog(tabpan, showOrders, JFXDialog.DialogTransition.CENTER).show();

    }

    @FXML
    void showFacturs(ActionEvent event) throws IOException {
        showFacture = FXMLLoader.load(getClass().getResource("/admins/showFacture.fxml"));

        new JFXDialog(tabpan, showFacture, JFXDialog.DialogTransition.CENTER).show();

    }

    @FXML
    void search1() { // recherch dans la table des liste des vents

        search1.textProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if (search1.textProperty().get().isEmpty()) {
                    tabView.setItems(data1);
                    return;
                }
                ObservableList<listVentVo> items = FXCollections.observableArrayList();
                ObservableList<TableColumn<listVentVo, ?>> column = tabView.getColumns();

                for (int row = 0; row < data1.size(); row++) {
                    for (int col = 0; col < column.size(); col++) {
                        TableColumn tabVar = column.get(col);
                        String cellData = tabVar.getCellData(data1.get(row)).toString();
                        cellData = cellData.toLowerCase();

                        if (cellData.contains(search1.getText().toLowerCase()) && cellData.startsWith(search1.getText().toLowerCase())) {
                            items.add(data1.get(row));
                            break;
                        }
                    }

                }
                tabView.setItems(items);
            }
        });
    }
    @FXML
    private void logOut() throws SQLException {

        function f = new function();
        f.exit();
    }

    @FXML
    void help(ActionEvent event) throws IOException {

        helpPage = FXMLLoader.load(getClass().getResource("/admins/help.fxml"));

        new JFXDialog(tabpan, helpPage, JFXDialog.DialogTransition.BOTTOM).show();

    }

    @FXML //obtenir l'interface de l'insert des utilisateurs 
    void insert(ActionEvent event) throws SQLException, IOException {

        inserPane = FXMLLoader.load(getClass().getResource("/admins/addUpdateUser.fxml"));

        new JFXDialog(tabpan, inserPane, JFXDialog.DialogTransition.BOTTOM).show();

    }

    ObservableList<String> listCategory = FXCollections.observableArrayList();

    @FXML
    private void lineChart(ActionEvent e) {
        try {
            PreparedStatement ps = null, ps1 = null, ps2 = null, ps3 = null;
            ResultSet rs = null, rs1 = null, rs2 = null, rs3 = null;
            Connection con = null;

            String sql = "SELECT id FROM catégories WHERE nom = '"
                    + catégCombo.getSelectionModel().getSelectedItem() + "'";
            con = dao.Dao.getConnection();

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                String sql1 = "SELECT id FROM produits WHERE idCatég = " + rs.getInt(1);
                con = dao.Dao.getConnection();

                ps1 = con.prepareStatement(sql1);
                rs1 = ps1.executeQuery();

                while (rs1.next()) {

                    String sql2 = "SELECT SUM(revenue) FROM listVente WHERE idProd = " + rs1.getInt(1);
                    con = dao.Dao.getConnection();

                    ps2 = con.prepareStatement(sql2);
                    rs2 = ps2.executeQuery();

                    while (rs2.next()) {

                        String sql3 = "SELECT date FROM listVente";
                        con = dao.Dao.getConnection();

                        ps3 = con.prepareStatement(sql3);
                        rs3 = ps3.executeQuery();

                        while (rs3.next()) {

//                            lineChart.setTitle("Category Statistic");
//
//                            XYChart.Series p = new XYChart.Series();
//
//                            p.setName("");
//
//                            p.getData().add(new XYChart.Data(rs3.getDate(1).toString(), rs2.getFloat(1)));
//
//                            lineChart.getData().add(p);
                        }
                    }
                }
            }

        } catch (ClassNotFoundException | SQLException ex) {

        }

    }

    @FXML
    void refresh(ActionEvent e) {
containner.getChildren().clear();

nameFL.clear();
        Lname.clear();
        imagess.clear();
        phones.clear();
        age.clear();
        adress.clear();
        types.clear();
        
        nameFL1.clear();
        imageFL1.clear();
        phoneFL1.clear();
        ageFL1.clear();
        adressFL1.clear();
        typeFL1.clear();
        
        nameFL2.clear();
        imageFL2.clear();
        phoneFL2.clear();
        ageFL2.clear();
        adressFL2.clear();
        typeFL2.clear();
        
        PreparedStatement pp;
        ResultSet rr;
        Connection cc;

        try {
            cc = dao.Dao.getConnection();
            String req = "SELECT firstname,lastname,phone,adress,image,type,age FROM utilisateurs";
            pp = cc.prepareStatement(req);
            rr = pp.executeQuery();

            while (rr.next()) {
                //remplir les information des utilisateurs pour afficher les profile
                nameFL.add(rr.getString(1));
                Lname.add(rr.getString(2));
                imagess.add(rr.getString(5));
                phones.add(rr.getString(3));
                adress.add(rr.getString(4));
                types.add(rr.getString(6));
                age.add(rr.getInt(7));

            }

            if ((nameFL.size() % 2) == 0) {
                for (int i = 0; i < nameFL.size() / 2; i++) {

                    nameFL1.add(nameFL.get(i));
                    imageFL1.add(imagess.get(i));
                    lnameFL1.add(Lname.get(i));
                    phoneFL1.add(phones.get(i));
                    adressFL1.add(adress.get(i));
                    ageFL1.add(age.get(i));
                    typeFL1.add(types.get(i));

                }
                for (int i = nameFL.size() / 2; i < nameFL.size(); i++) {

                    nameFL2.add(nameFL.get(i));
                    imageFL2.add(imagess.get(i));
                    lnameFL2.add(Lname.get(i));
                    phoneFL2.add(phones.get(i));
                    adressFL2.add(adress.get(i));
                    ageFL2.add(age.get(i));
                    typeFL2.add(types.get(i));

                }
            } else {
                for (int i = 0; i < (nameFL.size() - 1) / 2; i++) {

                    nameFL1.add(nameFL.get(i));
                    imageFL1.add(imagess.get(i));
                    lnameFL1.add(Lname.get(i));
                    phoneFL1.add(phones.get(i));
                    adressFL1.add(adress.get(i));
                    ageFL1.add(age.get(i));
                    typeFL1.add(types.get(i));

                }
                for (int i = (nameFL.size() - 1) / 2; i < nameFL.size(); i++) {

                    nameFL2.add(nameFL.get(i));
                    imageFL2.add(imagess.get(i));
                    lnameFL2.add(Lname.get(i));
                    phoneFL2.add(phones.get(i));
                    adressFL2.add(adress.get(i));
                    ageFL2.add(age.get(i));
                    typeFL2.add(types.get(i));

                }

            }

            System.out.println("nameFL1 = " + nameFL1.size() + "\nnameFL2 = " + nameFL2.size());

            HBox hb = null, hb1 = null;
            if (nameFL.size() % 2 == 0) {

                for (int i = 0; i < nameFL2.size() ; i++) {

                    hb = new HBox();
                    hb.setPrefHeight(280);
                    hb.setPrefWidth(395);
                    hb.setBackground(new Background(new BackgroundFill(Color.valueOf("#fff"), CornerRadii.EMPTY, Insets.EMPTY)));
                    hb.getStyleClass().addAll("user-box1");
                    hb.setSpacing(10);
                    Text img = new Text(imageFL1.get(i));
                    Image image = new Image("/img/bgnn");
                    Circle view = new Circle();
                    view.setRadius(50);
                    view.setFill(new ImagePattern(image));

                    VBox vb = new VBox();
                    vb.setPrefWidth(220);
                    vb.setPrefHeight(280);
                    vb.setBackground(new Background(new BackgroundFill(Color.valueOf("#fff"), CornerRadii.EMPTY, Insets.EMPTY)));

                    HBox bh = new HBox();

                    Text fName = new Text("Firstname : ");
                    Text nameF = new Text(nameFL1.get(i));
                    bh.getChildren().addAll(fName, nameF);

                    HBox bh1 = new HBox();

                    Text Lname = new Text("lastname : ");
                    Text nameLas = new Text(lnameFL1.get(i));
                    bh1.getChildren().addAll(Lname, nameLas);

                    HBox bh2 = new HBox();

                    Text type = new Text("Type : ");
                    Text typeN = new Text(typeFL1.get(i));
                    bh2.getChildren().addAll(type, typeN);

                    HBox bh3 = new HBox();

                    Text phone = new Text("Phone : ");
                    Text phoneN = new Text(phoneFL1.get(i));
                    bh3.getChildren().addAll(phone, phoneN);

                    HBox bh4 = new HBox();

                    Text adress = new Text("Adress : ");
                    Text adressN = new Text(adressFL1.get(i));
                    bh4.getChildren().addAll(adress, adressN);

                    HBox bh5 = new HBox();

                    Text age = new Text("Age : ");
                    Text ageN = new Text(String.valueOf(ageFL1.get(i)));
                    bh5.getChildren().addAll(age, ageN);

                    vb.getStyleClass().addAll("user-name2");
                    vb.getChildren().addAll(bh, bh1, bh2, bh3, bh4, bh5);

                    VBox vb1 = new VBox();
                    vb1.setPadding(new Insets(15, 10, 0, 30));
                    Label l = new Label("User Info");
                    l.getStyleClass().addAll("user-label");

                    vb1.getChildren().addAll(l, view);

                    hb.getChildren().addAll(vb, vb1);
                    hb.setOnMouseClicked((event) -> {
                        compairPhone = phoneN.getText();
                        compairimage = img.getText();
                        
                        try { 
                            deleteUserPlatform = FXMLLoader.load(getClass().getResource("/admins/deleteUser.fxml"));
                            new JFXDialog(tabpan, deleteUserPlatform, JFXDialog.DialogTransition.TOP).show();
                        } catch (IOException ex) {
                            Logger.getLogger(HomeAdminController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                    hb1 = new HBox();
                    hb1.setPrefHeight(280);
                    hb1.setPrefWidth(395);
                    hb1.setBackground(new Background(new BackgroundFill(Color.valueOf("#fff"), CornerRadii.EMPTY, Insets.EMPTY)));
                    hb1.getStyleClass().addAll("user-box0");
                    hb1.setPadding(new Insets(0, 10, 0, 0));
                    hb.setSpacing(10);
                    Text img1 = new Text(imageFL2.get(i));
                    Image image1 = new Image("/img/bgnn");
                    Circle view1 = new Circle();
                    view1.setRadius(50);
                    view1.setFill(new ImagePattern(image1));

                    VBox vb11 = new VBox();
                    vb11.setPrefWidth(220);
                    vb11.setPrefHeight(280);
                    vb11.setBackground(new Background(new BackgroundFill(Color.valueOf("#fff"), CornerRadii.EMPTY, Insets.EMPTY)));

                    HBox bh6 = new HBox();

                    Text fNames = new Text("Firstname : ");
                    Text nameFs = new Text(nameFL2.get(i));
                    bh6.getChildren().addAll(fNames, nameFs);

                    HBox bh7 = new HBox();

                    Text Lnames = new Text("lastname : ");
                    Text nameLass = new Text(lnameFL2.get(i));
                    bh7.getChildren().addAll(Lnames, nameLass);

                    HBox bh8 = new HBox();

                    Text types = new Text("Type : ");
                    Text typeNs = new Text(typeFL2.get(i));
                    bh8.getChildren().addAll(types, typeNs);

                    HBox bh9 = new HBox();

                    Text phones = new Text("Phone : ");
                    Text phoneNs = new Text(phoneFL2.get(i));
                    bh9.getChildren().addAll(phones, phoneNs);

                    HBox bh10 = new HBox();

                    Text adresss = new Text("Adress : ");
                    Text adressNs = new Text(adressFL2.get(i));
                    bh10.getChildren().addAll(adresss, adressNs);

                    HBox bh11 = new HBox();

                    Text ages = new Text("Age : ");
                    Text ageNs = new Text(String.valueOf(HomeAdminController.age.get(i)));
                    bh11.getChildren().addAll(ages, ageNs);

                    vb11.getStyleClass().addAll("user-name2");
                    vb11.getChildren().addAll(bh6, bh7, bh8, bh9, bh10, bh11);

                    VBox vb12 = new VBox();
                    vb12.setPadding(new Insets(15, 10, 0, 30));
                    Label l1 = new Label("User Info");
                    l1.getStyleClass().addAll("user-label");

                    vb12.getChildren().addAll(l1, view1);

                    hb1.getChildren().addAll(vb11, vb12);
                    hb1.setOnMouseClicked((event) -> {

                        compairPhone = phoneNs.getText();
                        compairimage = img1.getText();
                        try {
                            deleteUserPlatform = FXMLLoader.load(getClass().getResource("/admins/deleteUser.fxml"));
                            new JFXDialog(tabpan, deleteUserPlatform, JFXDialog.DialogTransition.TOP).show();
                        } catch (IOException ex) {
                            Logger.getLogger(HomeAdminController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });

                    HBox hb5 = new HBox();

                    hb5.getChildren().addAll(hb, hb1);
                    hb5.setPadding(new Insets(15, 15, 15, 15));
                    containner.getChildren().add(hb5);
                    scroll.vvalueProperty().bind(containner.heightProperty());
                }
            } else {

                for (int i = 0; i < nameFL1.size(); i++) {

                    hb = new HBox();
                    hb.setPrefHeight(280);
                    hb.setPrefWidth(395);
                    hb.setBackground(new Background(new BackgroundFill(Color.valueOf("#fff"), CornerRadii.EMPTY, Insets.EMPTY)));
                    hb.getStyleClass().addAll("user-box1");
                    hb.setSpacing(10);
                    Text img = new Text(imageFL1.get(i));
                    Image image = new Image("/imgUsers/" + imageFL1.get(i));
                    Circle view = new Circle();
                    view.setRadius(50);
                    view.setFill(new ImagePattern(image));

                    VBox vb = new VBox();
                    vb.setPrefWidth(220);
                    vb.setPrefHeight(280);
                    vb.setBackground(new Background(new BackgroundFill(Color.valueOf("#fff"), CornerRadii.EMPTY, Insets.EMPTY)));

                    HBox bh = new HBox();

                    Text fName = new Text("Firstname : ");
                    Text nameF = new Text(nameFL1.get(i));
                    bh.getChildren().addAll(fName, nameF);

                    HBox bh1 = new HBox();

                    Text Lname = new Text("lastname : ");
                    Text nameLas = new Text(lnameFL1.get(i));
                    bh1.getChildren().addAll(Lname, nameLas);

                    HBox bh2 = new HBox();

                    Text type = new Text("Type : ");
                    Text typeN = new Text(typeFL1.get(i));
                    bh2.getChildren().addAll(type, typeN);

                    HBox bh3 = new HBox();

                    Text phone = new Text("Phone : ");
                    Text phoneN = new Text(phoneFL1.get(i));
                    bh3.getChildren().addAll(phone, phoneN);

                    HBox bh4 = new HBox();

                    Text adress = new Text("Adress : ");
                    Text adressN = new Text(adressFL1.get(i));
                    bh4.getChildren().addAll(adress, adressN);

                    HBox bh5 = new HBox();

                    Text age = new Text("Age : ");
                    Text ageN = new Text(String.valueOf(ageFL1.get(i)));
                    bh5.getChildren().addAll(age, ageN);

                    vb.getStyleClass().addAll("user-name2");
                    vb.getChildren().addAll(bh, bh1, bh2, bh3, bh4, bh5);

                    VBox vb1 = new VBox();
                    vb1.setPadding(new Insets(15, 10, 0, 30));
                    Label l = new Label("User Info");
                    l.getStyleClass().addAll("user-label");

                    vb1.getChildren().addAll(l, view);

                    hb.getChildren().addAll(vb, vb1);

                    hb.setOnMouseClicked((event) -> {

                        compairPhone = phoneN.getText();
                        compairimage = img.getText();
                        try {
                            deleteUserPlatform = FXMLLoader.load(getClass().getResource("/admins/deleteUser.fxml"));
                            new JFXDialog(tabpan, deleteUserPlatform, JFXDialog.DialogTransition.TOP).show();
                        } catch (IOException ex) {
                            Logger.getLogger(HomeAdminController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                    hb1 = new HBox();
                    hb1.setPrefHeight(280);
                    hb1.setPrefWidth(395);
                    hb1.setBackground(new Background(new BackgroundFill(Color.valueOf("#fff"), CornerRadii.EMPTY, Insets.EMPTY)));
                    hb1.getStyleClass().addAll("user-box0");
                    hb1.setPadding(new Insets(0, 10, 0, 0));
                    hb.setSpacing(10);
                    Text img1 = new Text(imageFL2.get(i));
                    Image image1 = new Image("/img/bgnn");
                    Circle view1 = new Circle();
                    view1.setRadius(50);
                    view1.setFill(new ImagePattern(image1));

                    VBox vb11 = new VBox();
                    vb11.setPrefWidth(220);
                    vb11.setPrefHeight(280);
                    vb11.setBackground(new Background(new BackgroundFill(Color.valueOf("#fff"), CornerRadii.EMPTY, Insets.EMPTY)));

                    HBox bh6 = new HBox();

                    Text fNames = new Text("Firstname : ");
                    Text nameFs = new Text(nameFL2.get(i));
                    bh6.getChildren().addAll(fNames, nameFs);

                    HBox bh7 = new HBox();

                    Text Lnames = new Text("lastname : ");
                    Text nameLass = new Text(lnameFL2.get(i));
                    bh7.getChildren().addAll(Lnames, nameLass);

                    HBox bh8 = new HBox();

                    Text types = new Text("Type : ");
                    Text typeNs = new Text(typeFL2.get(i));
                    bh8.getChildren().addAll(types, typeNs);

                    HBox bh9 = new HBox();

                    Text phones = new Text("Phone : ");
                    Text phoneNs = new Text(phoneFL2.get(i));
                    bh9.getChildren().addAll(phones, phoneNs);

                    HBox bh10 = new HBox();

                    Text adresss = new Text("Adress : ");
                    Text adressNs = new Text(adressFL2.get(i));
                    bh10.getChildren().addAll(adresss, adressNs);

                    HBox bh11 = new HBox();

                    Text ages = new Text("Age : ");
                    Text ageNs = new Text(String.valueOf(HomeAdminController.age.get(i)));
                    bh11.getChildren().addAll(ages, ageNs);

                    vb11.getStyleClass().addAll("user-name2");
                    vb11.getChildren().addAll(bh6, bh7, bh8, bh9, bh10, bh11);

                    VBox vb12 = new VBox();
                    vb12.setPadding(new Insets(15, 10, 0, 30));
                    Label l1 = new Label("User Info");
                    l1.getStyleClass().addAll("user-label");

                    vb12.getChildren().addAll(l1, view1);

                    hb1.getChildren().addAll(vb11, vb12);

                    hb1.setOnMouseClicked((event) -> {

                        compairPhone = phoneNs.getText();
                        compairimage = img1.getText();
                        try {
                            deleteUserPlatform = FXMLLoader.load(getClass().getResource("/admins/deleteUser.fxml"));
                            new JFXDialog(tabpan, deleteUserPlatform, JFXDialog.DialogTransition.TOP).show();
                        } catch (IOException ex) {
                            Logger.getLogger(HomeAdminController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });

                    HBox hb5 = new HBox();

                    hb5.getChildren().addAll(hb, hb1);
                    hb5.setPadding(new Insets(15, 15, 15, 15));
                    containner.getChildren().add(hb5);
                    scroll.vvalueProperty().bind(containner.heightProperty());
                }

                HBox hb0 = new HBox();
                hb0.setPrefHeight(280);
                hb0.setPrefWidth(395);
                hb0.setBackground(new Background(new BackgroundFill(Color.valueOf("#fff"), CornerRadii.EMPTY, Insets.EMPTY)));
                hb0.getStyleClass().addAll("user-box1");
                hb.setSpacing(10);
                Text img = new Text(imageFL2.get(imageFL2.size() - 1));
                Image image1 = new Image("/img/bgnn");
                Circle view1 = new Circle();
                view1.setRadius(50);
                view1.setFill(new ImagePattern(image1));

                VBox vb1 = new VBox();
                vb1.setPrefWidth(220);
                vb1.setPrefHeight(280);
                vb1.setBackground(new Background(new BackgroundFill(Color.valueOf("#fff"), CornerRadii.EMPTY, Insets.EMPTY)));

                HBox bh6 = new HBox();

                Text fNames = new Text("Firstname : ");
                Text nameFs = new Text(nameFL2.get(nameFL2.size() - 1));
                bh6.getChildren().addAll(fNames, nameFs);

                HBox bh7 = new HBox();

                Text Lnames = new Text("lastname : ");
                Text nameLass = new Text(lnameFL2.get(lnameFL2.size() - 1));
                bh7.getChildren().addAll(Lnames, nameLass);

                HBox bh8 = new HBox();

                Text types = new Text("Type : ");
                Text typeNs = new Text(typeFL2.get(typeFL2.size() - 1));
                bh8.getChildren().addAll(types, typeNs);

                HBox bh9 = new HBox();

                Text phones = new Text("Phone : ");
                Text phoneNs = new Text(phoneFL2.get(phoneFL2.size() - 1));
                bh9.getChildren().addAll(phones, phoneNs);

                HBox bh10 = new HBox();

                Text adresss = new Text("Adress : ");
                Text adressNs = new Text(adressFL2.get(adressFL2.size() - 1));
                bh10.getChildren().addAll(adresss, adressNs);

                HBox bh11 = new HBox();

                Text ages = new Text("Age : ");
                Text ageNs = new Text(String.valueOf(ageFL2.get(ageFL2.size() - 1)));
                bh11.getChildren().addAll(ages, ageNs);

                vb1.getStyleClass().addAll("user-name2");
                vb1.getChildren().addAll(bh6, bh7, bh8, bh9, bh10, bh11);

                VBox vb2 = new VBox();
                vb2.setPadding(new Insets(15, 10, 0, 30));
                Label l = new Label("User Info");
                l.getStyleClass().addAll("user-label");

                vb2.getChildren().addAll(l, view1);

                hb0.getChildren().addAll(vb1, vb2);

                hb0.setOnMouseClicked((event) -> {

                    compairPhone = phoneNs.getText();
                    compairimage = img.getText();
                    try {
                            deleteUserPlatform = FXMLLoader.load(getClass().getResource("/admins/deleteUser.fxml"));
                            new JFXDialog(tabpan, deleteUserPlatform, JFXDialog.DialogTransition.TOP).show();
                        } catch (IOException ex) {
                            Logger.getLogger(HomeAdminController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                });

                HBox hb5 = new HBox();

                hb5.getChildren().addAll(hb0);
                hb5.setPadding(new Insets(15, 15, 15, 15));
                containner.getChildren().add(hb5);
                scroll.vvalueProperty().bind(containner.heightProperty());

            }
        } catch (SQLException | ClassNotFoundException ex) {

        }
//        try {
//            Stage stage = (Stage) ref.getScene().getWindow();
//            Parent root = FXMLLoader.load(getClass().getResource("/admins/homeAdmin.fxml"));
//
//            Scene s = new Scene(root);
//            stage.setScene(s);
//            s.getStylesheets().add(getClass().getResource("/styleCss/chat.css").toExternalForm());
//            stage.show();
//            stage.setResizable(false);
//
//        } catch (IOException ex) {
//            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    static ObservableList<String> nameFL = FXCollections.observableArrayList();
    static ObservableList<String> Lname = FXCollections.observableArrayList();
    static ObservableList<String> imagess = FXCollections.observableArrayList();
    static ObservableList<String> phones = FXCollections.observableArrayList();
    static ObservableList<String> adress = FXCollections.observableArrayList();
    static ObservableList<Integer> counts = FXCollections.observableArrayList();
    static ObservableList<Integer> age = FXCollections.observableArrayList();
    static ObservableList<String> types = FXCollections.observableArrayList();

    ObservableList<String> nameFL1 = FXCollections.observableArrayList();//remplir 1/4
    ObservableList<String> nameFL2 = FXCollections.observableArrayList();//remplir autre 1/4 ..etc
    ObservableList<String> imageFL1 = FXCollections.observableArrayList();
    ObservableList<String> imageFL2 = FXCollections.observableArrayList();
    ObservableList<String> lnameFL1 = FXCollections.observableArrayList();
    ObservableList<String> lnameFL2 = FXCollections.observableArrayList();
    ObservableList<String> phoneFL1 = FXCollections.observableArrayList();
    ObservableList<String> phoneFL2 = FXCollections.observableArrayList();
    ObservableList<String> adressFL1 = FXCollections.observableArrayList();
    ObservableList<String> adressFL2 = FXCollections.observableArrayList();
    ObservableList<Integer> ageFL1 = FXCollections.observableArrayList();
    ObservableList<Integer> ageFL2 = FXCollections.observableArrayList();
    ObservableList<String> typeFL1 = FXCollections.observableArrayList();
    ObservableList<String> typeFL2 = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //pour vider les information en cas le refresh pour éviter le probleme de répétition//
        containner.getChildren().clear();
        nameFL.clear();
        Lname.clear();
        imagess.clear();
        phones.clear();
        age.clear();
        adress.clear();
        types.clear();
        
        PreparedStatement pp;
        ResultSet rr;
        Connection cc;

        try {
            cc = dao.Dao.getConnection();
            String req = "SELECT firstname,lastname,phone,adress,image,type,age FROM utilisateurs";
            pp = cc.prepareStatement(req);
            rr = pp.executeQuery();

            while (rr.next()) {
                //remplir les information des utilisateurs pour afficher les profile
                nameFL.add(rr.getString(1));
                Lname.add(rr.getString(2));
                imagess.add(rr.getString(5));
                phones.add(rr.getString(3));
                adress.add(rr.getString(4));
                types.add(rr.getString(6));
                age.add(rr.getInt(7));

            }

            if ((nameFL.size() % 2) == 0) {
                for (int i = 0; i < nameFL.size() / 2; i++) {

                    nameFL1.add(nameFL.get(i));
                    imageFL1.add(imagess.get(i));
                    lnameFL1.add(Lname.get(i));
                    phoneFL1.add(phones.get(i));
                    adressFL1.add(adress.get(i));
                    ageFL1.add(age.get(i));
                    typeFL1.add(types.get(i));

                }
                for (int i = nameFL.size() / 2; i < nameFL.size(); i++) {

                    nameFL2.add(nameFL.get(i));
                    imageFL2.add(imagess.get(i));
                    lnameFL2.add(Lname.get(i));
                    phoneFL2.add(phones.get(i));
                    adressFL2.add(adress.get(i));
                    ageFL2.add(age.get(i));
                    typeFL2.add(types.get(i));

                }
            } else {
                for (int i = 0; i < (nameFL.size() - 1) / 2; i++) {

                    nameFL1.add(nameFL.get(i));
                    imageFL1.add(imagess.get(i));
                    lnameFL1.add(Lname.get(i));
                    phoneFL1.add(phones.get(i));
                    adressFL1.add(adress.get(i));
                    ageFL1.add(age.get(i));
                    typeFL1.add(types.get(i));

                }
                for (int i = (nameFL.size() - 1) / 2; i < nameFL.size(); i++) {

                    nameFL2.add(nameFL.get(i));
                    imageFL2.add(imagess.get(i));
                    lnameFL2.add(Lname.get(i));
                    phoneFL2.add(phones.get(i));
                    adressFL2.add(adress.get(i));
                    ageFL2.add(age.get(i));
                    typeFL2.add(types.get(i));

                }

            }

            System.out.println("nameFL1 = " + nameFL1.size() + "\nnameFL2 = " + nameFL2.size());

            HBox hb = null, hb1 = null;
            if (nameFL.size() % 2 == 0) {

                for (int i = 0; i < nameFL2.size(); i++) {

                    hb = new HBox();
                    hb.setPrefHeight(280);
                    hb.setPrefWidth(395);
                    hb.setBackground(new Background(new BackgroundFill(Color.valueOf("#fff"), CornerRadii.EMPTY, Insets.EMPTY)));
                    hb.getStyleClass().addAll("user-box1");
                    hb.setSpacing(10);
                    Text img = new Text(imageFL1.get(i));
                    Image image = new Image("/imgUsers/" + imageFL1.get(i));
                    Circle view = new Circle();
                    view.setRadius(50);
                    view.setFill(new ImagePattern(image));

                    VBox vb = new VBox();
                    vb.setPrefWidth(220);
                    vb.setPrefHeight(280);
                    vb.setBackground(new Background(new BackgroundFill(Color.valueOf("#fff"), CornerRadii.EMPTY, Insets.EMPTY)));

                    HBox bh = new HBox();

                    Text fName = new Text("Firstname : ");
                    Text nameF = new Text(nameFL1.get(i));
                    bh.getChildren().addAll(fName, nameF);

                    HBox bh1 = new HBox();

                    Text Lname = new Text("lastname : ");
                    Text nameLas = new Text(lnameFL1.get(i));
                    bh1.getChildren().addAll(Lname, nameLas);

                    HBox bh2 = new HBox();

                    Text type = new Text("Type : ");
                    Text typeN = new Text(typeFL1.get(i));
                    bh2.getChildren().addAll(type, typeN);

                    HBox bh3 = new HBox();

                    Text phone = new Text("Phone : ");
                    Text phoneN = new Text(phoneFL1.get(i));
                    bh3.getChildren().addAll(phone, phoneN);

                    HBox bh4 = new HBox();

                    Text adress = new Text("Adress : ");
                    Text adressN = new Text(adressFL1.get(i));
                    bh4.getChildren().addAll(adress, adressN);

                    HBox bh5 = new HBox();

                    Text age = new Text("Age : ");
                    Text ageN = new Text(String.valueOf(ageFL1.get(i)));
                    bh5.getChildren().addAll(age, ageN);

                    vb.getStyleClass().addAll("user-name2");
                    vb.getChildren().addAll(bh, bh1, bh2, bh3, bh4, bh5);

                    VBox vb1 = new VBox();
                    vb1.setPadding(new Insets(15, 10, 0, 30));
                    Label l = new Label("User Info");
                    l.getStyleClass().addAll("user-label");

                    vb1.getChildren().addAll(l, view);

                    hb.getChildren().addAll(vb, vb1);
                    hb.setOnMouseClicked((event) -> {
                        compairPhone = phoneN.getText();
                        compairimage = img.getText();
                        
                        try { 
                            deleteUserPlatform = FXMLLoader.load(getClass().getResource("/admins/deleteUser.fxml"));
                            new JFXDialog(tabpan, deleteUserPlatform, JFXDialog.DialogTransition.TOP).show();
                        } catch (IOException ex) {
                            Logger.getLogger(HomeAdminController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                    hb1 = new HBox();
                    hb1.setPrefHeight(280);
                    hb1.setPrefWidth(395);
                    hb1.setBackground(new Background(new BackgroundFill(Color.valueOf("#fff"), CornerRadii.EMPTY, Insets.EMPTY)));
                    hb1.getStyleClass().addAll("user-box0");
                    hb1.setPadding(new Insets(0, 10, 0, 0));
                    hb.setSpacing(10);
                    Text img1 = new Text(imageFL2.get(i));
                    
                    Image image1 = new Image("/imgUsers/" + imageFL2.get(i));
                    Circle view1 = new Circle();
                    view1.setRadius(50);
                    view1.setFill(new ImagePattern(image1));

                    VBox vb11 = new VBox();
                    vb11.setPrefWidth(220);
                    vb11.setPrefHeight(280);
                    vb11.setBackground(new Background(new BackgroundFill(Color.valueOf("#fff"), CornerRadii.EMPTY, Insets.EMPTY)));

                    HBox bh6 = new HBox();

                    Text fNames = new Text("Firstname : ");
                    Text nameFs = new Text(nameFL2.get(i));
                    bh6.getChildren().addAll(fNames, nameFs);

                    HBox bh7 = new HBox();

                    Text Lnames = new Text("lastname : ");
                    Text nameLass = new Text(lnameFL2.get(i));
                    bh7.getChildren().addAll(Lnames, nameLass);

                    HBox bh8 = new HBox();

                    Text types = new Text("Type : ");
                    Text typeNs = new Text(typeFL2.get(i));
                    bh8.getChildren().addAll(types, typeNs);

                    HBox bh9 = new HBox();

                    Text phones = new Text("Phone : ");
                    Text phoneNs = new Text(phoneFL2.get(i));
                    bh9.getChildren().addAll(phones, phoneNs);

                    HBox bh10 = new HBox();

                    Text adresss = new Text("Adress : ");
                    Text adressNs = new Text(adressFL2.get(i));
                    bh10.getChildren().addAll(adresss, adressNs);

                    HBox bh11 = new HBox();

                    Text ages = new Text("Age : ");
                    Text ageNs = new Text(String.valueOf(HomeAdminController.age.get(i)));
                    bh11.getChildren().addAll(ages, ageNs);

                    vb11.getStyleClass().addAll("user-name2");
                    vb11.getChildren().addAll(bh6, bh7, bh8, bh9, bh10, bh11);

                    VBox vb12 = new VBox();
                    vb12.setPadding(new Insets(15, 10, 0, 30));
                    Label l1 = new Label("User Info");
                    l1.getStyleClass().addAll("user-label");

                    vb12.getChildren().addAll(l1, view1);

                    hb1.getChildren().addAll(vb11, vb12);
                    hb1.setOnMouseClicked((event) -> {

                        compairPhone = phoneNs.getText();
                        compairimage = img1.getText();
                        try {
                            deleteUserPlatform = FXMLLoader.load(getClass().getResource("/admins/deleteUser.fxml"));
                            new JFXDialog(tabpan, deleteUserPlatform, JFXDialog.DialogTransition.TOP).show();
                        } catch (IOException ex) {
                            Logger.getLogger(HomeAdminController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });

                    HBox hb5 = new HBox();

                    hb5.getChildren().addAll(hb, hb1);
                    hb5.setPadding(new Insets(15, 15, 15, 15));
                    containner.getChildren().add(hb5);
                    scroll.vvalueProperty().bind(containner.heightProperty());
                }
            } else {

                for (int i = 0; i < nameFL1.size(); i++) {

                    hb = new HBox();
                    hb.setPrefHeight(280);
                    hb.setPrefWidth(395);
                    hb.setBackground(new Background(new BackgroundFill(Color.valueOf("#fff"), CornerRadii.EMPTY, Insets.EMPTY)));
                    hb.getStyleClass().addAll("user-box1");
                    hb.setSpacing(10);
                    Text img = new Text(imageFL1.get(i));
                    Image image = new Image("/imgUsers/" + imageFL1.get(i));
                    Circle view = new Circle();
                    view.setRadius(50);
                    view.setFill(new ImagePattern(image));

                    VBox vb = new VBox();
                    vb.setPrefWidth(220);
                    vb.setPrefHeight(280);
                    vb.setBackground(new Background(new BackgroundFill(Color.valueOf("#fff"), CornerRadii.EMPTY, Insets.EMPTY)));

                    HBox bh = new HBox();

                    Text fName = new Text("Firstname : ");
                    Text nameF = new Text(nameFL1.get(i));
                    bh.getChildren().addAll(fName, nameF);

                    HBox bh1 = new HBox();

                    Text Lname = new Text("lastname : ");
                    Text nameLas = new Text(lnameFL1.get(i));
                    bh1.getChildren().addAll(Lname, nameLas);

                    HBox bh2 = new HBox();

                    Text type = new Text("Type : ");
                    Text typeN = new Text(typeFL1.get(i));
                    bh2.getChildren().addAll(type, typeN);

                    HBox bh3 = new HBox();

                    Text phone = new Text("Phone : ");
                    Text phoneN = new Text(phoneFL1.get(i));
                    bh3.getChildren().addAll(phone, phoneN);

                    HBox bh4 = new HBox();

                    Text adress = new Text("Adress : ");
                    Text adressN = new Text(adressFL1.get(i));
                    bh4.getChildren().addAll(adress, adressN);

                    HBox bh5 = new HBox();

                    Text age = new Text("Age : ");
                    Text ageN = new Text(String.valueOf(ageFL1.get(i)));
                    bh5.getChildren().addAll(age, ageN);

                    vb.getStyleClass().addAll("user-name2");
                    vb.getChildren().addAll(bh, bh1, bh2, bh3, bh4, bh5);

                    VBox vb1 = new VBox();
                    vb1.setPadding(new Insets(15, 10, 0, 30));
                    Label l = new Label("User Info");
                    l.getStyleClass().addAll("user-label");

                    vb1.getChildren().addAll(l, view);

                    hb.getChildren().addAll(vb, vb1);

                    hb.setOnMouseClicked((event) -> {

                        compairPhone = phoneN.getText();
                        compairimage = img.getText();
                        try {
                            deleteUserPlatform = FXMLLoader.load(getClass().getResource("/admins/deleteUser.fxml"));
                            new JFXDialog(tabpan, deleteUserPlatform, JFXDialog.DialogTransition.TOP).show();
                        } catch (IOException ex) {
                            Logger.getLogger(HomeAdminController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });

                    hb1 = new HBox();
                    hb1.setPrefHeight(280);
                    hb1.setPrefWidth(395);
                    hb1.setBackground(new Background(new BackgroundFill(Color.valueOf("#fff"), CornerRadii.EMPTY, Insets.EMPTY)));
                    hb1.getStyleClass().addAll("user-box0");
                    hb1.setPadding(new Insets(0, 10, 0, 0));
                    hb.setSpacing(10);
                    Text img1 = new Text(imageFL2.get(i));
                    Image image1 = new Image("/imgUsers/" + imageFL2.get(i));
                    Circle view1 = new Circle();
                    view1.setRadius(50);
                    view1.setFill(new ImagePattern(image1));

                    VBox vb11 = new VBox();
                    vb11.setPrefWidth(220);
                    vb11.setPrefHeight(280);
                    vb11.setBackground(new Background(new BackgroundFill(Color.valueOf("#fff"), CornerRadii.EMPTY, Insets.EMPTY)));

                    HBox bh6 = new HBox();

                    Text fNames = new Text("Firstname : ");
                    Text nameFs = new Text(nameFL2.get(i));
                    bh6.getChildren().addAll(fNames, nameFs);

                    HBox bh7 = new HBox();

                    Text Lnames = new Text("lastname : ");
                    Text nameLass = new Text(lnameFL2.get(i));
                    bh7.getChildren().addAll(Lnames, nameLass);

                    HBox bh8 = new HBox();

                    Text types = new Text("Type : ");
                    Text typeNs = new Text(typeFL2.get(i));
                    bh8.getChildren().addAll(types, typeNs);

                    HBox bh9 = new HBox();

                    Text phones = new Text("Phone : ");
                    Text phoneNs = new Text(phoneFL2.get(i));
                    bh9.getChildren().addAll(phones, phoneNs);

                    HBox bh10 = new HBox();

                    Text adresss = new Text("Adress : ");
                    Text adressNs = new Text(adressFL2.get(i));
                    bh10.getChildren().addAll(adresss, adressNs);

                    HBox bh11 = new HBox();

                    Text ages = new Text("Age : ");
                    Text ageNs = new Text(String.valueOf(HomeAdminController.age.get(i)));
                    bh11.getChildren().addAll(ages, ageNs);

                    vb11.getStyleClass().addAll("user-name2");
                    vb11.getChildren().addAll(bh6, bh7, bh8, bh9, bh10, bh11);

                    VBox vb12 = new VBox();
                    vb12.setPadding(new Insets(15, 10, 0, 30));
                    Label l1 = new Label("User Info");
                    l1.getStyleClass().addAll("user-label");

                    vb12.getChildren().addAll(l1, view1);

                    hb1.getChildren().addAll(vb11, vb12);

                    hb1.setOnMouseClicked((event) -> {

                        compairPhone = phoneNs.getText();
                        compairimage = img1.getText();
                        try {
                            deleteUserPlatform = FXMLLoader.load(getClass().getResource("/admins/deleteUser.fxml"));
                            new JFXDialog(tabpan, deleteUserPlatform, JFXDialog.DialogTransition.TOP).show();
                        } catch (IOException ex) {
                            Logger.getLogger(HomeAdminController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });

                    HBox hb5 = new HBox();

                    hb5.getChildren().addAll(hb, hb1);
                    hb5.setPadding(new Insets(15, 15, 15, 15));
                    containner.getChildren().add(hb5);
                    scroll.vvalueProperty().bind(containner.heightProperty());
                }

                HBox hb0 = new HBox();
                hb0.setPrefHeight(280);
                hb0.setPrefWidth(395);
                hb0.setBackground(new Background(new BackgroundFill(Color.valueOf("#fff"), CornerRadii.EMPTY, Insets.EMPTY)));
                hb0.getStyleClass().addAll("user-box1");
                hb.setSpacing(10);
                Text img = new Text(imageFL2.get(imageFL2.size() - 1));
                Image image1 = new Image("/imgUsers/" + imageFL2.get(imageFL2.size() - 1));
                Circle view1 = new Circle();
                view1.setRadius(50);
                view1.setFill(new ImagePattern(image1));

                VBox vb1 = new VBox();
                vb1.setPrefWidth(220);
                vb1.setPrefHeight(280);
                vb1.setBackground(new Background(new BackgroundFill(Color.valueOf("#fff"), CornerRadii.EMPTY, Insets.EMPTY)));

                HBox bh6 = new HBox();

                Text fNames = new Text("Firstname : ");
                Text nameFs = new Text(nameFL2.get(nameFL2.size() - 1));
                bh6.getChildren().addAll(fNames, nameFs);

                HBox bh7 = new HBox();

                Text Lnames = new Text("lastname : ");
                Text nameLass = new Text(lnameFL2.get(lnameFL2.size() - 1));
                bh7.getChildren().addAll(Lnames, nameLass);

                HBox bh8 = new HBox();

                Text types = new Text("Type : ");
                Text typeNs = new Text(typeFL2.get(typeFL2.size() - 1));
                bh8.getChildren().addAll(types, typeNs);

                HBox bh9 = new HBox();

                Text phones = new Text("Phone : ");
                Text phoneNs = new Text(phoneFL2.get(phoneFL2.size() - 1));
                bh9.getChildren().addAll(phones, phoneNs);

                HBox bh10 = new HBox();

                Text adresss = new Text("Adress : ");
                Text adressNs = new Text(adressFL2.get(adressFL2.size() - 1));
                bh10.getChildren().addAll(adresss, adressNs);

                HBox bh11 = new HBox();

                Text ages = new Text("Age : ");
                Text ageNs = new Text(String.valueOf(ageFL2.get(ageFL2.size() - 1)));
                bh11.getChildren().addAll(ages, ageNs);

                vb1.getStyleClass().addAll("user-name2");
                vb1.getChildren().addAll(bh6, bh7, bh8, bh9, bh10, bh11);

                VBox vb2 = new VBox();
                vb2.setPadding(new Insets(15, 10, 0, 30));
                Label l = new Label("User Info");
                l.getStyleClass().addAll("user-label");

                vb2.getChildren().addAll(l, view1);

                hb0.getChildren().addAll(vb1, vb2);

                hb0.setOnMouseClicked((event) -> {

                    compairPhone = phoneNs.getText();
                    compairimage = img.getText();
                    try {
                            deleteUserPlatform = FXMLLoader.load(getClass().getResource("/admins/deleteUser.fxml"));
                            new JFXDialog(tabpan, deleteUserPlatform, JFXDialog.DialogTransition.TOP).show();
                        } catch (IOException ex) {
                            Logger.getLogger(HomeAdminController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                });

                HBox hb5 = new HBox();

                hb5.getChildren().addAll(hb0);
                hb5.setPadding(new Insets(15, 15, 15, 15));
                containner.getChildren().add(hb5);
                scroll.vvalueProperty().bind(containner.heightProperty());

            }
        } catch (SQLException | ClassNotFoundException e) {

        }

        flName1.setText(UsersLoginController.firstName + " " + UsersLoginController.lastName);

        Image image1 = new Image("/imgUsers/" + UsersLoginController.img);
        imgUser1.setFill(new ImagePattern(image1));

        flName2.setText(UsersLoginController.firstName + " " + UsersLoginController.lastName);

        Image image2 = new Image("/imgUsers/" + UsersLoginController.img);
        imgUser2.setFill(new ImagePattern(image2));

        flName4.setText(UsersLoginController.firstName + " " + UsersLoginController.lastName);

        Image image4 = new Image("/imgUsers/" + UsersLoginController.img);
        imgUser4.setFill(new ImagePattern(image4));

        flName5.setText(UsersLoginController.firstName + " " + UsersLoginController.lastName);

        Image image5 = new Image("/imgUsers/" + UsersLoginController.img);
        imgUser5.setFill(new ImagePattern(image5));
        flName6.setText(UsersLoginController.firstName + " " + UsersLoginController.lastName);

        Image image6 = new Image("/imgUsers/" + UsersLoginController.img);
        imgUser6.setFill(new ImagePattern(image6));

        Image imageFourn = new Image("/img/avatar.png");
        imgFourniseur.setFill(new ImagePattern(imageFourn));

        //Function for get Balance
        getBalanc();
       
//        try {
//            Connection con = null;
//            PreparedStatement ps = null;
//            ResultSet rs = null;
//
//            String sql = "SELECT * FROM utilisateurs ";
//
//            con = dao.Dao.getConnection();
//            ps = con.prepareStatement(sql);
//            rs = ps.executeQuery();
//
//            while (rs.next()) {
//
//                data.add(new userVo(rs.getInt(1), rs.getString(2), rs.getString(3),
//                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
//                        rs.getInt(8), rs.getString(9), rs.getString(10)));
//
//            }
//
//        } catch (ClassNotFoundException | SQLException ex) {
//            Logger.getLogger(HomeAdminController.class.getName()).log(Level.SEVERE, null, ex);
//
//        }

        //remplier la table de sales...
       dataTableSelling();

        ObservableList<String> Fnames = FXCollections.observableArrayList();
        ObservableList<String> Lnames = FXCollections.observableArrayList();
        ObservableList<String> images = FXCollections.observableArrayList();
        ObservableList<Integer> etats = FXCollections.observableArrayList();
        ObservableList<Integer> count = FXCollections.observableArrayList();
        ObservableList<String> typeU = FXCollections.observableArrayList();

        Connection co;
        PreparedStatement p;
        ResultSet r;

        try {
            co = dao.Dao.getConnection();

            String sql = "SELECT firstname,lastname,etat,image,id,type FROM utilisateurs WHERE id != " + UsersLoginController.id;
            p = co.prepareStatement(sql);
            r = p.executeQuery();

            while (r.next()) {
                typeU.add(r.getString(6));
                count.add(r.getInt(5));
                Lnames.add(r.getString(1));
                Fnames.add(r.getString(2));
                etats.add(r.getInt(3));
                images.add(r.getString(4));

            }
            // System.out.println(count);

            for (int i = 0; i < count.size(); i++) {

                //System.out.println(Lnames.get(i));
                HBox hb = new HBox();
                hb.setPrefWidth(375);
                hb.setPrefHeight(75);
                hb.setBackground(new Background(new BackgroundFill(Color.valueOf("#242A31"), CornerRadii.EMPTY, Insets.EMPTY)));
                hb.getStyleClass().addAll("user-box");
                hb.setPadding(new Insets(10, 10, 0, 15));
                hb.setSpacing(10);

                System.out.println("lie imag howa ::: " + images.get(i));

                Label id = new Label();
                id.setText(String.valueOf(count.get(i)));
                id.setVisible(false);
                System.out.println("im1");
                Image imag = new Image("/imgUsers/" + images.get(i));
                System.out.println("im2");
                Circle view = new Circle();
                view.setRadius(27);
                view.setFill(new ImagePattern(imag));

                VBox vb1 = new VBox();
                HBox hbvb = new HBox();
                Label l = new Label(Fnames.get(i) + " " + Lnames.get(i));
                l.getStyleClass().addAll("user-name");
                Label l1 = new Label(typeU.get(i));
                l1.getStyleClass().addAll("user-name1");
                Circle circle = new Circle();
                Label msg;
                if (etats.get(i) == 1) {
                    circle.setRadius(5);
                    circle.setFill(new Color(0, 1, 0, 1));
                    msg = new Label("Connected");
                } else {
                    circle.setRadius(5);
                    circle.setFill(new Color(0, 1, 0, 0));
                    msg = new Label("Desconnected");
                }
                hbvb.getChildren().addAll(l, id);

                msg.getStyleClass().addAll("user-name");

                vb1.getChildren().addAll(hbvb, msg);

                VBox vb2 = new VBox();
                FontAwesomeIconView awesome = new FontAwesomeIconView();
                awesome.setGlyphName("ELLIPSIS_H");
                awesome.setSize("23");
                awesome.setFill(Color.DARKGRAY);

                vb2.getChildren().add(awesome);
//        
                hb.getChildren().addAll(circle, view, vb1, vb2);

                container.getChildren().add(hb);

                hb.setOnMouseClicked((MouseEvent e) -> {
                    getId = Integer.parseInt(id.getText());
                    SendedName.setText(l.getText());
                    this.typeU.setText(l1.getText());
                    messageBox.getChildren().clear();

                    Connection c;
                    PreparedStatement p1, p2;
                    ResultSet r1, r2;

                    try {
                        c = dao.Dao.getConnection();
                        //For get dialog from database 
                        String sql1 = "SELECT dialog,idExpéditeur FROM chats WHERE idExpéditeur = "
                                + UsersLoginController.id + " AND idDestinatair = " + id.getText() + " OR idExpéditeur = "
                                + id.getText() + " AND idDestinatair = " + UsersLoginController.id;
                        p1 = c.prepareStatement(sql1);
                        r1 = p1.executeQuery();
                        while (r1.next()) {

                            dialog = r1.getString(1);

                            //لجلب صورة المرسل
                            String sqlIm = "SELECT image FROM utilisateurs WHERE id = " + id.getText();
                            p2 = c.prepareStatement(sqlIm);
                            r2 = p2.executeQuery();

                            while (r2.next()) {

                                dialog1 = r1.getString(1).toLowerCase();

                                //كود لجلب المحادثات المستلمة
                                if (r1.getInt(2) == Integer.parseInt(id.getText())) {

                                    HBox hBo = new HBox();

                                    hBo.setPrefWidth(809);
                                    hBo.setMinHeight(hBo.USE_COMPUTED_SIZE);
                                    hBo.setAlignment(Pos.CENTER_LEFT);
                                    hBo.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
                                    hBo.setPadding(new Insets(0, 0, 0, 10));

                                    Image iii = new Image("/imgUsers/" + r2.getString(1));

                                    Circle imageSend = new Circle();
                                    imageSend.setRadius(23);
                                    imageSend.setFill(new ImagePattern(iii));

                                    TextArea content = new TextArea();
                                    content.getStyleClass().addAll("send-msg-area");
                                    content.setWrapText(true);

                                    content.setMaxSize(440, 55);
                                    content.setMinSize(89, 55);
                                    content.setPrefSize(r1.getString(1).length() * 12, r1.getString(1).length() + 30);
                                    content.setMinHeight(content.USE_PREF_SIZE);
                                    content.setEditable(false);

                                    //المحادثة القادمة يتم التاكد ان كانت صورة يتم تحويلها من نص الى شكل صورة وعرضها
                                    if (r1.getString(1).matches(".*.jpg") || dialog1.matches(".*.png")
                                            || dialog1.matches(".*.png") || dialog1.matches(".*.gif")
                                            || dialog1.matches(".*.jpeg")) {
                                        ImageView imageSendedd = new ImageView(r1.getString(1));
                                        imageSendedd.setFitWidth(100);
                                        imageSendedd.setFitHeight(100);

                                        hBo.getChildren().addAll(imageSend, imageSendedd);

                                        //لعرض المحتوى ان كان صورة
                                        imageSendedd.setOnMouseClicked((event) -> {

                                            try {
//                                       
                                                ShowImageController.urlImage = imageSendedd.getImage();
                                                showImage = FXMLLoader.load(getClass().getResource("/auther/showImage.fxml"));
                                                new JFXDialog(tabpan, showImage, JFXDialog.DialogTransition.CENTER).show();

                                            } catch (IOException ex) {
                                                Logger.getLogger(caissiers.MessagePlatformController.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        });
                                    } else {
                                        //في حالة القادم نص وليس صورة
                                        content.setText(r1.getString(1));

                                        hBo.getChildren().addAll(imageSend, content);
                                    }

                                    hBo.setSpacing(19);
                                    messageBox.getChildren().add(hBo);

                                    scrollPaneMessageBox.vvalueProperty().bind(messageBox.heightProperty());

                                } else {
                                    // كود بالنسبة للمحادثات المرسلة
                                    HBox hBox = new HBox();

                                    hBox.setPrefWidth(809);
                                    hBox.setMinHeight(hBox.USE_COMPUTED_SIZE);
                                    hBox.setAlignment(Pos.CENTER_LEFT);
                                    hBox.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                                    hBox.setPadding(new Insets(0, 0, 0, 10));

                                    Image ii = new Image("/imgUsers/" + UsersLoginController.img);

                                    Circle imageSended = new Circle();
                                    imageSended.setRadius(23);
                                    imageSended.setFill(new ImagePattern(ii));

                                    TextArea contentArea = new TextArea();
                                    contentArea.getStyleClass().addAll("msg-src", "msg-area");
                                    contentArea.setWrapText(true);

                                    contentArea.setMaxSize(440, 55);
                                    contentArea.setMinSize(89, 55);
                                    contentArea.setPrefSize(r1.getString(1).length() * 12, r1.getString(1).length() + 30);
                                    contentArea.setMinHeight(contentArea.USE_PREF_SIZE);
                                    contentArea.setEditable(false);
                                    if (dialog1.matches(".*.jpg") || dialog1.matches(".*.png")
                                            || dialog1.matches(".*.png") || dialog1.matches(".*.gif")
                                            || dialog1.matches(".*.jpeg")) {
                                        ImageView imageSendedd = new ImageView(r1.getString(1));
                                        imageSendedd.setFitWidth(100);
                                        imageSendedd.setFitHeight(100);

                                        hBox.getChildren().addAll(imageSended, imageSendedd);

                                        //لعرض المحتوى ان كان صورة
                                        imageSendedd.setOnMouseClicked((event) -> {

                                            try {
//                                           

                                                ShowImageController.urlImage = imageSendedd.getImage();
                                                showImage = FXMLLoader.load(getClass().getResource("/auther/showImage.fxml"));
                                                new JFXDialog(tabpan, showImage, JFXDialog.DialogTransition.CENTER).show();

                                            } catch (IOException ex) {
                                                Logger.getLogger(caissiers.MessagePlatformController.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        });

                                    } else {
                                        contentArea.setText(r1.getString(1));

                                        hBox.getChildren().addAll(imageSended, contentArea);
                                    }
                                    hBox.setSpacing(19);
                                    messageBox.getChildren().add(hBox);
                                    scrollPaneMessageBox.vvalueProperty().bind(messageBox.heightProperty());

                                }
                            }
                        }

                    } catch (SQLException | ClassNotFoundException ex) {

                    }
                });
            }

        } catch (SQLException | ClassNotFoundException z) {

        }

        //show providers data in the table
        dataTableProvider();
    }
    
    @FXML
    void refreshProv(MouseEvent event) {
        list.clear();
        dataTableProvider();
    }

    @FXML
    void refreshSales(MouseEvent event) {
        data1.clear();
        dataTableSelling();
    }
    
    @FXML
    void refreshBalance(MouseEvent event) {
        getBalanc();
    }

    private void getBalanc(){
         try {
            PreparedStatement ps = null, ps1 = null, ps2 = null,ps3;
            ResultSet rs = null, rs1 = null, rs2 = null,rs3;
            Connection con = null;

            String sql = "SELECT SUM(price) FROM listVente ";
            con = dao.Dao.getConnection();

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                String sql1 = "SELECT COUNT(id) FROM produits ";
                con = dao.Dao.getConnection();

                ps1 = con.prepareStatement(sql1);
                rs1 = ps1.executeQuery();

                while (rs1.next()) {

                    String sql2 = "SELECT SUM(revenue) FROM listVente";
                    con = dao.Dao.getConnection();

                    ps2 = con.prepareStatement(sql2);
                    rs2 = ps2.executeQuery();

                    while (rs2.next()) {

                        String sql3 = "SELECT SUM(cridé) FROM fournisseur";
                        ps3 = con.prepareStatement(sql3);
                        rs3 = ps3.executeQuery();
                        
                        while(rs3.next()){
                        // System.out.println(LocalDateTime.now());
                        balance.setText(String.valueOf(rs.getFloat(1)) + "  DA");
                        pKind.setText(String.valueOf(rs1.getInt(1)));
                        revenue.setText(String.valueOf(rs2.getFloat(1)) + "  DA");
                        credit.setText(String.valueOf(rs3.getFloat(1))+"  DA");
                    }
                }
            }
            }
        } catch (ClassNotFoundException | SQLException e) {

        }

    }
    
    private void dataTableProvider(){
        
        try {
            Connection con;
            PreparedStatement ps1, ps2;
            ResultSet rs1, rs2;

            con = dao.Dao.getConnection();
            String sql1 = "SELECT * FROM fournisseur";
            ps1 = con.prepareStatement(sql1);
            rs1 = ps1.executeQuery();

            while (rs1.next()) {

                String sql2 = "SELECT nom FROM catégories WHERE id = " + rs1.getInt(6);
                ps2 = con.prepareStatement(sql2);
                rs2 = ps2.executeQuery();

                while (rs2.next()) {

                    list.add(new providerVo(rs1.getInt(1), rs1.getString(2), rs1.getString(3),
                             rs1.getString(4), rs1.getString(5), rs2.getString(1),
                            rs1.getDouble(8), rs1.getString(9), rs1.getString(10)));

                }

            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());

        }
        idPro.setCellValueFactory(new PropertyValueFactory<providerVo, Integer>("id"));
        firstnameT.setCellValueFactory(new PropertyValueFactory<providerVo, String>("firstname"));
        lastnameT.setCellValueFactory(new PropertyValueFactory<providerVo, String>("lastnaame"));
        phoneT.setCellValueFactory(new PropertyValueFactory<providerVo, String>("phone"));
        locationT.setCellValueFactory(new PropertyValueFactory<providerVo, String>("location"));
        noteT.setCellValueFactory(new PropertyValueFactory<providerVo, String>("note"));
        categoryT.setCellValueFactory(new PropertyValueFactory<providerVo, String>("category"));
        cridéT.setCellValueFactory(new PropertyValueFactory<providerVo, Double>("cridé"));
        emailT.setCellValueFactory(new PropertyValueFactory<providerVo, String>("email"));

        tabViewPro.setItems(list);
        
    }
    
    private void dataTableSelling(){
        
         try {
            PreparedStatement ps = null, ps1 = null, ps2 = null;
            ResultSet rs = null, rs1 = null, rs2 = null;
            Connection con = null;

            String sql = "SELECT idProd,quntité,price,revenue,date,idUser FROM listVente";
            con = dao.Dao.getConnection();

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                String sql1 = "SELECT nom  FROM produits WHERE id = " + rs.getInt(1);

                ps1 = con.prepareStatement(sql1);
                rs1 = ps1.executeQuery();

                while (rs1.next()) {

                    String sql2 = "SELECT firstname , lastname FROM utilisateurs WHERE "
                            + "id =" + rs.getInt(6);
                    ps2 = con.prepareStatement(sql2);
                    rs2 = ps2.executeQuery();

                    while (rs2.next()) {

                        data1.add(new listVentVo(rs1.getString(1), rs.getInt(2),
                                rs.getFloat(3), rs.getFloat(4), rs.getDate(5), rs2.getString(1) + " " + rs2.getString(2)));

                    }

                }
            }

            product.setCellValueFactory(new PropertyValueFactory<listVentVo, String>("nameProduct"));
            quantity.setCellValueFactory(new PropertyValueFactory<listVentVo, Integer>("quantity"));
            price.setCellValueFactory(new PropertyValueFactory<listVentVo, Float>("price"));
            revenue1.setCellValueFactory(new PropertyValueFactory<listVentVo, Float>("revenue"));
            dateS.setCellValueFactory(new PropertyValueFactory<listVentVo, Date>("date"));
            cashier.setCellValueFactory(new PropertyValueFactory<listVentVo, String>("nameUser"));

            tabView.setItems(data1);

        } catch (SQLException ex) {
            Logger.getLogger(HomeAdminController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HomeAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
