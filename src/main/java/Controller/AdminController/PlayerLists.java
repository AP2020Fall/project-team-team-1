package Controller.AdminController;

import Model.PlatoModel.Player;

public class PlayerLists {

    public static void showAllUsers() {
        for (Player player : Player.players) {
            System.out.println(player.getUserID());
        }
    }

    public static void showUsersByUserName(String userName) {

    }



}
