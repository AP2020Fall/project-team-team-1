package Client.OldView;

import Server.Controller.Exception.Plato.AcceptAndDeclineFriendException;
import Server.Controller.Exception.Plato.ExistFriendException;
import Server.Controller.Exception.Plato.ExistPlayerException;

import java.io.IOException;
import java.util.HashMap;

public class ShowFriendsRequests extends Menu {
    private String username;
    public ShowFriendsRequests(String username, Menu parentMenu) {
        super("Friends Requests", parentMenu);
        this.username=username;
        HashMap<Integer,Menu> submenus = new HashMap<>();
        submenus.put(1,AcceptRequest());
        submenus.put(2,DeclineRequest());
        this.setSubmenus(submenus);
    }
    private Menu AcceptRequest(){
        return new Menu("Accept",this) {
            @Override
            public void show() {

            }

            @Override
            public void execute() {
                String usernameOfRequests = scanner.nextLine();
                try {
                    playerGeneralController.acceptRequest(username,usernameOfRequests);
                    System.out.println(usernameOfRequests+" Successfully Added To Your Friends LIst");
                } catch (ExistPlayerException | AcceptAndDeclineFriendException e) {
                    System.out.println(e.getMessage());
                    this.parentMenu.run();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                this.parentMenu.run();
            }
        };
    }
    private Menu DeclineRequest(){
        return new Menu("Decline",this) {
            final String usernameOfRequests = scanner.nextLine();

            @Override
            public void execute() {
                try {
                    playerGeneralController.declineRequest(username,usernameOfRequests);
                    System.out.println(usernameOfRequests+"'s friendship request declined Successfully.");
                } catch (ExistPlayerException | AcceptAndDeclineFriendException e) {
                    System.out.println(e.getMessage());
                    this.parentMenu.run();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                this.parentMenu.run();
            }
        };
    }

    @Override
    public void execute() {
        try {
            String[] showEvent = playerGeneralController.showRequests(username).split("\\$");
            for (String out : showEvent) {
                System.out.println(out);
            }
        } catch (ExistFriendException e) {
            System.out.println(e.getMessage());
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
