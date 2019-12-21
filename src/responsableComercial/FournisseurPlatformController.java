package responsableComercial;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import static responsableStock.FourniseurController.itemSelected;
import vo.providerVo;

/**
 * FXML Controller class
 *
 * @author Mido
 */
public class FournisseurPlatformController implements Initializable {

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
    
    @FXML
    private JFXTextField search;
    
    ObservableList<providerVo> list = FXCollections.observableArrayList();
    
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
}
