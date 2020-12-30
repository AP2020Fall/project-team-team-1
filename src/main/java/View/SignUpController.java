package View;

import Controller.Exception.Plato.*;
import Controller.RegisterController.SignUp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SignUpController {
    protected SignUp processSignUp = new SignUp();
    @FXML
    Button btnExit;
    @FXML
    TextField txtName;
    @FXML
    TextField txtLastname;
    @FXML
    TextField txtUsername;
    @FXML
    TextField txtEmail;
    @FXML
    TextField txtPassword;
    @FXML
    TextField txtPhoneNum;
    @FXML
    Button btnSubmit;
    @FXML
    Button btnBack;

    @FXML
    private void appExit(ActionEvent event){
        System.exit(1);
    }
    @FXML
    private void signUp(ActionEvent event) throws EmptyExceptionForLastName, ExistEmailException, EmptyExceptionForUserName, EmptyExceptionForName, EmptyExceptionForEmail, ExistUserNameException {
        processSignUp.addPlayer(getInfo(txtName.getText(),txtLastname.getText(),txtUsername.getText(),txtEmail.getText(),txtPassword.getText(),txtPhoneNum.getText()));

    }
    @FXML
    private String getInfo(String name,String lastName,String username,String password , String email , String phoneNum){
        return name+" "+lastName+" "+username+" "+email+" "+password+" "+phoneNum;
    }
}
