package View;

import Controller.Exception.Plato.ExistFavoriteException;
import Controller.Exception.Plato.InvalidGameID;
import Controller.Exception.Plato.InvalidGameNameException;

import java.io.IOException;
import java.util.HashMap;

public class BattleShipMenu extends Menu {
    private String username;
    //todo check line 12
    public BattleShipMenu(String username, Menu parentMenu) {
        super(adminGeneralController.firstGameNameGetter()+" Menu", parentMenu);
        this.username = username;
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, showScoreBoard());
        submenus.put(2, showDetails());
        submenus.put(3, showLog());
        submenus.put(4, showWinsCount());
        submenus.put(5, showPlayedCount());
        submenus.put(6, addToFavorites());
        submenus.put(7,removeFavorites());
        submenus.put(8, showPoints());
        submenus.put(9, new RunBattleShip(username, null,10, this));
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
                    String[] showEvent = playerGeneralController.showScoreboardInThisGame(adminGeneralController.firstGameNameGetter()).split("\\$");
                    for (String out : showEvent) {
                        System.out.println(out);
                    }

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
                System.out.println(playerGeneralController.battleDetails());
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
        return new Menu("show " + this.username + "'s "+adminGeneralController.firstGameNameGetter()+" GameLog", this) {
            @Override
            public void show() {
                System.out.println("Enter back to last Menu");
            }

            @Override
            public void execute() {
                try {
                    String[] showEvent = playerGeneralController.showGameLogInThisGame(username, adminGeneralController.firstGameNameGetter()).split("\\$");
                    for (String out : showEvent) {
                        System.out.println(out);
                    }

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
        return new Menu("show " + this.username + " WinsCount in "+adminGeneralController.firstGameNameGetter(), this) {
            @Override
            public void show() {
                System.out.println("Enter back to last Menu");
            }

            @Override
            public void execute() {
                try {
                    System.out.println(playerGeneralController.showNumberOFWins(username, adminGeneralController.firstGameNameGetter()));
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
        return new Menu("show " + this.username + " PlayedCount in "+adminGeneralController.firstGameNameGetter(), this) {
            @Override
            public void show() {
                System.out.println("Enter back to last Menu");
            }

            @Override
            public void execute() {
                try {
                    System.out.println(playerGeneralController.showNumberOfGamePlayedInThisGame(username, adminGeneralController.firstGameNameGetter()));
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
                    playerGeneralController.addGameToFavoritesGames(username, adminGeneralController.firstGameNameGetter());
                    System.out.println(adminGeneralController.firstGameNameGetter()+" added to Your Favorites Successfully!");
                } catch (ExistFavoriteException e) {
                    System.out.println(e.getGameName() + " " + e.getMessage());
                } catch (InvalidGameNameException e) {
                    System.out.println(e.getGameName() + " " + e.getMessage());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
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
    private Menu removeFavorites() {
        return new Menu("remove from favorites", this) {
            @Override
            public void show() {
                System.out.println("Enter back to last Menu");
            }

            @Override
            public void execute() {
                try {
                    playerGeneralController.RemoveFavoritesGames(username, adminGeneralController.firstGameNameGetter());
                    System.out.println(adminGeneralController.firstGameNameGetter()+" removed from Favorites Successfully!");
                } catch (ExistFavoriteException e) {
                    System.out.println(e.getGameName() + " : " + e.getMessage());
                } catch (InvalidGameNameException e) {
                    System.out.println(e.getGameName() + " " + e.getMessage());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
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
        return new Menu("show " + this.username + "'s points in "+adminGeneralController.firstGameNameGetter(), this) {
            @Override
            public void show() {
                System.out.println("Enter back to last Menu");
            }

            @Override
            public void execute() {
                try {
                    System.out.println(playerGeneralController.showPlayerPointsInThisGame(username, adminGeneralController.firstGameNameGetter()));
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

    @Override
    public void run() {
        try {
            if (adminGeneralController.activationStatus("1").equalsIgnoreCase("false")){
                System.out.print(Color.RED);
                System.out.println(adminGeneralController.firstGameNameGetter()+" Is Not Available Right now ): Try Again Later ...");
                System.out.print(Color.RESET);
                this.parentMenu.run();
            }else {
                show();
                execute();
            }
        } catch (InvalidGameID invalidGameID) {
            System.out.println(invalidGameID.getMessage());
        }

    }
}

