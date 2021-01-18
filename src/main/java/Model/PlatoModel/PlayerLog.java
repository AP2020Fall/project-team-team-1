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
import java.util.Base64;
import java.util.Random;
import java.util.Scanner;

public class PlayerLog {
    private static final File playerLogsFile = new File("src\\main\\java\\Model\\Database\\PlayerLog.json");

    public static ArrayList<PlayerLog> playerLogs = new ArrayList<PlayerLog>();
    private int logID ;
    private int numberOfGamePlayed;
    private String gameName;
    private String username;
    private int numberOfWins;
    private long takenScore;
    private int numberOfLoses;

    public PlayerLog(String string, String gameName) {
        this.logID = randomPlayerLogId(700,900);
        this.gameName = gameName;
        this.username = string;
        this.numberOfGamePlayed = 0;
        this.numberOfWins = 0;
        this.numberOfLoses = 0;
        this.takenScore = 0;
    }

    public String getGameName() {
        return gameName;
    }

    public String getUsername() {
        return username;
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

    private int randomPlayerLogId(int min , int max){
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
    public static void addNewPlayerLog(PlayerLog playerLog) {
        playerLogs.add(playerLog);
        try {
            DataBase.save(playerLogs, playerLogsFile);
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
            DataBase.save(playerLogs, playerLogsFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadFromJsonFile() {
        Base64.Decoder decoder = Base64.getDecoder();

        if (!playerLogsFile.exists())
            return;
        StringBuilder read = new StringBuilder();
        try {
            Scanner myReader = new Scanner(playerLogsFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                byte[] bytes = decoder.decode(data);
                read.append(new String(bytes));
            }
            myReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Type type = new TypeToken<ArrayList<PlayerLog>>() {
        }.getType();
        ArrayList<PlayerLog> output = new Gson().fromJson(String.valueOf(read), type);
        playerLogs.clear();
        playerLogs.addAll(output);
    }

    @Override
    public String toString() {
        return "PlayerLog{" +
                "logID=" + logID +
                ", numberOfGamePlayed=" + numberOfGamePlayed +
                ", gameName='" + gameName + '\'' +
                ", userId=" + username +
                ", numberOfWins=" + numberOfWins +
                ", takenScore=" + takenScore +
                ", numberOfLoses=" + numberOfLoses +
                '}';
    }
}
