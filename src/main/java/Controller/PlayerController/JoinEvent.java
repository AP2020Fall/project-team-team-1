package Controller.PlayerController;

import Controller.AdminController.AdminGeneralController;
import Controller.AdminController.Event;
import Controller.CompetencyController.Existence;
import Controller.DotsAndBoxesController.Player;
import Controller.Exception.ExistEventException;

public class JoinEvent {

    public static void playEvent(String userName, String gameName, String eventId) throws ExistEventException {
        if (Existence.checkEventExistence(Integer.parseInt(eventId))) {
            Model.PlatoModel.Event event = Event.eventFinderByEventID(eventId);
            RunGame.findGameForRun(userName, gameName, String.valueOf(event.getScore()));
        } else if (!Existence.checkEventExistence(Integer.parseInt(eventId))) {
            System.out.println("Can not join this event");
        }
    }
//    private void giveWinnerScore(String username){
//
//    }
//    private void giveWinnerCoin(String username){
//
//    }

}
