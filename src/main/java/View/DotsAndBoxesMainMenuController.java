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

    @FXML
    Button btnFav;

    @FXML
    ImageView btnfavImage;

    @FXML
    Button btnExit;

    @FXML
    Button backToGameMenu ;

    @FXML
    private void setBtnFav(ActionEvent actionEvent) throws ExistFavoriteException, IOException, InvalidGameNameException {

        String[] favoriteGames = playerGeneralController.showFavoritesGames(LoginController.getUsername()).split("\\$");

        String gameNameForFavoriteGames = "nothing";

        for (String gameName : favoriteGames) {
            if (gameName.startsWith("D")||gameName.startsWith("d")){

                File file = new File("src\\main\\resources\\Icons\\addfav.png");
                Image image = new Image(file.toURI().toString());
                btnfavImage.setImage(image);
                gameNameForFavoriteGames = gameName;
            }
        }

        if (gameNameForFavoriteGames.equalsIgnoreCase("nothing")){

            playerGeneralController.addGameToFavoritesGames(LoginController.getUsername(),adminGeneralController.firstGameNameGetter());
            File file = new File("src\\main\\resources\\Icons\\addfav.png");
            Image image = new Image(file.toURI().toString());
            btnfavImage.setImage(image);
            return;
        }

        else {

            playerGeneralController.RemoveFavoritesGames(LoginController.getUsername(),gameNameForFavoriteGames);
            File file = new File("src\\main\\resources\\Icons\\removefav.png");
            Image image = new Image(file.toURI().toString());
            btnfavImage.setImage(image);
        }
    }

    @FXML
    private void loadFavoriteStatus() throws ExistFavoriteException {

        String[] favoriteGames = playerGeneralController.showFavoritesGames(LoginController.getUsername()).split("\\$");

        for (String gameName : favoriteGames) {

            if (gameName.startsWith("D") || gameName.startsWith("d")){

                File file = new File("src\\main\\resources\\Icons\\addfav.png");
                Image image = new Image(file.toURI().toString());
                btnfavImage.setImage(image);
            }
        }
    }

    @FXML
    private void goToGameMenu(ActionEvent actionEvent) throws IOException {

        URL url = new File("src/main/resources/FXML/GameMenu.fxml").toURI().toURL();

        Parent root = FXMLLoader.load(url);
        Scene message = new Scene(root);

        Stage newWindow = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        newWindow.setScene(message);
        newWindow.show();
    }

    @FXML
    private void goToDetails(ActionEvent actionEvent) throws IOException {

        URL url = new File("src/main/resources/FXML/BattleShipDetails.fxml").toURI().toURL();

        Parent root = FXMLLoader.load(url);
        Scene message = new Scene(root);

        Stage newWindow = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        newWindow.setScene(message);
        newWindow.show();
    }

    @FXML
    private void goToHistory(ActionEvent actionEvent) throws IOException {

        URL url = new File("src/main/resources/FXML/BattleShipHistory.fxml").toURI().toURL();

        Parent root = FXMLLoader.load(url);
        Scene message = new Scene(root);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    @FXML
    private void goToScoreBoard(ActionEvent actionEvent) throws IOException {

        URL url = new File("src/main/resources/FXML/BattleShipScoreBoard.fxml").toURI().toURL();

        Parent root = FXMLLoader.load(url);
        Scene message = new Scene(root);

        Stage newWindow = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        newWindow.setScene(message);
        newWindow.show();
    }

    //hmmmm didn't really get this
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadFavoriteStatus();
        } catch (ExistFavoriteException e) {
            e.printStackTrace();
        }
    }
}
