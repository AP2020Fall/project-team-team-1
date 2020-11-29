package View;

public class GamesMenu extends Menu {
    private String username;
    public GamesMenu(String username, Menu parentMenu) {
        super("Games", parentMenu);
        this.username=username;
    }
}
