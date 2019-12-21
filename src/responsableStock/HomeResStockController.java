package responsableStock;

import allFunction.function;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.awt.color.ColorSpace;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import login.UsersLoginController;

/**
 * FXML Controller class
 *
 * @author Mido
 */
public class HomeResStockController implements Initializable {

    @FXML
    private Circle imgUser;
    
    @FXML
    private Circle imgUser1;
    
    @FXML
    private StackPane homePane;
    
    @FXML
    private AnchorPane swichPane;
    
    @FXML
    private AnchorPane addPane;
    
    @FXML
    private AnchorPane addPane2;
    
    @FXML
    private AnchorPane statisticPlatform;
    
    @FXML
    private AnchorPane listeProductPane;
    
    @FXML
    private StackPane fourniserPlatform;
    
    @FXML
    private StackPane message;
    
    @FXML
    private JFXHamburger menu;
    @FXML
    private Pane popUpPane;
    @FXML
    private Pane updatePopUpPane;
    @FXML
    private StackPane rootPane;
    
    @FXML
    private FontAwesomeIconView riningIcon;
    
    boolean isShowed = false;
    boolean isShowed2 = false;
    
    @FXML
    private Text flname2;
    
    boolean isShow = true;
    private static JFXPopup staticJFXPopup; 
    private static JFXPopup staticJFXPopupList; 
    
    
    
     
     @FXML
     private void exit(){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Close");
        alert.setHeaderText("!!");
        alert.setContentText("are you shur?");
        
        alert.setOnCloseRequest(e->{
            ButtonType res = alert.getResult();
            if(res == ButtonType.OK){
                
                try {
                    function f = new function();
                    f.exit();
                } catch (SQLException ex) {
                    Logger.getLogger(HomeResStockController.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
             });
        
        alert.showAndWait();
    }
     
     @FXML
    private void drawe(){//For Show List Menu
         
         HamburgerSlideCloseTransition hsct = new HamburgerSlideCloseTransition(menu);
         
         hsct.setRate(-1);
        
             menu.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, (e)->{
                 hsct.setRate(-1);
                 hsct.play();
                 
             });
             
        JFXPopup fXPopup = new JFXPopup();
        fXPopup.setPopupContent(popUpPane);
        fXPopup.show(rootPane, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT, 0, 0);
        staticJFXPopup = fXPopup;
        hsct.play();
        
     }
 @FXML
    private void draweList(){//For Show List Menu
         
        JFXPopup fXPopup = new JFXPopup();
        fXPopup.setPopupContent(updatePopUpPane);
        fXPopup.show(rootPane, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT, 290, 285);
        staticJFXPopupList = fXPopup;
      
        
     }
   
     
    @FXML
    private void addGeneralQuantity(){
        
        try {
            
           addPane = FXMLLoader.load(getClass().getResource("/responsableStock/addGeneralQuantity.fxml"));
         //  if(isShowed  == false){
           new JFXDialog(rootPane, addPane, JFXDialog.DialogTransition.CENTER).show();
          // isShowed = true;
           //}
           
           
        } catch (IOException ex) {
            Logger.getLogger(HomeResStockController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void addQuantity(){
        
        try {
            
           addPane2 = FXMLLoader.load(getClass().getResource("/responsableStock/addQuantity.fxml"));
        //   if(isShowed2  == false){
           new JFXDialog(rootPane, addPane2, JFXDialog.DialogTransition.CENTER).show();
         //  isShowed2 = true;
         //  }
           
           
        } catch (IOException ex) {
            Logger.getLogger(HomeResStockController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Image image = new Image("/imgUsers/"+UsersLoginController.img);
                imgUser.setFill(new ImagePattern(image));
                Image image1 = new Image("/imgUsers/"+UsersLoginController.img);
                imgUser1.setFill(new ImagePattern(image1));
        
        try {
            
            
            homePane          = FXMLLoader.load(getClass().getResource("/responsableStock/homeStockPlatform.fxml"));
            listeProductPane  = FXMLLoader.load(getClass().getResource("/responsableComercial/listProduct.fxml"));
            statisticPlatform = FXMLLoader.load(getClass().getResource("/responsableStock/statisticResStockPlatform.fxml"));
            fourniserPlatform = FXMLLoader.load(getClass().getResource("/responsableStock/fourniseur.fxml"));
            message           = FXMLLoader.load(getClass().getResource("/responsableStock/message.fxml"));
            setNode(homePane);
            
        } catch (IOException ex) {
            Logger.getLogger(HomeResStockController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        riningIcon.setFill(StatisticResStockPlatformController.c);
        flname2.setText(UsersLoginController.firstName +" "+UsersLoginController.lastName);
    }    
    
    private void setNode(Node node){
        
        swichPane.getChildren().clear();
        swichPane.getChildren().add(node);
        
        FadeTransition ft = new FadeTransition(Duration.millis(1500));//pour afficher avec animation
        
            ft.setNode(node);
            ft.setFromValue(0.1);
            ft.setToValue(1);
            ft.setCycleCount(1);
            ft.setAutoReverse(false);
            ft.play();
    }
    
    @FXML
    private void home(){
        
        setNode(homePane);
    }
    
    @FXML
    private void listeProduct(){
        
        setNode(listeProductPane);
    }
    
    @FXML
    private void Statistic(){
        
        setNode(statisticPlatform);
    }
    @FXML
    private void Fournisseur(){
        
        setNode(fourniserPlatform);
    }
    
    @FXML
    private void Message(){
        
        setNode(message);
    }
}








