package View;

import Controller.CompetencyController.Validation;
import Controller.Exception.Plato.*;
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
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    protected SignUp processSignUp = new SignUp();
    protected static Validation validation = new Validation();
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
            Validation.gameNameIsValid(txtUsername.getText());
            Validation.emailIsValid(txtPassword.getText());
            Validation.phoneNumberIsValid(txtPhoneNum.getText());
            processSignUp.addPlayer(getInfo(txtName.getText(), txtLastname.getText(), txtUsername.getText(), txtEmail.getText(), txtPassword.getText(), txtPhoneNum.getText()));
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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidEmailException | InvalidNameException | InvalidGameNameException | InvalidPhoneNumberException e) {
            System.err.println(e.getMessage());
            File file = new File("src\\main\\resources\\Images\\Error copy.png");
            Image image = new Image(file.toURI().toString());
            imgNameError.setImage(image);
        }

    }

    @FXML
    private void goToRegisterMenu(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/FXML/Login.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    private String getInfo(String name, String lastName, String username, String password, String email, String phoneNum) {
        return name + " " + lastName + " " + username + " " + email + " " + password + " " + phoneNum;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        File file = new File("src\\main\\resources\\Sound\\Time.mp3");
//        Media media = new Media(file.toURI().toString());
//        MediaPlayer mediaPlayer = new MediaPlayer(media);
//        mediaPlayer.play();

    }
}
