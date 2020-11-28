package Controller.RegisterController;


import Controller.CompetencyController.Existence;
import Controller.Exception.ExistAdminException;
import Controller.Exception.ExistEmailException;
import Controller.Exception.ExistUserNameException;
import Model.PlatoModel.Admin;
import Model.PlatoModel.Player;
import java.util.Random;

public class SignUp {
    public void addAdmin(String adminInfo) throws ExistAdminException, ExistEmailException, ExistUserNameException {

        String[] adminInfoSplit = adminInfo.split("\\s");

        if (Existence.adminExistence()) {
            throw new ExistAdminException("There is no Admin Yet!");
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

        Admin.AddNewAdmin( new Admin(adminInfoSplit[0], adminInfoSplit[1], 1000 , adminInfoSplit[2], adminInfoSplit [4], adminInfoSplit[3], adminInfoSplit[5]));
//        return true;
    }

    public void addPlayer(String playerInfo) throws ExistUserNameException, ExistEmailException {

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

        Player.AddNewPlayer(new Player(playerInfoSplit[0], playerInfoSplit[1], randomUserId(2000,2999), playerInfoSplit[2],playerInfoSplit[4], playerInfoSplit[3],playerInfoSplit[5]));
    }

    private int randomUserId(int min , int max){
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}

