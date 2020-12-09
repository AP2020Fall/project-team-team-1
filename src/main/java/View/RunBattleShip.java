package View;

import Controller.BattleSeaController.BattleSeaController;
import Controller.Exception.BattleShip.*;
import Controller.Exception.Plato.InvalidUserNameException;
import Controller.Exception.Plato.WrongPasswordException;
import Controller.PlayerController.Game;

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

    private void runGame(String player1, String player2) {
        BattleSeaController battleSeaController1 = new BattleSeaController();
        int counter = 1;
        int counterForRandom = 1;
        boolean endGame = true;
        while (endGame) {
            if (counter == 1) {
                battleSeaController1.addPlayersToArrayList();

                while (true) {
                    if (counterForRandom == 1) {
                        battleSeaController1.randomShipPlaceForPlayer1();
                    }
                    System.out.println(player1 + "'s Board \nIf you want to change the map for random Board please Enter \" Randomize \" ");
                    System.out.println("If you want to change ship Coordinate please enter Command \" change ship < ship Number > coordinate to < X,Y > \" and for Direction please enter \" Change ship < ship Number > direction \"");
                    System.out.println("Ships names And Numbers for change: \n" +
                            "Battleship (B) ----> 1\n" +
                            "AirCarrier (A) ----> 2\n" +
                            "Cruiser    (C) ----> 3\n" +
                            "Destroyer  (D) ----> 4\n" +
                            "Submarine  (S) ----> 5");

                    String input = scanner.nextLine();

                    if (input.equalsIgnoreCase("done")) {
                        break;
                    }
                    if (input.startsWith("change")) {
                        try {
                            battleSeaController1.changeCoordinateProcessor("player1", input);
                        } catch (PlacedShipException e) {
                            System.out.println(e.getMessage());
                        } catch (NewCoordinateForShipException e) {
                            System.out.println(e.getMessage());
                        } catch (CorrectCoordinateForShipException e) {
                            System.out.println(e.getMessage());
                        } catch (ExistOtherShipException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    //todo for change first we should reset Ship of player and player board
                    //battleSeaController.restPlayer1Board();
                    counterForRandom++;
                }
                counterForRandom = 1;
                while (true) {
                    if (counterForRandom == 1) {
                        battleSeaController1.randomShipPlaceForPlayer2();
                    }
                    System.out.println(player2 + "'s Board \nIf you want to change the map for random Board please Enter \" Randomize \" ");
                    System.out.println("If you want to change ship Coordinate please enter Command \" change ship < ship Number > coordinate to < X,Y > \" and for Direction please enter \" Change ship < ship Number > direction \"");
                    System.out.println("Ships names And Numbers for change: \n" +
                            "Battleship (B) ----> 1\n" +
                            "AirCarrier (A) ----> 2\n" +
                            "Cruiser    (C) ----> 3\n" +
                            "Destroyer  (D) ----> 4\n" +
                            "Submarine  (S) ----> 5");

                    String input = scanner.nextLine();

                    if (input.equalsIgnoreCase("done")) {
                        break;
                    }
                    if (input.startsWith("change")) {
                        try {
                            battleSeaController1.changeCoordinateProcessor("player2", input);
                        } catch (PlacedShipException e) {
                            System.out.println(e.getMessage());
                        } catch (NewCoordinateForShipException e) {
                            System.out.println(e.getMessage());
                        } catch (CorrectCoordinateForShipException e) {
                            System.out.println(e.getMessage());
                        } catch (ExistOtherShipException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    //todo for change first we should reset Ship of player and player board
                    //battleSeaController.restPlayer2Board();
                    counterForRandom++;
                }
            }
            if (counter % 2 == 1) {
                System.out.println(player1 + "'s Turn ");
            } else {
                System.out.println(player2 + "'s Turn ");
            }
            System.out.println("Game instructions: \n" +
                    "boom X,Y\n" +
                    "Show <player1 or player2> all booms\n" +
                    "show <player1 or player2> correct booms\n" +
                    "Show <player1 or player2> incorrect booms\n" +
                    "show <player1 or player2> boomed ships\n" +
                    "show <player1 or player2> unboomed ships\n" +
                    "show <player1 or player2> coordinate plane");
            String nexCommand = scanner.nextLine();

            //battleSeaController.mainCommandProcessor(player1, player2);
            if (nexCommand.equalsIgnoreCase("Surrender")) {
                if (counter % 2 == 1) {
                    System.out.println(player2 + " Wins the Game !");
                    Game.giveScoreAndEditPlayerLog("BattleShip", player2, player1, 10);
                    endGame = false;
                } else {
                    System.out.println(player1 + " Wins the Game !");
                    Game.giveScoreAndEditPlayerLog("BattleShip", player1, player2, 10);
                    endGame = false;

                }
            }
            if (counter % 2 == 1) {
                try {
                    battleSeaController1.boomOrShow("player1", nexCommand);
                } catch (BattleShipWinner battleShipWinner) {
                    battleShipWinner.setPlayerName(player2);
                    System.out.println(battleShipWinner.getPlayerName() + battleShipWinner.getMessage());
                    Game.giveScoreAndEditPlayerLog("BattleShip", player2, player1, 10);
                    endGame = false;
                }
            } else {
                try {
                    battleSeaController1.boomOrShow("player2", nexCommand);
                } catch (BattleShipWinner battleShipWinner) {
                    battleShipWinner.setPlayerName(player1);
                    System.out.println(battleShipWinner.getPlayerName() + battleShipWinner.getMessage());
                    Game.giveScoreAndEditPlayerLog("BattleShip", player1, player2, 10);
                    endGame = false;
                }
            }

            counter++;
        }
        battleSeaController1.deletePlayer();
        setUsername2(null);
        this.parentMenu.run();

    }

    public static String arrayListToString(ArrayList<String> arrayList) {
        String output = "";
        for (String string : arrayList) {
            output += string + " ";
        }
        return output;
    }


}

