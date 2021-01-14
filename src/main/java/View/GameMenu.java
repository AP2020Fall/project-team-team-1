package View;

import Controller.AdminController.AdminGeneralController;
import Controller.Exception.Plato.InvalidGameID;
import Controller.PlayerController.PlayerGeneralController;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


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
    Button xButton;
    @FXML
    Button oButton;
    @FXML
    Button tButton;
    @FXML
    Button sButton;
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
        playMouseSound();
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
    @FXML
    private void goDotsAndBoxesMenu(ActionEvent actionEvent) throws InvalidGameID, IOException {
        playMouseSound();
        if (adminGeneralController.activationStatus("1").equalsIgnoreCase("false") || adminGeneralController.maintenanceStatus("1").equalsIgnoreCase("true")){
            showError();
            return;
        }
        URL url = new File("src/main/resources/FXML/DotsAndBoxesMainMenu.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    @FXML
    public void goToPlayerMenu(ActionEvent event) throws IOException {
        playMouseSound();
        URL url = new File("src/main/resources/FXML/PlayerMenu.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    @FXML
    public void goToGameLog(ActionEvent event) throws IOException {
        playMouseSound();
        URL url = new File("src/main/resources/FXML/GameLog.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
    public void playMouseSound(){
        File file = new File("src\\main\\resources\\Sound\\Click.mp3");
        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        xAnimation();
        sAnimation();
        oAnimation();
        tAnimation();
        btnBattle.setText(firsGame);
        btnDots.setText(secondGame);

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
