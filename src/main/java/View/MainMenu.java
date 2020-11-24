package View;

import Controller.CompetencyController.Validation;
import Controller.RegisterController.Delete;

import java.util.ArrayList;
import java.util.HashMap;

public class MainMenu extends Menu {
    public MainMenu() {
        super("Main Menu", null);
        HashMap<Integer,Menu> submenus = new HashMap<>();
        submenus.put(1,new LoginMenu(this));
        submenus.put(2,new SignUpMenu(this));
        this.setSubmenus(submenus);
    }
    private Menu DeleteUser(){
        return new Menu("Delete User",this) {
            @Override
            public void show() {
                System.out.println("You R About To Delete Your Account");
            }

            @Override
            public void execute() {
                ArrayList<String> deleteInfo = new ArrayList<>();
                getInfo(deleteInfo);
                Delete.deleteUser(arrayListToString(deleteInfo));
                if (){

                }else {
                    System.out.println("Something goes wrong Try Again mate!");
                    this.run();
                }
            }
        };
    }
    private void getInfo(ArrayList info){
        System.out.println("Please Enter Username : ");
        while (true){
            String username = scanner.nextLine();
            if (Validation.usernameIsValid(username)){
                info.add(username);
                break;
            }
        }
        System.out.println("Please Enter password : ");
        while (true){
            String password = scanner.nextLine();
            if (Validation.passwordIsValid(password)){
                info.add(password);
                break;
            }
        }
    }

    @Override
    public void run() {
        System.out.println("Welcome");
        this.show();
        this.execute();
    }
    public static String arrayListToString(ArrayList<String> arrayList){
        String output="";
        for (String string : arrayList) {
            output+=string+" ";
        }
        return output;
    }
}
