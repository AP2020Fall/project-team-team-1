package Controller.PlayerController;

import Model.PlatoModel.Message;

public class ViewPlatoBotMessages {

    public static String viewBotMessages ()  {
        StringBuilder viewBotMessages = new StringBuilder();
        int counter = 1;

        for (Message message : Message.getMessages()) {
            viewBotMessages.append(counter).append(". Message ID : ").append(message.getMassageID()).append(" Text: ").append(message.getText()).append("$");
            counter++;
        }
        return String.valueOf(viewBotMessages);

    }
//    public static void showAdminSuggestions (String username){
//        Player player = FindPlayerByInfo.findByUserName(username);
//        for (Integer ID : player.getSuggestedGamesID()) {
//            System.out.println(Suggestion.findSuggestionBySuggestionID(String.valueOf(ID)));
//        }
//    }


}
