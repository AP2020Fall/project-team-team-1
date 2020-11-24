package Controller.PlayerController;

import Model.PlatoModel.Player;

public class Suggestion {
    public static void showSuggestion(String username) {
        Player player = FindPlayerByInfo.findByUserName(username);
        for (Integer ID : player.getSuggestedGamesID()) {
            Model.PlatoModel.Suggestion suggestion = Controller.AdminController.Suggestion.findSuggestionBySuggestionID(String.valueOf(ID));
            System.out.println("suggestion Id : " + suggestion.getSuggestionID() + "suggested game :" + suggestion.getSuggestedGame());
        }

    }

    public static void playSuggestedGame(String userName, String suggestionId) {
        Player player = FindPlayerByInfo.findByUserName(userName);
        Model.PlatoModel.Suggestion suggestion = Controller.AdminController.Suggestion.findSuggestionBySuggestionID(suggestionId);
        RunGame.findGameForRun(player.getUserName(), suggestion.getSuggestedGame(), "10");
    }
}
