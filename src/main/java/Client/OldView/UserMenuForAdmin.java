package Client.OldView;

import java.util.HashMap;

public class UserMenuForAdmin extends Menu {
    String username;
    public UserMenuForAdmin(String username, Menu parentMenu) {
        super("Profile", parentMenu);
        this.username=username;
        HashMap<Integer,Menu> subMenus = new HashMap<>();
        subMenus.put(1,new ShowAdminInfo(this.username,this));
        subMenus.put(2,logout());
        this.setSubmenus(subMenus);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private Menu logout(){
        return new Menu("logout",this) {
            @Override
            public void show() {
                System.out.println("You have Log Out Successfully see you Soon Boss ");
            }

            @Override
            public void execute() {
            setUsername(null);
            this.parentMenu.parentMenu.parentMenu.parentMenu.parentMenu.run();
            }
        };
    }

    @Override
    public void run() {
        this.show();
        this.execute();
    }
}
