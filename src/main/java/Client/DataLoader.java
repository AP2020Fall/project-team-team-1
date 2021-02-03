package Client;

import Client.View.*;
import Server.Jwt;
import Server.Model.PlatoModel.Admin;
import Server.Model.PlatoModel.Event;
import Server.Model.PlatoModel.Player;
import Server.Model.PlatoModel.Suggestion;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;

public class DataLoader {
    //todo add coder
    //todo add request watcher for lot requests

    String key = "ata";
    Jwt util = new Jwt();


    /***********************************/
    public String waitingToPlay(String username) throws IOException {
        Client.getDataOutputStream().writeUTF("Waiting To Play " + username);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String setPassForPlay(String username, String pass) throws IOException {
        Client.getDataOutputStream().writeUTF("Set Pass For Play " + username + " " + pass);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String letsPlay(String username) throws IOException {
        Client.getDataOutputStream().writeUTF("playy with " + username);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String playReq(String username, String usernameForSent) throws IOException {
        Client.getDataOutputStream().writeUTF("play Req " + username + " " + usernameForSent);
        Client.getDataOutputStream().flush();

        return Client.getDataInputStream().readUTF();
    }

    public String gameMatcher(String p1, String p2, String score) throws IOException {
        Client.getDataOutputStream().writeUTF("Game Matcher " + p1 + " " + p2 + " " + score);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String removeGameMatcher(String p1) throws IOException {
        Client.getDataOutputStream().writeUTF("Remove Game Matcher " + p1);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String giveScoreAndEditPlayerLog(String gameName, String winner, String loser,long score) throws IOException {
        Client.getDataOutputStream().writeUTF("Give Score And Edit PlayerLog " + gameName + " " + winner + " " + loser +" " + score);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String historySaver(LocalDate localDate,String winner,String loser,String gameName) throws IOException {
        Client.getDataOutputStream().writeUTF("History Saver " + localDate + " " + winner + " " + loser+" "+gameName);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String enemyUsername(String username) throws IOException {
        Client.getDataOutputStream().writeUTF("Enemy Username " + username);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    /*******************BATTLE********************************/

    public String changeCoordinateProcessor(String player, String string) throws IOException {
        Client.getDataOutputStream().writeUTF("Change Coordinate Processor " + player + " " + string);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String getPlayerShipCoordinate(String uername, String index) throws IOException {
        Client.getDataOutputStream().writeUTF("Get Player Ship Coordinate " + uername + " " + index);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }


    public String playerAttack(String player, String string) throws IOException {
        Client.getDataOutputStream().writeUTF("BattleShip Player Attack " + player + " " + string);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String battleShipPlayerTurn(String player) throws IOException {
        Client.getDataOutputStream().writeUTF("BattleShip Player Turn " + player);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String battleShipChangeTurn(String player) throws IOException {
        Client.getDataOutputStream().writeUTF("BattleShip Change Turn " + player );
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }


    public String battleShipPlayerCorrectBoom(String player) throws IOException {
        Client.getDataOutputStream().writeUTF("BattleShip Player Correct Boom " + player );
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }


    public String battleShipPlayerInCorrectBoom(String player) throws IOException {
        Client.getDataOutputStream().writeUTF("BattleShip Player InCorrect Boom " + player );
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    /***********************************/
    public String makePlayerOnline(String username) throws IOException {
        Client.getDataOutputStream().writeUTF("Make Player Online " + username);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String makePlayerOffline(String username) throws IOException {
        Client.getDataOutputStream().writeUTF("Make Player Offline " + username);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String changePlayerStatus(String username, String status) throws IOException {
        Client.getDataOutputStream().writeUTF("Change Player Status " + username + " " + status);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String onlinePlayerInThisGame(String gameName) throws IOException {
        //** GameName Should Be 'BattleShip' or 'DotsAndBoxes'
        Client.getDataOutputStream().writeUTF("Online Player In This Game " + gameName);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String onlinePlayerStatus() throws IOException {
        Client.getDataOutputStream().writeUTF("Online Player Status ");
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    /***************************REGISTER***********************/
    public String register(String info) throws IOException {
        //String token = util.generateToken(,,key)
        Client.getDataOutputStream().writeUTF("Register " + info);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String login(String username, String password) throws IOException {
        String token = util.generateToken(password,username,key);
        Client.getDataOutputStream().writeUTF("login " + token);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    /***************************Profile edit******************/
    public String editProfileDetails(String username, String kind, String input) throws IOException {
        Client.getDataOutputStream().writeUTF("Edit Profile Details " + username + " " + kind + " " + input);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String editProfileBio(String username, String input) throws IOException {
        Client.getDataOutputStream().writeUTF("Edit Profile Bio " + username + " " + input);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String editPassWord(String username, String oldPassword, String newPassword) throws IOException {
        Client.getDataOutputStream().writeUTF("Edit Password " + username + " " + oldPassword + " " + newPassword);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String confirmPassWord(String username, String password) throws IOException {
        Client.getDataOutputStream().writeUTF("Confirm Password " + username + " " + password);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String setUserProfile(String username, String path) throws IOException {
        Client.getDataOutputStream().writeUTF("Edit Profile Picture " + username + " " + path);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String deletePlayer(String username) throws IOException {
        Client.getDataOutputStream().writeUTF("Delete Player " + username);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String editProfileAdmin(String field, String input) throws IOException {
        Client.getDataOutputStream().writeUTF("Admin edit profile " + field + " " + input);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String editEventDetails(String eventId, String kind, String input) throws IOException {
        Client.getDataOutputStream().writeUTF("Event Edit " + eventId + " " + kind + " " + input);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String loadAdminUsername() throws IOException {
        Client.getDataOutputStream().writeUTF("Load Admin Username");
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    /*************************Friends***************************/
    public String sentFriendRequest(String username, String friendUsername) throws IOException {
        Client.getDataOutputStream().writeUTF("Sent Friend Request" + username + " " + friendUsername);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String declineRequest(String username, String friendUsername) throws IOException {
        Client.getDataOutputStream().writeUTF("Decline Request " + username + " " + friendUsername);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String acceptRequest(String username, String friendUsername) throws IOException {
        Client.getDataOutputStream().writeUTF("Accept Request " + username + " " + friendUsername);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String removeFriend(String username, String friendUsername) throws IOException {
        Client.getDataOutputStream().writeUTF("Remove Friend " + username + " " + friendUsername);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    /***********************************************************/

    public Player loadPlayer(String playerUsername) throws IOException {
        Client.getDataOutputStream().writeUTF("data player " + playerUsername);
        Client.getDataOutputStream().flush();
        return new Gson().fromJson(Client.getDataInputStream().readUTF(), Player.class);

    }

    public String loadPlayerBasicInformation(String playerUsername) throws IOException {
        Client.getDataOutputStream().writeUTF("Basic Information player " + playerUsername);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();

    }

    public String playerAge(String username) throws IOException {
        Client.getDataOutputStream().writeUTF("Player Age " + username);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public Admin loadAdmin(String adminUsername) throws IOException {
        Client.getDataOutputStream().writeUTF("data admin " + adminUsername);
        Client.getDataOutputStream().flush();
        return new Gson().fromJson(Client.getDataInputStream().readUTF(), Admin.class);
    }

    public void loadEventsList() throws IOException {
        Client.getDataOutputStream().writeUTF("Event List");
        Client.getDataOutputStream().flush();
        Type type = new TypeToken<ArrayList<Event>>() {
        }.getType();
        ArrayList<Event> output = new Gson().fromJson(Client.getDataInputStream().readUTF(), type);
        PlayerEventsController.setEventForShow(output);
        AdminEvents.setEventArrayList(output);
    }

    public void loadPlayersList() throws IOException {
        Client.getDataOutputStream().writeUTF("Player List");
        Client.getDataOutputStream().flush();
        Type type = new TypeToken<ArrayList<Player>>() {
        }.getType();
        ArrayList<Player> output = new Gson().fromJson(Client.getDataInputStream().readUTF(), type);
        PlayerSearchFriendsController.setPlayerList(output);
        AdminUsersController.setPlayerArrayList(output);
    }

    public void loadSuggestion() throws IOException {
        Client.getDataOutputStream().writeUTF("suggestion list");
        Client.getDataOutputStream().flush();
        Type type = new TypeToken<ArrayList<Suggestion>>() {
        }.getType();
        ArrayList<Suggestion> output = new Gson().fromJson(Client.getDataInputStream().readUTF(), type);
        AdminEditSuggestion.setAllSuggestion(output);
    }

    public String loadPlayerFavoriteGames(String username) throws IOException {
        Client.getDataOutputStream().writeUTF("Player Favorite Games " + username);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String addPlayerFavoriteGames(String username, String gameNumber) throws IOException {
        Client.getDataOutputStream().writeUTF("Add Player Favorite Games " + username + " " + gameNumber);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String removePlayerFavoriteGames(String username, String gameNumber) throws IOException {
        Client.getDataOutputStream().writeUTF("Remove Player Favorite Games " + username + " " + gameNumber);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String loadPlayerGameLog(String username) throws IOException {
        Client.getDataOutputStream().writeUTF("Player Game Log " + username);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String loadPlayerSuggestedGames(String username) throws IOException {
        Client.getDataOutputStream().writeUTF("Player Suggested Games " + username);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String loadPlayerPlatoMessage() throws IOException {
        Client.getDataOutputStream().writeUTF("Player Plato Message");
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String validationStatus(String kind, String input) throws IOException {
        Client.getDataOutputStream().writeUTF("Validation " + kind + " " + input);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String battleShipDetails() throws IOException {
        Client.getDataOutputStream().writeUTF("load battle details");
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String firstGameNameGetter() throws IOException {
        Client.getDataOutputStream().writeUTF("first game name");
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String playerFriends(String username) throws IOException {
        Client.getDataOutputStream().writeUTF("Friends List " + username);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String playerRequests(String username) throws IOException {
        Client.getDataOutputStream().writeUTF("Friends Requests " + username);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String numberOfWins(String username, String gameName) throws IOException {
        Client.getDataOutputStream().writeUTF("win number " + username + " " + gameName);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String numberOfLoses(String username, String gameName) throws IOException {
        Client.getDataOutputStream().writeUTF("lose number " + username + " " + gameName);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String numberOfPlayThisGame(String username, String gameName) throws IOException {
        Client.getDataOutputStream().writeUTF("play number " + username + " " + gameName);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String pointsInThisGame(String username, String gameName) throws IOException {
        Client.getDataOutputStream().writeUTF("points number " + username + " " + gameName);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String playerPoints(String username) throws IOException {
        Client.getDataOutputStream().writeUTF("Player ToTal Point " + username);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String scoreBoardInBattle(String gameName) throws IOException {
        Client.getDataOutputStream().writeUTF("battle scoreboard " + gameName);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String scoreBoardInDots(String gameName) throws IOException {
        Client.getDataOutputStream().writeUTF("dots scoreboard " + gameName);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String secondGameNameGetter() throws IOException {
        Client.getDataOutputStream().writeUTF("second game name");
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String dotsDetails() throws IOException {
        Client.getDataOutputStream().writeUTF("Load Dots Details");
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String removeSuggestion(String suggestionId) throws IOException {
        Client.getDataOutputStream().writeUTF("Remove Suggestion " + suggestionId);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String addSuggestion(String suggestionId) throws IOException {
        Client.getDataOutputStream().writeUTF("Add Suggestion " + suggestionId);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String makeEvent(String string) throws IOException {
        Client.getDataOutputStream().writeUTF("Make Event " + string);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String mvpBattle() throws IOException {
        Client.getDataOutputStream().writeUTF("Mvp battle ");
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String mvpPlato() throws IOException {
        Client.getDataOutputStream().writeUTF("Mvp plato ");
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String mvpDots() throws IOException {
        Client.getDataOutputStream().writeUTF("Mvp dots ");
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String setDetailForBattle(String gameName, String text) throws IOException {
        Client.getDataOutputStream().writeUTF("Admin battle detail " + gameName + " " + text);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String setDetailForDots(String gameName, String text) throws IOException {
        Client.getDataOutputStream().writeUTF("Admin dots detail " + gameName + " " + text);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String activeGame(String string) throws IOException {
        Client.getDataOutputStream().writeUTF("Active Game " + string);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String deActiveGame(String string) throws IOException {
        Client.getDataOutputStream().writeUTF("Deactivate Game " + string);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String totalPlayedBattle() throws IOException {
        Client.getDataOutputStream().writeUTF("Total Played Battle ");
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String totalPlayedDots() throws IOException {
        Client.getDataOutputStream().writeUTF("Total Played Dots ");
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String totalPlayedPlato() throws IOException {
        Client.getDataOutputStream().writeUTF("Total Played Plato ");
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String activeStatus(String string) throws IOException {
        Client.getDataOutputStream().writeUTF("Activation Status " + string);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String maintenanceStatus(String string) throws IOException {
        Client.getDataOutputStream().writeUTF("Maintenance Status " + string);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String loadAdminInfo() throws IOException {
        Client.getDataOutputStream().writeUTF("Admin info ");
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String showMessagesForAdmin() throws IOException {
        Client.getDataOutputStream().writeUTF("Message plato ");
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String sendMessageByAdmin(String string) throws IOException {
        Client.getDataOutputStream().writeUTF("Send message " + string);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String setRememberStatus(String username, String bool) throws IOException {
        Client.getDataOutputStream().writeUTF("Set Remember Status " + username + " " + bool);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String loadRememberStatus(String username) throws IOException {
        Client.getDataOutputStream().writeUTF("Load Remember Status " + username);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String loadDirectPassword(String username) throws IOException {
        Client.getDataOutputStream().writeUTF("Load Direct Password " + username);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String loadEventActivation(String eventID) throws IOException {
        Client.getDataOutputStream().writeUTF("Load Event Activation " + eventID);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String playerJoinEvent(String username, String eventID) throws IOException {
        Client.getDataOutputStream().writeUTF("Player Join Event " + username + " " + eventID);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String deleteEvent(String eventID) throws IOException {
        Client.getDataOutputStream().writeUTF("Delete Event " + eventID);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String findEventName(String eventID) throws IOException {
        Client.getDataOutputStream().writeUTF("Find Event name " + eventID);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String findEventDateStart(String eventID) throws IOException {
        Client.getDataOutputStream().writeUTF("Date Event Start " + eventID);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String findEventDateEnd(String eventID) throws IOException {
        Client.getDataOutputStream().writeUTF("Date Event End " + eventID);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String findEventScore(String eventID) throws IOException {
        Client.getDataOutputStream().writeUTF("Event Score " + eventID);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String findEventComment(String eventID) throws IOException {
        Client.getDataOutputStream().writeUTF("Event Comment " + eventID);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String playerActivityState(String username) throws IOException {
        Client.getDataOutputStream().writeUTF("Player Activity " + username);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String playerBanState(String username) throws IOException {
        Client.getDataOutputStream().writeUTF("Ban Player " + username);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String playerUnBanState(String username) throws IOException {
        Client.getDataOutputStream().writeUTF("Unban Player " + username);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String reportedPlayers(String username) throws IOException {
        Client.getDataOutputStream().writeUTF("Report Player " + username);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

}
