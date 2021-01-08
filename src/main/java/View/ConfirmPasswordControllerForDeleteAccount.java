package View;

import Controller.CompetencyController.Existence;
import Controller.Exception.Plato.ExistPlayerException;
import Controller.PlayerController.PlayerGeneralController;
import Model.PlatoModel.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ConfirmPasswordControllerForDeleteAccount {
    protected static PlayerGeneralController playerGeneralController = new PlayerGeneralController();


    private  String username = LoginController.getUsername();

    protected static String confirm = "false";

    public static String getConfirm() {
        return confirm;
    }

    public static void setConfirm(String confirm) {
        ConfirmPasswordControllerForDeleteAccount.confirm = confirm;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @FXML
    Button btnBack;
    @FXML
    Button btnConfirm;
    @FXML
    PasswordField txtPassword;


    @FXML
    private void close(ActionEvent event){
        Stage stage = (Stage)btnBack.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void setBtnConfirm(ActionEvent event) throws IOException, ExistPlayerException {
        String password = txtPassword.getText();
        setConfirm(Existence.checkPasswordForView(getUsername(),password));
        if (getConfirm().equalsIgnoreCase("false")){
            showError();
        }
        else {
            String[] user = playerGeneralController.showBasicInformation(getUsername()).split("\\$");
//            File file = new File(user[6]);
            deleteDirectory(new File("src"+File.separator+"main"+File.separator+"resources"+File.separator+"Users"+File.separator+getUsername()));
            playerGeneralController.deleteUser(getUsername());
            setConfirm("false");
            System.exit(1);
            //todo Check this
        }

    }
    @FXML
    private void deleteDirectory(File fileForDelete) throws IOException {
        FileUtils.deleteDirectory(fileForDelete);
    }

    private void showError() throws IOException {
        URL url = new File("src/main/resources/FXML/LoginError.fxml").toURI().toURL();

        AnchorPane root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }
}
