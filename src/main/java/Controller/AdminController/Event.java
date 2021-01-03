package Controller.AdminController;

import Controller.CompetencyController.Existence;
import Controller.CompetencyController.Validation;
import Controller.Exception.Plato.*;
import Model.DataBase.DataBase;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;

public class Event {
    private static final File eventFile = new File("src\\main\\java\\Model\\Database\\Event.json");

    public static void addEvent(String input) throws StartDatesException, ExistEventException {

        String[] inputSpilt = input.split("\\s");
        LocalDate startDate = LocalDate.parse(inputSpilt[2]);
        LocalDate endDate = LocalDate.parse(inputSpilt[3]);
        if (startDate.isBefore(LocalDate.now())) {
            throw new StartDatesException("Start Date Must be after than Today");

        }
        if (startDate.isAfter(endDate)) {
            throw new StartDatesException("Start Date Must be before than End Date");
        }
        if (eventIDChecker(inputSpilt[0])!=null){
            throw new ExistEventException("ID exist");
        }

        Model.PlatoModel.Event.addNewEvent(new Model.PlatoModel.Event(Integer.parseInt(inputSpilt[0]),inputSpilt[1], startDate, endDate, Integer.parseInt(inputSpilt[4]), inputSpilt[5]));


    }

    public static String showEvent() throws ExistEventException, IOException {
        StringBuilder output = new StringBuilder();
        eventDateChecker();
        if (Model.PlatoModel.Event.getEvents().size() == 0)
            throw new ExistEventException("There is no Event for show!");
        for (Model.PlatoModel.Event event : Model.PlatoModel.Event.events) {
            output.append("EventId:").append(event.getEventID()).append(" Game name: ").append(event.getGameName()).append(" Start date: ").append(event.startDateProperty()).append(" End date: ").append(event.getEndDate()).append(" Score: ").append(event.getScore()).append(" Comment: ").append(event.getComment()).append("$");
        }
        return String.valueOf(output);
    }

    public static void editEvent(String input) throws InvalidDateException, ExistEventException, InvalidFieldException, StartDatesException, NotNullMessageException, InvalidGameNameException, IOException {
        eventDateChecker();
        String[] inputSpilt = input.split("\\s");
        Model.PlatoModel.Event event = eventFinderByEventID(inputSpilt[0]);


        if (!Existence.checkEventExistence(Integer.parseInt(inputSpilt[0]))) {
            throw new ExistEventException("There is no Event for show!");
        }

        if (inputSpilt[1].trim().equalsIgnoreCase("Game Name")) {
            editGameName(event, inputSpilt[2]);
        } else if (inputSpilt[1].trim().equalsIgnoreCase("Start Date")) {
            editStartDate(event, inputSpilt[2]);
        } else if (inputSpilt[1].trim().equalsIgnoreCase("End Date")) {
            editEndDate(event, inputSpilt[2]);
        } else if (inputSpilt[1].trim().equalsIgnoreCase("Score")) {
            editScore(event, inputSpilt[2]);
        } else if (inputSpilt[1].trim().equalsIgnoreCase("Comment")) {
            //editComment(event, inputSpilt[2].concat(" ").concat(inputSpilt[3].concat(" ").concat(inputSpilt[4].concat(" ").concat(inputSpilt[5]))));
            editComment(event,input);
        } else
            throw new InvalidFieldException("Entered Field for change in Invalid");


    }

    public static void removeEventByAdminFromView(String eventID) throws ExistEventException, IOException {
        removeEvent(eventID);

    }

    public static void removeEvent(String eventID) throws ExistEventException, IOException {
        if (Existence.checkEventExistence(Integer.parseInt(eventID))) {
            Model.PlatoModel.Event.events.remove(eventFinderByEventID(eventID));
        } else
            throw new ExistEventException("There is no Event with this ID");

        DataBase.save(Model.PlatoModel.Event.getEvents(), eventFile);

    }

    public static void eventDateChecker() throws ExistEventException, IOException {
        ArrayList<Model.PlatoModel.Event> listForDelete = new ArrayList<>();
        for (Model.PlatoModel.Event event : Model.PlatoModel.Event.getEvents()) {
//            if (event.getEndDate().isBefore(LocalDate.now())) {
//                listForDelete.add(event);
//            }
            LocalDate end = LocalDate.parse(event.getEndDate().toString());
            if (end.isBefore(LocalDate.now())){
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
    public static Model.PlatoModel.Event eventIDChecker(String eventID)  {
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

    protected static void editGameName(Model.PlatoModel.Event event, String input) throws InvalidGameNameException {
        Validation.gameNameIsValid(input);
        event.setGameName(input);
    }

    protected static void editComment(Model.PlatoModel.Event event, String input) throws NotNullMessageException {
        event.setComment(input);
        if (input.isEmpty()) {
            throw new NotNullMessageException("It is empty, Please enter a comment! ");
        }
    }


    protected static void editStartDate(Model.PlatoModel.Event event, String input) throws InvalidDateException, StartDatesException {
        LocalDate startDate = LocalDate.parse(input);


        Validation.dateIsValid(input);

        if (startDate.isBefore(LocalDate.now()) || !startDate.equals(LocalDate.now()))
            throw new StartDatesException("Start Date Must be After now You cant Start Event in pass");


        if (startDate.isAfter((ChronoLocalDate) event.getEndDate()))
            throw new StartDatesException("Start Date Must be Before than EndDate");

        event.setStartDate(startDate);

    }

    protected static void editEndDate(Model.PlatoModel.Event event, String input) throws InvalidDateException, StartDatesException {
        LocalDate endDate = LocalDate.parse(input);


        Validation.dateIsValid(input);


        if (endDate.isBefore((ChronoLocalDate) event.startDateProperty()))
            throw new StartDatesException("EndDate Must be After than StartDate");

        event.setEndDate(endDate);
    }

    protected static void editScore(Model.PlatoModel.Event event, String input) {
        event.setScore(Integer.parseInt(input));
    }


}
