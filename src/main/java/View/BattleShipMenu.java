package View;

import java.util.HashMap;

public class BattleShipMenu extends Menu {
    private String username;
    public BattleShipMenu(String username, Menu parentMenu) {
        super("BattleShip Menu", parentMenu);
        this.username=username;
        HashMap<Integer,Menu> submenus = new HashMap<>();
    }
}

