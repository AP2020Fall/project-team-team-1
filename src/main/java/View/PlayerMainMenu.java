package View;

import Controller.Exception.ExistFavoriteException;
import Controller.Exception.ExistFriendException;
import Controller.Exception.ExistPlayerException;

import java.util.HashMap;

public class PlayerMainMenu extends Menu{
    private String username;
    public PlayerMainMenu( Menu parentMenu, String username) {
        super("Menu", parentMenu);
        this.username = username;
        HashMap<Integer,Menu> submenus = new HashMap<>();
        submenus.put(1,showPoints());
        submenus.put(2,showFavoritesGames());
        submenus.put(3,showPlatoBotsMessage());
        submenus.put(4,showLastPlayed());
        submenus.put(5,new ViewAdminSuggestion(username,this));
        submenus.put(6,addFriend());
        submenus.put(7,new UserMenuForPlayer(username,this));
        this.setSubmenus(submenus);
    }
    private Menu showPoints(){
        return new Menu("show points",this) {
            @Override
            public void execute() {
                try {
                    playerGeneralController.showPoint(username);
                } catch (ExistPlayerException e) {
                    System.out.println(e.getPlayerName() + e.getMessage());
                }
            }
        };
    }
    private Menu showFavoritesGames(){
     return new Menu("Favorites Games",this) {
         @Override
         public void execute() {
             try {
                 playerGeneralController.showFavoritesGames(username);
             } catch (ExistFavoriteException e) {
                 System.out.println(e.getMessage());
             }
         }
     };
    }
    private Menu showPlatoBotsMessage(){
        return new Menu("PlatoBots Messages",this) {
            @Override
            public void execute() {
                playerGeneralController.viewBotMessages(username);
            }
        };
    }
    private Menu showLastPlayed(){
        return new Menu("Last Game Played",this) {
            @Override
            public void execute() {
                try {
                    playerGeneralController.showUserLastPlayed(username);
                } catch (ExistPlayerException e) {
                    System.out.println(e.getPlayerName() + e.getMessage());
                }
            }
        };
    }
    private Menu addFriend(){
        return new Menu("Add Friend",this) {
            @Override
            public void execute() {
                String friendName = requestFriendship();
                try {
                    playerGeneralController.addFriends(username,friendName);
                    System.out.println("Friend Request to "+ friendName +" is Pending.");
                    this.parentMenu.run();
                } catch (ExistFriendException e) {
                    System.out.println(e.getName() + e.getMessage());
                    this.parentMenu.run();
                } catch (ExistPlayerException e) {
                    System.out.println(e.getPlayerName()+e.getMessage());
                    this.parentMenu.run();
                }

            }
        };
    }
    private String requestFriendship(){
        System.out.println("Enter The Player You Want To be Friend With");
        return scanner.nextLine();
    }
}
