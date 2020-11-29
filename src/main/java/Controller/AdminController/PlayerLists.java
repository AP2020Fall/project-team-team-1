package Controller.AdminController;

import Controller.Exception.ExistPlayerException;
import Controller.PlayerController.FindPlayerByInfo;
import Model.PlatoModel.Admin;
import Model.PlatoModel.Player;

public class PlayerLists {

    public static void showAllUsers() throws ExistPlayerException {
        if (Player.players.size() == 0)
            throw new ExistPlayerException("There is no Player for Show");
        for (Player player : Player.players) {
            System.out.println(player.getUserName());
        }
    }

    public static void showUsersByUserName(String userName) throws ExistPlayerException {
        Player player = FindPlayerByInfo.findByUserName(userName);
        if (player == null)
            throw new ExistPlayerException(userName + " Is Invalid");
        System.out.println("getUserID: " + player.getUserID() + " Username: " + player.getUserName() + " Name: " + player.getName() + " LastName: " + player.getLastName() + " Email: " + player.getEmail() + " Phone Number: " + player.getPhoneNum());
    }

    public static void showAdminInfo() {
        Admin admin = Admin.getAdmins().get(0);
        System.out.println("getUserID: " + admin.getUserID() + " Username: " + admin.getUserName() + " Name: " + admin.getName() + " LastName: " + admin.getLastName() + " Email: " + admin.getEmail() + " Phone Number: " + admin.getPhoneNum());
    }


}
