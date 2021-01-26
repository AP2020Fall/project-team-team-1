package Client.View;

import Client.DataLoader;
import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FriendsMainMenu implements Initializable {

    private static final DataLoader dataLoader = new DataLoader();

    @FXML
    JFXButton btnFriendRequests;
    @FXML
    JFXButton btnFriends;
    @FXML
    Pane paneForward;
    @FXML
    Pane paneAcc;
    @FXML
    Pane paneFriend;
    @FXML
    JFXButton btnBack;
    @FXML
    ListView<String> listViewForRequests;
    @FXML
    ListView<String> listViewFriends;
    @FXML
    Button btnAcceptOrDecline;
    @FXML
    Button btnShowOrRemove;
    @FXML
    Label friendLabel;
    @FXML
    Pane friendPane;


    @FXML
    private void closeApp() {
        System.exit(1);
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        playMouseSound();
        URL url = new File("src/main/resources/FXML/PlayerMenu.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    @FXML
    private void setBtnAcceptOrDecline(ActionEvent event) throws IOException {
        playMouseSound();
        if (FriendRequests.getUsernameForAcceptOrDecline().equalsIgnoreCase("null")) {
            return;
        }
        URL url = new File("src/main/resources/FXML/FriendRequests.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    @FXML
    private void setBtnShowOrRemove(ActionEvent event) throws IOException {
        playMouseSound();
        if (FriendProfileController.getUsernameOfFriend().equalsIgnoreCase("null")) {
            return;
        }
        System.out.println(FriendProfileController.getUsernameOfFriend());

        URL url = new File("src/main/resources/FXML/FriendProfile.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();

    }

    @FXML
    private void setListViewForRequests() throws IOException {
        paneAcc.toFront();
        ObservableList<String> list = FXCollections.observableArrayList();
        listViewForRequests.setItems(list);

        String response = dataLoader.playerRequests(LoginController.getUsername());
        if (response.equals("THERE ARE NO REQUESTS TO SHOW")) {
            System.err.println("THERE ARE NO REQUESTS TO SHOW");
            return;
        }

        String[] showEvent = response.split("\\$");
        for (String out : showEvent) {
            listViewForRequests.getItems().add(out);
        }
        initActionsForRequests();
    }

    @FXML
    public void initActionsForRequests() {
        listViewForRequests.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent arg0) {
                String name = listViewForRequests.getSelectionModel().getSelectedItem();
                FriendRequests.setUsernameForAcceptOrDecline(name);
            }

        });
    }


    @FXML
    private void setListViewForFriends() throws IOException {
        paneFriend.toFront();
        ObservableList<String> list = FXCollections.observableArrayList();
        listViewFriends.setItems(list);

        String response = dataLoader.playerFriends(LoginController.getUsername());
        if (response.equals("YOU DON'T HAVE ANY FRIENDS")) {
            System.err.println("YOU DON'T HAVE ANY FRIENDS");
            return;
        }


        String[] showEvent = response.split("\\$");

        for (String out : showEvent) {
            listViewFriends.getItems().add(out);
        }
        initActionsForFriends();
    }

    @FXML
    public void initActionsForFriends() {
        listViewFriends.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent arg0) {
                String name = listViewFriends.getSelectionModel().getSelectedItem();
                FriendProfileController.setUsernameOfFriend(name);
            }

        });
    }
    public void playMouseSound() {
        File file = new File("src\\main\\resources\\Sound\\Click.mp3");
        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }


    @FXML
    public void close(ActionEvent event) {
        System.exit(1);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
