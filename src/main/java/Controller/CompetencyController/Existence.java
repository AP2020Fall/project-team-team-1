package Controller.CompetencyController;

import Controller.PlayerController.FindPlayerByInfo;
import Model.PlatoModel.*;


public class Existence {



    public static boolean checkUserNameExistence(String userName) {
        boolean result = false;
        for (Player user : Player.players) {
            if (user.getUserName().equals(userName)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public static boolean checkEmailExistence(String email) {
        boolean result = false;
        for (Player user : Player.players) {
            if (user.getEmail().equals(email)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public static boolean checkPassword(String username, String password) {
        boolean result = false;
        Player userByUsername = null;
        for (Player user : Player.players) {
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
    public static String checkPasswordForView (String username, String password) {
        boolean result = false;
        Player userByUsername = null;
        for (Player user : Player.players) {
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

        return String.valueOf(result);
    }

    public static boolean checkPasswordForAdmin(String password) {
        boolean result = false;
        Admin admin = null;

        admin = Admin.getAdmins().get(0);

        if (admin != null) {
            if (admin.getPassword().equals(password)) {
                result = true;
            }
        }

        return result;
    }
    public static boolean adminExistence() {
        boolean result = true;
        if (Admin.getAdmins().isEmpty())
            result = false;
        return result;
    }

    public static boolean checkEventExistence(int eventID) {
        boolean result = false;
        for (Event event : Event.getEvents()) {
            if (event.getEventID() == eventID){
                result = true;
                break;
            }
        }
        return result;
    }

    public static boolean checkGameExistence(String gameName){
        boolean result = false;
        if (gameName.equalsIgnoreCase(Games.getGames().get(0).getGameName()) || gameName.equalsIgnoreCase(Games.getGames().get(1).getGameName()))
                result = true;

        return result;
    }
    public static boolean checkPlayerActivation(String username){
        Player player = FindPlayerByInfo.findByUserName(username);
        return player.isActivation();
    }

}


