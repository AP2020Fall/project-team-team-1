package View;

import Controller.AdminController.AdminGeneralController;
import Controller.Exception.Plato.NotNullMessageException;
import Controller.PlayerController.PlayerGeneralController;
import Model.PlatoModel.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SendMessageAsPlatoBotController implements Initializable {
    protected static PlayerGeneralController playerGeneralController = new PlayerGeneralController();
    protected static AdminGeneralController adminGeneralController = new AdminGeneralController();
    String[] messages = playerGeneralController.viewBotMessages().split("//$");

    @FXML
    public Button btnSend;
    @FXML
    public TextField txtMessage;
    @FXML
    public VBox vbox;

    @FXML
    public void sendMessage(ActionEvent event) throws IOException, NotNullMessageException {
        adminGeneralController.sendMassageString(txtMessage.getText());
        URL url = new File("src/main/resources/FXML/SendMessageAsPlatoBot.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    @FXML
    private void showMessages(){
        for (int i = 0; i < messages.length; i++) {
            Label label = new Label();
            label.setText(messages[i]);
            vbox.getChildren().add(label);

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showMessages();
        vbox.setSpacing(10);
    }
}
