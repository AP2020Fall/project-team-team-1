package Model.PlatoModel;

import Model.DataBase.DataBase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Player extends User {
    private static final File playerFile = new File("src\\main\\java\\Model\\Database\\Player.json");

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
        playerLog= new ArrayList<>();
    }

    public static void AddNewPlayer(Player player) {
        players.add(player);
        User.addNewUser(player);

        player.playerLog.add(new PlayerLog(player.getUserName(),"DotsAndBoxes"));
        player.playerLog.add(new PlayerLog(player.getUserName(),"BattleShip"));



        try {
            DataBase.save(players, playerFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public static void saveInJsonFile() {
        User.saveInJsonFile();
//        if (playerFile.exists())
//            playerFile.delete();
        try {
            DataBase.save(players, playerFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadFromJsonFile() {
//        StringBuilder read = new StringBuilder();
        String read = "";
        try {
            Scanner myReader = new Scanner(playerFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
//                read.append(data);
                read = data;
            }
            myReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Type type = new TypeToken<ArrayList<Player>>() {
        }.getType();
        ArrayList<Player> output = new Gson().fromJson(String.valueOf(read), type);
        players.clear();
        players.addAll(output);

    }


    @Override
    public String toString() {
        return "Player{" +
                "coin=" + coin +
                ", registerDate=" + registerDate +
                ", friends=" + friends +
                ", friendsRequests=" + friendsRequests +
                ", playerLog=" + playerLog +
                ", suggestedGamesID=" + suggestedGamesID +
                ", favoritesGamesName=" + favoritesGamesName +
                ", lastPlayed='" + lastPlayed + '\'' +
                '}';
    }
}
