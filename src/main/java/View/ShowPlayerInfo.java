package View;

import Controller.Exception.*;

import java.util.ArrayList;
import java.util.HashMap;

public class ShowPlayerInfo extends Menu {
    private String username;

    public ShowPlayerInfo(String username, Menu parentMenu) {
        super("User info", parentMenu);
        this.username = username;
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, changePassword());
        submenus.put(2, changeAfield());
        this.setSubmenus(submenus);
    }

    private Menu changePassword() {
        return new Menu("change Password", this) {
            @Override
            public void execute() {
                ArrayList<String> info = new ArrayList<>();
                info.add(username);
                getPasswords(info);
                try {
                    playerGeneralController.editPassword(arrayListToString(info));
                    System.out.println("Change password Successfully");
                    this.parentMenu.run();
                } catch (InvalidPasswordException | WrongPasswordException | SamePasswordException e) {
                    System.out.println(e.getMessage());
                    this.run();
                }
            }
        };

    }

    private Menu changeAfield() {
        return new Menu("Change name | Lastname | Email | phoneNum", this) {
            @Override
            public void execute() {
                ArrayList<String> info = new ArrayList<>();
                getChangeINfo(info);
                //todo exceptions not completed!!!! kad dont touch it!!!!!!!!
                try {
                    playerGeneralController.editField(arrayListToString(info));
                    System.out.println(info.get(0) + " changed successfully.");
                    this.parentMenu.run();
                } catch (InvalidNameException | InvalidEmailException | InvalidPhoneNumberException | ExistEmailException | InvalidFieldException e) {
                    System.out.println(e.getMessage());
                    this.parentMenu.run();
                }
            }
        };
    }

    private void getChangeINfo(ArrayList<String> changeInfo) {
        changeInfo.add(username);
        System.out.println("Enter The field you want to change : ");
        String field = scanner.nextLine();
        changeInfo.add(field);
        System.out.println("Enter " + field + " new Value : ");
        String newValue = scanner.nextLine();
        changeInfo.add(newValue);
    }

    private void getPasswords(ArrayList<String> passwords) {
        System.out.println("Please Enter Your Previous Password");
        String oldPass = scanner.nextLine();
        passwords.add(oldPass);
        System.out.println("Please Enter Your New Password");
        String newPass = scanner.nextLine();
        passwords.add(newPass);
    }

    @Override
    public void execute() {
        try {
            playerGeneralController.showBasicInformation(username);
        } catch (ExistPlayerException e) {
            System.out.println(e.getPlayerName() + e.getMessage());
        }
        Menu nextMenu = null;
        String num = scanner.nextLine();
        if ((!num.matches("\\d+")) || Integer.parseInt(num) > submenus.size() + 1) {
            this.run();
        } else {
            int chosenMenu = Integer.parseInt(num);
            if (chosenMenu == submenus.size() + 1) {
                if (this.parentMenu == null) {
                    System.exit(1);
                } else {
                    nextMenu = this.parentMenu;
                    nextMenu.run();
                }
            } else {
                nextMenu = submenus.get(chosenMenu);
                nextMenu.run();
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
