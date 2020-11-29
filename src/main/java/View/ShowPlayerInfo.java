package View;

import java.util.HashMap;

public class ShowPlayerInfo extends Menu {
    private String username;
    public ShowPlayerInfo(String username, Menu parentMenu) {
        super("User info", parentMenu);
        this.username=username;
        HashMap<Integer,Menu> submenus = new HashMap<>();
    }
}
