package Model.PlatoModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Event {
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
    }
    
    private int makeEventID() {
        counter++;
        return counter;
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
