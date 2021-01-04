package View;

import Controller.AdminController.AdminGeneralController;
import Model.PlatoModel.Player;
import Model.PlatoModel.PlayerLog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminUsersController implements Initializable {
    protected static AdminGeneralController adminGeneralController = new AdminGeneralController();

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
        URL url = new File("src/main/resources/FXML/AdminMenu.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    @FXML
    private void exitApp(ActionEvent event) {
        System.exit(1);
    }

    private final ObservableList<Player> players = FXCollections.observableArrayList(

    );

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblPlayerNum.setText(String.valueOf(Player.getPlayers().size()));
        lblGamesNum.setText(adminGeneralController.numberOfTotalPlayed());
        lblMVP.setText(adminGeneralController.getMVPUser());
        tblID.setCellValueFactory(new PropertyValueFactory<>("UserID"));
        tblUsername.setCellValueFactory(new PropertyValueFactory<>("userName"));
        table.setItems(players);
        for (Player player : Player.getPlayers()) {
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
    }
}
