package Client.View;

import Client.DataLoader;
import Controller.AdminController.AdminGeneralController;
import Controller.PlayerController.PlayerGeneralController;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class YourGameController implements Initializable {
    protected static AdminGeneralController adminGeneralController = new AdminGeneralController();
    protected static PlayerGeneralController playerGeneralController = new PlayerGeneralController();
    private static DataLoader dataLoader = new DataLoader();
    private static String playerFavoriteGames ;
    private static String playerSuggestionGames ;

    public static String getPlayerFavoriteGames() {
        return playerFavoriteGames;
    }

    public static void setPlayerFavoriteGames(String playerFavoriteGames) {
        YourGameController.playerFavoriteGames = playerFavoriteGames;
    }

    public static String getPlayerSuggestionGames() {
        return playerSuggestionGames;
    }

    public static void setPlayerSuggestionGames(String playerSuggestionGames) {
        YourGameController.playerSuggestionGames = playerSuggestionGames;
    }

    @FXML
    Pane pane1;
    @FXML
    Pane pane2;
    @FXML
    Pane pane3;
    @FXML
    Pane pane4;
    @FXML
    public JFXButton BtnClose;
    @FXML
    public JFXButton btnPlatoBotsMessages;
    @FXML
    public JFXButton btnBack;
    @FXML
    public JFXButton btnSearchFriends;
    @FXML
    public JFXButton btnEvents;
    @FXML
    public JFXButton btnFavoritesGames;
    @FXML
    private void closeApp(ActionEvent event){
        System.exit(1);
    }
    @FXML
    private void back(ActionEvent event) throws IOException {
        playMouseSound();
        URL url = new File("src/main/resources/FXML/PlayerMainMenu.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    @FXML
    private void goToPlayerEvents(ActionEvent event) throws IOException {
        playMouseSound();
        URL url = new File("src/main/resources/FXML/PlayerEvents.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    @FXML
    private void goToPlayerFavoritesGames(ActionEvent event) throws IOException {
        playMouseSound();
        URL url = new File("src/main/resources/FXML/YourGame.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    @FXML
    private void goToPlayerSearchFriends(ActionEvent event) throws IOException {
        playMouseSound();
        URL url = new File("src/main/resources/FXML/PlayerSearchFriends.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    @FXML
    private void goToPlayerPlatoBotsMessages(ActionEvent event) throws IOException {
        playMouseSound();
        URL url = new File("src/main/resources/FXML/PlayerPlatoBotsMessages.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    @FXML
    private void favoriteBattle(){
        pane1.toBack();
    }
    @FXML
    private void favoriteDots(){
        pane3.toBack();
    }
    @FXML
    private void suggestedBattle(){
        pane2.toBack();
    }
    @FXML
    private void suggestedDots(){
        pane4.toBack();
    }
    @FXML
    private void setpanes(){

            //todo work on it
            String[] favoriteGames =getPlayerFavoriteGames().split("\\$");
            String[] suggestionGames = getPlayerSuggestionGames().split("\\s");

//            if (favoriteGames[0].equalsIgnoreCase("battlesea") || favoriteGames[1].equalsIgnoreCase("battlesea") || favoriteGames[0].startsWith("b") || favoriteGames[1].startsWith("b")){
//                favoriteBattle();
//            }
//            if (favoriteGames[0].equalsIgnoreCase("DotsAndBoxes") || favoriteGames[1].equalsIgnoreCase("DotsAndBoxes") || favoriteGames[0].startsWith("d") || favoriteGames[1].startsWith("d")){
//                favoriteDots();
//            }

        for (String favoriteGame : favoriteGames) {
            if (favoriteGame.toUpperCase().startsWith("b")){
                favoriteBattle();

            }else if (favoriteGame.toLowerCase().startsWith("d")){
                favoriteDots();
            }
        }
            for (String suggestionGame : suggestionGames) {
                if (suggestionGame.toLowerCase().startsWith("b")) {
                    suggestedBattle();
                }
                if (suggestionGame.toLowerCase().startsWith("d")) {
                    suggestedDots();
                }
            }


    }
    @FXML
    private void playBattle(ActionEvent event) throws IOException {
        playMouseSound();
        URL url = new File("src/main/resources/FXML/BattleShipRunMenu.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    @FXML
    private void playDots(ActionEvent event) throws IOException {
        playMouseSound();
        URL url = new File("src/main/resources/FXML/DotsAndBoxesRunMenu.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    @FXML
    private void goGameLog(ActionEvent event) throws IOException {
        playMouseSound();
        URL url = new File("src/main/resources/FXML/GameLog.fxml").toURI().toURL();
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    setpanes();
    }
}
