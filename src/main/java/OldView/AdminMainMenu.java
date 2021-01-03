package OldView;

import Controller.CompetencyController.Validation;
import Controller.Exception.Plato.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class AdminMainMenu extends Menu {
    String username;

    public AdminMainMenu(Menu parentMenu, String username) {
        super("Admin Main Menu", parentMenu);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, addEvent());
        submenus.put(2, new ViewEvents(this));
        submenus.put(3, addSuggestion());
        submenus.put(4, viewSuggestion());
//        submenus.put(5,addDetail());
        submenus.put(5, editDetails());
        submenus.put(6, viewUsers());
        submenus.put(7, new BanMenu(this));
        submenus.put(8, sendMessageAsPlatoBot());
        submenus.put(9, editGameName());
        submenus.put(10, gameOutOfServiceDueToUpdate());
        submenus.put(11, activeGameAfterUpdate());
        submenus.put(12, setGameActivity());
        submenus.put(13, new UserMenuForAdmin(username, this));
        this.setSubmenus(submenus);
        this.username = username;
    }

    private Menu addEvent() {
        return new Menu("add Event", this) {
            @Override
            public void show() {
                System.out.println("You Are About to add new Event Please Enter event information");
            }

            @Override
            public void execute() {
                ArrayList<String> input = new ArrayList<>();
                getEventInfo(input);
                try {
                    adminGeneralController.addEvent(arrayListToString(input));
                    System.out.println("Add Event Successfully");
                    parentMenu.run();
                } catch (StartDatesException e) {
                    System.out.println(e.getMessage());
                    this.run();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                } catch (ExistEventException e) {
                    System.out.println(e.getMessage());
                }

            }
        };
    }

    private void getEventInfo(ArrayList<String> arrayList) {
        System.out.println("please enter the game : ");
        while (true) {
            String game = scanner.nextLine();
            try {
                Validation.gameNameIsValid(game);
                arrayList.add(game);
                break;
            } catch (InvalidGameNameException e) {
                System.out.println(e.getGameName() + e.getMessage());
            }

        }
        System.out.println("Please Enter Start Date : ");
        while (true) {
            String startDate = scanner.nextLine();
            try {
                Validation.dateIsValid(startDate);
                arrayList.add(startDate);
                break;

            } catch (InvalidDateException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Please Enter End Date : ");
        while (true) {
            String endDate = scanner.nextLine();
            try {
                Validation.dateIsValid(endDate);
                arrayList.add(endDate);
                break;

            } catch (InvalidDateException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Please Enter The Score Of This Event : ");
        String score = scanner.nextLine();
        arrayList.add(score);

        System.out.println("Please Enter The Comment For This Event : ");
        String comment = scanner.nextLine();
        arrayList.add(comment);


    }

    private Menu addSuggestion() {
        return new Menu("add Suggestion", this) {
            @Override
            public void show() {
                System.out.println("Please Enter Your Suggestion");
            }

            @Override
            public void execute() {
                ArrayList<String> input = new ArrayList<>();
                getSuggestionInfo(input);
                try {
                    adminGeneralController.addSuggestion(arrayListToString(input));
                    System.out.println("Suggestion Added Successfully");
                    this.parentMenu.run();

                } catch (ExistPlayerException e) {
                    System.out.println(e.getPlayerName() + e.getMessage());
                    this.run();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }

            }
        };
    }

    private void getSuggestionInfo(ArrayList<String> arrayList) {
        System.out.println("Please Enter The Username : ");
        String username = scanner.nextLine();
        arrayList.add(username);
        System.out.println("Please Enter The Game Name : ");
        while (true) {
            String game = scanner.nextLine();
            try {
                Validation.gameNameIsValid(game);
                arrayList.add(game);
                break;
            } catch (InvalidGameNameException e) {
                System.out.println(e.getGameName() + e.getMessage());

            }

        }
    }

    private Menu viewSuggestion() {
        return new Menu("View Suggestion", this) {
            @Override
            public void show() {

            }

            @Override
            public void execute() {
                try {
                    String[] showEvent = adminGeneralController.showSuggestion().split("\\$");
                    for (String out : showEvent) {
                        System.out.println(out);
                    }

                } catch (ExistSuggestionException e) {
                    System.out.println(e.getMessage());
                }
                System.out.println("if you want to delete a Suggestion enter remove otherwise enter back.");
                String nextStep = scanner.nextLine();
                if (nextStep.equalsIgnoreCase("remove")) {
                    try {
                        adminGeneralController.removeSuggestion(getSuggestionID());
                        this.parentMenu.run();
                    } catch (ExistSuggestionException | IOException e) {
                        System.out.println(e.getMessage());
                        this.run();
                    }
                } else if (nextStep.equalsIgnoreCase("back")) {
                    this.parentMenu.run();
                } else this.run();

            }
        };
    }

    private String getSuggestionID() {
        System.out.println("If You Want To Delete a Suggestion Please Enter The Suggestion ID : ");
        return scanner.nextLine();
    }

    private Menu viewUsers() {
        return new Menu("view all users", this) {
            @Override
            public void show() {

            }

            @Override
            public void execute() {
                try {
                    String[] showEvent = adminGeneralController.showAllUsers().split("\\$");
                    for (String out : showEvent) {
                        System.out.println(out);
                    }
                } catch (ExistPlayerException e) {
                    System.out.println(e.getMessage());
                    this.run();
                }
                System.out.println("Enter Continue or Back");
                String nextCommand = scanner.nextLine();
                if (nextCommand.equalsIgnoreCase("back")) {
                    this.parentMenu.run();
                } else if (nextCommand.equalsIgnoreCase("continue")) {
                    try {
                        String[] showEvent = adminGeneralController.showUsersByUserName(getUsernameInformation()).split("\\$");
                        for (String out : showEvent) {
                            System.out.println(out);
                        }
                    } catch (ExistPlayerException e) {
                        System.out.println(e.getPlayerName() + e.getMessage());
                        this.run();
                    }
                    System.out.println(" enter back to get back to last menu ");
                    if (scanner.nextLine().equalsIgnoreCase("back")) {
                        this.parentMenu.run();
                    }
                } else this.run();
            }
        };
    }

    private String getUsernameInformation() {
        System.out.println("Enter the User name : ");
        return scanner.nextLine();
    }

    private Menu sendMessageAsPlatoBot() {
        return new Menu("Send Message", this) {
            @Override
            public void show() {
                System.out.println("Enter your message : (for exit enter back! )");
            }

            @Override
            public void execute() {
                try {
                    String message = scanner.nextLine();
                    if (message.equalsIgnoreCase("back")){
                        this.parentMenu.run();
                    }
                    adminGeneralController.sendMassageString(message);
                    System.out.println("Message Delivered!");
                    this.parentMenu.run();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                } catch (NotNullMessageException e) {
                    System.out.println(e.getMessage());
                    this.run();
                }
            }
        };
    }

//    private Menu addDetail() {
//        return new Menu("Add Details", this) {
//            @Override
//            public void show() {
//                System.out.println("Which Game Do You Want to Add Details to?" + adminGeneralController.firstGameNameGetter() + " OR " + adminGeneralController.secondGameNameGetter());
//            }
//
//            @Override
//            public void execute() {
//                String game = scanner.nextLine();
//                //todo ask hesam to add add details controller
//            }
//        };
//    }

    private String getText() {
        if (this.getName().equalsIgnoreCase("Add Details")) {
            System.out.println("Please Enter Your Detail For This Game : ");
        } else if (this.getName().equalsIgnoreCase("Send Message")) {
            System.out.println("Please Enter Your Message : ");
        }
        return scanner.nextLine();
    }

    private Menu editDetails() {
        return new Menu("Edit Details", this) {
            @Override
            public void show() {
                System.out.println("Which Game Do You Want to Edit? ");
            }

            @Override
            public void execute() {
                String gameName = scanner.nextLine();
                if (gameName.equalsIgnoreCase(adminGeneralController.firstGameNameGetter())) {
                    editBattleShipDetails().run();
                } else if (gameName.equalsIgnoreCase(adminGeneralController.secondGameNameGetter())) {
                    editDotsAndBoxesDetails().run();
                }
            }
        };
    }

    private Menu editBattleShipDetails() {
        return new Menu("Edit " + adminGeneralController.firstGameNameGetter() + " Details", this) {
            @Override
            public void show() {
                System.out.println(playerGeneralController.battleDetails());
            }

            @Override
            public void execute() {
                System.out.println("Please Enter new Details");
                try {
                    adminGeneralController.setDetails(adminGeneralController.firstGameNameGetter(), scanner.nextLine());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                this.parentMenu.run();
            }
        };
    }

    private Menu editDotsAndBoxesDetails() {
        return new Menu("Edit " + adminGeneralController.secondGameNameGetter() + " Details", this) {
            @Override
            public void show() {
                System.out.println(playerGeneralController.dotsDetails());
            }

            @Override
            public void execute() {
                System.out.println("Please Enter new Details");
                try {
                    adminGeneralController.setDetails(adminGeneralController.secondGameNameGetter(), scanner.nextLine());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                this.parentMenu.run();
            }
        };
    }

    private Menu editGameName() {
        return new Menu("edit Game Name", this) {
            @Override
            public void show() {
                System.out.println("which Game You Want to Edit : ( For Exit enter Back )");
                System.out.println(adminGeneralController.firstGameNameGetter());
                System.out.println(adminGeneralController.secondGameNameGetter());
            }

            @Override
            public void execute() {
                String game = scanner.nextLine();

                if (game.equalsIgnoreCase(adminGeneralController.firstGameNameGetter())) {
                    try {
                        System.out.println("Please enter new name : ");
                        adminGeneralController.changeGameName("1", scanner.nextLine());
                        System.out.println("GameName changed Successfully");
                        this.parentMenu.run();
                    } catch (InvalidGameID invalidGameID) {
                        invalidGameID.printStackTrace();
                        this.run();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                        this.run();
                    }
                } else if (game.equalsIgnoreCase(adminGeneralController.secondGameNameGetter())) {
                    try {
                        System.out.println("Please enter new name : ");
                        adminGeneralController.changeGameName("2", scanner.nextLine());
                        System.out.println("gameName Changed Successfully");
                        this.parentMenu.run();
                    } catch (InvalidGameID invalidGameID) {
                        invalidGameID.printStackTrace();
                        this.run();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                        this.run();
                    }
                } else if (game.equalsIgnoreCase("back")) {
                    this.parentMenu.run();
                } else {
                    System.out.println("Try Again");
                    this.run();
                }
            }
        };
    }

    private Menu gameOutOfServiceDueToUpdate() {
        return new Menu("Disable Game For Update", this) {
            @Override
            public void show() {
                System.out.println("Which game You Want To Update ? ( For Exit enter Back)");
                System.out.println(adminGeneralController.firstGameNameGetter());
                System.out.println(adminGeneralController.secondGameNameGetter());
            }

            @Override
            public void execute() {
                String gameName = scanner.nextLine();

                if (gameName.equals(adminGeneralController.firstGameNameGetter())) {
                    try {
                        adminGeneralController.activeMaintenanceMode("1");
                        System.out.println(adminGeneralController.firstGameNameGetter() + " DeActivate Successfully");
                        this.parentMenu.run();
                    } catch (InvalidGameID | GameMaintenance | IOException invalidGameID) {
                        System.out.println(invalidGameID.getMessage());
                        this.run();
                    }
                } else if (gameName.equals(adminGeneralController.secondGameNameGetter())) {
                    try {
                        adminGeneralController.activeMaintenanceMode("2");
                        System.out.println(adminGeneralController.secondGameNameGetter() + " DeActivate Successfully");
                        this.parentMenu.run();
                    } catch (InvalidGameID | GameMaintenance | IOException invalidGameID) {
                        System.out.println(invalidGameID.getMessage());
                        this.run();
                    }
                } else if (gameName.equalsIgnoreCase("back")) {
                    this.parentMenu.run();
                } else {
                    System.out.println("Try Again");
                    this.run();
                }
            }
        };
    }

    private Menu activeGameAfterUpdate() {
        return new Menu("Active Game After Update", this) {
            @Override
            public void show() {
                System.out.println("which Game You Want To Active ? ( For Exit enter Back)");
                System.out.println(adminGeneralController.firstGameNameGetter());
                System.out.println(adminGeneralController.secondGameNameGetter());
            }

            @Override
            public void execute() {
                String gameName = scanner.nextLine();

                if (gameName.equals(adminGeneralController.firstGameNameGetter())) {
                    try {
                        adminGeneralController.deActiveMaintenanceMode("1");
                        System.out.println(adminGeneralController.firstGameNameGetter() + " Activate Successfully");
                        this.parentMenu.run();
                    } catch (InvalidGameID | IOException | GameMaintenance invalidGameID) {
                        System.out.println(invalidGameID.getMessage());
                        this.run();
                    }
                } else if (gameName.equals(adminGeneralController.secondGameNameGetter())) {
                    try {
                        adminGeneralController.deActiveGame("2");
                        System.out.println(adminGeneralController.secondGameNameGetter() + " Activate Successfully");
                        this.parentMenu.run();
                    } catch (InvalidGameID | GameActivation | IOException invalidGameID) {
                        System.out.println(invalidGameID.getMessage());
                        this.run();
                    }
                } else if (gameName.equalsIgnoreCase("back")) {
                    this.parentMenu.run();
                } else {
                    System.out.println("Try Again");
                    this.run();
                }

            }
        };
    }

    private Menu setGameActivity() {
        return new Menu("Set Games Activity", this) {
            @Override
            public void show() {
                System.out.println("Active/DeActive");
            }

            @Override
            public void execute() {
                String Activity = setActivity();
                if (Activity.equalsIgnoreCase("active")) {
                    if (!checkIfGameIsActive(adminGeneralController.firstGameNameGetter())) {
                        System.out.println(adminGeneralController.firstGameNameGetter());
                    }
                    if (!checkIfGameIsActive(adminGeneralController.secondGameNameGetter())) {
                        System.out.println(adminGeneralController.secondGameNameGetter());
                    }
                    System.out.println("Which Game You Want to Active from above list ?  ( For Exit enter Back)");
                    String gameName = scanner.nextLine();

                    if (gameName.equalsIgnoreCase(adminGeneralController.firstGameNameGetter())) {
                        try {
                            adminGeneralController.activeGame("1");
                            System.out.println(adminGeneralController.firstGameNameGetter() + " Activate successfully !");
                            this.parentMenu.run();
                        } catch (InvalidGameID | GameActivation | IOException invalidGameID) {
                            System.out.println(invalidGameID.getMessage());
                            this.run();
                        }
                    } else if (gameName.equalsIgnoreCase(adminGeneralController.secondGameNameGetter())) {
                        try {
                            adminGeneralController.activationStatus("2");
                            System.out.println(adminGeneralController.secondGameNameGetter() + " Activate successfully !");
                            this.parentMenu.run();
                        } catch (InvalidGameID invalidGameID) {
                            System.out.println(invalidGameID.getMessage());
                            this.run();
                        }
                    } else if (gameName.equalsIgnoreCase("back")) {
                        this.parentMenu.run();
                    } else {
                        System.out.println("Try Again");
                        this.run();
                    }
                } else if (Activity.equalsIgnoreCase("deActive")) {
                    if (checkIfGameIsActive(adminGeneralController.firstGameNameGetter())) {
                        System.out.println(adminGeneralController.firstGameNameGetter());
                    }
                    if (checkIfGameIsActive(adminGeneralController.secondGameNameGetter())) {
                        System.out.println(adminGeneralController.secondGameNameGetter());
                    }
                    System.out.println("Which Game You Want To DeActive from above list ?  ( For Exit enter Back)");
                    String gameName = scanner.nextLine();

                    if (gameName.equalsIgnoreCase(adminGeneralController.firstGameNameGetter())) {
                        try {
                            adminGeneralController.deActiveGame("1");
                            System.out.println(adminGeneralController.firstGameNameGetter() + " Deactivate successfully !");
                            this.parentMenu.run();
                        } catch (InvalidGameID | IOException | GameActivation invalidGameID) {
                            System.out.println(invalidGameID.getMessage());
                            this.run();
                        }
                    } else if (gameName.equalsIgnoreCase(adminGeneralController.secondGameNameGetter())) {
                        try {
                            adminGeneralController.deActiveGame("2");
                            System.out.println(adminGeneralController.secondGameNameGetter() + " Deactivate successfully !");
                            this.parentMenu.run();
                        } catch (InvalidGameID | GameActivation | IOException invalidGameID) {
                            System.out.println(invalidGameID.getMessage());
                            this.run();
                        }
                    } else if (gameName.equalsIgnoreCase("back")) {
                        this.parentMenu.run();
                    } else {
                        System.out.println("Try Again");
                        this.run();
                    }
                }
            }
        };
    }

    private String setActivity() {
        String activity = scanner.nextLine();
        if (activity.equalsIgnoreCase("active")) {
            return "active";
        } else if (activity.equalsIgnoreCase("deActive")) {
            return "deActive";
        }
        return "active";
    }

    private boolean checkIfGameIsActive(String game) {
        if (game.equals(adminGeneralController.firstGameNameGetter())) {
            try {
                if (adminGeneralController.activationStatus("1").equalsIgnoreCase("true")) {
                    return true;
                } else
                    return false;

            } catch (InvalidGameID invalidGameID) {
                System.out.println(invalidGameID.getMessage());
                this.run();
            }
        } else if (game.equals(adminGeneralController.secondGameNameGetter())) {
            try {
                if (adminGeneralController.activationStatus("2").equalsIgnoreCase("true")) {
                    return true;
                } else
                    return false;
            } catch (InvalidGameID invalidGameID) {
                System.out.println(invalidGameID.getMessage());
                this.run();
            }
        }
        return false;
    }


    public static String arrayListToString(ArrayList<String> arrayList) {
        String output = "";
        for (String string : arrayList) {
            output += string + " ";
        }
        return output;
    }

    @Override
    public void show() {
        for (Integer menuNum : submenus.keySet()) {
            System.out.println(menuNum + ". " + submenus.get(menuNum).getName());
        }
    }
}
