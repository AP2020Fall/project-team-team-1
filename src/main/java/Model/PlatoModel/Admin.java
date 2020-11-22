package Model.PlatoModel;

import java.util.ArrayList;

public class Admin extends User {
    private static ArrayList<Admin> admins = new ArrayList<Admin>();


    public Admin(String name, String lastName, int userID, String userName, String password, String email, String phoneNum) {
        super(name, lastName, userID, userName, password, email, phoneNum);

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

}
