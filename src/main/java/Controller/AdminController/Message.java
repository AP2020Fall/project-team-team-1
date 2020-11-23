package Controller.AdminController;


import Controller.PlayerController.FindPlayerByInfo;
import Model.PlatoModel.Player;

public class Message {

    public static Model.PlatoModel.Message sendMassage(String username ,String text){
        Player player = FindPlayerByInfo.findByUserName(username);
        Model.PlatoModel.Message message = new Model.PlatoModel.Message(text,player);

        return message;
    }

    public static void showPlayerMassage(String username){

    }

}
