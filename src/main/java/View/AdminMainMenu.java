package View;

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
        submenus.put(5,addDetail());
        submenus.put(6, viewUsers());
        submenus.put(7,sendMessageAsPlatoBot());
        submenus.put(8, new UserMenuForAdmin(username, this));
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
                    e.printStackTrace();
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
                    e.printStackTrace();
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
                    adminGeneralController.showSuggestion();
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
                }else this.run();

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
                    adminGeneralController.showAllUsers();
                } catch (ExistPlayerException e) {
                    System.out.println(e.getMessage());
                    this.run();
                }
                System.out.println("Enter Continue or Back");
                String nextCommand = scanner.nextLine();
                if (nextCommand.equalsIgnoreCase("back")){
                    this.parentMenu.run();
                }else if (nextCommand.equalsIgnoreCase("continue")){
                    try {
                        adminGeneralController.showUsersByUserName(getUsernameInformation());
                    } catch (ExistPlayerException e) {
                        System.out.println(e.getPlayerName() + e.getMessage());
                        this.run();
                    }
                    System.out.println(" enter back to get back to last menu ");
                    if (scanner.nextLine().equalsIgnoreCase("back")) {
                        this.parentMenu.run();
                    }
                }else this.run();
            }
        };
    }

    private String getUsernameInformation() {
        System.out.println("Enter the User name : ");
        return scanner.nextLine();
    }
    private Menu sendMessageAsPlatoBot(){
        return new Menu("Send Message" , this) {
            @Override
            public void show() {
            }

            @Override
            public void execute() {
                try {
                    adminGeneralController.sendMassageString(getText());
                    System.out.println("Message Delivered!");
                    this.parentMenu.run();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (NotNullMessageException e) {
                    System.out.println(e.getMessage());
                    this.run();
                }
            }
        };
    }
    private Menu addDetail(){
        return new Menu("Add Details",this) {
            @Override
            public void show() {
                System.out.println("Which Game Do You Want to Add Details to? DotsAndBoxes/BattleShips");
            }

            @Override
            public void execute() {
                String game = scanner.nextLine();
                //todo ask hesam to add add details controller
            }
        };
    }
    private String getText(){
        if (this.getName().equalsIgnoreCase("Add Details")){
            System.out.println("Please Enter Your Detail For This Game : ");
        }else if (this.getName().equalsIgnoreCase("Send Message")){
            System.out.println("Please Enter Your Message : ");
        }
        return scanner.nextLine();
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
