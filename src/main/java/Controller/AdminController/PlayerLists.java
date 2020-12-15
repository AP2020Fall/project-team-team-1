package Controller.AdminController;

import Controller.Exception.Plato.ExistPlayerException;
import Controller.PlayerController.FindPlayerByInfo;
import Model.PlatoModel.Admin;
import Model.PlatoModel.Player;

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
        showAdminInfo.append("getUserID: ").append(admin.getUserID()).append(" |Username: ").append(admin.getUserName()).append(" |Name: ").append(admin.getName()).append(" |LastName: ").append(admin.getLastName()).append(" |Email: ").append(admin.getEmail()).append(" |Phone Number: ").append(admin.getPhoneNum()).append("$");
        return String.valueOf(showAdminInfo);
    }



}
