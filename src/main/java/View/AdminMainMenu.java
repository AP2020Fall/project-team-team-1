package View;

import Controller.CompetencyController.Validation;

import java.util.ArrayList;
import java.util.HashMap;

public class AdminMainMenu extends Menu {
    String username;
    public AdminMainMenu( Menu parentMenu,String username) {
        super("Admin Main Menu", parentMenu);
        HashMap<Integer,Menu> submenus= new HashMap<>();
        submenus.put(1,addEvent());
        submenus.put(2,new ViewEvents(this));
        submenus.put(3,addSuggestion());
        submenus.put(4,viewSuggestion());
        submenus.put(5,viewUsers());
        //submenus.put(6,new UserMenu(username,this));
        this.setSubmenus(submenus);
        this.username=username;
    }
    private Menu addEvent(){
        return new Menu("add Event",this) {
            @Override
            public void show() {
                System.out.println("You Are About to add new Event Please Enter event information");
            }

            @Override
            public void execute() {
                ArrayList<String> input = new ArrayList<>();
                getEventInfo(input);
                if (adminEventController.addEvent(arrayListToString(input))){
                    System.out.println("Add Event Successfully");
                    parentMenu.run();
                }else this.run();

            }
        };
    }
    private void getEventInfo(ArrayList<String> arrayList){
        //todo need validation
        System.out.println("please enter the game : ");
        while (true){
            String game = scanner.nextLine();
            if (game.equalsIgnoreCase("battleShip")||game.equalsIgnoreCase("dotsAndBoxes")){
                arrayList.add(game);
                break;
            }
        }
        System.out.println("Please Enter Start Date : ");
        while (true){
            String startDate = scanner.nextLine();
            if (Validation.dateIsValid(startDate)){
                arrayList.add(startDate);
                break;
            }
            else System.out.println("NOT VALID ENTER AGAIN!");
        }
        System.out.println("Please Enter End Date : ");
        while (true){
            String endDate = scanner.nextLine();
            if (Validation.dateIsValid(endDate)){
                arrayList.add(endDate);
                break;
            }else System.out.println("NOT VALID ENTER AGAIN!");
        }
        System.out.println("Please Enter The Score Of This Event : ");
        String score = scanner.nextLine();
        arrayList.add(score);


    }
    private Menu addSuggestion(){
        return new Menu("add Suggestion",this) {
            @Override
            public void show() {
                System.out.println("Please Enter Your Suggestion");
            }

            @Override
            public void execute() {
                ArrayList<String> input = new ArrayList<>();
                getSuggestionInfo(input);
                if (adminGeneralController.addSuggestion(arrayListToString(input))){
                    System.out.println("Suggestion Added Successfully");
                    this.parentMenu.run();
                }else {
                    System.out.println("NOT VALID ENTER AGAIN!");
                    this.run();
                }

            }
        };
    }
    private void getSuggestionInfo(ArrayList<String> arrayList){
        System.out.println("Please Enter The Username : ");
        String username = scanner.nextLine();
        arrayList.add(username);
        System.out.println("Please Enter The Game Name : ");
        while (true){
            String game = scanner.nextLine();
            if (game.equalsIgnoreCase("battleShip") || game.equalsIgnoreCase("dotsAndBoxes")) {
                arrayList.add(game);
                break;
            }else System.out.println("NOT VALID ENTER AGAIN!");
        }
    }
    private Menu viewSuggestion(){
        return new Menu("View Suggestion",this) {

            @Override
            public void execute() {
                adminGeneralController.showEvent();
                System.out.println("if you want to delete an event enter remove otherwise enter back.");
                String nextStep = scanner.nextLine();
                if (nextStep.equalsIgnoreCase("remove")){
                    adminGeneralController.removeSuggestion(getSuggestionID());
                    this.parentMenu.run();
                    //Todo It Doesnt check if this username is correct or not
                }else if (nextStep.equalsIgnoreCase("back")){
                    this.parentMenu.run();
                }

            }
        };
    }
    private String getSuggestionID(){
        System.out.println("If You Want To Delete a Suggestion Please Enter The Suggestion ID : ");
        String suggestionID=scanner.nextLine();
        return suggestionID;
    }
    private Menu viewUsers(){
        return new Menu("view all users",this) {
            @Override
            public void execute() {
                adminGeneralController.showAllUsers();
                adminGeneralController.showUsersByUserName(getUsernameInformation());
                System.out.println(" enter back to get back to last menu ");
                if (scanner.nextLine().equalsIgnoreCase("back")){
                    this.parentMenu.run();
                }
            }
        };
    }
    private String getUsernameInformation(){
        System.out.println("Enter the User name : ");
        return scanner.nextLine();
    }
    public static String arrayListToString(ArrayList<String> arrayList){
        String output="";
        for (String string : arrayList) {
            output+=string+" ";
        }
        return output;
    }
}
