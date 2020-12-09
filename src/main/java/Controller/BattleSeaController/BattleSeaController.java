package Controller.BattleSeaController;

import Controller.Exception.BattleShip.*;
import Model.BattleSeaModel.BattleSeaPlayer;
import Model.BattleSeaModel.Board;
import Model.BattleSeaModel.Coordinate.Direction;
import Model.BattleSeaModel.Run;
import Model.BattleSeaModel.Ships.Ship;


public class BattleSeaController {
    Run run;

    public BattleSeaController() {
        this.run = new Run();
    }


    public void changeCoordinateProcessor(String username, String string) throws PlacedShipException, NewCoordinateForShipException, CorrectCoordinateForShipException, ExistOtherShipException, InvalidCommandException {
        String[] inputSpilt = string.split("\\s");

        if (inputSpilt[3].equalsIgnoreCase("direction")) {
            Direction direction = null;
            int ship = Integer.parseInt(inputSpilt[2]) - 1;


            if (BattleSeaPlayer.battleSeaPlayers.get(0).getPlayer().equals(username)) {

                int xForInput = run.player1.getPlayerShip().get(ship).getCoordinate().getxStart() + 1;
                int yForInput = run.player1.getPlayerShip().get(ship).getCoordinate().getyStart() + 1;
                run.changeShipPlayer1Direction(xForInput, yForInput, Integer.parseInt(inputSpilt[2]), direction);
            } else if (BattleSeaPlayer.battleSeaPlayers.get(1).getPlayer().equals(username)) {

                int xForInput = run.player2.getPlayerShip().get(ship).getCoordinate().getxStart() + 1;
                int yForInput = run.player2.getPlayerShip().get(ship).getCoordinate().getyStart() + 1;
                run.changeShipPlayer2Direction(xForInput, yForInput, Integer.parseInt(inputSpilt[2]), direction);
            } else
                System.out.println("check you Username");

        } else if (inputSpilt[3].equalsIgnoreCase("coordinate")) {
            String[] coordinate = inputSpilt[5].split(",");
//            int ship = Integer.parseInt(inputSpilt[2]) - 1;
//            int xForInput = Run.player1.getPlayerShip().get(ship).getCoordinate().getxStart() + 1;
//            int yForInput = Run.player1.getPlayerShip().get(ship).getCoordinate().getyStart() + 1;

            if (BattleSeaPlayer.battleSeaPlayers.get(0).getPlayer().equals(username))
                run.changeShipPlayer1Coordinate(Integer.parseInt(coordinate[0]), Integer.parseInt(coordinate[1]), Integer.parseInt(inputSpilt[2]), Direction.VERTICAL);
            else if (BattleSeaPlayer.battleSeaPlayers.get(1).getPlayer().equals(username))
                run.changeShipPlayer2Coordinate(Integer.parseInt(coordinate[0]), Integer.parseInt(coordinate[1]), Integer.parseInt(inputSpilt[2]), Direction.VERTICAL);
            else
                System.out.println("check you Username");

        } else {
            throw new InvalidCommandException("Invalid command");
        }


    }

//    public String boomOrShow(String username, String string) throws BattleShipWinner, InvalidCommandException {
//        String[] inputSplit = string.split("\\s");
//        if (inputSplit[0].equalsIgnoreCase("boom")) {
//            try {
//                return boomProcessor(username, string);
//            } catch (BoomCheckException e) {
//                System.out.println(e.getMessage());
//            }
//        } else if (inputSplit[0].equalsIgnoreCase("show")) {
//            showCommandProcessor(username, string);
//        } else {
//            throw new InvalidCommandException("Invalid command");
//        }
//        return " Enter Again ";
//    }

    public String boomProcessor(String username, String string) throws BattleShipWinner, BoomCheckException, InvalidCommandException, CorrectCoordinateForShipException {
        String[] inputSpilt = string.split("\\s");

        if (inputSpilt[0].equalsIgnoreCase("boom")) {
            String[] coordinate = inputSpilt[1].split(",");

            if (BattleSeaPlayer.battleSeaPlayers.get(0).getPlayer().equals(username))
                return run.boomPlayer2Ships(Integer.parseInt(coordinate[0]), Integer.parseInt(coordinate[1]));
            else if (BattleSeaPlayer.battleSeaPlayers.get(1).getPlayer().equals(username))
                return run.boomPlayer1Ships(Integer.parseInt(coordinate[0]), Integer.parseInt(coordinate[1]));

        } else {
            throw new InvalidCommandException("Invalid command");
        }

        return "";
    }

    public void showCommandProcessor(String username, String string) throws InvalidCommandException {
        String[] inputSpilt = string.split("\\s");
        int counter = 1;

        if (inputSpilt[2].equalsIgnoreCase("ships")) {

            if (inputSpilt[1].equalsIgnoreCase("player1")) {
                Board.displayBoard(run.game.getFirstPlayerOwnBoard().getGameBoard());
            } else
                Board.displayBoard(run.game.getSecondPlayerOwnBoard().getGameBoard());

        } else if (inputSpilt[2].equalsIgnoreCase("all")) {
            for (String playerBoom : battleSeaPlayerFounder(inputSpilt[1]).getPlayerBooms()) {
                int xInput = Integer.parseInt(String.valueOf(playerBoom.charAt(0))) + 1;
                int yInput = Integer.parseInt(String.valueOf(playerBoom.charAt(2))) + 1;
                System.out.println(counter + ". X: " + xInput + " Y: " + yInput);
                counter++;
            }
        } else if (inputSpilt[2].equalsIgnoreCase("correct")) {
            for (String playerBoom : battleSeaPlayerFounder(inputSpilt[1]).getCorrectPlayerBooms()) {
                int xInput = Integer.parseInt(String.valueOf(playerBoom.charAt(0))) + 1;
                int yInput = Integer.parseInt(String.valueOf(playerBoom.charAt(2))) + 1;
                System.out.println(counter + ". X: " + xInput + " Y: " + yInput);
                counter++;
            }
        } else if (inputSpilt[2].equalsIgnoreCase("incorrect")) {
            for (String playerBoom : battleSeaPlayerFounder(inputSpilt[1]).getInCorrectPlayerBooms()) {
                int xInput = Integer.parseInt(String.valueOf(playerBoom.charAt(0))) + 1;
                int yInput = Integer.parseInt(String.valueOf(playerBoom.charAt(2))) + 1;
                System.out.println(counter + ".X: " + xInput + " Y: " + yInput);
                counter++;
            }
        } else if (inputSpilt[2].equalsIgnoreCase("boomed")) {
            for (Ship ship : battleSeaPlayerFounder(inputSpilt[1]).getBoomedPlayerShip()) {
                System.out.println(counter + ". Boomed Ships: " + ship.getName());
                counter++;
            }
        } else if (inputSpilt[2].equalsIgnoreCase("unboomed")) {
            for (Ship ship : battleSeaPlayerFounder(inputSpilt[1]).getUnBoomedPlayerShip()) {
                System.out.println(counter + ". Un Boomed Ships: " + ship.getName());
            }

        } else if (inputSpilt[3].equalsIgnoreCase("plane")) {
            if (BattleSeaPlayer.battleSeaPlayers.get(0).getPlayer().equals(inputSpilt[1]))
                Board.displayBoard(run.game.getFirstPlayerEnemyBoard().getGameBoard());
            else if (BattleSeaPlayer.battleSeaPlayers.get(1).getPlayer().equals(inputSpilt[1]))
                Board.displayBoard(run.game.getSecondPlayerOwnBoard().getGameBoard());

        } else {
            throw new InvalidCommandException("Invalid command");
        }
    }

    protected BattleSeaPlayer battleSeaPlayerFounder(String string) {
        BattleSeaPlayer player = null;
        for (BattleSeaPlayer battleSeaPlayer : BattleSeaPlayer.battleSeaPlayers) {
            if (battleSeaPlayer.getPlayer().equals(string)) {
                player = battleSeaPlayer;
                break;
            }
        }
        return player;
    }

    public void randomShipPlaceForPlayer1() {
        run.randomShipPlaceForPlayer1();
    }

    public void randomShipPlaceForPlayer2() {
        run.randomShipPlaceForPlayer2();
    }

    public void addPlayersToArrayList() {
        run.addPlayer();
    }

    public void deletePlayer() {
        run.deletePlayer();
    }


}
