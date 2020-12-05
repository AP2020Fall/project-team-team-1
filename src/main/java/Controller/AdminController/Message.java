package Controller.AdminController;


import Controller.Exception.ExistPlayerException;
import Controller.PlayerController.FindPlayerByInfo;
import Model.PlatoModel.Player;

public class Message {

    public static void sendMassage(String username ,String text) throws ExistPlayerException {
        Player player = FindPlayerByInfo.findByUserName(username);
        if (player == null)
            throw new ExistPlayerException(username + " Is Invalid");
        Model.PlatoModel.Message message = new Model.PlatoModel.Message(text,player);
        Model.PlatoModel.Message.addNewMessage(message);

        player.getReceivedMessages().add(message);
        //Player.saveInJsonFile();

    }

    public static void showPlayerMassage(String username) throws ExistPlayerException {
        Player player = FindPlayerByInfo.findByUserName(username);
        if (player == null)
            throw new ExistPlayerException(username + " Is Invalid");
        for (Model.PlatoModel.Message playerReceivedMessage : player.getReceivedMessages()) {
            System.out.println(playerReceivedMessage);
        }
    }

}
