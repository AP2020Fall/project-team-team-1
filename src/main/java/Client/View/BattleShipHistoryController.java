package Client.View;

import Client.DataLoader;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BattleShipHistoryController implements Initializable {

    private static final DataLoader dataLoader = new DataLoader();

    @FXML
    ImageView imageViewOfLevel;
    @FXML
    PieChart pieChart;
    @FXML
    Label numberofplayed;
    @FXML
    Label point;


    @FXML
    private void loadHistory() {
        try {
            setPieChart();
            setImageViewOfLevel();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void setPieChart() throws IOException {

        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(
                new PieChart.Data("Wins", Integer.parseInt(dataLoader.numberOfWins(LoginController.getUsername(),"first"))),
                new PieChart.Data("Losses", Integer.parseInt(dataLoader.numberOfLoses(LoginController.getUsername(),"first"))));
        pieChartData.forEach(data ->
                data.nameProperty().bind(
                        Bindings.concat(
                                data.getName(), " ", data.pieValueProperty(), " Times"
                        )
                )
        );
        numberofplayed.setText("You have played this Game for " + dataLoader.numberOfPlayThisGame(LoginController.getUsername(),"first") + " Times");
        pieChart.setLegendVisible(false);
        pieChart.setData(pieChartData);
        pieChart.setStartAngle(180);
    }

    @FXML
    private void setImageViewOfLevel() throws IOException {
        int level = Integer.parseInt(dataLoader.pointsInThisGame(LoginController.getUsername(),"first"));
        point.setText("Points: " + level);


        if (level >= 200) {
            File file = new File("src\\main\\resources\\Images\\levelKing.png");
            Image image = new Image(file.toURI().toString());
            imageViewOfLevel.setImage(image);
        } else if (level >= 150) {
            File file = new File("src\\main\\resources\\Images\\levelWarrior.png");
            Image image = new Image(file.toURI().toString());
            imageViewOfLevel.setImage(image);

        } else if (level >= 100) {
            File file = new File("src\\main\\resources\\Images\\level1.png");
            Image image = new Image(file.toURI().toString());
            imageViewOfLevel.setImage(image);

        } else if (level >= 50) {
            File file = new File("src\\main\\resources\\Images\\level2.png");
            Image image = new Image(file.toURI().toString());
            imageViewOfLevel.setImage(image);

        } else {
            File file = new File("src\\main\\resources\\Images\\level3.png");
            Image image = new Image(file.toURI().toString());
            imageViewOfLevel.setImage(image);
        }

    }
    @FXML
    private void goBattleShipMainMenu(ActionEvent actionEvent) throws IOException {
        playMouseSound();

        URL url = new File("src/main/resources/FXML/BattleShipMainMenu.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    public void playMouseSound(){
        File file = new File("src\\main\\resources\\Sound\\Click.mp3");
        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setImageViewOfLevel();
            setPieChart();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
