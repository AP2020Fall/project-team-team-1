package Client.View;

import Client.DataLoader;
import Server.Model.PlatoModel.Event;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PlayerEventsController implements Initializable {

    private static final DataLoader dataLoader = new DataLoader();

    private static ArrayList<Event> eventForShow ;

    public static ArrayList<Event> getEventForShow() {
        return eventForShow;
    }

    public static void setEventForShow(ArrayList<Event> eventForShow) {
        PlayerEventsController.eventForShow = eventForShow;
    }

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
    public TableView<Event> tableView = new TableView<>();
    @FXML
    public TableColumn<Event, String> game = new TableColumn<>();
    @FXML
    public TableColumn<Event,String> comment = new TableColumn<>();
    @FXML
    public TableColumn<Event, Integer> points = new TableColumn<>();
    @FXML
    public TableColumn<Event, LocalDate> start = new TableColumn<>();
    @FXML
    public TableColumn<Event, LocalDate> end = new TableColumn<>();
    @FXML
    public TableColumn<Event,Integer> id = new TableColumn<>();
    @FXML
    public Button btnJoin;
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
    public void playMouseSound(){
        File file = new File("src\\main\\resources\\Sound\\Click.mp3");
        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            dataLoader.loadEventsList();
            YourGameController.setPlayerFavoriteGames(dataLoader.loadPlayerFavoriteGames(LoginController.getUsername()));
            YourGameController.setPlayerSuggestionGames(dataLoader.loadPlayerSuggestedGames(LoginController.getUsername()));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        id.setCellValueFactory(new PropertyValueFactory<>("eventID"));
        game.setCellValueFactory(new PropertyValueFactory<>("GameName"));
        start.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        end.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        points.setCellValueFactory(new PropertyValueFactory<>("score"));
        comment.setCellValueFactory(new PropertyValueFactory<>("Comment"));

        tableView.setItems(events);
        for (Event event : eventForShow) {
            tableView.getItems().add(event);
        }

    }
    @FXML
    private final ObservableList<Event> events = FXCollections.observableArrayList();

    @FXML
    private void joinEvent(ActionEvent event) throws IOException {
        //todo Handel this
        if (dataLoader.loadEventActivation(String.valueOf(tableView.getSelectionModel().getSelectedItem().getEventID())).equals("true")){
           dataLoader.playerJoinEvent(LoginController.getUsername(), String.valueOf(tableView.getSelectionModel().getSelectedItem().getEventID()));
            if (tableView.getSelectionModel().getSelectedItem().getGameName().startsWith("b")||tableView.getSelectionModel().getSelectedItem().getGameName().startsWith("B")){
                BattleShipRunMenu.setScore(Long.parseLong(String.valueOf(tableView.getSelectionModel().getSelectedItem().getScore())));
                URL url = new File("src/main/resources/FXML/BattleShipRunMenu.fxml").toURI().toURL();
                Parent register = FXMLLoader.load(url);
                Scene message = new Scene(register);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(message);
                window.show();
            }if (tableView.getSelectionModel().getSelectedItem().getGameName().startsWith("d")||tableView.getSelectionModel().getSelectedItem().getGameName().startsWith("D")){
                DotsAndBoxesRunMenu.setPoint(Long.parseLong(String.valueOf(tableView.getSelectionModel().getSelectedItem().getScore())));
                URL url = new File("src/main/resources/FXML/DotsAndBoxesRunMenu.fxml").toURI().toURL();
                Parent register = FXMLLoader.load(url);
                Scene message = new Scene(register);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(message);
                window.show();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("NOT ACTIVE EVENT");
            alert.setContentText("This Event Is Not Active Yet Wait Until "+tableView.getSelectionModel().getSelectedItem().getStartDate());
            alert.showAndWait();
        }
    }
}
