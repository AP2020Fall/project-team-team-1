package View;

import Controller.CompetencyController.Validation;
import Controller.Exception.Plato.ExistPlayerException;
import Controller.Exception.Plato.ExistPlayerLogException;
import Controller.Exception.Plato.InvalidGameNameException;

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
                    String[] showEvent = playerGeneralController.showUserGamesStatistics(username).split("\\$");
                    for (String out : showEvent) {
                        System.out.println(out);
                    }

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
                try {

                    playerGeneralController.showHistory(username);
                } catch (ExistPlayerException e) {
                    System.out.println(e.getMessage());
                    this.parentMenu.run();
                } catch (ExistPlayerLogException e) {
                    System.out.println(e.getMessage());
                    this.parentMenu.run();
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
    private Menu showEachGameStatistics(){
        return new Menu("Game Statistics by Game",this) {
            @Override
            public void execute() {
                try {
                    String[] showEvent = playerGeneralController.showGameLogInThisGame(username,gameName()).split("\\$");
                    for (String out : showEvent) {
                        System.out.println(out);
                    }

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
        System.out.println("Please Enter The Game  "+adminGeneralController.firstGameNameGetter()+" OR "+adminGeneralController.secondGameNameGetter());
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
            public void show() {
                System.out.println("See You Soon Mate ");
            }

            @Override
            public void execute() {
                setUsername(null);
                this.parentMenu.parentMenu.parentMenu.parentMenu.parentMenu.run();
            }
        };
    }

}
