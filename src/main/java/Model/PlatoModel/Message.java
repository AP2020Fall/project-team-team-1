package Model.PlatoModel;

import Model.DataBase.DataBase;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Message {
    private static final File messageFile = new File("src\\main\\java\\Model\\Database\\Message.json");

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
        try {
            DataBase.save(messages,messageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void saveInJsonFile() {
        try {
            DataBase.save(messages,messageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
