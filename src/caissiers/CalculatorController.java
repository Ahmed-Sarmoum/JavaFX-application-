package caissiers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Mido
 */
public class CalculatorController implements Initializable {

    
    @FXML
    private VBox drawVbox;
    @FXML
    private JFXButton jbtn1;
    @FXML
    private JFXButton jbtn3;
    @FXML
    private JFXButton jbtn2;
    @FXML
    private JFXButton jbtn4;
    @FXML
    private JFXButton jbtn5;
    @FXML
    private JFXButton jbtn6;
    @FXML
    private JFXButton jbtn7;
    @FXML
    private JFXButton jbtn8;
    @FXML
    private JFXButton jbtn9;
    @FXML
    private JFXButton jbtn0;
    @FXML
    private JFXButton jbtnDot;
    @FXML
    private Label jlab;
    @FXML
    private JFXDrawer listDraw;
    
   double firstnum ;
   double secondnum ;
   double result ;
   String operations ;
  
    @FXML
    private void jbtn7(ActionEvent evt) {                                      

        if(jlab.getText() == "0"){
        jlab.setText("");
        }
        String iN = jlab.getText()  + jbtn7.getText();
        jlab.setText(iN);
    }                                     
    @FXML
    private void jbtn4(ActionEvent evt) {                                      

        if(jlab.getText() == "0"){
        jlab.setText("");
        }
        String iN = jlab.getText()  + jbtn4.getText();
        jlab.setText(iN);
    }                                     
    @FXML
    private void jbtn8(ActionEvent evt) {                                      

        if(jlab.getText() == "0"){
        jlab.setText("");
        }
        String iN = jlab.getText()  + jbtn8.getText();
        jlab.setText(iN);
    }                                     
    @FXML
    private void jbtn5(ActionEvent evt) {                                      

        if(jlab.getText() == "0"){
        jlab.setText("");
        }
        String iN = jlab.getText()  + jbtn5.getText();
        jlab.setText(iN);
    }                                     
    @FXML
    private void jbtn6(ActionEvent evt) {                                      

        if(jlab.getText() == "0"){
        jlab.setText("");
        }
        String iN = jlab.getText()  + jbtn6.getText();
        jlab.setText(iN);
    }                                     
    @FXML
    private void jbtn9(ActionEvent evt) {                                      

        if(jlab.getText() == "0"){
        jlab.setText("");
        }
        String iN = jlab.getText()  + jbtn9.getText();
        jlab.setText(iN);
    }                                     
                                       
    @FXML
    private void jbtnSub(ActionEvent evt) {                                        

        firstnum = Double.parseDouble(jlab.getText());
        jlab.setText("");
        operations = "-";
    }
   
    @FXML
    private void jbtn0(ActionEvent evt) {                                      

        if(jlab.getText() == "0"){
        
        }else{
        String iN = jlab.getText()  +  jbtn0.getText();
        jlab.setText(iN);
    }      }                               
    @FXML
    private void jbtnClear(ActionEvent evt) {                                          

        jlab.setText("0");
    }                                         
    @FXML
    private void jbtnDiv(ActionEvent evt) {                                        

        firstnum = Double.parseDouble(jlab.getText());
        jlab.setText("");
        operations = "/";
    }                                       
    @FXML
    private void jbtnprod(ActionEvent evt) {                                         

        firstnum = Double.parseDouble(jlab.getText());
        jlab.setText("");
        operations = "*";
    }                                        
    @FXML
    private void jbtn2(ActionEvent evt) {                                      

        if(jlab.getText() == "0"){
        jlab.setText("");
        }
        String iN = jlab.getText()  + jbtn2.getText();
        jlab.setText(iN);
    }                                     
    @FXML
    private void jbtn3(ActionEvent evt) {                                      

        if(jlab.getText() == "0"){
        jlab.setText("");
        }
        String iN = jlab.getText()  + jbtn3.getText();
        jlab.setText(iN);
    }                                     
    @FXML
    private void jbtn1(ActionEvent evt) {                                      

        if(jlab.getText() == "0"){
        jlab.setText("");
        }
        String iN = jlab.getText()  + jbtn1.getText();
        jlab.setText(iN);
    }
    @FXML
    private void jbtnNegatif(ActionEvent evt) {                                          

        double ops = Double.parseDouble(String.valueOf(jlab.getText()));
        ops = ops * (-1);
        jlab.setText(String.valueOf(ops));
    }
    @FXML
    private void jbtnEq(ActionEvent evt) {                                       

        String answer;
        //  secondnum = Double.parseDouble(jtxt.getText());

        //       String answer;
        secondnum = Double.parseDouble(jlab.getText());
        if (operations == "+") {
            result = firstnum + secondnum;
            answer = String.format("%.2f", result);
            jlab.setText(answer);
        } else if (operations == "-") {
            result = firstnum - secondnum;
            answer = String.format("%.2f", result);
            jlab.setText(answer);
        } else if (operations == "*") {
            result = firstnum * secondnum;
            answer = String.format("%.2f", result);
            jlab.setText(answer);
        } else if (operations == "/") {
            result = firstnum / secondnum;
            answer = String.format("%.2f", result);
            jlab.setText(answer);
        } else if (operations == "%") {
            result = firstnum % secondnum;
            answer = String.format("%.2f", result);
            jlab.setText(answer);

        }
    }         
    @FXML
    private void jbtnDot(ActionEvent evt) {                                        

        if(jlab.getText() == ""){
        jlab.setText("0");
        }
        if (!jlab.getText().contains(".")) {

            jlab.setText(jlab.getText() + jbtnDot.getText());
        }
        }
    
 
    @FXML
    private void jbtnSom(ActionEvent evt) {                                         
       firstnum = Double.parseDouble(jlab.getText());
        jlab.setText("");
        operations = "+";
    } 
    @FXML
    private void jbtnBackspace(ActionEvent evt) {                                              

        String bsp = null;
        if (jlab.getText().length() > 0) {

            StringBuilder strB = new StringBuilder(jlab.getText());
            strB.deleteCharAt(jlab.getText().length() - 1);
            bsp = strB.toString();
            jlab.setText(bsp);
        }
    }
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }    
    
}
