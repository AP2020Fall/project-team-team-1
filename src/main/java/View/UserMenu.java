package View;

import java.util.HashMap;
import Controller.GeneralController.UserController;

public class UserMenu extends Menu {
    String username;
    public UserMenu(String username,Menu parentMenu) {
        super("User Menu", parentMenu);
        this.username=username;
        HashMap<Integer,Menu> subMenus = new HashMap<>();
        subMenus.put(1,ShowUserInfo());

    }
    private Menu ShowUserInfo(){
     return new Menu("View User Information", this) {
         @Override
         public void show() {
             System.out.println("hey "+username+" here is your profile.");
             userController.showUserInfo(username);
         }

     };
    }
}
