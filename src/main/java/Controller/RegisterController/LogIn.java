package Controller.RegisterController;

import Controller.CompetencyController.Existence;

public class LogIn {

    //String[] splitInput = input.split("\\s");

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
}



