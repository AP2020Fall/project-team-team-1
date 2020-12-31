package View;

import Controller.AdminController.AdminGeneralController;
import Controller.Exception.Plato.ExistFavoriteException;
import Controller.Exception.Plato.ExistPlayerException;
import Controller.Exception.Plato.ExistPlayerLogException;
import Controller.Exception.Plato.InvalidGameNameException;
import Controller.PlayerController.PlayerGeneralController;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class BattleShip {
    protected static AdminGeneralController adminGeneralController = new AdminGeneralController();
    protected static PlayerGeneralController playerGeneralController = new PlayerGeneralController();

    @FXML
    Label topic;
    @FXML
    Label numberofplayed;
    @FXML
    Label point;
    @FXML
    Text detail;
    @FXML
    Button btnExit;
    @FXML
    Button btnFav;
    @FXML
    PieChart pieChart;
    @FXML
    ImageView imageViewOfLevel;
    @FXML
    ImageView btnfavImage;

//    @FXML
//    ListView<String> listView;

    /********************Loaders********************/
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
        private void loadFavStatus() throws ExistFavoriteException {
        String[] fav = playerGeneralController.showFavoritesGames(LoginController.getUsername()).split("\\$");

        for (String gameName : fav) {
            if (gameName.startsWith("B")||gameName.startsWith("b")){
                File file = new File("src\\main\\resources\\Icons\\addfav.png");
                Image image = new Image(file.toURI().toString());
                btnfavImage.setImage(image);

            }
        }

    }

    /********************Methods********************/

    @FXML
    private void setDetail() {
        detail.setText(playerGeneralController.battleDetails());
        topic.setText(adminGeneralController.firstGameNameGetter() + "'s Details");
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
    private void setBtnFav(ActionEvent actionEvent) throws ExistFavoriteException, IOException, InvalidGameNameException {
        String[] fav = playerGeneralController.showFavoritesGames(LoginController.getUsername()).split("\\$");

        String gameNameForFav = "nothing";

        for (String gameName : fav) {
            if (gameName.startsWith("B")||gameName.startsWith("b")){

                File file = new File("src\\main\\resources\\Icons\\addfav.png");
                Image image = new Image(file.toURI().toString());
                btnfavImage.setImage(image);
                gameNameForFav = gameName;

            }
        }
        if (gameNameForFav.equalsIgnoreCase("nothing")){
            playerGeneralController.addGameToFavoritesGames(LoginController.getUsername(),adminGeneralController.firstGameNameGetter());
            File file = new File("src\\main\\resources\\Icons\\addfav.png");
            Image image = new Image(file.toURI().toString());
            btnfavImage.setImage(image);
            return;
        }else {
            playerGeneralController.RemoveFavoritesGames(LoginController.getUsername(),gameNameForFav);
            File file = new File("src\\main\\resources\\Icons\\removefav.png");
            Image image = new Image(file.toURI().toString());
            btnfavImage.setImage(image);
        }



    }

//    @FXML
//    private void setListView() throws ExistPlayerLogException, ExistPlayerException {
//        ObservableList<String> list = FXCollections.observableArrayList();
//
//        listView.setItems(list);
//        String[] showEvent = playerGeneralController.showHistory(LoginController.getUsername()).split("\\$");
//        for (String out : showEvent) {
//            //String build = "⁕"+out;
//            listView.getItems().add("⁕"+out);
//            //System.out.println(out);
//        }
//}

    /********************Go To Menus********************/
    @FXML
    private void appExit(ActionEvent event) {
        System.exit(1);
    }

    @FXML
    private void goDetails(ActionEvent actionEvent) throws IOException {

        URL url = new File("src/main/resources/FXML/BattleShipDetails.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();

//        URL url = new File("src/main/resources/FXML/BattleShipDetails.fxml").toURI().toURL();
//        AnchorPane root = FXMLLoader.load(url);
//        Scene scene = new Scene(root);
//        Stage stage = new Stage();
//        stage.initStyle(StageStyle.UNDECORATED);
//        stage.setScene(scene);
//        stage.show();

    }
    @FXML
    private void goToHistory(ActionEvent actionEvent) throws IOException {

        URL url = new File("src/main/resources/FXML/BattleShipHistory.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();

//        URL url = new File("src/main/resources/FXML/BattleShipDetails.fxml").toURI().toURL();
//        AnchorPane root = FXMLLoader.load(url);
//        Scene scene = new Scene(root);
//        Stage stage = new Stage();
//        stage.initStyle(StageStyle.UNDECORATED);
//        stage.setScene(scene);
//        stage.show();
    }

    @FXML
    private void goHistory(ActionEvent actionEvent) throws IOException {

        URL url = new File("src/main/resources/FXML/BattleShipDetails.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();

//        URL url = new File("src/main/resources/FXML/BattleShipDetails.fxml").toURI().toURL();
//        AnchorPane root = FXMLLoader.load(url);
//        Scene scene = new Scene(root);
//        Stage stage = new Stage();
//        stage.initStyle(StageStyle.UNDECORATED);
//        stage.setScene(scene);
//        stage.show();
        setDetail();
    }

    @FXML
    private void goBattleShipMainMenu(ActionEvent actionEvent) throws IOException {

        URL url = new File("src/main/resources/FXML/BattleShipMainMenu.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
}
