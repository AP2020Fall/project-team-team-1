package View;

import Controller.CompetencyController.Validation;

import java.util.ArrayList;
import java.util.HashMap;

public class SignUpMenu extends Menu {
    public SignUpMenu( Menu parentMenu) {
        super("Signup Menu!", parentMenu);
        HashMap<Integer,Menu> submenus = new HashMap<>();
        submenus.put(1,registerAsAdmin());
        submenus.put(2,registerAsPlayer());
        this.setSubmenus(submenus);
    }
    private Menu registerAsAdmin(){
        return new Menu("Register As Admin",this) {
            @Override
            public void show() {
                System.out.println(this.getName()+": ");
            }

            @Override
            public void execute() {
                ArrayList<String> adminInfo = new ArrayList<>();
                getAdminInformation(adminInfo);
                processSignupController.addAdmin(arrayListToString(adminInfo));
            }
        };
    }
    private Menu registerAsPlayer(){
        return new Menu("Register As Player",this) {
            @Override
            public void show() {
                System.out.println(this.getName()+": ");
            }

            @Override
            public void execute() {
                ArrayList<String> playerInfo = new ArrayList<>();
                getPlayerInfo(playerInfo);
                processSignupController.addPlayer(arrayListToString(playerInfo));
            }
        };
    }
    private void getAdminInformation(ArrayList<String> adminInfo){
        System.out.println("Hello our Dear Admin \nPlease notice that if there is already an admin you can not register as Admin !");
        System.out.println("Please Enter Your Firstname :");
        while (true){
            String firstname = scanner.nextLine();
            if (Validation.NameOrLastNameIsValid(firstname)){
                adminInfo.add(firstname);
                break;
            }else System.out.println("Please Enter a Valid name!");
        }
        System.out.println("Please Enter Your Lastname :");
        while (true){
            String lastname = scanner.nextLine();
            if (Validation.NameOrLastNameIsValid(lastname)){
                adminInfo.add(lastname);
                break;
            }else System.out.println("Please Enter a Valid name!");
        }
        System.out.println("Please Enter Your Username :");
        while (true){
            String username = scanner.nextLine();
            if (Validation.UsernameIsValid(username)){
                adminInfo.add(username);
                break;
            }else System.out.println("Please Enter a Valid Username!");
        }
        System.out.println("Please Enter Your Email Address :");
        while (true){
            String email = scanner.nextLine();
            if (Validation.EmailIsValid(email)){
                adminInfo.add(email);
                break;
            }else System.out.println("Please Enter a Valid Email!");
        }
        System.out.println("Please Enter Your Password :");
        while (true){
            String password = scanner.nextLine();
            if (Validation.PasswordIsValid(password)){
                adminInfo.add(password);
                break;
            }else System.out.println("Please Enter a Valid Password!");
        }
        System.out.println("Please Enter Your PhoneNumber :");
        while (true){
            String phoneNumber = scanner.nextLine();
            if (Validation.PhoneNumberIsValid(phoneNumber)){
                adminInfo.add(phoneNumber);
                break;
            }else System.out.println("Please Enter a Valid PhoneNumber!");
        }
    }
    private void getPlayerInfo(ArrayList<String> playerInfo){
        System.out.println("Hello My Friend Welcome to Your New Home (: ");
        System.out.println("Please Enter Your Firstname :");
        while (true){
            String firstname = scanner.nextLine();
            if (Validation.NameOrLastNameIsValid(firstname)){
                playerInfo.add(firstname);
                break;
            }else System.out.println("Please Enter a Valid name!");
        }
        System.out.println("Please Enter Your Lastname :");
        while (true){
            String lastname = scanner.nextLine();
            if (Validation.NameOrLastNameIsValid(lastname)){
                playerInfo.add(lastname);
                break;
            }else System.out.println("Please Enter a Valid name!");
        }
        System.out.println("Please Enter Your Username :");
        while (true){
            String username = scanner.nextLine();
            if (Validation.UsernameIsValid(username)){
                playerInfo.add(username);
                break;
            }else System.out.println("Please Enter a Valid Username!");
        }
        System.out.println("Please Enter Your Email Address :");
        while (true){
            String email = scanner.nextLine();
            if (Validation.EmailIsValid(email)){
                playerInfo.add(email);
                break;
            }else System.out.println("Please Enter a Valid Email!");
        }
        System.out.println("Please Enter Your Password :");
        while (true){
            String password = scanner.nextLine();
            if (Validation.PasswordIsValid(password)){
                playerInfo.add(password);
                break;
            }else System.out.println("Please Enter a Valid Password!");
        }
        System.out.println("Please Enter Your PhoneNumber :");
        while (true){
            String phoneNumber = scanner.nextLine();
            if (Validation.PhoneNumberIsValid(phoneNumber)){
                playerInfo.add(phoneNumber);
                break;
            }else System.out.println("Please Enter a Valid PhoneNumber!");
        }
    }

    public static String arrayListToString(ArrayList<String> arrayList){
        String output="";
        for (String string : arrayList) {
            output+=string+" ";
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
