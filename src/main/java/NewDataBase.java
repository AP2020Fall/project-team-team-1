import Server.Controller.CompetencyController.Existence;
import Server.Model.PlatoModel.User;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class NewDataBase {
    private static Connection connection;
    private static Statement statement;
    static String url = "jdbc:sqlite:src/main/resources/DataBase/newDatabase.db";
     private static void createUser(){
         String sql =
                 "create table Accounts (\n" +
                         "ID TEXT  primary key,\n" +
                         "NAME TEXT not null,\n" +
                         "LASTNAME TEXT not null,\n" +
                         " EMAIL text,\n" +
                         " PHONE REAL,\n" +
                         " USERNAME,\n" +
                         "PASS TEXT)";
         connectDatas(sql);
     }
    public static void insertAccount(User user) {

            String sql = "INSERT INTO Accounts (ID,NAME,LASTNAME,EMAIL,PHONE,USERNAME, PASS)" +
                    "VALUES ('" + user.getUserID() + "','" + user.getName() + "','" + user.getLastName() + "','" + user.getEmail() + "', '" + user.getPhoneNum() + "', '" +
                    user.getUserName() + "', '" + user.getPassword()  + "')";
            connectData(sql);

    }
    public static void insertAccountAdd(User user) {
        String sql = "INSERT INTO Accounts (ID,NAME,LASTNAME,EMAIL,PHONE,USERNAME, PASS)" +
                "VALUES ('" + user.getUserID() + "','" + user.getName() + "','" + user.getLastName() + "','" + user.getEmail() + "', '" + user.getPhoneNum() + "', '" +
                user.getUserName() + "', '" + user.getPassword()  + "')";
        connectData(sql);

    }

    public static void getAccount() {
        try {
            if (new File("newDatabase.db").exists()) {
                createUser();
//                createProduct();
//                createFirm();
//                createBuy();
//                createSale();
            }
            connection = DriverManager.getConnection(url);
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Accounts;");
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("NAME");
                String last = resultSet.getString("LASTNAME");
                String username = resultSet.getString("USERNAME");
                String pass = resultSet.getString("PASS");
                String email = resultSet.getString("EMAIL");
                String phone = resultSet.getString("PHONE");
                User user;

                if (Existence.checkUserNameExistence(username)){
                    user=User.findUser(username);
                }else {
                    user = new User(name,last,id,username,pass,email,phone);
                    User.addNewUser(user);
                }


            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }
    public static void deleteAccount(User user) {
        String sql = "DELETE from Accounts where ID = '" + user.getUserName() + "'";
        connectData(sql);
    }

    private static void connectData(String sqls) {
        try {
            if (new File("newDatabase.db").exists()) {
                createUser();
//                createProduct();
//                createFirm();
//                createBuy();
//                createSale();
            }
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(url);
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            String sql = sqls;
            statement.executeUpdate(sql);
            statement.close();
            connection.commit();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }

    private static void connectDatas(String sqls) {
        try {

            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(url);
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            String sql = sqls;
            statement.executeUpdate(sql);
            statement.close();
            connection.commit();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }
}
