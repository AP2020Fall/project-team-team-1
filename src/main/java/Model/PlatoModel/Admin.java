package Model.PlatoModel;

import Model.DataBase.DataBase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends User {
    private static final File adminFile = new File("src\\main\\java\\Model\\Database\\Admin.json");

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
//        if (adminFile.exists())
//            adminFile.delete();
        try {
            DataBase.save(admins, adminFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void saveInJsonFile() {
        User.saveInJsonFile();
        if (adminFile.exists())
            adminFile.delete();
        try {
            DataBase.save(admins, adminFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadFromJsonFile() {
        if (!adminFile.exists())
            return;
        StringBuilder read = new StringBuilder();
        try {
            Scanner myReader = new Scanner(adminFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                read.append(data);
                System.out.println(read);
            }
            myReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Type type = new TypeToken<ArrayList<Admin>>() {
        }.getType();
        ArrayList<Admin> output = new Gson().fromJson(String.valueOf(read), type);
        admins.clear();
        admins.addAll(output);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
