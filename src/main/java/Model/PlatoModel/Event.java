package Model.PlatoModel;

import Model.DataBase.DataBase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Event {
    private static final File eventfile = new File("src\\main\\java\\Model\\Database\\Event.json");

    public static ArrayList<Event> events = new ArrayList<Event>();
    private ArrayList<Player> playersInThisEvent;
    private String gameName ;
    private LocalDate startDate;
    private LocalDate endDate;
    private long score;
    private int eventID;
    private int counter = 0;

    public Event(String gameName, LocalDate startDate, LocalDate endDate, long score) {
        this.eventID = makeEventID();
        this.gameName = gameName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.score = score;
        playersInThisEvent=new ArrayList<Player>();
    }
    private static void removeEvent(Event event){

    }

    public static ArrayList<Event> getEvents() {
        return events;
    }

    public static void setEvents(ArrayList<Event> events) {
        Event.events = events;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public ArrayList<Player> getPlayersInThisEvent() {
        return playersInThisEvent;
    }

    public void setPlayersInThisEvent(ArrayList<Player> playersInThisEvent) {
        this.playersInThisEvent = playersInThisEvent;
    }

    private void addPlayerToEvent(Player player){
        playersInThisEvent.add(player);
    }

    public static void addNewEvent(Event event){
        events.add(event);
        try {
            DataBase.save(events,eventfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private int makeEventID() {
        int id = 1;
        if (events.size()==0){
            return id;
        }else
            id=events.get(events.size()-1).getEventID();
            id+=1;
      return id;
    }
    public static void saveInJsonFile() {
        try {
            DataBase.save(events,eventfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void loadFromJsonFile(){
        String read = "";
        try {
            FileReader myFile1 = new FileReader(eventfile);
            BufferedReader br = new BufferedReader(myFile1);
            read = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Type type = new TypeToken<ArrayList<Event>>() {}.getType();
        ArrayList<Event> output = new Gson().fromJson(read,type);
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
                ", counter=" + counter +
                '}';
    }
}
