package Client.View;

import Client.Client;
import Client.DataLoader;
//import Server.Controller.Exception.Plato.BanExceptionForLogin;
import Server.Controller.Exception.Plato.ExistFriendException;
//import Server.Controller.Exception.Plato.InvalidUserNameException;
//import Server.Controller.Exception.Plato.WrongPasswordException;
import Server.Controller.PlayerController.FindPlayerByInfo;
import Server.Controller.PlayerController.PlayerGeneralController;
//import Server.Controller.RegisterController.LogIn;
import Server.Model.PlatoModel.Player;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class BattleShipRunMenu implements Initializable {
    private static final DataLoader dataLoader = new DataLoader();

    //    protected static PlayerGeneralController playerGeneralController = new PlayerGeneralController();
//    protected static LogIn logIn = new LogIn();
    private static long score = 10;

    public static long getScore() {
        return score;
    }

    public static void setScore(long score) {
        BattleShipRunMenu.score = score;
    }

    @FXML
    public Button btnSubmit;
    @FXML
    public JFXPasswordField txtPassword;
    @FXML
    public JFXTextField txtUsername;
    @FXML
    ComboBox<String> btnField;

    String username = LoginController.getUsername();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @FXML
    ListView<String> listViewFriends;


    @FXML
    private void goToBattleShipMainMenu(ActionEvent actionEvent) throws IOException {
        playMouseSound();

        URL url = new File("src/main/resources/FXML/BattleShipMainMenu.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();

    }

    //for change scene
    @FXML
    private void showGameRequests() {
        Stage stage = new Stage();
        Object root = null;
        try {
            URL url = new File("src/main/resources/FXML/GameRequests.fxml").toURI().toURL();
            root = FXMLLoader.load(url);
            stage.setScene(new Scene((Parent) root));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage window = (Stage) listViewFriends.getScene().getWindow();
        window.setScene(stage.getScene());
        window.show();
    }

    @FXML
    private void showGamePage() {
        Stage stage = new Stage();
        Object root = null;
        try {
            URL url = new File("src/main/resources/FXML/BattlePreparationTest.fxml").toURI().toURL();
            root = FXMLLoader.load(url);
            stage.setScene(new Scene((Parent) root));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage window = (Stage) listViewFriends.getScene().getWindow();
        window.setScene(stage.getScene());
        window.show();
    }

    public void playMouseSound() {
        File file = new File("src\\main\\resources\\Sound\\Click.mp3");
        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    private ArrayList<String> addToList() throws IOException {
        String response = dataLoader.onlinePlayerInThisGame("BattleShip");
        if (response.equals("No one Online For This Game")) {
            return null;
        }
        String[] friends = response.split("\\$");
        ArrayList<String> list = new ArrayList<>(Arrays.asList(friends));
        list.remove(LoginController.getUsername());
        return list;
    }

    @FXML
    private final ObservableList<String> observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            addToList();
            initActions();
        } catch (IOException e) {
            e.printStackTrace();
        }
        listViewFriends.setItems(observableList);
        try {
            listViewFriends.getItems().addAll(addToList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        requestGetterForPlay();
    }

    @FXML
    private void requestGetterForPlay() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                System.out.println("Timer");
                String response = "";
                String pass = "";
                try {
                    response = dataLoader.letsPlay(LoginController.getUsername());
                    pass = dataLoader.waitingToPlay(LoginController.getUsername());
                    System.out.println(response);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (pass.equalsIgnoreCase("true")) {
                    try {
                        dataLoader.setPassForPlay(LoginController.getUsername(), "false");
                        BattlePreparationControllerTest.setPlayer1(LoginController.getUsername());
                        BattlePreparationControllerTest.setPlayer2(dataLoader.enemyUsername(LoginController.getUsername()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    System.out.println("playyyyyyyyyyyyyyyyyyyyyyyy");
                    Platform.runLater(() -> showGamePage());
                    timer.cancel();
                }

                if (!response.equals("NO")) {
                    GameRequests.setUsernameThatSentRequest(response);
                    GameRequests.setGameNameForPlay("BattleShip");
                    System.out.println("play Time");
                    Platform.runLater(() -> showGameRequests());
                    timer.cancel();
                }

            }
        }, 0, 5000);


    }


    public void loginAsSecondPlayer(ActionEvent event) throws IOException {
        if (txtUsername.getText().equals(LoginController.getUsername())) {
            showError();
            return;
        }
        if (btnField.getValue().isEmpty()) {
            showError();
            return;
        }
        String response = dataLoader.login(txtUsername.getText(), txtPassword.getText());
        String[] split = response.split("\\s");

        if (response.equals("This Username is Ban By Admin. ")) {
            showBanError();
            return;
        }
        if (!split[0].equals("Success")) {
            showError();
            return;
        }
        BattlePreparationController.setPlayer1(this.username);
        BattlePreparationController.setPlayer2(txtUsername.getText());
        BattlePreparationController.setScore(getScore());
        BattleGameStartController.setTimeForGame(btnField.getValue());
        URL url = new File("src/main/resources/FXML/BattlePreparation.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();

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

    @FXML
    public void initActions() {
        listViewFriends.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent arg0) {
                String name = listViewFriends.getSelectionModel().getSelectedItem();
                txtUsername.setText(name);
                try {
                    dataLoader.playReq(LoginController.getUsername(), name);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
    }
}
