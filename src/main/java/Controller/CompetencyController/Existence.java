package Controller.CompetencyController;

import Model.PlatoModel.Admin;
import Model.PlatoModel.Player;
import Model.PlatoModel.User;


public class Existence {

    public static boolean checkUserNameExistence(String userName) {
        boolean result = false;
        for (User user : Player.users) {
            if (user.getUserName().equals(userName)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public static boolean checkEmailExistence(String email) {
        boolean result = false;
        for (User user : Player.users) {
            if (user.getEmail().equals(email)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public static boolean checkPassword(String username, String password) {
        boolean result = false;
        User userByUsername = null;
        for (User user : Player.users) {
            if (user.getUserName().equals(username)) {
                userByUsername = user;
                break;
            }
        }
        if (userByUsername != null) {
            if (userByUsername.getPassword().equals(password)) {
                result = true;
            }
        }
        return result;
    }

    public static boolean adminExistence() {
        boolean result = false;
        if (Admin.getAdmins().isEmpty())
            result = true;
        return result;
    }

}


