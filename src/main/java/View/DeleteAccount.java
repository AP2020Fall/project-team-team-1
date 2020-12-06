package View;

import Controller.CompetencyController.Validation;
import Controller.Exception.InvalidPasswordException;
import Controller.Exception.InvalidUserNameException;

import java.util.ArrayList;
import java.util.HashMap;

public class DeleteAccount extends Menu {
    public DeleteAccount(Menu parentMenu) {
        super("Delete Account", parentMenu);
        HashMap<Integer,Menu> submenus = new HashMap<>();
    }
    private Menu deleteAccount(){
        return new Menu("Delete",this) {
            @Override
            public void show() {

            }

            @Override
            public void execute() {
                ArrayList<String> input = new ArrayList<>();
                getInputPlayer(input);
                processDeleteAccountController.
            }
        };
    }
    private static void getInputPlayer(ArrayList<String> playerInput) {
        System.out.println("Please Enter Your Username");
        while (true) {
            String username = scanner.nextLine();
            try {
                Validation.usernameIsValid(username);
                playerInput.add(username);
                break;

            } catch (InvalidUserNameException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Please Enter Your Password");
        while (true) {
            String password = scanner.nextLine();
            try {
                Validation.passwordIsValid(password);
                playerInput.add(password);
                break;

            } catch (InvalidPasswordException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public static String arrayListToString(ArrayList<String> arrayList) {
        String output = "";
        for (String string : arrayList) {
            output += string + " ";
        }
        return output;
    }

}
