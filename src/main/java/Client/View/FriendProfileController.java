package Client.View;

import Client.DataLoader;
import Server.Controller.AdminController.AdminGeneralController;
import Server.Controller.Exception.Plato.*;
import Server.Controller.PlayerController.PlayerGeneralController;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FriendProfileController implements Initializable {

    //    protected static AdminGeneralController adminGeneralController = new AdminGeneralController();
//    protected static PlayerGeneralController playerGeneralController = new PlayerGeneralController();
    private static DataLoader dataLoader = new DataLoader();

    protected static String usernameOfFriend = "null";

    public static String getUsernameOfFriend() {
        return usernameOfFriend;
    }

    public static void setUsernameOfFriend(String usernameOfFriend) {
        FriendProfileController.usernameOfFriend = usernameOfFriend;
    }

    @FXML
    ImageView imgStatus;
    @FXML
    ImageView imgMedal;
    @FXML
    ImageView favBattle;
    @FXML
    ImageView favDots;
    @FXML
    Button btnRemove;
    @FXML
    Button btnBack;
    @FXML
    Label nameAndLastname;
    @FXML
    Label email;
    @FXML
    Label phoneNumber;
    @FXML
    Label numberOfPlayedLabel;
    @FXML
    Label winsLabel;
    @FXML
    Label loseLabel;
    @FXML
    Label battleLabel;
    @FXML
    Label dotsLabel;
    @FXML
    Label platoAgeLabel;
    @FXML
    TextArea bio;


    @FXML
    private void closeApp() {
        System.exit(1);
    }


    @FXML
    private void setImgStatusToProfile() throws IOException {
//        String[] userData = playerGeneralController.showFriendProfile(LoginController.getUsername(), getUsernameOfFriend()).split("\\$");
        String[] userData = dataLoader.loadPlayerBasicInformation(getUsernameOfFriend()).split("\\$");
        //todo watch out
        File file = new File(userData[6]);
        Image image = new Image(file.toURI().toString());
        imgStatus.setImage(image);
    }

    @FXML
    private void setBtnRemove(ActionEvent event) throws IOException {
        playMouseSound();
        dataLoader.removeFriend(LoginController.getUsername(),getUsernameOfFriend());

        setUsernameOfFriend("null");

        {
            URL url = new File("src/main/resources/FXML/FriendsMainMenu.fxml").toURI().toURL();
            Parent register = FXMLLoader.load(url);
            Scene message = new Scene(register);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(message);
            window.show();
        }
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        setUsernameOfFriend("null");
        URL url = new File("src/main/resources/FXML/FriendsMainMenu.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    @FXML
    private void setGameStatus() throws IOException {
        int wins = Integer.parseInt(dataLoader.numberOfWins(getUsernameOfFriend(), "first")) + Integer.parseInt(dataLoader.numberOfWins(getUsernameOfFriend(), "second"));
        int all = Integer.parseInt(dataLoader.numberOfPlayThisGame(getUsernameOfFriend(), "first")) + Integer.parseInt(dataLoader.numberOfPlayThisGame(getUsernameOfFriend(), "second"));
        int lose = all - wins;
        winsLabel.setText(String.valueOf(wins));
        loseLabel.setText(String.valueOf(lose));
        numberOfPlayedLabel.setText(String.valueOf(all));
    }

    @FXML
    private void setPlatoAgeLabel() throws IOException {
        platoAgeLabel.setText(dataLoader.playerAge(getUsernameOfFriend()) + " Days in Plato ");
    }

    @FXML
    private void setProfilesLabels() throws IOException {
        String[] userData = dataLoader.loadPlayerBasicInformation(getUsernameOfFriend()).split("\\$");

        nameAndLastname.setText(userData[1] + " " + userData[2] + "'s Profile");
        email.setText("Email: " + userData[0]);
        phoneNumber.setText("Phone number: " + userData[4]);
        btnRemove.setText("Remove " + getUsernameOfFriend());
        bio.setText(userData[5]);
    }

    @FXML
    private void setFavoriteGames() throws IOException {
        battleLabel.setText(dataLoader.firstGameNameGetter());
        dotsLabel.setText(dataLoader.secondGameNameGetter());

        String[] fav = dataLoader.loadPlayerFavoriteGames(getUsernameOfFriend()).split("\\$");

        for (String gameName : fav) {
            if (gameName.startsWith("B") || gameName.startsWith("b")) {

                File file = new File("src\\main\\resources\\Icons\\addfav.png");
                Image image = new Image(file.toURI().toString());
                favBattle.setImage(image);

            }
        }
        for (String gameName : fav) {
            if (gameName.startsWith("D") || gameName.startsWith("d")) {

                File file = new File("src\\main\\resources\\Icons\\addfav.png");
                Image image = new Image(file.toURI().toString());
                favDots.setImage(image);

            }
        }
    }

    @FXML
    private void setImgMedal() throws IOException {
        int level = Integer.parseInt(dataLoader.playerPoints(getUsernameOfFriend()));

        if (level >= 200) {
            File file = new File("src\\main\\resources\\Images\\levelKing.png");
            Image image = new Image(file.toURI().toString());
            imgMedal.setImage(image);
        } else if (level >= 150) {
            File file = new File("src\\main\\resources\\Images\\levelWarrior.png");
            Image image = new Image(file.toURI().toString());
            imgMedal.setImage(image);

        } else if (level >= 100) {
            File file = new File("src\\main\\resources\\Images\\level1.png");
            Image image = new Image(file.toURI().toString());
            imgMedal.setImage(image);

        } else if (level >= 50) {
            File file = new File("src\\main\\resources\\Images\\level2.png");
            Image image = new Image(file.toURI().toString());
            imgMedal.setImage(image);

        } else {
            File file = new File("src\\main\\resources\\Images\\level3.png");
            Image image = new Image(file.toURI().toString());
            imgMedal.setImage(image);
        }
    }

    public void playMouseSound() {
        File file = new File("src\\main\\resources\\Sound\\Click.mp3");
        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    @FXML
    private void loadUserData() {

        try {
            setImgMedal();
            setImgStatusToProfile();
            setProfilesLabels();
            setGameStatus();
            setPlatoAgeLabel();
            setFavoriteGames();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @FXML
    public void close(ActionEvent event) {
        System.exit(1);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadUserData();
    }
}
