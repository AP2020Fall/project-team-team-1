package View;

import Controller.AdminController.AdminGeneralController;
import Controller.AdminController.Event;
import Controller.Exception.Plato.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EventInfo implements Initializable {
    protected static AdminGeneralController adminGeneralController = new AdminGeneralController();
    protected static String id = "null" ;
    protected static String editField = "null";

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        EventInfo.id = id;
    }
    //    protected static String game;
//    protected static String start;
//    protected static String end;
//    protected static String score;
//    protected static String comment;


    @FXML
    public ImageView imgGame;

    @FXML
    public Label lblID;

    @FXML
    public Label lblGame;

    @FXML
    public Label lblEnd;

    @FXML
    public Label lblScore;

    @FXML
    public Label lblStart;

    @FXML
    public Label lblComment;

    @FXML
    public Button btnDelete;

    @FXML
    public Button btnGoToEditPane;

    @FXML
    public Pane EditPane;

    @FXML
    public Button btnEdit;

    @FXML
    public MenuButton btnField;

    @FXML
    public TextField txtNewValue;

    @FXML
    public Button btnCancel;

    @FXML
    public Pane simplePane;

    @FXML
    private void goToEdit(ActionEvent event){
        EditPane.toFront();
    }
    @FXML
    private void deleteEvent(ActionEvent event) throws IOException, ExistEventException {
        adminGeneralController.removeEventByAdminFromView(id);
        URL url = new File("src/main/resources/FXML/AdminEvent.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    @FXML

    private void backToPrevious(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/FXML/AdminEvent.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    @FXML
    private void closeApp(ActionEvent event){
        System.exit(1);
    }
    @FXML
    private void cancelEdit(ActionEvent event){
        simplePane.toFront();
    }
    @FXML
    private void editEvent(ActionEvent event) throws InvalidDateException, NotNullMessageException, InvalidFieldException, ExistEventException, StartDatesException, InvalidGameNameException, IOException {
        if (btnField.showingProperty().getValue().toString().toLowerCase().equals("start")){
            adminGeneralController.editEvent(id+" "+"Start Date"+" "+txtNewValue.getText());
        }else if (btnField.showingProperty().getValue().toString().toLowerCase().equals("end")){
            adminGeneralController.editEvent(id+" "+"end Date"+" "+txtNewValue.getText());
        }else if (btnField.showingProperty().getValue().toString().toLowerCase().equals("score")){
            adminGeneralController.editEvent(id+" "+"score"+" "+txtNewValue.getText());
        }else if (btnField.showingProperty().getValue().toString().toLowerCase().equals("comment")){
            adminGeneralController.editEvent(id+" "+"comment"+" "+txtNewValue.getText());
        }
        URL url = new File("src/main/resources/FXML/AdminEvent.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblID.setText(id);
        try {
            lblGame.setText(adminGeneralController.eventFinderByEventID(id).gameNameProperty().getValue());
            lblStart.setText(adminGeneralController.eventFinderByEventID(id).startDateProperty().getValue().toString());
            lblEnd.setText(adminGeneralController.eventFinderByEventID(id).endDateProperty().getValue().toString());
            lblScore.setText(adminGeneralController.eventFinderByEventID(id).scoreProperty().getValue().toString());
            lblComment.setText(adminGeneralController.eventFinderByEventID(id).commentProperty().getValue().toString());
        } catch (ExistEventException e) {
            System.err.println(e.getMessage());
        }
        try {
            if (adminGeneralController.eventFinderByEventID(id).gameNameProperty().getValue().toLowerCase().startsWith("b")){
                File file = new File("src\\main\\resources\\Icons\\battleShipIcon.png");
                Image image = new Image(file.toURI().toString());
                imgGame.setImage(image);
            }else if (adminGeneralController.eventFinderByEventID(id).gameNameProperty().getValue().toLowerCase().startsWith("d")){
                File file = new File("src\\main\\resources\\Icons\\DotsAndBoxesIcon.png");
                Image image = new Image(file.toURI().toString());
                imgGame.setImage(image);
            }
        } catch (ExistEventException e) {
            System.err.println(e.getMessage());
        }

    }


}
