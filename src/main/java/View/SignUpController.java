package View;

import Controller.AdminController.AdminGeneralController;
import Controller.CompetencyController.Validation;
import Controller.Exception.Plato.*;
import Controller.PlayerController.PlayerGeneralController;
import Controller.RegisterController.SignUp;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    protected SignUp processSignUp = new SignUp();
    protected static Validation validation = new Validation();
    protected static AdminGeneralController adminGeneralController = new AdminGeneralController();
    protected static PlayerGeneralController playerGeneralController = new PlayerGeneralController();

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
    private void signUp(ActionEvent event) {


        try {
//            File file = new File("src\\main\\resources\\Sound\\Time.mp3");
//            Media media = new Media(file.toURI().toString());
//            MediaPlayer mediaPlayer = new MediaPlayer(media);

            Validation.nameOrLastNameIsValid(txtName.getText());
            Validation.nameOrLastNameIsValid(txtLastname.getText());
            Validation.usernameIsValid(txtUsername.getText());
            Validation.emailIsValid(txtEmail.getText());
            Validation.phoneNumberIsValid(txtPhoneNum.getText());
            if (adminGeneralController.adminExistence().equalsIgnoreCase("true")){
                processSignUp.addPlayer(getInfo(txtName.getText(), txtLastname.getText(), txtUsername.getText(), txtPassword.getText(), txtEmail.getText(), txtPhoneNum.getText()));
                File image = createProfileFile(txtUsername.getText());
                copy(file,image);
                playerGeneralController.editProfileURL(txtUsername.getText(),String.valueOf(image.toPath()));
            }else if (adminGeneralController.adminExistence().equalsIgnoreCase("false")){
                processSignUp.addAdmin(getInfo(txtName.getText(), txtLastname.getText(), txtUsername.getText(), txtPassword.getText(), txtEmail.getText(), txtPhoneNum.getText()));
                File image = createProfileFile(txtUsername.getText());
                copy(file,image);
            }
            URL url = new File("src/main/resources/FXML/Login.fxml").toURI().toURL();
            Parent register = FXMLLoader.load(url);
            Scene message = new Scene(register);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(message);
            LoginController.mediaPlayer.stop();
//            mediaPlayer.stop();
            window.show();
        } catch (ExistUserNameException | EmptyExceptionForUserName e) {
            File file = new File("src\\main\\resources\\Images\\Error copy.png");
            Image image = new Image(file.toURI().toString());
            imgUsernameError.setImage(image);
        } catch (ExistEmailException | EmptyExceptionForEmail e) {
            File file = new File("src\\main\\resources\\Images\\Error copy.png");
            Image image = new Image(file.toURI().toString());
            imgEmailError.setImage(image);
        } catch (EmptyExceptionForName | EmptyExceptionForLastName emptyExceptionForName) {
            File file = new File("src\\main\\resources\\Images\\Error copy.png");
            Image image = new Image(file.toURI().toString());
            imgNameError.setImage(image);
        } catch (IOException | ExistAdminException e) {
            e.printStackTrace();
        } catch (InvalidEmailException | InvalidNameException | InvalidPhoneNumberException e) {
            System.err.println(e.getMessage());
            File file = new File("src\\main\\resources\\Images\\Error copy.png");
            Image image = new Image(file.toURI().toString());
            imgNameError.setImage(image);
        } catch (InvalidUserNameException e) {
            System.err.println(e.getMessage());
            File file = new File("src\\main\\resources\\Images\\Error copy.png");
            Image image = new Image(file.toURI().toString());
            imgUsernameError.setImage(image);
        }

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
    public void playMouseSound(){
        File file = new File("src\\main\\resources\\Sound\\Click.mp3");
        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    private String getInfo(String name, String lastName, String username, String password, String email, String phoneNum) {
        return name + " " + lastName + " " + username + " " + email + " " + password + " " + phoneNum;
    }

    @FXML
    private File chooseProfilePick(FileChooser fileChooser){
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
    @FXML
    public void selectProfilePic(ActionEvent event) {
        file = chooseProfilePick(new FileChooser());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        File file = new File("src\\main\\resources\\Sound\\Time.mp3");
//        Media media = new Media(file.toURI().toString());
//        MediaPlayer mediaPlayer = new MediaPlayer(media);
//        mediaPlayer.play();
        AdminMainMenu.mediaPlayerAdmin.stop();

    }


}
