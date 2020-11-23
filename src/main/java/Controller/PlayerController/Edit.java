package Controller.PlayerController;

import Controller.CompetencyController.Existence;
import Controller.CompetencyController.Validation;
import Model.PlatoModel.Admin;
import Model.PlatoModel.Player;

public class Edit {

    public static void editField (String username,String field, String input) {
        Player player = FindPlayerByInfo.findByUserName(username);

        if (field.trim().equalsIgnoreCase("name")) {
            editFirstName(player,input);
        } else if (field.trim().equalsIgnoreCase("lastname")) {
            editLastName(player,input);
        } else if (field.trim().equalsIgnoreCase("email")) {
            editEmail(player,input);
        } else if (field.trim().equalsIgnoreCase("phonenumber")) {
            editPhoneNumber(player,input);
        }
//        else if (field.trim().equalsIgnoreCase("username")) {
//            editUsername(player,input);
//        }
        else
            System.out.println("Can not edit this field !");
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

    public static void editFirstName(Player player, String input){
        boolean pass = false;
        pass = Validation.nameOrLastNameIsValid(input);
        if (!pass){
            System.out.println("This format is invalid !");
        }
        if (pass){
            player.setName(input);
        }
    }

    public static void editLastName(Player player,String input){
        boolean pass = false;
        pass = Validation.nameOrLastNameIsValid(input);
        if (!pass){
            System.out.println("This format is invalid !");
        }
        if (pass){
            player.setLastName(input);
        }
    }

    public static void editPassword(Player player , String oldPassword , String newPassword){
        boolean pass = false;
        pass = Existence.checkPassword(player.getUserName(), oldPassword);
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
                    player.setPassword(newPassword);
            }

        }
    }

    public static void editEmail(Player player ,String input){
        boolean pass = false;
        pass = Validation.emailIsValid(input);
        if (!pass)
            System.out.println("This format is invalid !");

        if (pass) {
            pass = Existence.checkEmailExistence(input);
            if (pass){
                System.out.println("Email is Existence!");
                pass = false;
            }
            else {
                pass = true;
            }

            if (pass){
                player.setEmail(input);
            }
        }
    }

    public static void editPhoneNumber(Player player,String input){
        boolean pass = false;
        pass = Validation.phoneNumberIsValid(input);
        if (!pass)
            System.out.println("This format is invalid !");

        if (pass){
            player.setPhoneNum(input);
        }
    }
}
