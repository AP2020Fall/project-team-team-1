package Client.View;

import Client.DataLoader;
import Server.Controller.Exception.Plato.GameActivation;
import Server.Controller.Exception.Plato.InvalidGameID;
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

public class AdminGamesBattleShip implements Initializable {

    private static DataLoader dataLoader = new DataLoader();
    String[] strings ;


    @FXML
    public Button btnGoToAdminBattleShipGame;
    @FXML
    public Button btnGoToAdminDotsGame;
    @FXML
    public Button btnDeActiveBattle;
    @FXML
    public Button btnActiveBattle;
    @FXML
    public Label lblBattleNumbers;
    @FXML
    public Label lblBattleMVP;
    @FXML
    public TextArea txtDetails;
    @FXML
    public Button btnEdit;
    @FXML
    public Label lblBattleDetails;
    public Button btnEditBattle;

    @FXML
    private void goToAdminDotsGame(ActionEvent event) throws IOException {
        playMouseSound();
        URL url = new File("src/main/resources/FXML/AdminGamesDotsAndBoxes.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    @FXML
    private void goToBattleGame(ActionEvent event) throws IOException {
        playMouseSound();
        URL url = new File("src/main/resources/FXML/AdminGamesBattleShip.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    @FXML
    private void goToLastMenu(ActionEvent event) throws IOException {
        playMouseSound();
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
    @FXML
    public void editBattleDetails(ActionEvent event) throws IOException {
        playMouseSound();
        dataLoader.setDetailForBattle("BattleShip",txtDetails.getText());
        URL url = new File("src/main/resources/FXML/AdminGamesBattleShip.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    @FXML
    public void activateBattle(ActionEvent event) throws InvalidGameID, IOException, GameActivation {
        playMouseSound();
        dataLoader.activeGame("1");
        URL url = new File("src/main/resources/FXML/AdminGamesBattleShip.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    @FXML
    public void deActivateBattleShip(ActionEvent event) throws InvalidGameID, IOException, GameActivation {
        playMouseSound();
        dataLoader.deActiveGame("1");
        URL url = new File("src/main/resources/FXML/AdminGamesBattleShip.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    @FXML
    private void btnIsActive() throws InvalidGameID, IOException {
        if (dataLoader.activeStatus("1").equalsIgnoreCase("false")) {
            btnActiveBattle.setDisable(false);
            btnDeActiveBattle.setDisable(true);
        } else {
            btnActiveBattle.setDisable(true);
            btnDeActiveBattle.setDisable(false);
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
            strings = dataLoader.mvpBattle().split("\\$");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            btnIsActive();
        } catch (InvalidGameID | IOException invalidGameID) {
            invalidGameID.printStackTrace();
        }
        try {
            lblBattleDetails.setText(dataLoader.battleShipDetails());
        } catch (IOException e) {
            e.printStackTrace();
        }
        lblBattleMVP.setText(strings[0]+" "+strings[1]+"PTS");
        try {
            lblBattleNumbers.setText(dataLoader.totalPlayedBattle());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
