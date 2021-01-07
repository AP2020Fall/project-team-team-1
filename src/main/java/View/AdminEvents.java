package View;

import Controller.AdminController.AdminGeneralController;
import Controller.Exception.Plato.ExistEventException;
import Controller.Exception.Plato.StartDatesException;
import Model.PlatoModel.Event;
import Model.PlatoModel.Player;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArrayBase;
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
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminEvents implements Initializable {
    protected static AdminGeneralController adminGeneralController = new AdminGeneralController();
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
    public TableColumn<Event,String> comment;
    @FXML
    public TableColumn<Event,String> game;
    @FXML
    public TableColumn<Event,Integer> id;
    @FXML
    public TableView<Event> table;
    @FXML
    public JFXButton btnBack;
    @FXML
    public Button btnSelectedEvent;

    @FXML
    public void addEvent(ActionEvent event) throws IOException {
        try {
            adminGeneralController.addEvent(txtID.getText()+" "+txtGame.getText()+" "+dateStart.getValue().toString()+" "+dateEnd.getValue().toString()+" "+txtScore.getText()+" "+txtComment.getText());
            LoginController.setUsername(null);
            URL url = new File("src/main/resources/FXML/AdminEvent.fxml").toURI().toURL();
            Parent register = FXMLLoader.load(url);
            Scene message = new Scene(register);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(message);
            window.show();
        } catch (StartDatesException e) {
            System.err.println(e.getMessage());
            UpdateError.setError(e.getMessage());
            showError();
            return;
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (ExistEventException e) {
            System.err.println(e.getMessage());
            UpdateError.setError(e.getMessage());
            showError();
            return;
        }
    }

    private void showError() throws IOException {
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
        if (EventInfo.getId().equalsIgnoreCase("null")){
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
    public void initActions(){
        table.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent arg0) {
                String id = String.valueOf(table.getSelectionModel().getSelectedItem().getEventID());
                EventInfo.setId(id);
            }
        });
    }

    private final ObservableList<Event>events= FXCollections.observableArrayList();

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
    @FXML
    public void backToLastMenu(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/FXML/AdminMenu.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
}
