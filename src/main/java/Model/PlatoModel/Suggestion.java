package Model.PlatoModel;

import Model.DataBase.DataBase;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Suggestion {
    private static final File suggestionFile = new File("src\\main\\java\\Model\\Database\\Suggestion.json");

    private static ArrayList<Suggestion> allSuggestions =new ArrayList<>();
    private int suggestionID=0;
    private Player playerName;
    private String suggestedGame;


    public Suggestion(int suggestionID,Player playerName, String suggestedGame) {
        this.playerName = playerName;
        this.suggestedGame = suggestedGame;
        this.suggestionID=suggestionID;
        playerName.suggestedGamesID.add(suggestionID);
    }


    public static ArrayList<Suggestion> getAllSuggestions() {
        return allSuggestions;
    }

    public int getSuggestionID() {
        return suggestionID;
    }

    public Player getPlayerName() {
        return playerName;
    }

    public String getSuggestedGame() {
        return suggestedGame;
    }

    public static void addNewSyggestion(Suggestion suggestion){
        allSuggestions.add(suggestion);
        try {
            DataBase.save(allSuggestions,suggestionFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void saveInJsonFile() {
        try {
            DataBase.save(allSuggestions,suggestionFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        @Override
    public String toString() {
        return "Suggestion{" +
                "suggestionID=" + suggestionID +
                ", playerName=" + playerName +
                ", suggestedGame='" + suggestedGame + '\'' +
                '}';
    }
}
