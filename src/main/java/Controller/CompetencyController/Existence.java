package Controller.CompetencyController;

import Model.PlatoModel.Admin;
import Model.PlatoModel.Player;
import Model.PlatoModel.User;


public class Existence {

    public static boolean CheckUserNameExistence(String userName) {
        boolean result = false;
        for (User user : Player.users) {
            if (user.getUserName().equals(userName))
                result = true;
        }
        return result;
    }

    public static boolean CheckEmailExistence(String email) {
        boolean result = false;
        return result;
    }

    public static boolean CheckPassword(String username, String password) {
        boolean result = false;
        return result;
    }
        public static boolean AdminExistence(){
            boolean result = false;
            if (Admin.getAdmins().isEmpty())
                result = true;
            return result;
        }

}


