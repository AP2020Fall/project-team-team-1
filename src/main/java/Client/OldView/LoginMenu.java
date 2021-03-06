package Client.OldView;

import Server.Controller.CompetencyController.Validation;
import Server.Controller.Exception.Plato.*;
import Server.Controller.RegisterController.LogIn;

import java.io.IOException;
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
        return new Menu("SignIn As Player", this) {
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
                    System.out.println("Login successfully !");
                    PlayerMainPage playerMainPage = new PlayerMainPage(input.get(0), this);
                    playerMainPage.run();
                } catch (InvalidUserNameException e) {
                    System.out.println(e.getUserName() + e.getMessage());
                    this.parentMenu.run();
                } catch (WrongPasswordException e) {
                    System.out.println(e.getMessage());
                    this.parentMenu.run();
                } catch (BanExceptionForLogin banExceptionForLogin) {
                    System.out.println(banExceptionForLogin.getMessage());
                    this.parentMenu.run();
                } catch (AlreadyBan alreadyBan) {
                    System.out.println(alreadyBan.getMessage());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }

            }
        };
    }

    private static void getInputPlayer(ArrayList<String> playerInput) {
        System.out.println("Please Enter Your Username");
        while (true) {
            String username = scanner.nextLine();
            if (username.equals(LogIn.getUsername())) {
                if (LogIn.isActive()) {
                    playerInput.add(username);
                    playerInput.add(LogIn.getPassword());
                    return;
                }
            }
            try {
                Validation.usernameIsValid(username);
                playerInput.add(username);
                LogIn.setUsername(username);
                break;

            } catch (InvalidUserNameException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Please Enter Your Password");
        while (true) {
            String password = scanner.nextLine();
//            try {
                //Validation.passwordIsValid(password);
                playerInput.add(password);
//                LogIn.setPassword(password);
                break;

//            } catch (StrongerPasswordException e) {
//                System.out.println(e.getMessage());
//            }
        }
        System.out.println("Remember me ? YES/NO");
        while (true) {
            String remember = scanner.nextLine();

            try {
                Validation.rememberInputValidation(remember);
                LogIn.setActive(remember);
                break;
            } catch (RememberMeException e) {
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
//            try {
                //Validation.passwordIsValid(password);
                adminInput.add(password);
                break;

//            } catch (StrongerPasswordException e) {
//                System.out.println(e.getMessage());
//            }
        }

    }

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
