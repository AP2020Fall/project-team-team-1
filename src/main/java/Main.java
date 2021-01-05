import Model.DataBase.DataBase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;


public class Main extends Application {
    public static void main(String[] args) throws IOException {
        try {
            DataBase.loadAllDataFromJsonFiles();
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
//        Menu.setScanner(new Scanner(System.in));
//        Menu currentMenu = new MainMenu();
//        currentMenu.run();


//        Timer timer = new Timer();
//        timer.start();
        //  hesam khare
        //  hesam khare mahze


        launch(args);

    }

    @Override
    public void start(Stage primaryStage) {
        try {

            URL url = new File("src/main/resources/FXML/AdminProfile.fxml").toURI().toURL();

            AnchorPane root = FXMLLoader.load(url);
            Scene scene = new Scene(root);
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch(Exception e)    {
            e.printStackTrace();
        }
    }

}
