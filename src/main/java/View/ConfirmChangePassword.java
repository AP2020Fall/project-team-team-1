package View;

import Controller.CompetencyController.Existence;
import Controller.Exception.Plato.InvalidPasswordException;
import Controller.Exception.Plato.SamePasswordException;
import Controller.Exception.Plato.StrongerPasswordException;
import Controller.Exception.Plato.WrongPasswordException;
import Controller.PlayerController.PlayerGeneralController;
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

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ConfirmChangePassword {


    protected static PlayerGeneralController playerGeneralController = new PlayerGeneralController();


    private  String username = LoginController.getUsername();

    protected static String confirm = "false";

    public static String getConfirm() {
        return confirm;
    }

    public static void setConfirm(String confirm) {
        ConfirmChangePassword.confirm = confirm;
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
    PasswordField txtNewPassword;


    @FXML
    private void close(ActionEvent event){
        Stage stage = (Stage)btnBack.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void setBtnConfirm(ActionEvent event) throws IOException, StrongerPasswordException, InvalidPasswordException, WrongPasswordException, SamePasswordException {

        String password = txtPassword.getText();
        String newPassword = txtNewPassword.getText();
        setConfirm(Existence.checkPasswordForView(getUsername(),password));
        if (getConfirm().equalsIgnoreCase("false")){
            showError();
        }
        else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(getUsername()).append(" ").append(password).append(" ").append(newPassword);
            playerGeneralController.editPassword(String.valueOf(stringBuilder));

            setConfirm("false");
            close(event);
            //todo Check this
        }

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
