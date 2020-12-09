package View;

import Controller.Exception.Plato.*;

import java.io.IOException;
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
                } catch (WrongPasswordException e) {
                    System.out.println(e.getMessage());
                } catch (ExistPlayerException e) {
                    System.out.println(e.getPlayerName() + e.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (StrongerPasswordException e) {
                    System.out.println(e.getMessage());
                }

            }
        };
    }
    private void getPasswords(ArrayList<String> passwords){
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
                    this.parentMenu.run();
                } catch (InvalidNameException | InvalidEmailException | InvalidPhoneNumberException | ExistEmailException | InvalidFieldException e) {
                    System.out.println(e.getMessage());
                    this.run();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
    }
    private void getChangeINfo(ArrayList<String> changeInfo){
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
        adminGeneralController.showAdminInfo();
        Menu nextMenu = null;
        String num = scanner.nextLine();
        if ((!num.matches("\\d+")) || Integer.parseInt(num) > submenus.size() + 1){
            this.run();
        }else {
            int chosenMenu = Integer.parseInt(num);
            if (chosenMenu==submenus.size()+1){
                if (this.parentMenu==null){
                    System.exit(1);
                }else {
                    nextMenu=this.parentMenu;
                    nextMenu.run();
                }
            } else {
                nextMenu = submenus.get(chosenMenu);
                nextMenu.run();
            }
        }
    }


}
