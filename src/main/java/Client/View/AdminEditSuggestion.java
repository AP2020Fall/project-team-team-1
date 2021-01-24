package Client.View;

import Server.Controller.AdminController.AdminGeneralController;
import Server.Controller.Exception.Plato.ExistSuggestionException;
import Server.Model.PlatoModel.Player;
import Server.Model.PlatoModel.Suggestion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminEditSuggestion implements Initializable {

    protected static AdminGeneralController adminGeneralController = new AdminGeneralController();

    int chosenID ;


    @FXML
    private TableView<Suggestion> table;
    @FXML
    private TableColumn<Suggestion, Integer> tblID;

    @FXML
    private TableColumn<Suggestion, Player> tblPlayer;

    @FXML
    private TableColumn<Suggestion, String> tblGame;

    @FXML
    void back(ActionEvent event) throws IOException {
        playMouseSound();
        URL url = new File("src/main/resources/FXML/AdminMenu.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    @FXML
    void deleteSuggestion(ActionEvent event) throws IOException, ExistSuggestionException {
        playMouseSound();
        adminGeneralController.removeSuggestion(String.valueOf(chosenID));
        chosenID=0;
        URL url = new File("src/main/resources/FXML/AdminEditSuggestion.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    @FXML
    void exit(ActionEvent event) {
        System.exit(1);
    }

    @FXML
    void goToBotsMessages(ActionEvent event) throws IOException {
        playMouseSound();
        URL url = new File("src/main/resources/FXML/SendMessageAsPlatoBot.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    @FXML
    void goToSuggestions(ActionEvent event) throws IOException {
        playMouseSound();
        URL url = new File("src/main/resources/FXML/AdminEditSuggestion.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    private final ObservableList<Suggestion> suggestions = FXCollections.observableArrayList();

    private void setTableInfo(){
        tblID.setCellValueFactory(new PropertyValueFactory<>("suggestionID"));
        tblGame.setCellValueFactory(new PropertyValueFactory<>("suggestedGame"));
        tblPlayer.setCellValueFactory(new PropertyValueFactory<>("playerName"));
        suggestions.addAll(Suggestion.getAllSuggestions());
        table.setItems(suggestions);
    }
    public void playMouseSound(){
        File file = new File("src\\main\\resources\\Sound\\Click.mp3");
        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTableInfo();
    }
}
