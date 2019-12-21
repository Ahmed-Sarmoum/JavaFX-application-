package login;

import admins.hashClass;
import allFunction.function;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import static javafx.scene.input.KeyCode.ENTER;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;


/**
 * FXML Controller class
 *
 * @author Mido
 */
public class UsersLoginController implements Initializable {

    public static String firstName,lastName;
    public static String type= " ";
    public static int id;
    public static String img;
  //  public static int idUserEntred=0;
    //public static String firstLastNameUser=" ";
    @FXML
    private Circle imgUser;
    @FXML
    private JFXTextField txtusername;
    @FXML
    private JFXPasswordField txtpassword;
    @FXML
    private StackPane rootPane;
    @FXML
    private VBox adminLogin;
    @FXML
    private Pane showSnak;
    @FXML
    private JFXButton btnLogin;
      @FXML
    private Text typetxt;
      
      private JFXSnackbar toast;
    
    
      @FXML
    void login() throws ClassNotFoundException, SQLException {

          function f = new function();
        
          //System.out.println("");
          
           //login pour les admins
            if(typetxt.getText().equals("Admin")){
       
                f.Login(typetxt, txtusername, txtpassword,
                        btnLogin, "/admins/homeAdmin", toast);
        }
            //login pour les Responsables comercial
            if(typetxt.getText().equals("Commercial Responsible")){ 
       
                f.Login(typetxt, txtusername, txtpassword
                        , btnLogin, "/responsableComercial/homeResCommercial", toast);
        }
            //login pour les Responsables de stock
            if(typetxt.getText().equals("Stock responsible")){ 
       
                f.Login(typetxt, txtusername, txtpassword,
                        btnLogin, "/responsableStock/homeResStock", toast);
        }
            //login pour les caissiers
            if(typetxt.getText().equals("Cashier")){ 
       
                f.Login(typetxt, txtusername, txtpassword
                        , btnLogin, "/caissiers/homeCashier", toast);
        }
    }
     
    @FXML
    void filterImage(KeyEvent event) {

        
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
  
        try{
            
            con = dao.Dao.getConnection();
            String sql = "SELECT image FROM utilisateurs WHERE username = '"+txtusername.getText()+""
                    + "' AND password = '"+hashClass.hashing(txtpassword.getText())+"'"
                    + "AND type = '"+typetxt.getText()+"'";
            ps = con.prepareStatement(sql);
            
            rs = ps.executeQuery();
            
            if(rs.next()){
                Image image = new Image("/imgUsers/"+rs.getString(1));
                imgUser.setFill(new ImagePattern(image));
                img = rs.getString(1);//pour garder l'image dans un variable static
            }else{
                Image image = new Image("/img/bgnn");
                imgUser.setFill(new ImagePattern(image));
            }
            
        }catch(SQLException|ClassNotFoundException e){
            
        
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        adminLogin.setOnKeyPressed(event -> { // Add Key Listener, if i click to the Enter Button Call Login() method
            if (event.getCode().equals(ENTER)) {
                try {
                    login();
                } catch (ClassNotFoundException|SQLException ex) {
                    Logger.getLogger(UsersLoginController.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }
        });
        
        typetxt.setText(LoginController.typeTxt);
       Image image = new Image("/img/bgnn");
                imgUser.setFill(new ImagePattern(image));
                
                toast = new JFXSnackbar(showSnak);

    }
    }    
    

