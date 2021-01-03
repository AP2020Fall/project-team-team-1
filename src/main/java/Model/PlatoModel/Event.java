package Model.PlatoModel;

import Model.DataBase.DataBase;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Event {
    private static final File eventFile = new File("src\\main\\java\\Model\\Database\\Event.json");

    public static ArrayList<Event> events = new ArrayList<Event>();
    private ArrayList<String> playersInThisEvent;
//    private String gameName;
//    private LocalDate startDate;
//    private LocalDate endDate;
//    private int score;
//    private int eventID;
//    private String comment;
    private SimpleStringProperty gameName;
    private SimpleStringProperty comment;
    private SimpleIntegerProperty eventID;
    private SimpleIntegerProperty score;
    private SimpleObjectProperty<LocalDate> startDate;
    private SimpleObjectProperty<LocalDate> endDate;

    public Event(int eventID,String gameName, LocalDate startDate, LocalDate endDate, int score ,String comment) {
//        this.eventID = makeEventID();
        this.eventID=new SimpleIntegerProperty(eventID);
        this.gameName =new SimpleStringProperty(gameName);
        this.startDate =new SimpleObjectProperty<>(startDate);
        this.endDate =new SimpleObjectProperty<>(endDate);
        this.score =new SimpleIntegerProperty(score);
        this.comment = new SimpleStringProperty(comment);
        playersInThisEvent = new ArrayList<String>();
    }

//    public String getComment() {
//        return comment;
//    }
//
//    public void setComment(String comment) {
//        this.comment = comment;
//    }
//
//    public static ArrayList<Event> getEvents() {
//        return events;
//    }
//
//    public static void setEvents(ArrayList<Event> events) {
//        Event.events = events;
//    }
//
//    public String getGameName() {
//        return gameName;
//    }
//
//    public void setGameName(String gameName) {
//        this.gameName = gameName;
//    }
//
//    public LocalDate getStartDate() {
//        return startDate;
//    }
//
//    public void setStartDate(LocalDate startDate) {
//        this.startDate = startDate;
//    }
//
//    public LocalDate getEndDate() {
//        return endDate;
//    }
//
//    public void setEndDate(LocalDate endDate) {
//        this.endDate = endDate;
//    }
//
//    public long getScore() {
//        return score;
//    }
//
//    public void setScore(int score) {
//        this.score = score;
//    }
//
//    public int getEventID() {
//        return eventID;
//    }
//
//    public void setEventID(int eventID) {
//        this.eventID = eventID;
//    }

    public ArrayList<String> getPlayersInThisEvent() {
        return playersInThisEvent;
    }

    public void setPlayersInThisEvent(ArrayList<String> playersInThisEvent) {
        this.playersInThisEvent = playersInThisEvent;
    }


    public static void addNewEvent(Event event) {
        events.add(event);
        try {
            DataBase.save(events, eventFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File getEventFile() {
        return eventFile;
    }

    public static ArrayList<Event> getEvents() {
        return events;
    }

    public static void setEvents(ArrayList<Event> events) {
        Event.events = events;
    }

    public String getGameName() {
        return gameName.get();
    }

    public SimpleStringProperty gameNameProperty() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName.set(gameName);
    }

    public String getComment() {
        return comment.get();
    }

    public SimpleStringProperty commentProperty() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment.set(comment);
    }

    public int getEventID() {
        return eventID.get();
    }

    public SimpleIntegerProperty eventIDProperty() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID.set(eventID);
    }

    public int getScore() {
        return score.get();
    }

    public SimpleIntegerProperty scoreProperty() {
        return score;
    }

    public void setScore(int score) {
        this.score.set(score);
    }



    public SimpleObjectProperty<LocalDate> startDateProperty() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate.set(startDate);
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate.set(endDate);
    }

    public Object getEndDate() {
        return endDate.get();
    }

    public SimpleObjectProperty<LocalDate> endDateProperty() {
        return endDate;
    }


    //    private int makeEventID() {
//        int id = 1;
//        if (events.size() == 0) {
//            return id;
//        } else
//            id = events.get(events.size() - 1).getEventID();
//        id += 1;
//        return id;
//    }

    public static void saveInJsonFile() {
        try {
            DataBase.save(events, eventFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadFromJsonFile() throws FileNotFoundException {
        if (!eventFile.exists())
            return;
        StringBuilder read = new StringBuilder();
        try {
            Scanner myReader = new Scanner(eventFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                read.append(data);
            }
            myReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Type type = new TypeToken<ArrayList<Event>>() {
        }.getType();
        ArrayList<Event> output = new Gson().fromJson(String.valueOf(read), type);
        events.clear();
        events.addAll(output);
    }

    @Override
    public String toString() {
        return "Event{" +
                "playersInThisEvent=" + playersInThisEvent +
                ", gameName='" + gameName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", score=" + score +
                ", eventID=" + eventID +
                ", comment='" + comment + '\'' +
                '}';
    }
}
