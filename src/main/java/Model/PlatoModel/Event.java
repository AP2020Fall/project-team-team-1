package Model.PlatoModel;

import java.util.ArrayList;
import java.util.Date;

public class Event {
    public static ArrayList<Event> events = new ArrayList<Event>();
    private ArrayList<Player> playersInThisEvent;
    private Games gameName ;
    private Date startDate;
    private Date endDate;
    private long score;
    private int eventID;
    private int counter = 0;

    public Event(Games gameName, Date startDate, Date endDate, long score) {
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

    public Games getGameName() {
        return gameName;
    }

    public void setGameName(Games gameName) {
        this.gameName = gameName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
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

    private void addNewEvent(){

    }
    
    private int makeEventID() {
        counter++;
        return counter;
    }

}
