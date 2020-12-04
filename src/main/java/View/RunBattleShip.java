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
        int counter = 1;
        while (true) {
            if (counter == 1) {
                battleSeaController.addPlayersToArrayList();

                while (true) {
                    System.out.println(player1 + "'s Board \nIf you want to change the map Enter \" Change \" , otherwise enter \" done \" ");
                    battleSeaController.randomShipPlaceForPlayer1();
                    if (scanner.nextLine().equalsIgnoreCase("done")) {
                        break;
                    }
                    //todo for change first we should reset Ship of player and player board
                    //battleSeaController.restPlayer1Board();

                }
                while (true) {
                    System.out.println(player2 + "'s Map \nIf you want to change the map Enter \" Change \" , otherwise enter \" done \" ");
                    battleSeaController.randomShipPlaceForPlayer2();
                    if (scanner.nextLine().equalsIgnoreCase("done")) {
                        break;
                    }
                    //todo for change first we should reset Ship of player and player board
                    //battleSeaController.restPlayer2Board();
                }
            }
            if (counter%2==1){
                System.out.println(player1 + "'s Turn ");
            }else {
                System.out.println(player2 + "'s Turn ");
            }

            String nexCommand = scanner.nextLine();

            //battleSeaController.mainCommandProcessor(player1, player2);
            if (counter % 2 == 1) {
                battleSeaController.mainCommandProcessor("player1", nexCommand);

            } else {
                battleSeaController.mainCommandProcessor("player2", nexCommand);
            }

            counter++;
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

