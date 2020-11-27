package Model.PlatoModel;

import java.util.ArrayList;

public class Suggestion {
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
    }


}
