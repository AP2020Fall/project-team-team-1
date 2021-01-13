package View;

import Controller.DotsAndBoxesController.DotsAndBoxesController;
import Controller.PlayerController.PlayerGeneralController;
import Model.DotsAndBoxesModel.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.stream.IntStream;

public class DotsAndBoxesGame implements Initializable {
    protected static DotsAndBoxesController dotsAndBoxesController = new DotsAndBoxesController();
    protected static PlayerGeneralController playerGeneralController = new PlayerGeneralController();
    @FXML
    public AnchorPane board;
    @FXML
    public ImageView imgFirst;
    @FXML
    public ImageView imgSecond;
    @FXML
    public Label lblPlayer1;
    @FXML
    public Label lblPlayer2;
    @FXML
    public Label lblPlayer1Points;
    @FXML
    public Label lblPlayer2Points;
    @FXML
    public Label lblTurn;
    private static String firstPlayer = "";
    private static String secondPlayer = "";
    private Circle[] dots = new Circle[64];
    private double valueX =165;
    private double valueY =165;
    private static int point = 30;

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getFirstPlayer() {
        return firstPlayer;
    }

    public void setFirstPlayer(String firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public String getSecondPlayer() {
        return secondPlayer;
    }

    public void setSecondPlayer(String secondPlayer) {
        this.secondPlayer = secondPlayer;
    }

    @FXML
    private void drawCircles(){
        int number =0;
        for (int i = 0; i <8 ; i++) {
            for (int j = 0; j <8 ; j++) {
                dots[number]=new Circle(5);
                dots[number].setFill(Color.rgb(189, 195, 199));
                dots[number].setStroke(Color.BLACK);
                dots[number].setCenterX(valueX);
                dots[number].setCenterY(valueY);
                dots[number].setId(""+i+"-"+j+"");
                dots[number].setCursor(Cursor.HAND);

                if ((number+1)%8 == 0 && number+1 != 64){
                    valueY+=37;
                    valueX=165;
                }else{
                    valueX+=37;
                }
                board.getChildren().add(dots[number]);
                number++;

            }
        }
    }
    @FXML
    private int findCircleNumber(String id){
        for (int i = 0; i <64 ; i++) {
            if (dots[i].getId().equals(id)){
                return i;
            }
        }
        return -1;
    }
    private String findIdByNumber(int number){
        return dots[number].getId();
    }
    private void setLabels(){
        lblPlayer1.setText(getFirstPlayer());
        lblPlayer1.setTextFill(Color.rgb(192, 57, 43));
        lblPlayer2.setText(getSecondPlayer());
        lblPlayer2.setTextFill(Color.rgb(41, 128, 185));
        lblPlayer1Points.setText(String.valueOf(dotsAndBoxesController.getRedPoints()));
        lblPlayer1Points.setTextFill(Color.rgb(192, 57, 43));
        lblPlayer2Points.setText(String.valueOf(dotsAndBoxesController.getBluePoints()));
        lblPlayer2Points.setTextFill(Color.rgb(41, 128, 185));
        lblTurn.setText(whoseTurnIsIt());
        if (whoseTurnIsIt().equals(firstPlayer)){
            lblTurn.setTextFill(Color.rgb(192, 57, 43));
        }else if (whoseTurnIsIt().equals(secondPlayer)){
            lblTurn.setTextFill(Color.rgb(41, 128, 185));
        }
    }

    @FXML
    private void setOnMouseClicked(MouseEvent mouseEvent){
        Circle circle = (Circle) mouseEvent.getTarget();
        hover(circle.getId());
        try {
            click(circle.getId());
        } catch (IOException ignored) {
            System.out.println(ignored.getMessage());
        }
    }
    @FXML
    private void hover(String id){
        int index = findCircleNumber(id);
        dots[index].setFill(Color.rgb(39, 55, 70));
    }
    @FXML
    private void click(String id) throws IOException {
        int n = findCircleNumber(id);
        if (n % 8 == 0) {
            drawLine(n, n + 1);
        }
        if ((n + 1) % 8 == 0) {
            drawLine(n, n - 1);
        }
        if ((n - 8) >= 0) {
            drawLine(n, n - 8);
        }
        if ((n + 8) <= 64) {
            drawLine(n, n + 8);
        }
        if (n % 8 != 0 && (n + 1) % 8 != 0) {
            drawLine(n, n - 1);
            drawLine(n, n + 1);
        }
    }
    private void drawLine(int first , int second) throws IOException {
        Line line = new Line();
        line.setStrokeWidth(2);
        line.setId(whoseTurnIsIt());
        line.setStroke(findColor());
        if (dots[second].getFill().equals(Color.rgb(39, 55, 70))){
            if (first-second==1){
                line.setStartX(dots[first].getCenterX()-dots[first].getRadius());
                line.setEndX(dots[second].getCenterX()+dots[second].getRadius());
                line.setStartY(dots[first].getCenterY());
                line.setEndY(dots[second].getCenterY());
                System.out.println(""+findIdByNumber(second)+","+findIdByNumber(first)+"");
                dotsAndBoxesController.doTheCommands(""+findIdByNumber(second)+","+findIdByNumber(first)+"");

            }
            if (first-second==-1){
                line.setStartX(dots[first].getCenterX()+dots[first].getRadius());
                line.setEndX(dots[second].getCenterX()-dots[second].getRadius());
                line.setStartY(dots[first].getCenterY());
                line.setEndY(dots[second].getCenterY());
                System.out.println(""+findIdByNumber(first)+","+findIdByNumber(second)+"");
                dotsAndBoxesController.doTheCommands(""+findIdByNumber(first)+","+findIdByNumber(second)+"");

            }
            if (first-second==8){
                line.setStartX(dots[first].getCenterX());
                line.setEndX(dots[second].getCenterX());
                line.setStartY(dots[first].getCenterY() - dots[first].getRadius());
                line.setEndY(dots[second].getCenterY() + dots[second].getRadius());
                System.out.println(""+findIdByNumber(second)+","+findIdByNumber(first)+"");
                dotsAndBoxesController.doTheCommands(""+findIdByNumber(second)+","+findIdByNumber(first)+"");

            }
            if (first-second==-8){
                line.setStartX(dots[first].getCenterX());
                line.setEndX(dots[second].getCenterX());
                line.setStartY(dots[first].getCenterY() + dots[first].getRadius());
                line.setEndY(dots[second].getCenterY() - dots[second].getRadius());
                System.out.println(""+findIdByNumber(first)+","+findIdByNumber(second)+"");
                dotsAndBoxesController.doTheCommands(""+findIdByNumber(first)+","+findIdByNumber(second)+"");

            }

            board.getChildren().add(line);
            IntStream.range(0, 64).forEachOrdered(i -> dots[i].setFill(Color.rgb(189, 195, 199)));
            lblPlayer1Points.setText(String.valueOf(dotsAndBoxesController.getRedPoints()));
            lblPlayer1Points.setTextFill(Color.rgb(192, 57, 43));
            lblPlayer2Points.setText(String.valueOf(dotsAndBoxesController.getBluePoints()));
            lblPlayer2Points.setTextFill(Color.rgb(41, 128, 185));
            if (dotsAndBoxesController.checkGameIsOver().equalsIgnoreCase("yes")){
                awardTheWinner();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Game Is Over");
                alert.show();
            }
            lblTurn.setText(whoseTurnIsIt());
            if (whoseTurnIsIt().equals(firstPlayer)){
                lblTurn.setTextFill(Color.rgb(192, 57, 43));
            }else if (whoseTurnIsIt().equals(secondPlayer)){
                lblTurn.setTextFill(Color.rgb(41, 128, 185));
            }
        }
    }
    // todo popup winner
    private Color findColor(){
        if (dotsAndBoxesController.turn().equals(Player.BLUE)){
            return Color.rgb(41, 128, 185);
        }else
            return Color.rgb(192, 57, 43);

    }
    private String whoseTurnIsIt(){
        if (dotsAndBoxesController.turn().equals(Player.BLUE)){
            return secondPlayer;
        }else{
            return firstPlayer;
        }
    }

    private void awardTheWinner() throws IOException {
        if (dotsAndBoxesController.whoIsWinner().equalsIgnoreCase("blue")){
            playerGeneralController.giveScoreAndEditPlayerLog("DotsAndBoxes",getSecondPlayer(),getFirstPlayer(),getPoint());
            playerGeneralController.historySaver(LocalDate.now(),getSecondPlayer(),getFirstPlayer(),"DotsAndBoxes");
        }else if (dotsAndBoxesController.whoIsWinner().equalsIgnoreCase("red")){
            playerGeneralController.giveScoreAndEditPlayerLog("DotsAndBoxes",getFirstPlayer(),getSecondPlayer(),getPoint());
            playerGeneralController.historySaver(LocalDate.now(),getFirstPlayer(),getSecondPlayer(),"DotsAndBoxes");
        }
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("11"+getFirstPlayer()+" "+getSecondPlayer());
        drawCircles();
        setLabels();
        try {
            setImages();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void setImages() throws MalformedURLException {
        String path = "src"+ File.separator+"main"+File.separator+"resources"+File.separator
                +"Users"+File.separator+getFirstPlayer()+File.separator
                +getFirstPlayer()+".jpg";
        URL url = new File(path).toURI().toURL();
        imgFirst.setImage(new Image(url.toExternalForm()));

        String path1 = "src"+ File.separator+"main"+File.separator+"resources"+File.separator
                +"Users"+File.separator+getSecondPlayer()+File.separator
                +getSecondPlayer()+".jpg";
        URL url1 = new File(path1).toURI().toURL();
        imgSecond.setImage(new Image(url1.toExternalForm()));
    }

    public void forfeit(ActionEvent actionEvent) throws IOException {
        if (whoseTurnIsIt().equals(secondPlayer)){
            playerGeneralController.giveScoreAndEditPlayerLog("DotsAndBoxes",getFirstPlayer(),getSecondPlayer(),getPoint());
            playerGeneralController.historySaver(LocalDate.now(),getFirstPlayer(),getSecondPlayer(),"DotsAndBoxes");
            setSecondPlayer(" ");
            URL url = new File("src/main/resources/FXML/GameMenu.fxml").toURI().toURL();
            Parent register = FXMLLoader.load(url);
            Scene message = new Scene(register);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(message);
            window.show();
        }else if (whoseTurnIsIt().equals(firstPlayer)){
            playerGeneralController.giveScoreAndEditPlayerLog("DotsAndBoxes",getSecondPlayer(),getFirstPlayer(),getPoint());
            playerGeneralController.historySaver(LocalDate.now(),getSecondPlayer(),getFirstPlayer(),"DotsAndBoxes");
            setSecondPlayer(" ");
            URL url = new File("src/main/resources/FXML/GameMenu.fxml").toURI().toURL();
            Parent register = FXMLLoader.load(url);
            Scene message = new Scene(register);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(message);
            window.show();
        }
    }
}
