package Client.View;

import Controller.AdminController.AdminGeneralController;
import Controller.Exception.Plato.*;
import Controller.PlayerController.PlayerGeneralController;
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
    protected static AdminGeneralController adminGeneralController = new AdminGeneralController();
    protected static PlayerGeneralController playerGeneralController = new PlayerGeneralController();

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
    private void setNumberOfReports() throws InvalidUserNameException{
        String[] numberOfReport;
        try {
            numberOfReport = adminGeneralController.showReportListOfPlayer(getUsernameProfileForShowToAdmin()).split("\\$");
        } catch (EmptyReportsList emptyReportsList) {
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
    private void setBtnBanAccount(){
        playMouseSound();
        if (adminGeneralController.playerActivation(getUsernameProfileForShowToAdmin()).equalsIgnoreCase("false")){
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
        playerGeneralController.deleteUser(getUsernameProfileForShowToAdmin());
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
    private void banAccount() throws AlreadyBan, IOException, ItsNotBan {
        playMouseSound();
        if (getUsernameProfileForShowToAdmin().equalsIgnoreCase("null")){
            return;
        }

        if (btnBanAccount.getText().equalsIgnoreCase("UnBan Account")){

            adminGeneralController.unBanPlayer(getUsernameProfileForShowToAdmin());

        }else {

            adminGeneralController.banPlayer(getUsernameProfileForShowToAdmin());

        }

    }
    @FXML
    private void makeSuggestionGoToPane(){
        frontOfMakeSuggestion.toBack();
    }

    @FXML
    private void makeSuggestion(ActionEvent event) throws IOException, ExistPlayerException {
        playMouseSound();
        if (getUsernameProfileForShowToAdmin().equalsIgnoreCase("null")){
            return;
        }
        StringBuilder string = new StringBuilder();
        string.append(getUsernameProfileForShowToAdmin()).append(" ").append(suggestionCombo.getValue());
        adminGeneralController.addSuggestion(String.valueOf(string));


        URL url = new File("src/main/resources/FXML/ShowUserProfileForAdmin.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }


    @FXML
    private void setSuggestionCombo(){
        suggestionCombo.getItems().add(adminGeneralController.firstGameNameGetter());
        suggestionCombo.getItems().add(adminGeneralController.secondGameNameGetter());
    }


    @FXML
    private void setImgStatusToProfile() throws ExistPlayerException {
        String[] userData = playerGeneralController.showBasicInformation(getUsernameProfileForShowToAdmin()).split("\\$");
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
    private void setGameStatus() throws InvalidGameNameException {
        int wins = Integer.parseInt(playerGeneralController.showNumberOFWins(getUsernameProfileForShowToAdmin(), adminGeneralController.secondGameNameGetter())) + Integer.parseInt(playerGeneralController.showNumberOFWins(getUsernameProfileForShowToAdmin(), adminGeneralController.firstGameNameGetter()));
        int all = Integer.parseInt(playerGeneralController.showNumberOfGamePlayedInThisGame(getUsernameProfileForShowToAdmin(), adminGeneralController.secondGameNameGetter())) + Integer.parseInt(playerGeneralController.showNumberOfGamePlayedInThisGame(getUsernameProfileForShowToAdmin(), adminGeneralController.firstGameNameGetter()));
        int lose = all - wins;
        winsLabel.setText(String.valueOf(wins));
        loseLabel.setText(String.valueOf(lose));
        numberOfPlayedLabel.setText(String.valueOf(all));
    }


    @FXML
    private void setImgMedal() throws ExistPlayerException {
        int level = Integer.parseInt(playerGeneralController.showPoint(getUsernameProfileForShowToAdmin()));

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
    private void setPlatoAgeLabel() throws ExistPlayerException {
        platoAgeLabel.setText(playerGeneralController.showUserAge(getUsernameProfileForShowToAdmin()) + " Days in Plato ");
    }

    @FXML
    private void setProfilesLabels() throws ExistPlayerException {
        String[] userData = playerGeneralController.showBasicInformation(getUsernameProfileForShowToAdmin()).split("\\$");

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
            setSuggestionCombo();
            setImgMedal();
            setPlatoAgeLabel();
            setProfilesLabels();
            setGameStatus();
            setBtnBanAccount();
            setImgStatusToProfile();
            setNumberOfReports();
        } catch ( InvalidUserNameException e) {
            System.err.println(e.getMessage()+"1");
        } catch (InvalidGameNameException e) {
            System.err.println(e.getMessage()+"2");
        } catch (ExistPlayerException e) {
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
