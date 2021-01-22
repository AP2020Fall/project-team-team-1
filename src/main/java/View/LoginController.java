package View;

import Client.Client;
import Controller.AdminController.AdminGeneralController;
import Controller.CompetencyController.Existence;
import Client.DataLoader;
import Controller.PlayerController.PlayerGeneralController;
import Controller.RegisterController.LogIn;
import Controller.RegisterController.SignUp;
import Model.PlatoModel.Admin;
import Model.PlatoModel.Player;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginController implements Initializable {
    private static DataLoader dataLoader = new DataLoader();

    protected static PlayerGeneralController playerGeneralController = new PlayerGeneralController();
    protected static SignUp processSignupController = new SignUp();
    protected static LogIn processLoginController = new LogIn();
    protected static AdminGeneralController adminGeneralController = new AdminGeneralController();
    protected static Existence existence = new Existence();
    private static final File file = new File("src\\main\\resources\\Sound\\Got.mp3");
    protected static Media media = new Media(file.toURI().toString());
    protected static MediaPlayer mediaPlayer = new MediaPlayer(media);

    private static Player player = null;
    private static Admin admin = null;
    private static String username = "null";

    public static Player getPlayer() {
        return player;
    }

    public static void setPlayer(Player player) {
        LoginController.player = player;
    }

    public static Admin getAdmin() {
        return admin;
    }

    public static void setAdmin(Admin admin) {
        LoginController.admin = admin;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        LoginController.username = username;
    }

    @FXML
    Button btnExit;
    @FXML
    TextField txtUsername;
    @FXML
    PasswordField txtPassword;
    @FXML
    Button btnSubmit;
    @FXML
    Button btnRegister;
    @FXML
    CheckBox checkBox;


    @FXML
    private void appExit(ActionEvent event) {
        System.exit(1);
    }

    @FXML
    private void goToRegisterMenu(ActionEvent event) throws IOException {
        playMouseSound();

        URL url = new File("src/main/resources/FXML/SignUpMenu.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    @FXML
    private void login(ActionEvent event) throws IOException {
        playMouseSound();
        Gson gson = new Gson();
        File file = new File("src\\main\\resources\\Sound\\Time.mp3");
        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        Client.getDataOutputStream().writeUTF("login "+txtUsername.getText()+","+txtPassword.getText());
        Client.getDataOutputStream().flush();
        String response = Client.getDataInputStream().readUTF();

        if (response.startsWith("Admin")){

            admin = dataLoader.loadAdmin(txtUsername.getText());
            setUsername(txtUsername.getText());
            URL url = new File("src/main/resources/FXML/AdminMenu.fxml").toURI().toURL();
            Parent register = FXMLLoader.load(url);
            Scene message = new Scene(register);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(message);
            mediaPlayer.stop();
            window.show();

        }else if (response.startsWith("Player")){

            player = dataLoader.loadPlayer(txtUsername.getText());
            setUsername(txtUsername.getText());
            URL url = new File("src/main/resources/FXML/PlayerMenu.fxml").toURI().toURL();
            Parent register = FXMLLoader.load(url);
            Scene message = new Scene(register);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(message);
            window.show();


        }

//        try {
//            if (adminGeneralController.getAdminUserName().equals(txtUsername.getText())) {
//                processLoginController.loginAsAdmin(getInfo(txtUsername.getText(), txtPassword.getText()));
//                setUsername(txtUsername.getText());
//                URL url = new File("src/main/resources/FXML/AdminMenu.fxml").toURI().toURL();
//                Parent register = FXMLLoader.load(url);
//                Scene message = new Scene(register);
//                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                window.setScene(message);
//                mediaPlayer.stop();
//                window.show();
//            } else {
////                if (playerGeneralController.rememberPasswordStatus(txtUsername.getText()).equalsIgnoreCase("true")){
////                    txtPassword.setText(playerGeneralController.getUsernamePassword(txtUsername.getText()));
////                }
//                processLoginController.loginAsPlayer(getInfo(txtUsername.getText(), txtPassword.getText()));
//                remember(checkBox);
//                setUsername(txtUsername.getText());
//                URL url = new File("src/main/resources/FXML/PlayerMenu.fxml").toURI().toURL();
//                Parent register = FXMLLoader.load(url);
//                Scene message = new Scene(register);
//                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                window.setScene(message);
//                window.show();
//            }
//        } catch (InvalidUserNameException | WrongPasswordException  | ExistAdminException e) {
//            showError();
//        } catch (BanExceptionForLogin e) {
//            showBanError();
//        }

    }

    private void showError() throws IOException {
        URL url = new File("src/main/resources/FXML/LoginError.fxml").toURI().toURL();

        AnchorPane root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }

    private void showBanError() throws IOException {
        URL url = new File("src/main/resources/FXML/ErrorBan.fxml").toURI().toURL();

        AnchorPane root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }

    private String getInfo(String txtUsername, String txtPassword) {
        return txtUsername + " " + txtPassword;
    }

    public void playMouseSound(){
        File file = new File("src\\main\\resources\\Sound\\Click.mp3");
        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
    private void remember(CheckBox checkBox){
        if (checkBox.mnemonicParsingProperty().getValue().equals(false)){
            try {
                playerGeneralController.setRememberPasswordStatus(txtUsername.getText(),"true");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (checkBox.mnemonicParsingProperty().getValue().equals(true)){
            try {
                playerGeneralController.setRememberPasswordStatus(txtUsername.getText(),"false");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        File file = new File("src\\main\\resources\\Sound\\Time.mp3");
//        Media media = new Media(file.toURI().toString());
//        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(0.8);
        mediaPlayer.play();
        AdminMainMenu.mediaPlayerAdmin.stop();
//        txtUsername.setText("amir");
//        if (playerGeneralController.rememberPasswordStatus(txtUsername.getText()).equalsIgnoreCase("true")){
//            txtPassword.setText(playerGeneralController.getUsernamePassword(txtUsername.getText()));
//        }
   }
}
