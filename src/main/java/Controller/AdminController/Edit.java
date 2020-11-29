package Controller.AdminController;

import Controller.CompetencyController.Existence;
import Controller.CompetencyController.Validation;
import Controller.Exception.*;
import Model.PlatoModel.Admin;

public class Edit {
    public static void editField(String field, String input) throws InvalidNameException, InvalidEmailException, InvalidPhoneNumberException, ExistEmailException {
        if (field.trim().equalsIgnoreCase("name")) {
            editName(input);
        } else if (field.trim().equalsIgnoreCase("lastname")) {
            editLastName(input);
        } else if (field.trim().equalsIgnoreCase("email")) {
            editEmail(input);
        } else if (field.trim().equalsIgnoreCase("phonenumber")) {
            editPhoneNumber(input);
        }
//        else if (field.trim().equalsIgnoreCase("username")) {
//            editUsername(input);
//        }
        else
            System.out.println("Field for edit is InValid !");
    }

    protected static void editName(String input) throws InvalidNameException {
        boolean pass = false;
        pass = Validation.nameOrLastNameIsValid(input);
        if (!pass)
            System.out.println("Format is InValid !");

        if (pass)
            Admin.getAdmins().get(0).setName(input);
    }

    protected static void editLastName(String input) throws InvalidNameException {
        boolean pass = false;
        pass = Validation.nameOrLastNameIsValid(input);
        if (!pass)
            System.out.println("Format is InValid !");

        if (pass)
            Admin.getAdmins().get(0).setLastName(input);
    }

    protected static void editEmail(String input) throws InvalidEmailException, ExistEmailException {
        boolean pass = false;

        Validation.emailIsValid(input);

        if (Existence.checkEmailExistence(input))
            throw new ExistEmailException("Email is Existence!");


        Admin.getAdmins().get(0).setEmail(input);

    }

//    protected static void editUsername(String input) {
//        boolean pass = false;
//        pass = Validation.usernameIsValid(input);
//        if (!pass)
//            System.out.println("Format is InValid !");
//
//        if (pass) {
//            pass = Existence.checkUserNameExistence(input);
//            if (pass){
//                System.out.println("Username is Existence!");
//                pass = false;
//            }else {
//                pass = true;
//            }
//
//            if (pass)
//                Admin.getAdmins().get(0).setUserName(input);
//        }
//    }

    protected static void editPhoneNumber(String input) throws InvalidPhoneNumberException {

        Validation.phoneNumberIsValid(input);
        Admin.getAdmins().get(0).setPhoneNum(input);

    }

    public static void editPassword(String oldPassword, String newPassword) throws InvalidPasswordException {
        boolean pass = false;
        pass = Existence.checkPassword(Admin.getAdmins().get(0).getUserName(), oldPassword);
        if (!pass)
            System.out.println("Password is InValid !");

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
}
