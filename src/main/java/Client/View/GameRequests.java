package Client.View;

import Client.DataLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class GameRequests implements Initializable {

    private static final DataLoader dataLoader = new DataLoader();

    protected static String usernameThatSentRequest = "null";

    protected static String gameNameForPlay = "null";

    public static String getUsernameThatSentRequest() {
        return usernameThatSentRequest;
    }

    public static void setUsernameThatSentRequest(String usernameThatSentRequest) {
        GameRequests.usernameThatSentRequest = usernameThatSentRequest;
    }

    public static String getGameNameForPlay() {
        return gameNameForPlay;
    }

    public static void setGameNameForPlay(String gameNameForPlay) {
        GameRequests.gameNameForPlay = gameNameForPlay;
    }

    @FXML
    ImageView imgStatus;
    @FXML
    Button btnAccept;
    @FXML
    Button btnDecline;

    @FXML
    Label name;

    @FXML
    private void setImgStatusToCheck() {
        File file = new File("src\\main\\resources\\Images\\check.png");
        Image image = new Image(file.toURI().toString());
        imgStatus.setImage(image);
    }

    @FXML
    private void setImgStatusToCross() {
        File file = new File("src\\main\\resources\\Images\\cross.png");
        Image image = new Image(file.toURI().toString());
        imgStatus.setImage(image);
    }

    @FXML
    private void setImgStatusToBackArrow() {
        File file = new File("src\\main\\resources\\Images\\Nothing.png");
        Image image = new Image(file.toURI().toString());
        imgStatus.setImage(image);
    }

    @FXML
    private void setBtnAccept(ActionEvent event) throws IOException {
        playMouseSound();
        dataLoader.setPassForPlay(getUsernameThatSentRequest(),"true");
        //todo set players and Score
        dataLoader.gameMatcher(LoginController.getUsername(),getUsernameThatSentRequest(),"10");

        setUsernameThatSentRequest("null");
        if (getGameNameForPlay().toLowerCase().startsWith("b")){
            URL url = new File("src/main/resources/FXML/BattlePreparationTest.fxml").toURI().toURL();
            Parent register = FXMLLoader.load(url);
            Scene message = new Scene(register);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(message);
            window.show();
        }else {
            URL url = new File("src/main/resources/FXML/DotsAndBoxesGameTest.fxml").toURI().toURL();
            Parent register = FXMLLoader.load(url);
            Scene message = new Scene(register);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(message);
            window.show();
        }

    }

    @FXML
    private void setBtnDecline(ActionEvent event) throws IOException {
        playMouseSound();

        setUsernameThatSentRequest("null");
        dataLoader.playReq("NO",LoginController.getUsername());
        if (getGameNameForPlay().toLowerCase().startsWith("b")) {
            URL url = new File("src/main/resources/FXML/BattleShipRunMenu.fxml").toURI().toURL();
            Parent register = FXMLLoader.load(url);
            Scene message = new Scene(register);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(message);
            window.show();
        }else {
            URL url = new File("src/main/resources/FXML/DotsAndBoxesRunMenu.fxml").toURI().toURL();
            Parent register = FXMLLoader.load(url);
            Scene message = new Scene(register);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(message);
            window.show();
        }
    }

    public void playMouseSound() {
        File file = new File("src\\main\\resources\\Sound\\Click.mp3");
        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    @FXML
    private void setName() {
        name.setText(getUsernameThatSentRequest() + " Sent you request !");
    }

    @FXML
    public void close(ActionEvent event) {
        System.exit(1);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setName();
    }
}
