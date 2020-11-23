package Controller.AdminController;

import Controller.CompetencyController.Existence;
import Controller.CompetencyController.Validation;
import Model.PlatoModel.Admin;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Event {
    public static void addEvent(String input) {
        String[] inputSpilt = input.split("\\s");
        //Model.PlatoModel.Event.addNewEvent(new Model.PlatoModel.Event());

    }

    public static void showEvent() {
        eventDateChecker();
        for (Model.PlatoModel.Event event : Model.PlatoModel.Event.events) {
            System.out.println("EventId: " + event.getEventID() + "Game name: " + event.getGameName() + "Start date: " + event.getStartDate() + "End date: " + event.getEndDate() + "Score: " + event.getScore());
        }
    }

    public static void editEvent(String eventID, String field, String input) {
        eventDateChecker();
        if (!Existence.checkEventExistence(Integer.parseInt(eventID))) {
            System.out.println("There is no Event with this it");
        } else {
            if (field.trim().equalsIgnoreCase("GameName")) {
                editGameName(eventID, input);
            } else if (field.trim().equalsIgnoreCase("StartDate")) {
                editStartDate(eventID, input);
            } else if (field.trim().equalsIgnoreCase("EndDate")) {
                editEndDate(eventID, input);
            } else if (field.trim().equalsIgnoreCase("Score")) {
                editScore(eventID, input);
            } else
                System.out.println("Field for edit is InValid !");
        }

    }

    public static void removeEvent(String eventID) {
        if (Existence.checkEventExistence(Integer.parseInt(eventID))) {
            Model.PlatoModel.Event.events.remove(eventFinderByEventID(eventID));
        }
    }

    public static void eventDateChecker() {
        ArrayList<Model.PlatoModel.Event> listForDelete = new ArrayList<>();
        for (Model.PlatoModel.Event event : Model.PlatoModel.Event.getEvents()) {
            if (event.getStartDate().isAfter(event.getEndDate())) {
                listForDelete.add(event);
            }
        }

        if (listForDelete.size() != 0) {
            for (Model.PlatoModel.Event eventForDelete : listForDelete) {
                removeEvent(String.valueOf(eventForDelete.getEventID()));
            }
        }


    }

    private static Model.PlatoModel.Event eventFinderByEventID(String eventID) {
        Model.PlatoModel.Event resultEvent = null;
        for (Model.PlatoModel.Event event : Model.PlatoModel.Event.getEvents()) {
            if (event.getEventID() == Integer.parseInt(eventID)) {
                resultEvent = event;
                break;
            }
        }
        return resultEvent;
    }

    /****************************************************EditMethods****************************************************/

    protected static void editGameName(String eventID ,String input) {
        Model.PlatoModel.Event event = eventFinderByEventID(eventID);
        if (input.equalsIgnoreCase("dotsAndBoxes")||input.equalsIgnoreCase("battleSea")){
            event.setGameName(input);
        }else {
            System.out.println("game dose not Exist!");
        }
    }

    protected static void editStartDate(String eventID ,String input) {
        Model.PlatoModel.Event event = eventFinderByEventID(eventID);
        boolean pass=true;
        LocalDate startDate = LocalDate.parse(input);

        /*********EXSEPTIONs FOR MAKE PASS FALSE*********/

        pass = Validation.dateIsValid(input);
        //todo exseption handeling

        pass = startDate.isAfter(LocalDate.now());
        //todo exseption handeling

        pass = startDate.isBefore(event.getEndDate());
        //todo exseption handeling

        if (pass)
            event.setStartDate(startDate);

    }

    protected static void editEndDate(String eventID ,String input) {
        Model.PlatoModel.Event event = eventFinderByEventID(eventID);
        boolean pass=true;
        LocalDate startDate = LocalDate.parse(input);

    }

    protected static void editScore(String eventID ,String input) {
        boolean pass = false;

    }


}
