package View;

import Controller.AdminController.AdminGeneralController;
import Controller.Exception.Plato.ExistEventException;
import Controller.PlayerController.PlayerGeneralController;
import Model.PlatoModel.Event;
import com.google.gson.Gson;
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
import java.util.Date;
import java.util.ResourceBundle;

public class PlayerEventsController implements Initializable {
    protected static PlayerGeneralController playerGeneralController = new PlayerGeneralController();
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
        id.setCellValueFactory(new PropertyValueFactory<>("eventID"));
        game.setCellValueFactory(new PropertyValueFactory<>("GameName"));
        start.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        end.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        points.setCellValueFactory(new PropertyValueFactory<>("score"));
        comment.setCellValueFactory(new PropertyValueFactory<>("Comment"));

//        Event.addNewEvent(new Event(1,"battleship",LocalDate.of(2021,2,2),LocalDate.of(2021,2,3),20,"hi"));
        tableView.setItems(events);
        for (Event event : Event.getEvents()) {
            tableView.getItems().add(event);
        }

    }
    @FXML
    private final ObservableList<Event> events = FXCollections.observableArrayList(
//            Event.getEvents().get(0)
//            new Event(1,"battleship",LocalDate.of(2021,2,2),LocalDate.of(2021,2,3),20,"bye")

    );
    @FXML
    private void joinEvent(ActionEvent event) throws IOException, ExistEventException {
        playerGeneralController.joinEvent(LoginController.getUsername(), String.valueOf(tableView.getSelectionModel().getSelectedItem().getEventID()));
    }
}
