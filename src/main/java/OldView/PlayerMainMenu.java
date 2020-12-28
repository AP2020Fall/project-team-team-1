package OldView;

import Controller.Exception.Plato.*;

import java.io.IOException;
import java.util.HashMap;

public class PlayerMainMenu extends Menu {
    private String username;

    public PlayerMainMenu(Menu parentMenu, String username) {
        super("Menu", parentMenu);
        this.username = username;
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, showLevel());
        submenus.put(2, showFavoritesGames());
        submenus.put(3, showPlatoBotsMessage());
        submenus.put(4, showLastPlayed());
        submenus.put(5, showGameHistory());
        submenus.put(6, new ViewAdminSuggestion(username, this));
        submenus.put(7, addFriend());
        submenus.put(8, joinEvent());
        submenus.put(9, new PlayEvent(username, this));
        submenus.put(10, new UserMenuForPlayer(username, this));
        submenus.put(11, report());
        this.setSubmenus(submenus);
    }

    private Menu showLevel() {
        return new Menu("show Level", this) {
            @Override
            public void execute() {
                try {
                    String[] showEvent = playerGeneralController.showPoint(username).split("\\$");
                    for (String out : showEvent) {
                        System.out.println(out);
                    }

                } catch (ExistPlayerException e) {
                    System.out.println(e.getPlayerName() + e.getMessage());
                    this.parentMenu.run();
                }
                Menu nextMenu = null;
                String num = scanner.nextLine();
                if ((!num.matches("\\d+")) || Integer.parseInt(num) > submenus.size() + 1) {
                    this.run();
                } else {
                    int chosenMenu = Integer.parseInt(num);
                    if (chosenMenu == submenus.size() + 1) {
                        if (this.parentMenu == null) {
                            System.exit(1);
                        } else {
                            nextMenu = this.parentMenu;
                            nextMenu.run();
                        }
                    } else {
                        nextMenu = submenus.get(chosenMenu);
                        nextMenu.run();
                    }
                }
            }
        };
    }

    private Menu showFavoritesGames() {
        return new Menu("Favorites Games", this) {
            @Override
            public void execute() {
                try {
                    String[] showEvent = playerGeneralController.showFavoritesGames(username).split("\\$");
                    for (String out : showEvent) {
                        System.out.println(out);
                    }
                } catch (ExistFavoriteException e) {
                    System.out.println(e.getMessage());
                    this.parentMenu.run();
                }
                Menu nextMenu = null;
                String num = scanner.nextLine();
                if ((!num.matches("\\d+")) || Integer.parseInt(num) > submenus.size() + 1) {
                    this.run();
                } else {
                    int chosenMenu = Integer.parseInt(num);
                    if (chosenMenu == submenus.size() + 1) {
                        if (this.parentMenu == null) {
                            System.exit(1);
                        } else {
                            nextMenu = this.parentMenu;
                            nextMenu.run();
                        }
                    } else {
                        nextMenu = submenus.get(chosenMenu);
                        nextMenu.run();
                    }
                }
            }
        };
    }

    private Menu showPlatoBotsMessage() {
        return new Menu("PlatoBots Messages", this) {
            @Override
            public void execute() {
                String[] showEvent = playerGeneralController.viewBotMessages().split("\\$");
                for (String out : showEvent) {
                    System.out.println(out);
                }

                Menu nextMenu = null;
                String num = scanner.nextLine();
                if ((!num.matches("\\d+")) || Integer.parseInt(num) > submenus.size() + 1) {
                    this.run();
                } else {
                    int chosenMenu = Integer.parseInt(num);
                    if (chosenMenu == submenus.size() + 1) {
                        if (this.parentMenu == null) {
                            System.exit(1);
                        } else {
                            nextMenu = this.parentMenu;
                            nextMenu.run();
                        }
                    } else {
                        nextMenu = submenus.get(chosenMenu);
                        nextMenu.run();
                    }
                }
            }
        };
    }

    private Menu showLastPlayed() {
        return new Menu("Last Game Played", this) {
            @Override
            public void execute() {
                try {
                    System.out.println(playerGeneralController.showUserLastPlayed(username));
                } catch (ExistPlayerException e) {
                    System.out.println(e.getPlayerName() + e.getMessage());
                    this.parentMenu.run();
                } catch (ExistPlayerLogException e) {
                    System.out.println(e.getMessage());
                    this.parentMenu.run();
                }
                Menu nextMenu = null;
                String num = scanner.nextLine();
                if ((!num.matches("\\d+")) || Integer.parseInt(num) > submenus.size() + 1) {
                    this.run();
                } else {
                    int chosenMenu = Integer.parseInt(num);
                    if (chosenMenu == submenus.size() + 1) {
                        if (this.parentMenu == null) {
                            System.exit(1);
                        } else {
                            nextMenu = this.parentMenu;
                            nextMenu.run();
                        }
                    } else {
                        nextMenu = submenus.get(chosenMenu);
                        nextMenu.run();
                    }
                }
            }
        };
    }

    private Menu addFriend() {
        return new Menu("Add Friend", this) {
            @Override
            public void execute() {
                String friendName = requestFriendship();
                try {
                    playerGeneralController.addFriends(username, friendName);
                    System.out.println("Friend Request to " + friendName + " is Pending.");
                    this.parentMenu.run();
                } catch (ExistFriendException e) {
                    System.out.println(e.getName() + e.getMessage());
                    this.parentMenu.run();
                } catch (ExistPlayerException e) {
                    System.out.println(e.getPlayerName() + e.getMessage());
                    this.parentMenu.run();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }

            }
        };
    }

    private String requestFriendship() {
        System.out.println("Enter The Player You Want To be Friend With");
        return scanner.nextLine();
    }

    private Menu joinEvent() {
        return new Menu("join Event", this) {
            @Override
            public void show() {
                try {
                    String[] showEvent = adminGeneralController.showEvent().split("\\$");
                    for (String out : showEvent) {
                        System.out.println(out);
                    }
                    System.out.println("Please Enter The Event You want To Join :");
                } catch (ExistEventException e) {
                    System.out.println(e.getMessage());
                    this.parentMenu.run();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    this.parentMenu.run();
                }
            }

            @Override
            public void execute() {
                String eventID = scanner.nextLine();
                try {
                    playerGeneralController.joinEvent(username, eventID);
                    System.out.print(Color.GREEN);
                    System.out.println("Join Event " + eventID + " Successfully!");
                    System.out.print(Color.RESET);
                    System.out.println("if you want to join another event type continue otherwise Enter back!");
                    String nextCommand = scanner.nextLine();
                    if (nextCommand.equalsIgnoreCase("continue")) {
                        this.run();
                    } else if (nextCommand.equalsIgnoreCase("back")) {
                        this.parentMenu.run();
                    }
                } catch (ExistEventException e) {
                    System.out.println(e.getMessage());
                    this.parentMenu.run();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    this.parentMenu.run();
                }

            }
        };
    }

    public Menu showGameHistory() {
        return new Menu("Show History", this) {
            @Override
            public void show() {
                System.out.println("History of " + username);
            }

            @Override
            public void execute() {
                try {
                    String[] showEvent = playerGeneralController.showHistory(username).split("\\$");
                    for (String out : showEvent) {
                        System.out.println(out);
                    }

                    System.out.println("Enter Back To Exit This Menu");
                    if (scanner.nextLine().equals("back")) {
                        this.parentMenu.run();
                    } else this.run();
                } catch (ExistPlayerException | ExistPlayerLogException e) {
                    System.out.println(e.getMessage());
                    this.parentMenu.run();
                }
            }
        };
    }

    private Menu report() {
        return new Menu("Report", this) {
            @Override
            public void show() {
                System.out.println("Please Enter The Username You want To Report : (enter back to exit)");
            }

            @Override
            public void execute() {
                String reportedUserName = scanner.nextLine();
                if (reportedUserName.equalsIgnoreCase("back")) {
                    this.parentMenu.run();
                }
                try {
                    playerGeneralController.reportsPlayer(username, reportedUserName);
                    System.out.print(Color.GREEN);
                    System.out.println(reportedUserName + " reported Successfully!");
                    System.out.print(Color.RESET);

                } catch (ExistPlayerException | IOException e) {
                    System.out.println(e.getMessage());
                    this.run();
                }
                this.run();
            }
        };
    }


}
