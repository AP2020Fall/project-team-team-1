package Model.PlatoModel;

import java.util.ArrayList;

public class Games {
    private static ArrayList<Games> games = new ArrayList<Games>();
    private int gameID;
    private String gameName;

    public Games(int gameID, String gameName) {
        this.gameID = gameID;
        this.gameName = gameName;
    }

    public static ArrayList<Games> getGames() {
        return games;
    }

    public static void setGames(ArrayList<Games> games) {
        Games.games = games;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
}
