package responsableComercial;

import allFunction.function;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import login.UsersLoginController;
import vo.userVo;

/**
 * FXML Controller class
 *
 * @author Mido
 */
public class HomeResCommercialController implements Initializable {

    @FXML
    private Circle imgUser;
    @FXML
    private Circle imgUser2;
    
    @FXML
    private StackPane stackCateg;
    
    @FXML
    private StackPane stackRayon;
    
    @FXML
    private AnchorPane home;
    
    @FXML
    private StackPane produit;
    
    @FXML
    private StackPane stackSales;
    
    @FXML
    private StackPane stackMessage;
    
    @FXML
    private StackPane stackStatistic;
    
    @FXML
    private StackPane stackSwitch;

    @FXML
    private AnchorPane switched;
    
    @FXML
    private HBox boxLogOut;
    
    @FXML
    private Label flname1;
    
    
    

    @FXML
    private Text flname2;
    
    public ObservableList<userVo> date = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Image image = new Image("/imgUsers/"+UsersLoginController.img);
                imgUser.setFill(new ImagePattern(image));
                imgUser2.setFill(new ImagePattern(image));
        
        try{
            
           
            
            home = FXMLLoader.load(getClass().getResource("/responsableComercial/homePlatform.fxml"));
            stackCateg = FXMLLoader.load(getClass().getResource("/responsableComercial/categPlatform.fxml"));
            stackRayon = FXMLLoader.load(getClass().getResource("/responsableComercial/fournisseurPlatform.fxml"));
            produit = FXMLLoader.load(getClass().getResource("/responsableComercial/produitPlatform.fxml"));
            stackSales = FXMLLoader.load(getClass().getResource("/responsableComercial/sales.fxml"));
            stackMessage = FXMLLoader.load(getClass().getResource("/responsableComercial/messagePlatform.fxml"));
            stackStatistic  = FXMLLoader.load(getClass().getResource("/responsableComercial/statisticPlatform.fxml"));
            
            setNode(home);
            
        }catch(IOException e){
            
        }
        
        flname1.setText(UsersLoginController.firstName +" "+UsersLoginController.lastName);
        flname2.setText(UsersLoginController.firstName +" "+UsersLoginController.lastName);
    }    
    
    public void setNode(Node node){
        
        switched.getChildren().clear();
        switched.getChildren().add(node);
        
        FadeTransition ft = new FadeTransition(Duration.millis(1500));//pour afficher avec animation
        
            ft.setNode(node);
            ft.setFromValue(0.1);
            ft.setToValue(1);
            ft.setCycleCount(1);
            ft.setAutoReverse(false);
            ft.play();
    }
    
    @FXML
    private void home() {//Function For Get Add New Users Node
   
 
        setNode(home);
        
    }
    @FXML
    private void categ() {//Function For Get Category Node
      
        setNode(stackCateg);
    }
    
    @FXML
    private void rayon() {//Function For Get Rayon Node
      
        setNode(stackRayon);
    }
    
    @FXML
    private void produit() {//Function For Get Produit Node
      
        setNode(produit);
    }
    
    @FXML
    private void sales() {//Function For Get Sales Node
      
        setNode(stackSales);
    }
    
    @FXML
    private void message() {//Function For Get Expenses Node
      
        setNode(stackMessage);
    }
    
    @FXML
    private void statistic() {//Function For Get Statistic Node
      
        setNode(stackStatistic);
    }
    
    @FXML
    private void logOut() throws SQLException {

        function f = new function();
        f.exit();
    }

    
}
