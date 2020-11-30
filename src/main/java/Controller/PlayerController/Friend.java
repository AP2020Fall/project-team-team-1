package Controller.PlayerController;


import Controller.Exception.AcceptAndDeclineFriendException;
import Controller.Exception.ExistFriendException;
import Controller.Exception.ExistPlayerException;
import Model.PlatoModel.Player;

public class Friend {
    public static void acceptRequest(String username, String friendUsername) throws AcceptAndDeclineFriendException, ExistPlayerException {
        Player playerHowReceivedRequests = FindPlayerByInfo.findByUserName(username);

        Player playerHowSentRequests = FindPlayerByInfo.findByUserName(friendUsername);

        if (playerHowSentRequests == null)
            throw new ExistPlayerException(friendUsername, " isn't exist please make sure about Username! ");

        if (!userNameIsInFriendRequest(username, friendUsername))
            throw new AcceptAndDeclineFriendException(" it isn't in your Request list!,make Sure about the Username ", friendUsername);

        playerHowReceivedRequests.getFriendsRequests().remove(playerHowSentRequests);

        playerHowReceivedRequests.getFriends().add(playerHowSentRequests);

        playerHowSentRequests.getFriends().add(playerHowReceivedRequests);

        Player.saveInJsonFile();


    }

    public static void declineRequest(String username, String friendUsername) throws AcceptAndDeclineFriendException, ExistPlayerException {
        Player playerHowReceivedRequests = FindPlayerByInfo.findByUserName(username);

        Player playerHowSentRequests = FindPlayerByInfo.findByUserName(friendUsername);

        if (playerHowSentRequests == null)
            throw new ExistPlayerException(friendUsername, " isn't exist please make sure about Username! ");

        if (!userNameIsInFriendRequest(username, friendUsername))
            throw new AcceptAndDeclineFriendException(" it isn't in your Request list!,make Sure about the Username ", friendUsername);


        playerHowReceivedRequests.getFriendsRequests().remove(playerHowSentRequests);

        Player.saveInJsonFile();

    }

    public static void addFriends(String username, String friendUsername) throws ExistFriendException, ExistPlayerException {
        Player player = FindPlayerByInfo.findByUserName(username);
        Player playerHowReceivedRequests = FindPlayerByInfo.findByUserName(friendUsername);

        if (playerHowReceivedRequests == null)
            throw new ExistPlayerException(friendUsername, " isn't exist please make sure about Username! ");

        if (userNameIsFriend(username, friendUsername))
            throw new ExistFriendException(friendUsername, " is already in you friends list :) ");


        playerHowReceivedRequests.getFriendsRequests().add(player);

        Player.saveInJsonFile();


    }

    public static void removeFriend(String username, String friendUsername) throws ExistFriendException, ExistPlayerException {
        Player player = FindPlayerByInfo.findByUserName(username);

        Player friend = FindPlayerByInfo.findByUserName(friendUsername);

        if (friend == null)
            throw new ExistPlayerException(friendUsername, " isn't exist please make sure about Username! ");

        if (!userNameIsFriend(username, friendUsername))
            throw new ExistFriendException(friendUsername, " isn't in you friends list, Make Sure about Username and Try Again! ");


        player.getFriends().remove(friend);

        Player.saveInJsonFile();

    }


    public static void showRequests(String username) throws ExistFriendException {
        Player player = FindPlayerByInfo.findByUserName(username);

        if (player.getFriendsRequests().size() == 0)
            throw new ExistFriendException(" There is no Request for Show! ");

        for (Player playerFriendRequests : player.getFriendsRequests()) {
            System.out.println("getUserID: " + playerFriendRequests.getUserID() + " Username: " + playerFriendRequests.getUserName() + " Name: " + playerFriendRequests.getName() + " LastName: " + playerFriendRequests.getLastName() + " Email: " + playerFriendRequests.getEmail() + " Phone Number: " + playerFriendRequests.getPhoneNum());
        }
    }

    public static void showFriends(String username) throws ExistFriendException {
        Player player = FindPlayerByInfo.findByUserName(username);

        if (player.getFriends().size() == 0)
            throw new ExistFriendException(" There is no Friend for Show! ");

        for (Player playerFriend : player.getFriends()) {
            System.out.println("getUserID: " + playerFriend.getUserID() + " Username: " + playerFriend.getUserName() + " Name: " + playerFriend.getName() + " LastName: " + playerFriend.getLastName() + " Email: " + playerFriend.getEmail() + " Phone Number: " + playerFriend.getPhoneNum());
        }
    }

    public static void showFriendProfile(String username, String friendUsername) throws ExistFriendException {

        Player player = FindPlayerByInfo.findByUserName(username);

        if (!userNameIsFriend(username, friendUsername))
            throw new ExistFriendException(friendUsername, " isn't in you friends list, Make Sure about Username and Try Again! ");

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

    protected static boolean userNameIsInFriendRequest(String username, String friendUsername) {
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
