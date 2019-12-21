package PFEProject;

import com.jfoenix.controls.JFXDecorator;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.stage.Stage;

/**
 *
 * @author Mido
 */
public class PFEProject extends Application {
    
    @Override
    public void start(Stage stage) throws IOException {
      
        //Parent root = FXMLLoader.load(getClass().getResource("/admins/homeAdmin.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/login/login.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("/camura/camura.fxml"));
        
         JFXDecorator decorator=new JFXDecorator(stage, root, false, false, false);
        decorator.setCustomMaximize(false);
        decorator.setBorder(Border.EMPTY);
        
        Scene s = new Scene(decorator);
        
        stage.setTitle("Login");
        s.getStylesheets().add(getClass().getResource("/styleCss/chat.css").toExternalForm());
        stage.setScene(s);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }

 
    public static void main(String[] args) {
        launch(args);
    }
    
}
