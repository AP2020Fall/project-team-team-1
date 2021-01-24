package Client.View;

import Server.Controller.AdminController.AdminGeneralController;
import Server.Controller.Exception.Plato.ExistFriendException;
import Server.Controller.PlayerController.PlayerGeneralController;
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
    protected static AdminGeneralController adminGeneralController = new AdminGeneralController();
    protected static PlayerGeneralController playerGeneralController = new PlayerGeneralController();

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
    private void closeApp(){
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
        if (FriendRequests.getUsernameForAcceptOrDecline().equalsIgnoreCase("null")){
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
        if (FriendProfileController.getUsernameOfFriend().equalsIgnoreCase("null")){
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
    private void setListViewForRequests()  {
        paneAcc.toFront();
        ObservableList<String> list = FXCollections.observableArrayList();
        listViewForRequests.setItems(list);
        String[] showEvent = new String[0];
        try {
            showEvent = playerGeneralController.showRequests(LoginController.getUsername()).split("\\$");
        } catch (ExistFriendException e) {
            System.err.println(e.getMessage());
            return;
        }
        for (String out : showEvent) {
            listViewForRequests.getItems().add(out);
        }
        initActionsForRequests();
    }
    @FXML
    public void initActionsForRequests(){
        listViewForRequests.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent arg0) {
                String name = listViewForRequests.getSelectionModel().getSelectedItem();
                FriendRequests.setUsernameForAcceptOrDecline(name);
            }

        });
    }


    @FXML
    private void setListViewForFriends(){
        paneFriend.toFront();
        ObservableList<String> list = FXCollections.observableArrayList();
        listViewFriends.setItems(list);
        String[] showEvent = new String[0];
        try {
            showEvent = playerGeneralController.showFriends(LoginController.getUsername()).split("\\$");
        } catch (ExistFriendException e) {
            System.err.println(e.getMessage());
            return;
        }

        for (String out : showEvent) {
            listViewFriends.getItems().add(out);
        }
        initActionsForFriends();
    }
    @FXML
    public void initActionsForFriends(){
        listViewFriends.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent arg0) {
                String name = listViewFriends.getSelectionModel().getSelectedItem();
                FriendProfileController.setUsernameOfFriend(name);
            }

        });
    }
    private void friendNumber() throws ExistFriendException {
        String[] friendList = playerGeneralController.showFriends(LoginController.getUsername()).split("\\$");
        if (friendList.length != 0) {
            friendPane.toBack();
            friendLabel.setText("You have " + friendList.length + " friends");
        }
    }
    public void playMouseSound(){
        File file = new File("src\\main\\resources\\Sound\\Click.mp3");
        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }



    @FXML
    public void close(ActionEvent event){
        System.exit(1);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            friendNumber();
        } catch (ExistFriendException e) {
            e.printStackTrace();
        }
//        try {
//            setListView();
//        } catch (ExistFriendException e) {
//            System.err.println("There is no Friend to show");
//        }
    }
}
