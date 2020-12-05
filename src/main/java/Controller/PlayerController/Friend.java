package Controller.PlayerController;

import Controller.Exception.AcceptAndDeclineFriendException;
import Controller.Exception.ExistFriendException;
import Controller.Exception.ExistPlayerException;
import Model.PlatoModel.Player;

public class Friend {
    public static void acceptRequest(String username, String friendUsername) throws AcceptAndDeclineFriendException, ExistPlayerException {
        Player playerWhoReceivedRequests = FindPlayerByInfo.findByUserName(username);

        Player playerWhoSentRequests = FindPlayerByInfo.findByUserName(friendUsername);

        if (playerWhoSentRequests == null)
            throw new ExistPlayerException(friendUsername, " THIS PLAYER DOESN'T EXIST! PLEASE MAKE SURE THE USERNAME IS VALID. ");

        if (!userNameIsInFriendRequest(username, friendUsername))
            throw new AcceptAndDeclineFriendException(" THIS PLAYER IS NOT IN YOUR REQUEST LIST! PLEASE MAKE SURE THE USERNAME IS VALID ", friendUsername);

        playerWhoReceivedRequests.getFriendsRequests().remove(playerWhoSentRequests);

        playerWhoReceivedRequests.getFriends().add(playerWhoSentRequests);

        playerWhoSentRequests.getFriends().add(playerWhoReceivedRequests);

        //Player.saveInJsonFile();


    }

    public static void declineRequest(String username, String friendUsername) throws AcceptAndDeclineFriendException, ExistPlayerException {
        Player playerWhoReceivedRequests = FindPlayerByInfo.findByUserName(username);

        Player playerWhoSentRequests = FindPlayerByInfo.findByUserName(friendUsername);

        if (playerWhoSentRequests == null)
            throw new ExistPlayerException(friendUsername, " THIS PLAYER DOESN'T EXIST! PLEASE MAKE SURE THE USERNAME IS VALID.");

        if (!userNameIsInFriendRequest(username, friendUsername))
            throw new AcceptAndDeclineFriendException(" THIS PLAYER IS NOT IN YOU REQUEST LIST! PLEASE MAKE SURE THE USERNAME IS VALID ", friendUsername);


        playerWhoReceivedRequests.getFriendsRequests().remove(playerWhoSentRequests);

        //Player.saveInJsonFile();

    }

    public static void addFriends(String username, String friendUsername) throws ExistFriendException, ExistPlayerException {
        Player player = FindPlayerByInfo.findByUserName(username);
        Player playerWhoReceivedRequests = FindPlayerByInfo.findByUserName(friendUsername);

        if (playerWhoReceivedRequests == null)
            throw new ExistPlayerException(friendUsername, "THIS PLAYER DOESN'T EXIST! PLEASE MAKE SURE THE USERNAME IS VALID. ");

        if (userNameIsFriend(username, friendUsername))
            throw new ExistFriendException(friendUsername, " THIS USER ALREADY IN YOUR FRIEND LIST :) ");

        playerWhoReceivedRequests.getFriendsRequests().add(player);

        //Player.saveInJsonFile();


    }

    public static void removeFriend(String username, String friendUsername) throws ExistFriendException, ExistPlayerException {
        Player player = FindPlayerByInfo.findByUserName(username);

        Player friend = FindPlayerByInfo.findByUserName(friendUsername);

        if (friend == null)
            throw new ExistPlayerException(friendUsername, " THIS PLAYER DOESN'T EXIST! PLEASE MAKE SURE THE USERNAME IS VALID. ");

        if (!userNameIsFriend(username, friendUsername))
            throw new ExistFriendException(friendUsername, "THIS PLAYER IS NOT IN YOU REQUEST LIST! PLEASE MAKE SURE THE USERNAME IS VALID ");


        player.getFriends().remove(friend);
        friend.getFriends().remove(player);
        //Player.saveInJsonFile();

    }


    public static void showRequests(String username) throws ExistFriendException {
        Player player = FindPlayerByInfo.findByUserName(username);
        int counter =1;
        if (player.getFriendsRequests().size() == 0)
            throw new ExistFriendException(" THERE ARE NO REQUESTS TO SHOW");

        for (Player playerFriendRequests : player.getFriendsRequests()) {
            System.out.println(counter+". Username: "+ playerFriendRequests.getUserName());
            counter++;
        }
    }

    public static void showFriends(String username) throws ExistFriendException {
        Player player = FindPlayerByInfo.findByUserName(username);
        int counter =1;

        if (player.getFriends().size() == 0)
            throw new ExistFriendException(" YOU DON'T HAVE ANY FRIENDS ");

        for (Player playerFriend : player.getFriends()) {
            System.out.println(counter+". Username: "+ playerFriend.getUserName());
        }
    }

    public static void showFriendProfile(String username, String friendUsername) throws ExistFriendException {

        Player player = FindPlayerByInfo.findByUserName(username);

        if (!userNameIsFriend(username, friendUsername))
            throw new ExistFriendException(friendUsername, " THIS PLAYER IS NOT IN YOU REQUEST LIST! PLEASE MAKE SURE THE USERNAME IS VALID ");

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
