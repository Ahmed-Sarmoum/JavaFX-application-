package caissiers;

import com.jfoenix.controls.JFXDialog;
import dao.Dao;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.stage.Window;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class javaController.DashboardPlatformController
 *
 * @author Mido
 */
public class DashboardPlatformController implements Initializable {

    public static String l;

    @FXML
    private Text factTotal;

    @FXML
    private TextArea listProduct;

    @FXML
    private Text date;

    @FXML
    private AnchorPane ventPlatform;

    @FXML
    private ScrollPane scroll;

    @FXML
    private VBox container;

    @FXML
    private Pane list;
    @FXML
    private StackPane rootPane;

    WebEngine engine = new WebEngine();
    Window owner;
    
    private Label jobStatus = new Label();
     private void print(Node node)  {
       
         
        // Define the Job Status Message
        jobStatus.textProperty().unbind();
        jobStatus.setText("Creating a printer job...");
         
        // Create a printer job for the default printer
        PrinterJob job = PrinterJob.createPrinterJob();
         
        if (job != null) 
        {
            // Show the printer job status
            jobStatus.textProperty().bind(job.jobStatusProperty().asString());
             
            // Print the node
            boolean printed = job.printPage(node);
 
            if (printed) 
            {
                // End the printer job
                job.endJob();
            } 
            else
            {
                // Write Error Message
                jobStatus.textProperty().unbind();
                jobStatus.setText("Printing failed.");
            }
        } 
        else
        {
            // Write Error Message
            jobStatus.setText("Could not create a printer job.");
        }
    }   

    
    //@FXML
private void handlePrintButton(Node n) {
        Printer printer = Printer.getDefaultPrinter();
    printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.EQUAL);
    PrinterJob job = PrinterJob.createPrinterJob(printer);
    job.showPrintDialog(owner);
    if (job != null) {
        engine.print(job);
        job.endJob();
    }
}
    
    @FXML
    void printFactur(){//Code for print factur
     try{
             Class.forName("com.mysql.jdbc.Driver");
             Connection con = DriverManager.getConnection
  ("jdbc:mysql://127.0.0.1/library?useUnicode=yes&characterEncoding=UTF-8","root","");
            
   // Connection con = Dao.getConnection();
             String path = "Invoice.jrxml";

             JasperReport jr = JasperCompileManager.compileReport(path);
             JasperPrint jp = JasperFillManager.fillReport(jr,null ,con);
             JasperViewer.viewReport(jp,false);
             
             con.close();
         }catch(Exception e){
             
         }
      //  print(list);
        
    }
    private boolean doPrint(Node n){
        PrinterJob job = PrinterJob.createPrinterJob();
        if(job == null)
            return false;
        if(!job.showPrintDialog(null))
            return false;
        return job.endJob();
        
    }
    
    @FXML
    void add(MouseEvent event) {

    }

    @FXML
    void clear(MouseEvent event) {

        VentPlatformController.namep.clear();
        VentPlatformController.priceProd.clear();
        VentPlatformController.totalProdVent.clear();
        VentPlatformController.quantProd.clear();

        listProduct.clear();
        factTotal.setText("0.0");
    }

    ObservableList<String> nameFL = FXCollections.observableArrayList();//remplir tout les categorie dans ce list
    ObservableList<String> nameFL1 = FXCollections.observableArrayList();//remplir 1/2 nombre de categorie
    ObservableList<String> nameFL2 = FXCollections.observableArrayList();//remplir autre 1/2 nombre de categorie

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        float count = 0.0f;

        //pour parcourir tout les prix dans la list de produit venter
        for (int j = 0; j < VentPlatformController.priceProd.size(); j++) {
            count = count + VentPlatformController.priceProd.get(j);
        }
        factTotal.setText(String.valueOf(count));//remplir le total de prix dans le textField

        //remplir les produit vente dans le facture
        for (int i = 0; i < VentPlatformController.namep.size(); i++) {
            listProduct.setText(listProduct.getText() + "\n" + VentPlatformController.namep.get(i) + "              "
                    + VentPlatformController.quantProd.get(i) + "               " + VentPlatformController.priceProd.get(i));
        }
        //get now date and time and show this in the invoice
        date.setText(String.valueOf(LocalDate.now() + "       Time : " + LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond()));

        Connection con;
        PreparedStatement ps, ps1, ps2;
        ResultSet rs, rs1, rs2;

        try {
            con = dao.Dao.getConnection();
            String sql = "SELECT nom FROM catégories WHERE status = 'Active'";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                nameFL.add(rs.getString(1));

            }
            System.out.println(nameFL.size());

            if ((nameFL.size() % 2) == 0) {
                for (int i = 0; i < nameFL.size() / 2; i++) {

                    nameFL1.add(nameFL.get(i));
                }
                for (int i = nameFL.size() / 2; i < nameFL.size(); i++) {

                    nameFL2.add(nameFL.get(i));
                }
            } else {
                for (int i = 0; i < (nameFL.size() - 1) / 2; i++) {

                    nameFL1.add(nameFL.get(i));
                }
                for (int i = (nameFL.size() - 1) / 2; i < nameFL.size(); i++) {

                    nameFL2.add(nameFL.get(i));
                }

            }

            System.out.println("nameFL1 = " + nameFL1.size() + "\nnameFL2 = " + nameFL2.size());

            HBox hb = null, hb1 = null;
            if (nameFL.size() % 2 == 0) {

                for (int i = 0; i < nameFL2.size(); i++) {

                    hb = new HBox();
                    hb.setPrefHeight(145);
                    hb.setPrefWidth(295);
                    hb.setBackground(new Background(new BackgroundFill(Color.valueOf("#fff"), CornerRadii.EMPTY, Insets.EMPTY)));
                    hb.getStyleClass().addAll("user-box1");
                    hb.setPadding(new Insets(0, 10, 0, 0));
                    hb.setSpacing(10);

                    Image image = new Image("/img/msn.png");
                    Circle view = new Circle();
                    view.setRadius(80);
                    view.setFill(new ImagePattern(image));

                    VBox vb = new VBox();
                    vb.setPrefWidth(62);
                    vb.setPrefHeight(145);
                    vb.setBackground(new Background(new BackgroundFill(Color.valueOf("#aaa"), CornerRadii.EMPTY, Insets.EMPTY)));
                    vb.getStyleClass().addAll("user-name2");
                    vb.setPadding(new Insets(0, 5, 0, 5));
                    Text t = new Text("CATEGORY");
                    t.getStyleClass().addAll("trans");
                    t.setWrappingWidth(9);
                    vb.getChildren().add(t);

                    VBox vb1 = new VBox();
                    vb1.setPadding(new Insets(15, 10, 0, 30));
                    Label l = new Label(nameFL1.get(i));
                    l.getStyleClass().addAll("user-name2");

                    vb1.getChildren().addAll(l, view);

                    hb.getChildren().addAll(vb, vb1);

                    hb.setOnMouseClicked((event) -> {

                        DashboardPlatformController.l = l.getText();
                        try {

                            ventPlatform = FXMLLoader.load(getClass().getResource("/caissiers/ventPlatform.fxml"));
                        } catch (IOException ex) {
                            Logger.getLogger(DashboardPlatformController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        new JFXDialog(rootPane, ventPlatform, JFXDialog.DialogTransition.CENTER).show();
                    });

                    hb1 = new HBox();
                    hb1.setPrefHeight(145);
                    hb1.setPrefWidth(295);
                    hb1.setBackground(new Background(new BackgroundFill(Color.valueOf("#fff"), CornerRadii.EMPTY, Insets.EMPTY)));
                    hb1.getStyleClass().addAll("user-box0");
                    hb1.setPadding(new Insets(0, 10, 0, 0));
                    hb.setSpacing(10);

                    Image image1 = new Image("/img/msn.png");
                    Circle view1 = new Circle();
                    view1.setRadius(80);
                    view1.setFill(new ImagePattern(image1));

                    VBox vb11 = new VBox();
                    vb11.setPrefWidth(62);
                    vb11.setPrefHeight(145);
                    vb11.setBackground(new Background(new BackgroundFill(Color.valueOf("#aaa"), CornerRadii.EMPTY, Insets.EMPTY)));
                    vb11.getStyleClass().addAll("user-name2");
                    vb11.setPadding(new Insets(0, 5, 0, 5));
                    Text t1 = new Text("CATEGORY");
                    t1.getStyleClass().addAll("trans");
                    t1.setWrappingWidth(9);
                    vb11.getChildren().add(t1);

                    VBox vb12 = new VBox();
                    vb12.setPadding(new Insets(15, 10, 0, 30));
                    Label l1 = new Label(nameFL2.get(i));
                    l1.getStyleClass().addAll("user-name2");

                    vb12.getChildren().addAll(l1, view1);

                    hb1.getChildren().addAll(vb11, vb12);

                    hb1.setOnMouseClicked((event) -> {

                        DashboardPlatformController.l = l1.getText();
                        try {
                            ventPlatform = FXMLLoader.load(getClass().getResource("/caissiers/ventPlatform.fxml"));
                        } catch (IOException ex) {
                            Logger.getLogger(DashboardPlatformController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        new JFXDialog(rootPane, ventPlatform, JFXDialog.DialogTransition.CENTER).show();
                    });

                    HBox hb2 = new HBox();

                    hb2.getChildren().addAll(hb, hb1);

                    container.getChildren().add(hb2);
                    scroll.vvalueProperty().bind(container.heightProperty());
                }
            } else {

                for (int i = 0; i < nameFL1.size(); i++) {

                    hb = new HBox();
                    hb.setPrefHeight(145);
                    hb.setPrefWidth(295);
                    hb.setBackground(new Background(new BackgroundFill(Color.valueOf("#fff"), CornerRadii.EMPTY, Insets.EMPTY)));
                    hb.getStyleClass().addAll("user-box1");
                    hb.setPadding(new Insets(0, 10, 0, 0));
                    hb.setSpacing(10);

                    Image image = new Image("/img/msn.png");
                    Circle view = new Circle();
                    view.setRadius(80);
                    view.setFill(new ImagePattern(image));

                    VBox vb = new VBox();
                    vb.setPrefWidth(62);
                    vb.setPrefHeight(145);
                    vb.setBackground(new Background(new BackgroundFill(Color.valueOf("#aaa"), CornerRadii.EMPTY, Insets.EMPTY)));
                    vb.getStyleClass().addAll("user-name2");
                    vb.setPadding(new Insets(0, 5, 0, 5));
                    Text t = new Text("CATEGORY");
                    t.getStyleClass().addAll("trans");
                    t.setWrappingWidth(9);
                    vb.getChildren().add(t);

                    VBox vb1 = new VBox();
                    vb1.setPadding(new Insets(15, 10, 0, 30));
                    Label l = new Label(nameFL1.get(i));
                    l.getStyleClass().addAll("user-name2");

                    vb1.getChildren().addAll(l, view);

                    hb.getChildren().addAll(vb, vb1);

                    hb.setOnMouseClicked((event) -> {

                        DashboardPlatformController.l = l.getText();
                        try {
                            ventPlatform = FXMLLoader.load(getClass().getResource("/caissiers/ventPlatform.fxml"));
                        } catch (IOException ex) {
                            Logger.getLogger(DashboardPlatformController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        new JFXDialog(rootPane, ventPlatform, JFXDialog.DialogTransition.CENTER).show();
                    });

                    hb1 = new HBox();
                    hb1.setPrefHeight(145);
                    hb1.setPrefWidth(295);
                    hb1.setBackground(new Background(new BackgroundFill(Color.valueOf("#fff"), CornerRadii.EMPTY, Insets.EMPTY)));
                    hb1.getStyleClass().addAll("user-box0");
                    hb1.setPadding(new Insets(0, 10, 0, 0));
                    hb.setSpacing(10);

                    Image image1 = new Image("/img/msn.png");
                    Circle view1 = new Circle();
                    view1.setRadius(80);
                    view1.setFill(new ImagePattern(image1));

                    VBox vb11 = new VBox();
                    vb11.setPrefWidth(62);
                    vb11.setPrefHeight(145);
                    vb11.setBackground(new Background(new BackgroundFill(Color.valueOf("#aaa"), CornerRadii.EMPTY, Insets.EMPTY)));
                    vb11.getStyleClass().addAll("user-name2");
                    vb11.setPadding(new Insets(0, 5, 0, 5));
                    Text t1 = new Text("CATEGORY");
                    t1.getStyleClass().addAll("trans");
                    t1.setWrappingWidth(9);
                    vb11.getChildren().add(t1);

                    VBox vb12 = new VBox();
                    vb12.setPadding(new Insets(15, 10, 0, 30));
                    Label l1 = new Label(nameFL2.get(i));
                    l1.getStyleClass().addAll("user-name2");

                    vb12.getChildren().addAll(l1, view1);

                    hb1.getChildren().addAll(vb11, vb12);

                    hb1.setOnMouseClicked((event) -> {

                        DashboardPlatformController.l = l1.getText();
                        try {
                            ventPlatform = FXMLLoader.load(getClass().getResource("/caissiers/ventPlatform.fxml"));
                        } catch (IOException ex) {
                            Logger.getLogger(DashboardPlatformController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        new JFXDialog(rootPane, ventPlatform, JFXDialog.DialogTransition.CENTER).show();
                    });

                    HBox hb2 = new HBox();

                    hb2.getChildren().addAll(hb, hb1);

                    container.getChildren().add(hb2);
                    scroll.vvalueProperty().bind(container.heightProperty());
                }
                HBox hb0 = new HBox();
                hb0.setPrefHeight(145);
                hb0.setPrefWidth(295);
                hb0.setBackground(new Background(new BackgroundFill(Color.valueOf("#fff"), CornerRadii.EMPTY, Insets.EMPTY)));
                hb0.getStyleClass().addAll("user-box1");
                hb0.setPadding(new Insets(0, 10, 0, 0));
                hb.setSpacing(10);

                Image image1 = new Image("/img/msn.png");
                Circle view1 = new Circle();
                view1.setRadius(80);
                view1.setFill(new ImagePattern(image1));

                VBox vb1 = new VBox();
                vb1.setPrefWidth(62);
                vb1.setPrefHeight(145);
                vb1.setBackground(new Background(new BackgroundFill(Color.valueOf("#aaa"), CornerRadii.EMPTY, Insets.EMPTY)));
                vb1.getStyleClass().addAll("user-name2");
                vb1.setPadding(new Insets(0, 5, 0, 5));
                Text t1 = new Text("CATEGORY");
                t1.getStyleClass().addAll("trans");
                t1.setWrappingWidth(9);
                vb1.getChildren().add(t1);

                VBox vb2 = new VBox();
                vb2.setPadding(new Insets(15, 10, 0, 30));
                Label l = new Label(nameFL2.get(nameFL2.size() - 1));
                l.getStyleClass().addAll("user-name2");

                vb2.getChildren().addAll(l, view1);

                hb0.getChildren().addAll(vb1, vb2);

                hb0.setOnMouseClicked((event) -> {

                    DashboardPlatformController.l = l.getText();
                    try {
                        ventPlatform = FXMLLoader.load(getClass().getResource("/caissiers/ventPlatform.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(DashboardPlatformController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    new JFXDialog(rootPane, ventPlatform, JFXDialog.DialogTransition.CENTER).show();

                });

                HBox hb4 = new HBox();

                hb4.getChildren().addAll(hb0);

                container.getChildren().add(hb4);
                scroll.vvalueProperty().bind(container.heightProperty());

            }
        } catch (SQLException | ClassNotFoundException e) {

        }
    }

}
