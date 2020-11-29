package View;

import java.util.HashMap;

public class PlayerMainMenu extends Menu{
    private String username;
    public PlayerMainMenu( Menu parentMenu, String username) {
        super("Menu", parentMenu);
        this.username = username;
        HashMap<Integer,Menu> submenus = new HashMap<>();

    }
}
