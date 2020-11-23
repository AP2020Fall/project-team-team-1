package Controller.PlayerController;

import Controller.CompetencyController.Existence;
import Controller.CompetencyController.Validation;
import Model.PlatoModel.Admin;

public class Edit {

    public static void editField(String field, String input) {
        if (field.trim().equalsIgnoreCase("name")) {
            editFirstName(input);
        } else if (field.trim().equalsIgnoreCase("lastname")) {
            editLastName(input);
        } else if (field.trim().equalsIgnoreCase("email")) {
            editEmail(input);
        } else if (field.trim().equalsIgnoreCase("phonenumber")) {
            editPhoneNumber(input);
        } else if (field.trim().equalsIgnoreCase("username")) {
            editUsername(input);
        } else
            System.out.println("Can not edit this field !");
    }

    protected static void editUsername(String input) {
        boolean pass = false;
        pass = Validation.usernameIsValid(input);
        if (!pass)
            System.out.println("this format is invalid !");

        if (pass) {
            pass = Existence.checkUserNameExistence(input);
            if (pass){
                System.out.println("Username is existence!");
                pass = false;
            }else {
                pass = true;
            }

            if (pass)
                Admin.getAdmins().get(0).setUserName(input);
        }
    }

    public static void editFirstName(String input){
        boolean pass = false;
        pass = Validation.nameOrLastNameIsValid(input);
        if (!pass)
            System.out.println("This format is invalid !");

        if (pass)
            Admin.getAdmins().get(0).setName(input);
    }

    public static void editLastName(String input){
        boolean pass = false;
        pass = Validation.nameOrLastNameIsValid(input);
        if (!pass)
            System.out.println("This format is invalid !");

        if (pass)
            Admin.getAdmins().get(0).setLastName(input);
    }

    public static void editPassword(String oldPassword , String newPassword){
        boolean pass = false;
        pass = Existence.checkPassword(Admin.getAdmins().get(0).getUserName(), oldPassword);
        if (!pass)
            System.out.println("This format is invalid !");

        if (pass) {
            if (oldPassword.equals(newPassword)) {
                System.out.println("Old password and new password are same! please enter new password");
                pass = false;
            }

            if (pass) {
                pass = Validation.passwordIsValid(newPassword);
                if (!pass)
                    System.out.println("Make Stranger Password (read Hits for make good Password)");

                if (pass)
                    Admin.getAdmins().get(0).setPassword(newPassword);
            }

        }
    }

    public static void editEmail(String input){
        boolean pass = false;
        pass = Validation.emailIsValid(input);
        if (!pass)
            System.out.println("This format is invalid !");

        if (pass) {
            pass = Existence.checkEmailExistence(input);
            if (pass){
                System.out.println("Email is Existence!");
                pass = false;
            }else {
                pass = true;
            }

            if (pass)
                Admin.getAdmins().get(0).setEmail(input);
        }
    }

    public static void editPhoneNumber(String input){
        boolean pass = false;
        pass = Validation.phoneNumberIsValid(input);
        if (!pass)
            System.out.println("This format is invalid !");

        if (pass)
            Admin.getAdmins().get(0).setPhoneNum(input);
    }
}
