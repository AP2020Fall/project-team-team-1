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

public class AdminMainMenu {
    @FXML
    public Button btnLogout;
    @FXML
    public Button btnGames;
    @FXML
    public Button btnUsers;
    @FXML
    public Button btnEvents;
    @FXML
    public Button btnProfile;



    @FXML
    public void close(ActionEvent event){
        System.exit(1);
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
    public void goToProfile(ActionEvent event) {
        //todo link
    }
    @FXML
    public void gotoGamesMenu(ActionEvent event) {
        //todo link
    }
    @FXML
    public void goToUsers(ActionEvent event){
        //todo link
    }
    @FXML
    public void goToEvents(ActionEvent event) throws IOException {
        LoginController.setUsername(null);
        URL url = new File("src/main/resources/FXML/AdminEvent.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
}
