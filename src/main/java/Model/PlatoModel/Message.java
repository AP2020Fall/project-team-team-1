package Model.PlatoModel;

import Model.DataBase.DataBase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Random;

public class Message {
    private static final File messageFile = new File("src\\main\\java\\Model\\Database\\Message.json");

    private static ArrayList<Message> messages = new ArrayList<>();
    private int massageID ;
    private String text;
    private Player receiver;

    public Message(String text, Player receiver) {
        this.text = text;
        this.receiver = receiver;
        this.massageID = randomMessageId(2000,2500);
    }

    private int massageID() {
        massageID++;
        return massageID;
    }

    public static void addNewMessage(Message message) {
        messages.add(message);
//        try {
//            DataBase.save(messages, messageFile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
    private int randomMessageId(int min , int max){
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

//    public static void saveInJsonFile() {
//        try {
//            DataBase.save(messages, messageFile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void loadFromJsonFile() {
//        if (!messageFile.exists())
//            return;
//        String read = "";
//        try {
//            FileReader myFile1 = new FileReader(messageFile);
//            BufferedReader br = new BufferedReader(myFile1);
//            read = br.readLine();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Type type = new TypeToken<ArrayList<Message>>() {
//        }.getType();
//        ArrayList<Message> output = new Gson().fromJson(read, type);
//        messages.clear();
//        messages.addAll(output);
//    }


    public int getMassageID() {
        return massageID;
    }

    public String getText() {
        return text;
    }

    public Player getReceiver() {
        return receiver;
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
