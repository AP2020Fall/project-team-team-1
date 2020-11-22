package Model.PlatoModel;

import java.util.ArrayList;

public class Suggestion {
    private static ArrayList<Suggestion> allSuggestions =new ArrayList<>();
    private int suggestionID=0;
    private Player playerName;
    private Game suggestedGame;
    private Admin adminName;

    public Suggestion(Player playerName, Game suggestedGame, Admin adminName) {
        this.playerName = playerName;
        this.suggestedGame = suggestedGame;
        this.adminName = adminName;
        suggestionID=suggestionID();
        playerName.suggestedGamesID.add(suggestionID());
    }

    private int suggestionID (){
        suggestionID++;
        return suggestionID;
    }
}
