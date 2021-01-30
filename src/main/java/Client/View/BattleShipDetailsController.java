package Client.View;

import Client.DataLoader;
import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BattleShipDetailsController implements Initializable {

    private static final DataLoader dataLoader = new DataLoader();
    @FXML
    Label topic;
    @FXML
    Text detail;

    @FXML
    private void setDetail() throws IOException {
        //detail.setText(playerGeneralController.battleDetails());
        topic.setText(dataLoader.firstGameNameGetter() + "'s Details");
        final String content = dataLoader.battleShipDetails();

        final Animation animation = new Transition() {
            {
                setCycleDuration(Duration.millis(2000));
            }

            protected void interpolate(double frac) {
                final int length = content.length();
                final int n = Math.round(length * (float) frac);
                detail.setText(content.substring(0, n));
            }
        };

        animation.play();
    }
    @FXML
    private void goBattleShipMainMenu(ActionEvent actionEvent) throws IOException {
        playMouseSound();

        URL url = new File("src/main/resources/FXML/BattleShipMainMenu.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    public void playMouseSound(){
        File file = new File("src\\main\\resources\\Sound\\Click.mp3");
        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setDetail();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
