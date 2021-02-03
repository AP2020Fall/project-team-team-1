package Server;

import Server.Controller.AdminController.AdminGeneralController;
import Server.Controller.CompetencyController.Existence;
import Server.Controller.CompetencyController.Validation;
import Server.Controller.Exception.BattleShip.*;
import Server.Controller.Exception.DotsAndBoxes.ExistLineException;
import Server.Controller.Exception.Plato.*;
import Server.Controller.OnlineUsersController;
import Server.Controller.PlayerController.FindPlayerByInfo;
import Server.Controller.PlayerController.Game;
import Server.Controller.PlayerController.PlayerGeneralController;
import Server.Controller.RegisterController.LogIn;
import Server.Controller.RegisterController.SignUp;
import Server.Model.DataBase.DataBase;
import Server.Model.PlatoModel.Admin;
import Server.Model.PlatoModel.Event;
import Server.Model.PlatoModel.Player;
import Server.Model.PlatoModel.Suggestion;
import com.google.gson.Gson;
import io.jsonwebtoken.Claims;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Server {
    protected static LogIn logIn = new LogIn();
    protected static SignUp signUp = new SignUp();
    protected static AdminGeneralController adminGeneralController = new AdminGeneralController();
    protected static PlayerGeneralController playerGeneralController = new PlayerGeneralController();
    protected static OnlineUsersController onlineUsersController = new OnlineUsersController();

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
//                new ClientHandler(clientSocket, dataOutputStream, dataInputStream).start();
                ClientHandler clientHandler = new ClientHandler(clientSocket, dataOutputStream, dataInputStream);
                ClientHandler.addNewClientHandler(clientHandler);
                clientHandler.start();
            } catch (Exception e) {
                System.err.println("Error in accepting client!");
                break;
            }
        }
    }

    static class ClientHandler extends Thread {
        public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
        Socket clientSocket;
        DataOutputStream dataOutputStream;
        DataInputStream dataInputStream;
        String username;
        Player player;
        Admin admin;

        public ClientHandler(Socket clientSocket, DataOutputStream dataOutputStream, DataInputStream dataInputStream) {
            this.clientSocket = clientSocket;
            this.dataOutputStream = dataOutputStream;
            this.dataInputStream = dataInputStream;
            this.username = "";
            this.player = null;
            this.admin = null;
        }

        public static void addNewClientHandler(ClientHandler clientHandler) {
            clientHandlers.add(clientHandler);
        }

        public static void removeClientHandler(ClientHandler clientHandler) {
            clientHandlers.remove(clientHandler);
        }

        public static ClientHandler clientHandlerFinder(String username) {
            for (Server.ClientHandler clientHandler : clientHandlers) {
                if (clientHandler.username.equals(username)) {
                    return clientHandler;
                }
            }
            return null;
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
                    System.err.println(OnlineUsers.getOnlineUsers());
                    if (input.equals("end")) {
                        System.out.println("Connection closed!!!");
                        break;
                    }
                }

            } catch (SocketException e) {
                System.err.println(e.getMessage());
                clientHandlers.remove(this);
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
            else if (input.startsWith("Edit Password"))
                answer = editPassword(input);
            else if (input.startsWith("Confirm Password"))
                answer = confirmPassword(input);
            else if (input.startsWith("Make Player Online"))
                answer = makePlayerOnline(input);
            else if (input.startsWith("Make Player Offline"))
                answer = makePlayerOffline(input);
            else if (input.startsWith("Change Player Status"))
                answer = changePlayerStatus(input);
            else if (input.startsWith("Online Player In This Game"))
                answer = onlinePlayerInThisGame(input);
            else if (input.startsWith("Delete Player"))
                answer = deletePlayer(input);
            else if (input.startsWith("Edit Profile Details"))
                answer = editProfileDetails(input);
            else if (input.startsWith("Edit Profile Bio"))
                answer = editProfileBio(input);
            else if (input.startsWith("login"))
                answer = login(input);
            else if (input.startsWith("Load Admin Username"))
                answer = getAdminUsername();
            else if (input.startsWith("Set Remember Status"))
                answer = setRememberStatus(input);
            else if (input.startsWith("Load Remember Status"))
                answer = getRememberStatus(input);
            else if (input.startsWith("Load Direct Password"))
                answer = getDirectPassword(input);
            else if (input.startsWith("data"))
                answer = getData(input);
            else if (input.startsWith("Event List"))
                answer = getEvents();
            else if (input.startsWith("Player List"))
                answer = getPlayersList();
            else if (input.startsWith("Basic Information player"))
                answer = getPlayersBasicInformation(input);
            else if (input.startsWith("Player Age"))
                answer = getPlayersAge(input);
            else if (input.startsWith("Player Favorite Games"))
                answer = playerFavoriteGames(input);
            else if (input.startsWith("Add Player Favorite Games"))
                answer = addPlayerFavoriteGames(input);
            else if (input.startsWith("Remove Player Favorite Games"))
                answer = removePlayerFavoriteGames(input);
            else if (input.startsWith("Player Suggested Games"))
                answer = playerSuggestedGames(input);
            else if (input.startsWith("Player Plato Message"))
                answer = playerPlatoMessage();
            else if (input.startsWith("Player Game Log"))
                answer = getPlayerGameLog(input);
            else if (input.startsWith("load battle details"))
                answer = showBattleDetails();
            else if (input.startsWith("Load Dots Details"))
                answer = showDotsDetails();
            else if (input.startsWith("first game name"))
                answer = firstGameName();
            else if (input.startsWith("second game name"))
                answer = secondGameName();
            else if (input.startsWith("Friends List"))
                answer = playerFriends(input);
            else if (input.startsWith("Friends Requests"))
                answer = playerRequests(input);
            else if (input.startsWith("Sent Friend Request"))
                answer = sentFriendRequest(input);
            else if (input.startsWith("Accept Request"))
                answer = acceptRequest(input);
            else if (input.startsWith("Decline Request"))
                answer = declineRequest(input);
            else if (input.startsWith("Remove Friend"))
                answer = removeFriend(input);
            else if (input.startsWith("win number"))
                answer = showNumberOfWins(input);
            else if (input.startsWith("lose number"))
                answer = showNumberOfLoses(input);
            else if (input.startsWith("play number"))
                answer = showNumberOfPlayed(input);
            else if (input.startsWith("Player ToTal Point"))
                answer = showNumberOfToTalPoints(input);
            else if (input.startsWith("points number"))
                answer = showNumberOfPointsInThisGame(input);
            else if (input.startsWith("battle scoreboard "))
                answer = showScoreBoardBattleSea(input);
            else if (input.startsWith("dots scoreboard "))
                answer = showScoreBoardDotsAndBoxes(input);
            else if (input.startsWith("suggestion list"))
                answer = getSuggestion();
            else if (input.startsWith("Remove Suggestion"))
                answer = removeSuggestionServer(input);
            else if (input.startsWith("Add Suggestion"))
                answer = addSuggestionServer(input);
            else if (input.startsWith("Make Event "))
                answer = makeEvent(input);
            else if (input.startsWith("Load Event Activation"))
                answer = getEventActivation(input);
            else if (input.startsWith("Player Join Event "))
                answer = joinEvent(input);
            else if (input.startsWith("Mvp battle "))
                answer = battleMvp();
            else if (input.startsWith("Mvp dots "))
                answer = dotsMvp();
            else if (input.startsWith("Mvp plato "))
                answer = platoMvp();
            else if (input.startsWith("Admin battle detail"))
                answer = battleDetail(input);
            else if (input.startsWith("Admin dots detail"))
                answer = dotsDetail(input);
            else if (input.startsWith("Active Game "))
                answer = activeGame(input);
            else if (input.startsWith("Deactivate Game "))
                answer = deActiveGame(input);
            else if (input.startsWith("Total Played Battle "))
                answer = totalPlayFirstGame();
            else if (input.startsWith("Total Played Dots "))
                answer = totalPlaySecondGame();
            else if (input.startsWith("Total Played Plato "))
                answer = totalPlayPlatoGame();
            else if (input.startsWith("Activation Status "))
                answer = gameActivationStatus(input);
            else if (input.startsWith("Maintenance Status "))
                answer = getMaintenanceStatus(input);
            else if (input.startsWith("Admin info "))
                answer = showAdminInfo();
            else if (input.startsWith("Message plato "))
                answer = showMessageAdmin();
            else if (input.startsWith("Send message "))
                answer = sendMessageAdmin(input);
            else if (input.startsWith("Admin edit profile "))
                answer = editAdminProfile(input);
            else if (input.startsWith("Delete Event "))
                answer = deleteEvent(input);
            else if (input.startsWith("Event Edit "))
                answer = editEventDetails(input);
            else if (input.startsWith("Event Score "))
                answer = findEventScore(input);
            else if (input.startsWith("Event Comment "))
                answer = findEventComment(input);
            else if (input.startsWith("Find Event name "))
                answer = findEventName(input);
            else if (input.startsWith("Date Event Start "))
                answer = findEventDateStart(input);
            else if (input.startsWith("Date Event End "))
                answer = findEventDateEnd(input);
            else if (input.startsWith("Player Activity "))
                answer = playerActivityStatus(input);
            else if (input.startsWith("Ban Player "))
                answer = playerBanStatus(input);
            else if (input.startsWith("Unban Player "))
                answer = playerUnbanStatus(input);
            else if (input.startsWith("Report Player "))
                answer = playerReportStatus(input);
            else if (input.startsWith("playy with"))
                answer = letsPLay(input);
            else if (input.startsWith("play Req"))
                answer = playReq(input);
            else if (input.startsWith("Waiting To Play"))
                answer = waitingToPlay(input);
            else if (input.startsWith("Set Pass For Play "))
                answer = setPassForPlay(input);
            else if (input.startsWith("Game Matcher "))
                answer = gameMatcher(input);
            else if (input.startsWith("Change Coordinate Processor "))
                answer = changeCoordinateProcessor(input);
            else if (input.startsWith("Get Player Ship Coordinate "))
                answer = getPlayerShipCoordinate(input);
            else if (input.startsWith("BattleShip Player Attack "))
                answer = playerAttack(input);
            else if (input.startsWith("BattleShip Player Turn"))
                answer = battleShipPlayerTurn(input);
            else if (input.startsWith("BattleShip Change Turn"))
                answer = battleShipChangeTurn(input);
            else if (input.startsWith("BattleShip Player Correct Boom"))
                answer = battleShipPlayerCorrectBoom(input);
            else if (input.startsWith("BattleShip Player InCorrect Boom"))
                answer = battleShipPlayerInCorrectBoom(input);
            else if (input.startsWith("Get DotsAndBoxes Point"))
                answer = getPlayerPoint(input);
            else if (input.startsWith("Check Game Is Over Dots"))
                answer = checkGameIsOverDots(input);
            else if (input.startsWith("Do The Commands Dots"))
                answer = doTheCommandsDots(input);
            else if (input.startsWith("Is Box Completed"))
                answer = isBoxCompleted(input);
            else if (input.startsWith("Is This Box Completed"))
                answer = isThisBoxCompleted(input);
            else if (input.startsWith("Dots Whose Turn"))
                answer = dotsWhoseTurn(input);
            else if (input.startsWith("Dots Who Is Winner"))
                answer = dotsWhoIsWinner(input);
            else if (input.startsWith("Get Blue Lines"))
                answer = getBlueLines(input);
            else if (input.startsWith("Get Red Lines"))
                answer = getRedLines(input);
            else if (input.startsWith("Give Score And Edit PlayerLog"))
                answer = giveScoreAndEditPlayerLog(input);
            else if (input.startsWith("History Saver"))
                answer = historySaver(input);
            else if (input.startsWith("Enemy Username"))
                answer = enemyUsername(input);
            else if (input.startsWith("Online Player Status"))
                answer = onlinePlayerStatus();
            else if (input.startsWith("Remove Game Matcher"))
                answer = removeGameMatcher(input);

            return answer;
        }

        private String removeGameMatcher(String string){
            String[] process = string.split("\\s");
            GameMatcher gameMatcher = GameMatcher.gameMatcherFinder(process[3]);
            GameMatcher.removeGameMatcher(gameMatcher);
            return "done";
        }

        /***************************************/

        private String getPlayerPoint(String string){
            String[] process = string.split("\\s");
            GameMatcher gameMatcher = GameMatcher.gameMatcherFinder(process[3]);
            if (process[4].equals("red")){
                return String.valueOf(gameMatcher.getDotsAndBoxesController().getRedPoints());
            }else {
                return String.valueOf(gameMatcher.getDotsAndBoxesController().getBluePlayer());
            }

        }

        private String checkGameIsOverDots(String string){
            String[] process = string.split("\\s");
            GameMatcher gameMatcher = GameMatcher.gameMatcherFinder(process[5]);
            return gameMatcher.getDotsAndBoxesController().checkGameIsOver();

        }


        private String doTheCommandsDots(String string){
            String[] process = string.split("\\s");
            GameMatcher gameMatcher = GameMatcher.gameMatcherFinder(process[4]);
            try {
                gameMatcher.getDotsAndBoxesController().doTheCommands(process[5]);
                return "done";
            } catch (ExistLineException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            }

        }


        private String isBoxCompleted(String string){
            String[] process = string.split("\\s");
            GameMatcher gameMatcher = GameMatcher.gameMatcherFinder(process[3]);
            return gameMatcher.getDotsAndBoxesController().isBoxCompleted1();
        }


        private String dotsWhoseTurn(String string){
            String[] process = string.split("\\s");
            GameMatcher gameMatcher = GameMatcher.gameMatcherFinder(process[3]);
            return String.valueOf(gameMatcher.getDotsAndBoxesController().turnColor());
        }


        private String dotsWhoIsWinner(String string){
            String[] process = string.split("\\s");
            GameMatcher gameMatcher = GameMatcher.gameMatcherFinder(process[4]);
            return gameMatcher.getDotsAndBoxesController().whoIsWinner();
        }



        private String isThisBoxCompleted(String string){
            String[] process = string.split("\\s");
            GameMatcher gameMatcher = GameMatcher.gameMatcherFinder(process[4]);
            return gameMatcher.getDotsAndBoxesController().isThisBoxCompleted(process[5]);
        }

        private String getBlueLines(String string){
            String[] process = string.split("\\s");
            GameMatcher gameMatcher = GameMatcher.gameMatcherFinder(process[3]);
            return gameMatcher.getDotsAndBoxesController().blueLines();
        }

        private String getRedLines(String string){
            String[] process = string.split("\\s");
            GameMatcher gameMatcher = GameMatcher.gameMatcherFinder(process[3]);
            return gameMatcher.getDotsAndBoxesController().redLines();
        }



        /***************************************/

        private String changeCoordinateProcessor(String string) {
            String[] process = string.split("\\s");
            GameMatcher gameMatcher = GameMatcher.gameMatcherFinder(process[3]);
            try {

                if (gameMatcher.getPlayer1().equals(process[3])) {

                    gameMatcher.getBattleSeaController().changeCoordinateProcessor("player1", string.substring(string.indexOf(process[4])));
                    return "done";

                } else {
                    gameMatcher.getBattleSeaController().changeCoordinateProcessor("player2", string.substring(string.indexOf(process[4])));
                    return "done";
                }


            } catch (PlacedShipException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            } catch (NewCoordinateForShipException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            } catch (CorrectCoordinateForShipException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            } catch (ExistOtherShipException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            } catch (InvalidCommandException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            }


        }

        private String getPlayerShipCoordinate(String string) {
            String[] process = string.split("\\s");
            GameMatcher gameMatcher = GameMatcher.gameMatcherFinder(process[4]);
            if (gameMatcher.getPlayer1().equals(process[4])){
                return gameMatcher.getBattleSeaController().getPlayer1Coordinate(Integer.parseInt(process[5]));
            }else {
                return gameMatcher.getBattleSeaController().getPlayer2Coordinate(Integer.parseInt(process[5]));
            }
        }

        private String playerAttack(String string){
            String[] process = string.split("\\s");
            GameMatcher gameMatcher = GameMatcher.gameMatcherFinder(process[3]);
            try {

                if (gameMatcher.getPlayer1().equals(process[3])){
                    return gameMatcher.getBattleSeaController().boomProcessor("player1",string.substring(string.indexOf(process[4])));

            }else {
                return gameMatcher.getBattleSeaController().boomProcessor("player2",string.substring(string.indexOf(process[4])));
            }

            } catch (BattleShipWinner battleShipWinner) {
                System.err.println(battleShipWinner.getMessage());
                return battleShipWinner.getMessage();
            } catch (BoomCheckException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            } catch (InvalidCommandException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            } catch (CorrectCoordinateForShipException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            }

        }

        private String battleShipPlayerTurn(String string){
            String[] process = string.split("\\s");
            GameMatcher gameMatcher = GameMatcher.gameMatcherFinder(process[3]);
            if (gameMatcher.getWhoseTurn().equals("player1")){
                return gameMatcher.getPlayer1();
            }else {
                return gameMatcher.getPlayer2();
            }

        }

        private String battleShipChangeTurn(String string){
            String[] process = string.split("\\s");
            GameMatcher gameMatcher = GameMatcher.gameMatcherFinder(process[3]);
            if (gameMatcher.getWhoseTurn().equals("player1")){
                gameMatcher.setWhoseTurn("player2");
            }else {
                gameMatcher.setWhoseTurn("player1");
            }
            return "done";
        }

        private String battleShipPlayerCorrectBoom(String string){
            String[] process = string.split("\\s");
            GameMatcher gameMatcher = GameMatcher.gameMatcherFinder(process[4]);
            if(gameMatcher.getPlayer1().equals(process[4])){
                return new Gson().toJson(gameMatcher.getBattleSeaController().getPlayer1CorrectBooms());
            }else {
                return new Gson().toJson(gameMatcher.getBattleSeaController().getPlayer2CorrectBooms());
            }
        }

        private String battleShipPlayerInCorrectBoom(String string){
            String[] process = string.split("\\s");
            GameMatcher gameMatcher = GameMatcher.gameMatcherFinder(process[4]);
            if(gameMatcher.getPlayer1().equals(process[4])){
                return new Gson().toJson(gameMatcher.getBattleSeaController().getPlayer1InCorrectBooms());
            }else {
                return new Gson().toJson(gameMatcher.getBattleSeaController().getPlayer2InCorrectBooms());
            }
        }

        /***************************************/

        private String letsPLay(String string) {
            String[] process = string.split("\\s");
            return OnlineUsers.onlineUsersFinder(process[2]).getRequestForGame();
        }

        private String setPassForPlay(String string) {
            String[] process = string.split("\\s");
            OnlineUsers.onlineUsersFinder(process[4]).setPassForPlay(Boolean.parseBoolean(process[5]));
            return "done";
        }

        private String waitingToPlay(String string) {
            String[] process = string.split("\\s");
            return String.valueOf(OnlineUsers.onlineUsersFinder(process[3]).getPassForPlay());
        }

        private String playReq(String string) {
            String[] process = string.split("\\s");
            OnlineUsers.onlineUsersFinder(process[3]).setRequestForGame(process[2]);
            return "done";
        }

        private String gameMatcher(String string) {
            String[] process = string.split("\\s");
            GameMatcher.addNewGameMatcher(new GameMatcher(process[2], process[3], Integer.parseInt(process[4])));
            return "done";
        }

        private String enemyUsername(String string) {
            String[] process = string.split("\\s");
            GameMatcher gameMatcher = GameMatcher.gameMatcherFinder(process[2]);
            if (gameMatcher.getPlayer1().equals(process[2])){
                return gameMatcher.getPlayer2();
            }else {
                return gameMatcher.getPlayer1();
            }
        }

        private String giveScoreAndEditPlayerLog(String string) {
            String[] process = string.split("\\s");
            Game.giveScoreAndEditPlayerLog(process[5],process[6],process[7],Long.parseLong(process[8]));
            return "done";
        }

        private String historySaver(String string) {
            String[] process = string.split("\\s");
            Game.historySaver(LocalDate.parse(process[2]),process[3],process[4],process[5]);
            return "done";
        }

        private String makePlayerOnline(String string) {
            String[] process = string.split("\\s");
            OnlineUsers.addNewOnlineUser(new OnlineUsers(process[3], "Login"));
            return "done";
        }

        private String makePlayerOffline(String string) {
            String[] process = string.split("\\s");
            OnlineUsers.makePlayerOffline(process[3]);
            return "done";
        }

        private String changePlayerStatus(String string) {
            String[] process = string.split("\\s");
            OnlineUsers.changePlayerStatus(process[3], string.substring(string.indexOf(process[4])));
            return "done";
        }

        private String onlinePlayerStatus() {
            StringBuilder stringBuilder = new StringBuilder();

            for (OnlineUsers user : OnlineUsers.getOnlineUsers()){
                stringBuilder.append(user.getUsername()).append("~~~~~~").append(user.getStatus()).append("$");
            }

            return String.valueOf(stringBuilder);
        }

        private String onlinePlayerInThisGame(String string) {
            String[] process = string.split("\\s");
            return onlineUsersController.onlineGameInThisGame(process[5]);
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

        private String editProfileBio(String string) {
            String[] process = string.split("\\s");

            try {
                playerGeneralController.editProfileURL(process[3], string.substring(string.indexOf(process[4])));
                return "done";
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }

            return "Failure";
        }

        private String editProfileDetails(String string) {
            String[] process = string.split("\\s");

            try {
                playerGeneralController.editField(process[3] + " " + process[4] + " " + process[5]);
                return "done";
            } catch (InvalidNameException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            } catch (InvalidEmailException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            } catch (InvalidPhoneNumberException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            } catch (ExistEmailException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            } catch (InvalidFieldException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            } catch (IOException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            }

        }

        private String editAdminProfile(String string) {
            String[] process = string.split("\\s");
            try {
                adminGeneralController.editField(process[3] + " " + process[4]);
                return "done";
            } catch (InvalidNameException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            } catch (InvalidEmailException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            } catch (InvalidPhoneNumberException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            } catch (ExistEmailException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            } catch (InvalidFieldException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            } catch (IOException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            }
        }

        private String editEventDetails(String string) {
            String[] process = string.split("\\s");
            try {
                adminGeneralController.editEvent(process[2] + " " + process[3] + " " + process[4]);
                return "done";
            } catch (InvalidDateException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            } catch (InvalidFieldException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            } catch (StartDatesException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            } catch (ExistEventException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            } catch (IOException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            } catch (NotNullMessageException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            } catch (InvalidGameNameException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            }
        }

        private String editPassword(String string) {
            String[] process = string.split("\\s");
            try {
                playerGeneralController.editPassword(string.substring(string.indexOf(process[2])));
                return "done";
            } catch (InvalidPasswordException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            } catch (WrongPasswordException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            } catch (SamePasswordException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            } catch (IOException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            } catch (StrongerPasswordException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            }

        }

        private String confirmPassword(String string) {
            String[] process = string.split("\\s");
            return Existence.checkPasswordForView(process[2], process[3]);
        }

        private String getAdminUsername() {
            return Admin.getAdmins().get(0).getUserName();
        }

        private String deletePlayer(String string) {
            String[] process = string.split("\\s");
            try {
                playerGeneralController.deleteUser(process[2]);
                return "done";
            } catch (IOException e) {
                return "failure";
            }
        }

        private String login(String input) {
            Jwt util = new Jwt();
            String[] token = input.split("\\s");
            Claims c = util.getClaims("ata", token[1]);

            String username = c.getSubject();
            String password = c.getId();
//            int commaIndex = input.indexOf(",");
//            String username = input.substring(6, commaIndex);
//            String password = input.substring(commaIndex + 1);
            if (util.isString(username) && util.isString(password)) {


                String out = username + " " + password;


            if (adminGeneralController.getAdminUserName().equals(username)) {
                try {
                    logIn.loginAsAdmin(out);
                    this.admin = Admin.getAdmins().get(0);
                    this.username = Admin.getAdmins().get(0).getUserName();
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
                    this.player = FindPlayerByInfo.findByUserName(username);
                    this.username = FindPlayerByInfo.findByUserName(username).getUserName();
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
            else {
                return "Sth is wrong";
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

        private String getPlayersBasicInformation(String input) {
            String[] process = input.split("\\s");
            try {
                return playerGeneralController.showBasicInformation(process[3]);
            } catch (ExistPlayerException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            }
        }

        private String getPlayersAge(String input) {
            String[] process = input.split("\\s");
            try {
                return playerGeneralController.showUserAge(process[2]);
            } catch (ExistPlayerException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
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

        private String getPlayerGameLog(String string) {
            String[] process = string.split("\\s");

            try {
                return playerGeneralController.showUserLog(process[3]);
            } catch (ExistPlayerLogException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            } catch (ExistPlayerException e) {
                System.err.println(e.getMessage());
                return "No Player";
            }
        }

        private String getPlayersList() {
            return new Gson().toJson(Player.getPlayers());
        }

        private String getSuggestion() {
            return new Gson().toJson(Suggestion.getAllSuggestions());
        }

        private String playerFavoriteGames(String string) {
            String[] process = string.split("\\s");
            try {
                return playerGeneralController.showFavoritesGames(process[3]);
            } catch (ExistFavoriteException e) {
                return "There is no Favorite Games";
            }
        }

        private String addPlayerFavoriteGames(String string) {
            String[] process = string.split("\\s");

            if (process[5].equalsIgnoreCase("first")) {
                try {
                    playerGeneralController.addGameToFavoritesGames(process[4], adminGeneralController.firstGameNameGetter());
                    return "done";
                } catch (ExistFavoriteException e) {
                    System.err.println(e.getMessage());
                    return "THIS GAME ALREADY EXISTS";
                } catch (InvalidGameNameException e) {
                    System.err.println(e.getMessage());
                    return e.getMessage();
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                    return e.getMessage();
                }

            } else if (process[5].equalsIgnoreCase("second")) {

                try {
                    playerGeneralController.addGameToFavoritesGames(process[4], adminGeneralController.secondGameNameGetter());
                    return "done";
                } catch (ExistFavoriteException e) {
                    System.err.println(e.getMessage());
                    return "THIS GAME ALREADY EXISTS";
                } catch (InvalidGameNameException e) {
                    System.err.println(e.getMessage());
                    return e.getMessage();
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                    return e.getMessage();
                }
            } else {
                return "Invalid input Game";
            }
        }

        private String removePlayerFavoriteGames(String string) {
            String[] process = string.split("\\s");

            if (process[5].equalsIgnoreCase("first")) {
                try {
                    playerGeneralController.RemoveFavoritesGames(process[4], adminGeneralController.firstGameNameGetter());
                    return "done";
                } catch (ExistFavoriteException e) {
                    System.err.println(e.getMessage());
                    return "THIS GAME DOESN'T EXIST IN YOUR LIST";
                } catch (InvalidGameNameException e) {
                    System.err.println(e.getMessage());
                    return e.getMessage();
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                    return e.getMessage();
                }

            } else if (process[5].equalsIgnoreCase("second")) {

                try {
                    playerGeneralController.RemoveFavoritesGames(process[4], adminGeneralController.secondGameNameGetter());
                    return "done";
                } catch (ExistFavoriteException e) {
                    System.err.println(e.getMessage());
                    return "THIS GAME DOESN'T EXIST IN YOUR LIST";
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                    return e.getMessage();
                } catch (InvalidGameNameException e) {
                    System.err.println(e.getMessage());
                    return e.getMessage();
                }

            } else {
                return "Invalid input Game";
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

        private String sentFriendRequest(String string) {
            String[] process = string.split("\\s");

            try {
                playerGeneralController.addFriends(process[3], process[4]);
                return "done";
            } catch (ExistFriendException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            } catch (ExistPlayerException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            } catch (IOException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            }

        }

        private String acceptRequest(String string) {
            String[] process = string.split("\\s");
            try {
                playerGeneralController.acceptRequest(process[2], process[3]);
                return "done";
            } catch (ExistPlayerException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            } catch (AcceptAndDeclineFriendException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            } catch (IOException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            }
        }

        private String declineRequest(String string) {
            String[] process = string.split("\\s");
            try {
                playerGeneralController.declineRequest(process[2], process[3]);
                return "done";
            } catch (ExistPlayerException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            } catch (AcceptAndDeclineFriendException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            } catch (IOException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            }
        }

        private String removeFriend(String string) {
            String[] process = string.split("\\s");
            try {
                playerGeneralController.removeFriend(process[2], process[3]);
                return "done";
            } catch (ExistFriendException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            } catch (ExistPlayerException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            } catch (IOException e) {
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

        private String showDotsDetails() {
            return playerGeneralController.dotsDetails();
        }

        private String firstGameName() {
            return adminGeneralController.firstGameNameGetter();
        }

        private String secondGameName() {
            return adminGeneralController.secondGameNameGetter();
        }

        private String showNumberOfWins(String string) {
            String[] process = string.split("\\s");
            if (process[3].equalsIgnoreCase("first")) {
                try {
                    return playerGeneralController.showNumberOFWins(process[2], adminGeneralController.firstGameNameGetter());
                } catch (InvalidGameNameException e) {
                    System.err.println(e.getMessage());
                    return e.getMessage();
                }
            } else if (process[3].equalsIgnoreCase("second")) {
                try {
                    return playerGeneralController.showNumberOFWins(process[2], adminGeneralController.secondGameNameGetter());
                } catch (InvalidGameNameException e) {
                    System.err.println(e.getMessage());
                    return e.getMessage();
                }
            }
            return "invalid";
        }

        private String showNumberOfLoses(String string) {
            String[] process = string.split("\\s");
            if (process[3].equalsIgnoreCase("first")) {
                try {
                    return playerGeneralController.numberOfLossesInThisGame(process[2], adminGeneralController.firstGameNameGetter());
                } catch (InvalidGameNameException e) {
                    System.err.println(e.getMessage());
                    return e.getMessage();
                }
            } else if (process[3].equalsIgnoreCase("second")) {
                try {
                    return playerGeneralController.numberOfLossesInThisGame(process[2], adminGeneralController.secondGameNameGetter());
                } catch (InvalidGameNameException e) {
                    System.err.println(e.getMessage());
                    return e.getMessage();
                }
            }
            return "invalid";
        }

        private String showNumberOfPlayed(String string) {
            String[] process = string.split("\\s");
            if (process[3].equalsIgnoreCase("first")) {
                try {
                    return playerGeneralController.showNumberOfGamePlayedInThisGame(process[2], adminGeneralController.firstGameNameGetter());
                } catch (InvalidGameNameException e) {
                    System.err.println(e.getMessage());
                    return e.getMessage();
                }
            } else if (process[3].equalsIgnoreCase("second")) {
                try {
                    return playerGeneralController.showNumberOfGamePlayedInThisGame(process[2], adminGeneralController.secondGameNameGetter());
                } catch (InvalidGameNameException e) {
                    System.err.println(e.getMessage());
                    return e.getMessage();
                }
            }
            return "invalid";
        }

        private String showNumberOfPointsInThisGame(String string) {
            String[] process = string.split("\\s");
            if (process[3].equalsIgnoreCase("first")) {
                try {
                    return playerGeneralController.showPlayerPointsInThisGame(process[2], adminGeneralController.firstGameNameGetter());
                } catch (InvalidGameNameException e) {
                    System.err.println(e.getMessage());
                    return e.getMessage();
                }
            } else if (process[3].equalsIgnoreCase("second")) {
                try {
                    return playerGeneralController.showPlayerPointsInThisGame(process[2], adminGeneralController.secondGameNameGetter());
                } catch (InvalidGameNameException e) {
                    System.err.println(e.getMessage());
                    return e.getMessage();
                }
            }
            return "invalid";
        }

        private String showNumberOfToTalPoints(String string) {
            String[] process = string.split("\\s");

            try {
                return playerGeneralController.showPoint(process[3]);
            } catch (ExistPlayerException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            }


        }

        private String showScoreBoardBattleSea(String string) {
            String[] process = string.split("\\s");
            try {
                return playerGeneralController.showScoreboardInThisGame(process[2]);
            } catch (InvalidGameNameException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            }

        }

        private String showScoreBoardDotsAndBoxes(String string) {
            String[] process = string.split("\\s");
            try {
                return playerGeneralController.showScoreboardInThisGame(process[2]);
            } catch (InvalidGameNameException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            }

        }

        private String removeSuggestionServer(String string) {
            String[] process = string.split("\\s");
            try {
                adminGeneralController.removeSuggestion(process[2]);
                return "done";
            } catch (ExistSuggestionException e) {

                System.err.println(e.getMessage());
                return e.getMessage();

            } catch (IOException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            }

        }

        private String addSuggestionServer(String string) {
            String[] process = string.split("\\s");
            try {
                adminGeneralController.addSuggestion(process[2]);
                return "done";
            } catch (IOException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            } catch (ExistPlayerException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            }

        }

        private String makeEvent(String string) {
            String[] process = string.split("\\s");
            try {
                adminGeneralController.addEvent(string.substring(string.indexOf(process[2])));
                return "done";
            } catch (StartDatesException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            } catch (IOException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            } catch (ExistEventException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            }
        }

        private String battleMvp() {
            return adminGeneralController.getMVPUserFirstGame();
        }

        private String dotsMvp() {
            return adminGeneralController.getMVPUserSecondGame();
        }

        private String showAdminInfo() {
            return adminGeneralController.showAdminInfo();
        }

        private String platoMvp() {
            return adminGeneralController.getMVPUserSecondGame();
        }

        private String battleDetail(String string) {
            String[] process = string.split("\\s");
            try {
                adminGeneralController.setDetails(process[3], string.substring(string.indexOf(process[4])));
                return "done";
            } catch (IOException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            }
        }

        private String dotsDetail(String string) {
            String[] process = string.split("\\s");
            try {
                adminGeneralController.setDetails(process[3], string.substring(string.indexOf(process[4])));
                return "done";
            } catch (IOException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            }
        }

        private String activeGame(String string) {
            String[] process = string.split("\\s");

            try {
                adminGeneralController.activeGame(process[2]);
                return "done";
            } catch (InvalidGameID e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            } catch (IOException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            } catch (GameActivation e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            }
        }

        private String deActiveGame(String string) {
            String[] process = string.split("\\s");
            try {
                adminGeneralController.deActiveGame(process[2]);
                return "done";
            } catch (InvalidGameID e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            } catch (IOException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            } catch (GameActivation e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            }
        }

        private String totalPlayFirstGame() {
//            if (new Jwt().isInteger(adminGeneralController.numberOfTotalPlayedFirstGame())){
//                return adminGeneralController.numberOfTotalPlayedFirstGame();
//            }
//            else {
//                return "improper inputs";
//            }
            return adminGeneralController.numberOfTotalPlayedFirstGame();
        }

        private String totalPlaySecondGame() {
            if (new Jwt().isInteger(adminGeneralController.numberOfTotalPlayedSecondGame())){
                return adminGeneralController.numberOfTotalPlayedSecondGame();
            }
            else {
                return "improper inputs";
            }

            //return adminGeneralController.numberOfTotalPlayedSecondGame();
        }

        private String totalPlayPlatoGame() {
            if (new Jwt().isInteger(adminGeneralController.numberOfTotalPlayed())){
                return adminGeneralController.numberOfTotalPlayed();
            }
            else {
                return "improper inputs";
            }

        }

        private String gameActivationStatus(String string) {
            String[] process = string.split("\\s");
            try {
                return adminGeneralController.activationStatus(process[2]);
            } catch (InvalidGameID e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            }
        }

        private String getMaintenanceStatus(String string) {
            String[] process = string.split("\\s");
            try {
                return adminGeneralController.maintenanceStatus(process[2]);
            } catch (InvalidGameID invalidGameID) {
                System.err.println(invalidGameID.getMessage());
                return invalidGameID.getMessage();
            }
        }

        private String showMessageAdmin() {
            return playerGeneralController.viewBotMessages();
        }

        private String sendMessageAdmin(String string) {
            String[] process = string.split("\\s");
            try {
                adminGeneralController.sendMassageString(string.substring(string.indexOf(process[2])));
                return "Message send!";
            } catch (IOException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            } catch (NotNullMessageException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            }
        }

        private String setRememberStatus(String string) {
            String[] process = string.split("\\s");
            try {
                playerGeneralController.setRememberPasswordStatus(process[3], process[4]);
                return "done";
            } catch (IOException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            }
        }

        private String getRememberStatus(String string) {
            String[] process = string.split("\\s");
            return playerGeneralController.rememberPasswordStatus(process[3]);
        }

        private String getDirectPassword(String string) {
            String[] process = string.split("\\s");
            return playerGeneralController.getUsernamePassword(process[3]);
        }

        private String joinEvent(String string) {
            String[] process = string.split("\\s");
            try {
                playerGeneralController.joinEvent(process[3], process[4]);
                return "done";
            } catch (ExistEventException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            } catch (IOException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            }
        }

        private String getEventActivation(String string) {
            String[] process = string.split("\\s");
            try {
                return playerGeneralController.eventActivation(process[3]);
            } catch (ExistEventException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            }
        }

        private String deleteEvent(String string) {
            String[] process = string.split("\\s");
            try {
                adminGeneralController.removeEventByAdminFromView(process[2]);
                return "done";
            } catch (ExistEventException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            } catch (IOException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            }
        }

        private String findEventName(String string) {
            String[] process = string.split("\\s");
            try {
                return adminGeneralController.eventFinderByEventID(process[3]).getGameName();
                //return "name changed";
            } catch (ExistEventException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            }
        }

        private String findEventDateStart(String string) {
            String[] process = string.split("\\s");
            try {
                return adminGeneralController.eventFinderByEventID(process[3]).getStartDate().toString();
                //return "date changed";
            } catch (ExistEventException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            }
        }

        private String findEventDateEnd(String string) {
            String[] process = string.split("\\s");
            try {
                return adminGeneralController.eventFinderByEventID(process[3]).getEndDate().toString();
                //return "date changed";
            } catch (ExistEventException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            }
        }

        private String findEventScore(String string) {
            String[] process = string.split("\\s");
            try {
                return String.valueOf(adminGeneralController.eventFinderByEventID(process[2]).getScore());
                //return "score changed";
            } catch (ExistEventException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            }
        }

        private String findEventComment(String string) {
            String[] process = string.split("\\s");
            try {
                System.out.println(adminGeneralController.eventFinderByEventID(process[2]));
                System.out.println(adminGeneralController.eventFinderByEventID(process[2]).getComment());
                return adminGeneralController.eventFinderByEventID(process[2]).getComment();
                //return "comment changed";
            } catch (ExistEventException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            }
        }

        private String playerActivityStatus(String string) {
            String[] process = string.split("\\s");
            return adminGeneralController.playerActivation(process[2]);
        }

        private String playerBanStatus(String string) {
            String[] process = string.split("\\s");
            try {
                adminGeneralController.banPlayer(process[2]);
                return "done";
            } catch (AlreadyBan e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            } catch (IOException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            }
        }

        private String playerUnbanStatus(String string) {
            String[] process = string.split("\\s");
            try {
                adminGeneralController.unBanPlayer(process[2]);
                return "done";
            } catch (AlreadyBan e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            } catch (IOException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            } catch (ItsNotBan e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            }
        }

        private String playerReportStatus(String string) {
            String[] process = string.split("\\s");
            try {
                return adminGeneralController.showReportListOfPlayer(process[2]);
            } catch (EmptyReportsList e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            } catch (InvalidUserNameException e) {
                System.err.println(e.getMessage());
                return e.getMessage();
            }
        }


        @Override
        public String toString() {
            return "ClientHandler{" +
                    "clientSocket=" + clientSocket +
                    ", dataOutputStream=" + dataOutputStream +
                    ", dataInputStream=" + dataInputStream +
                    ", username='" + username + '\'' +
                    ", player=" + player +
                    ", admin=" + admin +
                    '}';
        }
    }


}
