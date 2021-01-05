package View;

import Controller.AdminController.AdminGeneralController;
import Controller.Exception.Plato.GameActivation;
import Controller.Exception.Plato.InvalidGameID;
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

public class AdminGamesDotsAndBoxes implements Initializable {
    protected static AdminGeneralController adminGeneralController = new AdminGeneralController();

    @FXML
    public Button btnDeactivateDots;

    @FXML
    public Button btnActivateDots;

    @FXML
    public Label lblDotsNumber;

    @FXML
    public Label lblDotsMvp;

    @FXML
    public TextArea txtDetails;

    @FXML
    public Button btnEditDotsDetails;

    @FXML
    public Label lblDotsDetails;

    @FXML
    void activateDots(ActionEvent event) {
        try {
            adminGeneralController.activeGame("2");
        } catch (InvalidGameID invalidGameID) {
            invalidGameID.printStackTrace();
        } catch (GameActivation gameActivation) {
            gameActivation.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void deActiveDots(ActionEvent event) {
        try {
            adminGeneralController.deActiveGame("2");
        } catch (InvalidGameID invalidGameID) {
            invalidGameID.printStackTrace();
        } catch (GameActivation gameActivation) {
            gameActivation.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void editDotsDetails(ActionEvent event) {
        try {
            adminGeneralController.setDetails("DotsAndBoxes",txtDetails.getText());
            URL url = new File("src/main/resources/FXML/AdminGamesDotsAndBoxes.fxml").toURI().toURL();
            Parent register = FXMLLoader.load(url);
            Scene message = new Scene(register);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(message);
            window.show();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void goToAdminBattleShip(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/FXML/AdminGamesBattleShip.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    @FXML
    void goToAdminDots(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/FXML/AdminGamesDotsAndBoxes.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    @FXML
    public void goToLastMenu(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/FXML/AdminMenu.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    @FXML
    public void appExit(ActionEvent event) {
        System.exit(1);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
