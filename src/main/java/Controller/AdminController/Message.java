package Controller.AdminController;


import Controller.Exception.Plato.NotNullMessageException;

public class Message {

    public static void sendMassage(String text) throws NotNullMessageException {
        Model.PlatoModel.Message message = new Model.PlatoModel.Message(text);
        Model.PlatoModel.Message.addNewMessage(message);
        if (text.isEmpty()){
            throw new NotNullMessageException("Please enter a text !");
        }
    }

    public static String showPlayerMassage() {
        StringBuilder showMessage = new StringBuilder();
        for (Model.PlatoModel.Message message : Model.PlatoModel.Message.getMessages()) {
            showMessage.append("Message Id : ").append(message.getMassageID()).append(" Text : ").append(message.getText()).append("$");
        }
        return String.valueOf(showMessage);
    }

}
