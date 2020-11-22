package View;

import java.util.HashMap;

public class MainMenu extends Menu {
    public MainMenu() {
        super("Main Menu!", null);
        HashMap<Integer,Menu> submenus = new HashMap<>();
        submenus.put(1,new LoginMenu(this));
        submenus.put(2,new SignUpMenu(this));
        this.setSubmenus(submenus);
    }

    @Override
    public void run() {
        System.out.println("Welcome");
        this.show();
        this.execute();
    }
}
