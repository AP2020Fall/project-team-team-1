package Client.View;

import Client.DataLoader;
import Server.Controller.CompetencyController.Existence;
import Server.Controller.Exception.Plato.InvalidPasswordException;
import Server.Controller.Exception.Plato.SamePasswordException;
import Server.Controller.Exception.Plato.StrongerPasswordException;
import Server.Controller.Exception.Plato.WrongPasswordException;
import Server.Controller.PlayerController.PlayerGeneralController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ConfirmChangePassword {

    private static DataLoader dataLoader = new DataLoader();

    private  String username = LoginController.getUsername();

    protected static String confirm = "false";

    public static String getConfirm() {
        return confirm;
    }

    public static void setConfirm(String confirm) {
        ConfirmChangePassword.confirm = confirm;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @FXML
    Button btnBack;
    @FXML
    Button btnConfirm;
    @FXML
    PasswordField txtPassword;
    @FXML
    PasswordField txtNewPassword;


    @FXML
    private void close(ActionEvent event){
        playMouseSound();
        Stage stage = (Stage)btnBack.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void setBtnConfirm(ActionEvent event) throws IOException {
        playMouseSound();
        if (txtPassword.getText().isEmpty() || txtNewPassword.getText().isEmpty()){
            System.err.println("Fields are Empty");
            return;
        }
        setConfirm(dataLoader.confirmPassWord(getUsername(),txtPassword.getText()));
        if (getConfirm().equalsIgnoreCase("false")){
            showError();
        }
        else {

            String response = dataLoader.editPassWord(getUsername(),txtPassword.getText(),txtNewPassword.getText());
            if (!response.equals("done")){
                System.err.println("There is Problem");
                return;
            }
            setConfirm("false");
            close(event);
            //todo Check this
        }

    }
    public void playMouseSound(){
        File file = new File("src\\main\\resources\\Sound\\Click.mp3");
        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
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
}
