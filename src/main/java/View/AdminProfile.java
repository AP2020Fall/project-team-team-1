package View;

import Controller.AdminController.AdminGeneralController;
import Controller.Exception.Plato.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;


public class AdminProfile implements Initializable {
    protected static AdminGeneralController adminGeneralController = new AdminGeneralController();
    String[] strings = adminGeneralController.showAdminInfo().split("\\$");
    private File file;

    @FXML
    public ImageView imgProfile;

    @FXML
    public Label lblFirsName;

    @FXML
    public Label lblLastName;

    @FXML
    public Label lblID;

    @FXML
    public Button btnBack;

    @FXML
    public Button btnExit;

    @FXML
    public Label lblEmail;

    @FXML
    public Button btnEdit;

    @FXML
    public Pane editPane;

    @FXML
    public ComboBox<String> comboField;

    @FXML
    public TextField txtNewValue;

    @FXML
    public Button btnSubmitEdit;

    @FXML
    public Pane simplePane;
    @FXML
    public Label lblPhone;

    @FXML
    public void appExit(ActionEvent event) {
        System.exit(1);
    }



    @FXML
    public void goToEditPanel(ActionEvent event) {
        editPane.toFront();
    }


    @FXML
    public void backToLastMenuAdminProfile(ActionEvent event) throws IOException {
        playMouseSound();
        URL url = new File("src/main/resources/FXML/AdminMenu.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        simplePane.toFront();
        btnSubmitEdit.setDisable(!txtNewValue.getText().isEmpty());

        lblFirsName.setText(strings[2]);
        lblLastName.setText(strings[3]);
        lblEmail.setText(strings[4]);
        lblID.setText(strings[0]);
        lblPhone.setText(strings[5]);
        try {
            showAdminImage();
        } catch (MalformedURLException e) {
            System.err.println(e.getMessage());
        }
    }
    @FXML
    private void showAdminImage() throws MalformedURLException {
        playMouseSound();
        String path = "src"+File.separator+"main"+File.separator+"resources"+File.separator
                +"Users"+File.separator+"admin"+File.separator
                +"admin.jpg";
        URL url = new File(path).toURI().toURL();
        imgProfile.setImage(new Image(url.toExternalForm()));
    }
    @FXML
    public void editAdmin(ActionEvent event) throws InvalidEmailException, InvalidNameException, ExistEmailException, InvalidFieldException, InvalidPhoneNumberException, IOException {
        playMouseSound();
        if (comboField.getValue().equalsIgnoreCase("Email")){
            adminGeneralController.editField("email"+" "+txtNewValue.getText());
        }else if (comboField.getValue().equalsIgnoreCase("Phone")){
            adminGeneralController.editField("phoneNumber"+" "+txtNewValue.getText());
        }else if (comboField.getValue().equalsIgnoreCase("First name")){
            adminGeneralController.editField("name"+" "+txtNewValue.getText());
        }else if (comboField.getValue().equalsIgnoreCase("Last Name")){
            adminGeneralController.editField("LastName"+" "+txtNewValue.getText());
        }
        URL url = new File("src/main/resources/FXML/AdminProfile.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    public void changeProfilePicAdmin(ActionEvent event) throws IOException {
        playMouseSound();
        file = chooseProfilePick(new FileChooser());
        copy(file,createProfileFile("admin"));
        URL url = new File("src/main/resources/FXML/AdminProfile.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();

    }
    public void playMouseSound(){
        File file = new File("src\\main\\resources\\Sound\\Click.mp3");
        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    @FXML
    private File chooseProfilePick(FileChooser fileChooser){
        FileChooser.ExtensionFilter images = new FileChooser.ExtensionFilter("Images","*.Jpg");
        fileChooser.getExtensionFilters().add(images);
        return fileChooser.showOpenDialog(new Stage());
    }
    @FXML
    private File createProfileFile(String username){
        String path ="src"+File.separator+"main"+File.separator+"resources"+File.separator+
                "Users"+File.separator+username+File.separator+username+".jpg";
        return new File(path);
    }
    @FXML
    private void copy(File pic , File dest) throws IOException {
        FileUtils.copyFile(pic,dest);
    }

}
