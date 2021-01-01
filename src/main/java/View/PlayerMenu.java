package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class PlayerMenu {
    @FXML
    public Button btnFriendsMenu;
    @FXML
    public Button BtnLogOut;
    @FXML
    public Button btnProfile;
    @FXML
    public Button btnGamesMenu;
    @FXML
    public Button btnMainMenu;
    @FXML
    public void goToMainMenu(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/FXML/PlayerMainMenu.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    @FXML
    public void gotoGamesMenu(ActionEvent event) {

    }
    @FXML
    public void goToProfile(ActionEvent event) {

    }
    @FXML
    public void logOut(ActionEvent event) throws IOException {
        LoginController.setUsername(null);
        URL url = new File("src/main/resources/FXML/Login.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    @FXML
    public void goToFriendsMenu(ActionEvent event) {

    }
    @FXML
    public void close(ActionEvent event){
        System.exit(1);
    }
}
