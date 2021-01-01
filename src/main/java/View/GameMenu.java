package View;

import Controller.AdminController.AdminGeneralController;
import Controller.Exception.Plato.InvalidGameID;
import Controller.PlayerController.PlayerGeneralController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class GameMenu implements Initializable {
    protected static AdminGeneralController adminGeneralController = new AdminGeneralController();
    protected static PlayerGeneralController playerGeneralController = new PlayerGeneralController();
    String firsGame = adminGeneralController.firstGameNameGetter();
    String secondGame = adminGeneralController.secondGameNameGetter();
    @FXML
    Button btnBattle;
    @FXML
    Button btnDots;

//    @FXML
//    private void goBattleShipMenu(ActionEvent actionEvent) throws IOException, InvalidGameID {
//
//        if (adminGeneralController.activationStatus("1").equalsIgnoreCase("false")){
//            showError();
//            URL url = new File("src/main/resources/FXML/GameMenu.fxml").toURI().toURL();
//            Parent register = FXMLLoader.load(url);
//            Scene message = new Scene(register);
//            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
//            window.setScene(message);
//            window.show();
//        }
//        else if (adminGeneralController.activationStatus("1").equalsIgnoreCase("true")){
//            URL url = new File("src/main/resources/FXML/BattleShipMainMenu.fxml").toURI().toURL();
//            Parent register = FXMLLoader.load(url);
//            Scene message = new Scene(register);
//            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
//            window.setScene(message);
//            window.show();
//        }
//
//
//        if (adminGeneralController.maintenanceStatus("1").equalsIgnoreCase("true")){
//            showError();
//            URL url = new File("src/main/resources/FXML/GameMenu.fxml").toURI().toURL();
//            Parent register = FXMLLoader.load(url);
//            Scene message = new Scene(register);
//            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
//            window.setScene(message);
//            window.show();
//        } else if (adminGeneralController.maintenanceStatus("1").equalsIgnoreCase("false")){
//            URL url = new File("src/main/resources/FXML/BattleShipMainMenu.fxml").toURI().toURL();
//            Parent register = FXMLLoader.load(url);
//            Scene message = new Scene(register);
//            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
//            window.setScene(message);
//            window.show();
//        }
//
//    }

    @FXML
    private void goBattleShipMenu(ActionEvent actionEvent) throws InvalidGameID, IOException {
        if (adminGeneralController.activationStatus("1").equalsIgnoreCase("false") || adminGeneralController.maintenanceStatus("1").equalsIgnoreCase("true")){
            showError();
            return;
        }
            URL url = new File("src/main/resources/FXML/BattleShipMainMenu.fxml").toURI().toURL();
            Parent register = FXMLLoader.load(url);
            Scene message = new Scene(register);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(message);
            window.show();
    }



    private void showError() throws IOException {
        URL url = new File("src/main/resources/FXML/UpdateError.fxml").toURI().toURL();

        AnchorPane root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        btnBattle.setText(firsGame);
        btnDots.setText(secondGame);

    }
}
