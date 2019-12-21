package responsableStock;

import auther.ShowImageController;
import caissiers.MessagePlatformController;
import static caissiers.MessagePlatformController.dialog;
import static caissiers.MessagePlatformController.getId;
import static caissiers.MessagePlatformController.urlImg;
import com.jfoenix.controls.JFXDialog;
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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
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
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import login.UsersLoginController;
import org.controlsfx.control.Notifications;
import vo.chatVo;

/**
 * FXML Controller class
 *
 * @author Mido
 */
public class MessageController implements Initializable {

    public static String dialog1;
    
    @FXML
    private AnchorPane showImage;
    
    @FXML
    private Text SendedName;

    @FXML
    private Label typeUser;
    
    @FXML
    private StackPane message;
    
    @FXML
    private JFXTextArea msgEntred;

    @FXML
    private VBox messageBox;

    @FXML
    private JFXTextField searchMsg;
    
    @FXML
    private ScrollPane scrollPaneMessageBox;
    @FXML
    private VBox container;

    Notifications notification;
    
    
     @FXML
    private void searchMsg(KeyEvent event){
        
        container.getChildren().clear();
        
        ObservableList<String> Fnames = FXCollections.observableArrayList();
        ObservableList<String> Lnames = FXCollections.observableArrayList();
        ObservableList<String> images = FXCollections.observableArrayList();
        ObservableList<Integer> etats = FXCollections.observableArrayList();
        ObservableList<Integer> count = FXCollections.observableArrayList();
        ObservableList<String> typeUs = FXCollections.observableArrayList();

        
                 Connection co ;
                 PreparedStatement ps;
                 ResultSet rss;
                 
                 try{
                     co = dao.Dao.getConnection();
                     
                     String sql = "SELECT firstname,lastname,etat,image,id,type FROM utilisateurs WHERE lastname LIKE '"+searchMsg.getText()+"%' OR firstname LIKE '"+searchMsg.getText()+"%'";
                     ps = co.prepareStatement(sql);
                     rss = ps.executeQuery();
                     
                 while(rss.next()){
                     
                 typeUs.add(rss.getString(6));
                count.add(rss.getInt(5));
                Lnames.add(rss.getString(1));
                Fnames.add(rss.getString(2));
                etats.add(rss.getInt(3));
                images.add(rss.getString(4));

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

                Image image = new Image("/imgUsers/" + images.get(i));
                Circle view = new Circle();
                view.setRadius(27);
                view.setFill(new ImagePattern(image));

                VBox vb1 = new VBox();
                HBox hbvb = new HBox();
                Label l = new Label(Fnames.get(i) + " " + Lnames.get(i));
                l.getStyleClass().addAll("user-name");
                Label l1 = new Label(typeUs.get(i));
                l1.getStyleClass().addAll("user-name1");
                Circle circle = new Circle();
                Label msg;
                if ((etats.get(i) == 1) && (typeUs.get(i).equals("Admin"))) {
                    circle.setRadius(5);
                    circle.setFill(new Color(1, 0, 0, 1));
                    msg = new Label("Connected");
                }
                else
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
                    typeUser.setText(l1.getText());
                    messageBox.getChildren().clear();

                    Connection c;
                    PreparedStatement ps1, ps2;
                    ResultSet rs1, rs2;

                    try {
                        c = dao.Dao.getConnection();
                        //For get dialog from database 
                        String sql1 = "SELECT dialog,idExpéditeur FROM chats WHERE idExpéditeur = "
                                + UsersLoginController.id + " AND idDestinatair = " + id.getText() + " OR idExpéditeur = "
                                + id.getText() + " AND idDestinatair = " + UsersLoginController.id;
                        ps1 = c.prepareStatement(sql1);
                        rs1 = ps1.executeQuery();
                        while (rs1.next()) {
                            
                            dialog = rs1.getString(1);
                            
                            //لجلب صورة المرسل
                            String sqlIm = "SELECT image FROM utilisateurs WHERE id = " + id.getText();
                            ps2 = c.prepareStatement(sqlIm);
                            rs2 = ps2.executeQuery();

                            while (rs2.next()) {

                                dialog1 = rs1.getString(1).toLowerCase();
                                
                               //كود لجلب المحادثات المستلمة
                                if (rs1.getInt(2) == Integer.parseInt(id.getText())) {

                                    HBox hBo = new HBox();

                                    hBo.setPrefWidth(809);
                                    hBo.setMinHeight(hBo.USE_COMPUTED_SIZE);
                                    hBo.setAlignment(Pos.CENTER_LEFT);
                                    hBo.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
                                    hBo.setPadding(new Insets(0, 0, 0, 10));

                                    Image iii = new Image("/imgUsers/" + rs2.getString(1));

                                    Circle imageSend = new Circle();
                                    imageSend.setRadius(23);
                                    imageSend.setFill(new ImagePattern(iii));

                                    TextArea content = new TextArea();
                                    content.getStyleClass().addAll("send-msg-area");
                                    content.setWrapText(true);

                                    content.setMaxSize(440, 55);
                                    content.setMinSize(89, 55);
                                    content.setPrefSize(rs1.getString(1).length() * 12, rs1.getString(1).length() + 30);
                                    content.setMinHeight(content.USE_PREF_SIZE);
                                    content.setEditable(false);
                                    
                                    //المحادثة القادمة يتم التاكد ان كانت صورة يتم تحويلها من نص الى شكل صورة وعرضها
                                    if (dialog1.matches(".*.jpg") || dialog1.matches(".*.png")
                                            || dialog1.matches(".*.png") || dialog1.matches(".*.gif")
                                            || dialog1.matches(".*.jpeg")) {
                                        ImageView imageSendedd = new ImageView(rs1.getString(1));
                                        imageSendedd.setFitWidth(100);
                                        imageSendedd.setFitHeight(100);

                                        hBo.getChildren().addAll(imageSend, imageSendedd);

                                        //لعرض المحتوى ان كان صورة
                                        imageSendedd.setOnMouseClicked((evt) -> {

                                            try {
//                                       
                                                ShowImageController.urlImage = imageSendedd.getImage();
                                                showImage = FXMLLoader.load(getClass().getResource("/auther/showImage.fxml"));
                                                new JFXDialog(message, showImage, JFXDialog.DialogTransition.CENTER).show();

                                            } catch (IOException ex) {
                                                Logger.getLogger(caissiers.MessagePlatformController.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        });
                                    } else {
                                        //في حالة القادم نص وليس صورة
                                        content.setText(rs1.getString(1));

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
                                    contentArea.setPrefSize(rs1.getString(1).length() * 12, rs1.getString(1).length() + 30);
                                    contentArea.setMinHeight(contentArea.USE_PREF_SIZE);
                                    contentArea.setEditable(false);
                                    if (dialog1.matches(".*.jpg") || dialog1.matches(".*.png")
                                            || dialog1.matches(".*.png") || dialog1.matches(".*.gif")
                                            || dialog1.matches(".*.jpeg")) {
                                        ImageView imageSendedd = new ImageView(rs1.getString(1));
                                        imageSendedd.setFitWidth(100);
                                        imageSendedd.setFitHeight(100);

                                        hBox.getChildren().addAll(imageSended, imageSendedd);
                                        
                                        //لعرض المحتوى ان كان صورة
                                    imageSendedd.setOnMouseClicked((evet) -> {
                                      
                                              
                                        try {
//                                       
                                                ShowImageController.urlImage = imageSendedd.getImage();
                                                showImage = FXMLLoader.load(getClass().getResource("/auther/showImage.fxml"));
                                                new JFXDialog(message, showImage, JFXDialog.DialogTransition.CENTER).show();
                                                
                                           
                                        } catch (IOException ex) {
                                            Logger.getLogger(caissiers.MessagePlatformController.class.getName()).log(Level.SEVERE, null, ex);
                                        }                                    });

                                    } else {
                                        contentArea.setText(rs1.getString(1));

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
    
    
    public int count() {

        int count = 0;

        if (msgEntred.getText().length() == 40 || msgEntred.getText().length() > 55) {
            count = msgEntred.getText().length() + 30;
        }
        return count;
    }

     @FXML
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

    @FXML
    public void imageChooser() throws SQLException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Images (*.png, *.jpg, *.jpeg, *.gif)", "*.png", "*.jpg", "*.jpeg", "*.gif");
        fileChooser.getExtensionFilters().add(extFilter);
        File f = fileChooser.showOpenDialog(allFunction.function.root.getScene().getWindow());

        if (f == null) {
            return;
        }

        //Image image = new Image(f.toURI().toString());
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
    public void fileChooser() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Files (*.*)", "*.*");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(allFunction.function.root.getScene().getWindow());
        System.out.println(file);

    }

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
    void getCont(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

                         
                 
       ObservableList<String> Fnames = FXCollections.observableArrayList();
        ObservableList<String> Lnames = FXCollections.observableArrayList();
        ObservableList<String> images = FXCollections.observableArrayList();
        ObservableList<Integer> etats = FXCollections.observableArrayList();
        ObservableList<Integer> count = FXCollections.observableArrayList();
        ObservableList<String> type = FXCollections.observableArrayList();

        Connection con;
        PreparedStatement ps;
        ResultSet rs;

        try {
            con = dao.Dao.getConnection();

            String sql = "SELECT firstname,lastname,etat,image,id,type FROM utilisateurs WHERE id != "+UsersLoginController.id;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                type.add(rs.getString(6));
                count.add(rs.getInt(5));
                Lnames.add(rs.getString(1));
                Fnames.add(rs.getString(2));
                etats.add(rs.getInt(3));
                images.add(rs.getString(4));

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

                Image image = new Image("/imgUsers/" + images.get(i));
                Circle view = new Circle();
                view.setRadius(27);
                view.setFill(new ImagePattern(image));

                VBox vb1 = new VBox();
                HBox hbvb = new HBox();
                Label l = new Label(Fnames.get(i) + " " + Lnames.get(i));
                Label l1 = new Label(type.get(i));
                l.getStyleClass().addAll("user-name");
                Circle circle = new Circle();
                
                Label msg;
                
                if ((etats.get(i) == 1) && (type.get(i).equals("Admin"))) {
                    circle.setRadius(5);
                    circle.setFill(new Color(1, 0, 0, 1));
                    msg = new Label("Connected");
                }
                else
                
                if ((etats.get(i) == 1) && (!type.get(i).equals("Admin"))) {
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

                hb.setOnMouseClicked((MouseEvent ee) -> {
                    getId = Integer.parseInt(id.getText());
                    SendedName.setText(l.getText());
                    typeUser.setText(l1.getText());

                    messageBox.getChildren().clear();

                    Connection c;
                    PreparedStatement ps1, ps2;
                    ResultSet rs1, rs2;

                    try {
                        c = dao.Dao.getConnection();
                        //For get dialog from database 
                        String sql1 = "SELECT dialog,idExpéditeur FROM chats WHERE idExpéditeur = "
                                + UsersLoginController.id + " AND idDestinatair = " + id.getText() + " OR idExpéditeur = "
                                + id.getText() + " AND idDestinatair = " + UsersLoginController.id;
                        ps1 = c.prepareStatement(sql1);
                        rs1 = ps1.executeQuery();
                        while (rs1.next()) {
                            
                            dialog = rs1.getString(1);
                            
                            //لجلب صورة المرسل
                            String sqlIm = "SELECT image FROM utilisateurs WHERE id = " + id.getText();
                            ps2 = c.prepareStatement(sqlIm);
                            rs2 = ps2.executeQuery();

                            while (rs2.next()) {

                                dialog1 = rs1.getString(1).toLowerCase();
                                
                               //كود لجلب المحادثات المستلمة
                                if (rs1.getInt(2) == Integer.parseInt(id.getText())) {

                                    
                                    
                                    HBox hBo = new HBox();

                                    hBo.setPrefWidth(809);
                                    hBo.setMinHeight(hBo.USE_COMPUTED_SIZE);
                                    hBo.setAlignment(Pos.CENTER_LEFT);
                                    hBo.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
                                    hBo.setPadding(new Insets(0, 0, 0, 10));

                                    Image iii = new Image("/imgUsers/" + rs2.getString(1));

                                    Circle imageSend = new Circle();
                                    imageSend.setRadius(23);
                                    imageSend.setFill(new ImagePattern(iii));

                                    TextArea content = new TextArea();
                                    content.getStyleClass().addAll("send-msg-area");
                                    content.setWrapText(true);

                                    content.setMaxSize(440, 55);
                                    content.setMinSize(89, 55);
                                    content.setPrefSize(rs1.getString(1).length() * 12, rs1.getString(1).length() + 30);
                                    content.setMinHeight(content.USE_PREF_SIZE);
                                    content.setEditable(false);
                                    
                                    //المحادثة القادمة يتم التاكد ان كانت صورة يتم تحويلها من نص الى شكل صورة وعرضها
                                    if (dialog1.matches(".*.jpg") || dialog1.matches(".*.png")
                                            || dialog1.matches(".*.png") || dialog1.matches(".*.gif")
                                            || dialog1.matches(".*.jpeg")) {
                                        ImageView imageSendedd = new ImageView(rs1.getString(1));
                                        imageSendedd.setFitWidth(100);
                                        imageSendedd.setFitHeight(100);

                                        hBo.getChildren().addAll(imageSend, imageSendedd);

                                        //لعرض المحتوى ان كان صورة
                                        imageSendedd.setOnMouseClicked((event) -> {

                                            try {
//                                       
                                                ShowImageController.urlImage = imageSendedd.getImage();
                                                showImage = FXMLLoader.load(getClass().getResource("/auther/showImage.fxml"));
                                                new JFXDialog(message, showImage, JFXDialog.DialogTransition.CENTER).show();

                                            } catch (IOException ex) {
                                                Logger.getLogger(MessagePlatformController.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        });
                                        
                                    } else {
                                        //في حالة القادم نص وليس صورة
                                        content.setText(rs1.getString(1));

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
                                    contentArea.setPrefSize(rs1.getString(1).length() * 12, rs1.getString(1).length() + 30);
                                    contentArea.setMinHeight(contentArea.USE_PREF_SIZE);
                                    contentArea.setEditable(false);
                                    if (dialog1.matches(".*.jpg") || dialog1.matches(".*.png")
                                            || dialog1.matches(".*.png") || dialog1.matches(".*.gif")
                                            || dialog1.matches(".*.jpeg")) {
                                        ImageView imageSendedd = new ImageView(rs1.getString(1));
                                        imageSendedd.setFitWidth(100);
                                        imageSendedd.setFitHeight(100);

                                        hBox.getChildren().addAll(imageSended, imageSendedd);
                                        
                                        //لعرض المحتوى ان كان صورة
                                    imageSendedd.setOnMouseClicked((event) -> {
                                      
                                              
                                        try {

                                                ShowImageController.urlImage = imageSendedd.getImage();
                                                showImage = FXMLLoader.load(getClass().getResource("/auther/showImage.fxml"));
                                                new JFXDialog(message, showImage, JFXDialog.DialogTransition.CENTER).show();
                                                
                                         
                                        } catch (IOException ex) {
                                            Logger.getLogger(MessagePlatformController.class.getName()).log(Level.SEVERE, null, ex);
                                        }                                    });

                                    } else {
                                        contentArea.setText(rs1.getString(1));

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
}
