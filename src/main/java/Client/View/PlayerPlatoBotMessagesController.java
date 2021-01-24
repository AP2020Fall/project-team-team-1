package Client.View;

import Client.DataLoader;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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

public class PlayerPlatoBotMessagesController implements Initializable {

    private static DataLoader dataLoader = new DataLoader();

    @FXML
    public JFXButton BtnClose;
    @FXML
    public JFXButton btnPlatoBotsMessages;
    @FXML
    public JFXButton btnBack;
    @FXML
    public JFXButton btnSearchFriends;
    @FXML
    public JFXButton btnEvents;
    @FXML
    public JFXButton btnFavoritesGames;
    @FXML
    VBox vbox;

    @FXML
    private void closeApp(ActionEvent event) {
        System.exit(1);
    }

    String temp = " ";
//    @FXML
//    ListView<String> listView;

    //    @FXML
//    private void setListView() throws ExistPlayerLogException, IOException, NotNullMessageException {
//        ObservableList<String> list = FXCollections.observableArrayList();
//
//        listView.setItems(list);
//        String[] showMessage = playerGeneralController.viewBotMessages().split("\\$");
//        for (String out : showMessage) {
//            listView.getItems().add(out);
//        }
////        listView.getItems().add("====================================");
////        for (String s : showMessage) {
////            String[] forShow = s.split("\\$");
////            listView.getItems().add(forShow[2]);
////            for (String s1 : showMessage) {
////                String[] forShow1 = s1.split("\\$");
////                if (forShow[2].equalsIgnoreCase(forShow1[2])){
////                    listView.getItems().add(forShow1[0]+". "+forShow1[1]);
////                }
////            }
////        }
//
//    }
    @FXML
    private void showMessages() throws IOException {
        String[] messages = dataLoader.loadPlayerPlatoMessage().split("\\$");

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
            String path = "src" + File.separator + "main" + File.separator + "resources" + File.separator
                    + "Users" + File.separator + "admin" + File.separator
                    + "admin.jpg";
            URL url = null;
            try {
                url = new File(path).toURI().toURL();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            ImageView imageView = new ImageView();
            imageView.setFitWidth(20);
            imageView.setFitHeight(20);
            assert url != null;
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
            message.getChildren().addAll(imageView, text, time);
            vbox.getChildren().addAll(date, message);

        }
    }

    private String checkDate(String string) {
        LocalDate time = LocalDate.parse(string);
        if (time.equals(LocalDate.now())) {
            return "Today";
        } else if (time.isEqual(LocalDate.now().minusDays(1))) {
            return "Yesterday";
        } else return string;
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        playMouseSound();
        URL url = new File("src/main/resources/FXML/PlayerMainMenu.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    @FXML
    private void goToPlayerEvents(ActionEvent event) throws IOException {
        playMouseSound();
        URL url = new File("src/main/resources/FXML/PlayerEvents.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    @FXML
    private void goToPlayerFavoritesGames(ActionEvent event) throws IOException {
        playMouseSound();
        URL url = new File("src/main/resources/FXML/YourGame.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    @FXML
    private void goToPlayerSearchFriends(ActionEvent event) throws IOException {
        playMouseSound();
        URL url = new File("src/main/resources/FXML/PlayerSearchFriends.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    @FXML
    private void goToPlayerPlatoBotsMessages(ActionEvent event) throws IOException {
        playMouseSound();
        URL url = new File("src/main/resources/FXML/PlayerPlatoBotsMessages.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    public void playMouseSound() {
        File file = new File("src\\main\\resources\\Sound\\Click.mp3");
        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vbox.setSpacing(10);
        try {
            showMessages();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
//        try {
//        setListView();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (NotNullMessageException e) {
//            e.printStackTrace();
//        } catch (ExistPlayerLogException e) {
//            e.printStackTrace();
//        }
    }
}
