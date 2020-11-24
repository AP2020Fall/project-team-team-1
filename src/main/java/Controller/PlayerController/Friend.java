package Controller.PlayerController;


import Model.PlatoModel.Player;

public class Friend {
    private static  void acceptRequest (){

    }
    private  static void declineRequest (String username){

    }

    private void addFriends(String username,String friendUsername){
        Player player = FindPlayerByInfo.findByUserName(username);
        Player playerHowReceivedRequests = FindPlayerByInfo.findByUserName(friendUsername);

        playerHowReceivedRequests.getFriendsRequests().add(player);

    }
    private void removeFriend(String username){

    }

    private boolean UserNameIsFriend(String username){
        boolean result = true;
        return true;
    }
    private void showFriendProfile(String username){

    }
    public static void showRequests(String username){

    }
    public static void showFriends(String username){

    }
    private static void removeFromRequestList(String username){

    }
    private void searchInFriendList (String username){

    }

}
