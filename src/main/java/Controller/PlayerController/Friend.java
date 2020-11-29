package Controller.PlayerController;


import Model.PlatoModel.Player;

public class Friend {
    public static void acceptRequest(String username, String friendUsername) {
        Player playerHowReceivedRequests = FindPlayerByInfo.findByUserName(username);
        Player playerHowSentRequests = FindPlayerByInfo.findByUserName(friendUsername);
        if (!userNameIsInFriendRequest(username, friendUsername)){
            System.out.println(friendUsername+" is not in "+username+" Request list!");
            return;
        }
        playerHowReceivedRequests.getFriendsRequests().remove(playerHowSentRequests);
        playerHowReceivedRequests.getFriends().add(playerHowSentRequests);
        playerHowSentRequests.getFriends().add(playerHowReceivedRequests);
    }

    public static void declineRequest(String username, String friendUsername) {
        Player playerHowReceivedRequests = FindPlayerByInfo.findByUserName(username);
        Player playerHowSentRequests = FindPlayerByInfo.findByUserName(friendUsername);
        if (!userNameIsInFriendRequest(username, friendUsername)){
            System.out.println(friendUsername+" is not in "+username+" Request list!");
            return;
        }
        playerHowReceivedRequests.getFriendsRequests().remove(playerHowSentRequests);
    }

    public static void addFriends(String username, String friendUsername) {
        Player player = FindPlayerByInfo.findByUserName(username);
        Player playerHowReceivedRequests = FindPlayerByInfo.findByUserName(friendUsername);

        if (userNameIsFriend(username, friendUsername)){
            System.out.println(friendUsername+" is already friend");
            return;
        }

        playerHowReceivedRequests.getFriendsRequests().add(player);


    }

    public static void removeFriend(String username, String friendUsername) {
        Player player = FindPlayerByInfo.findByUserName(username);
        Player friend = FindPlayerByInfo.findByUserName(friendUsername);

//todo check palyer is friend or no!
        if (!userNameIsFriend(username, friendUsername)){
            System.out.println(friendUsername+" is not exist in your Friends");
            return;
        }
        if (!userNameIsFriend(username, friendUsername)){
            System.out.println(friendUsername+" is not in "+username+" Request list!");
            return;
        }

        player.getFriends().remove(friend);
    }


    public static void showRequests(String username) {
        Player player = FindPlayerByInfo.findByUserName(username);

        for (Player playerFriendRequests : player.getFriendsRequests()) {
            System.out.println("getUserID: " + playerFriendRequests.getUserID() + " Username: " + playerFriendRequests.getUserName() + " Name: " + playerFriendRequests.getName() + " LastName: " + playerFriendRequests.getLastName() + " Email: " + playerFriendRequests.getEmail() + " Phone Number: " + playerFriendRequests.getPhoneNum());
        }
    }

    public static void showFriends(String username) {
        Player player = FindPlayerByInfo.findByUserName(username);

        for (Player playerFriend : player.getFriends()) {
            System.out.println("getUserID: " + playerFriend.getUserID() + " Username: " + playerFriend.getUserName() + " Name: " + playerFriend.getName() + " LastName: " + playerFriend.getLastName() + " Email: " + playerFriend.getEmail() + " Phone Number: " + playerFriend.getPhoneNum());
        }
    }

    public static void showFriendProfile(String username, String friendUsername) {
        // if its her/his friend
        Player player = FindPlayerByInfo.findByUserName(username);
        if (!userNameIsFriend(username, friendUsername)){
            System.out.println(friendUsername+" is not "+username+" friend");
            return;
        }
        for (Player playerFriend : player.getFriends()) {
            if (playerFriend.getUserName().equals(friendUsername)) {
                System.out.println("getUserID: " + playerFriend.getUserID() + " Username: " + playerFriend.getUserName() + " Name: " + playerFriend.getName() + " LastName: " + playerFriend.getLastName() + " Email: " + playerFriend.getEmail() + " Phone Number: " + playerFriend.getPhoneNum());
                break;
            }
        }

    }

    protected static boolean userNameIsFriend(String username, String friendUsername) {
        boolean result = false;
        Player player = FindPlayerByInfo.findByUserName(username);

        for (Player playerFriend : player.getFriends()) {
            if (playerFriend.getUserName().equals(friendUsername)) {
                result = true;
                break;
            }

        }
        return result;
    }
    protected static boolean userNameIsInFriendRequest (String username, String friendUsername) {
        boolean result = false;
        Player player = FindPlayerByInfo.findByUserName(username);

        for (Player playerFriendRec : player.getFriendsRequests()) {
            if (playerFriendRec.getUserName().equals(friendUsername)) {
                result = true;
                break;
            }

        }
        return result;
    }

//    private static void removeFromRequestList(String username) {
//
//    }
//
//    private void searchInFriendList(String friendUsername) {
//
//    }

}
