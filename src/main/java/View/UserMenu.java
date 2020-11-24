package View;

import java.util.HashMap;

public class UserMenu extends Menu {
    String username;
    public UserMenu(String username) {
        super("User Menu", null);
        this.username=username;
        HashMap<Integer,Menu> subMenus = new HashMap<>();
    }
    private Menu ShowUserInfo(){
     return new Menu("View User Information", this) {

     };
    }
}
