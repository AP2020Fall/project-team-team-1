package Controller.PlayerController;

import Controller.AdminController.Suggestion;
import Controller.Exception.ExistPlatoMessageException;
import Model.PlatoModel.Message;
import Model.PlatoModel.Player;

public class ViewPlatoBotMessages {

    public static void viewBotMessages (String username) throws ExistPlatoMessageException {
        Player player = FindPlayerByInfo.findByUserName(username);

        if (player.getReceivedMessages().size() == 0)
            throw new ExistPlatoMessageException(" There is no Message for Show! ");

        for (Message receivedMessage : player.getReceivedMessages()) {
            System.out.println(receivedMessage);
        }

    }
//    public static void showAdminSuggestions (String username){
//        Player player = FindPlayerByInfo.findByUserName(username);
//        for (Integer ID : player.getSuggestedGamesID()) {
//            System.out.println(Suggestion.findSuggestionBySuggestionID(String.valueOf(ID)));
//        }
//    }


}
