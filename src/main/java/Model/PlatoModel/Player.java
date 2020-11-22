package Model.PlatoModel;

import java.util.ArrayList;
import java.util.Date;

public class Player extends User {
    private static ArrayList<Player> players = new ArrayList<Player>();
    private double coin;
    private Date registerDate;
    private ArrayList<Player> friends ;
    private static ArrayList<Player> friendsRequests ;
    private ArrayList<PlayerLog> playerLog;
    protected ArrayList<Integer> suggestedGamesID ;
    private ArrayList<Message> receivedMessages;
    private ArrayList<String> favoritesGamesName ;

    public Player(String name, String lastName, int userID, String userName, String password, String email, String phoneNum) {
        super(name, lastName, userID, userName, password, email, phoneNum);
        suggestedGamesID = new ArrayList<>();
        receivedMessages = new ArrayList<>();
        favoritesGamesName = new ArrayList<>();
        PlayerLog.addNewPlayerLog( new PlayerLog(userID,"DotsAndBoxes"));
        PlayerLog.addNewPlayerLog( new PlayerLog(userID,"BattleSea"));
    }

    public ArrayList<Player> getFriends() {
        return friends;
    }


    private void editProfile(String field, String string){

    }

    private void addNewFriend(Player player){
        friends.add(player);
    }

    public ArrayList<String> getFavoritesGamesName() {
        return favoritesGamesName;
    }

    public void setFavoritesGamesName(ArrayList<String> favoritesGamesName) {
        this.favoritesGamesName = favoritesGamesName;
    }

    private void addGameLog(PlayerLog gameLog){
       playerLog.add(gameLog);

    }



}
