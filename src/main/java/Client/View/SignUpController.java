package Client.View;

import Client.DataLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

//    protected SignUp processSignUp = new SignUp();
//    protected static Validation validation = new Validation();
//    protected static AdminGeneralController adminGeneralController = new AdminGeneralController();
//    protected static PlayerGeneralController playerGeneralController = new PlayerGeneralController();
    private static DataLoader dataLoader = new DataLoader();

    private File file;

    @FXML
    public Button btnProfile;
    @FXML
    Button btnExit;
    @FXML
    TextField txtName;
    @FXML
    TextField txtLastname;
    @FXML
    TextField txtUsername;
    @FXML
    TextField txtEmail;
    @FXML
    PasswordField txtPassword;
    @FXML
    TextField txtPhoneNum;
    @FXML
    Button btnSubmit;
    @FXML
    Button btnBack;
    @FXML
    ImageView imgUsernameError;
    @FXML
    ImageView imgEmailError;
    @FXML
    ImageView imgPasswordError;
    @FXML
    ImageView imgPhoneNumError;
    @FXML
    ImageView imgNameError;


    @FXML
    private void appExit(ActionEvent event) {
        System.exit(1);
    }

    @FXML
    private void signUp(ActionEvent event) throws IOException {
        boolean validationPass = true;
        turnOffErrors();


        if (txtName.getText().isEmpty() || !dataLoader.validationStatus("Name", txtName.getText()).equals("Valid Name")) {
            System.err.println("Error in Name Validation");
            imgNameError.setVisible(true);
            validationPass = false;
        }
        if (txtLastname.getText().isEmpty() || !dataLoader.validationStatus("LastName", txtLastname.getText()).equals("Valid LastName")) {
            System.err.println("Error in LastName Validation");
            imgNameError.setVisible(true);
            validationPass = false;
        }
        if (txtUsername.getText().isEmpty() || !dataLoader.validationStatus("Username", txtUsername.getText()).equals("Valid Username")) {
            System.err.println("Error in Username Validation");
            imgUsernameError.setVisible(true);
            validationPass = false;
        }
        if (txtEmail.getText().isEmpty() || !dataLoader.validationStatus("Email", txtEmail.getText()).equals("Valid Email")) {
            System.err.println("Error in Email Validation");
            imgEmailError.setVisible(true);
            validationPass = false;
        }
        if (txtPhoneNum.getText().isEmpty() || !dataLoader.validationStatus("PhoneNumber", txtPhoneNum.getText()).equals("Valid PhoneNumber")) {
            System.err.println("Error in PhoneNumber Validation");
            imgPhoneNumError.setVisible(true);
            validationPass = false;
        }
        if (txtPassword.getText().isEmpty() || !dataLoader.validationStatus("Password", txtPassword.getText()).equals("Valid Password")) {
            System.err.println("Error in Password Validation");
            imgPasswordError.setVisible(true);
            validationPass = false;
        }

        if (!validationPass) {
            return;
        }

        String result = dataLoader.register(getInfo(txtName.getText(), txtLastname.getText(), txtUsername.getText(), txtPassword.getText(), txtEmail.getText(), txtPhoneNum.getText()));

        if (result.equals("THIS USERNAME ALREADY BELONGS TO A USER")) {
            System.err.println(result);
            imgUsernameError.setVisible(true);
            return;
        }
        if (result.equals("THIS EMAIL ALREADY BELONGS TO A USER")) {
            System.err.println(result);
            imgEmailError.setVisible(true);
            return;
        }
        if (result.equals("THE ADMIN ALREADY EXISTS!")) {
            System.err.println(result);
            imgUsernameError.setVisible(true);
            return;
        }

        File image = createProfileFile(txtUsername.getText());
        copy(file, image);
        result = dataLoader.setUserProfile(txtUsername.getText(), String.valueOf(image.toPath()));

        if (result.equals("Failure")) {
            System.err.println("There is problem with Set Profile");
            return;
        }

        URL url = new File("src/main/resources/FXML/Login.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        LoginController.mediaPlayer.stop();
        window.show();

    }

    @FXML
    private void goToRegisterMenu(ActionEvent event) throws IOException {
        playMouseSound();
        URL url = new File("src/main/resources/FXML/Login.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    public void playMouseSound() {
        File file = new File("src\\main\\resources\\Sound\\Click.mp3");
        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    private String getInfo(String name, String lastName, String username, String password, String email, String phoneNum) {
        return name + " " + lastName + " " + username + " " + password + " " + email + " " + phoneNum;
    }

    @FXML
    private void turnOffErrors() {
        imgNameError.setVisible(false);
        imgUsernameError.setVisible(false);
        imgEmailError.setVisible(false);
        imgPhoneNumError.setVisible(false);
        imgPasswordError.setVisible(false);
    }

    @FXML
    private File chooseProfilePick(FileChooser fileChooser) {
        FileChooser.ExtensionFilter images = new FileChooser.ExtensionFilter("Images", "*.Jpg");
        fileChooser.getExtensionFilters().add(images);
        return fileChooser.showOpenDialog(new Stage());
    }

    @FXML
    private File createProfileFile(String username) {
        String path = "src" + File.separator + "main" + File.separator + "resources" + File.separator +
                "Users" + File.separator + username + File.separator + username + ".jpg";
        return new File(path);
    }

    @FXML
    private void copy(File pic, File dest) throws IOException {
        FileUtils.copyFile(pic, dest);
    }

    @FXML
    public void selectProfilePic(ActionEvent event) {
        file = chooseProfilePick(new FileChooser());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AdminMainMenu.mediaPlayerAdmin.stop();
        turnOffErrors();

    }


}
