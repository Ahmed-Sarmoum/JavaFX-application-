package responsableComercial;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
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
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import vo.listVentVo;

/**
 * FXML Controller class
 *
 * @author Mido
 */
public class SalesController implements Initializable {

    @FXML
    private TableView<listVentVo> tabView;

    @FXML
    private TableColumn<listVentVo, String> product;

    @FXML
    private TableColumn<listVentVo, Integer> quantity;

    @FXML
    private TableColumn<listVentVo, Float> price;
    
    @FXML
    private TableColumn<listVentVo, Float> revenue;

    @FXML
    private TableColumn<listVentVo, Date> dateS;

    @FXML
    private TableColumn<listVentVo, String> cashier;
    
    @FXML
    private JFXTextField quantityS;

    @FXML
    private JFXTextField priceS;

    @FXML
    private JFXTextField persontag;
    
    @FXML
    private JFXComboBox<String> catégCombo;//Combobox Qui Contient les Categories 

    @FXML
    private JFXComboBox<String> prodCombo;//Combobox Qui Contient les Rayons
    
    ObservableList<listVentVo> list = FXCollections.observableArrayList();
    ObservableList<String> listCategory = FXCollections.observableArrayList();
    ObservableList<String> listProdect = FXCollections.observableArrayList();
    
    @FXML
    private JFXTextField search;
    @FXML
    private JFXDatePicker search1;

    @FXML
    void search(MouseEvent event) {

        search.textProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if (search.textProperty().get().isEmpty()) {
                    tabView.setItems(list);
                    return;
                }
                ObservableList<listVentVo> items = FXCollections.observableArrayList();
                ObservableList<TableColumn<listVentVo, ?>> column = tabView.getColumns();

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
    private void calculat(ActionEvent e){
        
        PreparedStatement ps = null,ps1 = null,ps2 = null;
        ResultSet rs = null,rs1 = null,rs2 = null;
        Connection con = null;
        
        try{
            con = dao.Dao.getConnection();
            String sql = "SELECT id FROM produits WHERE  nom = '"+
                    prodCombo.getSelectionModel().getSelectedItem()+"'";
            
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
            
            String sql1 = "SELECT SUM(quntité),SUM(price) FROM listVente"
                    + " WHERE idProd = "+rs.getInt(1);
            
            
            ps1 = con.prepareStatement(sql1);
            
            rs1 = ps1.executeQuery();
            
            while(rs1.next()){
                
                String sql2 = "SELECT SUM(price) FROM listVente " ;
                ps2 = con.prepareStatement(sql2);
                
                rs2 = ps2.executeQuery();
                
                while(rs2.next()){
                    
                    quantityS.setText(String.valueOf(rs1.getInt(1)));
                    priceS.setText(String.valueOf(rs1.getInt(2)));
                                        
                    double p = rs1.getInt(2) * 100 / rs2.getInt(1);
                    System.out.println(p);
                    persontag.setText(String.valueOf(p)+"  %");
                }
            }
            
            }
        }catch(ClassNotFoundException|SQLException ex){
            
            
        }
    }
    
    @FXML
    void refresh(MouseEvent event) {

        list.clear();
    dataSalesTable();
  
          
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        PreparedStatement ps = null,ps1 = null,ps2 = null;
        ResultSet rs = null,rs1 = null,rs2 = null;
        Connection con = null;
        
        try{
          
            dataSalesTable();
            
       
            PreparedStatement ps4 = null;
            ResultSet rs4 = null;

            String sql4 = "SELECT nom FROM catégories";
            con = dao.Dao.getConnection();
            ps4 = con.prepareStatement(sql4);

            rs4 = ps4.executeQuery();

            while (rs4.next()) {

                listCategory.add(rs4.getString(1));
                catégCombo.setItems(listCategory);

            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(StatisticPlatformController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
    @FXML
    private void shosedProdectFromCategory(ActionEvent e) {

        prodCombo.getItems().clear();
        quantityS.clear();
        persontag.clear();
        priceS.clear();

        try {
            PreparedStatement ps1 = null, ps2 = null;
            ResultSet rs1 = null, rs2 = null;
            Connection con = null;

            con = dao.Dao.getConnection();
            String sql2 = "SELECT id FROM catégories WHERE nom = '"
                    + catégCombo.getSelectionModel().getSelectedItem() + "'";
            ps2 = con.prepareStatement(sql2);

            rs2 = ps2.executeQuery();

            while (rs2.next()) {
                String sql1 = "SELECT nom FROM produits WHERE idCatég = "
                        + rs2.getInt(1);
                ps1 = con.prepareStatement(sql1);

                rs1 = ps1.executeQuery();

                while (rs1.next()) {

                    listProdect.add(rs1.getString(1));
                    prodCombo.setItems(listProdect);
                }
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(StatisticPlatformController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    //Function for refresh table
    private void dataSalesTable() {
        
         PreparedStatement ps = null,ps1 = null,ps2 = null;
        ResultSet rs = null,rs1 = null,rs2 = null;
        Connection con = null;
        try{
              
            String sql = "SELECT idProd,quntité,price,revenue,date,idUser FROM listVente";
            con = dao.Dao.getConnection();
            
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                
                String sql1 = "SELECT nom  FROM produits WHERE id = "+rs.getInt(1);
                
                ps1 = con.prepareStatement(sql1);
                rs1 = ps1.executeQuery();
                
                while(rs1.next()){
                
                 String sql2 = "SELECT firstname , lastname FROM utilisateurs WHERE "
                         + "id ="+rs.getInt(6);
                 ps2 = con.prepareStatement(sql2);
                 rs2 = ps2.executeQuery();
                 
                 while(rs2.next()){
                     
                     list.add(new listVentVo(rs1.getString(1), rs.getInt(2),
                                 rs.getFloat(3),rs.getFloat(4), rs.getDate(5), rs2.getString(1)+" "+rs2.getString(2)));
                     
                 }
                    
                }
            }
            
       
        product.setCellValueFactory(new PropertyValueFactory<listVentVo,String>("nameProduct"));
        quantity.setCellValueFactory(new PropertyValueFactory<listVentVo,Integer>("quantity"));
        price.setCellValueFactory(new PropertyValueFactory<listVentVo,Float>("price"));
        revenue.setCellValueFactory(new PropertyValueFactory<listVentVo,Float>("revenue"));
        dateS.setCellValueFactory(new PropertyValueFactory<listVentVo,Date>("date"));
        cashier.setCellValueFactory(new PropertyValueFactory<listVentVo,String>("nameUser"));
        
        tabView.setItems(list);
        
    
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(StatisticPlatformController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
}
