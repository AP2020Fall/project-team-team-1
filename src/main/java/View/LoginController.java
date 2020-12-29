package View;

import Controller.AdminController.AdminGeneralController;
import Controller.Exception.Plato.BanExceptionForLogin;
import Controller.Exception.Plato.ExistAdminException;
import Controller.Exception.Plato.InvalidUserNameException;
import Controller.Exception.Plato.WrongPasswordException;
import Controller.RegisterController.LogIn;
import Controller.RegisterController.SignUp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;



public class LoginController {
    protected static SignUp processSignupController = new SignUp();
    protected static LogIn processLoginController = new LogIn();
    protected static AdminGeneralController adminGeneralController = new AdminGeneralController();
    @FXML
    Button btnExit;
    @FXML
    TextField txtUsername;
    @FXML
    PasswordField txtPassword;
    @FXML
    Button btnSubmit;
    @FXML
    Button btnRegister;

    @FXML
    private void appExit(ActionEvent event){
        System.exit(1);
    }
    @FXML
    private void goToRegisterMenu(ActionEvent event) throws IOException {

        URL url = new File("src/main/resources/FXML/SignUpMenu.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    @FXML
    private void login(ActionEvent event) throws IOException {
        try {
            if (adminGeneralController.getAdminUserName().equals(txtUsername.getText())){
                processLoginController.loginAsAdmin(getInfo(txtUsername.getText(),txtPassword.getText()));
                System.out.println("khube");
            }else
            processLoginController.loginAsPlayer(getInfo(txtUsername.getText(),txtPassword.getText()));
            System.out.println("khube");
        } catch (InvalidUserNameException | WrongPasswordException | BanExceptionForLogin | ExistAdminException e) {
            showError();
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
    private String getInfo(String txtUsername,String txtPassword){
        return txtUsername+" "+txtPassword;
    }
}
