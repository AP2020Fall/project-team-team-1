package Controller.RegisterController;

import Controller.Exception.InvalidUserNameException;

import static org.junit.jupiter.api.Assertions.*;
import java.lang.Exception.*;
import Controller.RegisterController.LogIn;

class LogInTest2 {

    @org.junit.jupiter.api.Test
    void loginAsPlayer() {
        LogIn logIn = new LogIn();
        assertThrows(InvalidUserNameException.class, () -> logIn.loginAsPlayer("yamsiin 007Password"));
    }

    @org.junit.jupiter.api.Test
    void loginAsAdmin() {
    }
}