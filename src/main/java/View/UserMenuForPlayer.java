package View;

import java.util.HashMap;

public class UserMenuForPlayer extends Menu {
    private String username;
    public UserMenuForPlayer(String username, Menu parentMenu) {
        super("Profile", parentMenu);
        this.username=username;
        HashMap<Integer,Menu> submenus = new HashMap<>();
        submenus.put(1,new ShowPlayerInfo(username,this));
    }
    private Menu viewPlatoStatistics(){
        return new Menu("Plato Statistics",this) {
            @Override
            public void execute() {
//                playerGeneralController.
                //todo player statistics
            }
        };
    }
    private Menu viewGameHistory(){
        return new Menu() {
        };
    }

}
