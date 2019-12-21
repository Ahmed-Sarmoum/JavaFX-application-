package caissiers;

import allFunction.function;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXPopup;
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
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.ENTER;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import login.LoginController;
import login.UsersLoginController;
import org.controlsfx.control.Notifications;
import responsableStock.HomeStockPlatformController;

/**
 * FXML Controller class
 *
 * @author Mido
 */
public class HomeCashierController implements Initializable {

    public static String nameChoosed = null;
    
    @FXML
    private StackPane root;
    @FXML
    private HBox btnRefresh;
    
    @FXML
    private JFXTextField search;
    
    @FXML
    private AnchorPane dashboardPlatform;
    
    @FXML
    private StackPane rootPane;
    
    @FXML
    private StackPane rootSubscrib;

    @FXML
    private AnchorPane switchedPane;
    
    @FXML
    private AnchorPane listeProductPane;
    
    @FXML
    private AnchorPane calculatorPane;
    
    @FXML
    private StackPane invoicePlatform;
    
    @FXML
    private StackPane message;
    
    @FXML
    private HBox logout;
       
    @FXML
    private Pane popUpPane;
    
    private static JFXPopup staticJFXPopup;
    
    @FXML
    private Text name1;

    @FXML
    private Text name2;
    
    @FXML
    private AnchorPane ventPlatform;
    
    @FXML
    private Circle imgUser;

    Notifications n;
    
    @FXML
    private void showList(){
        
        JFXPopup fXPopup = new JFXPopup();
        fXPopup.setPopupContent(popUpPane);
        fXPopup.show(rootPane, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT, -10,-20);
        staticJFXPopup = fXPopup;
    }

    @FXML
    void exite(MouseEvent event) {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Close");
        alert.setHeaderText("!!");
        alert.setContentText("are you shur?");
        
        alert.setOnCloseRequest(e->{
            ButtonType res = alert.getResult();
            if(res == ButtonType.OK){
                
                function f = new function();
                try {
                    f.exit();
                } catch (SQLException ex) {
                    Logger.getLogger(HomeCashierController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                }
             });
        
        alert.showAndWait();
        
        VentPlatformController.namep.clear();
        VentPlatformController.priceProd.clear();
        VentPlatformController.totalProdVent.clear();
        VentPlatformController.quantProd.clear();
       
    }
    
    @FXML
    void refresh(MouseEvent event) {

          
            Parent root = null;
            
            
              
               Stage stage = (Stage)btnRefresh.getScene().getWindow();
              try {
               root = FXMLLoader.load(getClass().getResource("/caissiers/homeCashier.fxml"));
                
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
                Scene s = new Scene(root);
                stage.setScene(s);
                s.getStylesheets().add(getClass().getResource("/styleCss/chat.css").toExternalForm());
                stage.show();
                stage.setResizable(false);
    }
    
    @FXML
    private void search(){
        nameChoosed = null;
        Connection con;
        PreparedStatement ps,ps1;
        ResultSet rs,rs1;
        try{
            con = dao.Dao.getConnection();
        String sql = "SELECT idCatég FROM produits WHERE nom = '"+search.getText()+"'";
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        if(rs.next()){
            String sql1 = "SELECT nom FROM catégories WHERE id = "+rs.getInt(1)+" AND status = 'Active'";
            ps1 = con.prepareStatement(sql1);
            rs1 = ps1.executeQuery();
            while(rs1.next()){
                
                nameChoosed = rs1.getString(1);
                
            }
            Image img = new Image("/img/chek.png");
        n = n.create().title("Confemation").
                text(nameChoosed +" Is Choosed").
                graphic(new ImageView(img)).
                hideAfter(Duration.seconds(3)).
                position(Pos.TOP_CENTER).darkStyle();
        n.show();
        
           DashboardPlatformController.l = nameChoosed;
                    try {
                        ventPlatform = FXMLLoader.load(getClass().getResource("/caissiers/ventPlatform.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(DashboardPlatformController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    new JFXDialog(rootPane, ventPlatform, JFXDialog.DialogTransition.CENTER).show();
              
        
        }else{
             Image img = new Image("/img/remove.png");
        n = n.create().title("WORNING").
                text("This name product is incorect or inconnu").
                graphic(new ImageView(img)).
                hideAfter(Duration.seconds(5)).
                position(Pos.TOP_CENTER).darkStyle();
        n.show();
        }
         
           }catch(SQLException|ClassNotFoundException e){
                 
                  
                }
       
        
    }

    @FXML
    void logoute() throws IOException, SQLException {
               
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("log out");
        alert.setHeaderText("!!");
        alert.setContentText("are you shur?");
        
        alert.setOnCloseRequest(e->{
            ButtonType res = alert.getResult();
            if(res == ButtonType.OK){
                
                function f = new function();
                try {
                    
                 f.logOut(logout);
                    
                } catch (SQLException ex) {
                    Logger.getLogger(HomeStockPlatformController.class.getName()).log(Level.SEVERE, null, ex);
                }
    
            
            }else{
                
            }
        });
        
        alert.showAndWait();
        
             
       
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        
//        root.setOnKeyPressed(event -> { 
//            
//            if (event.getCode().equals(ENTER)) {
// 
//                    search();
//            
//            }
//        });
        
        Image image = new Image("/imgUsers/"+UsersLoginController.img);
                imgUser.setFill(new ImagePattern(image));
        
        try {
            dashboardPlatform = FXMLLoader.load(getClass().getResource("/caissiers/dashboardPlatform.fxml"));
            listeProductPane = FXMLLoader.load(getClass().getResource("/responsableComercial/listProduct.fxml"));
            calculatorPane = FXMLLoader.load(getClass().getResource("/caissiers/calculator.fxml"));
            message = FXMLLoader.load(getClass().getResource("/caissiers/messagePlatform.fxml"));
            rootSubscrib = FXMLLoader.load(getClass().getResource("/caissiers/subscriber.fxml"));
            setNode(dashboardPlatform);
            
        } catch (IOException ex) {
            Logger.getLogger(HomeCashierController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        name1.setText(UsersLoginController.firstName+" "+UsersLoginController.lastName);
        name2.setText(UsersLoginController.firstName+" "+UsersLoginController.lastName);
    }

       
    private void setNode(Node node){
        
        switchedPane.getChildren().clear();
        switchedPane.getChildren().add(node);
        
        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        
        ft.setNode(node);
            ft.setFromValue(0.1);
            ft.setToValue(1);
            ft.setCycleCount(1);
            ft.setAutoReverse(false);
            ft.play();
    }
    
    @FXML
    private void dashboard(){
        setNode(dashboardPlatform);
    }
    @FXML
    private void products(){
        setNode(listeProductPane);
    }
    @FXML
    private void calculator(){
        setNode(calculatorPane);
    }
    
    @FXML
    private void message(){
        
        setNode(message);
    }
    @FXML
    private void subscriber(){
        
        setNode(rootSubscrib);
    }
    
}













