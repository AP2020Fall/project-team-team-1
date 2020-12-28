package OldView;

import Controller.DotsAndBoxesController.DotsAndBoxesController;
import Controller.Exception.Plato.ExistFavoriteException;
import Controller.Exception.Plato.InvalidGameID;
import Controller.Exception.Plato.InvalidGameNameException;

import java.io.IOException;
import java.util.HashMap;

public class DotsAndBoxesMenu extends Menu {
    DotsAndBoxesController dotsAndBoxesController;
    private String username;

    public DotsAndBoxesMenu(String username, Menu parentMenu) {
        super(adminGeneralController.secondGameNameGetter() + " Menu", parentMenu);
        this.dotsAndBoxesController = new DotsAndBoxesController();
        HashMap<Integer, Menu> submenus = new HashMap<>();
        this.username = username;
        submenus.put(1, showScoreBoard());
        submenus.put(2, showDetails());
        submenus.put(3, showLog());
        submenus.put(4, showWinsCount());
        submenus.put(5, showPlayedCount());
        submenus.put(6, addToFavorites());
        submenus.put(7, removeFavorites());
        submenus.put(8, showPoints());
        submenus.put(9, new RunDotsAndBoxes(username, null,10, dotsAndBoxesController, this));
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
                    }                    this.parentMenu.run();
                } catch (InvalidGameNameException e) {
                    System.out.println(e.getGameName() + e.getMessage());
                    this.parentMenu.run();
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
                System.out.println(playerGeneralController.dotsDetails());
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
        return new Menu("show " + this.username + "'s " + adminGeneralController.secondGameNameGetter() + " GameLog", this) {
            @Override
            public void show() {
                System.out.println("Enter back to last Menu");
            }

            @Override
            public void execute() {
                try {
                    String[] showEvent = playerGeneralController.showGameLogInThisGame(username, adminGeneralController.secondGameNameGetter()).split("\\$");
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
        return new Menu("show " + this.username + " WinsCount in " + adminGeneralController.secondGameNameGetter(), this) {
            @Override
            public void show() {
                System.out.println("Enter back to last Menu");
            }

            @Override
            public void execute() {
                try {
                    System.out.println(playerGeneralController.showNumberOFWins(username, adminGeneralController.secondGameNameGetter()));
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

    private Menu showPlayedCount() {
        return new Menu("show " + this.username + " PlayedCount in " + adminGeneralController.secondGameNameGetter(), this) {
            @Override
            public void show() {
                System.out.println("Enter back to last Menu");
            }

            @Override
            public void execute() {
                try {
                    System.out.println(playerGeneralController.showNumberOfGamePlayedInThisGame(username, adminGeneralController.secondGameNameGetter()));
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

    private Menu addToFavorites() {
        return new Menu("add to favorites", this) {
            @Override
            public void show() {
                System.out.println("Enter back to last Menu");
            }

            @Override
            public void execute() {
                try {
                    playerGeneralController.addGameToFavoritesGames(username, adminGeneralController.secondGameNameGetter());
                    System.out.println(adminGeneralController.secondGameNameGetter() + " added to Your Favorites Successfully!");
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
        return new Menu("Remove from favorites", this) {
            @Override
            public void show() {
                System.out.println("Enter back to last Menu");
            }

            @Override
            public void execute() {
                try {
                    playerGeneralController.RemoveFavoritesGames(username, adminGeneralController.secondGameNameGetter());
                    System.out.println(adminGeneralController.secondGameNameGetter() + " removed from Your Favorites Successfully!");
                } catch (ExistFavoriteException e) {
                    System.out.println(e.getGameName() + " : " + e.getMessage());
                } catch (IOException | InvalidGameNameException e) {
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
        return new Menu("show " + this.username + "'s points in " + adminGeneralController.secondGameNameGetter(), this) {
            @Override
            public void show() {
                System.out.println("Enter back to last Menu");
            }

            @Override
            public void execute() {
                try {
                    System.out.println(playerGeneralController.showPlayerPointsInThisGame(username, adminGeneralController.secondGameNameGetter()));
                } catch (InvalidGameNameException e) {
                    System.out.println(e.getGameName() + e.getMessage());
                    this.run();
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
            if (adminGeneralController.activationStatus("2").equalsIgnoreCase("false")){
                System.out.print(Color.RED);
                System.out.println(adminGeneralController.secondGameNameGetter()+" Is Not Available Right now ): Try Again Later ...");
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
