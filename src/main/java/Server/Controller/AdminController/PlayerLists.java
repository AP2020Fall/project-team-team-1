package Server.Controller.AdminController;

import Server.Controller.Exception.Plato.ExistPlayerException;
import Server.Controller.PlayerController.FindPlayerByInfo;
import Server.Model.PlatoModel.Admin;
import Server.Model.PlatoModel.Player;

public class PlayerLists {

    public static String showAllUsers() throws ExistPlayerException {
        StringBuilder showAllUsers = new StringBuilder();
        if (Player.players.size() == 0)
            throw new ExistPlayerException("There is no Player for Show");
        for (Player player : Player.players) {
            showAllUsers.append(player.getUserName()).append("$");
        }
        return String.valueOf(showAllUsers);
    }

    public static String showUsersByUserName(String userName) throws ExistPlayerException {
        StringBuilder showUsersByUserName = new StringBuilder();
        Player player = FindPlayerByInfo.findByUserName(userName);
        if (player == null)
            throw new ExistPlayerException(userName + " Is Invalid");

        showUsersByUserName.append("getUserID: ").append(player.getUserID()).append(" |Username: ").append(player.getUserName()).append(" |Name: ").append(player.getName()).append(" |LastName: ").append(player.getLastName()).append(" |Email: ").append(player.getEmail()).append(" |Phone Number: ").append(player.getPhoneNum()).append("$");
        return String.valueOf(showUsersByUserName);
    }

    public static String showAdminInfo() {
        StringBuilder showAdminInfo = new StringBuilder();
        Admin admin = Admin.getAdmins().get(0);
        showAdminInfo.append(admin.getUserID()).append("$").append(admin.getUserName()).append("$").append(admin.getName()).append("$").append(admin.getLastName()).append("$").append(admin.getEmail()).append("$").append(admin.getPhoneNum()).append("$").append(admin.getBio()).append("$").append(admin.getProfileURL()).append("$");
        return String.valueOf(showAdminInfo);
    }



}
