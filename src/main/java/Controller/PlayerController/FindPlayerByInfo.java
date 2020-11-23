package Controller.PlayerController;


import Model.PlatoModel.Player;
import Model.PlatoModel.User;

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
    public static Player findByUserID(String userName){
        Player player = null;
        for (Player wantedPlayer : Player.getPlayers()) {
            if (wantedPlayer.getUserID() == Integer.parseInt(userName)){
                player = wantedPlayer ;
                break;
            }
        }
        return player ;
    }
    public static Player findByUserEmail(String userName){
        Player player = null;
        for (Player wantedPlayer : Player.getPlayers()) {
            if (wantedPlayer.getEmail().equals(userName)) {
                player = wantedPlayer ;
                break;
            }
        }
        return player;
    }
}
