package Controller.AdminController;

import Controller.CompetencyController.Existence;
import Controller.CompetencyController.Validation;
import Controller.Exception.Plato.*;
import Model.PlatoModel.Admin;

public class Edit {
    public static void editField(String field, String input) throws InvalidNameException, InvalidEmailException, InvalidPhoneNumberException, ExistEmailException, InvalidFieldException {
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
            throw new InvalidFieldException("Field for Edit is Invalid");

        //Admin.saveInJsonFile();
    }

    protected static void editName(String input) throws InvalidNameException {
        Validation.nameOrLastNameIsValid(input);

        Admin.getAdmins().get(0).setName(input);
    }

    protected static void editLastName(String input) throws InvalidNameException {
        Validation.nameOrLastNameIsValid(input);

        System.out.println("Format is InValid !");


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

    public static void editPassword(String oldPassword, String newPassword) throws InvalidPasswordException, WrongPasswordException, StrongerPasswordException {
        boolean pass = false;

        if (!Existence.checkPassword(Admin.getAdmins().get(0).getUserName(), oldPassword))
            throw new WrongPasswordException();

        if (oldPassword.equals(newPassword))
            throw new InvalidPasswordException("Old password and new password are same! please enter new password");


        Validation.passwordIsValid(newPassword);


        Admin.getAdmins().get(0).setPassword(newPassword);




    }
}
