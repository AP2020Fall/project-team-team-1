package Client.View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LoginError {
    @FXML
    Button btnOk;
    @FXML
    private void close(ActionEvent event){
        Stage stage = (Stage)btnOk.getScene().getWindow();
        stage.close();
    }
}
