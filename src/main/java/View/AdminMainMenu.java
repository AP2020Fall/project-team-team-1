package View;

import java.util.HashMap;

public class AdminMainMenu extends Menu {
    public AdminMainMenu( Menu parentMenu) {
        super("Admin Main Menu", parentMenu);
        HashMap<Integer,Menu> submenus= new HashMap<>();
        submenus.put(1,addEvent());
        submenus.put(2,new ViewEvents(this));
        submenus.put(3,addSuggestion());
        submenus.put(4,viewSuggestion());
        submenus.put(5,viewUsers());
    }
    private Menu addEvent(){
        return new Menu("add Event",this) {

        };
    }
    private Menu addSuggestion(){
        return new Menu("add Suggestion",this) {

        };
    }
    private Menu viewSuggestion(){
        return new Menu("View Suggestion",this) {
        };
    }
    private Menu viewUsers(){
        return new Menu("view all users",this) {
        };
    }
}