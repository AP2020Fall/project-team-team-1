package Client.View;

import Controller.BattleSeaController.BattleSeaController;
import Controller.Exception.BattleShip.*;
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
import java.net.URL;
import java.util.ResourceBundle;

public class BattleTestController implements Initializable {
    private static String player1 = "player";
    private static String player2 = "player2";
    private static long score ;
    BattleSeaController battleSeaController1 ;

    public static long getScore() {
        return score;
    }

    public static void setScore(long score) {
        BattleTestController.score = score;
    }

    public BattleSeaController getBattleSeaController1() {
        return battleSeaController1;
    }

    public void setBattleSeaController1(BattleSeaController battleSeaController1) {
        this.battleSeaController1 = battleSeaController1;
    }

    public static String getPlayer1() {
        return player1;
    }

    public static void setPlayer1(String player1) {
        BattleTestController.player1 = player1;
    }

    public static String getPlayer2() {
        return player2;
    }

    public static void setPlayer2(String player2) {
        BattleTestController.player2 = player2;
    }

    @FXML
    GridPane gridPlayer1;
    @FXML
    GridPane gridPlayer2;
    @FXML
    Button btnStartPlayer1;
    @FXML
    Button btnStartPlayer2;
    @FXML
    Pane player1StartPane;
    @FXML
    Pane player2StartPane;
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

    /**************** player2 ship Choose ******************/
    @FXML
    ImageView ship01;
    @FXML
    ImageView ship11;
    @FXML
    ImageView ship21;
    @FXML
    ImageView ship31;
    @FXML
    ImageView ship41;
    @FXML
    TextField shipNum1;
    @FXML
    TextField coorOrDir1;
    @FXML
    ImageView stat1;
    @FXML
    Button process1;
    @FXML
    Label error1;


    /*******************************************************/


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
    private void setShip01() {
        shipNum1.setText("Battleship Id: 1");
    }

    @FXML
    private void setShip11() {
        shipNum1.setText("AirCarrier Id: 2");
    }

    @FXML
    private void setShip21() {
        shipNum1.setText("Cruiser Id: 3");
    }

    @FXML
    private void setShip31() {
        shipNum1.setText("Destroyer Id: 4");
    }

    @FXML
    private void setShip41() {
        shipNum1.setText("Submarine Id: 5");
    }

    @FXML
    private void process() {
        playMouseSound();
        if (coorOrDir.getText().isEmpty() || shipNum.getText().isEmpty()) {
            error.setText("Inputs are Empty");
            stat.setVisible(true);
            error.setVisible(true);
            return;
        }
        stat.setVisible(false);
        String[] shipId = shipNum.getText().split(" ");
        try {

            if (coorOrDir.getText().equals("direction")) {
                String string = "change ship " + shipId[2] + " direction";
                battleSeaController1.changeCoordinateProcessor("player1", string);
            } else if (coorOrDir.getText().matches("(^(([1-9]|10),([1-9]|10))$)")){
                String string = "change ship " + shipId[2] + " coordinate to "+coorOrDir.getText();
                battleSeaController1.changeCoordinateProcessor("player1", string);

            }else {
                error.setText("watch out you input");
                stat.setVisible(true);
                error.setVisible(true);
                return;
            }

        } catch (PlacedShipException e) {
            error.setText(e.getMessage());
            stat.setVisible(true);
            error.setVisible(true);
            return;

        } catch (NewCoordinateForShipException e) {
            error.setText(e.getMessage());
            stat.setVisible(true);
            error.setVisible(true);
            return;


        } catch (CorrectCoordinateForShipException e) {
            error.setText(e.getMessage());
            stat.setVisible(true);
            error.setVisible(true);
            return;


        } catch (ExistOtherShipException e) {
            error.setText(e.getMessage());
            stat.setVisible(true);
            error.setVisible(true);
            return;


        } catch (InvalidCommandException e) {
            error.setText(e.getMessage());
            stat.setVisible(true);
            error.setVisible(true);
            return;


        }
        error.setVisible(false);

        updateBoard(gridPlayer1);


    }
    @FXML
    private void process1() {
        playMouseSound();
        if (coorOrDir1.getText().isEmpty() || shipNum1.getText().isEmpty()) {
            error1.setText("Inputs are Empty");
            stat1.setVisible(true);
            error1.setVisible(true);
            return;
        }
        stat1.setVisible(false);
        String[] shipId = shipNum1.getText().split(" ");
        try {

            if (coorOrDir1.getText().equals("direction")) {
                String string = "change ship " + shipId[2] + " direction";
                battleSeaController1.changeCoordinateProcessor("player2", string);
            } else if (coorOrDir1.getText().matches("(^(([1-9]|10),([1-9]|10))$)")){
                String string = "change ship " + shipId[2] + " coordinate to "+coorOrDir1.getText();
                battleSeaController1.changeCoordinateProcessor("player2", string);

            }else {
                error1.setText("watch out you input");
                stat1.setVisible(true);
                error1.setVisible(true);
                return;
            }

        } catch (PlacedShipException e) {
            error1.setText(e.getMessage());
            stat1.setVisible(true);
            error1.setVisible(true);
            return;


        } catch (NewCoordinateForShipException e) {
            error1.setText(e.getMessage());
            stat1.setVisible(true);
            error1.setVisible(true);
            return;

        } catch (CorrectCoordinateForShipException e) {
            error1.setText(e.getMessage());
            stat1.setVisible(true);
            error1.setVisible(true);
            return;

        } catch (ExistOtherShipException e) {
            error1.setText(e.getMessage());
            stat1.setVisible(true);
            error1.setVisible(true);
            return;

        } catch (InvalidCommandException e) {
            error1.setText(e.getMessage());
            stat1.setVisible(true);
            error1.setVisible(true);
            return;

        }
        error1.setVisible(false);

        updateBoard1(gridPlayer2);
    }


    @FXML
    private void next() {
        playMouseSound();
        player1Pane.setVisible(false);

    }

    @FXML
    private void next1(ActionEvent event) throws IOException {
        playMouseSound();
        GameStartController.setGridPanePlayer1(gridPlayer1);
        GameStartController.setGridPanePlayer2(gridPlayer2);
        GameStartController.setBattleSeaController1(battleSeaController1);
        GameStartController.setScore(getScore());
        URL url = new File("src/main/resources/FXML/GameStart.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }


    @FXML
    private void setPlayer1Username() {
        player1Username.setText(player1 + " as  \"player1\"  Turn");
    }

    @FXML
    private void setPlayer2Username() {
        player2Username.setText(player2 + " as  \"player2\"  Turn");
    }

    @FXML
    private void bringPlayer1PaneToFront() {
        updateBoard(gridPlayer1);
        player1StartPane.setVisible(false);
    }

    @FXML
    private void bringPlayer2PaneToFront() {
        updateBoard1(gridPlayer2);
        player2StartPane.setVisible(false);
    }


    @FXML
    private void updateBoard(GridPane grid) {
        Node node = grid.getChildren().get(0);
        grid.getChildren().clear();
        grid.getChildren().add(node);
        for (int j = 0; j < 5; j++) {
            String string = battleSeaController1.getPlayer1Coordinate(j);
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

    @FXML
    private void updateBoard1(GridPane grid) {
        Node node = grid.getChildren().get(0);
        grid.getChildren().clear();
        grid.getChildren().add(node);
        for (int j = 0; j < 5; j++) {
            String string = battleSeaController1.getPlayer2Coordinate(j);
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
        setBattleSeaController1(new BattleSeaController());
        battleSeaController1.addPlayersToArrayList();
        battleSeaController1.randomShipPlaceForPlayer1();
        battleSeaController1.randomShipPlaceForPlayer2();
        stat.setVisible(false);
        stat1.setVisible(false);
        setPlayer1Username();
        setPlayer2Username();
        error.setVisible(false);
        error1.setVisible(false);

    }
}
