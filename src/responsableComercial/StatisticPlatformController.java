package responsableComercial;

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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Mido
 */
public class StatisticPlatformController implements Initializable {

   static private float sumPriceFromCategSelected;
    
    @FXML
    private BarChart<?,?> barChart;

    @FXML
    private PieChart pieChart;

    Notifications notification;

    static private String name;
    static private float value;
    @FXML
    private JFXComboBox<String> catégCombo;//Combobox Qui Contient les Categories 

    @FXML
    private JFXComboBox<String> catégCombo1;

    String[] n = {};
    int[] v = {};

    PieChart p = new PieChart();
    ObservableList<String> listCategory = FXCollections.observableArrayList();
    ObservableList<PieChart.Data> ol = FXCollections.observableArrayList();
    ObservableList<String> listProdect = FXCollections.observableArrayList();
    ObservableList<String> listVide = FXCollections.observableArrayList();

    @FXML
    private void barChart() {

        catégCombo.setDisable(false);
        catégCombo1.setDisable(true);
    }

    @FXML
    private void pieChart() {

        sumPriceFromCategSelected();
        
        catégCombo.setDisable(true);
        catégCombo1.setDisable(false);
        pieChart.getData().clear();

        try {

            PreparedStatement ps = null, ps1 = null, ps3 = null, ps0 = null;
            ResultSet rs = null, rs1 = null, rs3 = null, rs0 = null;
            Connection con = null;

            con = dao.Dao.getConnection();

            String sql3 = "SELECT id FROM catégories WHERE nom = '"
                    + catégCombo1.getSelectionModel().getSelectedItem() + "'";
            ps3 = con.prepareStatement(sql3);
            rs3 = ps3.executeQuery();

            if (rs3.next()) {

                String sql1 = "SELECT nom,id FROM produits WHERE idCatég = "
                        + rs3.getInt(1);
                ps1 = con.prepareStatement(sql1);
                rs1 = ps1.executeQuery();

                while (rs1.next()) {
                    String sql = "SELECT SUM(price) FROM listVente WHERE idProd = "
                            + rs1.getInt(2);

                    ps = con.prepareStatement(sql);
                    rs = ps.executeQuery();

                    while (rs.next()) {

                        String sql0 = "SELECT SUM(revenue) FROM listVente";

                        ps0 = con.prepareStatement(sql0);
                        rs0 = ps0.executeQuery();
                        
                        while (rs0.next()) {
                            
                            name = rs1.getString(1);
                            value = rs.getInt(1) * 100 / sumPriceFromCategSelected;

                            ol.add(new PieChart.Data(name, value));

                        }
                    }

                }
            }
            pieChart.setData(ol);
            /**
             * *************************** Methods*************************
             */
            pieChart.setTitle("Statistic Revenue Products For The " + catégCombo1.getSelectionModel().getSelectedItem() + " Category");
            pieChart.setLabelsVisible(true);//لإزالة الكلمات من على الدايرة
            //pc.setLabelLineLength(50);//لتكبير الخط للكلمات
           // pieChart.setLegendSide(Side.TOP);//للتحكم في مكان المقياس

            /**
             * ************************Fin Methods************************
             */
            /**
             * *************************Action*****************************
             */
            for (PieChart.Data data : pieChart.getData()) {

                data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {

                        Image img = new Image("/img/static.png");
                        notification = notification.create().title("Statistic").
                                text("Name : " + data.getName() + "\n Value : " + (int) data.getPieValue() + "%").
                                graphic(new ImageView(img)).
                                hideAfter(Duration.seconds(5)).
                                position(Pos.CENTER);
                        notification.show();
                   }
               });

            }

        } catch (ClassNotFoundException | SQLException e) {

        }
        System.out.println(sumPriceFromCategSelected);
    }
    
    private void sumPriceFromCategSelected(){
        
        sumPriceFromCategSelected = 0.0f;
        try{  
        PreparedStatement ps = null, ps1 = null, ps2 = null;
            ResultSet rs = null, rs1 = null, rs2 = null;
            Connection con = null;
        
      
            con = dao.Dao.getConnection();
            String sql = "SELECT id FROM catégories WHERE nom = '"
                    + catégCombo1.getSelectionModel().getSelectedItem() + "'";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                
                String sql1 =  "SELECT id FROM produits WHERE idCatég = "+rs.getInt(1);
                ps1 = con.prepareStatement(sql1);
                rs1 = ps1.executeQuery();
                
                while(rs1.next()){
                    String sql2 =  "SELECT SUM(revenue) FROM listVente WHERE idProd = "+rs1.getInt(1);
                    ps2 = con.prepareStatement(sql2);
                    rs2 = ps2.executeQuery();
                   
                    while(rs2.next()){
                        sumPriceFromCategSelected = sumPriceFromCategSelected + rs2.getFloat(1);
                    }
                }
                
            }
            
        }catch(ClassNotFoundException|SQLException ew){
            
        }
        
    }
    
    

    @FXML
    private void pieChart1() {

        sumPriceFromCategSelected();
        pieChart.getData().clear();

        try {

            PreparedStatement ps = null, ps1 = null, ps3 = null, ps0 = null;
            ResultSet rs = null, rs1 = null, rs3 = null, rs0 = null;
            Connection con = null;

            con = dao.Dao.getConnection();

            String sql3 = "SELECT id FROM catégories WHERE nom = '"
                    + catégCombo1.getSelectionModel().getSelectedItem() + "'";
            ps3 = con.prepareStatement(sql3);
            rs3 = ps3.executeQuery();

            while (rs3.next()) {

                String sql1 = "SELECT nom,id FROM produits WHERE idCatég = "
                        + rs3.getInt(1);
                ps1 = con.prepareStatement(sql1);
                rs1 = ps1.executeQuery();

                while (rs1.next()) {
                    String sql = "SELECT SUM(revenue) FROM listVente WHERE idProd = "
                            + rs1.getInt(2);

                    ps = con.prepareStatement(sql);
                    rs = ps.executeQuery();

                    while (rs.next()) {

                            
                            name = rs1.getString(1);
                                value = rs.getInt(1) * 100 / sumPriceFromCategSelected;

                            ol.add(new PieChart.Data(name, value));

                        
                    }

                }
            }
            pieChart.setData(ol);
            /**
             * *************************** Methods*************************
             */
            pieChart.setTitle("Statistic Revenue of Products For The " + catégCombo1.getSelectionModel().getSelectedItem() + " Category");
            pieChart.setLabelsVisible(true);//لإزالة الكلمات من على الدايرة
            //pc.setLabelLineLength(50);//لتكبير الخط للكلمات
           // pieChart.setLegendSide(Side.TOP);//للتحكم في مكان المقياس

            /**
             * ************************Fin Methods************************
             */
            /**
             * *************************Action*****************************
             */
            for (PieChart.Data data : pieChart.getData()) {
                
                data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {

                        Image img = new Image("/img/static.png");
                        notification = notification.create().title("Statistic").
                                text("Name : " + data.getName() + "\n Value : " + (int) data.getPieValue() + "%").
                                graphic(new ImageView(img)).
                                hideAfter(Duration.seconds(5)).
                                position(Pos.CENTER);
                        notification.show();
                    }
                });

            }

        } catch (ClassNotFoundException | SQLException e) {

        }
        System.out.println(sumPriceFromCategSelected);
    }

    @FXML
    private void showStatistic(ActionEvent e) {

        barChart.getData().clear();

        try {

            PreparedStatement ps = null, ps1 = null, ps0 = null;
            ResultSet rs = null, rs1 = null, rs0 = null;
            Connection con = null;

            con = dao.Dao.getConnection();

            String sql0 = "SELECT id FROM catégories WHERE nom = '"
                    + catégCombo.getSelectionModel().getSelectedItem() + "'";

            ps0 = con.prepareStatement(sql0);
            rs0 = ps0.executeQuery();

            while (rs0.next()) {

                String sql = "SELECT nom,id FROM produits WHERE idCatég = " + rs0.getInt(1);

                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();

                while (rs.next()) {

                    String sql1 = "SELECT SUM(revenue) FROM listVente WHERE idProd = '"
                            + rs.getInt(2) + "'";

                    ps1 = con.prepareStatement(sql1);
                    rs1 = ps1.executeQuery();

                    while (rs1.next()) {

                        CategoryAxis x = new CategoryAxis();
                        x.setLabel("Prodects");
                        

                        NumberAxis y = new NumberAxis(0,rs1.getFloat(1),rs1.getFloat(1)/20);
                        y.setLabel("Sales");
                        
                        
                        
                        XYChart.Series e1 = new XYChart.Series();

                        e1.getData().add(new XYChart.Data(rs.getString(1), rs1.getFloat(1)));
                        e1.setName(rs.getString(1));

                        barChart.setTitle("Statistic SUM Revenue of Products For The " + catégCombo.getSelectionModel().getSelectedItem() + " Category");
                        
                        barChart.getData().add(e1);

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

            String sql = "SELECT nom FROM catégories WHERE status = 'Active'";
            con = dao.Dao.getConnection();
            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()) {

                listCategory.add(rs.getString(1));
                catégCombo.setItems(listCategory);
                catégCombo1.setItems(listCategory);

            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(StatisticPlatformController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }

}
