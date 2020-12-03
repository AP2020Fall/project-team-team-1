package Controller.RegisterController;


import Controller.CompetencyController.Existence;
import Controller.Exception.*;
import Model.PlatoModel.Admin;
import Model.PlatoModel.Player;
import java.util.Random;

public class SignUp {
    public void addAdmin(String adminInfo) throws ExistAdminException, ExistEmailException, ExistUserNameException, EmptyExceptionForName, EmptyExceptionForLastName {

        String[] adminInfoSplit = adminInfo.split("\\s");

        if (Existence.adminExistence()) {
            throw new ExistAdminException("THE ADMIN ALREADY EXISTS!");
//            System.out.println("THE ADMIN ALREADY EXISTS");
//            return false;
        }
        if ( ( Existence.checkUserNameExistence(adminInfoSplit[2]))){
            throw new ExistUserNameException("THIS USERNAME ALREADY BELONGS TO A USER");
//            System.out.println("THIS USERNAME ALREADY BELONGS TO A USER");
//            return false;
        }

        if(( Existence.checkEmailExistence(adminInfoSplit[3]))){
            throw new ExistEmailException("THIS EMAIL ALREADY BELONGS TO A USER");
//            System.out.println("THIS EMAIL ALREADY BELONGS TO A USER");
//            return false;
        }
        if ((adminInfoSplit[0].isEmpty())) {
            throw new EmptyExceptionForName("Name field can not be empty");
        }
        if (adminInfoSplit[1].isEmpty()){
            throw new EmptyExceptionForLastName("Last name field can not be empty");
        }

        Admin.AddNewAdmin( new Admin(adminInfoSplit[0], adminInfoSplit[1], 1000 , adminInfoSplit[2], adminInfoSplit [4], adminInfoSplit[3], adminInfoSplit[5]));
//        return true;
    }

    public void addPlayer(String playerInfo) throws ExistUserNameException, ExistEmailException, EmptyExceptionForName, EmptyExceptionForLastName, EmptyExceptionForUserName, EmptyExceptionForEmail {

        String[] playerInfoSplit = playerInfo.split("\\s");

        if ( ( Existence.checkUserNameExistence(playerInfoSplit[2]))){
            throw new ExistUserNameException("THIS USERNAME ALREADY BELONGS TO A USER");
//            System.out.println("THIS USERNAME ALREADY BELONGS TO A USER");
//            return false;
        }

        if(( Existence.checkEmailExistence(playerInfoSplit[3]))){
            throw new ExistEmailException("THIS EMAIL ALREADY BELONGS TO A USER");
//            System.out.println("THIS EMAIL ALREADY BELONGS TO A USER");
//            return false;
        }
        if ((playerInfoSplit[0].isEmpty())) {
            throw new EmptyExceptionForName("Name field can not be empty");
        }
        if (playerInfoSplit[1].isEmpty()){
            throw new EmptyExceptionForLastName("Last name field can not be empty");
        }
        if (playerInfoSplit[2].isEmpty()){
            throw new EmptyExceptionForUserName("User name field can not be empty");
        }
        if (playerInfoSplit[3].isEmpty()){
            throw new EmptyExceptionForEmail("Email field can not be empty");
        }

        Player.AddNewPlayer(new Player(playerInfoSplit[0], playerInfoSplit[1], randomUserId(2000,2999), playerInfoSplit[2],playerInfoSplit[4], playerInfoSplit[3],playerInfoSplit[5]));
    }

    private int randomUserId(int min , int max){
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}

