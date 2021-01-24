package Client.View;

import Server.Controller.AdminController.AdminGeneralController;
import Server.Controller.Exception.Plato.InvalidGameNameException;
import Server.Controller.PlayerController.PlayerGeneralController;
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
    protected static AdminGeneralController adminGeneralController = new AdminGeneralController();
    protected static PlayerGeneralController playerGeneralController = new PlayerGeneralController();


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
        } catch (InvalidGameNameException e) {
            System.out.println(e.getMessage() + e.getGameName());
        }

    }

    @FXML
    private void setPieChart() throws InvalidGameNameException {

        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(
                new PieChart.Data("Wins", Integer.parseInt(playerGeneralController.showNumberOFWins(LoginController.getUsername(), adminGeneralController.firstGameNameGetter()))),
                new PieChart.Data("Losses", Integer.parseInt(playerGeneralController.numberOfLossesInThisGame(LoginController.getUsername(), adminGeneralController.firstGameNameGetter()))));
        pieChartData.forEach(data ->
                data.nameProperty().bind(
                        Bindings.concat(
                                data.getName(), " ", data.pieValueProperty(), " Times"
                        )
                )
        );
        numberofplayed.setText("You have played this Game for " + playerGeneralController.showNumberOfGamePlayedInThisGame(LoginController.getUsername(), adminGeneralController.firstGameNameGetter()) + " Times");
        pieChart.setLegendVisible(false);
        pieChart.setData(pieChartData);
        pieChart.setStartAngle(180);
    }

    @FXML
    private void setImageViewOfLevel() throws InvalidGameNameException {
        int level = Integer.parseInt(playerGeneralController.showPlayerPointsInThisGame(LoginController.getUsername(), adminGeneralController.firstGameNameGetter()));
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
        } catch (InvalidGameNameException e) {
            System.err.println(e.getMessage());
        }
    }
}
