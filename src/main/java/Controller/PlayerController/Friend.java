package Controller.PlayerController;


import Model.PlatoModel.Player;

public class Friend {
    private static  void acceptRequest (String username,String friendUsername){
        Player playerHowReceivedRequests = FindPlayerByInfo.findByUserName(username);
        Player playerHowSentRequests = FindPlayerByInfo.findByUserName(friendUsername);

        playerHowReceivedRequests.getFriendsRequests().remove(playerHowSentRequests);
        playerHowReceivedRequests.getFriends().add(playerHowSentRequests);
        playerHowSentRequests.getFriends().add(playerHowReceivedRequests);
    }
    private  static void declineRequest (String username,String friendUsername){
        Player playerHowReceivedRequests = FindPlayerByInfo.findByUserName(username);
        Player playerHowSentRequests = FindPlayerByInfo.findByUserName(friendUsername);

        playerHowReceivedRequests.getFriendsRequests().remove(playerHowSentRequests);
    }

    private void addFriends(String username,String friendUsername){
        Player player = FindPlayerByInfo.findByUserName(username);
        Player playerHowReceivedRequests = FindPlayerByInfo.findByUserName(friendUsername);
//todo check palyer is friend or no!
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
