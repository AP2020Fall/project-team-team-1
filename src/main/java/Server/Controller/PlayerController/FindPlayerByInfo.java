package Server.Controller.PlayerController;


import Server.Model.PlatoModel.Player;
import Server.Model.PlatoModel.User;

public class FindPlayerByInfo {
    public static Player findByUserName(String userName){
        Player player = null;
        for (Player wantedPlayer : Player.getPlayers()) {
            if (wantedPlayer.getUserName().equals(userName)){
                player = wantedPlayer ;
                break;
            }
        }
        return player ;
    }
    public static Player findByUserID(String userId){
        Player player = null;
        for (Player wantedPlayer : Player.getPlayers()) {
            if (wantedPlayer.getUserID() == Integer.parseInt(userId)){
                player = wantedPlayer ;
                break;
            }
        }
        return player ;
    }
    public static Player findByUserEmail(String userEmail){
        Player player = null;
        for (Player wantedPlayer : Player.getPlayers()) {
            if (wantedPlayer.getEmail().equals(userEmail)) {
                player = wantedPlayer ;
                break;
            }
        }
        return player;
    }

    public static User findByUserByUsername(String username){
        User user1 = null;
//        Player player = null;
        for (User wantedPlayer : User.getUsers()) {
            if (wantedPlayer.getUserName().equals(username)) {
                user1 = wantedPlayer ;
                break;
            }
        }
        return user1;
    }
}
