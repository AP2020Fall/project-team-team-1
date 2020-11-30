package Model.PlatoModel;

import Model.DataBase.DataBase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class PlayerLog {
    private static final File playerLogsFile = new File("src\\main\\java\\Model\\Database\\PlayerLog.json");

    public static ArrayList<PlayerLog> playerLogs = new ArrayList<PlayerLog>();
    private int logID = 0;
    private int numberOfGamePlayed;
    private String gameName;
    private int userId;
    private int numberOfWins;
    private long takenScore;
    private int numberOfLoses;
    public PlayerLog(int userId , String gameName){
        this.logID= logIdMaker();
        this.gameName = gameName;
        this.userId = userId ;
        this.numberOfGamePlayed = 0;
        this.numberOfWins = 0 ;
        this.numberOfLoses = 0 ;
        this.takenScore = 0 ;
    }

    public static ArrayList<PlayerLog> getPlayerLogs() {
        return playerLogs;
    }

    public static void setPlayerLogs(ArrayList<PlayerLog> playerLogs) {
        PlayerLog.playerLogs = playerLogs;
    }

    public int getLogID() {
        return logID;
    }

    public void setLogID(int logID) {
        this.logID = logID;
    }

    public int getNumberOfGamePlayed() {
        return numberOfGamePlayed;
    }

    public void setNumberOfGamePlayed(int numberOfGamePlayed) {
        this.numberOfGamePlayed = numberOfGamePlayed;
    }

    public int getNumberOfWins() {
        return numberOfWins;
    }

    public void setNumberOfWins(int numberOfWins) {
        this.numberOfWins = numberOfWins;
    }

    public long getTakenScore() {
        return takenScore;
    }

    public void setTakenScore(long takenScore) {
        this.takenScore = takenScore;
    }
    public int logIdMaker (){
        logID++;
        return logID;
    }
    public static void addNewPlayerLog(PlayerLog playerLog){
        playerLogs.add(playerLog);
        try {
            DataBase.save(playerLogs,playerLogsFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getNumberOfLoses() {
        return numberOfLoses;
    }

    public void setNumberOfLoses(int numberOfLoses) {
        this.numberOfLoses = numberOfLoses;
    }

    public static void saveInJsonFile() {
        try {
            DataBase.save(playerLogs,playerLogsFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void loadFromJsonFile(){
        String read = "";
        try {
            FileReader myFile1 = new FileReader(playerLogsFile);
            BufferedReader br = new BufferedReader(myFile1);
            read = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Type type = new TypeToken<ArrayList<PlayerLog>>() {}.getType();
        ArrayList<PlayerLog> output = new Gson().fromJson(read,type);
        playerLogs.clear();
        playerLogs.addAll(output);
    }

    @Override
    public String toString() {
        return "PlayerLog{" +
                "logID=" + logID +
                ", numberOfGamePlayed=" + numberOfGamePlayed +
                ", gameName='" + gameName + '\'' +
                ", userId=" + userId +
                ", numberOfWins=" + numberOfWins +
                ", takenScore=" + takenScore +
                ", numberOfLoses=" + numberOfLoses +
                '}';
    }
}
