package Controller.AdminController;


import Controller.PlayerController.FindPlayerByInfo;
import Model.PlatoModel.Player;

public class Message {

    public void sendMassage(String username ,String text){
        Player player = FindPlayerByInfo.findByUserName(username);
//        if (player == null)
//            todo exception handling
        Model.PlatoModel.Message message = new Model.PlatoModel.Message(text,player);
        Model.PlatoModel.Message.addNewMessage(message);
        player.getReceivedMessages().add(message);

    }

    public void showPlayerMassage(String username){
        Player player = FindPlayerByInfo.findByUserName(username);
//        if (player == null)
//            todo exception handling
        for (Model.PlatoModel.Message playerReceivedMessage : player.getReceivedMessages()) {
            System.out.println(playerReceivedMessage);
        }

    }

}
