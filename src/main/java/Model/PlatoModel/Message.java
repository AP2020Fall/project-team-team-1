package Model.PlatoModel;

import java.util.ArrayList;

public class Message {
    private static ArrayList<Message> messages = new ArrayList<>();
    private int massageID=0;
    private String text;
    private Player receiver;

    public Message(String text, Player receiver) {
        this.text = text;
        this.receiver = receiver;
        this.massageID=massageID();
    }
    private int massageID(){
        massageID++;
        return massageID;
    }

    public static void addNewMessage(Message message){
        messages.add(message);
    }

    @Override
    public String toString() {
        return "Message{" +
                "massageID=" + massageID +
                ", text='" + text + '\'' +
                ", receiver=" + receiver +
                '}';
    }
}
