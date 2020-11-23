package Controller.AdminController;

public class Suggestion {
    public static void addSuggestion(String input) {
        String[] inputSpilt = input.split("\\s");
//        new Model.PlatoModel.Suggestion();

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

}
