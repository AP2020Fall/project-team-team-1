package Server.Controller.AdminController;

import Server.Controller.CompetencyController.Existence;
import Server.Controller.CompetencyController.Validation;
import Server.Controller.Exception.Plato.*;
import Server.Model.DataBase.DataBase;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Event {
    private static final File eventFile = new File("src\\main\\resources\\DataBase\\Event.json");

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

        Server.Model.PlatoModel.Event.addNewEvent(new Server.Model.PlatoModel.Event(Integer.parseInt(inputSpilt[0]),inputSpilt[1], startDate, endDate, Integer.parseInt(inputSpilt[4]), inputSpilt[5]));


    }

    public static String showEvent() throws ExistEventException, IOException {
        StringBuilder output = new StringBuilder();
        eventDateChecker();
        if (Server.Model.PlatoModel.Event.getEvents().size() == 0)
            throw new ExistEventException("There is no Event for show!");
        for (Server.Model.PlatoModel.Event event : Server.Model.PlatoModel.Event.events) {
            output.append("EventId:").append(event.getEventID()).append(" Game name: ").append(event.getGameName()).append(" Start date: ").append(event.getStartDate()).append(" End date: ").append(event.getEndDate()).append(" Score: ").append(event.getScore()).append(" Comment: ").append(event.getComment()).append("$");
        }
        return String.valueOf(output);
    }

    public static void editEvent(String input) throws InvalidDateException, ExistEventException, InvalidFieldException, StartDatesException, NotNullMessageException, InvalidGameNameException, IOException {
        eventDateChecker();
        String[] inputSpilt = input.split("\\s");
        Server.Model.PlatoModel.Event event = eventFinderByEventID(inputSpilt[0]);


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
            editComment(event,input.substring(input.indexOf(inputSpilt[2])));
        } else
            throw new InvalidFieldException("Entered Field for change in Invalid");


    }

    public static void removeEventByAdminFromView(String eventID) throws ExistEventException, IOException {
        removeEvent(eventID);

    }

    public static void removeEvent(String eventID) throws ExistEventException, IOException {
        if (Existence.checkEventExistence(Integer.parseInt(eventID))) {
            Server.Model.PlatoModel.Event.events.remove(eventFinderByEventID(eventID));
        } else
            throw new ExistEventException("There is no Event with this ID");

        DataBase.save(Server.Model.PlatoModel.Event.getEvents(), eventFile);

    }

    public static void eventDateChecker() throws ExistEventException, IOException {
        ArrayList<Server.Model.PlatoModel.Event> listForDelete = new ArrayList<>();
        for (Server.Model.PlatoModel.Event event : Server.Model.PlatoModel.Event.getEvents()) {
//            if (event.getEndDate().isBefore(LocalDate.now())) {
//                listForDelete.add(event);
//            }
            LocalDate end = LocalDate.parse(event.getEndDate().toString());
            if (end.isBefore(LocalDate.now())){
                listForDelete.add(event);
            }
        }

        if (listForDelete.size() != 0) {
            for (Server.Model.PlatoModel.Event eventForDelete : listForDelete) {
                removeEvent(String.valueOf(eventForDelete.getEventID()));
            }
        }

    }

    public static Server.Model.PlatoModel.Event eventFinderByEventID(String eventID) throws ExistEventException {
        Server.Model.PlatoModel.Event resultEvent = null;
        for (Server.Model.PlatoModel.Event event : Server.Model.PlatoModel.Event.getEvents()) {
            if (event.getEventID() == Integer.parseInt(eventID)) {
                resultEvent = event;
                break;
            }
        }
        if (resultEvent == null)
            throw new ExistEventException("There is no Event with this ID");

        return resultEvent;
    }
    public static Server.Model.PlatoModel.Event eventIDChecker(String eventID)  {
        Server.Model.PlatoModel.Event resultEvent = null;
        for (Server.Model.PlatoModel.Event event : Server.Model.PlatoModel.Event.getEvents()) {
            if (event.getEventID() == Integer.parseInt(eventID)) {
                resultEvent = event;
                break;
            }
        }

        return resultEvent;
    }

    /****************************************************EditMethods****************************************************/

    protected static void editGameName(Server.Model.PlatoModel.Event event, String input) throws InvalidGameNameException {
        Validation.gameNameIsValid(input);
        event.setGameName(input);
    }

    protected static void editComment(Server.Model.PlatoModel.Event event, String input) throws NotNullMessageException {
        event.setComment(input);
        if (input.isEmpty()) {
            throw new NotNullMessageException("It is empty, Please enter a comment! ");
        }
    }


    protected static void editStartDate(Server.Model.PlatoModel.Event event, String input) throws InvalidDateException, StartDatesException {
        LocalDate startDate = LocalDate.parse(input);


        Validation.dateIsValid(input);

        if (startDate.isBefore(LocalDate.now()) || !startDate.equals(LocalDate.now()))
            throw new StartDatesException("Start Date Must be After now You cant Start Event in pass");


        if (startDate.isAfter(event.getEndDate()))
            throw new StartDatesException("Start Date Must be Before than EndDate");

        event.setStartDate(startDate);

    }

    protected static void editEndDate(Server.Model.PlatoModel.Event event, String input) throws InvalidDateException, StartDatesException {
        LocalDate endDate = LocalDate.parse(input);


        Validation.dateIsValid(input);


        if (endDate.isBefore(event.getStartDate()))
            throw new StartDatesException("EndDate Must be After than StartDate");

        event.setEndDate(endDate);
    }

    protected static void editScore(Server.Model.PlatoModel.Event event, String input) {
        event.setScore(Integer.parseInt(input));
    }

    public static void editEventProfile(String eventID , String url) throws ExistEventException {
        Server.Model.PlatoModel.Event event = eventFinderByEventID(eventID);
        event.setProfileURL(url);
    }
    public static String getEventProfileUrl(String eventID) throws ExistEventException {
        Server.Model.PlatoModel.Event event = eventFinderByEventID(eventID);
        return event.getProfileURL();
    }



}
