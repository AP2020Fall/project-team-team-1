package Server.Controller.RegisterController;

import Server.Controller.CompetencyController.Existence;
import Server.Controller.Exception.Plato.*;
import Server.Model.PlatoModel.Admin;

import java.io.IOException;

public class LogIn {

    private static String username = "Test";
    private static String password = "Test";
    private static boolean active = false;

    public void loginAsPlayer(String input) throws InvalidUserNameException, WrongPasswordException, BanExceptionForLogin, IOException, AlreadyBan {
        String[] inputSplit = input.split("\\s");

        if (!(Existence.checkUserNameExistence(inputSplit[0]))) {
            throw new InvalidUserNameException(inputSplit[0]);
        }

        if (!Existence.checkPlayerActivation(inputSplit[0]))
            throw new BanExceptionForLogin("This Username is Ban By Admin. ");

        if (!(Existence.checkPassword(inputSplit[0],inputSplit[1]))) {
            throw new WrongPasswordException();
        }
        LogIn.setPassword(inputSplit[1]);

    }

    public void loginAsAdmin(String input) throws InvalidUserNameException, ExistAdminException, WrongPasswordException {

        String[] inputSplit = input.split("\\s");

        if (!Existence.adminExistence()) {
            throw new ExistAdminException("There is no Admin Yet!");
        }

        if (!(Admin.getAdmins().get(0).getUserName().equals(inputSplit[0]))) {
            throw new InvalidUserNameException(inputSplit[0]);

        }

        if (!(Existence.checkPasswordForAdmin(inputSplit[1]))) {
            throw new WrongPasswordException();

        }

    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        LogIn.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        LogIn.password = password;
    }

    public static boolean isActive() {
        return active;
    }

    public static void setActive(String active) {
        if (active.equalsIgnoreCase("yes")){
            LogIn.active = true;
        }else
            LogIn.active = false;
    }
}