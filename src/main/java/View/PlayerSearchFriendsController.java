package View;

import Model.PlatoModel.Player;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

public class PlayerSearchFriendsController implements Initializable {
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
    public TableView<Player> tableView;
    @FXML
    public TableColumn<Player,String> tblFriends;
    @FXML
    public TextField txtSearch;

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
        URL url = new File("src/main/resources/FXML/YourGame.fxml").toURI().toURL();
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
    private final ObservableList<Player> friends = FXCollections.observableArrayList(

    );

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblFriends.setCellValueFactory(new PropertyValueFactory<>("userName"));
        tableView.setItems(friends);
        for (Player player : Player.getPlayers()) {
            tableView.getItems().add(player);
        }

        //------------------filter and search -----------------------
        FilteredList<Player> filteredList = new FilteredList<>(friends,b->true);

        txtSearch.textProperty().addListener((Observable,oldValue,newValue)->{
            filteredList.setPredicate(player -> {
                if (newValue.isEmpty()){
                    return true;
                }
                String lowerCaseValue = newValue.toLowerCase();

//                if (player.getUserName().toLowerCase().indexOf(lowerCaseValue)!=-1){
//                    return true;
//                }
                if (player.getUserName().toLowerCase().startsWith(lowerCaseValue)){
                    return true;
                }
                else return false;
            });
        });
        SortedList<Player> sortedData = new SortedList<>(filteredList);

        sortedData.comparatorProperty().bind(tableView.comparatorProperty());

        tableView.setItems(filteredList);
        //------------setDoubleClick---------//
        tableView.setRowFactory(tv->{
            TableRow<Player> row = new TableRow<>();
            row.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (event.getClickCount()==2&&(!row.isEmpty())){
                        String rowData = row.getItem().getUserName();
                        System.out.println(rowData);
                    }
                }
            });
            return row;
        });
    }
}
