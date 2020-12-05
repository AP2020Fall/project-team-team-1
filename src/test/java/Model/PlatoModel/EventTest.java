package Model.PlatoModel;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void addNewEvent() {
        Model.PlatoModel.Event event1 = new Model.PlatoModel.Event("BattleSea", LocalDate.parse("2020-11-24"), LocalDate.parse("2020-11-26"), 50);
        Model.PlatoModel.Event.addNewEvent(event1);
        assertEquals(Event.getEvents().size(),1);
    }
}