package View;

import Controller.CompetencyController.Validation;
import Controller.Exception.*;
import Controller.RegisterController.SignUp;
import org.junit.experimental.theories.suppliers.TestedOn;

import java.util.ArrayList;
import java.util.HashMap;


//TODO link signup menu and proper menu
public class SignUpMenu extends Menu {
    private Menu LoginMenu;

    public SignUpMenu(Menu parentMenu) {
        super("Signup Menu", parentMenu);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, registerAsAdmin());
        submenus.put(2, registerAsPlayer());
        this.setSubmenus(submenus);
    }

    private Menu registerAsAdmin() {
        return new Menu("Register As Admin", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ": ");
            }

            @Override
            public void execute() {
                ArrayList<String> adminInfo = new ArrayList<>();
                getAdminInformation(adminInfo);
                try {
                    processSignupController.addAdmin(arrayListToString(adminInfo));
                    System.out.println("Admin Registered Successfully");
                    this.parentMenu.parentMenu.submenus.get(1).run();
                } catch (ExistAdminException e) {
                    System.out.println(e.getMessage());
                    this.run();
                } catch (ExistUserNameException e) {
                    System.out.println(e.getMessage());
                    this.run();
                } catch (ExistEmailException e) {
                    System.out.println(e.getMessage());
                    this.run();
                } catch (EmptyExceptionForUserName e) {
                    System.out.println(e.getMessage());
                    this.run();
                } catch (EmptyExceptionForLastName e) {
                    System.out.println(e.getMessage());
                    this.run();
                } catch (EmptyExceptionForName e) {
                    System.out.println(e.getMessage());
                    this.run();
                } catch (EmptyExceptionForEmail e) {
                    System.out.println(e.getMessage());
                    this.run();
                }

            }
        };
    }

    private Menu registerAsPlayer() {
        return new Menu("Register As Player", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ": ");
            }

            @Override
            public void execute() {
                ArrayList<String> playerInfo = new ArrayList<>();
                getPlayerInfo(playerInfo);
                try {
                    processSignupController.addPlayer(arrayListToString(playerInfo));
                    System.out.println("Player Registered Successfully");
                    this.parentMenu.parentMenu.submenus.get(1).run();
                } catch (ExistUserNameException e) {
                    System.out.println(e.getMessage());
                    this.run();
                } catch (ExistEmailException e) {
                    System.out.println(e.getMessage());
                    this.run();
                } catch (EmptyExceptionForName e) {
                    System.out.println(e.getMessage());
                    this.run();
                } catch (EmptyExceptionForLastName e) {
                    System.out.println(e.getMessage());
                    this.run();
                } catch (EmptyExceptionForUserName e) {
                    System.out.println(e.getMessage());
                    this.run();
                } catch (EmptyExceptionForEmail e) {
                    System.out.println(e.getMessage());
                    this.run();
                }
            }
        };
    }

    private void getAdminInformation(ArrayList<String> adminInfo) {
        System.out.println("Hello our Dear Admin \nPlease notice that if there is already an admin you can not register as Admin !");
        System.out.println("Please Enter Your Firstname :");
        while (true) {
            String firstname = scanner.nextLine();
            try {
                Validation.nameOrLastNameIsValid(firstname);
                    adminInfo.add(firstname);
                    break;
            } catch (InvalidNameException e) {
                    System.out.println(e.getMessage());
                }
            }

        System.out.println("Please Enter Your Lastname :");
        while (true) {
            String lastname = scanner.nextLine();
            try {
                Validation.nameOrLastNameIsValid(lastname);
                    adminInfo.add(lastname);
                    break;
            } catch (InvalidNameException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Please Enter Your Username :");
        while (true) {
            String username = scanner.nextLine();
            try {
                Validation.usernameIsValid(username);
                adminInfo.add(username);
                break;
            } catch (InvalidUserNameException e) {
                System.out.println(e.getUserName() + e.getMessage());
            }
        }
        System.out.println("Please Enter Your Email Address :");
        while (true) {
            String email = scanner.nextLine();
            try {
                Validation.emailIsValid(email);
                adminInfo.add(email);
                break;

            } catch (InvalidEmailException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Please Enter Your Password :");
        while (true) {
            String password = scanner.nextLine();
            try {
                Validation.passwordIsValid(password);
                adminInfo.add(password);
                break;

            } catch (InvalidPasswordException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Please Enter Your PhoneNumber :");
        while (true) {
            String phoneNumber = scanner.nextLine();
            try {
                Validation.phoneNumberIsValid(phoneNumber);
                adminInfo.add(phoneNumber);
                break;

            } catch (InvalidPhoneNumberException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    private void getPlayerInfo(ArrayList<String> playerInfo) {
        System.out.println("Hello My Friend Welcome to Your New Home (: ");
        System.out.println("Please Enter Your Firstname :");
        while (true) {
            String firstname = scanner.nextLine();
            try {
                Validation.nameOrLastNameIsValid(firstname);
                playerInfo.add(firstname);
                break;
            } catch (InvalidNameException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Please Enter Your Lastname :");
        while (true) {
            String lastname = scanner.nextLine();
            try {
                Validation.nameOrLastNameIsValid(lastname);
                playerInfo.add(lastname);
                break;
            } catch (InvalidNameException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Please Enter Your Username :");
        while (true) {
            String username = scanner.nextLine();
            try {
                Validation.usernameIsValid(username);
                playerInfo.add(username);
                break;

            } catch (InvalidUserNameException e) {
                System.out.println(e.getUserName() + e.getMessage());
            }
        }
        System.out.println("Please Enter Your Email Address :");
        while (true) {
            String email = scanner.nextLine();
            try {
                Validation.emailIsValid(email);
                playerInfo.add(email);
                break;

            } catch (InvalidEmailException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Please Enter Your Password :");
        while (true) {
            String password = scanner.nextLine();
            try {
                Validation.passwordIsValid(password);
                playerInfo.add(password);
                break;

            } catch (InvalidPasswordException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Please Enter Your PhoneNumber :");
        while (true) {
            String phoneNumber = scanner.nextLine();
            try {
                Validation.phoneNumberIsValid(phoneNumber);
                playerInfo.add(phoneNumber);
                break;

            } catch (InvalidPhoneNumberException e) {
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

    @Override
    public void run() {
        this.show();
        this.execute();
//        System.out.println("registration completed");
//        this.parentMenu.run();
    }
}
