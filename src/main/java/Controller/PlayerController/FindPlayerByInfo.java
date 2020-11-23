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
}
