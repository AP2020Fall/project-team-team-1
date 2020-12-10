package Controller.PlayerController;

import Controller.AdminController.Event;
import Controller.CompetencyController.Existence;
import Controller.Exception.Plato.ExistEventException;

import java.time.LocalDate;

public class JoinEvent {

    public static void playEvent(String userName, String gameName, String eventId) throws ExistEventException {
        if (!Existence.checkEventExistence(Integer.parseInt(eventId)))
            throw new ExistEventException("Can not join this event !,Make Suer about Event ID");

            Model.PlatoModel.Event event = Event.eventFinderByEventID(eventId);
            Game.findGameForRun(userName, gameName, String.valueOf(event.getScore()));


    }
    protected static void activeEvent(String username){
        for (Model.PlatoModel.Event event : Model.PlatoModel.Event.events) {
            for (String playerEvent : event.getPlayersInThisEvent()) {
                if (playerEvent.equals(username)){
                    if (findActivity(event.getStartDate())){
                        System.out.println("EventId: " + event.getEventID() + " Game name: " + event.getGameName() + " Start date: " + event.getStartDate() + " End date: " + event.getEndDate() + " Score: " + event.getScore());
                    }
                }
            }
        }
    }
    private static boolean findActivity(LocalDate localDate){
        return !localDate.isAfter(LocalDate.now());
    }

}
