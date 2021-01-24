package Server.Controller.AdminController;

import Server.Controller.CompetencyController.Existence;
import Server.Controller.CompetencyController.Validation;
import Server.Controller.Exception.Plato.*;
import Server.Model.PlatoModel.Admin;

public class Edit {
    public static void editField(String field, String input) throws InvalidNameException, InvalidEmailException, InvalidPhoneNumberException, ExistEmailException, InvalidFieldException {
        if (field.trim().equalsIgnoreCase("name")) {
            editName(input);
        } else if (field.trim().equalsIgnoreCase("lastname")) {
            editLastName(input);
        } else if (field.trim().equalsIgnoreCase("email")) {
            editEmail(input);
        } else if (field.trim().equalsIgnoreCase("phoneNumber")) {
            editPhoneNumber(input);
        } else
            throw new InvalidFieldException("Field for Edit is Invalid");

    }

    protected static void editName(String input) throws InvalidNameException {
        Validation.nameOrLastNameIsValid(input);

        Admin.getAdmins().get(0).setName(input);
    }

    protected static void editLastName(String input) throws InvalidNameException {
        Validation.nameOrLastNameIsValid(input);



        Admin.getAdmins().get(0).setLastName(input);
    }

    protected static void editEmail(String input) throws InvalidEmailException, ExistEmailException {

        Validation.emailIsValid(input);

        if (Existence.checkEmailExistence(input))
            throw new ExistEmailException("Email is Existence!");


        Admin.getAdmins().get(0).setEmail(input);

    }

    protected static void editPhoneNumber(String input) throws InvalidPhoneNumberException {

        Validation.phoneNumberIsValid(input);

        Admin.getAdmins().get(0).setPhoneNum(input);

    }

    public static void editPassword(String oldPassword, String newPassword) throws InvalidPasswordException, WrongPasswordException, StrongerPasswordException {

        if (!Existence.checkPasswordForAdmin(oldPassword))
            throw new WrongPasswordException();

        if (oldPassword.equals(newPassword))
            throw new InvalidPasswordException("Old password and new password are same! please enter new password");


        Validation.passwordIsValid(newPassword);


        Admin.getAdmins().get(0).setPassword(newPassword);

    }

    public static void editBio (String input){
        Admin.getAdmins().get(0).setBio(input);
    }
    public static void editProfileURL(String input){
        Admin.getAdmins().get(0).setProfileURL(input);
    }
}
