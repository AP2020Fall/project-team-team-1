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
        }
        System.out.println("Please Enter End Date : ");
        while (true){
            String endDate = scanner.nextLine();
            if (Validation.dateIsValid(endDate)){
                arrayList.add(endDate);
                break;
            }
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
                getEventInfo(input);

            }
        };
    }
    private void getSuggestionInfo(ArrayList<String> arrayList){
        System.out.println("Please Enter The Username");
        String username = scanner.nextLine();
        arrayList.add(username);
        String game = scanner.nextLine();
        while (true){
            if (game.equalsIgnoreCase("battleSea") || game.equalsIgnoreCase("dotsAndBoxes")) {
                arrayList.add(game);
                break;
            }
        }
    }
    private Menu viewSuggestion(){
        return new Menu("View Suggestion",this) {
        };
    }
    private Menu viewUsers(){
        return new Menu("view all users",this) {
        };
    }
    public static String arrayListToString(ArrayList<String> arrayList){
        String output="";
        for (String string : arrayList) {
            output+=string+" ";
        }
        return output;
    }
}
