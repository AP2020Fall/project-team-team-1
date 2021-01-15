package View;

import Controller.DotsAndBoxesController.DotsAndBoxesController;
import Controller.Exception.DotsAndBoxes.ExistLineException;
import Controller.PlayerController.PlayerGeneralController;
import Model.DotsAndBoxesModel.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class DotsAndBoxesGame implements Initializable {
    private static final File file = new File("src\\main\\resources\\Sound\\chase.mp3");
    protected static Media media = new Media(file.toURI().toString());
    protected static MediaPlayer mediaPlayer = new MediaPlayer(media);
//    private static final File file2 = new File("src\\main\\resources\\Sound\\hesam.mp3");
//    protected static Media media2 = new Media(file2.toURI().toString());
//    protected static MediaPlayer mediaPlayer2 = new MediaPlayer(media2);
    protected static DotsAndBoxesController dotsAndBoxesController;
    protected static PlayerGeneralController playerGeneralController = new PlayerGeneralController();
    @FXML
    Pane forZoom;
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
    public Label lblPlayer1Points = new Label();
    @FXML
    public Label lblPlayer2Points = new Label();
    @FXML
    public Label lblTurn = new Label();
    private static String firstPlayer = "Amirzgh1";
    private static String secondPlayer = "player";
    private static String winner = " ";
    public Pane WinnerPane;
    public Label lblWinner;
    public HBox hbox;
    private Circle[] dots = new Circle[64];
    private Label[] labels = new Label[49];
    private double valueX =165;
    private double valueY =165;
    private double valueLabelX = 178;
    private double valueLabelY = 172;
    private static long point = 30;
    private static ArrayList<Label> availableLabels = new ArrayList<>();
    private static String lastPlayed;
    private static ArrayList<String> numberOfFillBoxes = new ArrayList<>();

    public static String getLastPlayed() {
        return lastPlayed;
    }

    public static void setLastPlayed(String lastPlayed) {
        DotsAndBoxesGame.lastPlayed = lastPlayed;
    }

    public static String getWinner() {
        return winner;
    }

    public static void setWinner(String winner) {
        DotsAndBoxesGame.winner = winner;
    }

    public long getPoint() {
        return point;
    }

    public void setPoint(long point) {
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
    private void createLabels(){
        IntStream.range(0,49).forEach(i->{
            labels[i]=new Label();
            labels[i].setId(String.valueOf(i));
            labels[i].setPrefWidth(20);
            labels[i].setPrefHeight(20);
            labels[i].setLayoutX(valueLabelX);
            labels[i].setLayoutY(valueLabelY);
//            labels[i].setText(labels[i].getId());
//            labels[i].setTextFill(Color.WHITE);
            if ((i+1)%7==0 && i+1 !=49){
                valueLabelY+=37;
                valueLabelX=178;
            }else {
                valueLabelX+=37;
            }
            availableLabels.add(labels[i]);
            board.getChildren().add(labels[i]);
        });
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

//    @FXML
//    private void updateScoreBoard(){
//        Label redPoint = new Label(String.valueOf(dotsAndBoxesController.getRedPoints()));
//        redPoint.setTextFill(Color.rgb(192, 57, 43));
//        Text text = new Text("-");
//        text.setFill(Color.WHITE);
//        Label bluePoint = new Label(String.valueOf(dotsAndBoxesController.getBluePoints()));
//        bluePoint.setTextFill(Color.rgb(41, 128, 185));
//        hbox.getChildren().addAll(redPoint,text,bluePoint);
//    }

    @FXML
    private void setOnMouseClicked(MouseEvent mouseEvent){
        Circle circle = (Circle) mouseEvent.getTarget();
        hover(circle.getId());
        try {
            click(circle.getId());
        } catch (IOException ignored) {
            return;
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
    @FXML
    private void drawLine(int first , int second) {
        File file2 = new File("src\\main\\resources\\Sound\\hesam.mp3");
        Media media2 = new Media(file2.toURI().toString());
        MediaPlayer mediaPlayer2 = new MediaPlayer(media2);
        mediaPlayer2.setVolume(0.5);

        if (dotsAndBoxesController.checkGameIsOver().equalsIgnoreCase("yes")){
            try {
                awardTheWinner();
            } catch (IOException e) {
                return;
            }
            lblWinner.setText(getWinner());
            WinnerPane.setVisible(true);
            WinnerPane.toFront();
        }
        Line line = new Line();
        line.setStrokeWidth(2);
        line.setId(whoseTurnIsIt());
        line.setStroke(findColor());
        if (dots[second].getFill().equals(Color.rgb(39, 55, 70))){
            if (first-second==1){


                try {
                    dotsAndBoxesController.doTheCommands(""+findIdByNumber(second)+","+findIdByNumber(first)+"");
                    line.setStartX(dots[first].getCenterX()-dots[first].getRadius());
                    line.setEndX(dots[second].getCenterX()+dots[second].getRadius());
                    line.setStartY(dots[first].getCenterY());
                    line.setEndY(dots[second].getCenterY());

//                    System.out.println(""+findIdByNumber(second)+","+findIdByNumber(first)+"");
                } catch (ExistLineException e) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }

            }
            if (first-second==-1){

                try {
                    dotsAndBoxesController.doTheCommands(""+findIdByNumber(first)+","+findIdByNumber(second)+"");
                    line.setStartX(dots[first].getCenterX()+dots[first].getRadius());
                    line.setEndX(dots[second].getCenterX()-dots[second].getRadius());
                    line.setStartY(dots[first].getCenterY());
                    line.setEndY(dots[second].getCenterY());

//                    System.out.println(""+findIdByNumber(first)+","+findIdByNumber(second)+"");
                } catch (ExistLineException e) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }

            }
            if (first-second==8){

                try {
                    dotsAndBoxesController.doTheCommands(""+findIdByNumber(second)+","+findIdByNumber(first)+"");
                    line.setStartX(dots[first].getCenterX());
                    line.setEndX(dots[second].getCenterX());
                    line.setStartY(dots[first].getCenterY() - dots[first].getRadius());
                    line.setEndY(dots[second].getCenterY() + dots[second].getRadius());

//                    System.out.println(""+findIdByNumber(second)+","+findIdByNumber(first)+"");
                } catch (ExistLineException e) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }

            }
            if (first-second==-8){

                try {
                    dotsAndBoxesController.doTheCommands(""+findIdByNumber(first)+","+findIdByNumber(second)+"");
                    line.setStartX(dots[first].getCenterX());
                    line.setEndX(dots[second].getCenterX());
                    line.setStartY(dots[first].getCenterY() + dots[first].getRadius());
                    line.setEndY(dots[second].getCenterY() - dots[second].getRadius());

//                    System.out.println(""+findIdByNumber(first)+","+findIdByNumber(second)+"");
                } catch (ExistLineException e) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }
            }

            board.getChildren().add(line);
            IntStream.range(0, 64).forEachOrdered(i -> dots[i].setFill(Color.rgb(189, 195, 199)));
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
//        showCompletedBoxes(dotsAndBoxesController.completedBoxes());
        findOwner1(dotsAndBoxesController.isBoxCompleted1());
        mediaPlayer2.play();
    }
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
            setWinner(getSecondPlayer());
            playerGeneralController.giveScoreAndEditPlayerLog("DotsAndBoxes",getSecondPlayer(),getFirstPlayer(),getPoint());
            playerGeneralController.historySaver(LocalDate.now(),getSecondPlayer(),getFirstPlayer(),"DotsAndBoxes");
        }else if (dotsAndBoxesController.whoIsWinner().equalsIgnoreCase("red")){
            setWinner(getFirstPlayer());
            playerGeneralController.giveScoreAndEditPlayerLog("DotsAndBoxes",getFirstPlayer(),getSecondPlayer(),getPoint());
            playerGeneralController.historySaver(LocalDate.now(),getFirstPlayer(),getSecondPlayer(),"DotsAndBoxes");
        }
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dotsAndBoxesController = new DotsAndBoxesController();
        mediaPlayer.setVolume(0.5);
        mediaPlayer.play();
        hbox.setSpacing(20);
        hbox.alignmentProperty().set(Pos.CENTER);
        forZoom.setVisible(false);
        addMouseScrolling(imgFirst);
        addMouseScrolling2(imgSecond);
        hbox.getChildren().addAll(lblPlayer1Points,lblTurn,lblPlayer2Points);
        board.toFront();
        WinnerPane.setVisible(false);
//        System.out.println("11"+getFirstPlayer()+" "+getSecondPlayer());
        drawCircles();
        createLabels();
        setLabels();
        try {
            setImages();
        } catch (MalformedURLException e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    private void showCompletedBoxes(ArrayList<String> boxes){
        for (String box : boxes) {
            findOwner(box);
//            System.out.println(box);
        }
    }

    @FXML
    private void findOwner(String id){
        if (dotsAndBoxesController.isThisBoxCompleted(id).equalsIgnoreCase("blue")){
            setThisLabel(availableLabels.get(Integer.parseInt(id)),"blue");
//            System.out.println(id);
        }else if (dotsAndBoxesController.isThisBoxCompleted(id).equalsIgnoreCase("red")){
            setThisLabel(availableLabels.get(Integer.parseInt(id)),"red");
//            System.out.println(id);
        }
    }
    @FXML
    private void findOwner1(String id){
        String[] process = id.split("\\$");
        for (String s : process) {
            if (!numberOfFillBoxes.contains(s)){
                numberOfFillBoxes.add(s);
                int idOfLabel = Integer.parseInt(s);
                if (whoseTurnIsIt().equals(firstPlayer)){
                    setThisLabel(availableLabels.get(idOfLabel),"red");
                }else
                    setThisLabel(availableLabels.get(idOfLabel),"blue");

            }
        }
    }
    private Label findLabelById(String id){
        for (int i = 0; i < labels.length; i++) {
            if (labels[Integer.parseInt(id)].getId().equals(id)){
//                System.out.println("hoy");
                return labels[Integer.parseInt(id)];
            }
        }
        return null;
    }

    @FXML
    private void setThisLabel(Label label,String color){
        if (color.equals("blue")){
            label.setText(getSecondPlayer().substring(0,1));
            label.setTextFill(Color.rgb(41, 128, 185));
        }
        if (color.equals("red")){
            label.setText(getFirstPlayer().substring(0,1));
            label.setTextFill(Color.rgb(192, 57, 43));
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
        playMouseSound();
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
        mediaPlayer.stop();
    }

    public void backToPlato(ActionEvent actionEvent) throws IOException {
        mediaPlayer.stop();
        URL url = new File("src/main/resources/FXML/PlayerMainMenu.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    public void ViewProfilePlayer1(MouseEvent mouseEvent) throws IOException {
        FriendProfileForSentRequestController.setUsernameOfFriendForSentRequest(getFirstPlayer());
        URL url = new File("src/main/resources/FXML/FriendProfileForSentRequest.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene message = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(message);
        stage.show();
    }

    public void ViewProfilePlayer2(MouseEvent mouseEvent) throws IOException {
        FriendProfileForSentRequestController.setUsernameOfFriendForSentRequest(getSecondPlayer());
        URL url = new File("src/main/resources/FXML/FriendProfileForSentRequest.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene message = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(message);
        stage.show();
    }
    public void playMouseSound(){
        File file = new File("src\\main\\resources\\Sound\\Click.mp3");
        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayerMouse = new MediaPlayer(media);
        mediaPlayerMouse.play();
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
                node.setLayoutX(300);
                node.setLayoutY(300);

            }
            if (node.getScaleX() < 1.5){
                forZoom.setVisible(false);
                node.toBack();

                node.setLayoutX(14);
                node.setLayoutY(160);
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
                node.setLayoutX(300);
                node.setLayoutY(300);

            }
            if (node.getScaleX() < 1.5){
                forZoom.setVisible(false);
                node.toBack();

                node.setLayoutX(464);
                node.setLayoutY(160);
            }
        });
    }
}
