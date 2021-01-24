package Server;

import java.util.ArrayList;

public class OnlineUsers {
    private static ArrayList<OnlineUsers> onlineUsers = new ArrayList<>();
    private String username;
    private String status;

    public OnlineUsers(String username, String status) {
        this.username = username;
        this.status = status;
    }

    public static ArrayList<OnlineUsers> getOnlineUsers() {
        return onlineUsers;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public  void setStatus(String status) {
        this.status = status;
    }
    public static void addNewOnlineUser(OnlineUsers onlineUsers){
        getOnlineUsers().add(onlineUsers);
    }
    public static void makePlayerOffline(String username){
        OnlineUsers onlineUsers = null;
        for (OnlineUsers onlineUser : onlineUsers.getOnlineUsers()) {
            if (onlineUser.username.equals(username))
                onlineUsers = onlineUser;
        }
        if (onlineUsers == null)
            return;

        getOnlineUsers().remove(onlineUsers);
    }
}
