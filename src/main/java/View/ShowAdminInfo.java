package View;

import Controller.Exception.InvalidEmailException;
import Controller.Exception.InvalidNameException;
import Controller.Exception.InvalidPasswordException;
import Controller.Exception.InvalidPhoneNumberException;

import java.util.ArrayList;
import java.util.HashMap;

public class ShowAdminInfo extends Menu {
    private final String username;
    public ShowAdminInfo(String username , Menu parentMenu) {
        super("User Info", parentMenu);
        this.username = username;
        HashMap<Integer,Menu> submenus = new HashMap<>();
        submenus.put(1,changePassword());
        submenus.put(2,changeAfield());
        this.setSubmenus(submenus);
    }
    private Menu changePassword(){
        return new Menu("change Password",this) {
            @Override
            public void execute() {
                ArrayList<String> info = new ArrayList<>();
                info.add(username);
                getPasswords(info);
                try {
                    adminGeneralController.editPassword(arrayListToString(info));
                    System.out.println("Change password Successfully");
                    this.parentMenu.run();
                } catch (InvalidPasswordException e) {
                    System.out.println(e.getMessage());
                    this.run();
                }
            }
        };
    }
    private void getPasswords(ArrayList passwords){
        System.out.println("Please Enter Your Previous Password");
        String oldPass = scanner.nextLine();
        passwords.add(oldPass);
        System.out.println("Please Enter Your New Password");
        String newPass = scanner.nextLine();
        passwords.add(newPass);
    }
    private Menu changeAfield(){
        return new Menu("Change name | Lastname | Email | phoneNum", this) {
            @Override
            public void execute() {
                ArrayList<String> info = new ArrayList<>();
                getChangeINfo(info);
                try {
                    adminGeneralController.editField(arrayListToString(info));
                    System.out.println(info.get(0)+" changed successfully.");
                } catch (InvalidNameException | InvalidEmailException | InvalidPhoneNumberException e) {
                    System.out.println(e.getMessage());
                    this.run();
                }
            }
        };
    }
    private void getChangeINfo(ArrayList changeInfo){
        System.out.println("Enter The field you want to change : ");
        String field = scanner.nextLine();
        changeInfo.add(field);
        System.out.println("Enter "+ field +" new Value : ");
        String newValue = scanner.nextLine();
        changeInfo.add(newValue);
    }
    public static String arrayListToString(ArrayList<String> arrayList) {
        String output = "";
        for (String string : arrayList) {
            output += string + " ";
        }
        return output;
    }

    @Override
    public void execute() {

    }
}
