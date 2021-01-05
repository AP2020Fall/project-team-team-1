package View;

import Controller.AdminController.AdminGeneralController;
import Controller.Exception.Plato.GameActivation;
import Controller.Exception.Plato.InvalidGameID;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminGamesBattleShip implements Initializable {
    protected static AdminGeneralController adminGeneralController = new AdminGeneralController();
    @FXML
    public Button btnGoToAdminBattleShipGame;
    @FXML
    public Button btnGoToAdminDotsGame;
    @FXML
    public Button btnDeActiveBattle;
    @FXML
    public Button btnActiveBattle;
    @FXML
    public Label lblBattleNumbers;
    @FXML
    public Label lblBattleMVP;
    @FXML
    public TextArea txtDetails;
    @FXML
    public Button btnEdit;
    @FXML
    public Label lblBattleDetails;
    public Button btnEditBattle;

    @FXML
    private void goToAdminDotsGame(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/FXML/AdminGamesDotsAndBoxes.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    @FXML
    private void goToBattleGame(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/FXML/AdminGamesBattleShip.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    @FXML
    private void goToLastMenu(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/FXML/AdminMenu.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    @FXML
    private void appExit(ActionEvent event){
        System.exit(1);
    }

    public void editBattleDetails(ActionEvent event) throws IOException {
        adminGeneralController.setDetails("BattleShip",txtDetails.getText());
        URL url = new File("src/main/resources/FXML/AdminGamesBattleShip.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    public void activateBattle(ActionEvent event) throws InvalidGameID, IOException, GameActivation {

        adminGeneralController.activeGame("1");

    }

    public void deActivateBattleShip(ActionEvent event) throws InvalidGameID, IOException, GameActivation {
         adminGeneralController.deActiveGame("1");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            if (adminGeneralController.activationStatus("1").equalsIgnoreCase("Game is Already Activate")){
                btnActiveBattle.setDisable(false);
                btnDeActiveBattle.setDisable(true);
            }else {
                btnActiveBattle.setDisable(true);
                btnDeActiveBattle.setDisable(false);
            }
        } catch (InvalidGameID invalidGameID) {
            System.err.println(invalidGameID.getMessage());
        }

        //Up Or Down ?

//        try {
//            if (adminGeneralController.activationStatus("1").equalsIgnoreCase("Game is Already Activate")){
//                btnActiveBattle.setOnMouseClicked(event -> {
//                    if (event.getClickCount()>1){
//                        System.out.println("Already Active");
//                    }
//                });
//            }
//        } catch (InvalidGameID invalidGameID) {
//            invalidGameID.printStackTrace();
//        }

    }
}
