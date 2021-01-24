package Server.Controller.AdminController;


import Server.Controller.Exception.Plato.NotNullMessageException;

public class Message {

    public static void sendMassage(String text) throws NotNullMessageException {
        Server.Model.PlatoModel.Message message = new Server.Model.PlatoModel.Message(text);
        Server.Model.PlatoModel.Message.addNewMessage(message);
        if (text.isEmpty()){
            throw new NotNullMessageException("Please enter a text !");
        }
    }

    public static String showPlayerMassage() {
        StringBuilder showMessage = new StringBuilder();
        for (Server.Model.PlatoModel.Message message : Server.Model.PlatoModel.Message.getMessages()) {
            showMessage.append("Message Id : ").append(message.getMassageID()).append(" Text : ").append(message.getText()).append("$");
        }
        return String.valueOf(showMessage);
    }

}
