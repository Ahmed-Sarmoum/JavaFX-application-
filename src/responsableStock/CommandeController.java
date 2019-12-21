package responsableStock;

import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Mido
 */
public class CommandeController implements Initializable {

    public static String firstN;
    public static String lastN;
    public static String Categ;
    public static int idCom;
    public static double creditCom;
    public static String listP;
    public static Date dat;
    public static Time tim;
    
    
    @FXML
    private Pane comand;
    @FXML
    private Text firstname;

    @FXML
    private Text lastname;

    @FXML
    private Text category;

    @FXML
    private Text id;

    @FXML
    private Text credit;

    @FXML
    private TextArea listProd;

    @FXML
    private Text date;

    @FXML
    private Text time;
    
      private Label jobStatus = new Label();

    
    private void printer(Node node) 
    {
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

    @FXML
    void printFactur(){//Code for print factur
   
        printer(comand);
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        firstname.setText(firstN);
        lastname.setText(lastN);
        category.setText(Categ);
        id.setText(String.valueOf(idCom));
        credit.setText(String.valueOf(creditCom)+" DA");
        listProd.setText(listP);
        date.setText(String.valueOf(dat));
        time.setText(String.valueOf(tim));
    }

    
 
    
}
