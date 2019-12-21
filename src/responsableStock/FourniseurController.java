package responsableStock;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
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
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.text.html.Option;
import login.LoginController;
import org.controlsfx.control.Notifications;
import vo.providerVo;
import vo.quantityVo;

/**
 * FXML Controller class
 *
 * @author Mido
 */
public class FourniseurController implements Initializable {

    public static providerVo itemSelected;
    public static int idF;
    public static int deleteP ;
    

    @FXML
    private AnchorPane updateProvider;
    @FXML
    private JFXButton deleteBtn;
    @FXML
    private StackPane fourniserPlatform;
    
    @FXML
    private StackPane addUpdateFournisseur;
    
    @FXML
    private StackPane contactFourniseur;
   
    @FXML
    private Label nameProvider;

    @FXML
    private Label location2;

    @FXML
    private Label nameCategory2;

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
    private Label note;

    @FXML
    private TableView<providerVo> tabView;

    @FXML
    private TableColumn<providerVo, Integer> id;

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
    
    Notifications notification;
    
    @FXML
    private JFXTextField search;
    
    ObservableList<providerVo> list = FXCollections.observableArrayList();
    
    @FXML
    private void addUpdateFournisseur() throws IOException{
  
    addUpdateFournisseur = FXMLLoader.load(getClass().getResource("/responsableStock/addUpdateFournisseur.fxml"));
    
    new JFXDialog(fourniserPlatform, addUpdateFournisseur, JFXDialog.DialogTransition.CENTER).show();
            
    }
    
    @FXML
    private void orderFournisseur() throws IOException{
        
    contactFourniseur = FXMLLoader.load(getClass().getResource("/responsableStock/orderFournisseur.fxml"));
    
    new JFXDialog(fourniserPlatform, contactFourniseur, JFXDialog.DialogTransition.CENTER).show();
            
    }
    
    @FXML
    private void updateFournisseur() throws IOException{
        
    updateProvider = FXMLLoader.load(getClass().getResource("/responsableStock/updateFournisseur.fxml"));
    
    new JFXDialog(fourniserPlatform, updateProvider, JFXDialog.DialogTransition.CENTER).show();
            
    }
    
    @FXML
    void search(MouseEvent event) {

        search.textProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if (search.textProperty().get().isEmpty()) {
                    tabView.setItems(list);
                    return;
                }
                ObservableList<providerVo> items = FXCollections.observableArrayList();
                ObservableList<TableColumn<providerVo, ?>> column = tabView.getColumns();

                for (int row = 0; row < list.size(); row++) {
                    for (int col = 0; col < column.size(); col++) {
                        TableColumn tabVar = column.get(col);
                        String cellData = tabVar.getCellData(list.get(row)).toString();
                        cellData = cellData.toLowerCase();

                        if (cellData.contains(search.getText().toLowerCase()) && cellData.startsWith(search.getText().toLowerCase())) {
                            items.add(list.get(row));
                            break;
                        }
                    }

                }
                tabView.setItems(items);
            }
        });
    
    }
    
     @FXML
    void refresh(MouseEvent event) {

        list.clear();
        dataProviderTable();
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        dataProviderTable();
       
        
    }    
    
    private void dataProviderTable(){
        
         Connection con ;
        PreparedStatement ps1,ps2;
        ResultSet rs1,rs2;
        
        try{
            con = dao.Dao.getConnection();
            String sql1= "SELECT * FROM fournisseur";
            ps1 = con.prepareStatement(sql1);
            rs1 = ps1.executeQuery();
            
            while(rs1.next()){
                
                String sql2 = "SELECT nom FROM catégories WHERE id = "+rs1.getInt(6);
                ps2 = con.prepareStatement(sql2);
                rs2 = ps2.executeQuery();
                
                while(rs2.next()){
                    
                    list.add(new providerVo(rs1.getInt(1), rs1.getString(2), rs1.getString(3)
                            , rs1.getString(4), rs1.getString(5), rs2.getString(1),
                            rs1.getDouble(8), rs1.getString(9),rs1.getString(10)));
                    
                    
                }
            
            }
            
            
            
        }catch(SQLException|ClassNotFoundException e){
            System.out.println(e.getMessage());
            
        }
            id.setCellValueFactory(new PropertyValueFactory<providerVo,Integer>("id"));
            firstnameT.setCellValueFactory(new PropertyValueFactory<providerVo,String>("firstname"));
            lastnameT.setCellValueFactory(new PropertyValueFactory<providerVo,String>("lastnaame"));
            phoneT.setCellValueFactory(new PropertyValueFactory<providerVo,String>("phone"));
            locationT.setCellValueFactory(new PropertyValueFactory<providerVo,String>("location"));
            noteT.setCellValueFactory(new PropertyValueFactory<providerVo,String>("note"));
            categoryT.setCellValueFactory(new PropertyValueFactory<providerVo,String>("category"));
            cridéT.setCellValueFactory(new PropertyValueFactory<providerVo,Double>("cridé"));
            emailT.setCellValueFactory(new PropertyValueFactory<providerVo,String>("email"));

            tabView.setItems(list);
    }
    
    @FXML
    private void deleteProvider() throws SQLException{

        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("CONFIRMATION");
        a.setHeaderText("???");
        a.setContentText("Are you shur ?");
        
        a.setOnCloseRequest((event) -> {
            
            
                ButtonType res = a.getResult();
                if(res == ButtonType.OK){
                try {
                deleteP = dao.providerDao.getInstance().deleteProvider(itemSelected);
        
                } catch (SQLException ex) {
                Logger.getLogger(FourniseurController.class.getName()).log(Level.SEVERE, null, ex);
            }
                }
            
        });
        a.showAndWait();
        
        
        
        if(deleteP > 0){
            Image image  = new Image("/img/chek.png");
            notification = notification.create().title("Delete Provider").
                    text("Delete Successfully").
                    graphic(new ImageView(image)).
                    hideAfter(Duration.seconds(3)).
                    position(Pos.TOP_CENTER).darkStyle();
            notification.show();
          
            list.clear();
            dataProviderTable();
        }else{
            System.out.println("ERROR");
        }
    }
    
    @FXML
    private void getDataFromTable(){
        
        itemSelected = tabView.getSelectionModel().getSelectedItem();

        nameCategory.setText(itemSelected.getCategory());
        nameCategory2.setText(itemSelected.getCategory());
        location.setText(itemSelected.getLocation());
        location2.setText(itemSelected.getLocation());
        note.setText(itemSelected.getNote());
        nameProvider.setText(itemSelected.getFirstname()+" "+ itemSelected.getLastnaame());
        phone.setText(itemSelected.getPhone());
        email.setText(itemSelected.getEmail());
        cridé.setText(String.valueOf(itemSelected.getCridé()));
        

    }
}







