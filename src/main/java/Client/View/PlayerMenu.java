package Client.View;

import Client.DataLoader;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class PlayerMenu implements Initializable {
    private static final DataLoader dataLoader = new DataLoader();
    Timer timer = new Timer();

    @FXML
    Button tButton;
    @FXML
    Button xButton;
    @FXML
    Button sButton;
    @FXML
    Button oButton;
    @FXML
    Pane pane;
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
        playMouseSound();
        timer.cancel();

        URL url = new File("src/main/resources/FXML/PlayerMainMenu.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    @FXML
    public void gotoGamesMenu(ActionEvent event) throws IOException {
        playMouseSound();
        timer.cancel();

        URL url = new File("src/main/resources/FXML/GameMenu.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    @FXML
    public void goToProfile(ActionEvent event) throws IOException {
        playMouseSound();
        timer.cancel();

        URL url = new File("src/main/resources/FXML/Profile.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    @FXML
    public void logOut(ActionEvent event) throws IOException {
        playMouseSound();
        timer.cancel();

        dataLoader.makePlayerOffline(LoginController.getUsername());
        LoginController.setUsername("null");
        LoginController.setPlayer(null);
        URL url = new File("src/main/resources/FXML/Login.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    @FXML
    public void goToFriendsMenu(ActionEvent event) throws IOException {
        playMouseSound();
        timer.cancel();

        URL url = new File("src/main/resources/FXML/FriendsMainMenu.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    @FXML
    public void close(ActionEvent event){
        System.exit(1);
    }

    public void playMouseSound(){
        File file = new File("src\\main\\resources\\Sound\\Click.mp3");
        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
    @FXML
    private void broken() {

        Stage stage = new Stage();
        Object root = null;
        try {
            URL url = new File("src/main/resources/FXML/Login.fxml").toURI().toURL();
            root = FXMLLoader.load(url);
            stage.setScene(new Scene((Parent) root));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage window = (Stage) BtnLogOut.getScene().getWindow();
        window.setScene(stage.getScene());
        window.show();
    }
    @FXML
    private void timerForBroken() {
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                timer.cancel();
                try {
                    dataLoader.makePlayerOffline(LoginController.getUsername());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                LoginController.setUsername("null");
                LoginController.setPlayer(null);
                //broken();
                Platform.runLater(() -> broken());
            }
        }, 30000, 30000);


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        oAnimation();
        xAnimation();
        sAnimation();
        tAnimation();
        LoginController.mediaPlayer.stop();
        timerForBroken();
    }

    public void xAnimation(){
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(10), xButton);
        rotateTransition.setByAngle(360);
        rotateTransition.setRate(10);
        rotateTransition.setCycleCount(10);
        rotateTransition.play();
    }
    public void oAnimation(){
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(10), oButton);
        rotateTransition.setByAngle(360);
        rotateTransition.setRate(10);
        rotateTransition.setCycleCount(10);
        rotateTransition.play();
    }
    public void sAnimation(){
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(10), sButton);
        rotateTransition.setByAngle(360);
        rotateTransition.setRate(10);
        rotateTransition.setCycleCount(10);
        rotateTransition.play();
    }
    public void tAnimation(){
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(10), tButton);
        rotateTransition.setByAngle(360);
        rotateTransition.setRate(10);
        rotateTransition.setCycleCount(10);
        rotateTransition.play();
    }
}
