package View;

import Controller.AdminController.AdminGeneralController;
import Controller.Exception.Plato.ExistPlayerException;
import Controller.Exception.Plato.ExistPlayerLogException;
import Controller.Exception.Plato.InvalidGameNameException;
import Controller.PlayerController.PlayerGeneralController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DotsAndBoxesScoreBoardController implements Initializable {
    protected static AdminGeneralController adminGeneralController = new AdminGeneralController();
    protected static PlayerGeneralController playerGeneralController = new PlayerGeneralController();

    @FXML
    ListView<String> listView;

    @FXML
    private void setListView() throws ExistPlayerLogException, ExistPlayerException, InvalidGameNameException {
        ObservableList<String> list = FXCollections.observableArrayList();

        listView.setItems(list);
        String[] showEvent = playerGeneralController.showScoreboardInThisGame(adminGeneralController.secondGameNameGetter()).split("\\$");
        for (String out : showEvent) {
            listView.getItems().add(out);
        }
    }
    @FXML
    private void goDotsAndBoxesMainMenu(ActionEvent actionEvent) throws IOException {

        URL url = new File("src/main/resources/FXML/DotsAndBoxesMainMenu.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setListView();
        } catch (ExistPlayerLogException | ExistPlayerException | InvalidGameNameException e) {
            System.out.println(e.getMessage());
        }
    }
}
