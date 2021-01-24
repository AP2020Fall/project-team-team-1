package Client.View;

import Server.Controller.AdminController.AdminGeneralController;
import Server.Controller.Exception.Plato.ExistFavoriteException;
import Server.Controller.Exception.Plato.InvalidGameNameException;
import Server.Controller.PlayerController.PlayerGeneralController;
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

public class DotsAndBoxesMainMenuController implements Initializable {
    protected static AdminGeneralController adminGeneralController = new AdminGeneralController();
    protected static PlayerGeneralController playerGeneralController = new PlayerGeneralController();
    String secondGame = adminGeneralController.secondGameNameGetter();

    @FXML
    Button btnExit;
    @FXML
    Button btnFav;
    @FXML
    ImageView btnfavImage;
    @FXML
    Button backToGameMenu;
    @FXML
    Label labelDots;


    /********************Loaders********************/

    @FXML
    private void loadFavStatus() {
        //playMouseSound();
        String[] fav = new String[0];
        try {
            fav = playerGeneralController.showFavoritesGames(LoginController.getUsername()).split("\\$");
        } catch (ExistFavoriteException e) {
            System.err.println(e.getMessage());
            return;
        }

        for (String gameName : fav) {
            if (gameName.startsWith("D") || gameName.startsWith("d")) {
                File file = new File("src\\main\\resources\\Icons\\addfav.png");
                Image image = new Image(file.toURI().toString());
                btnfavImage.setImage(image);

            }
        }

    }

    /********************Methods********************/
    @FXML
    private void setBtnFav(ActionEvent actionEvent) throws IOException, InvalidGameNameException, ExistFavoriteException {
        playMouseSound();
        String[] fav = new String[0];
        try {
            fav = playerGeneralController.showFavoritesGames(LoginController.getUsername()).split("\\$");
        } catch (ExistFavoriteException e) {
            playerGeneralController.addGameToFavoritesGames(LoginController.getUsername(), adminGeneralController.secondGameNameGetter());
            File file = new File("src\\main\\resources\\Icons\\addfav.png");
            Image image = new Image(file.toURI().toString());
            btnfavImage.setImage(image);
            return;

        }

        String gameNameForFav = "nothing";

        for (String gameName : fav) {
            if (gameName.startsWith("D") || gameName.startsWith("d")) {

                File file = new File("src\\main\\resources\\Icons\\addfav.png");
                Image image = new Image(file.toURI().toString());
                btnfavImage.setImage(image);
                gameNameForFav = gameName;

            }
        }
        if (gameNameForFav.equalsIgnoreCase("nothing")) {
            playerGeneralController.addGameToFavoritesGames(LoginController.getUsername(), adminGeneralController.secondGameNameGetter());
            File file = new File("src\\main\\resources\\Icons\\addfav.png");
            Image image = new Image(file.toURI().toString());
            btnfavImage.setImage(image);
            return;
        } else {
            playerGeneralController.RemoveFavoritesGames(LoginController.getUsername(), gameNameForFav);
            File file = new File("src\\main\\resources\\Icons\\removefav.png");
            Image image = new Image(file.toURI().toString());
            btnfavImage.setImage(image);
        }


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

        URL url = new File("src/main/resources/FXML/DotsAndBoxesDetails.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();


    }

    @FXML
    private void goToHistory(ActionEvent actionEvent) throws IOException {
        playMouseSound();

        URL url = new File("src/main/resources/FXML/DotsAndBoxesHistory.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();

    }

    @FXML
    private void goToScoreBoard(ActionEvent actionEvent) throws IOException {
        playMouseSound();

        URL url = new File("src/main/resources/FXML/DotsAndBoxesScoreBoard.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    @FXML
    private void runGame(ActionEvent actionEvent) throws IOException {
        playMouseSound();

        URL url = new File("src/main/resources/FXML/DotsAndBoxesRunMenu.fxml").toURI().toURL();
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
        loadFavStatus();
        labelDots.setText("WELCOME TO ".concat(secondGame));

    }
}
