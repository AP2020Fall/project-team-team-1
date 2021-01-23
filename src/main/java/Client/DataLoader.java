package Client;

import Model.PlatoModel.Admin;
import Model.PlatoModel.Event;
import Model.PlatoModel.Player;
import View.PlayerEventsController;
import View.PlayerSearchFriendsController;
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
    public String setUserProfile(String username,String path) throws IOException {
        Client.getDataOutputStream().writeUTF("Edit Profile " + username + " " + path);
        Client.getDataOutputStream().flush();
        return Client.getDataInputStream().readUTF();
    }

    /**********************************************************/

    public Player loadPlayer(String playerUsername) throws IOException {
        Client.getDataOutputStream().writeUTF("data player " + playerUsername);
        Client.getDataOutputStream().flush();
        return new Gson().fromJson(Client.getDataInputStream().readUTF(), Player.class);

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
}
