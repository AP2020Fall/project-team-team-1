package Client.View;

import Client.DataLoader;

import Server.Model.PlatoModel.Player;
import Server.Model.PlatoModel.Suggestion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminEditSuggestion implements Initializable {


    private static DataLoader dataLoader = new DataLoader();

    int chosenID ;
    private static ArrayList<Suggestion> allSuggestion;

    public static ArrayList<Suggestion> getAllSuggestion() {
        return allSuggestion;
    }

    public static void setAllSuggestion(ArrayList<Suggestion> allSuggestion) {
        AdminEditSuggestion.allSuggestion = allSuggestion;
    }

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
    void deleteSuggestion(ActionEvent event) throws IOException {
        playMouseSound();
        if (chosenID==0){
            System.err.println("amir gave so choose id");
            return;
        }
        dataLoader.removeSuggestion(String.valueOf(chosenID));
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
        suggestions.addAll(allSuggestion);
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
        try {
            dataLoader.loadSuggestion();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setTableInfo();
        table.setRowFactory(tv->{
            TableRow<Suggestion> row = new TableRow<>();
            row.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (!row.isEmpty()){

                        chosenID = row.getItem().getSuggestionID();
                    }
                }
            });
            return row;
        });
    }
}
