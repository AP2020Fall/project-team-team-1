package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BattleWinnerController implements Initializable {

    private static String winnerPlayerUsername = "";

    public static String getWinnerPlayerUsername() {
        return winnerPlayerUsername;
    }

    public static void setWinnerPlayerUsername(String winnerPlayerUsername) {
        BattleWinnerController.winnerPlayerUsername = winnerPlayerUsername;
    }

    @FXML
    Label winnerPlayer;

    @FXML
    private void backToMenu(ActionEvent event) throws IOException {
        GameStartController.mediaPlayer.stop();
        URL url = new File("src/main/resources/FXML/GameMenu.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        winnerPlayer.setText(getWinnerPlayerUsername()+" Is The Winner !");
    }
}
