package Controller.PlayerController;

import Controller.AdminController.Suggestion;
import Model.PlatoModel.Message;
import Model.PlatoModel.Player;

public class ViewPlatoBotMessages {

    public static void viewBotMessages (String username){
        Player player = FindPlayerByInfo.findByUserName(username);
        for (Message receivedMessage : player.getReceivedMessages()) {
            System.out.println(receivedMessage);
        }

    }
    public static void showAdminSuggestions (String username){
        Player player = FindPlayerByInfo.findByUserName(username);
        for (Integer ID : player.getSuggestedGamesID()) {
            System.out.println(Suggestion.findSuggestionBySuggestionID(String.valueOf(ID)));
        }
    }


}
