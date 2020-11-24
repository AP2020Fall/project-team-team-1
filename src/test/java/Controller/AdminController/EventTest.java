package Controller.AdminController;

import junit.framework.TestCase;

import java.time.LocalDate;

public class EventTest extends TestCase {
    Event event = new Event();

    public void testAddEvent() {
        String string ="BattleSea 2020-11-24 2020-11-26 50";
        Boolean pass = event.addEvent(string);
        assertTrue(pass);
    }

    public void testShowEvent() {
        Model.PlatoModel.Event event = new Model.PlatoModel.Event("BattleSea", LocalDate.parse("2020-11-24"),LocalDate.parse("2020-11-26"),50);
        Model.PlatoModel.Event.addNewEvent(event);
    }

    public void testEditEvent() {
        Model.PlatoModel.Event event1 = new Model.PlatoModel.Event("BattleSea", LocalDate.parse("2020-11-24"),LocalDate.parse("2020-11-26"),50);
        Model.PlatoModel.Event.addNewEvent(event1);

        event.editEvent("1","StartDate","2020-11-25");
        assertEquals(event1.getStartDate().toString(),"2020-11-25");

        event.editEvent("1","GameName","dotsAndBoxes");
        assertEquals(event1.getGameName(),"dotsAndBoxes");

        event.editEvent("1","EndDate","2020-11-28");
        assertEquals(event1.getEndDate().toString(),"2020-11-28");

        event.editEvent("1","Score","90");
        assertEquals(event1.getScore(),90);
    }

    public void testRemoveEvent() {
        Model.PlatoModel.Event event1 = new Model.PlatoModel.Event("BattleSea", LocalDate.parse("2020-11-24"),LocalDate.parse("2020-11-26"),50);
        Model.PlatoModel.Event.addNewEvent(event1);
        Event.removeEvent("1");
        assertEquals(Model.PlatoModel.Event.getEvents().size(),0);

    }
}