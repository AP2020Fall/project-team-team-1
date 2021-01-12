package View;

import Controller.AdminController.AdminGeneralController;
import Controller.Exception.Plato.*;
import Controller.PlayerController.PlayerGeneralController;
import com.jfoenix.controls.JFXTextArea;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
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

    protected static AdminGeneralController adminGeneralController = new AdminGeneralController();
    protected static PlayerGeneralController playerGeneralController = new PlayerGeneralController();

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
    private void setImgStatusToProfile() throws ExistFriendException {
        String[] userData = playerGeneralController.showFriendProfile(LoginController.getUsername(), getUsernameOfFriend()).split("\\$");
        //todo watch out
        File file = new File(userData[8]);
        Image image = new Image(file.toURI().toString());
        imgStatus.setImage(image);
    }

    @FXML
    private void setBtnRemove(ActionEvent event) throws IOException, ExistFriendException, ExistPlayerException {
        playMouseSound();
        playerGeneralController.removeFriend(LoginController.getUsername(), getUsernameOfFriend());
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
    private void setGameStatus() throws InvalidGameNameException {
        int wins = Integer.parseInt(playerGeneralController.showNumberOFWins(getUsernameOfFriend(), adminGeneralController.secondGameNameGetter())) + Integer.parseInt(playerGeneralController.showNumberOFWins(LoginController.getUsername(), adminGeneralController.firstGameNameGetter()));
        int all = Integer.parseInt(playerGeneralController.showNumberOfGamePlayedInThisGame(getUsernameOfFriend(), adminGeneralController.secondGameNameGetter())) + Integer.parseInt(playerGeneralController.showNumberOfGamePlayedInThisGame(LoginController.getUsername(), adminGeneralController.firstGameNameGetter()));
        int lose = all - wins;
        winsLabel.setText(String.valueOf(wins));
        loseLabel.setText(String.valueOf(lose));
        numberOfPlayedLabel.setText(String.valueOf(all));
    }

    @FXML
    private void setPlatoAgeLabel() throws ExistPlayerException {
        platoAgeLabel.setText(playerGeneralController.showUserAge(getUsernameOfFriend()) + " Days in Plato ");
    }

    @FXML
    private void setProfilesLabels() throws ExistFriendException {
        String[] userData = playerGeneralController.showFriendProfile(LoginController.getUsername(), getUsernameOfFriend()).split("\\$");
        for (String userDatum : userData) {
            System.out.println(userDatum);
        }
        nameAndLastname.setText(userData[2] + " " + userData[3] + "'s Profile");
        email.setText("Email: " + userData[4]);
        phoneNumber.setText("Phone number: " + userData[5]);
        btnRemove.setText("Remove " + userData[1]);
        bio.setText(userData[7]);
    }

    @FXML
    private void setFavoriteGames() throws ExistFavoriteException {
        battleLabel.setText(adminGeneralController.firstGameNameGetter());
        dotsLabel.setText(adminGeneralController.secondGameNameGetter());

        String[] fav = playerGeneralController.showFavoritesGames(getUsernameOfFriend()).split("\\$");

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
    private void setImgMedal() throws ExistPlayerException {
        int level = Integer.parseInt(playerGeneralController.showPoint(getUsernameOfFriend()));

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
    public void playMouseSound(){
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
        } catch (ExistPlayerException | ExistFriendException | InvalidGameNameException | ExistFavoriteException e) {
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
