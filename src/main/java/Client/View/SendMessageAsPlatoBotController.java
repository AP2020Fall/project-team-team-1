package Client.View;

import Server.Controller.AdminController.AdminGeneralController;
import Server.Controller.Exception.Plato.NotNullMessageException;
import Server.Controller.PlayerController.PlayerGeneralController;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
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
        playMouseSound();
        adminGeneralController.sendMassageString(txtMessage.getText());
        URL url = new File("src/main/resources/FXML/SendMessageAsPlatoBot.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    @FXML
    private void showMessages() throws MalformedURLException {
        for (String s : messages) {

            String[] dates = s.split(",");
            Label date = new Label();


            if (!temp.equals(checkDate(dates[1].substring(0, 10)))) {
                date.setText(checkDate(dates[1].substring(0, 10)));
            } else {
                date.setText(" ");
            }
            date.setTextFill(Color.web("#00acea"));
            date.setAlignment(Pos.CENTER);
            date.setPrefWidth(360);
            date.setPrefHeight(10);
            temp = checkDate(dates[1].substring(0, 10));

            HBox message = new HBox();
            String path = "src"+File.separator+"main"+File.separator+"resources"+File.separator
                    +"Users"+File.separator+"admin"+File.separator
                    +"admin.jpg";
            URL url = new File(path).toURI().toURL();

            ImageView imageView = new ImageView();
            imageView.setFitWidth(20);
            imageView.setFitHeight(20);
            imageView.setImage(new Image(url.toExternalForm()));
            Label text = new Label();
            text.setText(dates[0].substring(6));
            text.setTextFill(Color.WHITE);
            text.setPrefWidth(280);
            text.setPrefHeight(20);
            Label time = new Label();
            time.setText(dates[2]);
            time.setTextFill(Color.GRAY);
            time.setPrefHeight(20);
            message.getChildren().addAll(imageView,text, time);
            vbox.getChildren().addAll(date, message);

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
        try {
            showMessages();
        } catch (MalformedURLException e) {
            return;
        }
    }
    @FXML
    public void exit(ActionEvent event) {
        System.exit(1);
    }
    @FXML
    public void back(ActionEvent event) throws IOException {
        playMouseSound();
        URL url = new File("src/main/resources/FXML/AdminMenu.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    public void goToBotsMessages(ActionEvent event) throws IOException {
        playMouseSound();
        URL url = new File("src/main/resources/FXML/SendMessageAsPlatoBot.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    public void goToSuggestions(ActionEvent event) throws IOException {
        playMouseSound();
        URL url = new File("src/main/resources/FXML/AdminEditSuggestion.fxml").toURI().toURL();
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
}
