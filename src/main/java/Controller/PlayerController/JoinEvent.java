package Controller.PlayerController;

import Controller.CompetencyController.Existence;
import Controller.Exception.Plato.ExistEventException;
import Model.PlatoModel.Event;
import Model.PlatoModel.Player;

import java.security.SecureRandom;
import java.time.LocalDate;

public class JoinEvent {

    public static void joinEvent(String userName,String eventId) throws ExistEventException {

        if (!Existence.checkEventExistence(Integer.parseInt(eventId)))
            throw new ExistEventException("Can not join this event !,Make Suer about Event ID");

        for (Event event : Event.events) {
            if (event.getEventID() == Integer.parseInt(eventId)){
                event.getPlayersInThisEvent().add(userName);
            }
        }

    }
    protected static String eventGameName(String eventId) throws ExistEventException {
        Event event = null;
        event = Controller.AdminController.Event.eventFinderByEventID(eventId);
        if (event == null){
            throw new ExistEventException("There is no event for show");
        }
        return event.getGameName();
    }

    protected static String eventScore(String eventId) throws ExistEventException {
        Event event = null;
        event = Controller.AdminController.Event.eventFinderByEventID(eventId);
        if (event == null){
            throw new ExistEventException("There is no event Please Check EventId !");
        }
        return String.valueOf(event.getScore());
    }
    protected static void activeEvent(String username) throws ExistEventException {
        Controller.AdminController.Event.eventDateChecker();
        for (Model.PlatoModel.Event event : Model.PlatoModel.Event.events) {
            for (String playerEvent : event.getPlayersInThisEvent()) {
                if (playerEvent.equals(username)){
                    if (findActivity(event.getStartDate())){
                        System.out.println("EventId: " + event.getEventID() + " Game name: " + event.getGameName() + " Start date: " + event.getStartDate() + " End date: " + event.getEndDate() + " Score: " + event.getScore() + " Comment: " + event.getComment());
                    }
                }
            }
        }
    }
    private static boolean findActivity(LocalDate localDate){
        return localDate.isAfter(LocalDate.now());
    }

}
