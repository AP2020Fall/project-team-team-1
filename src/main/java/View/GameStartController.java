package View;

import Controller.AdminController.AdminGeneralController;
import Controller.BattleSeaController.BattleSeaController;
import Controller.Exception.BattleShip.BattleShipWinner;
import Controller.Exception.BattleShip.BoomCheckException;
import Controller.Exception.BattleShip.CorrectCoordinateForShipException;
import Controller.Exception.BattleShip.InvalidCommandException;
import Controller.PlayerController.Game;
import Controller.PlayerController.PlayerGeneralController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class GameStartController implements Initializable {
    private static PlayerGeneralController playerGeneralController;
    private static AdminGeneralController adminGeneralController = new AdminGeneralController();
    private static BattleSeaController battleSeaController1;
    private static GridPane gridPanePlayer1;
    private static GridPane gridPanePlayer2;
    private static Boolean passForNextTurnPlayer1 ;
    private static Boolean passForNextTurnPlayer2 ;
    private static long score = 10 ;

    public static long getScore() {
        return score;
    }

    public static void setScore(long score) {
        GameStartController.score = score;
    }

    public static Boolean getPassForNextTurnPlayer1() {
        return passForNextTurnPlayer1;
    }

    public static void setPassForNextTurnPlayer1(Boolean passForNextTurnPlayer1) {
        GameStartController.passForNextTurnPlayer1 = passForNextTurnPlayer1;
    }

    public static Boolean getPassForNextTurnPlayer2() {
        return passForNextTurnPlayer2;
    }

    public static void setPassForNextTurnPlayer2(Boolean passForNextTurnPlayer2) {
        GameStartController.passForNextTurnPlayer2 = passForNextTurnPlayer2;
    }

    public static GridPane getGridPanePlayer1() {
        return gridPanePlayer1;
    }

    public static void setGridPanePlayer1(GridPane gridPanePlayer1) {
        GameStartController.gridPanePlayer1 = gridPanePlayer1;
    }

    public static GridPane getGridPanePlayer2() {
        return gridPanePlayer2;
    }

    public static void setGridPanePlayer2(GridPane gridPanePlayer2) {
        GameStartController.gridPanePlayer2 = gridPanePlayer2;
    }

    public static BattleSeaController getBattleSeaController1() {
        return battleSeaController1;
    }

    public static void setBattleSeaController1(BattleSeaController battleSeaController1) {
        GameStartController.battleSeaController1 = battleSeaController1;
    }

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

    @FXML
    Pane ownBoardPlayer2;

    @FXML
    GridPane gridPlayerPlayer2Own;

    @FXML
    Pane player2GamePane;

    @FXML
    GridPane gridPlayerPlayer2Enemy;

    @FXML
    TextField player2X;

    @FXML
    TextField player2Y;

    @FXML
    Label player2Error;

    @FXML
    ImageView player2Stat;
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
    Pane forZoom;
    /*********************************************************************************/

    @FXML
    private void player1Attack(ActionEvent event) throws IOException {

        if (passForNextTurnPlayer1){
            player1Error.setText("You Already Attacked ! Push Next Turn ");
            player1Stat.setVisible(true);
            player1Error.setVisible(true);
            return;
        }

        if (player1X.getText().isEmpty() || player1Y.getText().isEmpty()){
            player1Error.setText("Empty Inputs");
            player1Stat.setVisible(true);
            player1Error.setVisible(true);
            return;

        }
        if (!player1X.getText().matches("(^([1-9]|10)$)") || !player1Y.getText().matches("(^([1-9]|10)$)")){
            player1Error.setText("Enter number Between 1 to 10");
            player1Stat.setVisible(true);
            player1Error.setVisible(true);
            return;
        }

        String boom = "boom "+player1X.getText().trim()+","+player1Y.getText().trim();
        String result = "";
        try {
            result = getBattleSeaController1().boomProcessor("player1",boom);
        } catch (BattleShipWinner battleShipWinner) {
            Game.giveScoreAndEditPlayerLog(adminGeneralController.firstGameNameGetter(), BattleTestController.getPlayer1(), BattleTestController.getPlayer2(), getScore() );
            Game.historySaver(LocalDate.now(),  BattleTestController.getPlayer1(),  BattleTestController.getPlayer2(), adminGeneralController.firstGameNameGetter());

            BattleWinnerController.setWinnerPlayerUsername(BattleTestController.getPlayer1());
            URL url = new File("src/main/resources/FXML/BattleWinner.fxml").toURI().toURL();
            Parent register = FXMLLoader.load(url);
            Scene message = new Scene(register);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(message);
            window.show();

        } catch (BoomCheckException e) {
            player1Error.setText(e.getMessage());
            player1Stat.setVisible(true);
            player1Error.setVisible(true);
            return;

        } catch (InvalidCommandException e) {
            player1Error.setText(e.getMessage());
            player1Stat.setVisible(true);
            player1Error.setVisible(true);
            return;

        } catch (CorrectCoordinateForShipException e) {
            player1Error.setText(e.getMessage());
            player1Stat.setVisible(true);
            player1Error.setVisible(true);
            return;

        }


        if (result.equals("InCorrect Boom")){

            File file = new File("src\\main\\resources\\Images\\cross.png");
            ImageView image = new ImageView(file.toURI().toString());
            ImageView image1 = new ImageView(file.toURI().toString());
            image.setFitWidth(38);
            image.setFitHeight(38);
            image1.setFitWidth(38);
            image1.setFitHeight(38);
            int x = Integer.parseInt(player1X.getText())-1;
            int y = Integer.parseInt(player1Y.getText())-1;
            gridPlayerPlayer1Enemy.add(image, x, y);
            gridPlayerPlayer2Own.add(image1,x,y);
            setPassForNextTurnPlayer1(true);

        } else {


            File file = new File("src\\main\\resources\\Images\\BattleShip\\explosion.png");
            ImageView image = new ImageView(file.toURI().toString());
            ImageView image1 = new ImageView(file.toURI().toString());
            image.setFitWidth(38);
            image.setFitHeight(38);
            image1.setFitWidth(38);
            image1.setFitHeight(38);
            int x = Integer.parseInt(player1X.getText())-1;
            int y = Integer.parseInt(player1Y.getText())-1;
            gridPlayerPlayer1Enemy.add(image, x, y);
            gridPlayerPlayer2Own.add(image1,x,y);

        }
        setErrorsOff();

    }


    @FXML
    private void player2Attack(ActionEvent event) throws IOException {

        if (passForNextTurnPlayer2){
            player2Error.setText("You Already Attacked ! Push Next Turn ");
            player2Stat.setVisible(true);
            player2Error.setVisible(true);
            return;
        }

        if (player2X.getText().isEmpty() || player2Y.getText().isEmpty()){
            player2Error.setText("Empty Inputs");
            player2Stat.setVisible(true);
            player2Error.setVisible(true);
            return;

        }
        if (!player2X.getText().matches("(^([1-9]|10)$)") || !player2Y.getText().matches("(^([1-9]|10)$)")){
            player2Error.setText("Enter number Between 1 to 10");
            player2Stat.setVisible(true);
            player2Error.setVisible(true);
            return;
        }

        String boom = "boom "+player2X.getText().trim()+","+player2Y.getText().trim();
        String result = "";
        try {
            result = getBattleSeaController1().boomProcessor("player2",boom);
        } catch (BattleShipWinner battleShipWinner) {
            Game.giveScoreAndEditPlayerLog(adminGeneralController.firstGameNameGetter(), BattleTestController.getPlayer2(), BattleTestController.getPlayer1(), getScore());
            Game.historySaver(LocalDate.now(), BattleTestController.getPlayer2(), BattleTestController.getPlayer1(), adminGeneralController.firstGameNameGetter());

            BattleWinnerController.setWinnerPlayerUsername(BattleTestController.getPlayer2());
            URL url = new File("src/main/resources/FXML/BattleWinner.fxml").toURI().toURL();
            Parent register = FXMLLoader.load(url);
            Scene message = new Scene(register);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(message);
            window.show();

        } catch (BoomCheckException e) {
            player2Error.setText(e.getMessage());
            player2Stat.setVisible(true);
            player2Error.setVisible(true);
            return;

        } catch (InvalidCommandException e) {
            player2Error.setText(e.getMessage());
            player2Stat.setVisible(true);
            player2Error.setVisible(true);
            return;

        } catch (CorrectCoordinateForShipException e) {
            player2Error.setText(e.getMessage());
            player2Stat.setVisible(true);
            player2Error.setVisible(true);
            return;

        }


        if (result.equals("InCorrect Boom")){

            File file = new File("src\\main\\resources\\Images\\cross.png");
            ImageView image = new ImageView(file.toURI().toString());
            ImageView image1 = new ImageView(file.toURI().toString());
            image.setFitWidth(38);
            image.setFitHeight(38);
            image1.setFitWidth(38);
            image1.setFitHeight(38);
            int x = Integer.parseInt(player2X.getText())-1;
            int y = Integer.parseInt(player2Y.getText())-1;
            gridPlayerPlayer2Enemy.add(image, x, y);
            gridPlayerPlayer1Own.add(image1,x,y);
            setPassForNextTurnPlayer2(true);

        } else {

            File file = new File("src\\main\\resources\\Images\\BattleShip\\explosion.png");
            ImageView image = new ImageView(file.toURI().toString());
            ImageView image1 = new ImageView(file.toURI().toString());
            image.setFitWidth(38);
            image.setFitHeight(38);
            image1.setFitWidth(38);
            image1.setFitHeight(38);
            int x = Integer.parseInt(player2X.getText())-1;
            int y = Integer.parseInt(player2Y.getText())-1;
            gridPlayerPlayer2Enemy.add(image, x, y);
            gridPlayerPlayer1Own.add(image1,x,y);

        }

        setErrorsOff();
    }
    /*********************************************************************************/


    @FXML
    private void player1NexttTurn(ActionEvent event) {
        if (!passForNextTurnPlayer1){
            player1Error.setText("Attack First");
            player1Stat.setVisible(true);
            player1Error.setVisible(true);
            return;
        }
        setPassForNextTurnPlayer1(false);
        player1GamePane.setVisible(false);
        setErrorsOff();

    }

    @FXML
    private void player2NexttTurn(ActionEvent event) {
        if (!passForNextTurnPlayer2){
            player2Error.setText("Attack First");
            player2Stat.setVisible(true);
            player2Error.setVisible(true);
            return;
        }
        setPassForNextTurnPlayer2(false);
        player1GamePane.setVisible(true);
        setErrorsOff();

    }
    /*********************************************************************************/
    @FXML
    private void player1OwnBoardView(ActionEvent event) {
        ownBoardPlayer1.setVisible(true);
    }

    @FXML
    private void player2OwnBoardView(ActionEvent event) {
        ownBoardPlayer2.setVisible(true);
    }

    @FXML
    void ownBoardPlayer1Off(ActionEvent event) {
        ownBoardPlayer1.setVisible(false);
    }

    @FXML
    void ownBoardPlayer2Off(ActionEvent event) {
        ownBoardPlayer2.setVisible(false);
    }

    /*********************************************************************************/

    @FXML
    void SurrenderPlayer1(ActionEvent event) throws IOException {
        Game.giveScoreAndEditPlayerLog(adminGeneralController.firstGameNameGetter(), BattleTestController.getPlayer2(), BattleTestController.getPlayer1(), getScore());
        Game.historySaver(LocalDate.now(), BattleTestController.getPlayer2(), BattleTestController.getPlayer1(), adminGeneralController.firstGameNameGetter());

        BattleWinnerController.setWinnerPlayerUsername(BattleTestController.getPlayer2());
        URL url = new File("src/main/resources/FXML/BattleWinner.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    @FXML
    void SurrenderPlayer2(ActionEvent event) throws IOException {
        Game.giveScoreAndEditPlayerLog(adminGeneralController.firstGameNameGetter(), BattleTestController.getPlayer1(), BattleTestController.getPlayer2(), getScore() );
        Game.historySaver(LocalDate.now(),  BattleTestController.getPlayer1(),  BattleTestController.getPlayer2(), adminGeneralController.firstGameNameGetter());

        BattleWinnerController.setWinnerPlayerUsername(BattleTestController.getPlayer1());
        URL url = new File("src/main/resources/FXML/BattleWinner.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }


    /*********************************************************************************/

    @FXML
    private void setErrorsOff(){
        player1Stat.setVisible(false);
        player2Stat.setVisible(false);

        player1Error.setVisible(false);
        player2Error.setVisible(false);
    }
    @FXML
    private void setOwnBoardsOff(){
        ownBoardPlayer1.setVisible(false);
        ownBoardPlayer2.setVisible(false);
    }
    @FXML
    private void setProfiles() throws MalformedURLException {
        player1User.setText(BattleTestController.getPlayer1());
        player2User.setText(BattleTestController.getPlayer2());
        point.setText(getScore()+"PT");

        String path = "src"+ File.separator+"main"+File.separator+"resources"+File.separator
                +"Users"+File.separator+BattleTestController.getPlayer1()+File.separator
                +BattleTestController.getPlayer1()+".jpg";

        URL url = new File(path).toURI().toURL();
        player1Pro.setImage(new Image(url.toExternalForm()));

        String path1 = "src"+ File.separator+"main"+File.separator+"resources"+File.separator
                +"Users"+File.separator+BattleTestController.getPlayer2()+File.separator
                +BattleTestController.getPlayer2()+".jpg";
        URL url1 = new File(path1).toURI().toURL();
        player2Pro.setImage(new Image(url1.toExternalForm()));

    }

    public void addMouseScrolling(Node node) {
        node.setOnScroll((ScrollEvent event) -> {
            // Adjust the zoom factor as per your requirement
            double zoomFactor = 1.05;
            double deltaY = event.getDeltaY();
            if (deltaY < 0){
                zoomFactor = 2.0 - zoomFactor;
            }
            node.setScaleX(node.getScaleX() * zoomFactor);
            node.setScaleY(node.getScaleY() * zoomFactor);
            if (node.getScaleX() > 1.5){
                forZoom.setVisible(true);
                node.toFront();
                node.setLayoutX(280);
                node.setLayoutY(250);

            }
            if (node.getScaleX() < 1.5){
                forZoom.setVisible(false);
                node.toBack();

                node.setLayoutX(159);
                node.setLayoutY(12);
            }
        });
    }
    public void addMouseScrolling2(Node node) {
        node.setOnScroll((ScrollEvent event) -> {
            // Adjust the zoom factor as per your requirement
            double zoomFactor = 1.05;
            double deltaY = event.getDeltaY();
            if (deltaY < 0){
                zoomFactor = 2.0 - zoomFactor;
            }
            node.setScaleX(node.getScaleX() * zoomFactor);
            node.setScaleY(node.getScaleY() * zoomFactor);
            if (node.getScaleX() > 1.5){
                forZoom.setVisible(true);
                node.toFront();
                node.setLayoutX(280);
                node.setLayoutY(250);

            }
            if (node.getScaleX() < 1.5){
                forZoom.setVisible(false);
                node.toBack();

                node.setLayoutX(374);
                node.setLayoutY(12);
            }
        });
    }

    /*********************************************************************************/

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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

        gridPlayerPlayer2Own.getChildren().clear();
        gridPlayerPlayer2Own.getChildren().addAll(getGridPanePlayer2().getChildren());

        setPassForNextTurnPlayer1(false);
        setPassForNextTurnPlayer2(false);
        addMouseScrolling(player1Pro);
        addMouseScrolling2(player2Pro);
    }
}
