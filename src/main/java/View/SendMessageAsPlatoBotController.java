package View;

import Controller.AdminController.AdminGeneralController;
import Controller.Exception.Plato.NotNullMessageException;
import Controller.PlayerController.PlayerGeneralController;
import Model.PlatoModel.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SendMessageAsPlatoBotController implements Initializable {
    protected static PlayerGeneralController playerGeneralController = new PlayerGeneralController();
    protected static AdminGeneralController adminGeneralController = new AdminGeneralController();
    String[] messages = playerGeneralController.viewBotMessages().split("\\$");
    String temp = " " ;

    @FXML
    public Button btnSend;
    @FXML
    public TextField txtMessage;
    @FXML
    public VBox vbox;

    @FXML
    public void sendMessage(ActionEvent event) throws IOException, NotNullMessageException {
        adminGeneralController.sendMassageString(txtMessage.getText());
        URL url = new File("src/main/resources/FXML/SendMessageAsPlatoBot.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    @FXML
    private void showMessages(){
        for (int i = 0; i < messages.length; i++) {

            String[] dates = messages[i].split(",");
            Label date = new Label();


            if (!temp.equals(checkDate(dates[1].substring(0,10)))){
                date.setText(checkDate(dates[1].substring(0,10)));
            }else {
                date.setText(" ");
            }
            date.setTextFill(Color.web("#00acea"));
            date.setAlignment(Pos.CENTER);
            date.setPrefWidth(360);
            date.setPrefHeight(10);
            temp=checkDate(dates[1].substring(0,10));
            //**********************//

//            Label label = new Label();
//            label.setText(dates[0].substring(6));
//            label.setTextFill(Color.WHITE);
//            label.setPrefWidth(360);
//            label.setPrefHeight(20);
//            vbox.getChildren().addAll(date,label);

            //*********************//

            HBox message = new HBox();
            Label text = new Label();
            text.setText(dates[0].substring(6));
            text.setTextFill(Color.WHITE);
            text.setPrefWidth(300);
            text.setPrefHeight(20);
            Label time = new Label();
            time.setText(dates[2]);
            time.setTextFill(Color.GRAY);
//            time.setPrefWidth(20);
            time.setPrefHeight(20);
            message.getChildren().addAll(text,time);
            vbox.getChildren().addAll(date,message);

        }
    }
    private String checkDate(String string){
        LocalDate time = LocalDate.parse(string);
        if (time.equals(LocalDate.now())){
            return "Today";
        }else if (time.isEqual(LocalDate.now().minusDays(1))){
            return "Yesterday";
        }else return string;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vbox.setSpacing(10);
        showMessages();
    }
}
