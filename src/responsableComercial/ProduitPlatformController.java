
package responsableComercial;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextArea;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonType;

import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import vo.prodectVo;

/**
 * FXML Controller class
 *
 * @author Mido
 */
public class ProduitPlatformController implements Initializable {

    public static String[] category;
    public static String[] rayon;
    public static prodectVo isSelect;

    @FXML
    private TableView<prodectVo> tabView;

    @FXML
    private TableColumn<prodectVo, Integer> idP;

    @FXML
    private TableColumn<prodectVo, String> nameP;

    @FXML
    private TableColumn<prodectVo, String> descP;

    @FXML
    private TableColumn<prodectVo, Float> priceP;
    
    @FXML
    private TableColumn<prodectVo, Float> pricePInit;
    
    @FXML
    private TableColumn<prodectVo, Integer> quntP;

    @FXML
    private TableColumn<prodectVo, String> categoryP;

    @FXML
    private TableColumn<prodectVo, String> rayonP;

    ObservableList<prodectVo> list = FXCollections.observableArrayList();

    @FXML
    private JFXTextField Tname;

    @FXML
    private JFXTextField Tprice;
    
    @FXML
    private JFXTextField TpriceInit;
    
    @FXML
    private JFXTextField Tqunt;

    @FXML
    private JFXTextField Tcateg;

    @FXML
    private JFXTextField Trayon;

    @FXML
    private JFXTextArea Tdesc;

    @FXML
    private JFXTextField search;
    
    @FXML
    private AnchorPane addProdectPlatform;
    @FXML
    private AnchorPane updateProdectPlatform;
    @FXML
    private StackPane prodectContainer;

   public static Notifications  notification;

    @FXML
    private void addProdect(ActionEvent e) {

        try {
            addProdectPlatform = FXMLLoader.load(getClass().getResource("/responsableComercial/addProdect.fxml"));

            new JFXDialog(prodectContainer, addProdectPlatform, JFXDialog.DialogTransition.TOP).show();

        } catch (IOException ex) {
            Logger.getLogger(ProduitPlatformController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void UpdateProdect(ActionEvent e) {

        try {
            updateProdectPlatform = FXMLLoader.load(getClass().getResource("/responsableComercial/updateProdect.fxml"));

            new JFXDialog(prodectContainer, updateProdectPlatform, JFXDialog.DialogTransition.TOP).show();

        } catch (IOException ex) {
            Logger.getLogger(ProduitPlatformController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    public void deleteProdect(ActionEvent ee) throws SQLException {

        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Exit");
        a.setHeaderText("!!");
        a.setContentText("are you shur?");

        a.setOnCloseRequest(e -> {
            ButtonType res = a.getResult();
            if (res == ButtonType.OK) {
                try {
                  prodectVo  isS = tabView.getSelectionModel().getSelectedItem();

                    int isDelete = dao.prodectDao.getInstence().deleteProdect(isS);

                    if (isDelete > 0) {

                        // afficher si l'Delete success
                        Image img = new Image("/img/chek.png");
                         notification = notification.create().title("Delete Prodect").
                                text("Delete Successfully").
                                graphic(new ImageView(img)).
                                hideAfter(Duration.seconds(2)).
                                position(Pos.CENTER).darkStyle();
                        notification.show();
                        
                        list.clear();
                        
                        dataProdectTable();
                    } else {
                        //Sinon
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("ERROR");
                        alert.setHeaderText("!!");
                        alert.setContentText("ERROR To Delete ...!!");

                        alert.showAndWait();
                        return;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(CategPlatformController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {

            }
        });

        a.showAndWait();

    }

    @FXML
    private void showData() {

        isSelect = tabView.getSelectionModel().getSelectedItem();

        Tname.setText(isSelect.getNameP());
        Tdesc.setText(isSelect.getDescription());
        String f1 = String.valueOf(isSelect.getPriceP());
        Tprice.setText(f1);
        String f2 = String.valueOf(isSelect.getPricePInitial());
        TpriceInit.setText(f2);
        String q = String.valueOf(isSelect.getQuntInRay());
        Tqunt.setText(q);
        Tcateg.setText(isSelect.getCategory());
        Trayon.setText(isSelect.getRayon());
    }

    @FXML
    private void search() { //Search in prodect Table

        search.textProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if (search.textProperty().get().isEmpty()) {
                    tabView.setItems(list);
                    return;
                }
                ObservableList<prodectVo> items = FXCollections.observableArrayList();
                ObservableList<TableColumn<prodectVo, ?>> column = tabView.getColumns();

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
    
    //Function for show Prodect Statistic Form BarChar
    @FXML
    private void barCharStatistic(){
        
        
    }
    
    @FXML
    void refresh(MouseEvent event) {

        list.clear();
       dataProdectTable();
        
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
dataProdectTable();
        
        
    }

    //Function for refresh the table
    private void dataProdectTable(){
        PreparedStatement ps = null, ps2 = null, ps3 = null;
        Connection con = null;
        ResultSet rs = null, rs2 = null, rs3 = null;

        try {
            con = dao.Dao.getConnection();

            //for get Prodect Data
            String sql = "SELECT id,nom,description,prix,prixInitial,quntité,"
                    + "idCatég,idRay FROM produits";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                //for get Category name 
                String sql2 = "SELECT nom FROM catégories WHERE id =" + rs.getInt(7)+" AND status = 'Active'";//rs.getInt(5) this category ID
                ps2 = con.prepareStatement(sql2);
                rs2 = ps2.executeQuery();

                while (rs2.next()) {

                    //for get Rayon name
                    String sql3 = "SELECT nom FROM rayons WHERE id =" + rs.getInt(8);//rs.getInt(6) this Rayon ID
                    ps3 = con.prepareStatement(sql3);
                    rs3 = ps3.executeQuery();

                    while (rs3.next()) {

                        list.add(new prodectVo(rs.getInt(1), rs.getString(2),
                                rs.getString(3), rs.getFloat(4),rs.getInt(5),rs.getInt(6),
                                rs2.getString(1), rs3.getString(1)));
                    }
                }
            }

        } catch (SQLException | ClassNotFoundException e) {

        }

        idP.setCellValueFactory(new PropertyValueFactory<prodectVo, Integer>("id"));
        nameP.setCellValueFactory(new PropertyValueFactory<prodectVo, String>("nameP"));
        descP.setCellValueFactory(new PropertyValueFactory<prodectVo, String>("description"));
        priceP.setCellValueFactory(new PropertyValueFactory<prodectVo, Float>("priceP"));
        pricePInit.setCellValueFactory(new PropertyValueFactory<prodectVo, Float>("pricePInitial"));
        quntP.setCellValueFactory(new PropertyValueFactory<prodectVo, Integer>("quntInRay"));
        categoryP.setCellValueFactory(new PropertyValueFactory<prodectVo, String>("category"));
        rayonP.setCellValueFactory(new PropertyValueFactory<prodectVo, String>("rayon"));

        tabView.setItems(list);
    }
}
