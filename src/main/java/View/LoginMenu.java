package View;

import Controller.CompetencyController.Validation;

import java.util.ArrayList;
import java.util.HashMap;

public class LoginMenu extends Menu {
    public LoginMenu( Menu parentMenu) {
        super("login Menu", parentMenu);
        HashMap<Integer,Menu> submenus = new HashMap<>();
        submenus.put(1,signInAsAdminMenu());
        submenus.put(2,signInAsPlayer());
        this.setSubmenus(submenus);
    }
    private Menu signInAsAdminMenu(){
        return new Menu("SignIn As Admin ",this) {
            @Override
            public void show() {
                System.out.println(this.getName()+": ");
            }

            @Override
            public void execute() {
                ArrayList<String> input = new ArrayList<>();
                getInputAdmin(input);
                processLoginController.loginAsAdmin(arrayListToString(input));
                //todo link sign in to next menu from here
            }
        };
    }
    private Menu signInAsPlayer(){
        return new Menu("Sign in As Player",this) {
            @Override
            public void show() {
                System.out.println(this.getName()+": ");
            }

            @Override
            public void execute() {
                ArrayList<String> input = new ArrayList<>();
                getInputAdmin(input);
                processLoginController.loginAsAdmin(arrayListToString(input));
            }
        };
    }
    private static void getInputPlayer(ArrayList<String> playerInput){
        System.out.println("Please Enter Your Username");
        while (true){
            String username = scanner.nextLine();
            if (Validation.usernameIsValid(username)){
                playerInput.add(username);
                break;
            }else System.out.println("Please Enter Valid Username");
        }
        System.out.println("Please Enter Your Password");
        while (true){
            String password = scanner.nextLine();
            if (Validation.passwordIsValid(password)){
                playerInput.add(password);
                break;
            }else System.out.println("Please Enter Valid Password");
        }
    }
    private static void getInputAdmin(ArrayList<String> adminInput){
        System.out.println("Please Enter Your Username");
        while (true){
            String username = scanner.nextLine();
            if (Validation.usernameIsValid(username)){
                adminInput.add(username);
                break;
            }else System.out.println("Please Enter Valid Username");
        }
        System.out.println("Please Enter Your Password");
        while (true){
            String password = scanner.nextLine();
            if (Validation.passwordIsValid(password)){
                adminInput.add(password);
                break;
            }else System.out.println("Please Enter Valid Password");
        }

    }
    //todo next menu to admin menu

    @Override
    public void run() {
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
