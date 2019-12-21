package allFunction;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import responsableComercial.AddProdectController;
import static caissiers.DashboardPlatformController.l;
import admins.HomeAdminController;
import admins.hashClass;
import caissiers.VentPlatformController;
import com.jfoenix.controls.JFXSnackbar;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import login.LoginController;
import login.UsersLoginController;
import validation.validation;

/**
 *
 * @author Mido
 */

public class function {
    
    
    //Function is Empty
    public void isEmp(boolean et,String name,JFXSnackbar to){
        
        if(et == true){
            to.show(name+" is Empty !", 3000);
            return;
        }
    }
   
    //Function is not Text
    public void isNotText(boolean et,String name,JFXSnackbar toa){
        
        if(et == true){
            toa.show("Enter text in "+name+" Field !!", 2500);
            return;
        }
    }
    //Function is not Text
    public void isNumber(boolean et,String name,JFXSnackbar toa){
        
        if(et == true){
            toa.show("Enter Number in "+name+" Field !!", 2500);
            return;
        }
    }
    
    
    public static Parent root;
    ObservableList<String> listCategory = FXCollections.observableArrayList();
    ObservableList<String> listRayon = FXCollections.observableArrayList();
    
    
    //Exit Function
    public void exit() throws SQLException{
        PreparedStatement ps1 = null;
        Connection con = null;
         
         try {
             con = dao.Dao.getConnection();
           
              String sql1 = "UPDATE utilisateurs SET etat = "+0+" WHERE id = "+UsersLoginController.id;
                ps1 = con.prepareStatement(sql1);
                
                ps1.executeUpdate();
            
             
         } catch (SQLException|ClassNotFoundException e) {
         }finally{
             con.close();
             ps1.close();
         }
        
        Platform.exit();
    }
    
    //logout function
     public void logOut(HBox boxLogOut ) throws SQLException {

         VentPlatformController.namep.clear();
        VentPlatformController.priceProd.clear();
        VentPlatformController.totalProdVent.clear();
        VentPlatformController.quantProd.clear();
         
        PreparedStatement ps1 = null;
        Connection con = null;
         
         try {
             con = dao.Dao.getConnection();
           
              String sql1 = "UPDATE utilisateurs SET etat = "+0+" WHERE id = "+UsersLoginController.id;
                ps1 = con.prepareStatement(sql1);
                
                ps1.executeUpdate();
            
             
         } catch (SQLException|ClassNotFoundException e) {
         }finally{
             con.close();
             ps1.close();
         }
        
        Parent root = null;

        Stage stage = (Stage) boxLogOut.getScene().getWindow();
        try {

            root = FXMLLoader.load(getClass().getResource("/login/login.fxml"));

            
        } catch (IOException ex) {
            Logger.getLogger(HomeAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
         Scene s = new Scene(root);
                stage.setScene(s);
                stage.centerOnScreen();
                s.getStylesheets().add(getClass().getResource("/styleCss/chat.css").toExternalForm());
                stage.show();
                stage.setResizable(false);
    
    
}
      //logout function
     public void logOut(JFXButton boxLogOut ) throws SQLException {

         PreparedStatement ps1 = null;
        Connection con = null;
         
         try { // ce method pour libirer etat d'utilisateur pour login prochain fois
           con = dao.Dao.getConnection();
              String sql1 = "UPDATE utilisateurs SET etat = "+0+" WHERE id = "+UsersLoginController.id;
                ps1 = con.prepareStatement(sql1);
                
                ps1.executeUpdate();
            
             
         } catch (SQLException|ClassNotFoundException e) {
         }finally{
             con.close();
             ps1.close();
         }
         
        Stage stage = null;
        Parent root = null;

        stage = (Stage) boxLogOut.getScene().getWindow();
        try {

            root = FXMLLoader.load(getClass().getResource("/login/login.fxml"));

        } catch (IOException ex) {
            Logger.getLogger(HomeAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
         Scene s = new Scene(root);
                stage.setScene(s);
                stage.centerOnScreen();
                s.getStylesheets().add(getClass().getResource("/styleCss/login.css").toExternalForm());
                stage.show();
                stage.setResizable(false);
    
    
}
    
    
    //Fonction Login Selon Le Type d'Utilisateur
    public void Login(Text typeTxt,JFXTextField userName,JFXPasswordField password
     ,JFXButton btnLogin,String FXML,
     JFXSnackbar toast) throws ClassNotFoundException, SQLException{
                 
        
        if (userName.getText().trim().isEmpty()) {
            toast.show("Username is Empty !", 2000);
            return;
        }


        if (password.getText().trim().isEmpty()) {
            toast.show("Password is Empty !", 2000);
            return;
        }
        
        
        
        PreparedStatement ps = null,ps1 = null;
        Connection con = null;
        ResultSet rs = null;
        try{
      
        String sql = "SELECT * FROM utilisateurs WHERE type = '"+typeTxt.getText()+"' AND "
                + " username = '"+userName.getText()+
                "' AND password = '"+hashClass.hashing(password.getText())+"';";
        
        con = dao.Dao.getConnection();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        
        
        
        if(rs.next()){
            
            UsersLoginController.type = rs.getString("type");
            UsersLoginController.id = rs.getInt("id");
            //id1 =id;
            UsersLoginController.firstName = rs.getString("firstname");
            UsersLoginController.lastName = rs.getString("lastname");
       
         if(rs.getInt("etat") == 1){//pour login une foi
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("WARNING");
            a.setHeaderText("!!");
            a.setContentText("You are already Conected ..!! ");
            
            a.showAndWait();
        }else{
            
            // un seul utilisateur peut avoir login selon le nombre etat = 1
            String sql1 = "UPDATE utilisateurs SET etat = "+1+" WHERE id = "+UsersLoginController.id;
                ps1 = con.prepareStatement(sql1);
                
                ps1.executeUpdate();
            
              Stage  stage = (Stage) btnLogin.getScene().getWindow();

              root = FXMLLoader.load(getClass().getResource(FXML+".fxml"));
      
                Scene s = new Scene(root);
                stage.setTitle(typeTxt.getText()+" Interfac");
                stage.setScene(s);
                stage.centerOnScreen();
                //stage.setX(120);
                //stage.setY(50);
                s.getStylesheets().add(getClass().getResource("/styleCss/chat.css").toExternalForm());
                stage.show();
                //stage.setResizable(false);
        
        }
        }else{
            toast.show("Username and/or Password Wrong..!!",3000);
        }
            
        }catch(SQLException e){
            
            toast.show("Connection Failed ..:(",3000);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ps.close();
            rs.close();
            con.close();
        }
        
    }
    
    //Fonction de transition de login 
    public void transitionLogin(JFXButton btn){
        
          TranslateTransition transition4 = new TranslateTransition();
           transition4.setDuration(Duration.seconds(3));
           transition4.setNode(btn);
 
           transition4.setToY(-100);
           transition4.setToX(-30);
           
           ScaleTransition scaleTransition4 = new ScaleTransition(Duration.seconds(3),btn);
           scaleTransition4.setToY(-1);
           scaleTransition4.setToX(-1);
           
           RotateTransition rt4 = new RotateTransition(Duration.seconds(3), btn);
           rt4.setByAngle(360);
           
           ParallelTransition pt = new ParallelTransition(transition4
           ,scaleTransition4,rt4);
           pt.play();
        
    }
    
    
    //function for get rayon and category..... 
    public void getRayonCateg(JFXComboBox<String> category ,JFXComboBox<String> rayon){
        
        try {
            PreparedStatement ps = null, ps1 = null;
            ResultSet rs = null, rs1 = null;
            Connection con = null;

            String sql = "SELECT nom FROM catégories WHERE status = 'Active'";//for get all category names
            con = dao.Dao.getConnection();

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            String sql1 = "SELECT nom FROM rayons";//for get all rayon names
            con = dao.Dao.getConnection();

            ps1 = con.prepareStatement(sql1);
            rs1 = ps1.executeQuery();

            while (rs1.next()) {

                listRayon.add(rs1.getString(1));

                rayon.setItems(listRayon);
            }

            while (rs.next()) {

                listCategory.add(rs.getString(1));

                category.setItems(listCategory);
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddProdectController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AddProdectController.class.getName()).log(Level.SEVERE, null, ex);
        }

    } 
    //function for Validation all Field Product..... 
    public void validationProduct(JFXTextField nameP,JFXTextField priceP,JFXTextField pricePI,JFXTextArea descP){
        
        // Validation des Champ
        boolean name = validation.isEmpty(nameP.getText());
        boolean price = validation.isEmpty(priceP.getText());
        boolean priceFloat = validation.isNumber(priceP.getText());
        boolean priceI = validation.isEmpty(pricePI.getText());
        boolean priceFloatI = validation.isNumber(pricePI.getText());
        boolean desc = validation.isEmpty(descP.getText());


        if (name == true) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("!!");
            alert.setContentText("Prodect name is Empty \n Get Data first...!!");

            alert.showAndWait();
            return;
        }
        if (desc == true) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("!!");
            alert.setContentText("Description is Empty...!!");

            alert.showAndWait();
            return;
        }
        if (price == true) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("!!");
            alert.setContentText("Price is Empty...!!");

            alert.showAndWait();
            return;
        }
        if (priceFloat == true) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("!!");
            alert.setContentText("Enter Double in price...!!");

            alert.showAndWait();
            return;
        }
        
        if (priceI == true) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("!!");
            alert.setContentText("Price Initial is Empty...!!");

            alert.showAndWait();
            return;
        
        }
        
        if (priceFloatI == true) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("!!");
            alert.setContentText("Enter Double in Price Initial...!!");

            alert.showAndWait();
            return;
        }
        // Fin Validation ..............
    }
    //function for Validation ID Field Category..... 
    public void validationForGetData(JFXTextField id){
        
        boolean idIsE = validation.isEmpty(id.getText());
        boolean idIsD = validation.isNumber(id.getText());
        
        if(idIsE == true){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("!!");
            alert.setContentText("ID is Empty ...!!");
            
            alert.showAndWait();
            return;
        }
        if(idIsD == true){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("!!");
            alert.setContentText("ID is not number ...!!");
            
            alert.showAndWait();
            return;
        }
    }
    //function for Validation all Field Category..... 
    public void validationCategory(JFXTextField id,JFXTextField categName,JFXComboBox<String> status,JFXTextField chefName){
        
        boolean idIsE = validation.isEmpty(id.getText());
        boolean idIsD = validation.isNumber(id.getText());
        
        if(idIsE == true){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("!!");
            alert.setContentText("ID is Empty ...!!");
            
            alert.showAndWait();
            return;
        }
        if(idIsD == true){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("!!");
            alert.setContentText("ID is not number ...!!");
            
            alert.showAndWait();
            return;
        }
        boolean isT = validation.isText(categName.getText());
        boolean isE = validation.isEmpty(categName.getText());
        
        
         if(isE == true){
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("!!");
            alert.setContentText("Category name is Empty...!!");
            
            alert.showAndWait();
            return;
        }
        
        if(isT == true){
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("!!");
            alert.setContentText("the value in category name is not text ..!!");
            
            alert.showAndWait();
            return;
        }
        
        if(status.getSelectionModel().getSelectedItem().equals("")){
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("!!");
            alert.setContentText("Status is Not Selected...!!");
            
            alert.showAndWait();
            return;
        }
       
        boolean isCefT = validation.isText(chefName.getText());
        boolean isChefE = validation.isEmpty(chefName.getText());
        
        if(isCefT == true){
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("!!");
            alert.setContentText("Chef Section name is Empty...!!");
            
            alert.showAndWait();
            return;
        }
        
        if(isChefE == true){
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("!!");
            alert.setContentText("the value in Chef Section name is not text ..!!");
            
            alert.showAndWait();
            return;
        }
    }
    
    public void ventPlatform(AnchorPane a,Text LL,StackPane rootPane) throws IOException{
    
        l = LL.getText();
        a = FXMLLoader.load(getClass().getResource("/caissiers/ventPlatform.fxml"));
        new JFXDialog(rootPane, a, JFXDialog.DialogTransition.BOTTOM).show();
        
            
        
    }
    
    
//    //function for get Data for id int from database to String in application
//    public void getDataFromIdIntToString(){
//        
//        String sql2 = "SELECT nom FROM catégories WHERE id =" + rs.getInt(5);
//    }
    
    
    
    public void calculatProg(Text nomC,JFXProgressBar p){
        PreparedStatement ps = null, ps1 = null, ps2 = null, ps3 = null;
        ResultSet rs = null, rs1 = null, rs2 = null, rs3 = null;
        Connection con = null;
        
        try{
            con = dao.Dao.getConnection();
            String sql = "SELECT id FROM catégories WHERE nom = '"+nomC.getText()+"'";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                 String sql1 = "SELECT id FROM produits WHERE idCatég = "+rs.getInt(1);
                 ps1 = con.prepareStatement(sql1);
                 rs1 = ps1.executeQuery();
                 
                 while (rs1.next()) {                    
                 
                    String sql2 = "SELECT SUM(revenue) FROM listVente WHERE idProd = "+rs1.getInt(1);
                    ps2 = con.prepareStatement(sql2);
                    rs2 = ps2.executeQuery();
                    
                    while(rs2.next()){
                        String sql3 = "SELECT SUM(revenue) FROM listVente";
                        ps3 = con.prepareStatement(sql3);
                        rs3 = ps3.executeQuery();
                        
                        while (rs3.next()) {                            
                            
                            float t = rs2.getFloat(1) / rs3.getFloat(1);
                            System.out.println(t);
                            
                            p.setProgress(t);
                        }
                }
                }
            }
            
            
            
        }catch(ClassNotFoundException|SQLException z){
            
            
        }
        
    }
    
    
    
}
