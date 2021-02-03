package Client;

import Client.View.LoginController;
import javafx.application.Platform;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Client {
    public static ArrayList<Client> clients = new ArrayList<>();
    static Socket socket;
    Scanner scanner;
    static DataInputStream dataInputStream;
    static DataOutputStream dataOutputStream;

    public static Socket getSocket() {
        return socket;
    }

    public static void setSocket(Socket socket) {
        Client.socket = socket;
    }

    public static DataInputStream getDataInputStream() {
        return dataInputStream;
    }

    public static void setDataInputStream(DataInputStream dataInputStream) {
        Client.dataInputStream = dataInputStream;
    }

    public static DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }

    public static void setDataOutputStream(DataOutputStream dataOutputStream) {
        Client.dataOutputStream = dataOutputStream;
    }

    public static void main(String[] args) {
        new Client().connect();
    }

    public void connect() {
        try {
            socket = new Socket("127.0.0.1", 8888);
            System.out.println("Successfully connected to server!");
            handleConnection();
        } catch (IOException e) {
            System.err.println("Error starting client!");
        }
    }

    public void handleConnection() {
        try {
            scanner = new Scanner(System.in);
            dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            dataOutputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            String userInput = "";
            while (!userInput.equalsIgnoreCase("end")) {
                Main.main(new String[1]);
//                System.out.println("here");
//                userInput = scanner.nextLine();
//                dataOutputStream.writeUTF(userInput);
//                dataOutputStream.flush();
//                String response = dataInputStream.readUTF();
//                System.out.println(response);
                if (userInput.equals("end"))
                    return;
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void addNewClient(Client client){
        clients.add(client);
    }

}
