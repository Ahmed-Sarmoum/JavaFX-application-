package auther;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Mido
 */
public class ShowImageController implements Initializable {

    public static Image urlImage;
    
    @FXML
    private ImageView img;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                
     img.setImage(urlImage);
     img.setFitWidth(751);
    }    
    
}
