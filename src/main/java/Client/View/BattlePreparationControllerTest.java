package Client.View;

import Client.DataLoader;
import Server.Controller.BattleSeaController.BattleSeaController;
import Server.Controller.Exception.BattleShip.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class BattlePreparationControllerTest implements Initializable {
    private static final DataLoader dataLoader = new DataLoader();


    private static String player1 = "player";
    private static String player2 = "player2";
    private static long score ;
//    BattleSeaController battleSeaController1 ;

    public static long getScore() {
        return score;
    }

    public static void setScore(long score) {
        BattlePreparationControllerTest.score = score;
    }

//    public BattleSeaController getBattleSeaController1() {
//        return battleSeaController1;
//    }
//
//    public void setBattleSeaController1(BattleSeaController battleSeaController1) {
//        this.battleSeaController1 = battleSeaController1;
//    }

    public static String getPlayer1() {
        return player1;
    }

    public static void setPlayer1(String player1) {
        BattlePreparationControllerTest.player1 = player1;
    }

    public static String getPlayer2() {
        return player2;
    }

    public static void setPlayer2(String player2) {
        BattlePreparationControllerTest.player2 = player2;
    }

    @FXML
    GridPane gridPlayer1;
    @FXML
    Button btnStartPlayer1;
    @FXML
    Pane player1StartPane;
    @FXML
    Pane player1Pane;
    @FXML
    Label player1Username;
    @FXML
    Label player2Username;

    /**************** player1 ship Choose ******************/
    @FXML
    ImageView ship0;
    @FXML
    ImageView ship1;
    @FXML
    ImageView ship2;
    @FXML
    ImageView ship3;
    @FXML
    ImageView ship4;
    @FXML
    TextField shipNum;
    @FXML
    TextField coorOrDir;
    @FXML
    ImageView stat;
    @FXML
    Button process;
    @FXML
    Label error;


    @FXML
    private void setShip0() {
        shipNum.setText("Battleship Id: 1");
    }

    @FXML
    private void setShip1() {
        shipNum.setText("AirCarrier Id: 2");
    }

    @FXML
    private void setShip2() {
        shipNum.setText("Cruiser Id: 3");
    }

    @FXML
    private void setShip3() {
        shipNum.setText("Destroyer Id: 4");
    }

    @FXML
    private void setShip4() {
        shipNum.setText("Submarine Id: 5");
    }

    @FXML
    private void process() throws IOException {
        playMouseSound();
        if (coorOrDir.getText().isEmpty() || shipNum.getText().isEmpty()) {
            error.setText("Inputs are Empty");
            stat.setVisible(true);
            error.setVisible(true);
            return;
        }
        stat.setVisible(false);
        String[] shipId = shipNum.getText().split(" ");

            if (coorOrDir.getText().equals("direction")) {
                String string = "change ship " + shipId[2] + " direction";
//                battleSeaController1.changeCoordinateProcessor("player1", string);
                dataLoader.changeCoordinateProcessor(LoginController.getUsername(),string);
            } else if (coorOrDir.getText().matches("(^(([1-9]|10),([1-9]|10))$)")){
                String string = "change ship " + shipId[2] + " coordinate to "+coorOrDir.getText();
                dataLoader.changeCoordinateProcessor(LoginController.getUsername(),string);

            }else {
                error.setText("watch out you input");
                stat.setVisible(true);
                error.setVisible(true);
                return;
            }


        error.setVisible(false);

        updateBoard(gridPlayer1);


    }



    @FXML
    private void next(ActionEvent event) throws IOException {
        playMouseSound();
        player1Pane.setVisible(false);
        playMouseSound();
        BattleGameStartControllerTest.setGridPanePlayer1(gridPlayer1);
//        BattleGameStartController.setBattleSeaController1(battleSeaController1);
        BattleGameStartControllerTest.setScore(getScore());
        URL url = new File("src/main/resources/FXML/BattleGameStartTest.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();

    }

    @FXML
    private void next1(ActionEvent event) throws IOException {
        playMouseSound();
        BattleGameStartController.setGridPanePlayer1(gridPlayer1);
//        BattleGameStartController.setBattleSeaController1(battleSeaController1);
        BattleGameStartController.setScore(getScore());
        URL url = new File("src/main/resources/FXML/BattleGameStart.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }


    @FXML
    private void setPlayer1Username() {
        player1Username.setText(LoginController.getUsername() + " Turn");
    }


    @FXML
    private void bringPlayer1PaneToFront() throws IOException {
        updateBoard(gridPlayer1);
        player1StartPane.setVisible(false);
    }


    @FXML
    private void updateBoard(GridPane grid) throws IOException {
        Node node = grid.getChildren().get(0);
        grid.getChildren().clear();
        grid.getChildren().add(node);
        for (int j = 0; j < 5; j++) {
            String string = dataLoader.getPlayerShipCoordinate(LoginController.getUsername(),String.valueOf(j));
            int index = j;

            String[] proses = string.split("\\$");
            int counter = 1;
            if (proses[5].equalsIgnoreCase("VERTICAL")) {
                for (int i = Integer.parseInt(proses[1]); i <= Integer.parseInt(proses[3]); i++) {
                    File file = new File("src\\main\\resources\\Images\\BattleShip\\Ship-Split\\row-" + index + "-col-" + String.valueOf(counter) + ".png");
                    ImageView image = new ImageView(file.toURI().toString());
                    image.setFitWidth(45);
                    image.setFitHeight(30);
                    image.setRotate(90);
                    int x = Integer.parseInt(proses[0]);
                    grid.add(image, x, i);
                    counter++;

                }
            } else {
                for (int i = Integer.parseInt(proses[0]); i <= Integer.parseInt(proses[2]); i++) {
                    File file = new File("src\\main\\resources\\Images\\BattleShip\\Ship-Split\\row-" + index + "-col-" + String.valueOf(counter) + ".png");
                    ImageView image = new ImageView(file.toURI().toString());
                    image.setFitWidth(45);
                    image.setFitHeight(30);
                    int y = Integer.parseInt(proses[1]);
                    grid.add(image, i, y);
                    counter++;

                }


            }
        }
    }

    public void playMouseSound(){
        File file = new File("src\\main\\resources\\Sound\\Click.mp3");
        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stat.setVisible(false);
        setPlayer1Username();
        error.setVisible(false);

    }
}
