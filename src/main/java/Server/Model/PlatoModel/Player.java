package Server.Model.PlatoModel;

import Server.Model.DataBase.DataBase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Player extends User {
    private static final File playerFile = new File("src\\main\\resources\\DataBase\\Player.json");

    public static ArrayList<Player> players = new ArrayList<Player>();
    private LocalDate registerDate;
    private ArrayList<String> friends;
    private ArrayList<String> friendsRequests;
    private ArrayList<PlayerLog> playerLog;
    protected ArrayList<Integer> suggestedGamesID;
    //private ArrayList<Message> receivedMessages;
    private ArrayList<String> favoritesGamesName;
    private ArrayList<String> lastPlayed;
    private ArrayList<String> playersWhoReportMe;
    private boolean activation;
    private boolean remember;
    private String profileURL;
    private String bio;
    private String requestForGame;

    public Player(String name, String lastName, int userID, String userName, String password, String email, String phoneNum) {
        super(name, lastName, userID, userName, password, email, phoneNum);
        this.suggestedGamesID = new ArrayList<>();
        this.friendsRequests = new ArrayList<>();
        //receivedMessages = new ArrayList<>();
        this.lastPlayed = new ArrayList<>();
        this.registerDate = LocalDate.now();
        this.favoritesGamesName = new ArrayList<>();
        this.friends = new ArrayList<>();
        this.playerLog = new ArrayList<>();
        this.playersWhoReportMe = new ArrayList<>();
        this.activation = true;
        this.remember = false;
        this.profileURL = "src\\main\\resources\\Images\\default-profile.png";
        this.bio = "Its Simple Bio ...";
        this.requestForGame = "NO";
    }

    public static void AddNewPlayer(Player player) {
        players.add(player);
        User.addNewUser(player);

        player.playerLog.add(new PlayerLog(player.getUserName(), "DotsAndBoxes"));
        player.playerLog.add(new PlayerLog(player.getUserName(), "BattleShip"));


        try {
            DataBase.save(players, playerFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }

    public ArrayList<String> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<String> friends) {
        this.friends = friends;
    }

    public String getProfileURL() {
        return profileURL;
    }

    public void setProfileURL(String profileURL) {
        this.profileURL = profileURL;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public ArrayList<String> getFriendsRequests() {
        return friendsRequests;
    }

    public void setFriendsRequests(ArrayList<String> friendsRequests) {
        this.friendsRequests = friendsRequests;
    }

    public String getRequestForGame() {
        return requestForGame;
    }

    public void setRequestForGame(String requestForGame) {
        this.requestForGame = requestForGame;
    }

    public ArrayList<String> getPlayersWhoReportMe() {
        return playersWhoReportMe;
    }

    public void setPlayersWhoReportMe(ArrayList<String> playersWhoReportMe) {
        this.playersWhoReportMe = playersWhoReportMe;
    }

    public boolean isActivation() {
        return activation;
    }

    public void setActivation(boolean activation) {
        this.activation = activation;
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

    public ArrayList<String> getLastPlayed() {
        return lastPlayed;
    }

    public void setLastPlayed(ArrayList<String> lastPlayed) {
        this.lastPlayed = lastPlayed;
    }

    public static void saveInJsonFile() {

        User.saveInJsonFile();

        try {
            DataBase.save(players, playerFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void loadFromJsonFile() {

        if (!playerFile.exists())
            return;
        StringBuilder read = new StringBuilder();
        try {
            Scanner myReader = new Scanner(playerFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                read.append(data);
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
                "registerDate=" + registerDate +
                ", friends=" + friends +
                ", friendsRequests=" + friendsRequests +
                ", playerLog=" + playerLog +
                ", suggestedGamesID=" + suggestedGamesID +
                ", favoritesGamesName=" + favoritesGamesName +
                ", lastPlayed=" + lastPlayed +
                ", playersWhoReportMe=" + playersWhoReportMe +
                ", activation=" + activation +
                '}';
    }
}
