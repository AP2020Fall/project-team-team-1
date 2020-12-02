package Controller.PlayerController;

import Controller.CompetencyController.Existence;
import Controller.CompetencyController.Validation;
import Controller.Exception.*;
import Model.PlatoModel.Admin;
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
        }
//        else if (field.trim().equalsIgnoreCase("username")) {
//            editUsername(player,input);
//        }
        else
            throw new InvalidFieldException("Entered Field for change in Invalid");

        //Player.saveInJsonFile();
    }

//    protected static void editUsername(Player player,String input) {
//        boolean pass = false;
//        pass = Validation.usernameIsValid(input);
//        if (!pass)
//            System.out.println("this format is invalid !");
//
//        if (pass) {
//            pass = Existence.checkUserNameExistence(input);
//            if (pass){
//                System.out.println("Username is existence!");
//                pass = false;
//            }
//            else {
//                //player.setUserName(input);
//                pass = true;
//            }
//
//            if (pass)
//                player.setUserName(input);
//        }
//    }

    protected static void editFirstName(Player player, String input) throws InvalidNameException {

        Validation.nameOrLastNameIsValid(input);

        player.setName(input);
    }

    protected static void editLastName(Player player, String input) throws InvalidNameException {
        Validation.nameOrLastNameIsValid(input);

        player.setLastName(input);

    }

    public static void editPassword(String username, String oldPassword, String newPassword) throws InvalidPasswordException, WrongPasswordException, SamePasswordException {
        Player player = FindPlayerByInfo.findByUserName(username);

        if (!Existence.checkPassword(player.getUserName(), oldPassword))
            throw new WrongPasswordException();


        if (oldPassword.equals(newPassword))
            throw new SamePasswordException("New Password is same to Old password! please Enter new Password");


        Validation.passwordIsValid(newPassword);

        player.setPassword(newPassword);
        //Player.saveInJsonFile();


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
}
