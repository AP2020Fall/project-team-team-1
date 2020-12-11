package Controller.RegisterController;

import Controller.CompetencyController.Existence;
import Controller.Exception.Plato.ExistAdminException;
import Controller.Exception.Plato.InvalidUserNameException;
import Controller.Exception.Plato.WrongPasswordException;
import Model.PlatoModel.Admin;

import java.io.File;

public class LogIn {
    //private static final File loginFile = new File("src\\main\\java\\Model\\Database\\LastLogin.json");

    private static String username = "Test";
    private static String password = "Test";

    public void loginAsPlayer(String input) throws InvalidUserNameException, WrongPasswordException {
        String[] inputSplit = input.split("\\s");

        if (!(Existence.checkUserNameExistence(inputSplit[0]))) {
            throw new InvalidUserNameException(inputSplit[0]);
            //System.out.println("INVALID USERNAME");
            //return false;
        }

        if (!(Existence.checkPassword(inputSplit[0],inputSplit[1]))) {
            //System.out.println("WRONG PASSWORD");
            throw new WrongPasswordException();
            //return false;
        }

        //System.out.println("LOGIN SUCCESSFULLY");
        //return true;
    }

    public void loginAsAdmin(String input) throws InvalidUserNameException, ExistAdminException, WrongPasswordException {

        String[] inputSplit = input.split("\\s");

        if (!Existence.adminExistence()) {
            throw new ExistAdminException("There is no Admin Yet!");
//            System.out.println("There is no Admin Yet!");
//            return false;
        }

        if (!(Admin.getAdmins().get(0).getUserName().equals(inputSplit[0]))) {
            throw new InvalidUserNameException(inputSplit[0]);
//            System.out.println("INVALID USERNAME");
//            return false;
        }

        if (!(Existence.checkPasswordForAdmin(inputSplit[1]))) {
            throw new WrongPasswordException();
//            System.out.println("WRONG PASSWORD");
//            return false;
        }
//        System.out.println("LOGIN SUCCESSFULLY");
//        return true;
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
}