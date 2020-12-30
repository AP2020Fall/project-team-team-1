package View;

import Controller.AdminController.AdminGeneralController;
import Controller.PlayerController.PlayerGeneralController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class BattleShip {
    protected static AdminGeneralController adminGeneralController = new AdminGeneralController();
    protected static PlayerGeneralController playerGeneralController = new PlayerGeneralController();

    @FXML
    Button details;
    @FXML
    Label topic;
    @FXML
    Text detail;
    @FXML
    Button btnExit;
    @FXML
    private void setTopic(){
       topic.setText(adminGeneralController.firstGameNameGetter()+"'s Details");
    }
    @FXML
    private void setDetail(){
        detail.setText(playerGeneralController.battleDetails());
        topic.setText(adminGeneralController.firstGameNameGetter()+"'s Details");
    }
    @FXML
    private void appExit(ActionEvent event){
        System.exit(1);
    }
    @FXML
    private void goDetails(ActionEvent actionEvent) throws IOException {

        URL url = new File("src/main/resources/FXML/BattleShipDetails.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    @FXML
    private void goBattleShipMainMenu(ActionEvent actionEvent) throws IOException {

        URL url = new File("src/main/resources/FXML/BattleShipMainMenu.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
}
