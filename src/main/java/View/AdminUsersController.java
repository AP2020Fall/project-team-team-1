package View;

import Model.PlatoModel.Player;
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
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminUsersController implements Initializable {

    @FXML
    public TableView<Player> table;

    @FXML
    public TableColumn<Player, Integer> tblID;

    @FXML
    public TableColumn<Player, Integer> tblUsername;

    @FXML
    public TableColumn<Player, Integer> tblPoints;

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
    private void back(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/FXML/AdminMenu.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    @FXML
    private void exitApp(ActionEvent event){
        System.exit(1);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
