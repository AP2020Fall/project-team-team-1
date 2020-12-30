package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.awt.event.MouseEvent;

public class SignUpController {
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
    private void SignUp(ActionEvent event){

    }
    @FXML
    private void getInfo(String name,String lastName,String username,String password , String email , String phoneNum){

    }
}
