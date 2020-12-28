package OldView;

import java.util.HashMap;

public class GamesMenu extends Menu {
    private String username;
    public GamesMenu(String username, Menu parentMenu) {
        super("Games", parentMenu);
        this.username=username;
        HashMap<Integer,Menu> submenus = new HashMap<>();
        submenus.put(1,new BattleShipMenu(username,this));
        submenus.put(2,new DotsAndBoxesMenu(username,this));
        this.setSubmenus(submenus);
    }


}
