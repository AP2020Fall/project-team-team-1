package Server;

import Controller.AdminController.AdminGeneralController;
import Controller.Exception.Plato.BanExceptionForLogin;
import Controller.Exception.Plato.ExistAdminException;
import Controller.Exception.Plato.InvalidUserNameException;
import Controller.Exception.Plato.WrongPasswordException;
import Controller.PlayerController.FindPlayerByInfo;
import Controller.RegisterController.LogIn;
import Model.DataBase.DataBase;
import Model.PlatoModel.Admin;
import Model.PlatoModel.Player;
import com.google.gson.Gson;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    protected static LogIn logIn = new LogIn();
    protected static AdminGeneralController adminGeneralController = new AdminGeneralController();

    public static void main(String[] args) throws IOException {
        try {
            DataBase.loadAllDataFromJsonFiles();
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }

        run();
    }

    static void run() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        while (true) {
            Socket clientSocket;
            try {
                System.out.println("Waiting for Client.Client...");
                clientSocket = serverSocket.accept();
                System.out.println("A client Connected!");
                DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
                DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
                new ClientHandler(clientSocket, dataOutputStream, dataInputStream).start();
            } catch (Exception e) {
                System.err.println("Error in accepting client!");
                break;
            }
        }
    }

    static class ClientHandler extends Thread {
        Socket clientSocket;
        DataOutputStream dataOutputStream;
        DataInputStream dataInputStream;
        Player player;
        Admin admin;

        public ClientHandler(Socket clientSocket, DataOutputStream dataOutputStream, DataInputStream dataInputStream) {
            this.clientSocket = clientSocket;
            this.dataOutputStream = dataOutputStream;
            this.dataInputStream = dataInputStream;
            this.player = null;
            this.admin = null;
        }


        @Override
        public void run() {
            try {
                String input;
                while (true) {
                    input = dataInputStream.readUTF();
                    if (player != null)
                        System.out.println("Client.Client with username " + player.getLastName() + " sent : " + input);
                    else System.out.println("A client sent : " + input);
                    String answer = answerClient(input);
                    dataOutputStream.writeUTF(answer);
                    dataOutputStream.flush();
                    System.out.println("server answered : " + answer);
                    if (input.equals("end")) {
                        System.out.println("Connection closed!!!");
                        break;
                    }
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }

        }

        private String answerClient(String input) {
            String answer = "null";
            if (input.startsWith("register"))
                answer = "register(input)";
            else if (input.startsWith("login"))
                answer = login(input);
            else if (input.startsWith("data"))
                answer = getData(input);

            return answer;
        }

        private String login(String input) {
            int commaIndex = input.indexOf(",");
            String username = input.substring(6, commaIndex);
            String password = input.substring(commaIndex + 1);
            String out = username + " " + password;

            if (adminGeneralController.getAdminUserName().equals(username)){
                try {
                    logIn.loginAsAdmin(out);
                    admin = Admin.getAdmins().get(0);
                    return "Admin Success login";

                } catch (InvalidUserNameException e) {
                    e.printStackTrace();
                } catch (ExistAdminException e) {
                    e.printStackTrace();
                } catch (WrongPasswordException e) {
                    e.printStackTrace();
                }
            }else {
                try {
                    logIn.loginAsPlayer(out);
                    player = FindPlayerByInfo.findByUserName(username);
                    return "Player Success login";

                } catch (InvalidUserNameException e) {
                    e.printStackTrace();
                } catch (WrongPasswordException e) {
                    e.printStackTrace();
                } catch (BanExceptionForLogin banExceptionForLogin) {
                    banExceptionForLogin.printStackTrace();
                }

            }



            return "Failure";
        }
        private String getData(String input){
            String[] process = input.split("\\s");
            Gson gson = new Gson();
            if (process[1].equalsIgnoreCase("admin")){
                return gson.toJson(Admin.getAdmins().get(0));
            }else {
                return gson.toJson(FindPlayerByInfo.findByUserName(process[2]));
            }
        }




    }
}
