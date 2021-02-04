package Client.View;

import Client.DataLoader;

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
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ShowUserProfileForAdminController implements Initializable {

    private static final DataLoader dataLoader = new DataLoader();

    private static String usernameProfileForShowToAdmin = "null";

    public static String getUsernameProfileForShowToAdmin() {
        return usernameProfileForShowToAdmin;
    }

    public static void setUsernameProfileForShowToAdmin(String usernameProfileForShowToAdmin) {
        ShowUserProfileForAdminController.usernameProfileForShowToAdmin = usernameProfileForShowToAdmin;
    }

    @FXML
    ImageView imgStatus;
    @FXML
    ImageView imgMedal;
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
    Label platoAgeLabel;
    @FXML
    Label numberOfReports;
    @FXML
    public ComboBox<String> suggestionCombo;
    @FXML
    TextArea bio;
    @FXML
    Button btnDeleteAccount;
    @FXML
    Button btnBanAccount;
    @FXML
    Button btnGoToSuggestionPane;
    @FXML
    Pane frontOfMakeSuggestion;
    @FXML
    Button btnMakeSuggestion;


    @FXML
    private void closeApp() {
        System.exit(1);
    }

    @FXML
    private void setNumberOfReports() {
        String[] numberOfReport;
        try {
            numberOfReport = dataLoader.reportedPlayers(getUsernameProfileForShowToAdmin()).split("\\$");
        } catch (IOException emptyReportsList) {
            System.err.println(emptyReportsList.getMessage());
            numberOfReports.setText("0");
            numberOfReports.setTextFill(Color.web("#00ff00"));
            return;
        }
        if (numberOfReport.length > 3){
            numberOfReports.setText(String.valueOf(numberOfReport.length));
            numberOfReports.setTextFill(Color.web("#FF0000"));
            return;
        }

        numberOfReports.setText(String.valueOf(numberOfReport.length));
    }
    @FXML
    private void setBtnBanAccount() throws IOException {
        playMouseSound();
        if (dataLoader.playerActivityState(getUsernameProfileForShowToAdmin()).equalsIgnoreCase("false")){
            btnBanAccount.setText("UnBan Account");
        }else
            btnBanAccount.setText("ban Account");
    }


    @FXML
    private void setBtnDeleteAccount(ActionEvent event) throws IOException {
        playMouseSound();
        if (getUsernameProfileForShowToAdmin().equalsIgnoreCase("null")){
            return;
        }
        dataLoader.deletePlayer(getUsernameProfileForShowToAdmin());
        setUsernameProfileForShowToAdmin("null");
        {
            URL url = new File("src/main/resources/FXML/AdminMainMenu.fxml").toURI().toURL();
            Parent register = FXMLLoader.load(url);
            Scene message = new Scene(register);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(message);
            window.show();
        }

    }
    @FXML
    private void banAccount(ActionEvent event) throws IOException {
        playMouseSound();
        if (getUsernameProfileForShowToAdmin().equalsIgnoreCase("null")){
            return;
        }

        if (btnBanAccount.getText().equalsIgnoreCase("UnBan Account")){

            dataLoader.playerUnBanState(getUsernameProfileForShowToAdmin());

        }else {

            dataLoader.playerBanState(getUsernameProfileForShowToAdmin());

        }
        URL url = new File("src/main/resources/FXML/ShowUserProfileForAdmin.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();

    }
    @FXML
    private void makeSuggestionGoToPane(){
        frontOfMakeSuggestion.toBack();
    }

    @FXML
    private void makeSuggestion(ActionEvent event) throws IOException{
        playMouseSound();
        if (getUsernameProfileForShowToAdmin().equalsIgnoreCase("null")){
            return;
        }
        StringBuilder string = new StringBuilder();
        string.append(getUsernameProfileForShowToAdmin()).append(" ").append(suggestionCombo.getValue());
        dataLoader.addSuggestion(String.valueOf(string));


        URL url = new File("src/main/resources/FXML/ShowUserProfileForAdmin.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }


    @FXML
    private void setSuggestionCombo() throws IOException {
        suggestionCombo.getItems().add(dataLoader.firstGameNameGetter());
        suggestionCombo.getItems().add(dataLoader.secondGameNameGetter());
    }


    @FXML
    private void setImgStatusToProfile() throws  IOException {
        String[] userData = dataLoader.loadPlayerBasicInformation(getUsernameProfileForShowToAdmin()).split("\\$");
        File file = new File(userData[6]);
        Image image = new Image(file.toURI().toString());
        imgStatus.setImage(image);
    }


    @FXML
    private void back(ActionEvent event) throws IOException {

        URL url = new File("src/main/resources/FXML/AdminMenu.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    @FXML
    private void setGameStatus() throws IOException {
        int wins = Integer.parseInt(dataLoader.numberOfWins(getUsernameProfileForShowToAdmin(),"first")) + Integer.parseInt(dataLoader.numberOfWins(getUsernameProfileForShowToAdmin(),"second"));
        int all = Integer.parseInt(dataLoader.numberOfPlayThisGame(getUsernameProfileForShowToAdmin(),"first")) + Integer.parseInt(dataLoader.numberOfPlayThisGame(getUsernameProfileForShowToAdmin(),"second"));
        int lose = all - wins;
        winsLabel.setText(String.valueOf(wins));
        loseLabel.setText(String.valueOf(lose));
        numberOfPlayedLabel.setText(String.valueOf(all));
    }


    @FXML
    private void setImgMedal() throws IOException {
        int level = Integer.parseInt(dataLoader.playerPoints(getUsernameProfileForShowToAdmin()));

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
        platoAgeLabel.setText(dataLoader.playerAge(getUsernameProfileForShowToAdmin()) + " Days in Plato ");
    }

    @FXML
    private void setProfilesLabels() throws IOException {
        String[] userData = dataLoader.loadPlayerBasicInformation(getUsernameProfileForShowToAdmin()).split("\\$");

        for (String s : userData) {
            System.out.println(s);
        }
        nameAndLastname.setText(userData[1] + " " + userData[2] + "'s Profile");
        email.setText("Email: " + userData[0]);
        phoneNumber.setText("Phone number: " + userData[4]);
        bio.setText(userData[5]);

    }

    @FXML
    private void loadUserData() {
        try {
            setBtnBanAccount();
            setSuggestionCombo();
            setImgMedal();
            setPlatoAgeLabel();
            setProfilesLabels();
            setGameStatus();
            //setBtnBanAccount();
            setImgStatusToProfile();
            setNumberOfReports();

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
    public void playMouseSound(){
        File file = new File("src\\main\\resources\\Sound\\Click.mp3");
        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
}
