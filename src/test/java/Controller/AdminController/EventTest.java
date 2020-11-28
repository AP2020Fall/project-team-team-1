package Controller.AdminController;

import Controller.Exception.InvalidDateException;
import Controller.Exception.StartDatesException;
import junit.framework.TestCase;

import java.time.LocalDate;

public class EventTest extends TestCase {
    Event event = new Event();

    public void testAddEvent() throws StartDatesException {
        String string ="BattleSea 2020-11-24 2020-11-26 50";
        event.addEvent(string);

    }

    public void testShowEvent() {
        Model.PlatoModel.Event event = new Model.PlatoModel.Event("BattleSea", LocalDate.parse("2020-11-24"),LocalDate.parse("2020-11-26"),50);
        Model.PlatoModel.Event.addNewEvent(event);
    }

    public void testEditEvent() throws InvalidDateException {
        Model.PlatoModel.Event event1 = new Model.PlatoModel.Event("BattleSea", LocalDate.parse("2020-11-24"),LocalDate.parse("2020-11-26"),50);
        Model.PlatoModel.Event.addNewEvent(event1);

        String input1 = "1 StartDate 2020-11-25";
        event.editEvent(input1);
        assertEquals(event1.getStartDate().toString(),"2020-11-25");

        String input2 = "1 GameName dotsAndBoxes";
        event.editEvent(input2);
        assertEquals(event1.getGameName(),"dotsAndBoxes");

        String input3 = "1 EndDate 2020-11-28";
        event.editEvent(input3);
        assertEquals(event1.getEndDate().toString(),"2020-11-28");

        String input4 = "1 Score 90";
        event.editEvent(input4);
        assertEquals(event1.getScore(),90);
    }

    public void testRemoveEvent() {
        Model.PlatoModel.Event event1 = new Model.PlatoModel.Event("BattleSea", LocalDate.parse("2020-11-24"),LocalDate.parse("2020-11-26"),50);
        Model.PlatoModel.Event.addNewEvent(event1);
        Event.removeEvent("1");
        assertEquals(Model.PlatoModel.Event.getEvents().size(),0);

    }
}