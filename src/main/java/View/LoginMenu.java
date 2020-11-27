package View;

import Controller.CompetencyController.Validation;
import Controller.Exception.ExistAdminException;
import Controller.Exception.InvalidPasswordException;
import Controller.Exception.InvalidUserNameException;
import Controller.Exception.WrongPasswordException;

import java.util.ArrayList;
import java.util.HashMap;

public class LoginMenu extends Menu {

    public LoginMenu(Menu parentMenu) {
        super("login Menu", parentMenu);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, signInAsAdminMenu());
        submenus.put(2, signInAsPlayer());
        this.setSubmenus(submenus);
    }

    private Menu signInAsAdminMenu() {
        return new Menu("SignIn As Admin ", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ": ");
            }

            @Override
            public void execute() {
                Menu nextMenu = null;
                ArrayList<String> input = new ArrayList<>();
                getInputAdmin(input);
                AdminMainMenu adminMainMenu = new AdminMainMenu(this, input.get(0));
//                processLoginController.loginAsAdmin(arrayListToString(input));
                try {
                    processLoginController.loginAsAdmin(arrayListToString(input));
                    System.out.println("You Signed in Successfully");
                    nextMenu = adminMainMenu;
                    nextMenu.run();

//                        System.out.println("Username Or Password is Invalid!");
//                        this.run();

                } catch (InvalidUserNameException e) {
                    System.out.println(e.getUserName() + e.getMessage());
                    this.parentMenu.run();
                } catch (ExistAdminException e) {
                    System.out.println(e.getMessage());
                    this.parentMenu.run();
                } catch (WrongPasswordException e) {
                    System.out.println(e.getMessage());
                    this.parentMenu.run();
                }
            }
        };
    }

    private Menu signInAsPlayer() {
        return new Menu("Sign in As Player", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ": ");
            }

            @Override
            public void execute() {
                ArrayList<String> input = new ArrayList<>();
                getInputPlayer(input);
                try {
                    processLoginController.loginAsPlayer(arrayListToString(input));
                    System.out.println(" Login successfully !");
                    //todo this.runNextMenu ();
                } catch (InvalidUserNameException e) {
                    System.out.println(e.getUserName() + e.getMessage());
                    this.parentMenu.run();
                } catch (WrongPasswordException e) {
                    System.out.println(e.getMessage());
                    this.parentMenu.run();
                }
                //todo link to player menu
                //this.parentMenu.run();

                //todo the upper command is just temporary
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

    private static void getInputAdmin(ArrayList<String> adminInput) {
        System.out.println("Please Enter Your Username");
        while (true) {
            String username = scanner.nextLine();
            try {
                Validation.usernameIsValid(username);
                adminInput.add(username);
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
                adminInput.add(password);
                break;

            } catch (InvalidPasswordException e) {
                System.out.println(e.getMessage());
            }
        }

    }
    //todo next menu to admin menu

    @Override
    public void run() {
        this.show();
        this.execute();
    }

    public static String arrayListToString(ArrayList<String> arrayList) {
        String output = "";
        for (String string : arrayList) {
            output += string + " ";
        }
        return output;
    }

}
