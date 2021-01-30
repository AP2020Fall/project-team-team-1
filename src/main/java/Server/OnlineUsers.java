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

    public static void makePlayerOffline(String username) {
        OnlineUsers onlineUser = onlineUsersFinder(username);
        if (onlineUser == null)
            return;
        onlineUsers.remove(onlineUser);
    }

    public static void changePlayerStatus(String username,String status){
        OnlineUsers onlineUser = onlineUsersFinder(username);
        if (onlineUser == null){
            return;
        }
        onlineUser.setStatus(status);

    }

    private static OnlineUsers onlineUsersFinder(String username){
        for (OnlineUsers onlineUser : onlineUsers) {
            if (onlineUser.username.equals(username)){
                return onlineUser;
            }
        }
        return null;
    }

    public static ArrayList<String> onlineUsersInThisGame(String gameName){
        ArrayList<String> usernames = new ArrayList<>();
        for (OnlineUsers onlineUser : onlineUsers) {
            if (onlineUser.status.equals(gameName)){
                usernames.add(onlineUser.username);
            }
        }
        return usernames;
    }

    @Override
    public String toString() {
        return "OnlineUsers{" +
                "username='" + username + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
