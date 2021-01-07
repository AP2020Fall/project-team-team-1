package Controller.PlayerController;

import Controller.CompetencyController.Existence;
import Controller.CompetencyController.Validation;
import Controller.Exception.Plato.*;
import Controller.RegisterController.LogIn;
import Model.PlatoModel.Player;

public class Edit {

    public static void editField(String username, String field, String input) throws InvalidNameException, InvalidEmailException, InvalidPhoneNumberException, ExistEmailException, InvalidFieldException {
        Player player = FindPlayerByInfo.findByUserName(username);

        if (field.trim().equalsIgnoreCase("name")) {
            editFirstName(player, input);
        } else if (field.trim().equalsIgnoreCase("lastname")) {
            editLastName(player, input);
        } else if (field.trim().equalsIgnoreCase("email")) {
            editEmail(player, input);
        } else if (field.trim().equalsIgnoreCase("phonenumber")) {
            editPhoneNumber(player, input);
        } else
            throw new InvalidFieldException("Entered Field for change in Invalid");

    }


    protected static void editFirstName(Player player, String input) throws InvalidNameException {

        Validation.nameOrLastNameIsValid(input);

        player.setName(input);
    }

    protected static void editLastName(Player player, String input) throws InvalidNameException {
        Validation.nameOrLastNameIsValid(input);

        player.setLastName(input);

    }

    public static void editPassword(String username, String oldPassword, String newPassword) throws InvalidPasswordException, WrongPasswordException, SamePasswordException, StrongerPasswordException {
        Player player = FindPlayerByInfo.findByUserName(username);

        if (!Existence.checkPassword(player.getUserName(), oldPassword))
            throw new WrongPasswordException();


        if (oldPassword.equals(newPassword))
            throw new SamePasswordException("New Password is same to Old password! please Enter new Password");


        Validation.passwordIsValid(newPassword);

        player.setPassword(newPassword);

        LogIn.setPassword(newPassword);

    }

    protected static void editEmail(Player player, String input) throws InvalidEmailException, ExistEmailException {

        Validation.emailIsValid(input);


        if (Existence.checkEmailExistence(input))
            throw new ExistEmailException("This Email is Belong to another Player Enter another Email");

        player.setEmail(input);


    }

    protected static void editPhoneNumber(Player player, String input) throws InvalidPhoneNumberException {

        Validation.phoneNumberIsValid(input);

        player.setPhoneNum(input);

    }
    public static void editBio(String Username, String input){
        Player player = FindPlayerByInfo.findByUserName(Username);
        player.setBio(input);
    }
    public static void editProfileURL(String username, String input){
        Player player = FindPlayerByInfo.findByUserName(username);
        player.setProfileURL(input);
    }

}
