package Server.Model.PlatoModel;

import Server.Model.DataBase.DataBase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Message {
    private static final File messageFile = new File("src\\main\\resources\\DataBase\\Message.json");

    private static ArrayList<Message> messages = new ArrayList<>();
    private int massageID ;
    private String text;
    private LocalDate date;
    private LocalTime time;

    public Message(String text) {
        this.text = text;
        this.massageID = randomMessageId(2000,2500);
        this.date = LocalDate.now();
        this.time = LocalTime.now();
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }


    public static ArrayList<Message> getMessages() {
        return messages;
    }

    public static void addNewMessage(Message message) {
        if (! message.getText().isEmpty()){
            messages.add(message);
            try {
                DataBase.save(messages, messageFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private int randomMessageId(int min , int max){
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public static void saveInJsonFile(ArrayList arrayList,File file) throws IOException {
        if (file.exists()){
            file.delete();
        }
        file.createNewFile();

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        FileWriter fWriter = new FileWriter(file,true);
        fWriter.write(gson.toJson(arrayList));
        fWriter.close();
    }

    public static void loadFromJsonFile() {
        if (!messageFile.exists())
            return;
        StringBuilder read = new StringBuilder();
        try {
            Scanner myReader = new Scanner(messageFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                read.append(data);
            }
            myReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Type type = new TypeToken<ArrayList<Message>>() {
        }.getType();
        ArrayList<Message> output = new Gson().fromJson(String.valueOf(read), type);
        messages.clear();
        messages.addAll(output);
    }


    public int getMassageID() {
        return massageID;
    }

    public String getText() {
        return text;
    }


    @Override
    public String toString() {
        return "Message{" +
                "massageID=" + massageID +
                ", text='" + text + '\'' +
                '}';
    }
}
