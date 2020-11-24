package Controller.AdminController;

import junit.framework.TestCase;

public class EventTest extends TestCase {
    Event generalController = new Event();

    public void testAddEvent() {
        String string ="BattleSea 2020-11-24 2020-11-26 50";
        Boolean pass = generalController.addEvent(string);
        assertTrue(pass);
    }

    public void testShowEvent() {
    }

    public void testEditEvent() {
    }

    public void testRemoveEvent() {
    }
}