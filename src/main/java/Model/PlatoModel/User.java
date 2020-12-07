package Model.PlatoModel;

import Model.DataBase.DataBase;
import com.google.gson.Gson;

import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

public class User {
    private static final File userFile = new File("src\\main\\java\\Model\\Database\\User.json");

    public static ArrayList<User> users = new ArrayList<User>();
    private String name;
    private String lastName;
    private int UserID;
    private String userName;
    private String password;
    private String email;
    private String phoneNum;

    public User(String name, String lastName, int userID, String userName, String password, String email, String phoneNum) {
        this.name = name;
        this.lastName = lastName;
        UserID = userID;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phoneNum = phoneNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public static void addNewUser(User user) {
        users.add(user);

        if (userFile.exists())
            userFile.delete();

        try {
            DataBase.save(users, userFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveInJsonFile() {
//        if (userFile.exists())
//            userFile.delete();
        try {
            DataBase.save(users, userFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadFromJsonFile() {
        if (!userFile.exists())
            return;
        Type type = new TypeToken<ArrayList<User>>() {
        }.getType();
        Gson gson =new Gson();
        StringBuilder read = new StringBuilder();
        try {
            Scanner myReader = new Scanner(userFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                read.append(data);
                System.out.println(read);
            }
            myReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<User> output = gson.fromJson(String.valueOf(read),type);
        users.clear();
        users.addAll(output);
        System.out.println(users);

    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", UserID=" + UserID +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                '}';
    }
}
