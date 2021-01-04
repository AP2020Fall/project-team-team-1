package View;

import Controller.AdminController.AdminGeneralController;
import Controller.Exception.Plato.ExistFavoriteException;
import Controller.Exception.Plato.ExistSuggestionException;
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
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PlayerFavoritesGamesController implements Initializable {
    protected static AdminGeneralController adminGeneralController = new AdminGeneralController();
    protected static PlayerGeneralController playerGeneralController = new PlayerGeneralController();

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
        URL url = new File("src/main/resources/FXML/PlayerMainMenu.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    @FXML
    private void goToPlayerEvents(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/FXML/PlayerEvents.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    @FXML
    private void goToPlayerFavoritesGames(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/FXML/PlayerFavoritesGames.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    @FXML
    private void goToPlayerSearchFriends(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/FXML/PlayerSearchFriends.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    @FXML
    private void goToPlayerPlatoBotsMessages(ActionEvent event) throws IOException {
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
        try {
            String[] favoriteGames = playerGeneralController.showFavoritesGames(LoginController.getUsername()).split("\\$");
            String[] suggestionGames = playerGeneralController.showSuggestion(LoginController.getUsername()).split("\\$");
//            for (String favoriteGame : favoriteGames) {
//                if (favoriteGame.equals("B")){
//                    favoriteBattle();
//                    System.out.println("amir khare");
//                }
//                if (favoriteGame.toLowerCase().equals("d")){
//                    favoriteDots();
//                }
//                System.out.println("amir gave");
//            }
//            for (String suggestionGame : suggestionGames) {
//                if (!suggestionGame.equals("B")){
//                    suggestedBattle();
//                }
//                if (suggestionGame.toLowerCase().equals("d")){
//                    suggestedDots();
//                }
//            }
            if (favoriteGames[0].equalsIgnoreCase("battlesea") || favoriteGames[1].equalsIgnoreCase("battlesea")){
                favoriteBattle();
            }
            if (favoriteGames[0].equalsIgnoreCase("DotsAndBoxes") || favoriteGames[1].equalsIgnoreCase("DotsAndBoxes")){
                favoriteDots();
            }
            if (suggestionGames[0].equalsIgnoreCase("DotsAndBoxes") || suggestionGames[1].equalsIgnoreCase("DotsAndBoxes")){
                suggestedDots();
            }
            if (suggestionGames[0].equalsIgnoreCase("battlesea") || suggestionGames[1].equalsIgnoreCase("battlesea")){
                suggestedBattle();
            }

        } catch (ExistFavoriteException e) {
            System.err.println(e.getMessage());
        } catch (ExistSuggestionException e) {
            System.err.println(e.getMessage());
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    setpanes();
    }
}
