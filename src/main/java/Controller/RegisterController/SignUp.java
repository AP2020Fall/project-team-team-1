package Controller.RegisterController;


import Controller.CompetencyController.Existence;
import Model.PlatoModel.Admin;
import Model.PlatoModel.Player;

import java.util.Random;

public class SignUp {
    public boolean addAdmin(String adminInfo) {

        String[] adminInfoSplit = adminInfo.split("\\s");

        if (Existence.adminExistence()) {
            System.out.println("THE ADMIN ALREADY EXISTS");
            return false;
        }

        Admin.AddNewAdmin( new Admin(adminInfoSplit[0], adminInfoSplit[1], 1000 , adminInfoSplit[2], adminInfoSplit [3], adminInfoSplit[4], adminInfoSplit[5]));
        return true;
    }

    public boolean addPlayer(String playerInfo) {

        String[] playerInfoSplit = playerInfo.split("\\s");

        if ( !( Existence.checkUserNameExistence(playerInfoSplit[2]))){
            System.out.println("THIS USERNAME ALREADY BELONGS TO A USER");
            return false;
        }

        if(!( Existence.checkEmailExistence(playerInfoSplit[4]))){
            System.out.println("THIS EMAIL ALREADY BELONGS TO A USER");
            return false;
        }

        Player.AddNewPlayer(new Player(playerInfoSplit[0], playerInfoSplit[1], randomUserId(2000,2999), playerInfoSplit[2],playerInfoSplit[3], playerInfoSplit[4],playerInfoSplit[5]));
        //todo user must be fixed! we dont have to get a username to signup
        return true;
    }

    private int randomUserId(int min , int max){
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}

