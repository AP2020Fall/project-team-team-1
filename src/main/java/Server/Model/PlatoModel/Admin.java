package Server.Model.PlatoModel;

import Server.Model.DataBase.DataBase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends User {
    private static final File adminFile = new File("src\\main\\resources\\DataBase\\Admin.json");

    private static ArrayList<Admin> admins = new ArrayList<Admin>();
    private String profileURL;
    private String bio;

    public Admin(String name, String lastName, int userID, String userName, String password, String email, String phoneNum) {
        super(name, lastName, userID, userName, password, email, phoneNum);
        this.profileURL = "src\\main\\resources\\Images\\default-profile.png";
        this.bio = "Its Simple Bio ...";
    }

    public static ArrayList<Admin> getAdmins() {
        return admins;
    }

    public String getProfileURL() {
        return profileURL;
    }

    public void setProfileURL(String profileURL) {
        this.profileURL = profileURL;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public static void setAdmins(ArrayList<Admin> admins) {
        Admin.admins = admins;
    }

    public static void AddNewAdmin(Admin admin) {
        admins.add(admin);
        User.addNewUser(admin);

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
