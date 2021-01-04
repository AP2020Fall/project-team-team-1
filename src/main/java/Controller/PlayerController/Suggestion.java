package Controller.PlayerController;

import Controller.Exception.Plato.ExistSuggestionException;
import Model.PlatoModel.Player;

import static Controller.AdminController.Suggestion.findSuggestionBySuggestionID;

public class Suggestion {
    public static String showSuggestion(String username) throws ExistSuggestionException {
        StringBuilder showSuggestion = new StringBuilder();
        Player player = FindPlayerByInfo.findByUserName(username);

        if (player.getSuggestedGamesID().size() == 0)
            throw new ExistSuggestionException(" There is not Suggestion for Show! ");

        for (Integer ID : player.getSuggestedGamesID()) {
            Model.PlatoModel.Suggestion suggestion = findSuggestionBySuggestionID(String.valueOf(ID));
            showSuggestion.append("suggestion Id : ").append(suggestion.getSuggestionID()).append(" suggested game : ").append(suggestion.getSuggestedGame()).append("$");
        }
        return String.valueOf(showSuggestion);

    }

    public static void playSuggestedGame(String userName, String suggestionId) throws ExistSuggestionException {
        Player player = FindPlayerByInfo.findByUserName(userName);
        Model.PlatoModel.Suggestion suggestion = findSuggestionBySuggestionID(suggestionId);

        if (suggestion == null)
            throw new ExistSuggestionException(" Suggestion with this ID doesn't Exist !, Make Sure about Suggestion and try Again ");

        Game.findGameForRun(player.getUserName(), suggestion.getSuggestedGame(), "10");
    }

    public static String suggestionGameName(String suggestionID) throws ExistSuggestionException {
        Model.PlatoModel.Suggestion suggestion = null;
        suggestion = findSuggestionBySuggestionID(suggestionID);
        if (suggestion == null)
            throw new ExistSuggestionException("There is no Suggestion with this ID");

        return suggestion.getSuggestedGame();
    }
    public static String findSuggestionBySuggestionIDForGameName(String suggestionID){
        Model.PlatoModel.Suggestion suggestion = null;
        for (Model.PlatoModel.Suggestion allSuggestion : Model.PlatoModel.Suggestion.getAllSuggestions()) {
            if (allSuggestion.getSuggestionID() == Integer.parseInt(suggestionID)){
                suggestion = allSuggestion;
                break;
            }
        }
        return suggestion.getSuggestedGame();
    }
}
