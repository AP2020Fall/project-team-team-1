package View;

import Controller.Exception.ExistEventException;
import Controller.Exception.InvalidDateException;
import Controller.Exception.InvalidFieldException;
import Controller.Exception.StartDatesException;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewEvents extends Menu {
    public ViewEvents(Menu parentMenu) {
        super("View Events", parentMenu);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, editEvent());
        submenus.put(2,removeEvent());
        this.setSubmenus(submenus);
    }

    private Menu editEvent() {
        return new Menu("edit event", this) {
            @Override
            public void execute() {
                ArrayList<String> editInfo = new ArrayList<>();
                getEditEventInfo(editInfo);
                try {
                    adminGeneralController.editEvent(arrayListToString(editInfo));
                } catch (InvalidDateException e) {
                    System.out.println(e.getMessage());
                } catch (InvalidFieldException | ExistEventException | StartDatesException e) {
                    e.printStackTrace();
                }
                this.parentMenu.run();
            }
        };
    }

    private Menu removeEvent() {
        return new Menu("remove Event", this) {

            @Override
            public void execute() {
                try {
                    adminEventController.removeEventByAdminFromView(getEventID());
                    this.parentMenu.run();
                } catch (ExistEventException e) {
                    System.out.println(e.getMessage());
                }

            }
        };
    }

    private String getEventID() {
        System.out.println("Please enter Event ID You Want to Delete : ");
        String eventID = scanner.nextLine();
        return eventID;
    }

    private static void getEditEventInfo(ArrayList input) {
        System.out.println("Please Enter Event ID : ");
        String eventID = scanner.nextLine();
        input.add(eventID);
        System.out.println("Please Enter The Field You want To Edit : ");
        String field = scanner.nextLine();
        input.add(field);
        System.out.println("Please Enter The New Value : ");
        String newValue = scanner.nextLine();
        input.add(newValue);
    }

    public static String arrayListToString(ArrayList<String> arrayList) {
        String output = "";
        for (String string : arrayList) {
            output += string + " ";
        }
        return output;
    }

    @Override
    public void execute() {
        try {
            adminGeneralController.showEvent();
        } catch (ExistEventException e) {
            System.out.println(e.getMessage());
        }
    }
}
