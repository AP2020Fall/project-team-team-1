package Model.PlatoModel;

import java.util.ArrayList;

public class Admin extends User {
    private static ArrayList<Admin> admins = new ArrayList<Admin>();


    public Admin(String name, String lastName,  String userName, String password, String email, String phoneNum) {
        super(name, lastName, 1000, userName, password, email, phoneNum);

    }

    public static ArrayList<Admin> getAdmins() {
        return admins;
    }


    public static void setAdmins(ArrayList<Admin> admins) {
        Admin.admins = admins;
    }

    public static void AddNewAdmin(Admin admin) {
        admins.add(admin);
        User.addNewUser(admin);

    }

    @Override
    public String toString() {
        return super.toString();
    }
}
