package Controller.AdminController;

import Controller.CompetencyController.Existence;
import Controller.CompetencyController.Validation;
import Controller.Exception.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class Event {
    public static void addEvent(String input) throws StartDatesException {

        String[] inputSpilt = input.split("\\s");
        LocalDate startDate = LocalDate.parse(inputSpilt[1]);
        LocalDate endDate = LocalDate.parse(inputSpilt[2]);
        if (startDate.isBefore(LocalDate.now())) {
            throw new StartDatesException("Start Date Must be after than Today");

        }
        if (startDate.isAfter(endDate)) {
            throw new StartDatesException("Start Date Must be before than End Date");
        }

        Model.PlatoModel.Event.addNewEvent(new Model.PlatoModel.Event(inputSpilt[0], startDate, endDate, Long.parseLong(inputSpilt[3])));


    }

    public static void showEvent() throws ExistEventException {
        eventDateChecker();
        if (Model.PlatoModel.Event.getEvents().size() == 0)
            throw new ExistEventException("There is no Event for show!");
        for (Model.PlatoModel.Event event : Model.PlatoModel.Event.events) {
            if (event.getStartDate().isBefore(LocalDate.now()) || event.getStartDate().equals(LocalDate.now()))
                System.out.println("EventId: " + event.getEventID() + "Game name: " + event.getGameName() + "Start date: " + event.getStartDate() + "End date: " + event.getEndDate() + "Score: " + event.getScore());

        }
    }

    public static void editEvent(String input) throws InvalidDateException, ExistEventException, InvalidFieldException, StartDatesException {
        eventDateChecker();
        String[] inputSpilt = input.split("\\s");
        Model.PlatoModel.Event event = eventFinderByEventID(inputSpilt[0]);

        if (event == null) {
            throw new ExistEventException("There is no Event with this ID");
        }

        if (!Existence.checkEventExistence(Integer.parseInt(inputSpilt[0]))) {
            throw new ExistEventException("There is no Event for show!");
        }

        if (inputSpilt[1].trim().equalsIgnoreCase("GameName")) {
            editGameName(event, inputSpilt[2]);
        } else if (inputSpilt[1].trim().equalsIgnoreCase("StartDate")) {
            editStartDate(event, inputSpilt[2]);
        } else if (inputSpilt[1].trim().equalsIgnoreCase("EndDate")) {
            editEndDate(event, inputSpilt[2]);
        } else if (inputSpilt[1].trim().equalsIgnoreCase("Score")) {
            editScore(event, inputSpilt[2]);
        } else
            throw new InvalidFieldException("Entered Field for change in Invalid");

    }

    public void removeEventByAdminFromView(String eventID) throws ExistEventException {
        removeEvent(eventID);
    }

    public static void removeEvent(String eventID) throws ExistEventException {
        if (Existence.checkEventExistence(Integer.parseInt(eventID))) {
            Model.PlatoModel.Event.events.remove(eventFinderByEventID(eventID));
        } else
            throw new ExistEventException("There is no Event with this ID");
    }

    public static void eventDateChecker() throws ExistEventException {
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

    public static Model.PlatoModel.Event eventFinderByEventID(String eventID) throws ExistEventException {
        Model.PlatoModel.Event resultEvent = null;
        for (Model.PlatoModel.Event event : Model.PlatoModel.Event.getEvents()) {
            if (event.getEventID() == Integer.parseInt(eventID)) {
                resultEvent = event;
                break;
            }
        }
        if (resultEvent == null)
            throw new ExistEventException("There is no Event with this ID");

        return resultEvent;
    }

    /****************************************************EditMethods****************************************************/

    protected static void editGameName(Model.PlatoModel.Event event, String input) {
        try {
            Validation.gameNameIsValid(input);
            event.setGameName(input);
        } catch (InvalidGameNameException e) {
            System.out.println(e.getGameName() + e.getMessage());
        }


    }

    protected static void editStartDate(Model.PlatoModel.Event event, String input) throws InvalidDateException, StartDatesException {
        boolean pass = true;
        LocalDate startDate = LocalDate.parse(input);


        Validation.dateIsValid(input);

        if (startDate.isBefore(LocalDate.now()) || !startDate.equals(LocalDate.now()))
            throw new StartDatesException("Start Date Must be After now You cant Start Event in pass");


        if (startDate.isAfter(event.getEndDate()))
            throw new StartDatesException("Start Date Must be Before than EndDate");

        event.setStartDate(startDate);

    }

    protected static void editEndDate(Model.PlatoModel.Event event, String input) throws InvalidDateException, StartDatesException {
        boolean pass = true;
        LocalDate endDate = LocalDate.parse(input);


        Validation.dateIsValid(input);


        if (endDate.isBefore(event.getStartDate()))
            throw new StartDatesException("EndDate Must be After than StartDate");

        event.setEndDate(endDate);
    }

    protected static void editScore(Model.PlatoModel.Event event, String input) {
        event.setScore(Long.parseLong(input));
    }


}
