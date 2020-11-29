package View;

import java.util.HashMap;

public class PlayerMainPage extends Menu {
    private String username;
    public PlayerMainPage(String username,Menu parentMenu) {
        super("Main Page", parentMenu);
        this.username=username;
        HashMap<Integer,Menu> submenus = new HashMap<>();
        submenus.put(1,new PlayerMainMenu(this,username));
        submenus.put(2,new GamesMenu(username,this));
        submenus.put(3,new PlayerFriends(username,this));
        this.setSubmenus(submenus);
    }
}
