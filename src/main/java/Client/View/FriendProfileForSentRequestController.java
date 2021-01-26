package Client.View;

import Client.DataLoader;
import com.jfoenix.controls.JFXTextArea;
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
import java.util.ResourceBundle;

public class FriendProfileForSentRequestController implements Initializable {

    private static final DataLoader dataLoader = new DataLoader();


    private static String usernameOfFriendForSentRequest = "null";

    public static String getUsernameOfFriendForSentRequest() {
        return usernameOfFriendForSentRequest;
    }

    public static void setUsernameOfFriendForSentRequest(String usernameOfFriendForSentRequest) {
        FriendProfileForSentRequestController.usernameOfFriendForSentRequest = usernameOfFriendForSentRequest;
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
    Button btnSentRequest;
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
    JFXTextArea bio;


    @FXML
    private void closeApp() {
        System.exit(1);
    }


    @FXML
    private void setImgStatusToProfile() throws IOException {
        String[] userData = dataLoader.loadPlayerBasicInformation(getUsernameOfFriendForSentRequest()).split("\\$");
        //todo watch out
        File file = new File(userData[6]);
        Image image = new Image(file.toURI().toString());
        imgStatus.setImage(image);
    }

    @FXML
    private void setBtnSentRequest(ActionEvent event) throws IOException {
        playMouseSound();
        if (getUsernameOfFriendForSentRequest().equalsIgnoreCase("null")) {
            return;
        }

        dataLoader.sentFriendRequest(LoginController.getUsername(), getUsernameOfFriendForSentRequest());

        setUsernameOfFriendForSentRequest("null");

        {
            URL url = new File("src/main/resources/FXML/PlayerSearchFriends.fxml").toURI().toURL();
            Parent register = FXMLLoader.load(url);
            Scene message = new Scene(register);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(message);
            window.show();
        }
    }

    @FXML
    private void back(ActionEvent event) throws IOException {

        URL url = new File("src/main/resources/FXML/PlayerSearchFriends.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    @FXML
    private void setGameStatus() throws IOException {
        int wins = Integer.parseInt(dataLoader.numberOfWins(getUsernameOfFriendForSentRequest(), "first")) + Integer.parseInt(dataLoader.numberOfWins(LoginController.getUsername(), "second"));
        int all = Integer.parseInt(dataLoader.numberOfPlayThisGame(getUsernameOfFriendForSentRequest(), "first")) + Integer.parseInt(dataLoader.numberOfPlayThisGame(LoginController.getUsername(), "second"));
        int lose = all - wins;
        winsLabel.setText(String.valueOf(wins));
        loseLabel.setText(String.valueOf(lose));
        numberOfPlayedLabel.setText(String.valueOf(all));
    }

    @FXML
    private void setPlatoAgeLabel() throws IOException {
        platoAgeLabel.setText(dataLoader.playerAge(getUsernameOfFriendForSentRequest()) + " Days in Plato ");
    }

    @FXML
    private void setProfilesLabels() throws IOException {
        String[] userData = dataLoader.loadPlayerBasicInformation(getUsernameOfFriendForSentRequest()).split("\\$");
        nameAndLastname.setText(userData[1] + " " + userData[2] + "'s Profile");
        email.setText("Email: " + userData[0]);
        //phoneNumber.setText("Phone number: " + userData[5]);
        //btnRemove.setText("Remove " + userData[1]);
        bio.setText(userData[5]);
    }

    @FXML
    private void setFavoriteGames() throws IOException {
        playMouseSound();
        battleLabel.setText(dataLoader.firstGameNameGetter());
        dotsLabel.setText(dataLoader.secondGameNameGetter());

        String[] fav = dataLoader.loadPlayerFavoriteGames(getUsernameOfFriendForSentRequest()).split("\\$");

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
        int level = Integer.parseInt(dataLoader.playerPoints(getUsernameOfFriendForSentRequest()));

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
            System.err.println(e.getMessage());
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
