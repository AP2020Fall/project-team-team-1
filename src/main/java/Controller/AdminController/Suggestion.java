package Controller.AdminController;

import Controller.Exception.ExistPlayerException;
import Controller.Exception.ExistSuggestionException;
import Controller.PlayerController.FindPlayerByInfo;
import Model.PlatoModel.Player;

import java.util.Random;

public class Suggestion {
    public static void addSuggestion(String input) throws ExistPlayerException {
        String[] inputSpilt = input.split("\\s");
        Player player = FindPlayerByInfo.findByUserName(inputSpilt[0]);
        if (player == null)
            throw new ExistPlayerException(inputSpilt[0]," Is not Exist in Players List");

        Model.PlatoModel.Suggestion suggestion = new Model.PlatoModel.Suggestion(randomUserId(10000,10100),player,inputSpilt[1]);
        Model.PlatoModel.Suggestion.addNewSyggestion(suggestion);

    }

    public static void showSuggestion() throws ExistSuggestionException {
        if (Model.PlatoModel.Suggestion.getAllSuggestions().size()==0)
            throw new ExistSuggestionException("There is no Suggestion for show");

        for (Model.PlatoModel.Suggestion allSuggestion : Model.PlatoModel.Suggestion.getAllSuggestions()) {
            System.out.println("Suggestion ID: "+allSuggestion.getSuggestionID()+" Suggested Player: "+allSuggestion.getPlayerName().getUserName()+" Suggested Game: "+allSuggestion.getSuggestedGame());
        }
    }

    public static void removeSuggestion(String suggestionID) throws ExistSuggestionException {
        Model.PlatoModel.Suggestion suggestion = findSuggestionBySuggestionID(suggestionID);
        if (suggestion == null)
            throw new ExistSuggestionException("There is no Suggestion for Delete with ID");
        Model.PlatoModel.Suggestion.getAllSuggestions().remove(suggestion);
        //Model.PlatoModel.Suggestion.saveInJsonFile();
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
