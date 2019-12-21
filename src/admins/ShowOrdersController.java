package admins;

import com.jfoenix.controls.JFXDialog;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import responsableStock.CommandeController;
import responsableStock.OrderFournisseurController;

/**
 * FXML Controller class
 *
 * @author Mido
 */
public class ShowOrdersController implements Initializable {

    @FXML
    private VBox container;

    @FXML
    private AnchorPane commandePlatform;
    
    @FXML
    private ScrollPane scroll;
    
    @FXML
    private StackPane showOrders;

    ObservableList<String> nameFL = FXCollections.observableArrayList();
    ObservableList<Timestamp> date = FXCollections.observableArrayList();
    ObservableList<Integer> idComand = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Connection con;
        PreparedStatement ps, ps1, ps2;
        ResultSet rs, rs1, rs2;

        try {
            con = dao.Dao.getConnection();
            String sql = "SELECT id,idFournisseur,date FROM commande";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                String sql1 = "SELECT firstname,lastname FROM fournisseur WHERE id = " + rs.getInt(2);
                ps1 = con.prepareStatement(sql1);
                rs1 = ps1.executeQuery();

                while (rs1.next()) {
                    nameFL.add(rs1.getString(1) + " " + rs1.getString(2));
                    date.add(rs.getTimestamp(3));
                    idComand.add(rs.getInt(1));

                }
            }

            for (int i = 0; i < idComand.size(); i++) {
                Line ll = new Line();
                ll.setStartX(0);
                ll.setEndX(560);

                HBox hb = new HBox();
                hb.setPrefWidth(590);
                hb.setPrefHeight(90);
                hb.setSpacing(20);
                hb.getStyleClass().add("color-container");

                Text id = new Text(" " + String.valueOf(idComand.get(i)) + "         ");
                id.getStyleClass().add("text-id");

                Line l = new Line();
                l.setStartX(0);
                l.setStartY(0);
                l.setEndX(0);
                l.setEndY(70);

                VBox vb = new VBox();
                vb.setPrefWidth(240);
                vb.setPrefHeight(73);
                vb.setSpacing(8);
                Text name = new Text("  To : " + nameFL.get(i));
                name.getStyleClass().add("text-name");

                Text date = new Text(String.valueOf("   "+this.date.get(i)));
                date.getStyleClass().add("text-date");

                vb.getChildren().addAll(name, date);

                Text click = new Text("Click to show order");
                click.getStyleClass().add("text-click");
                //click.setWrappingWidth(50);

                hb.getChildren().addAll(id, vb, click);

                container.getChildren().addAll(hb, ll);

                scroll.vvalueProperty().bind(container.heightProperty());

                
                hb.setOnMouseClicked((event) -> {

                    PreparedStatement ps0,ps00,ps000;
                    ResultSet rs0,rs00,rs000;
                
                    try {
                    
                    String sql0 = "SELECT idFournisseur,listProduct,date,id FROM commande WHERE id ="+id.getText();
                    ps0 = con.prepareStatement(sql0);
                    rs0 = ps0.executeQuery();
                    
                    while(rs0.next()){
                        String sql00 = "SELECT firstname,lastname,idCategory,cridé FROM fournisseur WHERE id = "+rs0.getInt(1);
                        ps00 = con.prepareStatement(sql00);
                        rs00 = ps00.executeQuery();
                        
                        while(rs00.next()){
                            String sql000 = "SELECT nom FROM catégories WHERE id = "+rs00.getInt(3);
                            ps000 = con.prepareStatement(sql000);
                            rs000 = ps000.executeQuery();
                            
                            while(rs000.next()){
                                
                                CommandeController.Categ = rs000.getString(1);
                                CommandeController.creditCom = rs00.getDouble(4);
                                CommandeController.dat = rs0.getDate(3);
                                CommandeController.tim = rs0.getTime(3);
                                CommandeController.firstN = rs00.getString(1);
                                CommandeController.lastN = rs00.getString(2);
                                CommandeController.idCom = rs0.getInt(4);
                                CommandeController.listP = rs0.getString(2);
                            }
                        }
                    
                    }
                    
                    
                    
                        
                        commandePlatform = FXMLLoader.load(getClass().getResource("/responsableStock/commande.fxml"));
                    
                    } catch (SQLException|IOException ex) {
                        Logger.getLogger(OrderFournisseurController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    new JFXDialog(showOrders, commandePlatform, JFXDialog.DialogTransition.RIGHT).show();

                });


            }
        } catch (SQLException | ClassNotFoundException e) {

        }
    }
}
