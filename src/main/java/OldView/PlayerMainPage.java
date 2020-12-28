package OldView;

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

    @Override
    public void execute() {
        Menu nextMenu = null;
        String num = scanner.nextLine();
        if ((!num.matches("\\d+")) || Integer.parseInt(num) > submenus.size() + 1){
            this.run();
        }else {
            int chosenMenu = Integer.parseInt(num);
            if (chosenMenu==submenus.size()+1){
                System.out.println("Not Valid Try Again");
                this.run();
            } else {
                nextMenu = submenus.get(chosenMenu);
                nextMenu.run();
            }
        }
    }

    @Override
    public void show() {
        for (Integer menuNum : submenus.keySet()) {
            System.out.println(menuNum + ". " + submenus.get(menuNum).getName());
        }
    }
}
