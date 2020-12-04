package View;

import Controller.CompetencyController.Validation;
import Controller.Exception.ExistPlayerException;
import Controller.Exception.InvalidGameNameException;

import java.util.HashMap;

public class UserMenuForPlayer extends Menu {
    private String username;
    public UserMenuForPlayer(String username, Menu parentMenu) {
        super("Profile", parentMenu);
        this.username=username;
        HashMap<Integer,Menu> submenus = new HashMap<>();
        submenus.put(1,new ShowPlayerInfo(username,this));
        submenus.put(2,viewPlatoStatistics());
        submenus.put(3,viewGameHistory());
        submenus.put(4,showEachGameStatistics());
        submenus.put(5,logout());
        this.setSubmenus(submenus);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private Menu viewPlatoStatistics(){
        return new Menu("Plato Statistics",this) {
            @Override
            public void execute() {
                try {
                    playerGeneralController.showUserGamesStatistics(username);
                } catch (ExistPlayerException e) {
                    System.out.println(e.getPlayerName() + e.getMessage());
                }
                while (true){
                    System.out.println("Enter back to get back to last menu! ");
                    String nextCommand = scanner.nextLine();
                    if (nextCommand.equalsIgnoreCase("back")){
                        this.parentMenu.run();
                        break;
                    }
                }
            }
        };
    }
    private Menu viewGameHistory(){
        return new Menu("Game History",this) {
            @Override
            public void execute() {
                playerGeneralController.showHistory(username);
                while (true){
                    System.out.println("Enter back to get back to last menu! ");
                    String nextCommand = scanner.nextLine();
                    if (nextCommand.equalsIgnoreCase("back")){
                        this.parentMenu.run();
                        break;
                    }
                }
            }
        };
    }
    private Menu showEachGameStatistics(){
        return new Menu("Game Statistics by Game",this) {
            @Override
            public void execute() {
                try {
                    playerGeneralController.showGameLogInThisGame(username,gameName());
                    while (true){
                        System.out.println("Enter back to get back to last menu! ");
                        String nextCommand = scanner.nextLine();
                        if (nextCommand.equalsIgnoreCase("back")){
                            this.parentMenu.run();
                            break;
                        }
                    }
                } catch (InvalidGameNameException e) {
                    System.out.println(e.getGameName()+e.getMessage());
                    this.run();
                }

            }
        };
    }
    private String gameName(){
        System.out.println("Please Enter The Game  Battleships|DotsAndBoxes ");
        while (true) {
            String game = scanner.nextLine();
            try {
                Validation.gameNameIsValid(game);
                return game;
            } catch (InvalidGameNameException e) {
                System.out.println(e.getGameName() + e.getMessage());
            }
        }
    }
    private Menu logout(){
        return new Menu("logout",this) {
            @Override
            public void execute() {
                setUsername(null);
                this.parentMenu.parentMenu.parentMenu.parentMenu.parentMenu.run();
            }
        };
    }

}
