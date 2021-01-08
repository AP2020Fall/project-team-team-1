package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class SendMessageAsPlatoBotController implements Initializable {
    @FXML
    public Button btnSend;
    @FXML
    public TextField txtMessage;
    @FXML
    public VBox vbox;

    @FXML
    public void sendMessage(ActionEvent event) {

    }

    private void showMessages(){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
