package View;

import java.util.HashMap;
import java.util.Scanner;

public abstract class Menu {
    private String name;
    protected HashMap<Integer,Menu> submenus = new HashMap<>();
    protected Menu parentMenu;
    public static Scanner scanner;

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
        System.out.println("You Are in " + this.name + "please chose a valid option :");
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
