package Controller.AdminController;

import Controller.CompetencyController.Existence;
import Controller.CompetencyController.Validation;

import java.time.LocalDate;
import java.util.ArrayList;

public class Event {
    public boolean addEvent(String input) {
        boolean pass = true;
        String[] inputSpilt = input.split("\\s");
        LocalDate startDate = LocalDate.parse(inputSpilt[1]);
        LocalDate endDate = LocalDate.parse(inputSpilt[2]);
        if (startDate.isAfter(endDate)) {
            pass = false;
            return false;
        }
        if (pass) {
//            if (Existence.checkEventExistence(Integer.parseInt(inputSpilt[0]))){
//                pass = false;
//            return false;
//            }

            if (pass) {
                Model.PlatoModel.Event.addNewEvent(new Model.PlatoModel.Event(inputSpilt[0], startDate, endDate, Long.parseLong(inputSpilt[3])));
                return true;
            }

        }
        return false;

    }

    public static void showEvent() {
        eventDateChecker();
        for (Model.PlatoModel.Event event : Model.PlatoModel.Event.events) {
            if (event.getStartDate().isBefore(LocalDate.now()) || event.getStartDate().equals(LocalDate.now()))
                System.out.println("EventId: " + event.getEventID() + "Game name: " + event.getGameName() + "Start date: " + event.getStartDate() + "End date: " + event.getEndDate() + "Score: " + event.getScore());
        }
    }

    public void editEvent(String eventID, String field, String input) {
        eventDateChecker();
        Model.PlatoModel.Event event = eventFinderByEventID(eventID);

        if (event == null) {
            //todo exception handling

        }

        if (!Existence.checkEventExistence(Integer.parseInt(eventID))) {
            System.out.println("There is no Event with this it");
        } else {
            if (field.trim().equalsIgnoreCase("GameName")) {
                editGameName(event, input);
            } else if (field.trim().equalsIgnoreCase("StartDate")) {
                editStartDate(event, input);
            } else if (field.trim().equalsIgnoreCase("EndDate")) {
                editEndDate(event, input);
            } else if (field.trim().equalsIgnoreCase("Score")) {
                editScore(event, input);
            } else
                System.out.println("Field for edit is InValid !");
        }

    }
    public void removeEventByAdminFromView(String eventID){
        removeEvent(eventID);
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
        //todo if we have waiting list for events we should put it here
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

    protected static void editGameName(Model.PlatoModel.Event event, String input) {
        if (input.equalsIgnoreCase("dotsAndBoxes") || input.equalsIgnoreCase("battleSea")) {
            event.setGameName(input);
        } else {
            System.out.println("game dose not Exist!");
        }
    }

    protected static void editStartDate(Model.PlatoModel.Event event, String input) {
        boolean pass = true;
        LocalDate startDate = LocalDate.parse(input);

        /*********EXCEPTION FOR MAKE PASS FALSE*********/

        pass = Validation.dateIsValid(input);
        //todo exception handling

        pass = startDate.isAfter(LocalDate.now());
        //todo exception handling

        pass = startDate.isBefore(event.getEndDate());
        //todo exception handling

        if (pass)
            event.setStartDate(startDate);

    }

    protected static void editEndDate(Model.PlatoModel.Event event, String input) {
        boolean pass = true;
        LocalDate endDate = LocalDate.parse(input);

        /*********EXCEPTION FOR MAKE PASS FALSE*********/

        pass = Validation.dateIsValid(input);
        //todo exception handling

        pass = endDate.isAfter(event.getStartDate());
        //todo exception handling

        if (pass)
            event.setEndDate(endDate);
    }

    protected static void editScore(Model.PlatoModel.Event event, String input) {
        event.setScore(Long.parseLong(input));
    }


}
