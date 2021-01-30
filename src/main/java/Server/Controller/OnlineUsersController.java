package Server.Controller;


import Server.OnlineUsers;

import java.util.ArrayList;

public class OnlineUsersController {

    public String onlineGameInThisGame(String gameName){
        ArrayList<String> usernames = OnlineUsers.onlineUsersInThisGame(gameName);
        StringBuilder stringBuilder = new StringBuilder();
        if (usernames.size() == 0){
            return "No one Online For This Game";
        }
        for (String username : usernames) {
            stringBuilder.append(username).append("$");
        }
        return String.valueOf(stringBuilder);
    }
}
