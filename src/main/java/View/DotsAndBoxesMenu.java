package View;

import Controller.DotsAndBoxesController.DotsAndBoxesController;
import Controller.Exception.Plato.ExistFavoriteException;
import Controller.Exception.Plato.InvalidGameNameException;

import java.io.IOException;
import java.util.HashMap;

public class DotsAndBoxesMenu extends Menu {
    DotsAndBoxesController dotsAndBoxesController = new DotsAndBoxesController();
    private String username;
    public DotsAndBoxesMenu(String username, Menu parentMenu) {
        super("DotsAndBoxes Menu", parentMenu);
        HashMap<Integer,Menu> submenus = new HashMap<>();
        this.username=username;
        submenus.put(1,showScoreBoard());
        submenus.put(2,showDetails());
        submenus.put(3,showLog());
        submenus.put(4,showWinsCount());
        submenus.put(5,showPlayedCount());
        submenus.put(6,addToFavorites());
        submenus.put(7,removeFavorites());
        submenus.put(8,showPoints());
        submenus.put(9,new RunDotsAndBoxes(username,null,dotsAndBoxesController ,this));
        this.setSubmenus(submenus);
    }
    private Menu showScoreBoard(){
        return new Menu("show Score Board",this) {
            @Override
            public void show() {
                System.out.println("Enter back to last Menu");
            }

            @Override
            public void execute() {
                try {
                    playerGeneralController.showScoreboardInThisGame("DotsAndBoxes");
                    this.parentMenu.run();
                } catch (InvalidGameNameException e) {
                    System.out.println(e.getGameName()+e.getMessage());
                    this.parentMenu.run();
                }
            }
        };
    }
    private Menu showDetails(){
        return new Menu("show Details",this) {
            @Override
            public void show() {
                System.out.println("Enter back to last Menu");
            }

            @Override
            public void execute() {
                System.out.println(playerGeneralController.dotsDetails());
                while (true){
                    String next = scanner.nextLine();
                    if (next.equalsIgnoreCase("back")){
                        this.parentMenu.run();
                        break;
                    }
                }
            }
        };
    }
    private Menu showLog(){
        return new Menu("show "+this.username+"'s DotsAndBoxes GameLog",this) {
            @Override
            public void show() {
                System.out.println("Enter back to last Menu");
            }

            @Override
            public void execute() {
                try {
                    playerGeneralController.showGameLogInThisGame(username,"DotsAndBoxes");
                    while (true){
                        String next = scanner.nextLine();
                        if (next.equalsIgnoreCase("back")){
                            this.parentMenu.run();
                            break;
                        }
                    }
                } catch (InvalidGameNameException e) {
                    System.out.println(e.getGameName()+e.getMessage());
                    this.run();
                }

            }
        };
    }
    private Menu showWinsCount(){
        return new Menu("show "+this.username+" WinsCount in DotsAndBoxes",this) {
            @Override
            public void show() {
                System.out.println("Enter back to last Menu");
            }

            @Override
            public void execute() {
                try {
                    playerGeneralController.showNumberOFWins(username,"DotsAndBoxes");
                    while (true){
                        String next = scanner.nextLine();
                        if (next.equalsIgnoreCase("back")){
                            this.parentMenu.run();
                            break;
                        }
                    }
                } catch (InvalidGameNameException e) {
                    System.out.println(e.getGameName()+e.getMessage());
                    this.run();
                }

            }
        };
    }
    private Menu showPlayedCount(){
        return new Menu("show "+this.username+" PlayedCount in DotsAndBoxes",this) {
            @Override
            public void show() {
                System.out.println("Enter back to last Menu");
            }

            @Override
            public void execute() {
                try {
                    playerGeneralController.showNumberOfGamePlayedInThisGame(username,"DotsAndBoxes");
                    while (true){
                        String next = scanner.nextLine();
                        if (next.equalsIgnoreCase("back")){
                            this.parentMenu.run();
                            break;
                        }
                    }
                } catch (InvalidGameNameException e) {
                    System.out.println(e.getGameName()+e.getMessage());
                    this.run();
                }

            }
        };
    }
    private Menu addToFavorites(){
        return new Menu("add to favorites",this) {
            @Override
            public void show() {
                System.out.println("Enter back to last Menu");
            }

            @Override
            public void execute() {
                try {
                    playerGeneralController.addGameToFavoritesGames(username,"DotsAndBoxes");
                    System.out.println("DotsAndBoxes added to Your Favorites Successfully!");
                } catch (ExistFavoriteException e) {
                    System.out.println(e.getGameName()+" "+e.getMessage());
                } catch (InvalidGameNameException e) {
                    System.out.println(e.getGameName()+" "+e.getMessage());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                while (true){
                    String next = scanner.nextLine();
                    if (next.equalsIgnoreCase("back")){
                        this.parentMenu.run();
                        break;
                    }
                }
            }
        };
    }
    private Menu removeFavorites(){
        return new Menu("Remove from favorites",this) {
            @Override
            public void show() {
                System.out.println("Enter back to last Menu");
            }

            @Override
            public void execute() {
                try {
                    playerGeneralController.RemoveFavoritesGames(username,"DotsAndBoxes");
                    System.out.println("DotsAndBoxes removed from Your Favorites Successfully!");
                } catch (ExistFavoriteException e) {
                    System.out.println(e.getGameName()+" : "+e.getMessage());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                } catch (InvalidGameNameException e) {
                    System.out.println(e.getMessage());
                }
                while (true){
                    String next = scanner.nextLine();
                    if (next.equalsIgnoreCase("back")){
                        this.parentMenu.run();
                        break;
                    }
                }
            }
        };
    }
    private Menu showPoints(){
        return new Menu("show "+this.username+"'s points in DotsAndBoxes",this) {
            @Override
            public void show() {
                System.out.println("Enter back to last Menu");
            }

            @Override
            public void execute() {
                try {
                    playerGeneralController.showPlayerPointsInThisGame(username,"DotsAndBoxes");
                } catch (InvalidGameNameException e) {
                    System.out.println(e.getGameName()+e.getMessage());
                    this.run();
                }
                while (true) {
                    String next = scanner.nextLine();
                    if (next.equalsIgnoreCase("back")) {
                        this.parentMenu.run();
                        break;
                    }
                }
            }
        };
    }
}
