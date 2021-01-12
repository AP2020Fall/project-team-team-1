package View;

import Controller.AdminController.AdminGeneralController;
import Controller.Exception.Plato.AcceptAndDeclineFriendException;
import Controller.Exception.Plato.ExistPlayerException;
import Controller.PlayerController.PlayerGeneralController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FriendRequests implements Initializable {
    protected static AdminGeneralController adminGeneralController = new AdminGeneralController();
    protected static PlayerGeneralController playerGeneralController = new PlayerGeneralController();

    protected static String usernameForAcceptOrDecline = "null";

    public static String getUsernameForAcceptOrDecline() {
        return usernameForAcceptOrDecline;
    }

    public static void setUsernameForAcceptOrDecline(String usernameForAcceptOrDecline) {
        FriendRequests.usernameForAcceptOrDecline = usernameForAcceptOrDecline;
    }

    @FXML
    ImageView imgStatus;
    @FXML
    Button btnAccept;
    @FXML
    Button btnDecline;
    @FXML
    Button btnBack;
    @FXML
    Label name;

    @FXML
    private void setImgStatusToCheck(){
        File file = new File("src\\main\\resources\\Images\\check.png");
        Image image = new Image(file.toURI().toString());
        imgStatus.setImage(image);
    }
    @FXML
    private void setImgStatusToCross(){
        File file = new File("src\\main\\resources\\Images\\cross.png");
        Image image = new Image(file.toURI().toString());
        imgStatus.setImage(image);
    }
    @FXML
    private void setImgStatusToBackArrow(){
        File file = new File("src\\main\\resources\\Images\\Nothing.png");
        Image image = new Image(file.toURI().toString());
        imgStatus.setImage(image);
    }

    @FXML
    private void setBtnAccept(ActionEvent event) throws IOException, ExistPlayerException, AcceptAndDeclineFriendException {
        playMouseSound();
        playerGeneralController.acceptRequest(LoginController.getUsername(),getUsernameForAcceptOrDecline());
        setUsernameForAcceptOrDecline("null");

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
    private void setBtnDecline(ActionEvent event) throws IOException, ExistPlayerException, AcceptAndDeclineFriendException {
        playMouseSound();
        playerGeneralController.declineRequest(LoginController.getUsername(),getUsernameForAcceptOrDecline());
        setUsernameForAcceptOrDecline("null");

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

        URL url = new File("src/main/resources/FXML/FriendsMainMenu.fxml").toURI().toURL();
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

    @FXML
    private void setName(){
        name.setText(getUsernameForAcceptOrDecline()+" Sent you request !");
    }
    @FXML
    public void close(ActionEvent event){
        System.exit(1);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setName();
    }
}
