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

    public static void showPlayerMassage() {
        for (Model.PlatoModel.Message message : Model.PlatoModel.Message.getMessages()) {
            System.out.println("Message Id : " + message.getMassageID() +" Text : " + message.getText());
        }
//        Player player = FindPlayerByInfo.findByUserName(username);
//        if (player == null)
//            throw new ExistPlayerException(username + " Is Invalid");
//        for (Model.PlatoModel.Message playerReceivedMessage : player.getReceivedMessages()) {
//            System.out.println(playerReceivedMessage);
//        }
    }

}
