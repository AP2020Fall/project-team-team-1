package Client.View;

import Client.DataLoader;
import Server.Model.PlatoModel.Player;
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
import javafx.scene.control.*;
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

public class AdminUsersController implements Initializable {
    private static final DataLoader dataLoader = new DataLoader();
    private static ArrayList<Player> playerArrayList ;

    public static ArrayList<Player> getPlayerArrayList() {
        return playerArrayList;
    }

    public static void setPlayerArrayList(ArrayList<Player> playerArrayList) {
        AdminUsersController.playerArrayList = playerArrayList;
    }

    @FXML
    public TableView<Player> table;

    @FXML
    public TableColumn<Player, Integer> tblUsername;


    @FXML
    public TextField txtSearch;

    @FXML
    public Button btnGoToPlayer;

    @FXML
    public Label lblPlayerNum;

    @FXML
    public Label lblGamesNum;

    @FXML
    public Label lblMVP;

    @FXML
    public Button btnBack;

    @FXML
    public Button btnExit;
    @FXML
    public TableColumn<Player, Integer> tblID;

    @FXML
    private void backToAdminMenu(ActionEvent event) throws IOException {
        playMouseSound();
        URL url = new File("src/main/resources/FXML/AdminMenu.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    @FXML
    private void goToPlayer(ActionEvent event) throws IOException {
        playMouseSound();
        if (ShowUserProfileForAdminController.getUsernameProfileForShowToAdmin().equalsIgnoreCase("null")){
            return;
        }

        {
            URL url = new File("src/main/resources/FXML/ShowUserProfileForAdmin.fxml").toURI().toURL();
            Parent register = FXMLLoader.load(url);
            Scene message = new Scene(register);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(message);
            window.show();
        }
    }
    @FXML
    private void exitApp(ActionEvent event) {
        System.exit(1);
    }
    @FXML
    public void initActions(){
        playMouseSound();
        table.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent arg0) {
                String usernameForShow = table.getSelectionModel().getSelectedItem().getUserName();
                ShowUserProfileForAdminController.setUsernameProfileForShowToAdmin(usernameForShow);
            }
        });
    }

    private final ObservableList<Player> players = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblPlayerNum.setText(String.valueOf(Player.getPlayers().size()));
        try {
            lblGamesNum.setText(dataLoader.totalPlayedPlato());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            lblMVP.setText(dataLoader.mvpPlato().split("\\$")[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        tblID.setCellValueFactory(new PropertyValueFactory<>("UserID"));
        tblUsername.setCellValueFactory(new PropertyValueFactory<>("userName"));
        table.setItems(players);
        for (Player player : playerArrayList) {
            table.getItems().add(player);
        }
        //-----------filter------------------//
        FilteredList<Player> filteredList = new FilteredList<>(players, b -> true);

        txtSearch.textProperty().addListener((Observable, oldValue, newValue) -> {
            filteredList.setPredicate(player -> {
                if (newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseValue = newValue.toLowerCase();

                if (player.getUserName().toLowerCase().startsWith(lowerCaseValue)) {
                    return true;
                } else return false;
            });
        });
        SortedList<Player> sortedData = new SortedList<>(filteredList);

        sortedData.comparatorProperty().bind(table.comparatorProperty());

        table.setItems(filteredList);
        initActions();

        //------------setDoubleClick---------//
//        table.setRowFactory(tv->{
//            TableRow<Player> row = new TableRow<>();
//            row.setOnMouseClicked(new EventHandler<MouseEvent>() {
//                @Override
//                public void handle(MouseEvent event) {
//                    if (!row.isEmpty()){
//                        String rowData = row.getItem().getUserName();
//                        ShowUserProfileForAdminController.setUsername(rowData);
//                    }
//                }
//            });
//            return row;
//        });

    }
    public void playMouseSound(){
        File file = new File("src\\main\\resources\\Sound\\Click.mp3");
        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
}
