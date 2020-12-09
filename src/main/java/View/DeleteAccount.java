package View;

import Controller.CompetencyController.Validation;
import Controller.Exception.Plato.InvalidUserNameException;
import Controller.Exception.Plato.StrongerPasswordException;
import Controller.Exception.Plato.WrongPasswordException;

import java.io.IOException;
import java.util.ArrayList;

public class DeleteAccount extends Menu {
    public DeleteAccount(Menu parentMenu) {
        super("Delete Account", parentMenu);
//        HashMap<Integer,Menu> submenus = new HashMap<>();
//        submenus.put(1,deleteAccount());
//        this.setSubmenus(submenus);
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
                try {
                    processDeleteAccountController.deleteUser(arrayListToString(input));
                    System.out.println(input.get(0)+" deleted Successfully");
                    this.parentMenu.parentMenu.run();
                } catch (InvalidUserNameException | WrongPasswordException | IOException e) {
                    System.out.println(e.getMessage());
                    this.run();
                }
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

            } catch (StrongerPasswordException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void show() {
        System.out.println("You Are About To Delete An Account Are You Sure ? Yes/No");
    }

    @Override
    public void execute() {
        String continueDeleting = scanner.nextLine();
        if (continueDeleting.equalsIgnoreCase("no")){
            System.out.println("oOoOps That Was Close @_@ ");
            this.parentMenu.run();
        }else deleteAccount().run();
    }

    public static String arrayListToString(ArrayList<String> arrayList) {
        String output = "";
        for (String string : arrayList) {
            output += string + " ";
        }
        return output;
    }

}
