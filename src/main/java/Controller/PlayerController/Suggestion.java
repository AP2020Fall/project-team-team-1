package Controller.PlayerController;

import Controller.Exception.ExistSuggestionException;
import Model.PlatoModel.Player;

public class Suggestion {
    public static void showSuggestion(String username) throws ExistSuggestionException {
        Player player = FindPlayerByInfo.findByUserName(username);

        if (player.getSuggestedGamesID().size() == 0)
            throw new ExistSuggestionException(" There is not Suggestion for Show! ");

        for (Integer ID : player.getSuggestedGamesID()) {
            Model.PlatoModel.Suggestion suggestion = Controller.AdminController.Suggestion.findSuggestionBySuggestionID(String.valueOf(ID));
            System.out.println("suggestion Id : " + suggestion.getSuggestionID() + "suggested game :" + suggestion.getSuggestedGame());
        }

    }

    public static void playSuggestedGame(String userName, String suggestionId) throws ExistSuggestionException {
        Player player = FindPlayerByInfo.findByUserName(userName);
        Model.PlatoModel.Suggestion suggestion = Controller.AdminController.Suggestion.findSuggestionBySuggestionID(suggestionId);

        if (suggestion == null)
            throw new ExistSuggestionException(" Suggestion with this ID doesn't Exist !, Make Sure about Suggestion and try Again ");

        Game.findGameForRun(player.getUserName(), suggestion.getSuggestedGame(), "10");
    }
}
