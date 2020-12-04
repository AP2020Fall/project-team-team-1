package View;

import Controller.Exception.InvalidUserNameException;
import Controller.Exception.WrongPasswordException;

import java.util.ArrayList;

public class RunBattleShip extends Menu {
    private final String Username1;
    private String Username2;

    public RunBattleShip(String username1, String username2, Menu parentMenu) {
        super("Run BattleShip", parentMenu);
        this.Username1 = username1;

    }

    public void setUsername2(String username2) {
        Username2 = username2;
    }

    @Override
    public void show() {
        System.out.println("Welcome to Battle Ship");
        System.out.println("If You Need Help Just Enter Help");
    }

    @Override
    public void execute() {
        if (this.Username2 != null) {
            runGame(this.Username1, this.Username2);
        } else temporaryLogin().run();
    }

    private Menu temporaryLogin() {
        return new Menu("login", this) {
            @Override
            public void execute() {
                ArrayList<String> info = new ArrayList<>();
                System.out.println("please Enter The Username ");
                info.add(scanner.nextLine());
                System.out.println("Please Enter The password");
                info.add(scanner.nextLine());
                try {
                    processLoginController.loginAsPlayer(arrayListToString(info));
                    setUsername2(info.get(0));
                } catch (InvalidUserNameException | WrongPasswordException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Try Again...");
                    this.run();
                }
                this.parentMenu.run();
            }
        };
    }

    private static void runGame(String player1, String player2) {
        while (!battleSeaController.tellMeWhenTheGameIsOver().equalsIgnoreCase("over")) {
            String nexCommand = scanner.nextLine();
            battleSeaController.mainCommandProcessor(player1, player2);
        }
    }

    public static String arrayListToString(ArrayList<String> arrayList) {
        String output = "";
        for (String string : arrayList) {
            output += string + " ";
        }
        return output;
    }


    }

