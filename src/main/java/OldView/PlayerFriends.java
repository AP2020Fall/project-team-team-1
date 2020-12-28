package OldView;

import java.util.HashMap;

public class PlayerFriends extends Menu {
    private String username;
    public PlayerFriends(String username,Menu parentMenu) {
        super("Friends", parentMenu);
        this.username= username;
        HashMap<Integer,Menu> submenus = new HashMap<>();
        submenus.put(1,new ShowFriends(username,this));
        submenus.put(2,new ShowFriendsRequests(username,this));
        this.setSubmenus(submenus);
    }

}
