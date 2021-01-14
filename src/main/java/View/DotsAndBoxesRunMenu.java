package View;

import Controller.Exception.Plato.BanExceptionForLogin;
import Controller.Exception.Plato.ExistFriendException;
import Controller.Exception.Plato.InvalidUserNameException;
import Controller.Exception.Plato.WrongPasswordException;
import Controller.PlayerController.PlayerGeneralController;
import Controller.RegisterController.LogIn;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class DotsAndBoxesRunMenu implements Initializable {
    protected static PlayerGeneralController playerGeneralController = new PlayerGeneralController();
    protected static LogIn logIn = new LogIn();
    public Button btnSubmit;
    public JFXPasswordField txtPassword;
    public JFXTextField txtUsername;
    private static long point=10;

    String username =LoginController.getUsername();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @FXML
    ListView<String> listViewFriends;

    public static long getPoint() {
        return point;
    }

    public static void setPoint(long point) {
        DotsAndBoxesRunMenu.point = point;
    }

    @FXML
    private void goToDotsAndBoxesMainMenu(ActionEvent actionEvent) throws IOException {
        playMouseSound();

        URL url = new File("src/main/resources/FXML/DotsAndBoxesMainMenu.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();

    }
    public void playMouseSound(){
        File file = new File("src\\main\\resources\\Sound\\Click.mp3");
        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
    private ArrayList<String>  addToList() throws ExistFriendException {
        String[] friends = playerGeneralController.showFriends(this.getUsername()).split("\\$");
        System.out.println(friends);
        return new ArrayList<>(Arrays.asList(friends));
    }
    @FXML
    private final ObservableList<String> observableList=FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
         //System.out.println("Hello");
        try {
            addToList();
        } catch (ExistFriendException e) {
            e.printStackTrace();
        }
        listViewFriends.setItems(observableList);
        try {
            listViewFriends.getItems().addAll(addToList());
        } catch (ExistFriendException e) {
            e.printStackTrace();
        }
    }

    public void loginAsSecondPlayer(ActionEvent event) throws IOException {
        try {
            logIn.loginAsPlayer(txtUsername.getText()+" "+txtPassword.getText());
            DotsAndBoxesGame dotsAndBoxesGame = new DotsAndBoxesGame();
            dotsAndBoxesGame.setFirstPlayer(this.username);
            System.out.println(this.username);
            dotsAndBoxesGame.setSecondPlayer(txtUsername.getText());
            System.out.println(txtUsername.getText());
            dotsAndBoxesGame.setPoint(getPoint());
            URL url = new File("src/main/resources/FXML/DotsAndBoxesGame.fxml").toURI().toURL();
            Parent register = FXMLLoader.load(url);
            Scene message = new Scene(register);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(message);
            window.show();
        } catch (InvalidUserNameException | WrongPasswordException e) {
            showError();
        } catch (BanExceptionForLogin banExceptionForLogin) {
            showBanError();
        }
    }
    private void showError() throws IOException {
        URL url = new File("src/main/resources/FXML/LoginError.fxml").toURI().toURL();

        AnchorPane root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }

    private void showBanError() throws IOException {
        URL url = new File("src/main/resources/FXML/ErrorBan.fxml").toURI().toURL();

        AnchorPane root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }
}
