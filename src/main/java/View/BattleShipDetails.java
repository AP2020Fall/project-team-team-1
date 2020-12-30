package View;

import Controller.AdminController.AdminGeneralController;
import Controller.PlayerController.PlayerGeneralController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class BattleShipDetails {
    protected static AdminGeneralController adminGeneralController = new AdminGeneralController();
    protected static PlayerGeneralController playerGeneralController = new PlayerGeneralController();

    @FXML
    Label topic;
    @FXML
    Text detail;

    private void setTopic(){
        topic.setText(adminGeneralController.firstGameNameGetter()+"'s Details");
    }
    private void setDetail(){
        detail.setText(playerGeneralController.battleDetails());
    }
}
