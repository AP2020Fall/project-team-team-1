package Controller.PlayerController;

import Controller.Exception.Plato.AcceptAndDeclineFriendException;
import Controller.Exception.Plato.ExistFriendException;
import Controller.Exception.Plato.ExistPlayerException;
import Model.PlatoModel.Player;

public class Friend {
    public static void acceptRequest(String username, String friendUsername) throws AcceptAndDeclineFriendException, ExistPlayerException {
        Player playerWhoReceivedRequests = FindPlayerByInfo.findByUserName(username);

        Player playerWhoSentRequests = FindPlayerByInfo.findByUserName(friendUsername);

        if (playerWhoSentRequests == null)
            throw new ExistPlayerException(friendUsername, " THIS PLAYER DOESN'T EXIST! PLEASE MAKE SURE THE USERNAME IS VALID. ");

        if (!userNameIsInFriendRequestByUsername(username, friendUsername))
            throw new AcceptAndDeclineFriendException(" THIS PLAYER IS NOT IN YOUR REQUEST LIST! PLEASE MAKE SURE THE USERNAME IS VALID ", friendUsername);

        playerWhoReceivedRequests.getFriendsRequests().remove(friendUsername);

        playerWhoReceivedRequests.getFriends().add(friendUsername);

        playerWhoSentRequests.getFriends().add(username);


    }

    public static void declineRequest(String username, String friendUsername) throws AcceptAndDeclineFriendException, ExistPlayerException {
        Player playerWhoReceivedRequests = FindPlayerByInfo.findByUserName(username);

        Player playerWhoSentRequests = FindPlayerByInfo.findByUserName(friendUsername);

        if (playerWhoSentRequests == null)
            throw new ExistPlayerException(friendUsername, " THIS PLAYER DOESN'T EXIST! PLEASE MAKE SURE THE USERNAME IS VALID.");

        if (!userNameIsInFriendRequestByUsername(username, friendUsername))
            throw new AcceptAndDeclineFriendException(" THIS PLAYER IS NOT IN YOU REQUEST LIST! PLEASE MAKE SURE THE USERNAME IS VALID ", friendUsername);


        playerWhoReceivedRequests.getFriendsRequests().remove(friendUsername);

    }

    public static void addFriends(String username, String friendUsername) throws ExistFriendException, ExistPlayerException {
        Player player = FindPlayerByInfo.findByUserName(username);
        Player playerWhoReceivedRequests = FindPlayerByInfo.findByUserName(friendUsername);

        if (playerWhoReceivedRequests == null)
            throw new ExistPlayerException(friendUsername, "THIS PLAYER DOESN'T EXIST! PLEASE MAKE SURE THE USERNAME IS VALID. ");

        if (userNameIsFriendByUsername(username, friendUsername))
            throw new ExistFriendException(friendUsername, " THIS USER ALREADY IN YOUR FRIEND LIST :) ");

        playerWhoReceivedRequests.getFriendsRequests().add(username);


    }

    public static void removeFriend(String username, String friendUsername) throws ExistFriendException, ExistPlayerException {
        Player player = FindPlayerByInfo.findByUserName(username);

        Player friend = FindPlayerByInfo.findByUserName(friendUsername);

        if (friend == null)
            throw new ExistPlayerException(friendUsername, " THIS PLAYER DOESN'T EXIST! PLEASE MAKE SURE THE USERNAME IS VALID. ");

        if (!userNameIsFriendByUsername(username, friendUsername))
            throw new ExistFriendException(friendUsername, "THIS PLAYER IS NOT IN YOU REQUEST LIST! PLEASE MAKE SURE THE USERNAME IS VALID ");


        player.getFriends().remove(friendUsername);
        friend.getFriends().remove(username);

    }


    public static void showRequests(String username) throws ExistFriendException {
        Player player = FindPlayerByInfo.findByUserName(username);
        int counter =1;
        if (player.getFriendsRequests().size() == 0)
            throw new ExistFriendException(" THERE ARE NO REQUESTS TO SHOW");

        for (String playerFriendRequests : player.getFriendsRequests()) {
            System.out.println(counter+". Username: "+ playerFriendRequests);
            counter++;
        }
    }

    public static void showFriends(String username) throws ExistFriendException {
        Player player = FindPlayerByInfo.findByUserName(username);
        int counter =1;

        if (player.getFriends().size() == 0)
            throw new ExistFriendException(" YOU DON'T HAVE ANY FRIENDS ");

        for (String playerFriend : player.getFriends()) {
            System.out.println(counter+". Username: "+ playerFriend);
            counter++;
        }
    }

    public static void showFriendProfile(String username, String friendUsername) throws ExistFriendException {

        Player player = FindPlayerByInfo.findByUserName(username);
        Player friend = FindPlayerByInfo.findByUserName(friendUsername);

        if (!userNameIsFriendByUsername(username, friendUsername))
            throw new ExistFriendException(friendUsername, " THIS PLAYER IS NOT IN YOU REQUEST LIST! PLEASE MAKE SURE THE USERNAME IS VALID ");

        for (String playerFriend : player.getFriends()) {
            if (playerFriend.equals(friend.getUserName())) {
                System.out.println("getUserID: " + friend.getUserID() + " Username: " + friend.getUserName() + " Name: " + friend.getName() + " LastName: " + friend.getLastName() + " Email: " + friend.getEmail() + " Phone Number: " + friend.getPhoneNum());
                break;
            }
        }

    }


    protected static boolean userNameIsFriendByUsername(String username, String friendUsername) {
        boolean result = false;
        Player player = FindPlayerByInfo.findByUserName(username);

        for (String friend : player.getFriends()) {
            if (friend.equals(friendUsername)){
                result = true;
                break;
            }
        }

        return result;
    }


    protected static boolean userNameIsInFriendRequestByUsername(String username, String friendUsername) {
        boolean result = false;
        Player player = FindPlayerByInfo.findByUserName(username);

        for (String friendsRequest : player.getFriendsRequests()) {
            if (friendsRequest.equals(friendsRequest)){
                result = true;
                break;
            }
        }

        return result;
    }


}
