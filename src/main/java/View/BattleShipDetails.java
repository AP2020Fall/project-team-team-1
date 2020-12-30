package View;

import Controller.AdminController.AdminGeneralController;
import Controller.PlayerController.PlayerGeneralController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class BattleShipDetails {
    protected static AdminGeneralController adminGeneralController = new AdminGeneralController();
    protected static PlayerGeneralController playerGeneralController = new PlayerGeneralController();

    @FXML
    Label topic;
    @FXML
    Text detail;
    @FXML
    Button btnExit;

    private void setTopic(){
        topic.setText(adminGeneralController.firstGameNameGetter()+"'s Details");
    }
    private void setDetail(){
        detail.setText(playerGeneralController.battleDetails());
    }
    @FXML
    private void appExit(ActionEvent event){
        System.exit(1);
    }
}
