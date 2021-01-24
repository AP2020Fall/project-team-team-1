package Server.Controller.AdminController;

import Server.Controller.Exception.Plato.*;
import junit.framework.TestCase;

public class EventTest extends TestCase {
    Event event = new Event();

    public void testAddEvent() throws StartDatesException, ExistEventException {
        String string = "BattleSea 2021-12-07 2022-12-08 50";
        event.addEvent(string);
        assertEquals(string,"BattleSea 2021-12-07 2022-12-08 50");
    }

//    public void testShowEvent() throws ExistEventException, IOException {
//        Server.Model.PlatoModel.Event event1 = new Server.Model.PlatoModel.Event("BattleSea", LocalDate.parse("2020-11-24"), LocalDate.parse("2020-11-26"), 50,"salam ");
//        Server.Model.PlatoModel.Event.addNewEvent(event1);
//        Event.showEvent();
//        assertEquals(Server.Model.PlatoModel.Event.events.size(),1);
//    }

//    public void testEditEvent() throws InvalidDateException, InvalidFieldException, StartDatesException, ExistEventException, NotNullMessageException, InvalidGameNameException, IOException {
//        Server.Model.PlatoModel.Event event1 = new Server.Model.PlatoModel.Event("BattleSea", LocalDate.parse("2020-12-05"), LocalDate.parse("2022-12-05"), 50,"salam");
//        Server.Model.PlatoModel.Event.addNewEvent(event1);
//
//        String input1 = "1 StartDate 2020-12-05";
//        event.editEvent(input1);
//        assertEquals(event1.getStartDate().toString(), "2020-12-05");
//
//        String input2 = "1 GameName dotsAndBoxes";
//        event.editEvent(input2);
//        assertEquals(event1.getGameName(), "dotsAndBoxes");
//
//        String input3 = "1 EndDate 2020-12-07";
//        event.editEvent(input3);
//        assertEquals(event1.getEndDate().toString(), "2020-12-07");
//
//        String input4 = "1 Score 90";
//        event.editEvent(input4);
//        assertEquals(event1.getScore(), 90);
//    }

//    public void testRemoveEvent() throws ExistEventException, IOException {
//        Server.Model.PlatoModel.Event event1 = new Server.Model.PlatoModel.Event("BattleSea", LocalDate.parse("2020-11-24"), LocalDate.parse("2020-11-26"), 50,"salam");
//        Server.Model.PlatoModel.Event.addNewEvent(event1);
//        Event.removeEvent("1");
//        assertEquals(Server.Model.PlatoModel.Event.getEvents().size(), 0);
//
//    }
//    public void testEventFinderByEventID() throws ExistEventException {
//        Server.Model.PlatoModel.Event event1 = new Server.Model.PlatoModel.Event("BattleSea", LocalDate.parse("2020-11-24"), LocalDate.parse("2020-11-26"), 50,"salam");
//        Server.Model.PlatoModel.Event.addNewEvent(event1);
//        Event.eventFinderByEventID("1");
//        assertEquals(Server.Model.PlatoModel.Event.events.size(), 1);
//    }
//    public void testEventDateChecker() throws ExistEventException, IOException {
//        Server.Model.PlatoModel.Event event1 = new Server.Model.PlatoModel.Event("BattleSea", LocalDate.parse("2020-11-24"), LocalDate.parse("2020-11-26"), 50,"salam");
//        Server.Model.PlatoModel.Event.addNewEvent(event1);
//        System.out.println(Server.Model.PlatoModel.Event.events);
//        Event.eventDateChecker();
//        System.out.println(Server.Model.PlatoModel.Event.events);
//        assertEquals(Server.Model.PlatoModel.Event.events.size(),1);
//    }
}