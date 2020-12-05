package View;

import Controller.Exception.ExistFavoriteException;
import Controller.Exception.InvalidGameNameException;

import java.util.HashMap;

public class BattleShipMenu extends Menu {
    private String username;

    public BattleShipMenu(String username, Menu parentMenu) {
        super("BattleShip Menu", parentMenu);
        this.username = username;
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, showScoreBoard());
        submenus.put(2, showDetails());
        submenus.put(3, showLog());
        submenus.put(4, showWinsCount());
        submenus.put(5, showPlayedCount());
        submenus.put(6, addToFavorites());
        submenus.put(7, showPoints());
        submenus.put(8, new RunBattleShip(username, null, this));
        this.setSubmenus(submenus);
    }

    private Menu showScoreBoard() {
        return new Menu("show Score Board", this) {
            @Override
            public void show() {
                System.out.println("Enter back to last Menu");
            }

            @Override
            public void execute() {
                try {
                    playerGeneralController.showScoreboardInThisGame("BattleShip");
                    while (true) {
                        String next = scanner.nextLine();
                        if (next.equalsIgnoreCase("back")) {
                            this.parentMenu.run();
                            break;
                        }
                    }
                } catch (InvalidGameNameException e) {
                    System.out.println(e.getGameName() + e.getMessage());
                    this.run();
                }
            }
        };
    }

    private Menu showDetails() {
        return new Menu("show Details", this) {
            @Override
            public void show() {
                System.out.println("Enter back to last Menu");
            }

            @Override
            public void execute() {
                //todo fill this fucking shit
                battleSeaController.details();
                while (true) {
                    String next = scanner.nextLine();
                    if (next.equalsIgnoreCase("back")) {
                        this.parentMenu.run();
                        break;
                    }
                }
            }
        };
    }

    private Menu showLog() {
        return new Menu("show " + this.username + "'s Battleship GameLog", this) {
            @Override
            public void show() {
                System.out.println("Enter back to last Menu");
            }

            @Override
            public void execute() {
                try {
                    playerGeneralController.showGameLogInThisGame(username, "battleship");
                    while (true) {
                        String next = scanner.nextLine();
                        if (next.equalsIgnoreCase("back")) {
                            this.parentMenu.run();
                            break;
                        }
                    }
                } catch (InvalidGameNameException e) {
                    System.out.println(e.getGameName() + e.getMessage());
                    this.run();
                }

            }
        };
    }

    private Menu showWinsCount() {
        return new Menu("show " + this.username + " WinsCount in BattleShip", this) {
            @Override
            public void show() {
                System.out.println("Enter back to last Menu");
            }

            @Override
            public void execute() {
                try {
                    playerGeneralController.showNumberOFWins(username, "battleship");
                    while (true) {
                        String next = scanner.nextLine();
                        if (next.equalsIgnoreCase("back")) {
                            this.parentMenu.run();
                            break;
                        }
                    }
                } catch (InvalidGameNameException e) {
                    System.out.println(e.getGameName() + e.getMessage());
                }

            }
        };
    }

    private Menu showPlayedCount() {
        return new Menu("show " + this.username + " PlayedCount in BattleShip", this) {
            @Override
            public void show() {
                System.out.println("Enter back to last Menu");
            }

            @Override
            public void execute() {
                try {
                    playerGeneralController.showNumberOfGamePlayedInThisGame(username, "battleship");
                    while (true) {
                        String next = scanner.nextLine();
                        if (next.equalsIgnoreCase("back")) {
                            this.parentMenu.run();
                            break;
                        }
                    }
                } catch (InvalidGameNameException e) {
                    System.out.println(e.getGameName()+e.getMessage());
                }

            }
        };
    }

    private Menu addToFavorites() {
        return new Menu("add to favorites", this) {
            @Override
            public void show() {
                System.out.println("Enter back to last Menu");
            }

            @Override
            public void execute() {
                try {
                    playerGeneralController.addGameToFavoritesGames(username, "battleship");
                    System.out.println("battleship added to Your Favorites Successfully!");
                } catch (ExistFavoriteException e) {
                    System.out.println(e.getGameName() + " " + e.getMessage());
                } catch (InvalidGameNameException e) {
                    System.out.println(e.getGameName() + " " + e.getMessage());
                }
                while (true) {
                    String next = scanner.nextLine();
                    if (next.equalsIgnoreCase("back")) {
                        this.parentMenu.run();
                        break;
                    }
                }
            }
        };
    }

    private Menu showPoints() {
        return new Menu("show " + this.username + "'s points in BattleShip", this) {
            @Override
            public void show() {
                System.out.println("Enter back to last Menu");
            }

            @Override
            public void execute() {
                try {
                    playerGeneralController.showPlayerPointsInThisGame(username, "BattleShip");
                } catch (InvalidGameNameException e) {
                    System.out.println(e.getGameName());
                }
                while (true) {
                    String next = scanner.nextLine();
                    if (next.equalsIgnoreCase("back")) {
                        this.parentMenu.run();
                        break;
                    }
                }

            }
        };
    }
}

