package Client.View;

import Client.DataLoader;
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
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ConfirmPasswordControllerForDeleteAccount {
    private static final DataLoader dataLoader = new DataLoader();

    private String username = LoginController.getUsername();
    protected static String confirm = "false";

    public static String getConfirm() {
        return confirm;
    }

    public static void setConfirm(String confirm) {
        ConfirmPasswordControllerForDeleteAccount.confirm = confirm;
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
    private void close(ActionEvent event) {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void setBtnConfirm(ActionEvent event) throws IOException {
        playMouseSound();
        if (txtPassword.getText().isEmpty()){
            System.err.println("Field is Empty");
            return;
        }
        setConfirm(dataLoader.confirmPassWord(getUsername(),txtPassword.getText()));
        if (getConfirm().equalsIgnoreCase("false")) {
            showError();
        } else {
            deleteDirectory(new File("src" + File.separator + "main" + File.separator + "resources" + File.separator + "Users" + File.separator + getUsername()));
            dataLoader.deletePlayer(getUsername(),txtPassword.getText());
            setConfirm("false");
            System.exit(1);
            //todo Check this
        }

    }

    @FXML
    private void deleteDirectory(File fileForDelete) throws IOException {
        playMouseSound();
        FileUtils.deleteDirectory(fileForDelete);
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

    public void playMouseSound() {
        File file = new File("src\\main\\resources\\Sound\\Click.mp3");
        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
}
