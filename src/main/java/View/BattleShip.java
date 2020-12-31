package View;

import Controller.AdminController.AdminGeneralController;
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
    Text detail;
    @FXML
    Button btnExit;
    @FXML
    PieChart pieChart;


    @FXML
    private void setDetail() {
        detail.setText(playerGeneralController.battleDetails());
        topic.setText(adminGeneralController.firstGameNameGetter() + "'s Details");
    }

    @FXML
    private void setPieChart() throws InvalidGameNameException {
        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(
                new PieChart.Data("Wins",Integer.parseInt(playerGeneralController.showNumberOFWins(LoginController.getUsername(),adminGeneralController.firstGameNameGetter()))),
                new PieChart.Data("Losses", Integer.parseInt(playerGeneralController.numberOfLossesInThisGame(LoginController.getUsername(),adminGeneralController.firstGameNameGetter()))));
        pieChartData.forEach(data ->
                data.nameProperty().bind(
                        Bindings.concat(
                                data.getName(), " ", data.pieValueProperty(), " Times"
                        )
                )
        );
        pieChart.setLegendVisible(false);
        pieChart.setData(pieChartData);
        pieChart.setStartAngle(90);
    }

    /************************Go To Menus*************************/
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
        try {
            setPieChart();
        } catch (InvalidGameNameException e) {
            e.printStackTrace();
        }
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
