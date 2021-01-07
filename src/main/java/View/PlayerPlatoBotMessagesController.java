package View;

import Controller.AdminController.AdminGeneralController;
import Controller.Exception.Plato.ExistPlayerException;
import Controller.Exception.Plato.ExistPlayerLogException;
import Controller.Exception.Plato.InvalidGameNameException;
import Controller.Exception.Plato.NotNullMessageException;
import Controller.PlayerController.PlayerGeneralController;
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
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PlayerPlatoBotMessagesController implements Initializable {
    protected static AdminGeneralController adminGeneralController = new AdminGeneralController();
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
    private void closeApp(ActionEvent event){
        System.exit(1);
    }
    @FXML
    ListView<String> listView;

    @FXML
    private void setListView() throws ExistPlayerLogException, IOException, NotNullMessageException {
        ObservableList<String> list = FXCollections.observableArrayList();

        listView.setItems(list);
        String[] showMessage = playerGeneralController.viewBotMessages().split("\\&");
        for (String out : showMessage) {
            listView.getItems().add(out);
        }
//        listView.getItems().add("====================================");
//        for (String s : showMessage) {
//            String[] forShow = s.split("\\$");
//            listView.getItems().add(forShow[2]);
//            for (String s1 : showMessage) {
//                String[] forShow1 = s1.split("\\$");
//                if (forShow[2].equalsIgnoreCase(forShow1[2])){
//                    listView.getItems().add(forShow1[0]+". "+forShow1[1]);
//                }
//            }
//        }

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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
        setListView();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NotNullMessageException e) {
            e.printStackTrace();
        } catch (ExistPlayerLogException e) {
            e.printStackTrace();
        }
    }
}
