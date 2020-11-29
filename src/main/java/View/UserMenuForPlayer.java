package View;

public class UserMenuForPlayer extends Menu {
    private String username;
    public UserMenuForPlayer(String username, Menu parentMenu) {
        super("Profile", parentMenu);
        this.username=username;

    }

}
