package OldView;

import Controller.AdminController.*;
import Controller.AdminController.Edit;
import Controller.AdminController.Suggestion;
import Controller.BattleSeaController.BattleSeaController;
import Controller.PlayerController.*;
import Controller.RegisterController.Delete;
import Controller.RegisterController.LogIn;
import Controller.RegisterController.SignUp;

import java.util.HashMap;
import java.util.Scanner;

public abstract class Menu {
    private String name;
    protected HashMap<Integer,Menu> submenus = new HashMap<>();
    protected Menu parentMenu;
    public static Scanner scanner;
//---------------------------------------Controller//-------------------------------------------
  //  protected static Coin coinController = new Coin();
    protected static Edit adminEditController = new Edit();
    protected static Event adminEventController = new Event();
    protected static Message adminMessageController = new Message();
    protected static PlayerLists adminPlayerListController = new PlayerLists();
    protected static Controller.PlayerController.Edit playerEditController = new Controller.PlayerController.Edit();
    protected static FavoriteGames playerFavoritesGame = new FavoriteGames();
    protected static FindPlayerByInfo playerFindPlayerByInfoController = new FindPlayerByInfo();
    protected static Friend playerFriendController = new Friend();
    protected static JoinEvent playerJoinEventController = new JoinEvent();
    protected static PlayerInfo playerInfoController = new PlayerInfo();
    protected static PlayerStatusInGame playerStatusInGameController = new PlayerStatusInGame();
    //protected static Game playerGameController = new Game();
    protected static Suggestion adminSuggestionController = new Suggestion();
    protected static Controller.PlayerController.Suggestion playerSuggestionController = new Controller.PlayerController.Suggestion();
    protected static ViewPlatoBotMessages playerViewPlatoBotsMassagesController = new ViewPlatoBotMessages();
    protected static Delete processDeleteAccountController = new Delete();
    protected static LogIn processLoginController = new LogIn();
    protected static SignUp processSignupController = new SignUp();
    protected static AdminGeneralController adminGeneralController = new AdminGeneralController();
    protected static PlayerGeneralController playerGeneralController = new PlayerGeneralController();
    protected static BattleSeaController battleSeaController = new BattleSeaController();
//------------------------------------------------------------------------------------------------

    public Menu(String name, Menu parentMenu) {
        this.name = name;
        this.parentMenu = parentMenu;
    }

    public void setSubmenus(HashMap<Integer, Menu> submenus) {
        this.submenus = submenus;
    }

    public String getName() {
        return name;
    }

    public static void setScanner(Scanner scanner) {
        Menu.scanner = scanner;
    }
    public void show(){
        System.out.println("You Are in " + this.name + " Please chose a valid option :");
        for (Integer menuNum : submenus.keySet()) {
            System.out.println(menuNum + ". " + submenus.get(menuNum).getName());
        }
        if (this.parentMenu != null)
            System.out.println((submenus.size() + 1) + ". Back");
        else
            System.out.println((submenus.size() + 1) + ". Exit");
    }
    public void execute(){
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
    public void run(){
        this.show();
        this.execute();
    }
}
