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

public class BattleShipMainMenuController implements Initializable {

    private static final DataLoader dataLoader = new DataLoader();
    Boolean isFav = false;

    @FXML
    Button btnExit;
    @FXML
    Button btnFav;
    @FXML
    ImageView btnfavImage;
    @FXML
    Button backToGameMenu;
    @FXML
    Label labelBattle;


    /********************Loaders********************/

    @FXML
    private void loadFavStatus() throws IOException {
        //playMouseSound();
        String response = dataLoader.loadPlayerFavoriteGames(LoginController.getUsername());
        if (response.equals("There is no Favorite Games")) {
            System.err.println("There is no Favorite Games");
            return;
        }

        String[] fav = response.split("\\$");
        for (String gameName : fav) {
            if (gameName.startsWith("B") || gameName.startsWith("b")) {
                File file = new File("src\\main\\resources\\Icons\\addfav.png");
                Image image = new Image(file.toURI().toString());
                btnfavImage.setImage(image);
                isFav = true;

            }
        }

    }

    /********************Methods********************/
    @FXML
    private void setBtnFav(ActionEvent actionEvent) throws IOException {

        if (!isFav) {
            dataLoader.addPlayerFavoriteGames(LoginController.getUsername(), "first");
            File file = new File("src\\main\\resources\\Icons\\addfav.png");
            Image image = new Image(file.toURI().toString());
            btnfavImage.setImage(image);
            return;
        } else {
            dataLoader.removePlayerFavoriteGames(LoginController.getUsername(), "first");
            File file = new File("src\\main\\resources\\Icons\\removefav.png");
            Image image = new Image(file.toURI().toString());
            btnfavImage.setImage(image);
        }
        isFav = false;
        loadFavStatus();


    }

    @FXML
    private void goGameMenu(ActionEvent actionEvent) throws IOException {
        playMouseSound();

        URL url = new File("src/main/resources/FXML/GameMenu.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }


    /********************Go To Menus********************/
    @FXML
    private void appExit(ActionEvent event) {
        System.exit(1);
    }

    @FXML
    private void goDetails(ActionEvent actionEvent) throws IOException {
        playMouseSound();

        URL url = new File("src/main/resources/FXML/BattleShipDetails.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();


    }

    @FXML
    private void goToHistory(ActionEvent actionEvent) throws IOException {
        playMouseSound();

        URL url = new File("src/main/resources/FXML/BattleShipHistory.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();

    }

    @FXML
    private void goToScoreBoard(ActionEvent actionEvent) throws IOException {
        playMouseSound();

        URL url = new File("src/main/resources/FXML/BattleShipScoreBoard.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    @FXML
    private void runGame(ActionEvent actionEvent) throws IOException {
        playMouseSound();
        dataLoader.changePlayerStatus(LoginController.getUsername(),"BattleShip");
        URL url = new File("src/main/resources/FXML/BattleShipRunMenu.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();

    }

    public void playMouseSound() {
        File file = new File("src\\main\\resources\\Sound\\Click.mp3");
        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            dataLoader.changePlayerStatus(LoginController.getUsername(),"Login");
            loadFavStatus();
            labelBattle.setText("WELCOME TO ".concat(dataLoader.firstGameNameGetter()));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
