package View;

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
    }


}
