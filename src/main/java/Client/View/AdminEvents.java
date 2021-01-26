package Client.View;

import Client.DataLoader;
import Server.Controller.AdminController.AdminGeneralController;
import Server.Controller.Exception.Plato.ExistEventException;
import Server.Controller.Exception.Plato.StartDatesException;
import Server.Model.PlatoModel.Event;
import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminEvents implements Initializable {
    private static final DataLoader dataLoader = new DataLoader();
    public TextField txtID;
    @FXML
    public TextField txtComment;
    @FXML
    public TextField txtGame;
    @FXML
    public Button btnAdd;
    @FXML
    public DatePicker dateStart;
    @FXML
    public DatePicker dateEnd;
    @FXML
    public TextField txtScore;
    @FXML
    public TableColumn<Event, String> comment;
    @FXML
    public TableColumn<Event, String> game;
    @FXML
    public TableColumn<Event, Integer> id;
    @FXML
    public TableView<Event> table;
    @FXML
    public JFXButton btnBack;
    @FXML
    public Button btnSelectedEvent;
    public Button btnProfile;
    private File file;

    @FXML
    public void addEvent(ActionEvent event) throws IOException {
        playMouseSound();
        if (txtID.getText().isEmpty() ||txtGame.getText().isEmpty() || dateStart.getValue().toString().isEmpty() ||dateEnd.getValue().toString().isEmpty() ||txtScore.getText().isEmpty() ||txtComment.getText().isEmpty()){
            UpdateError.setError("There is empty field");
            showError();
            return;
        }
        String response = dataLoader.makeEvent(txtID.getText() + " " + txtGame.getText() + " " + dateStart.getValue().toString() + " " + dateEnd.getValue().toString() + " " + txtScore.getText() + " " + txtComment.getText());

        if (response.equalsIgnoreCase("Start Date Must be after than Today")){
            UpdateError.setError("Start Date Must be after than Today");
            showError();
            return;
        }
        if (response.equalsIgnoreCase("Start Date Must be before than End Date")){
            UpdateError.setError("Start Date Must be before than End Date");
            showError();
            return;
        }
        if (response.equalsIgnoreCase("ID exist")){
            UpdateError.setError("ID exist");
            showError();
            return;
        }
        if (!response.equalsIgnoreCase("done")){
            UpdateError.setError("event error");
            showError();
            return;
        }
        File image = createProfileFile(txtID.getText());
        copy(file, image);
        LoginController.setUsername(null);
        URL url = new File("src/main/resources/FXML/AdminEvent.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
//        } catch (StartDatesException e) {
//            System.err.println(e.getMessage());

//            return;
//        } catch (IOException e) {
//            System.err.println(e.getMessage());
//        } catch (ExistEventException e) {
//            System.err.println(e.getMessage());
//            UpdateError.setError(e.getMessage());
//            showError();
//            return;
//        }
    }

    private void showError() throws IOException {
        playMouseSound();
        URL url = new File("src/main/resources/FXML/EventError.fxml").toURI().toURL();

        AnchorPane root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void goToEventInfo(ActionEvent event) throws IOException {
        playMouseSound();
        if (EventInfo.getId().equalsIgnoreCase("null")) {
            return;
        }
        URL url = new File("src/main/resources/FXML/EventInfo.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        Stage window = new Stage();
        window.setScene(message);
        window.show();
    }

    @FXML
    public void initActions() {
        table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent arg0) {
                String id = String.valueOf(table.getSelectionModel().getSelectedItem().getEventID());
                EventInfo.setId(id);
            }
        });
    }

    private final ObservableList<Event> events = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id.setCellValueFactory(new PropertyValueFactory<>("eventID"));
        game.setCellValueFactory(new PropertyValueFactory<>("GameName"));
        comment.setCellValueFactory(new PropertyValueFactory<>("Comment"));
        table.setItems(events);
        for (Event event : Event.getEvents()) {
            table.getItems().add(event);
        }
        initActions();
//        table.setRowFactory(tv->{
//            TableRow<Event> row = new TableRow<>();
//            row.setOnMouseClicked(new EventHandler<MouseEvent>() {
//                @Override
//                public void handle(MouseEvent event) {
//                    if (event.getClickCount()==2&&(!row.isEmpty())){
//                        String rowData = row.getItem().eventIDProperty().toString();
//                        EventInfo.setId(rowData);
//                        try {
//                            goToEventInfo();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                }
//            });
//
//            return row;
//        });

    }

    public void playMouseSound() {
        File file = new File("src\\main\\resources\\Sound\\Click.mp3");
        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    @FXML
    public void backToLastMenu(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/FXML/AdminMenu.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    @FXML
    private File chooseProfilePick(FileChooser fileChooser) {
        FileChooser.ExtensionFilter images = new FileChooser.ExtensionFilter("Images", "*.Jpg");
        fileChooser.getExtensionFilters().add(images);
        return fileChooser.showOpenDialog(new Stage());
    }

    @FXML
    private File createProfileFile(String username) {
        String path = "src" + File.separator + "main" + File.separator + "resources" + File.separator +
                "Events" + File.separator + username + File.separator + username + ".jpg";
        return new File(path);
    }

    @FXML
    private void copy(File pic, File dest) throws IOException {
        FileUtils.copyFile(pic, dest);
    }

    public void addProfile(ActionEvent event) {
        file = chooseProfilePick(new FileChooser());
    }
}
