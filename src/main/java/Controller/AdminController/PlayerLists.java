package Controller.AdminController;

import Controller.PlayerController.FindPlayerByInfo;
import Model.PlatoModel.Player;

public class PlayerLists {

    public void showAllUsers() {
        for (Player player : Player.players) {
            System.out.println(player.getUserID());
        }
    }

    public void showUsersByUserName(String userName) {
        Player player = FindPlayerByInfo.findByUserName(userName);
//        if (player == null)
//            todo exception handling
        System.out.println("getUserID: "+ player.getUserID()+" Username: "+player.getUserName()+" Name: "+player.getName()+" LastName: "+player.getLastName()+" Email: "+player.getEmail()+" Phone Number: "+player.getPhoneNum());
    }



}
