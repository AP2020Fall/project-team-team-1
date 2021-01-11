package View;

import Controller.AdminController.AdminGeneralController;
import Controller.Exception.Plato.GameActivation;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminGamesDotsAndBoxes implements Initializable {
    protected static AdminGeneralController adminGeneralController = new AdminGeneralController();
    protected static PlayerGeneralController playerGeneralController = new PlayerGeneralController();
    String[] strings = adminGeneralController.getMVPUserSecondGame().split("\\$");

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
    void activateDots(ActionEvent event) throws InvalidGameID, IOException, GameActivation {
        playMouseSound();
        adminGeneralController.activeGame("2");
        URL url = new File("src/main/resources/FXML/AdminGamesDotsAndBoxes.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    @FXML
    void deActiveDots(ActionEvent event) throws InvalidGameID, IOException, GameActivation {
        playMouseSound();
        adminGeneralController.deActiveGame("2");
        URL url = new File("src/main/resources/FXML/AdminGamesDotsAndBoxes.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    @FXML
    void editDotsDetails(ActionEvent event) {
        playMouseSound();
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
        playMouseSound();
        URL url = new File("src/main/resources/FXML/AdminGamesBattleShip.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    @FXML
    void goToAdminDots(ActionEvent event) throws IOException {
        playMouseSound();
        URL url = new File("src/main/resources/FXML/AdminGamesDotsAndBoxes.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    @FXML
    public void goToLastMenu(ActionEvent event) throws IOException {
        playMouseSound();
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
    @FXML
    private void btnIsActive() throws InvalidGameID {
        if (adminGeneralController.activationStatus("2").equalsIgnoreCase("false")) {
            btnActivateDots.setDisable(false);
            btnDeactivateDots.setDisable(true);
        } else {
            btnActivateDots.setDisable(true);
            btnDeactivateDots.setDisable(false);
        }
    }
    public void playMouseSound(){
        File file = new File("src\\main\\resources\\Sound\\Click.mp3");
        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            btnIsActive();
        } catch (InvalidGameID invalidGameID) {
            invalidGameID.printStackTrace();
        }
        lblDotsDetails.setText(playerGeneralController.dotsDetails());
        lblDotsMvp.setText(strings[0]+" "+strings[1]+"PTS");
        lblDotsNumber.setText(adminGeneralController.numberOfTotalPlayedSecondGame());
    }
}
