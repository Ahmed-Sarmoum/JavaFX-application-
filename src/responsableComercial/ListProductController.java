package responsableComercial;

import allFunction.function;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import vo.prodectVo;

/**
 * FXML Controller class
 *
 * @author Mido
 */
public class ListProductController implements Initializable {

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
    private TableColumn<prodectVo, Integer> quntP;

    @FXML
    private TableColumn<prodectVo, String> categoryP;

    @FXML
    private TableColumn<prodectVo, String> rayonP;

    ObservableList<prodectVo> list = FXCollections.observableArrayList();

    @FXML
    private JFXTextField search;

    @FXML
    private JFXButton logout;

    @FXML
    void logOut(MouseEvent event) {

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
                    Logger.getLogger(ListProductController.class.getName()).log(Level.SEVERE, null, ex);
                }
    
            
            }else{
                
            }
        });
        
        alert.showAndWait();
        
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
    
      @FXML
    void getData(MouseEvent event) {
        
        list.clear();
        
PreparedStatement ps = null, ps2 = null, ps3 = null;
        Connection con = null;
        ResultSet rs = null, rs2 = null, rs3 = null;

        try {
            con = dao.Dao.getConnection();

            //for get Prodect Data
            String sql = "SELECT id,nom,description,prix,quntité,idCatég,idRay FROM produits";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                //for get Category name 
                String sql2 = "SELECT nom FROM catégories WHERE id =" + rs.getInt(6)+" AND status = 'Active'";//rs.getInt(5) this category ID
                ps2 = con.prepareStatement(sql2);
                rs2 = ps2.executeQuery();

                while (rs2.next()) {

                    //for get Rayon name
                    String sql3 = "SELECT nom FROM rayons WHERE id =" + rs.getInt(7);//rs.getInt(6) this Rayon ID
                    ps3 = con.prepareStatement(sql3);
                    rs3 = ps3.executeQuery();

                    while (rs3.next()) {

                        list.add(new prodectVo(rs.getInt(1), rs.getString(2),
                                rs.getString(3), rs.getFloat(4),rs.getInt(5),
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
        quntP.setCellValueFactory(new PropertyValueFactory<prodectVo, Integer>("quntInRay"));
        categoryP.setCellValueFactory(new PropertyValueFactory<prodectVo, String>("category"));
        rayonP.setCellValueFactory(new PropertyValueFactory<prodectVo, String>("rayon"));

        tabView.setItems(list);
        
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         PreparedStatement ps = null, ps2 = null, ps3 = null;
        Connection con = null;
        ResultSet rs = null, rs2 = null, rs3 = null;

        try {
            con = dao.Dao.getConnection();

            //for get Prodect Data
            String sql = "SELECT id,nom,description,prix,quntité,idCatég,idRay FROM produits";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                //for get Category name 
                String sql2 = "SELECT nom FROM catégories WHERE id =" + rs.getInt(6)+" AND status = 'Active'";//rs.getInt(5) this category ID
                ps2 = con.prepareStatement(sql2);
                rs2 = ps2.executeQuery();

                while (rs2.next()) {

                    //for get Rayon name
                    String sql3 = "SELECT nom FROM rayons WHERE id =" + rs.getInt(7);//rs.getInt(6) this Rayon ID
                    ps3 = con.prepareStatement(sql3);
                    rs3 = ps3.executeQuery();

                    while (rs3.next()) {

                        list.add(new prodectVo(rs.getInt(1), rs.getString(2),
                                rs.getString(3), rs.getFloat(4),rs.getInt(5),
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
        quntP.setCellValueFactory(new PropertyValueFactory<prodectVo, Integer>("quntInRay"));
        categoryP.setCellValueFactory(new PropertyValueFactory<prodectVo, String>("category"));
        rayonP.setCellValueFactory(new PropertyValueFactory<prodectVo, String>("rayon"));

        tabView.setItems(list);
    }    
    
}
