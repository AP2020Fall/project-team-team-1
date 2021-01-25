package Client;

import Server.Model.PlatoModel.Admin;
import Server.Model.PlatoModel.Event;
import Server.Model.PlatoModel.Player;
import Client.View.PlayerEventsController;
import Client.View.PlayerSearchFriendsController;
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
    public String editProfileDetails(String username,String kind, String input) throws IOException {
        Client.getDataOutputStream().writeUTF("Edit Profile Details " + username + " "+kind +" " + input);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }
    public String editProfileBio(String username, String input) throws IOException {
        Client.getDataOutputStream().writeUTF("Edit Profile Bio " + username + " " + input);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String setUserProfile(String username, String path) throws IOException {
        Client.getDataOutputStream().writeUTF("Edit Profile Picture " + username + " " + path);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    /**********************************************************/

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

    public String playerAge(String username ) throws IOException {
        Client.getDataOutputStream().writeUTF("Player Age "+ username );
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

    public String loadPlayerFavoriteGames(String username) throws IOException {
        Client.getDataOutputStream().writeUTF("Player Favorite Games " + username);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }
    public String addPlayerFavoriteGames(String username,String gameNumber) throws IOException {
        Client.getDataOutputStream().writeUTF("Add Player Favorite Games " + username + " "+ gameNumber);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }
    public String removePlayerFavoriteGames(String username , String gameNumber) throws IOException {
        Client.getDataOutputStream().writeUTF("Remove Player Favorite Games " + username + " "+ gameNumber);
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

    public String numberOfWins(String username , String gameName) throws IOException {
        Client.getDataOutputStream().writeUTF("win number "+ username +" "+ gameName);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String numberOfLoses(String username , String gameName) throws IOException {
        Client.getDataOutputStream().writeUTF("lose number "+ username +" "+ gameName);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String numberOfPlayThisGame(String username , String gameName) throws IOException {
        Client.getDataOutputStream().writeUTF("play number "+ username +" "+ gameName);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String pointsInThisGame(String username , String gameName) throws IOException {
        Client.getDataOutputStream().writeUTF("points number "+ username +" "+ gameName);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String playerPoints(String username ) throws IOException {
        Client.getDataOutputStream().writeUTF("Player ToTal Point "+ username );
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String scoreBoardInBattle(String gameName) throws IOException {
        Client.getDataOutputStream().writeUTF("battle scoreboard "+ gameName);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String scoreBoardInDots(String gameName) throws IOException {
        Client.getDataOutputStream().writeUTF("dots scoreboard "+ gameName);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String secondGameNameGetter() throws IOException {
        Client.getDataOutputStream().writeUTF("second game name");
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    public String dotsDetails() throws IOException {
        Client.getDataOutputStream().writeUTF("load dots details");
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

}
