package Model.PlatoModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Player extends User {
    public static ArrayList<Player> players = new ArrayList<Player>();
    private double coin;
    private LocalDate registerDate;
    private ArrayList<Player> friends;
    private ArrayList<Player> friendsRequests;
    private ArrayList<PlayerLog> playerLog;
    protected ArrayList<Integer> suggestedGamesID;
    private ArrayList<Message> receivedMessages;
    private ArrayList<String> favoritesGamesName;
    private String lastPlayed;

    public Player(String name, String lastName, int userID, String userName, String password, String email, String phoneNum) {
        super(name, lastName, userID, userName, password, email, phoneNum);
        suggestedGamesID = new ArrayList<>();
        friendsRequests = new ArrayList<>();
        receivedMessages = new ArrayList<>();
        this.lastPlayed = "";
        this.registerDate = LocalDate.now();
        favoritesGamesName = new ArrayList<>();
        friends = new ArrayList<>();
        PlayerLog.addNewPlayerLog(new PlayerLog(userID, "DotsAndBoxes"));
        PlayerLog.addNewPlayerLog(new PlayerLog(userID, "BattleSea"));
    }

    public static void AddNewPlayer(Player player) {
        players.add(player);
        User.addNewUser(player);
    }

    public ArrayList<Player> getFriends() {
        return friends;
    }


    private void editProfile(String field, String string) {

    }

    private void addNewFriend(Player player) {

    }

    public void setFriends(ArrayList<Player> friends) {
        this.friends = friends;
    }

    public ArrayList<Player> getFriendsRequests() {
        return friendsRequests;
    }

    public void setFriendsRequests(ArrayList<Player> friendsRequests) {
        this.friendsRequests = friendsRequests;
    }

    public ArrayList<String> getFavoritesGamesName() {
        return favoritesGamesName;
    }

    public void setFavoritesGamesName(ArrayList<String> favoritesGamesName) {
        this.favoritesGamesName = favoritesGamesName;
    }

    private void addGameLog(PlayerLog gameLog) {
        playerLog.add(gameLog);

    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }

    public ArrayList<Message> getReceivedMessages() {
        return receivedMessages;
    }

    public void setReceivedMessages(ArrayList<Message> receivedMessages) {
        this.receivedMessages = receivedMessages;
    }

    public static ArrayList<Player> getPlayers() {
        return players;
    }



    public ArrayList<Integer> getSuggestedGamesID() {
        return suggestedGamesID;
    }

    public ArrayList<PlayerLog> getPlayerLog() {
        return playerLog;
    }

    public void setPlayerLog(ArrayList<PlayerLog> playerLog) {
        this.playerLog = playerLog;
    }

    public String getLastPlayed() {
        return lastPlayed;
    }

    public void setLastPlayed(String lastPlayed) {
        this.lastPlayed = lastPlayed;
    }
}
