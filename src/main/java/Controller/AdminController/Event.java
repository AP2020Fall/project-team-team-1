package Controller.AdminController;

import Controller.CompetencyController.Existence;

import java.util.ArrayList;

public class Event {
    public static void addEvent(String input) {
        String[] inputSpilt = input.split("\\s");
        //Model.PlatoModel.Event.addNewEvent(new Model.PlatoModel.Event());

    }

    public static void showEvent() {
        eventDateChecker();
        for (Model.PlatoModel.Event event : Model.PlatoModel.Event.events) {
            System.out.println("EventId: "+event.getEventID()+"Game name: "+event.getGameName()+"Start date: "+event.getStartDate()+"End date: "+event.getEndDate()+"Score: "+event.getScore());
        }
    }

    public static void editEvent(String eventID,String field, String input) {
        eventDateChecker();
        if (!Existence.checkEventExistence(Integer.parseInt(eventID))){
            System.out.println("There is no Event with this it");
        }else {

        }

    }

    public static void removeEvent(String eventID) {
        if (Existence.checkEventExistence(Integer.parseInt(eventID))){
            Model.PlatoModel.Event.events.remove(eventFinderByEventID(eventID));
        }
    }
    public static void eventDateChecker() {
        ArrayList<Model.PlatoModel.Event> listForDelete = new ArrayList<>();
        for (Model.PlatoModel.Event event : Model.PlatoModel.Event.getEvents()) {
            if (event.getStartDate().after(event.getEndDate())){
                listForDelete.add(event);
            }
        }

        if (listForDelete.size() != 0){
            for (Model.PlatoModel.Event eventForDelete : listForDelete) {
                removeEvent(String.valueOf(eventForDelete.getEventID()));
            }
        }


    }

    private static Model.PlatoModel.Event eventFinderByEventID(String eventID){
        Model.PlatoModel.Event resultEvent = null;
        for (Model.PlatoModel.Event event: Model.PlatoModel.Event.getEvents()) {
            if (event.getEventID() == Integer.parseInt(eventID)){
                resultEvent = event;
                break;
            }
        }
        return resultEvent;
    }


}
