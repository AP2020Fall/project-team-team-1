package Model.PlatoModel;

import Model.DataBase.DataBase;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Admin extends User {
    private static final File adminFile = new File("src\\main\\java\\Model\\Database\\Admin.json");

    private static ArrayList<Admin> admins = new ArrayList<Admin>();


    public Admin(String name, String lastName,int userID,  String userName, String password, String email, String phoneNum) {
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
        try {
            DataBase.save(admins,adminFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void saveInJsonFile() {
        try {
            DataBase.save(admins,adminFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
