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
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {
//    protected static AdminGeneralController adminGeneralController = new AdminGeneralController();
//    protected static PlayerGeneralController playerGeneralController = new PlayerGeneralController();
    private static DataLoader dataLoader = new DataLoader();

    protected static String usernameForShowProfile = "null";

    public static String getUsernameForShowProfile() {
        return usernameForShowProfile;
    }

    public static void setUsernameForShowProfile(String usernameForShowProfile) {
        ProfileController.usernameForShowProfile = usernameForShowProfile;
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
    Button btnBack;
    @FXML
    Label nameAndLastname;
    @FXML
    Label email;
    @FXML
    Label phoneNumber;
    @FXML
    Label numberOfFriendsLabel;
    @FXML
    Label winsLabel;
    @FXML
    Label loseLabel;
    @FXML
    Label platoAgeLabel;
    @FXML
    Label battleLabel;
    @FXML
    Label dotsLabel;
    @FXML
    public Pane EditPane;
    @FXML
    public Pane simplePane;
    @FXML
    public Button btnEdit;
    @FXML
    public Button btnCancel;
    @FXML
    public ComboBox<String> btnField;
    @FXML
    public Button btnGoToEditPane;
    @FXML
    public Button btnDelete;
    @FXML
    public Button btnEditBio;
    @FXML
    public TextField txtNewValue;
    @FXML
    TextArea bio;
    @FXML
    Label friendLabel;
    @FXML
    Pane friendPane;


    @FXML
    private void closeApp() {
        System.exit(1);
    }

    @FXML
    private void goToEdit(ActionEvent event) {
//        EditPane.toFront();
        simplePane.toBack();
    }

    @FXML
    private void cancelEdit(ActionEvent event) {
        playMouseSound();
        simplePane.toFront();
    }


    @FXML
    private void deleteAccount() throws IOException {
        playMouseSound();
        showConfirmPasswordPopUp();
        //todo make popup because need password
    }

    private void showConfirmPasswordPopUp() throws IOException {
        playMouseSound();
        URL url = new File("src/main/resources/FXML/ConfirmPasswordForDeleteAccount.fxml").toURI().toURL();
        AnchorPane root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void changePassword() throws IOException {
        playMouseSound();
        showChangePasswordPopUp();
    }
    private void showChangePasswordPopUp() throws IOException {
        playMouseSound();
        URL url = new File("src/main/resources/FXML/ChangePassword.fxml").toURI().toURL();
        AnchorPane root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void editEvent(ActionEvent event) throws IOException {
        playMouseSound();
        String response = "";
            if (txtNewValue.getText().isEmpty()){
                System.err.println("Value is Empty");
                return;
            }
            if (btnField.getValue().equals("Field")){
                System.err.println("Field is Empty");
                return;
            }

            if (btnField.getValue().toLowerCase().equals("name")) {
                response = dataLoader.editProfileDetails(LoginController.getUsername() , "name" , txtNewValue.getText());
            } else if (btnField.getValue().toLowerCase().equals("lastname")) {
                response = dataLoader.editProfileDetails(LoginController.getUsername() , "lastname" , txtNewValue.getText());
            } else if (btnField.getValue().toLowerCase().equals("email")) {
                response = dataLoader.editProfileDetails(LoginController.getUsername() , "email" , txtNewValue.getText());
            } else if (btnField.getValue().toLowerCase().equals("phonenumber")) {
                response = dataLoader.editProfileDetails(LoginController.getUsername() , "phonenumber" , txtNewValue.getText());
            }

            if (!response.equals("done")){
                System.err.println("There are Some Errors in Validation3");
                return;
            }


        URL url = new File("src/main/resources/FXML/Profile.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    @FXML
    private void setBtnEditBio(ActionEvent event) throws IOException {
        playMouseSound();
        dataLoader.editProfileBio(getUsernameForShowProfile(),bio.getText());
//        playerGeneralController.editBio(getUsernameForShowProfile(), bio.getText());
    }

    @FXML
    private void setImgStatusToProfile() throws IOException {
        playMouseSound();
        String[] userData = dataLoader.loadPlayerBasicInformation(getUsernameForShowProfile()).split("\\$");
//        String[] userData = playerGeneralController.showBasicInformation(getUsernameForShowProfile()).split("\\$");
        File file = new File(userData[6]);
        URL url = file.toURI().toURL();
        Image image = new Image(url.toExternalForm());
        imgStatus.setImage(image);
    }


    @FXML
    private void back(ActionEvent event) throws IOException {
        playMouseSound();
        setUsernameForShowProfile("null");
        URL url = new File("src/main/resources/FXML/PlayerMenu.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    @FXML
    private void setGameStatus() throws IOException {
        int wins = Integer.parseInt(dataLoader.numberOfWins(getUsernameForShowProfile(),"first")) + Integer.parseInt(dataLoader.numberOfWins(getUsernameForShowProfile(),"second"));
        int all = Integer.parseInt(dataLoader.numberOfLoses(getUsernameForShowProfile(),"first")) + Integer.parseInt(dataLoader.numberOfLoses(getUsernameForShowProfile(),"second"));
        int lose = all - wins;
        winsLabel.setText(String.valueOf(wins));
        loseLabel.setText(String.valueOf(lose));
//        numberOfPlayedLabel.setText(String.valueOf(all));
    }

    @FXML
    private void setFavoriteGames() throws IOException {
        playMouseSound();
        battleLabel.setText(dataLoader.firstGameNameGetter());
        dotsLabel.setText(dataLoader.secondGameNameGetter());

        String[] fav = dataLoader.loadPlayerFavoriteGames(getUsernameForShowProfile()).split("\\$");

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
        int level = Integer.parseInt(dataLoader.playerPoints(getUsernameForShowProfile()));

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

    @FXML
    private void setPlatoAgeLabel() throws IOException {
        platoAgeLabel.setText(dataLoader.playerAge(getUsernameForShowProfile()) + " Days in Plato ");
    }

    @FXML
    private void setProfilesLabels() throws IOException {
        String[] userData = dataLoader.loadPlayerBasicInformation(getUsernameForShowProfile()).split("\\$");
        nameAndLastname.setText(userData[1] + " " + userData[2] + "'s Profile");
        email.setText("Email: " + userData[0]);
        phoneNumber.setText("Phone number: " + userData[4]);
        bio.setText(userData[5]);
        String[] playerFriend = dataLoader.playerFriends(getUsernameForShowProfile()).split("\\$");

        numberOfFriendsLabel.setText(String.valueOf(playerFriend.length));
    }

    @FXML
    private void loadUserData() {

        try {
            setImgStatusToProfile();
            setImgMedal();
            setPlatoAgeLabel();
            setProfilesLabels();
            setGameStatus();
            setFavoriteGames();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    private void friendNumber() throws IOException {
        String[] friendList = dataLoader.playerFriends(getUsernameForShowProfile()).split("\\$");
        if (friendList.length!= 0){
            friendPane.toBack();
            friendLabel.setText("Number of friends : " + friendList.length);
        }
    }

    @FXML
    public void close(ActionEvent event) {
        System.exit(1);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUsernameForShowProfile(LoginController.getUsername());
        loadUserData();

        try {
            friendNumber();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void editProfilePic(ActionEvent event) throws IOException {

        File file = chooseProfilePick(new FileChooser());
        copy(file,createProfileFile(getUsernameForShowProfile()));
        playMouseSound();
        dataLoader.setUserProfile(getUsernameForShowProfile(),createProfileFile(getUsernameForShowProfile()).getPath());
        URL url = new File("src/main/resources/FXML/Profile.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    @FXML
    private File chooseProfilePick(FileChooser fileChooser){
        playMouseSound();
        FileChooser.ExtensionFilter images = new FileChooser.ExtensionFilter("Images","*.Jpg");
        fileChooser.getExtensionFilters().add(images);
        return fileChooser.showOpenDialog(new Stage());
    }
    @FXML
    private File createProfileFile(String username){
        String path ="src"+File.separator+"main"+File.separator+"resources"+File.separator+
                "Users"+File.separator+username+File.separator+username+".jpg";
        return new File(path);
    }
    @FXML
    private void copy(File pic , File dest) throws IOException {
        FileUtils.copyFile(pic,dest);
    }
    public void playMouseSound(){
        File file = new File("src\\main\\resources\\Sound\\Click.mp3");
        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
}
