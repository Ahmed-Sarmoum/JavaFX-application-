package responsableStock;

import responsableComercial.StatisticPlatformController;
import allFunction.function;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Mido
 */
public class StatisticResStockPlatformController implements Initializable {

    public static Color c ;
    
    @FXML
    private BarChart barChart;

    @FXML
    private JFXComboBox catégCombo;

    @FXML
    private JFXComboBox prodCombo;

    @FXML
    private JFXButton logout;
    
    ObservableList<String> listCategory = FXCollections.observableArrayList();
    ObservableList<String> listProdect = FXCollections.observableArrayList();
    

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
                    Logger.getLogger(StatisticResStockPlatformController.class.getName()).log(Level.SEVERE, null, ex);
                }
    
            
            }else{
                
            }
        });
        
        alert.showAndWait();
    }

    @FXML
    void shosedProdectFromCategory(ActionEvent event) {

         prodCombo.getItems().clear();

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
                String sql1 = "SELECT nom FROM produits WHERE idCatég = '"
                        + rs2.getInt(1) + "'";
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

    @FXML
    void showStatistic(ActionEvent event) {

        barChart.getData().clear();

        try {

            PreparedStatement ps = null, ps1 = null;
            ResultSet rs = null, rs1 = null;
            Connection con = null;

            con = dao.Dao.getConnection();

            String sql = "SELECT quntité,quntitéMinimumStock,quntitéMinimumRayon,quntitéMaximum"
                    + " FROM produits,quntité WHERE produits.id = quntité.idProdect AND"
                    + " produits.nom = '" + prodCombo.getSelectionModel().getSelectedItem() + "'";

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                String sql1 = "SELECT qunt FROM stocks,produits WHERE  stocks.idProd = produits.id AND"
                        + " produits.nom = '" + prodCombo.getSelectionModel().getSelectedItem() + "'";

                ps1 = con.prepareStatement(sql1);
                rs1 = ps1.executeQuery();

                while (rs1.next()) {

                    CategoryAxis x = new CategoryAxis();
                    x.setLabel("Stocks & Rayons");

                    NumberAxis y = new NumberAxis(0, 600, 10);
                    y.setLabel("Quntité");

                    /**
                     * ***************************
                     * Variable*****************************
                     */
                    XYChart.Series e1 = new XYChart.Series();
                    e1.setName("Quntité");

                    e1.getData().add(new XYChart.Data("Stock", rs1.getInt(1)));
                    e1.getData().add(new XYChart.Data("Rayon", rs.getInt(1)));

                    XYChart.Series e2 = new XYChart.Series();
                    e2.setName("Quntité Minimum");

                    e2.getData().add(new XYChart.Data("Stock", rs.getInt(2)));
                    e2.getData().add(new XYChart.Data("Rayon", rs.getInt(3)));

                    barChart.setTitle("Prodect Statistic  \""+prodCombo.getSelectionModel().getSelectedItem()+"\"");
                    barChart.getData().addAll(e1, e2);

                    
                    
                    if(rs.getInt(1) <= rs.getInt(3)){
                        c = Color.RED;
                    }else{
                        c = Color.WHITE;
                    }
                    
                }

            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(StatisticPlatformController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         try {
            PreparedStatement ps = null, ps1 = null, ps2 = null;
            ResultSet rs = null, rs1 = null, rs2 = null;
            Connection con = null;

            String sql = "SELECT nom FROM catégories";
            con = dao.Dao.getConnection();
            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()) {

                listCategory.add(rs.getString(1));
                catégCombo.setItems(listCategory);

            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(StatisticPlatformController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    
}
