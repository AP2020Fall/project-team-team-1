package View;

import java.util.HashMap;

public class GamesMenu extends Menu {
    private String username;
    public GamesMenu(String username, Menu parentMenu) {
        super("Games", parentMenu);
        this.username=username;
        HashMap<Integer,Menu> submenus = new HashMap<>();
        submenus.put(1,new BattleShipMenu(username,this));
        this.setSubmenus(submenus);
    }


}
