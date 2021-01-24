package Server.Controller.RegisterController;


import Server.Controller.CompetencyController.Existence;
import Server.Controller.Exception.Plato.*;
import Server.Model.PlatoModel.Admin;
import Server.Model.PlatoModel.Player;
import java.util.Random;

public class SignUp {
    public void addAdmin(String adminInfo) throws ExistAdminException, ExistEmailException, ExistUserNameException{

        String[] adminInfoSplit = adminInfo.split("\\s");

        if (Existence.adminExistence()) {
            throw new ExistAdminException("THE ADMIN ALREADY EXISTS!");
        }

        if ( ( Existence.checkUserNameExistence(adminInfoSplit[2]))){
            throw new ExistUserNameException("THIS USERNAME ALREADY BELONGS TO A USER");
        }

        if(( Existence.checkEmailExistence(adminInfoSplit[3]))){
            throw new ExistEmailException("THIS EMAIL ALREADY BELONGS TO A USER");
        }

        Admin.AddNewAdmin( new Admin(adminInfoSplit[0], adminInfoSplit[1], 1000 , adminInfoSplit[2], adminInfoSplit [3], adminInfoSplit[4], adminInfoSplit[5]));

    }

    public void addPlayer(String playerInfo) throws ExistUserNameException, ExistEmailException {

        String[] playerInfoSplit = playerInfo.split("\\s");

        if ( ( Existence.checkUserNameExistence(playerInfoSplit[2]))){
            throw new ExistUserNameException("THIS USERNAME ALREADY BELONGS TO A USER");
        }

        if(( Existence.checkEmailExistence(playerInfoSplit[3]))){
            throw new ExistEmailException("THIS EMAIL ALREADY BELONGS TO A USER");
        }

        Player.AddNewPlayer(new Player(playerInfoSplit[0], playerInfoSplit[1], randomUserId(2000,2999), playerInfoSplit[2],playerInfoSplit[3], playerInfoSplit[4],playerInfoSplit[5]));
    }

    private int randomUserId(int min , int max){
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}

