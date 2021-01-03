package View;

import Controller.AdminController.AdminGeneralController;
import Controller.Exception.Plato.ExistFavoriteException;
import Controller.Exception.Plato.ExistPlayerException;
import Controller.Exception.Plato.ExistPlayerLogException;
import Controller.Exception.Plato.InvalidGameNameException;
import Controller.PlayerController.PlayerGeneralController;
import javafx.animation.Animation;
import javafx.animation.Transition;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class DotsAndBoxesMainMenuController implements Initializable {
    protected static AdminGeneralController adminGeneralController = new AdminGeneralController();
    protected static PlayerGeneralController playerGeneralController = new PlayerGeneralController();
    String firsGame = adminGeneralController.firstGameNameGetter();
    String secondGame = adminGeneralController.secondGameNameGetter();

    @FXML
    Button btnExit;
    @FXML
    Button btnFav;
    @FXML
    ImageView btnfavImage;
    @FXML
    Button backToGameMenu ;
    @FXML
    Label labelDots ;


    /********************Loaders********************/

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
            playerGeneralController.addGameToFavoritesGames(LoginController.getUsername(),adminGeneralController.secondGameNameGetter());
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
    @FXML
    private void goGameMenu(ActionEvent actionEvent) throws IOException {

        URL url = new File("src/main/resources/FXML/GameMenu.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }



    /********************Go To Menus********************/
    @FXML
    private void appExit(ActionEvent event) {
        System.exit(1);
    }

    @FXML
    private void goDetails(ActionEvent actionEvent) throws IOException {

        URL url = new File("src/main/resources/FXML/DotsAndBoxesDetails.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();


    }
    @FXML
    private void goToHistory(ActionEvent actionEvent) throws IOException {

        URL url = new File("src/main/resources/FXML/DotsAndBoxesHistory.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();

    }

    @FXML
    private void goToScoreBoard(ActionEvent actionEvent) throws IOException {

        URL url = new File("src/main/resources/FXML/DotsAndBoxesScoreBoard.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadFavStatus();
            labelDots.setText("WELCOME TO ".concat(secondGame));
        } catch (ExistFavoriteException e) {
            e.printStackTrace();
        }
    }
}
