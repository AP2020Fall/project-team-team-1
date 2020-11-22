package Model.PlatoModel;

import java.util.ArrayList;

public class PlayerLog {
    public static ArrayList<PlayerLog> playerLogs = new ArrayList<PlayerLog>();
    private int logID = 0;
    private int numberOfGamePlayed;
    private String gameName;
    private int userId;
    private int numberOfWins;
    private long takenScore;
    public PlayerLog(int userId , String gameName){
        this.logID= logIdMaker();
        this.gameName = gameName;
        this.userId = userId ;
        this.numberOfGamePlayed = 0;
        this.numberOfWins = 0 ;
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
    }
}
