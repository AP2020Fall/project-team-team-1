package Server.Controller.AdminController;

import Server.Controller.Exception.Plato.ExistPlayerException;
import Server.Controller.Exception.Plato.ExistSuggestionException;
import Server.Controller.PlayerController.FindPlayerByInfo;
import Server.Model.PlatoModel.Player;

import java.util.Random;

public class Suggestion {
    public static void addSuggestion(String input) throws ExistPlayerException {
        String[] inputSpilt = input.split("\\s");
        Player player = FindPlayerByInfo.findByUserName(inputSpilt[0]);
        if (player == null)
            throw new ExistPlayerException(inputSpilt[0]," Is not Exist in Players List");

        Server.Model.PlatoModel.Suggestion suggestion = new Server.Model.PlatoModel.Suggestion(randomUserId(10000,10400),player,inputSpilt[1]);
        Server.Model.PlatoModel.Suggestion.addNewSuggestion(suggestion);

    }

    public static String showSuggestion() throws ExistSuggestionException {
        StringBuilder showSuggestion = new StringBuilder();
        if (Server.Model.PlatoModel.Suggestion.getAllSuggestions().size()==0)
            throw new ExistSuggestionException("There is no Suggestion for show");

        for (Server.Model.PlatoModel.Suggestion allSuggestion : Server.Model.PlatoModel.Suggestion.getAllSuggestions()) {
            showSuggestion.append("Suggestion ID: ").append(allSuggestion.getSuggestionID()).append(" Suggested Player: ").append(allSuggestion.getPlayerName().getUserName()).append(" Suggested Game: ").append(allSuggestion.getSuggestedGame()).append("$");
        }
        return String.valueOf(showSuggestion);
    }

    public static void removeSuggestion(String suggestionID) throws ExistSuggestionException {
        Server.Model.PlatoModel.Suggestion suggestion = findSuggestionBySuggestionID(suggestionID);
        if (suggestion == null)
            throw new ExistSuggestionException("There is no Suggestion for Delete with ID");
        Server.Model.PlatoModel.Suggestion.getAllSuggestions().remove(suggestion);
    }
    public static String suggestionGameName(String suggestionID) throws ExistSuggestionException {
        Server.Model.PlatoModel.Suggestion suggestion = null;
        suggestion = findSuggestionBySuggestionID(suggestionID);
        if (suggestion == null)
           throw new ExistSuggestionException("there is no Suggestion with this ID");

        return suggestion.getSuggestedGame();
    }
    private boolean checkSuggestionExistence(String suggestionID) {
        boolean result = false;
        for (Server.Model.PlatoModel.Suggestion allSuggestion : Server.Model.PlatoModel.Suggestion.getAllSuggestions()) {
            if (allSuggestion.getSuggestionID() == Integer.parseInt(suggestionID)){
                result = true;
                break;
            }
        }
        return result;
    }

    public static Server.Model.PlatoModel.Suggestion findSuggestionBySuggestionID(String suggestionID){
        Server.Model.PlatoModel.Suggestion suggestion = null;
        for (Server.Model.PlatoModel.Suggestion allSuggestion : Server.Model.PlatoModel.Suggestion.getAllSuggestions()) {
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
