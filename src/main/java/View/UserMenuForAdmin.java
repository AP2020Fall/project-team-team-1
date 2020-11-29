package View;

import java.util.HashMap;

public class UserMenuForAdmin extends Menu {
    String username;
    public UserMenuForAdmin(String username, Menu parentMenu) {
        super("User Menu", parentMenu);
        this.username=username;
        HashMap<Integer,Menu> subMenus = new HashMap<>();
        subMenus.put(1,new ShowAdminInfo(this.username,this));
        this.setSubmenus(subMenus);
    }

    @Override
    public void run() {
        this.show();
        this.execute();
    }
}
