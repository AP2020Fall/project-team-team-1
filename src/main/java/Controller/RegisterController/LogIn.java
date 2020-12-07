package Controller.RegisterController;

import Controller.CompetencyController.Existence;
import Controller.Exception.ExistAdminException;
import Controller.Exception.InvalidUserNameException;
import Controller.Exception.WrongPasswordException;
import Model.PlatoModel.Admin;

public class LogIn {


    public void loginAsPlayer(String input) throws InvalidUserNameException, WrongPasswordException {
        String[] inputSplit = input.split("\\s");

        if (!(Existence.checkUserNameExistence(inputSplit[0]))) {
            throw new InvalidUserNameException(inputSplit[0]);
            //System.out.println("INVALID USERNAME");
            //return false;
        }

        if (!(Existence.checkPasswordForAdmin(inputSplit[1]))) {
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

        if (!(Existence.checkPassword(inputSplit[0], inputSplit[1]))) {
            throw new WrongPasswordException();
//            System.out.println("WRONG PASSWORD");
//            return false;
        }
//        System.out.println("LOGIN SUCCESSFULLY");
//        return true;
    }
}