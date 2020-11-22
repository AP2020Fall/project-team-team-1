package View;

import Controller.AdminController.*;
import Controller.AdminController.Edit;
import Controller.AdminController.Suggestion;
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
    protected Coin coinController;
    protected Edit adminEditController;
    protected Event adminEventController;
    protected Message adminMessageController;
    protected PlayerLists adminPlayerListController;
    protected Controller.PlayerController.Edit playerEditController;
    protected FavoriteGames playerFavoritesGame;
    protected FindPlayerByInfo playerFindPlayerByInfoController;
    protected Friend playerFriendController;
    protected JoinEvent playerJoinEventController;
    protected PlayerInfo playerInfoController;
    protected PlayerStatusInGame playerStatusInGameController;
    protected RunGame playerRunGameController;
    protected Suggestion adminSuggestionController;
    protected Controller.PlayerController.Suggestion playerSuggestionController;
    protected ViewPlatoBotMessages playerViewPlatoBotsMassagesController;
    protected Delete processDeleteAccountController;
    protected LogIn processLoginController;
    protected SignUp processSignupController;
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
        System.out.println(this.name + ":");
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
        System.out.println("You Are in " + this.name + " Please chose a valid option :");
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
