package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;



public class LoginController {
    @FXML
    Button btnExit;

    @FXML
    private void appExit(ActionEvent event){
        System.exit(1);
    }
}
