package Server;

import Server.Controller.AdminController.AdminGeneralController;
import Server.Controller.CompetencyController.Validation;
import Server.Controller.Exception.Plato.*;
import Server.Controller.PlayerController.FindPlayerByInfo;
import Server.Controller.PlayerController.PlayerGeneralController;
import Server.Controller.RegisterController.LogIn;
import Server.Controller.RegisterController.SignUp;
import Server.Model.DataBase.DataBase;
import Server.Model.PlatoModel.Admin;
import Server.Model.PlatoModel.Event;
import Server.Model.PlatoModel.Player;
import com.google.gson.Gson;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    protected static LogIn logIn = new LogIn();
    protected static SignUp signUp = new SignUp();
    protected static AdminGeneralController adminGeneralController = new AdminGeneralController();
    protected static PlayerGeneralController playerGeneralController = new PlayerGeneralController();


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
            //todo hal moshkel 2 bar darkhast
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
            if (input.startsWith("Register"))
                answer = register(input);
            else if (input.startsWith("Validation"))
                answer = validation(input);
            else if (input.startsWith("Edit Profile Picture"))
                answer = editProfilePic(input);
            else if (input.startsWith("login"))
                answer = login(input);
            else if (input.startsWith("data"))
                answer = getData(input);
            else if (input.startsWith("Event List"))
                answer = getEvents();
            else if (input.startsWith("Player List"))
                answer = getPlayersList();
            else if (input.startsWith("Player Favorite Games"))
                answer = playerFavoriteGames(input);
            else if (input.startsWith("Player Suggested Games"))
                answer = playerSuggestedGames(input);
            else if (input.startsWith("Player Plato Message"))
                answer = playerPlatoMessage();
            else if (input.startsWith("load battle details"))
                answer = showBattleDetails();
            else if (input.startsWith("first game name"))
                answer = firstGameName();
            else if (input.startsWith("Friends List"))
                answer = playerFriends(input);
            else if (input.startsWith("Friends Requests"))
                answer = playerRequests(input);
            else if (input.startsWith("win number"))
                answer = showNumberOfWins(input);
            else if (input.startsWith("lose number"))
                answer = showNumberOfLoses(input);
            else if (input.startsWith("play number"))
                answer = showNumberOfPlayed(input);
            else if (input.startsWith("points number"))
                answer = showNumberOfPointsInThisGame(input);

            return answer;
        }

        private String register(String input) {
            try {

                if (adminGeneralController.adminExistence().equalsIgnoreCase("true")) {
                    signUp.addPlayer(input.substring(9));
                    return "Sign Up Done";
                } else {
                    signUp.addAdmin(input.substring(9));
                    return "Sign Up Done";
                }
            } catch (ExistUserNameException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            } catch (ExistEmailException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            } catch (ExistAdminException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            }

        }

        private String editProfilePic(String string) {
            String[] process = string.split("\\s");

            try {
                playerGeneralController.editProfileURL(process[3], process[4]);
                return "done";
            } catch (IOException e) {
                e.printStackTrace();
            }

            return "Failure";
        }


        private String login(String input) {
            int commaIndex = input.indexOf(",");
            String username = input.substring(6, commaIndex);
            String password = input.substring(commaIndex + 1);
            String out = username + " " + password;

            if (adminGeneralController.getAdminUserName().equals(username)) {
                try {
                    logIn.loginAsAdmin(out);
                    admin = Admin.getAdmins().get(0);
                    return "Success Admin login";

                } catch (InvalidUserNameException e) {
                    System.err.println(e.getMessage());
                    return e.getMessage();
                } catch (ExistAdminException e) {
                    System.err.println(e.getMessage());
                    return e.getMessage();
                } catch (WrongPasswordException e) {
                    System.err.println(e.getMessage());
                    return e.getMessage();
                }
            } else {
                try {
                    logIn.loginAsPlayer(out);
                    player = FindPlayerByInfo.findByUserName(username);
                    OnlineUsers.addNewOnlineUser(new OnlineUsers(username, "Nothing"));
                    return "Success Player login";

                } catch (InvalidUserNameException e) {
                    System.err.println(e.getMessage());
                    return e.getMessage();
                } catch (WrongPasswordException e) {
                    System.err.println(e.getMessage());
                    return e.getMessage();
                } catch (BanExceptionForLogin banExceptionForLogin) {
                    System.err.println(banExceptionForLogin.getMessage());
                    return banExceptionForLogin.getMessage();
                }

            }

        }

        private String getData(String input) {
            String[] process = input.split("\\s");
            Gson gson = new Gson();
            if (process[1].equalsIgnoreCase("admin")) {
                return gson.toJson(Admin.getAdmins().get(0));
            } else {
                return gson.toJson(FindPlayerByInfo.findByUserName(process[2]));
            }
        }

        private String getEvents() {
            try {
                adminGeneralController.eventDateChecker();
            } catch (ExistEventException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new Gson().toJson(Event.getEvents());
        }

        private String getPlayersList() {
            return new Gson().toJson(Player.getPlayers());
        }

        private String playerFavoriteGames(String string) {
            String[] process = string.split("\\s");
            try {
                return playerGeneralController.showFavoritesGames(process[3]);
            } catch (ExistFavoriteException e) {
                return "There is no Favorite Games";
            }
        }

        private String playerSuggestedGames(String string) {
            String[] process = string.split("\\s");
            StringBuilder stringBuilder = new StringBuilder();
            for (Integer integer : playerGeneralController.findByUserName(process[3]).getSuggestedGamesID()) {
                stringBuilder.append(playerGeneralController.findSuggestionBySuggestionIDForGameName(String.valueOf(integer))).append(" ");
            }
            return String.valueOf(stringBuilder);
        }

        private String playerPlatoMessage() {
            return playerGeneralController.viewBotMessages();
        }

        private String playerFriends(String string) {
            String[] process = string.split("\\s");
            try {
                return playerGeneralController.showFriends(process[2]);
            } catch (ExistFriendException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            }
        }

        private String playerRequests(String string) {
            String[] process = string.split("\\s");
            try {
                return playerGeneralController.showRequests(process[2]);
            } catch (ExistFriendException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            }
        }

        private String validation(String string) {
            String[] process = string.split("\\s");
            if (process[1].equalsIgnoreCase("Email")) {
                try {
                    Validation.emailIsValid(process[2]);
                    return "Valid Email";
                } catch (InvalidEmailException e) {
                    return e.getMessage();
                }
            } else if (process[1].equalsIgnoreCase("PhoneNumber")) {
                try {
                    Validation.phoneNumberIsValid(process[2]);
                    return "Valid PhoneNumber";
                } catch (InvalidPhoneNumberException e) {
                    return e.getMessage();
                }
            } else if (process[1].equalsIgnoreCase("Name")) {
                try {
                    Validation.nameOrLastNameIsValid(process[2]);
                    return "Valid Name";
                } catch (InvalidNameException e) {
                    return e.getMessage();
                }

            } else if (process[1].equalsIgnoreCase("LastName")) {
                try {
                    Validation.nameOrLastNameIsValid(process[2]);
                    return "Valid LastName";
                } catch (InvalidNameException e) {
                    return e.getMessage();
                }
            } else if (process[1].equalsIgnoreCase("Password")) {
                try {
                    Validation.passwordIsValid(process[2]);
                    return "Valid Password";
                } catch (StrongerPasswordException e) {
                    return e.getMessage();
                }
            } else if (process[1].equalsIgnoreCase("Age")) {
                try {
                    Validation.ageIsValid(process[2]);
                    return "Valid Age";
                } catch (InvalidAgeException e) {
                    return e.getMessage();
                }
            } else if (process[1].equalsIgnoreCase("Username")) {
                try {
                    Validation.usernameIsValid(process[2]);
                    return "Valid Username";
                } catch (InvalidUserNameException e) {
                    return e.getMessage();
                }
            } else if (process[1].equalsIgnoreCase("GameName")) {
                try {
                    Validation.gameNameIsValid(process[2]);
                    return "Valid GameName";
                } catch (InvalidGameNameException e) {
                    return e.getMessage();
                }
            } else if (process[1].equalsIgnoreCase("Remember")) {
                try {
                    Validation.rememberInputValidation(process[2]);
                    return "Valid Remember";
                } catch (RememberMeException e) {
                    return e.getMessage();
                }
            } else if (process[1].equalsIgnoreCase("Date")) {
                try {
                    Validation.dateIsValid(process[2]);
                    return "Valid Date";
                } catch (InvalidDateException e) {
                    return e.getMessage();
                }
            }

            return "InValid Input";
        }

        private String showBattleDetails() {
            return playerGeneralController.battleDetails();
        }

        private String firstGameName() {
            return adminGeneralController.firstGameNameGetter();
        }

        private String showNumberOfWins(String string) {
            String[] process = string.split("\\s");
            if (process[3].equalsIgnoreCase("first")){
                try {
                    return playerGeneralController.showNumberOFWins(process[2],adminGeneralController.firstGameNameGetter());
                } catch (InvalidGameNameException e) {
                    System.err.println(e.getMessage());
                    return e.getMessage();
                }
            }
            else if (process[3].equalsIgnoreCase("second")){
                try {
                    return playerGeneralController.showNumberOFWins(process[2],adminGeneralController.firstGameNameGetter());
                } catch (InvalidGameNameException e) {
                    System.err.println(e.getMessage());
                    return e.getMessage();
                }
            }
            return "invalid";
        }
        private String showNumberOfLoses(String string) {
            String[] process = string.split("\\s");
            if (process[3].equalsIgnoreCase("first")){
                try {
                    return playerGeneralController.numberOfLossesInThisGame(process[2],adminGeneralController.firstGameNameGetter());
                } catch (InvalidGameNameException e) {
                    System.err.println(e.getMessage());
                    return e.getMessage();
                }
            }
            else if (process[3].equalsIgnoreCase("second")){
                try {
                    return playerGeneralController.numberOfLossesInThisGame(process[2],adminGeneralController.firstGameNameGetter());
                } catch (InvalidGameNameException e) {
                    System.err.println(e.getMessage());
                    return e.getMessage();
                }
            }
            return "invalid";
        }
        private String showNumberOfPlayed(String string) {
            String[] process = string.split("\\s");
            if (process[3].equalsIgnoreCase("first")){
                try {
                    return playerGeneralController.showNumberOfGamePlayedInThisGame(process[2],adminGeneralController.firstGameNameGetter());
                } catch (InvalidGameNameException e) {
                    System.err.println(e.getMessage());
                    return e.getMessage();
                }
            }
            else if (process[3].equalsIgnoreCase("second")){
                try {
                    return playerGeneralController.showNumberOfGamePlayedInThisGame(process[2],adminGeneralController.firstGameNameGetter());
                } catch (InvalidGameNameException e) {
                    System.err.println(e.getMessage());
                    return e.getMessage();
                }
            }
            return "invalid";
        }
        private String showNumberOfPointsInThisGame(String string) {
            String[] process = string.split("\\s");
            if (process[3].equalsIgnoreCase("first")){
                try {
                    return playerGeneralController.showPlayerPointsInThisGame(process[2],adminGeneralController.firstGameNameGetter());
                } catch (InvalidGameNameException e) {
                    System.err.println(e.getMessage());
                    return e.getMessage();
                }
            }
            else if (process[3].equalsIgnoreCase("second")){
                try {
                    return playerGeneralController.showPlayerPointsInThisGame(process[2],adminGeneralController.firstGameNameGetter());
                } catch (InvalidGameNameException e) {
                    System.err.println(e.getMessage());
                    return e.getMessage();
                }
            }
            return "invalid";
        }



    }
}
