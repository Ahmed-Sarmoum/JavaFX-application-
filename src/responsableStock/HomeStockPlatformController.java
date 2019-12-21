package responsableStock;

import allFunction.function;
import login.LoginController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import vo.quantityVo;
import vo.stockVo;

/**
 * FXML Controller class
 *
 * @author Mido
 */
public class HomeStockPlatformController implements Initializable {

    public static int isSelect;
    public static int isSelect1;
    public static quantityVo isSelected;
    public static stockVo isSelected2;
    
    @FXML
    private TableView<quantityVo> tabViewGQ;
    @FXML
    private TableView<stockVo> tabViewQ;
    @FXML
    private TableColumn<quantityVo, Integer> idGQ;
    
    @FXML
    private JFXButton refresh;

    @FXML
    private TableColumn<quantityVo, String> productGQ;

    @FXML
    private TableColumn<quantityVo, Integer> qMinS;

    @FXML
    private TableColumn<quantityVo, Integer> qMinR;

    @FXML
    private TableColumn<quantityVo, Integer> qMax;

    @FXML
    private TableColumn<stockVo, Integer> idQ;

    @FXML
    private TableColumn<stockVo, String> productQ;

    @FXML
    private TableColumn<stockVo, Integer> stockQ;

    @FXML
    private TableColumn<stockVo, Integer> rayonQ;
    
     @FXML
    private TableColumn<stockVo, String> dateD;

    @FXML
    private TableColumn<stockVo, String> dateF;
    
    @FXML
    private Label date;
    
    @FXML
    private JFXTextField search1;

    @FXML
    private JFXTextField search2;
    
    @FXML
    private StackPane upDePlatform;
    
    @FXML
    private StackPane upDePlatform2;
    
    @FXML
    private AnchorPane swichPane;
    
    @FXML
    private StackPane fourniserPlatform;
    
    @FXML
    private StackPane homePane;
    
    @FXML
    private StackPane rootPane;
    
    @FXML
    private Label time;
    
    @FXML
    private JFXButton logout;
    
    ObservableList<stockVo> listQ = FXCollections.observableArrayList();
    ObservableList<quantityVo> listGQ = FXCollections.observableArrayList();
    
    
    @FXML
    private void searchGQ() { //Search in General Quantity Table

        search1.textProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if (search1.textProperty().get().isEmpty()) {
                    tabViewGQ.setItems(listGQ);
                    return;
                }
                ObservableList<quantityVo> items = FXCollections.observableArrayList();
                ObservableList<TableColumn<quantityVo, ?>> column = tabViewGQ.getColumns();

                for (int row = 0; row < listGQ.size(); row++) {
                    for (int col = 0; col < column.size(); col++) {
                        TableColumn tabVar = column.get(col);
                        String cellData = tabVar.getCellData(listGQ.get(row)).toString();
                        cellData = cellData.toLowerCase();

                        if (cellData.contains(search1.getText().toLowerCase()) && cellData.startsWith(search1.getText().toLowerCase())) {
                            items.add(listGQ.get(row));
                            break;
                        }
                    }

                }
                tabViewGQ.setItems(items);
            }
        });
    }
    
    @FXML
    private void searchQ() { //Search in Quantity Table

        search2.textProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if (search2.textProperty().get().isEmpty()) {
                    tabViewQ.setItems(listQ);
                    return;
                }
                ObservableList<stockVo> items = FXCollections.observableArrayList();
                ObservableList<TableColumn<stockVo, ?>> column = tabViewQ.getColumns();

                for (int row = 0; row < listQ.size(); row++) {
                    for (int col = 0; col < column.size(); col++) {
                        TableColumn tabVar = column.get(col);
                        String cellData = tabVar.getCellData(listQ.get(row)).toString();
                        cellData = cellData.toLowerCase();

                        if (cellData.contains(search2.getText().toLowerCase()) && cellData.startsWith(search2.getText().toLowerCase())) {
                            items.add(listQ.get(row));
                            break;
                        }
                    }

                }
                tabViewQ.setItems(items);
            }
        });
    }
    
    
    
    @FXML
    void logOut() {

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
    
    @FXML
    private void selectItems(){
        
        
    }
    @FXML
    private void showData(){
        
         try {
            
             isSelected = tabViewGQ.getSelectionModel().getSelectedItem();
          
             isSelect = isSelected.getId();
            upDePlatform = FXMLLoader.load(getClass().getResource("/responsableStock/updateAndDelete.fxml"));
            
            new JFXDialog(homePane, upDePlatform, JFXDialog.DialogTransition.CENTER).show();
            
        } catch (IOException ex) {
            Logger.getLogger(HomeStockPlatformController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @FXML
    private void refresh(){
        
        listGQ.clear();
        listQ.clear();
        try {
            PreparedStatement ps = null,ps2 = null,ps3 = null,ps4 = null;
            ResultSet rs = null,rs2 = null,rs3 = null,rs4 = null;
            Connection con = null;
            
            con = dao.Dao.getConnection();
            
            String sql1 = "SELECT id,qunt,idProd ,dateD,dateF FROM stocks ";
            String sql3 = "SELECT * FROM quntité ";
            
            ps3 = con.prepareStatement(sql3);
            rs3 = ps3.executeQuery();
            
            ps = con.prepareStatement(sql1);
            rs = ps.executeQuery();
            
            while(rs.next() && rs3.next()){
                
                String sql2 = "SELECT nom,quntité FROM produits WHERE id = "+rs.getInt(3);
                String sql4 = "SELECT nom FROM produits WHERE id = "+rs3.getInt(2);
                
                ps4 = con.prepareStatement(sql4);
                rs4 = ps4.executeQuery();
                
                ps2 = con.prepareStatement(sql2);
                rs2 = ps2.executeQuery();
                
                while(rs2.next() && rs4.next()){
                
                listQ.add(new stockVo(rs.getInt(1), rs.getInt(2),
                        rs2.getString(1), rs2.getInt(2),rs.getString(4),rs.getString(5)));
                
                listGQ.add(new quantityVo(rs3.getInt(1), rs4.getString(1), rs3.getInt(3)
                        , rs3.getInt(4), rs3.getInt(5)));
                
                }
            }
            
            
            
        } catch (ClassNotFoundException|SQLException ex) {
            Logger.getLogger(HomeResStockController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
            idQ.setCellValueFactory(new PropertyValueFactory<stockVo,Integer>("id"));
            productQ.setCellValueFactory(new PropertyValueFactory<stockVo,String>("prodectQ"));
            stockQ.setCellValueFactory(new PropertyValueFactory<stockVo,Integer>("quantity"));
            rayonQ.setCellValueFactory(new PropertyValueFactory<stockVo,Integer>("idRayon"));
            dateD.setCellValueFactory(new PropertyValueFactory<stockVo,String>("dateD"));
            dateF.setCellValueFactory(new PropertyValueFactory<stockVo,String>("dateF"));
            
            idGQ.setCellValueFactory(new PropertyValueFactory<quantityVo,Integer>("id"));
            productGQ.setCellValueFactory(new PropertyValueFactory<quantityVo,String>("productGQ"));
            qMinS.setCellValueFactory(new PropertyValueFactory<quantityVo,Integer>("quantMinStock"));
            qMinR.setCellValueFactory(new PropertyValueFactory<quantityVo,Integer>("quantMinRayon"));
            qMax.setCellValueFactory(new PropertyValueFactory<quantityVo,Integer>("quantMax"));
            
            tabViewQ.setItems(listQ);
            tabViewGQ.setItems(listGQ);
        
//          Stage stage =null;
//            Parent root = null;
//            
//            try {
//                stage = (Stage) refresh.getScene().getWindow();
//                root = FXMLLoader.load(getClass().getResource("/responsableStock/homeResStock.fxml"));
//                
//            } catch (IOException ex) {
//                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            
//                Scene s = new Scene(root);
//                stage.setTitle("Stock Responsible");
//                stage.setScene(s);
//                s.getStylesheets().add(getClass().getResource("/styleCss/chat.css").toExternalForm());
//                stage.show();
//                stage.setResizable(false);
    }
    
     @FXML
    private void showData2(){
        
         try {
            
             isSelected2 = tabViewQ.getSelectionModel().getSelectedItem();
          isSelect1 = isSelected2.getId();
            upDePlatform2 = FXMLLoader.load(getClass().getResource("/responsableStock/updateAndDelete2.fxml"));
            
            new JFXDialog(homePane, upDePlatform2, JFXDialog.DialogTransition.CENTER).show();
            
        } catch (IOException ex) {
            Logger.getLogger(HomeStockPlatformController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
//    private void setNode(Node node){
//        
//        swichPane.getChildren().clear();
//        swichPane.getChildren().add(node);
//        
//        FadeTransition ft = new FadeTransition(Duration.millis(1500));//pour afficher avec animation
//        
//            ft.setNode(node);
//            ft.setFromValue(0.1);
//            ft.setToValue(1);
//            ft.setCycleCount(1);
//            ft.setAutoReverse(false);
//            ft.play();
//    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
//        try {
//            
//            
//            upDePlatform = FXMLLoader.load(getClass().getResource("/javaFXML/updateAndDelete.fmxl"));
//            
//            
//        } catch (IOException ex) {
//            Logger.getLogger(HomeStockPlatformController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        
        
        
        try {
            PreparedStatement ps = null,ps2 = null,ps3 = null,ps4 = null;
            ResultSet rs = null,rs2 = null,rs3 = null,rs4 = null;
            Connection con = null;
            
            con = dao.Dao.getConnection();
            
            String sql1 = "SELECT id,qunt,idProd,dateD,dateF FROM stocks ";
            String sql3 = "SELECT * FROM quntité ";
            
            ps3 = con.prepareStatement(sql3);
            rs3 = ps3.executeQuery();
            
            ps = con.prepareStatement(sql1);
            rs = ps.executeQuery();
            
            while(rs.next() && rs3.next()){
                
                String sql2 = "SELECT nom,quntité FROM produits WHERE id = "+rs.getInt(3);
                String sql4 = "SELECT nom FROM produits WHERE id = "+rs3.getInt(2);
                
                ps4 = con.prepareStatement(sql4);
                rs4 = ps4.executeQuery();
                
                ps2 = con.prepareStatement(sql2);
                rs2 = ps2.executeQuery();
                
                while(rs2.next() && rs4.next()){
                
                listQ.add(new stockVo(rs.getInt(1), rs.getInt(2),
                        rs2.getString(1), rs2.getInt(2),rs.getString(4),rs.getString(5)));
                
                listGQ.add(new quantityVo(rs3.getInt(1), rs4.getString(1), rs3.getInt(3)
                        , rs3.getInt(4), rs3.getInt(5)));

                }
            }
            
            
            
        } catch (ClassNotFoundException|SQLException ex) {
            Logger.getLogger(HomeResStockController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
            idQ.setCellValueFactory(new PropertyValueFactory<stockVo,Integer>("id"));
            productQ.setCellValueFactory(new PropertyValueFactory<stockVo,String>("prodectQ"));
            stockQ.setCellValueFactory(new PropertyValueFactory<stockVo,Integer>("quantity"));
            rayonQ.setCellValueFactory(new PropertyValueFactory<stockVo,Integer>("idRayon"));
            dateD.setCellValueFactory(new PropertyValueFactory<stockVo,String>("dateD"));
            dateF.setCellValueFactory(new PropertyValueFactory<stockVo,String>("dateF"));
            
            idGQ.setCellValueFactory(new PropertyValueFactory<quantityVo,Integer>("id"));
            productGQ.setCellValueFactory(new PropertyValueFactory<quantityVo,String>("productGQ"));
            qMinS.setCellValueFactory(new PropertyValueFactory<quantityVo,Integer>("quantMinStock"));
            qMinR.setCellValueFactory(new PropertyValueFactory<quantityVo,Integer>("quantMinRayon"));
            qMax.setCellValueFactory(new PropertyValueFactory<quantityVo,Integer>("quantMax"));
            
            tabViewQ.setItems(listQ);
            tabViewGQ.setItems(listGQ);
            
            
                     /**************time****************/
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {            
            Calendar cal = Calendar.getInstance();
            int second = cal.get(Calendar.SECOND);
            int minute = cal.get(Calendar.MINUTE);
            int hour = cal.get(Calendar.HOUR);
            time.setText(((hour<10)?"0" : "") + hour + ":" + ((minute<10)?"0" : "") + minute + ":" + ((second<10)?"0" : "") + second);
            }),
             new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
       
        clock.play();
        
        date.setText(String.valueOf(LocalDate.now()));
    
        
        
    }    
    
}
