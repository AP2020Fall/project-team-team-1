package Controller.PlayerController;

import Model.PlatoModel.Message;

import java.time.format.DateTimeFormatter;

public class ViewPlatoBotMessages {

    public static String viewBotMessages ()  {
        StringBuilder viewBotMessages = new StringBuilder();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        for (Message message : Message.getMessages()) {
            viewBotMessages.append(message.getMassageID()).append(" : ").append(message.getText()).append("    ,").append(message.getDate()).append(" , ").append(message.getTime().format(dtf)).append("$");
        }
        return String.valueOf(viewBotMessages);

    }
}
