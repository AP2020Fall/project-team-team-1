package Client.View;

import Client.DataLoader;
import Server.Controller.Exception.Plato.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class EventInfo implements Initializable {
    //protected static AdminGeneralController adminGeneralController = new AdminGeneralController();
    private static DataLoader dataLoader = new DataLoader();
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
    public ComboBox<String> btnField;

    @FXML
    public TextField txtNewValue;

    @FXML
    public Button btnCancel;

    @FXML
    public Pane simplePane;

    @FXML
    private void goToEditPane(ActionEvent event){
        playMouseSound();
        EditPane.toFront();
    }
    @FXML
    private void deleteEvent(ActionEvent event) throws IOException, ExistEventException {
        playMouseSound();
        dataLoader.deleteEvent(id);
        URL url = new File("src/main/resources/FXML/AdminEvent.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    @FXML

    private void backToPrevious(ActionEvent event) throws IOException {
        playMouseSound();
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
        playMouseSound();
        simplePane.toFront();
    }
    @FXML
    private void editEvent(ActionEvent event) throws InvalidDateException, NotNullMessageException, InvalidFieldException, ExistEventException, StartDatesException, InvalidGameNameException, IOException {
        playMouseSound();
        String response = "";
        if (txtNewValue.getText().isEmpty()||btnField.getValue().equalsIgnoreCase("field")){
            return;
        }
        if (btnField.getValue().toLowerCase().equals("start")){
            response = dataLoader.editEventDetails(id,"Start Date",txtNewValue.getText());
        }else if (btnField.getValue().toLowerCase().equals("end")){
            response = dataLoader.editEventDetails(id,"end Date",txtNewValue.getText());
        }else if (btnField.getValue().toLowerCase().equals("score")){
            response = dataLoader.editEventDetails(id,"score",txtNewValue.getText());
        }else if (btnField.getValue().toLowerCase().equals("comment")){
            response = dataLoader.editEventDetails(id,"comment",txtNewValue.getText());
        }
        if (!response.equalsIgnoreCase("done")){
            return;
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
            lblGame.setText(dataLoader.findEventName(id));
            lblStart.setText(dataLoader.findEventDateStart(id));
            lblEnd.setText(dataLoader.findEventDateEnd(id));
            lblScore.setText(String.valueOf(dataLoader.findEventScore(id)));
            lblComment.setText(dataLoader.findEventComment(id));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (dataLoader.findEventName(id).toLowerCase().startsWith("b")){
                String path ="src"+File.separator+"main"+File.separator+"resources"+File.separator+
                        "Events"+File.separator+getId()+File.separator+getId()+".jpg";
                URL url = new File(path).toURI().toURL();
                imgGame.setImage(new Image(url.toExternalForm()));
            }else if (dataLoader.findEventName(id).toLowerCase().startsWith("b")){
                String path ="src"+File.separator+"main"+File.separator+"resources"+File.separator+
                        "Events"+File.separator+getId()+File.separator+getId()+".jpg";
                URL url = new File(path).toURI().toURL();
                imgGame.setImage(new Image(url.toExternalForm()));
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void playMouseSound(){
        File file = new File("src\\main\\resources\\Sound\\Click.mp3");
        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }


}
