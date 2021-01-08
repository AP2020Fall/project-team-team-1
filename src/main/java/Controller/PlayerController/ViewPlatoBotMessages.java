package Controller.PlayerController;

import Model.PlatoModel.Message;

public class ViewPlatoBotMessages {

    public static String viewBotMessages ()  {
        StringBuilder viewBotMessages = new StringBuilder();

        for (Message message : Message.getMessages()) {
            viewBotMessages.append(message.getMassageID()).append(" : ").append(message.getText()).append("    ,").append(message.getDate()).append(" , ").append(message.getTime().getHour()).append(":").append(message.getTime().getMinute()).append("$");
        }
        return String.valueOf(viewBotMessages);

    }


}
