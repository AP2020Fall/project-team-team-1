package View;

import Controller.AdminController.AdminGeneralController;
import Controller.PlayerController.PlayerGeneralController;
import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DotsAndBoxesDetailsController implements Initializable {
    protected static AdminGeneralController adminGeneralController = new AdminGeneralController();
    protected static PlayerGeneralController playerGeneralController = new PlayerGeneralController();

    @FXML
    Label topic;
    @FXML
    Text detail;

    @FXML
    private void setDetail() {
        //detail.setText(playerGeneralController.battleDetails());
        topic.setText(adminGeneralController.secondGameNameGetter() + "'s Details");
        final String content = playerGeneralController.dotsDetails();

        final Animation animation = new Transition() {
            {
                setCycleDuration(Duration.millis(2000));
            }

            protected void interpolate(double frac) {
                final int length = content.length();
                final int n = Math.round(length * (float) frac);
                detail.setText(content.substring(0, n));
            }
        };

        animation.play();
    }
    @FXML
    private void goDotsAndBoxesMainMenu(ActionEvent actionEvent) throws IOException {

        URL url = new File("src/main/resources/FXML/DotsAndBoxesMainMenu.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDetail();
    }
}
