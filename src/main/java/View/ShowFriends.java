package View;

import Controller.Exception.Plato.ExistFriendException;
import Controller.Exception.Plato.ExistPlayerException;

import java.io.IOException;
import java.util.HashMap;

public class ShowFriends extends Menu {
    private String username;
    public ShowFriends(String username, Menu parentMenu) {
        super("View Friends List", parentMenu);
        HashMap<Integer,Menu> submenus = new HashMap<>();
        submenus.put(1,removeFriend());
        submenus.put(2,ViewFriendProfile());
        this.username=username;
        this.setSubmenus(submenus);
    }
    private Menu removeFriend(){
        return new Menu("Remove Friend",this) {
            @Override
            public void execute() {
                System.out.println("Please Enter The Friend You Want To Remove From Your Friends : ");
                String friendUsername = scanner.nextLine();
                try {
                    playerGeneralController.removeFriend(username,friendUsername);
                    System.out.println(friendUsername + "Successfully Removed From Your Friends!");
                    this.parentMenu.run();
                } catch (ExistFriendException | ExistPlayerException e) {
                    System.out.println(e.getMessage());
                    this.parentMenu.run();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        };
    }
    private Menu ViewFriendProfile(){
        return new Menu("View Friends Profile",this) {
            @Override
            public void execute() {
                System.out.println("Please Enter The Friend You Want To View From Your Friends : ");
                String friendUsername = scanner.nextLine();
                try {
                    String[] showEvent = playerGeneralController.showFriendProfile(username,friendUsername).split("\\$");
                    for (String out : showEvent) {
                        System.out.println(out);
                    }
                } catch (ExistFriendException e) {
                    System.out.println(e.getName()+e.getMessage());
                    this.parentMenu.run();
                }
                Menu nextMenu = null;
                String num = scanner.nextLine();
                if ((!num.matches("\\d+")) || Integer.parseInt(num) > submenus.size() + 1){
                    this.run();
                }else {
                    int chosenMenu = Integer.parseInt(num);
                    if (chosenMenu==submenus.size()+1){
                        if (this.parentMenu==null){
                            System.exit(1);
                        }else {
                            nextMenu=this.parentMenu;
                            nextMenu.run();
                        }
                    } else {
                        nextMenu = submenus.get(chosenMenu);
                        nextMenu.run();
                    }
                }
            }
        };
    }

    @Override
    public void execute() {
        try {
            String[] showEvent = playerGeneralController.showFriends(username).split("\\$");
            for (String out : showEvent) {
                System.out.println(out);
            }
        } catch (ExistFriendException e) {
            System.out.println(e.getName()+e.getMessage());
        }
        Menu nextMenu = null;
        String num = scanner.nextLine();
        if ((!num.matches("\\d+")) || Integer.parseInt(num) > submenus.size() + 1){
            this.run();
        }else {
            int chosenMenu = Integer.parseInt(num);
            if (chosenMenu==submenus.size()+1){
                if (this.parentMenu==null){
                    System.exit(1);
                }else {
                    nextMenu=this.parentMenu;
                    nextMenu.run();
                }
            } else {
                nextMenu = submenus.get(chosenMenu);
                nextMenu.run();
            }
        }
    }
}
