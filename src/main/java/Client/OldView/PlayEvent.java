package Client.OldView;

import Server.Controller.DotsAndBoxesController.DotsAndBoxesController;
import Server.Controller.Exception.Plato.ExistEventException;

import java.io.IOException;
import java.util.HashMap;


public class PlayEvent extends Menu {
    private String username;
    private static DotsAndBoxesController dotsAndBoxesController = new DotsAndBoxesController();
    public PlayEvent(String username, Menu parentMenu) {
        super("Play Event", parentMenu);
        this.username = username;
        HashMap<Integer,Menu> submenus = new HashMap<>();

    }

    @Override
    public void show() {
        try {
            String[] showEvent =  playerGeneralController.activeEvent(username).split("\\$");
            for (String out : showEvent) {
                System.out.println(out);
            }

        } catch (ExistEventException | IOException e) {
            System.out.println(e.getMessage());
            this.parentMenu.run();
        }
        System.out.println("Which Event Do You Want To Play ? ");
    }

    @Override
    public void execute() {
        String eventID = scanner.nextLine();
        try {
            if (playerGeneralController.eventGameName(eventID).startsWith("d")||playerGeneralController.eventGameName(eventID).startsWith("D")){
                new RunDotsAndBoxes(username,null,Integer.parseInt(playerGeneralController.eventScore(eventID)),dotsAndBoxesController,this).run();
            }else if (playerGeneralController.eventGameName(eventID).startsWith("b")||playerGeneralController.eventGameName(eventID).startsWith("B")){
                new RunBattleShip(username,null,Integer.parseInt(playerGeneralController.eventScore(eventID)),this).run();
            }
        } catch (ExistEventException e) {
            System.out.println(e.getMessage());
            this.parentMenu.run();
        }
        this.parentMenu.parentMenu.run();
    }
}
