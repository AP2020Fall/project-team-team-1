package Controller.PlayerController;

import Controller.AdminController.Event;
import Controller.CompetencyController.Existence;
import Controller.Exception.ExistEventException;

public class JoinEvent {

    public static void playEvent(String userName, String gameName, String eventId) throws ExistEventException {
        if (!Existence.checkEventExistence(Integer.parseInt(eventId)))
            throw new ExistEventException("Can not join this event !,Make Suer about Event ID");

            Model.PlatoModel.Event event = Event.eventFinderByEventID(eventId);
            RunGame.findGameForRun(userName, gameName, String.valueOf(event.getScore()));


    }
//    private void giveWinnerScore(String username){
//
//    }
//    private void giveWinnerCoin(String username){
//
//    }

}
