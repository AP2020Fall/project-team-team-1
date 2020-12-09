package View;

import Controller.Exception.Plato.ExistEventException;
import Controller.Exception.Plato.InvalidDateException;
import Controller.Exception.Plato.InvalidFieldException;
import Controller.Exception.Plato.StartDatesException;

import java.io.IOException;
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
            public void show() {

            }

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
                } catch (IOException e) {
                    e.printStackTrace();
                }
                this.parentMenu.run();
            }
        };
    }

    private Menu removeEvent() {
        return new Menu("remove Event", this) {
            @Override
            public void show() {

            }

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
            Menu nextMenu = null;
            String num = scanner.nextLine();
            if ((!num.matches("\\d+")) || Integer.parseInt(num) > submenus.size() + 1){
                this.run();
            }else {
                int chosenMenu = Integer.parseInt(num);
                if (chosenMenu==submenus.size()+1){
                    if (this.parentMenu==null){
                        System.exit(1);
                    }else {
                        nextMenu=this.parentMenu;
                        nextMenu.run();
                    }
                } else {
                    nextMenu = submenus.get(chosenMenu);
                    nextMenu.run();
                }
            }
        } catch (ExistEventException e) {
            System.out.println(e.getMessage());
            this.parentMenu.run();
        }
    }
}
