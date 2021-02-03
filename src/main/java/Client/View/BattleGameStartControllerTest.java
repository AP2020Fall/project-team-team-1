package Client.View;

import Client.Client;
import Client.DataLoader;
import Server.Controller.AdminController.AdminGeneralController;
import Server.Controller.BattleSeaController.BattleSeaController;
import Server.Controller.Exception.BattleShip.BattleShipWinner;
import Server.Controller.Exception.BattleShip.BoomCheckException;
import Server.Controller.Exception.BattleShip.CorrectCoordinateForShipException;
import Server.Controller.Exception.BattleShip.InvalidCommandException;
//import Server.Controller.PlayerController.Game;
import Server.Controller.PlayerController.PlayerGeneralController;
import Server.Model.PlatoModel.Event;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class BattleGameStartControllerTest implements Initializable {
    private static final DataLoader dataLoader = new DataLoader();

    private static String turn = "player1";
    private static boolean pass = false;

    public static String getTurn() {
        return turn;
    }

    public static void setTurn(String turn) {
        BattleGameStartControllerTest.turn = turn;
    }

    private static final File file = new File("src\\main\\resources\\Sound\\medalion.mp3");
    protected static Media media = new Media(file.toURI().toString());
    protected static MediaPlayer mediaPlayer = new MediaPlayer(media);
//    private static PlayerGeneralController playerGeneralController;
//    private static AdminGeneralController adminGeneralController = new AdminGeneralController();
//    private static BattleSeaController battleSeaController1;
    private static GridPane gridPanePlayer1;
//    private static GridPane gridPanePlayer2;
    private static Boolean passForNextTurnPlayer1;
//    private static Boolean passForNextTurnPlayer2;
    private static long score = 10;
//    private static String timeForGame = "10";
    Timer timer1 = new Timer();
//    Timer timer2 = new Timer();

//    public static String getTimeForGame() {
//        return timeForGame;
//    }
//
//    public static void setTimeForGame(String timeForGame) {
//        BattleGameStartControllerTest.timeForGame = timeForGame;
//    }

    public static long getScore() {
        return score;
    }

    public static void setScore(long score) {
        BattleGameStartControllerTest.score = score;
    }

    public static Boolean getPassForNextTurnPlayer1() {
        return passForNextTurnPlayer1;
    }

    public static void setPassForNextTurnPlayer1(Boolean passForNextTurnPlayer1) {
        BattleGameStartControllerTest.passForNextTurnPlayer1 = passForNextTurnPlayer1;
    }

//    public static Boolean getPassForNextTurnPlayer2() {
//        return passForNextTurnPlayer2;
//    }
//
//    public static void setPassForNextTurnPlayer2(Boolean passForNextTurnPlayer2) {
//        BattleGameStartControllerTest.passForNextTurnPlayer2 = passForNextTurnPlayer2;
//    }

    public static GridPane getGridPanePlayer1() {
        return gridPanePlayer1;
    }

    public static void setGridPanePlayer1(GridPane gridPanePlayer1) {
        BattleGameStartControllerTest.gridPanePlayer1 = gridPanePlayer1;
    }

//    public static GridPane getGridPanePlayer2() {
//        return gridPanePlayer2;
//    }
//
//    public static void setGridPanePlayer2(GridPane gridPanePlayer2) {
//        BattleGameStartControllerTest.gridPanePlayer2 = gridPanePlayer2;
//    }

//    public static BattleSeaController getBattleSeaController1() {
//        return battleSeaController1;
//    }
//
//    public static void setBattleSeaController1(BattleSeaController battleSeaController1) {
//        BattleGameStartControllerTest.battleSeaController1 = battleSeaController1;
//    }

    /***************************** Player1 *****************************/

    @FXML
    Pane ownBoardPlayer1;

    @FXML
    GridPane gridPlayerPlayer1Own;

    @FXML
    Pane player1GamePane;

    @FXML
    GridPane gridPlayerPlayer1Enemy;

    @FXML
    TextField player1X;

    @FXML
    TextField player1Y;

    @FXML
    Label player1Error;

    @FXML
    ImageView player1Stat;
    /***************************** Player2 *****************************/

//    @FXML
//    Pane ownBoardPlayer2;
//
//    @FXML
//    GridPane gridPlayerPlayer2Own;
//
//    @FXML
//    Pane player2GamePane;
//
//    @FXML
//    GridPane gridPlayerPlayer2Enemy;
//
//    @FXML
//    TextField player2X;
//
//    @FXML
//    TextField player2Y;
//
//    @FXML
//    Label player2Error;
//
//    @FXML
//    ImageView player2Stat;
    /***************************** Info *****************************/
    @FXML
    ImageView player1Pro;

    @FXML
    ImageView player2Pro;

    @FXML
    Label player1User;

    @FXML
    Label player2User;

    @FXML
    Label point;
    @FXML
    Label timerView;

    @FXML
    Pane forZoom;

    /*********************************************************************************/

    @FXML
    private void player1Attack(ActionEvent event) throws IOException {
        playMouseSound();
        if (getTurn().equals(dataLoader.enemyUsername(LoginController.getUsername()))) {
            player1Error.setText("Wait for Enemy");
            player1Stat.setVisible(true);
            player1Error.setVisible(true);
            return;
        }

        if (player1X.getText().isEmpty() || player1Y.getText().isEmpty()) {
            player1Error.setText("Empty Inputs");
            player1Stat.setVisible(true);
            player1Error.setVisible(true);
            return;

        }
        if (!player1X.getText().matches("(^([1-9]|10)$)") || !player1Y.getText().matches("(^([1-9]|10)$)")) {
            player1Error.setText("Enter number Between 1 to 10");
            player1Stat.setVisible(true);
            player1Error.setVisible(true);
            return;
        }

        String boom = "boom " + player1X.getText().trim() + "," + player1Y.getText().trim();
        String result = "";
            result = dataLoader.playerAttack(LoginController.getUsername(),boom);

            if (result.startsWith(" is the Winner")){
                dataLoader.giveScoreAndEditPlayerLog(dataLoader.firstGameNameGetter(),LoginController.getUsername(),dataLoader.enemyUsername(LoginController.getUsername()),getScore());
                dataLoader.historySaver(LocalDate.now(),LoginController.getUsername(),dataLoader.enemyUsername(LoginController.getUsername()), dataLoader.firstGameNameGetter());
                dataLoader.removeGameMatcher(LoginController.getUsername());
                BattleWinnerController.setWinnerPlayerUsername(BattlePreparationController.getPlayer1());
                URL url = new File("src/main/resources/FXML/BattleWinner.fxml").toURI().toURL();
                Parent register = FXMLLoader.load(url);
                Scene message = new Scene(register);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(message);
                window.show();
            }



        if (result.equals("InCorrect Boom")) {
            playWaterSound();
            File file = new File("src\\main\\resources\\Images\\cross.png");
            ImageView image = new ImageView(file.toURI().toString());
            ImageView image1 = new ImageView(file.toURI().toString());
            image.setFitWidth(38);
            image.setFitHeight(38);
            image1.setFitWidth(38);
            image1.setFitHeight(38);
            int x = Integer.parseInt(player1X.getText()) - 1;
            int y = Integer.parseInt(player1Y.getText()) - 1;
            FadeTransition ft = new FadeTransition(Duration.millis(3000), image);
            ft.setFromValue(1.0);
            ft.setToValue(0.3);
            ft.setCycleCount(2);
            ft.setAutoReverse(true);

            ft.play();
            gridPlayerPlayer1Enemy.add(image, x, y);
//            gridPlayerPlayer2Own.add(image1, x, y);
            dataLoader.battleShipChangeTurn(LoginController.getUsername());
            setPassForNextTurnPlayer1(true);

        } else {

            playBoomSound();
            File file = new File("src\\main\\resources\\Images\\BattleShip\\explosion.png");
            ImageView image = new ImageView(file.toURI().toString());
            ImageView image1 = new ImageView(file.toURI().toString());
            image.setFitWidth(38);
            image.setFitHeight(38);
            image1.setFitWidth(38);
            image1.setFitHeight(38);
            int x = Integer.parseInt(player1X.getText()) - 1;
            int y = Integer.parseInt(player1Y.getText()) - 1;

            FadeTransition ft = new FadeTransition(Duration.millis(3000), image);
            ft.setFromValue(1.0);
            ft.setToValue(0.3);
            ft.setCycleCount(2);
            ft.setAutoReverse(true);
            ft.play();

            gridPlayerPlayer1Enemy.add(image, x, y);

//            gridPlayerPlayer2Own.add(image1, x, y);
//            gridPlayerPlayer1Enemy.getChildren(image);

        }
        setErrorsOff();

    }

    @FXML
    private void updateBoard() throws IOException {
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();

        ArrayList<String> outputCorrectBooms = new Gson().fromJson(dataLoader.battleShipPlayerCorrectBoom(dataLoader.enemyUsername(LoginController.getUsername())), type);
        ArrayList<String> correctBooms = new ArrayList<>(outputCorrectBooms);

        ArrayList<String> outputInCorrectBooms = new Gson().fromJson(dataLoader.battleShipPlayerInCorrectBoom(dataLoader.enemyUsername(LoginController.getUsername())), type);
        ArrayList<String> inCorrectBooms = new ArrayList<>(outputInCorrectBooms);

        for (String correctBoom : correctBooms) {
            File file = new File("src\\main\\resources\\Images\\explosion.png");
            ImageView image1 = new ImageView(file.toURI().toString());
            image1.setFitWidth(38);
            image1.setFitHeight(38);
            String[] cordinate = correctBoom.split("\\s");
            gridPlayerPlayer1Own.add(image1,Integer.parseInt(cordinate[0]),Integer.parseInt(cordinate[1]));
        }

        for (String inCorrectBoom : inCorrectBooms) {
            File file = new File("src\\main\\resources\\Images\\cross.png");
            ImageView image1 = new ImageView(file.toURI().toString());
            image1.setFitWidth(38);
            image1.setFitHeight(38);
            String[] cordinate = inCorrectBoom.split("\\s");
            gridPlayerPlayer1Own.add(image1,Integer.parseInt(cordinate[0]),Integer.parseInt(cordinate[1]));
        }

    }



    /*********************************************************************************/


    @FXML
    private void player1NexttTurn() {
        timer1.cancel();
//        timer2 = new Timer();
        playMouseSound();
        if (!passForNextTurnPlayer1) {
            player1Error.setText("Attack First");
            player1Stat.setVisible(true);
            player1Error.setVisible(true);
            return;
        }
        player1Y.clear();
        player1X.clear();
        setPassForNextTurnPlayer1(false);
        player1GamePane.setVisible(false);
        setErrorsOff();

    }

    @FXML
    private void player2NexttTurn() {
//        timer2.cancel();
//        timer1 = new Timer();
//        player2X.clear();
//        player2Y.clear();
//        playMouseSound();
//        if (!passForNextTurnPlayer2) {
//            player2Error.setText("Attack First");
//            player2Stat.setVisible(true);
//            player2Error.setVisible(true);
//            return;
//        }
//
//        setPassForNextTurnPlayer2(false);
//        player1GamePane.setVisible(true);
//        setErrorsOff();
//        timer(timer1);

    }

    /*********************************************************************************/
    @FXML
    private void player1OwnBoardView(ActionEvent event) {
        playMouseSound();
        ownBoardPlayer1.setVisible(true);
    }

    @FXML
    void ownBoardPlayer1Off(ActionEvent event) {
        playMouseSound();
        ownBoardPlayer1.setVisible(false);
    }

    /*********************************************************************************/

    @FXML
    public void SurrenderPlayer1(ActionEvent event) throws IOException {
        playMouseSound();
        if (!pass){
            if (LoginController.getUsername().equals(player2User.getText())){
                return;
            }
        }


        dataLoader.giveScoreAndEditPlayerLog(dataLoader.firstGameNameGetter(),dataLoader.enemyUsername(LoginController.getUsername()),LoginController.getUsername(),getScore());
        dataLoader.historySaver(LocalDate.now(),dataLoader.enemyUsername(LoginController.getUsername()),LoginController.getUsername(), dataLoader.firstGameNameGetter());

        dataLoader.playReq("Surrender",dataLoader.enemyUsername(LoginController.getUsername()));
        System.out.println(dataLoader.letsPlay(dataLoader.enemyUsername(LoginController.getUsername())));
        dataLoader.removeGameMatcher(LoginController.getUsername());
        if (pass){
            showWinPage();
        }
        pass = false;
        BattleWinnerController.setWinnerPlayerUsername(BattlePreparationController.getPlayer2());
        URL url = new File("src/main/resources/FXML/BattleWinner.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    @FXML
    public void SurrenderPlayer2(ActionEvent event) throws IOException {
        playMouseSound();
        if (!pass){
            if (LoginController.getUsername().equals(player1User.getText())){
                return;
            }
        }


        dataLoader.giveScoreAndEditPlayerLog(dataLoader.firstGameNameGetter(),LoginController.getUsername(),dataLoader.enemyUsername(LoginController.getUsername()),getScore());
        dataLoader.historySaver(LocalDate.now(),LoginController.getUsername(),dataLoader.enemyUsername(LoginController.getUsername()), dataLoader.firstGameNameGetter());

        dataLoader.playReq("Surrender",dataLoader.enemyUsername(LoginController.getUsername()) );
        System.out.println(dataLoader.letsPlay(dataLoader.enemyUsername(LoginController.getUsername())));
        dataLoader.removeGameMatcher(LoginController.getUsername());

        if (pass){
            showWinPage();
        }
        pass = false;

        BattleWinnerController.setWinnerPlayerUsername(BattlePreparationController.getPlayer1());
        URL url = new File("src/main/resources/FXML/BattleWinner.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }


    /*********************************************************************************/

    @FXML
    private void setErrorsOff() {
        player1Stat.setVisible(false);
        player1Error.setVisible(false);
    }

    @FXML
    private void setOwnBoardsOff() {
        ownBoardPlayer1.setVisible(false);
    }

    @FXML
    private void setProfiles() throws MalformedURLException {
        player1User.setText(BattlePreparationController.getPlayer1());
        player2User.setText(BattlePreparationController.getPlayer2());
        point.setText(score + "PT");

        String path = "src" + File.separator + "main" + File.separator + "resources" + File.separator
                + "Users" + File.separator + BattlePreparationController.getPlayer1() + File.separator
                + BattlePreparationController.getPlayer1() + ".jpg";

        URL url = new File(path).toURI().toURL();
        player1Pro.setImage(new Image(url.toExternalForm()));

        String path1 = "src" + File.separator + "main" + File.separator + "resources" + File.separator
                + "Users" + File.separator + BattlePreparationController.getPlayer2() + File.separator
                + BattlePreparationController.getPlayer2() + ".jpg";
        URL url1 = new File(path1).toURI().toURL();
        player2Pro.setImage(new Image(url1.toExternalForm()));

    }

    public void addMouseScrolling(Node node) {
        node.setOnScroll((ScrollEvent event) -> {
            // Adjust the zoom factor as per your requirement
            double zoomFactor = 1.05;
            double deltaY = event.getDeltaY();
            if (deltaY < 0) {
                zoomFactor = 2.0 - zoomFactor;
            }
            node.setScaleX(node.getScaleX() * zoomFactor);
            node.setScaleY(node.getScaleY() * zoomFactor);
            if (node.getScaleX() > 1.5) {
                forZoom.setVisible(true);
                node.toFront();
                node.setLayoutX(280);
                node.setLayoutY(250);

            }
            if (node.getScaleX() < 1.5) {
                forZoom.setVisible(false);
                node.toBack();

                node.setLayoutX(140);
                node.setLayoutY(12);
            }
        });
    }

    public void addMouseScrolling2(Node node) {
        node.setOnScroll((ScrollEvent event) -> {
            // Adjust the zoom factor as per your requirement
            double zoomFactor = 1.05;
            double deltaY = event.getDeltaY();
            if (deltaY < 0) {
                zoomFactor = 2.0 - zoomFactor;
            }
            node.setScaleX(node.getScaleX() * zoomFactor);
            node.setScaleY(node.getScaleY() * zoomFactor);
            if (node.getScaleX() > 1.5) {
                forZoom.setVisible(true);
                node.toFront();
                node.setLayoutX(280);
                node.setLayoutY(250);

            }
            if (node.getScaleX() < 1.5) {
                forZoom.setVisible(false);
                node.toBack();

                node.setLayoutX(436);
                node.setLayoutY(12);
            }
        });
    }

    public void playWaterSound() {
        File file = new File("src\\main\\resources\\Sound\\water.mp3");
        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    public void playBoomSound() {
        File file = new File("src\\main\\resources\\Sound\\boom.mp3");
        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    public void playMouseSound() {
        File file = new File("src\\main\\resources\\Sound\\Click.mp3");
        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    /*********************************************************************************/
//    private void timer(Timer timer2) {
//        timer2.schedule(new TimerTask() {
//            int time = Integer.parseInt(getTimeForGame());
//
//            @Override
//            public void run() {
//                time--;
////                System.out.println(time);
//                Platform.runLater(() -> timerView.setText(String.valueOf(time)));
//                if (time == 0) {
//                    if (player1GamePane.isVisible()) {
//                        timer2.cancel();
//                        setPassForNextTurnPlayer1(true);
//                        player1NexttTurn();
//                    } else {
//                        timer2.cancel();
//                        setPassForNextTurnPlayer2(true);
//                        player2NexttTurn();
//                    }
//                }
//            }
//        }, 1000, 1000);
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mediaPlayer.setVolume(0.08);
        mediaPlayer.play();
        setErrorsOff();
        setOwnBoardsOff();
        forZoom.setVisible(false);

        try {
            setProfiles();
        } catch (MalformedURLException e) {
            System.err.println(e.getMessage());
        }
        gridPlayerPlayer1Own.getChildren().clear();
        gridPlayerPlayer1Own.getChildren().addAll(getGridPanePlayer1().getChildren());
        whoseTurn();

        setPassForNextTurnPlayer1(false);
//        setPassForNextTurnPlayer2(false);
        addMouseScrolling(player1Pro);
        addMouseScrolling2(player2Pro);
//        timer(timer1);
    }

    @FXML
    private void whoseTurn() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                try {
                    setTurn(dataLoader.battleShipPlayerTurn(LoginController.getUsername()));
                    Platform.runLater(() -> timerView.setText(getTurn()));
                    Platform.runLater(() -> {
                        try {
                            updateBoard();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                    if (dataLoader.letsPlay(LoginController.getUsername()).equals("Surrender")){
                        BattleWinnerController.setWinnerPlayerUsername(dataLoader.enemyUsername(LoginController.getUsername()));

                        timer.cancel();
                        pass = true;
                        SurrenderPlayer1(new ActionEvent());
                        SurrenderPlayer2(new ActionEvent());
                    }
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }

            }
        }, 0, 5000);


    }
    @FXML
    ListView list;

    @FXML
    private void showWinPage() {
        Stage stage = new Stage();
        Object root = null;
        try {
            URL url = new File("src/main/resources/FXML/BattleWinner.fxml").toURI().toURL();
            root = FXMLLoader.load(url);
            stage.setScene(new Scene((Parent) root));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage window = (Stage) player1Pro.getScene().getWindow();
        window.setScene(stage.getScene());
        window.show();
    }
}


