package Controller.PlayerController;

import Controller.AdminController.Suggestion;
import Controller.Exception.ExistPlatoMessageException;
import Model.PlatoModel.Message;
import Model.PlatoModel.Player;

public class ViewPlatoBotMessages {

    public static void viewBotMessages ()  {

        int counter = 1;

        for (Message message : Message.getMessages()) {
            System.out.println(counter+". Message ID : "+message.getMassageID()+" Text: "+ message.getText());
        }

    }
//    public static void showAdminSuggestions (String username){
//        Player player = FindPlayerByInfo.findByUserName(username);
//        for (Integer ID : player.getSuggestedGamesID()) {
//            System.out.println(Suggestion.findSuggestionBySuggestionID(String.valueOf(ID)));
//        }
//    }


}
