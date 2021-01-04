package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminGamesBattleShip implements Initializable {
    @FXML
    public Button btnGoToAdminBattleShipGame;
    @FXML
    public Button btnGoToAdminDotsGame;
    @FXML
    public Button btnDeActive;
    @FXML
    public Button btnActive;
    @FXML
    public Label lblNumbers;
    @FXML
    public Label lblMVP;
    @FXML
    public TextArea txtDetails;
    @FXML
    public Button btnEdit;
    @FXML
    public Label lblDetails;

    @FXML
    private void goToAdminDotsGame(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/FXML/AdminGamesDotsAndBoxes.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    @FXML
    private void goToBattleGame(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/FXML/AdminGamesBattleShip.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    @FXML
    private void goToLastMenu(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/FXML/AdminMenu.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    @FXML
    private void appExit(ActionEvent event){
        System.exit(1);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
