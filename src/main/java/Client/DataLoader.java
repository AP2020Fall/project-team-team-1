package Client;

import Client.View.AdminEditSuggestion;
import Server.Model.PlatoModel.Admin;
import Server.Model.PlatoModel.Event;
import Server.Model.PlatoModel.Player;
import Client.View.PlayerEventsController;
import Client.View.PlayerSearchFriendsController;
import Server.Model.PlatoModel.Suggestion;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class DataLoader {
    //todo add coder
    //todo add request watcher for lot requests

    /***************************REGISTER***********************/
    public String register(String info) throws IOException {
        Client.getDataOutputStream().writeUTF("Register " + info);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String login(String username, String password) throws IOException {
        Client.getDataOutputStream().writeUTF("login " + username + "," + password);
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

    public String deletePlayer(String username, String password) throws IOException {
        Client.getDataOutputStream().writeUTF("Delete Player " + username + " " + password);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String editProfileAdmin(String field , String input) throws IOException {
        Client.getDataOutputStream().writeUTF("Admin edit profile " + field + " " + input);
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
    }

    public void loadPlayersList() throws IOException {
        Client.getDataOutputStream().writeUTF("Player List");
        Client.getDataOutputStream().flush();
        Type type = new TypeToken<ArrayList<Player>>() {
        }.getType();
        ArrayList<Player> output = new Gson().fromJson(Client.getDataInputStream().readUTF(), type);
        PlayerSearchFriendsController.setPlayerList(output);
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

}
