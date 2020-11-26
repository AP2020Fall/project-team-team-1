package Controller.RegisterController;

import Controller.CompetencyController.Existence;
import Model.PlatoModel.Admin;

public class LogIn {


    public boolean loginAsPlayer(String input) {
        String[] inputSplit = input.split("\\s");

        if (!(Existence.checkUserNameExistence(inputSplit[0]))) {
            System.out.println("INVALID USERNAME");
            return false;
        }

        if (!(Existence.checkPassword(inputSplit[0], inputSplit[1]))) {
            System.out.println("WRONG PASSWORD");
            return false;
        }
        System.out.println("LOGIN SUCCESSFULLY");
        return true;
    }

    public boolean loginAsAdmin(String input) {

        String[] inputSplit = input.split("\\s");

        if (!Existence.adminExistence()) {
            System.out.println("There is no Admin Yet!");
            return false;
        }

        if (!(Admin.getAdmins().get(0).getUserName().equals(inputSplit[0]))) {
            System.out.println("INVALID USERNAME");
            return false;
        }

        if (!(Existence.checkPassword(inputSplit[0], inputSplit[1]))) {
            System.out.println("WRONG PASSWORD");
            return false;
        }
        System.out.println("LOGIN SUCCESSFULLY");
        return true;
    }
}