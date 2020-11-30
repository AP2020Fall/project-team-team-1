package Model.PlatoModel;

import Model.DataBase.DataBase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;

import java.lang.reflect.Type;
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
    public static void loadFromJsonFile(){
                String read = "";
        try {
            FileReader myFile1 = new FileReader(adminFile);
            BufferedReader br = new BufferedReader(myFile1);
            read = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Type type = new TypeToken<ArrayList<Admin>>() {}.getType();
        ArrayList<Admin> output = new Gson().fromJson(read,type);
        admins.clear();
        admins.addAll(output);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
