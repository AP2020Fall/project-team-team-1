package Controller.PlayerController;

import Controller.CompetencyController.Existence;
import Controller.Exception.Plato.ExistEventException;
import Model.PlatoModel.Event;
import Model.PlatoModel.Player;

import java.io.IOException;
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
    protected static String activeEvent(String username) throws ExistEventException, IOException {
        StringBuilder activeEvent = new StringBuilder();
        Controller.AdminController.Event.eventDateChecker();
        for (Model.PlatoModel.Event event : Model.PlatoModel.Event.events) {
            for (String playerEvent : event.getPlayersInThisEvent()) {
                if (playerEvent.equals(username)){
                    LocalDate start = LocalDate.parse(event.getStartDate().toString());
                    if (findActivity(start)){
                        activeEvent.append("EventId: ").append(event.getEventID()).append(" Game name: ").append(event.getGameName()).append(" Start date: ").append(event.getStartDate()).append(" End date: ").append(event.getEndDate()).append(" Score: ").append(event.getScore()).append(" Comment: ").append(event.getComment()).append("$");
                    }
                }
            }
        }
        return String.valueOf(activeEvent);
    }
    private static boolean findActivity(LocalDate localDate){
        return localDate.isBefore(LocalDate.now());
    }

}
