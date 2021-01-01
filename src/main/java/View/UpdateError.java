package View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.logging.Handler;

public class UpdateError {
    @FXML
    Button btnOk;
    @FXML
    private void close(ActionEvent event){
        Stage stage = (Stage)btnOk.getScene().getWindow();
        stage.close();
    }
}
