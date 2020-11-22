package View;

import Controller.CompetencyController.Validation;

import java.util.ArrayList;
import java.util.HashMap;

public class SignUpMenu extends Menu {
    public SignUpMenu( Menu parentMenu) {
        super("Signup Menu!", parentMenu);
        HashMap<Integer,Menu> submenus = new HashMap<>();

    }
    private Menu registerAsAdmin(){
        return new Menu("register Admin",this) {
            @Override
            public void show() {
                System.out.println(this.getName());
            }
        }
    }
    private void getManagerInformation(ArrayList<String> managerInfo){
        System.out.println("Hello our Dear Admin \n Please notice that if there is already an admin you can not register as Admin !");
        System.out.println("Please Enter Your Firstname :");
        while (true){
            String firstname = scanner.nextLine();
            if (Validation.NameOrLastNameIsValid(firstname)){
                managerInfo.add(firstname);
                break;
            }else System.out.println("Please Enter a Valid name!");
        }
        System.out.println("Please Enter Your Lastname :");
        while (true){
            String lastname = scanner.nextLine();
            if (Validation.NameOrLastNameIsValid(lastname)){
                managerInfo.add(lastname);
                break;
            }else System.out.println("Please Enter a Valid name!");
        }
        System.out.println("Please Enter Your Username :");
        while (true){
            String username = scanner.nextLine();
            if (Validation.UsernameIsValid(username)){
                managerInfo.add(username);
                break;
            }else System.out.println("Please Enter a Valid Username!");
        }
        System.out.println("Please Enter Your Email Address :");
        while (true){
            String email = scanner.nextLine();
            if (Validation.EmailIsValid(email)){
                managerInfo.add(email);
                break;
            }else System.out.println("Please Enter a Valid Email!");
        }
        System.out.println("Please Enter Your Password :");
        while (true){
            String password = scanner.nextLine();
            if (Validation.PasswordIsValid(password)){
                managerInfo.add(password);
                break;
            }else System.out.println("Please Enter a Valid Password!");
        }
        System.out.println("Please Enter Your PhoneNumber :");
        while (true){
            String password = scanner.nextLine();
            if (Validation.PasswordIsValid(password)){
                managerInfo.add(password);
                break;
            }else System.out.println("Please Enter a Valid PhoneNumber!");
        }


    }


}
