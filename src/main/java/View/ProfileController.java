package View;

import Controller.AdminController.AdminGeneralController;
import Controller.Exception.Plato.*;
import Controller.PlayerController.PlayerGeneralController;
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

public class ProfileController implements Initializable {
    protected static AdminGeneralController adminGeneralController = new AdminGeneralController();
    protected static PlayerGeneralController playerGeneralController = new PlayerGeneralController();

    protected static String username = LoginController.getUsername();

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        ProfileController.username = username;
    }

    @FXML
    ImageView imgStatus;
    @FXML
    ImageView imgMedal;
    @FXML
    ImageView favBattle;
    @FXML
    ImageView favDots;
    @FXML
    Button btnBack;
    @FXML
    Label nameAndLastname;
    @FXML
    Label email;
    @FXML
    Label phoneNumber;
    @FXML
    Label numberOfPlayedLabel;
    @FXML
    Label winsLabel;
    @FXML
    Label loseLabel;
    @FXML
    Label platoAgeLabel;
    @FXML
    Label battleLabel;
    @FXML
    Label dotsLabel;
    @FXML
    public Pane EditPane;
    @FXML
    public Pane simplePane;
    @FXML
    public Button btnEdit;
    @FXML
    public Button btnCancel;
    @FXML
    public MenuButton btnField;
    @FXML
    public Button btnGoToEditPane;
    @FXML
    public Button btnDelete;
    @FXML
    public TextField txtNewValue;


    @FXML
    private void closeApp() {
        System.exit(1);
    }

    @FXML
    private void goToEdit(ActionEvent event) {
        EditPane.toFront();
    }

    @FXML
    private void cancelEdit(ActionEvent event) {
        simplePane.toFront();
    }

    //todo make popup and button for change pass


    @FXML
    private void deleteAccount(){
        //todo make popup because need password
    }

    @FXML
    private void editEvent(ActionEvent event) throws IOException {
//        try {
//            if (btnField.showingProperty().getValue().toString().toLowerCase().equals("name")) {
//                playerGeneralController.editField(LoginController.getUsername() + " " + "name" + " " + txtNewValue.getText());
//            } else if (btnField.showingProperty().getValue().toString().toLowerCase().equals("lastname")) {
//                playerGeneralController.editField(LoginController.getUsername() + " " + "lastname" + " " + txtNewValue.getText());
//            } else if (btnField.showingProperty().getValue().toString().toLowerCase().equals("email")) {
//                playerGeneralController.editField(LoginController.getUsername() + " " + "email" + " " + txtNewValue.getText());
//            } else if (btnField.showingProperty().getValue().toString().toLowerCase().equals("phonenumber")) {
//                playerGeneralController.editField(LoginController.getUsername() + " " + "phonenumber" + " " + txtNewValue.getText());
//            }
//        } catch (InvalidNameException e) {
//            e.printStackTrace();
//        } catch (InvalidEmailException e) {
//            e.printStackTrace();
//        } catch (InvalidPhoneNumberException e) {
//            e.printStackTrace();
//        } catch (ExistEmailException e) {
//            e.printStackTrace();
//        } catch (InvalidFieldException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        URL url = new File("src/main/resources/FXML/Profile.fxml").toURI().toURL();
//        Parent register = FXMLLoader.load(url);
//        Scene message = new Scene(register);
//        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        window.setScene(message);
//        window.show();
    }

    @FXML
    private void setImgStatusToCross() {
        File file = new File("src\\main\\resources\\Images\\cross.png");
        Image image = new Image(file.toURI().toString());
//        imgStatus.setImage(image);
    }

    @FXML
    private void setImgStatusToProfile() {
        File file = new File("src\\main\\resources\\Images\\default-profile.png");
        Image image = new Image(file.toURI().toString());
        imgStatus.setImage(image);
    }


    @FXML
    private void back(ActionEvent event) throws IOException {

        URL url = new File("src/main/resources/FXML/PlayerMenu.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    @FXML
    private void setGameStatus() throws InvalidGameNameException {
        int wins = Integer.parseInt(playerGeneralController.showNumberOFWins(LoginController.getUsername(), adminGeneralController.secondGameNameGetter())) + Integer.parseInt(playerGeneralController.showNumberOFWins(LoginController.getUsername(), adminGeneralController.firstGameNameGetter()));
        int all = Integer.parseInt(playerGeneralController.showNumberOfGamePlayedInThisGame(LoginController.getUsername(), adminGeneralController.secondGameNameGetter())) + Integer.parseInt(playerGeneralController.showNumberOfGamePlayedInThisGame(LoginController.getUsername(), adminGeneralController.firstGameNameGetter()));
        int lose = all - wins;
        winsLabel.setText(String.valueOf(wins));
        loseLabel.setText(String.valueOf(lose));
        numberOfPlayedLabel.setText(String.valueOf(all));
    }


    @FXML
    private void setFavoriteGames() throws ExistFavoriteException {
        battleLabel.setText(adminGeneralController.firstGameNameGetter());
        dotsLabel.setText(adminGeneralController.secondGameNameGetter());

        String[] fav = playerGeneralController.showFavoritesGames(LoginController.getUsername()).split("\\$");

        for (String gameName : fav) {
            if (gameName.startsWith("B") || gameName.startsWith("b")) {

                File file = new File("src\\main\\resources\\Icons\\addfav.png");
                Image image = new Image(file.toURI().toString());
                favBattle.setImage(image);

            }
        }
        for (String gameName : fav) {
            if (gameName.startsWith("D") || gameName.startsWith("d")) {

                File file = new File("src\\main\\resources\\Icons\\addfav.png");
                Image image = new Image(file.toURI().toString());
                favDots.setImage(image);

            }
        }
    }

    @FXML
    private void setImgMedal() throws ExistPlayerException {
        int level = Integer.parseInt(playerGeneralController.showPoint(getUsername()));

        if (level >= 200) {
            File file = new File("src\\main\\resources\\Images\\levelKing.png");
            Image image = new Image(file.toURI().toString());
            imgMedal.setImage(image);
        } else if (level >= 150) {
            File file = new File("src\\main\\resources\\Images\\levelWarrior.png");
            Image image = new Image(file.toURI().toString());
            imgMedal.setImage(image);

        } else if (level >= 100) {
            File file = new File("src\\main\\resources\\Images\\level1.png");
            Image image = new Image(file.toURI().toString());
            imgMedal.setImage(image);

        } else if (level >= 50) {
            File file = new File("src\\main\\resources\\Images\\level2.png");
            Image image = new Image(file.toURI().toString());
            imgMedal.setImage(image);

        } else {
            File file = new File("src\\main\\resources\\Images\\level3.png");
            Image image = new Image(file.toURI().toString());
            imgMedal.setImage(image);
        }
    }
    @FXML
    private void setPlatoAgeLabel() throws ExistPlayerException {
        platoAgeLabel.setText(playerGeneralController.showUserAge(LoginController.getUsername())+" Days in plato ");
    }
    @FXML
    private void setProfilesLabels() throws ExistPlayerException {
        String[] userData = playerGeneralController.showBasicInformation(getUsername()).split("\\$");
        nameAndLastname.setText(userData[1]+" "+userData[2]+"'s Profile");
        email.setText("Email: "+ userData[0]);
        phoneNumber.setText("Phone number: "+ userData[4]);
    }

    @FXML
    private void loadUserData() {
        try {
            setImgMedal();
            setPlatoAgeLabel();
            setProfilesLabels();
            setGameStatus();
            setFavoriteGames();
        } catch (ExistPlayerException | InvalidGameNameException | ExistFavoriteException e) {
            System.err.println(e.getMessage());
        }

    }

    @FXML
    public void close(ActionEvent event) {
        System.exit(1);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadUserData();
    }

}
