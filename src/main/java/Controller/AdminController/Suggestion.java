package Controller.AdminController;

import Controller.PlayerController.FindPlayerByInfo;
import Model.PlatoModel.Player;

import java.util.Random;

public class Suggestion {
    public static boolean addSuggestion(String input) {
        String[] inputSpilt = input.split("\\s");
        Player player = FindPlayerByInfo.findByUserName(inputSpilt[0]);
        new Model.PlatoModel.Suggestion(randomUserId(10000,10100),player,inputSpilt[1]);
        boolean result = true;
        return result;
    }

    public static void showSuggestion() {
        for (Model.PlatoModel.Suggestion allSuggestion : Model.PlatoModel.Suggestion.getAllSuggestions()) {
            System.out.println("Suggestion ID: "+allSuggestion.getSuggestionID()+" Suggested Player: "+allSuggestion.getPlayerName()+" Suggested Game: "+allSuggestion.getSuggestedGame());
        }
    }

    public static void removeSuggestion(String suggestionID) {
        Model.PlatoModel.Suggestion suggestion = null;
        suggestion = findSuggestionBySuggestionID(suggestionID);
//        if (suggestion == null)
//            //todo exception handling
        Model.PlatoModel.Suggestion.getAllSuggestions().remove(suggestion);
    }

    private boolean checkSuggestionExistence(String suggestionID) {
        boolean result = false;
        for (Model.PlatoModel.Suggestion allSuggestion : Model.PlatoModel.Suggestion.getAllSuggestions()) {
            if (allSuggestion.getSuggestionID() == Integer.parseInt(suggestionID)){
                result = true;
                break;
            }
        }
        return result;
    }

    public static Model.PlatoModel.Suggestion findSuggestionBySuggestionID(String suggestionID){
        Model.PlatoModel.Suggestion suggestion = null;
        for (Model.PlatoModel.Suggestion allSuggestion : Model.PlatoModel.Suggestion.getAllSuggestions()) {
            if (allSuggestion.getSuggestionID() == Integer.parseInt(suggestionID)){
                suggestion = allSuggestion;
                break;
            }
        }
        return suggestion;
    }
    private static int randomUserId(int min , int max){
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

}
