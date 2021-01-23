package Client.View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateError implements Initializable {
    protected static String error = "error";

    public static String getError() {
        return error;
    }

    public static void setError(String error) {
        UpdateError.error = error;
    }

    @FXML
    Label errorLabel;
    @FXML
    Button btnOk;
    @FXML
    private void close(ActionEvent event){
        Stage stage = (Stage)btnOk.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void setErrorLabel(){
        errorLabel.setText(getError());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setErrorLabel();
    }
}
