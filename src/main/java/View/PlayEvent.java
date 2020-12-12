package View;

import Controller.DotsAndBoxesController.DotsAndBoxesController;
import Controller.Exception.Plato.ExistEventException;

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
        playerGeneralController.activeEvent(username);
        System.out.println("Which Event Do You Want To Play ? ");
    }

    @Override
    public void execute() {
        String eventID = scanner.nextLine();
        try {
            if (playerGeneralController.eventGameName(eventID).startsWith("dotsAndBoxes")){
                new RunDotsAndBoxes(username,null,Integer.parseInt(playerGeneralController.eventScore(eventID)),dotsAndBoxesController,this);
            }else if (playerGeneralController.eventGameName(eventID).equalsIgnoreCase("battleship")){
                new RunBattleShip(username,null,Integer.parseInt(playerGeneralController.eventScore(eventID)),this);
            }
        } catch (ExistEventException e) {
            System.out.println(e.getMessage());
        }
        this.parentMenu.run();
    }
}
