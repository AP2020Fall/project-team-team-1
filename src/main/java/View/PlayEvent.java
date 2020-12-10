package View;

import java.util.HashMap;

public class PlayEvent extends Menu {
    private String username;
    public PlayEvent(String username, Menu parentMenu) {
        super("Play Event", parentMenu);
        this.username = username;
        HashMap<Integer,Menu> submenus = new HashMap<>();

    }

    @Override
    public void show() {
        playerGeneralController.activeEvent(username);
        System.out.println("Which Event Do You Want To Play ? ");
    }

    @Override
    public void execute() {
        String eventID = scanner.nextLine();

        //todo link to games with scores
    }
}
