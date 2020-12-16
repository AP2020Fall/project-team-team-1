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


    public String changeCoordinateProcessor(String username, String string) throws PlacedShipException, NewCoordinateForShipException, CorrectCoordinateForShipException, ExistOtherShipException, InvalidCommandException {
        String[] inputSpilt = string.split("\\s");

        if (inputSpilt[3].equalsIgnoreCase("direction")) {
            Direction direction = null;
            int ship = Integer.parseInt(inputSpilt[2]) - 1;


            if (BattleSeaPlayer.battleSeaPlayers.get(0).getPlayer().equals(username)) {

                int xForInput = run.player1.getPlayerShip().get(ship).getCoordinate().getxStart() + 1;
                int yForInput = run.player1.getPlayerShip().get(ship).getCoordinate().getyStart() + 1;
                return run.changeShipPlayer1Direction(xForInput, yForInput, Integer.parseInt(inputSpilt[2]), direction);
            } else if (BattleSeaPlayer.battleSeaPlayers.get(1).getPlayer().equals(username)) {

                int xForInput = run.player2.getPlayerShip().get(ship).getCoordinate().getxStart() + 1;
                int yForInput = run.player2.getPlayerShip().get(ship).getCoordinate().getyStart() + 1;
                return run.changeShipPlayer2Direction(xForInput, yForInput, Integer.parseInt(inputSpilt[2]), direction);
            }

        } else if (inputSpilt[3].equalsIgnoreCase("coordinate")) {
            String[] coordinate = inputSpilt[5].split(",");
//            int ship = Integer.parseInt(inputSpilt[2]) - 1;
//            int xForInput = Run.player1.getPlayerShip().get(ship).getCoordinate().getxStart() + 1;
//            int yForInput = Run.player1.getPlayerShip().get(ship).getCoordinate().getyStart() + 1;

            if (BattleSeaPlayer.battleSeaPlayers.get(0).getPlayer().equals(username))
                return run.changeShipPlayer1Coordinate(Integer.parseInt(coordinate[0]), Integer.parseInt(coordinate[1]), Integer.parseInt(inputSpilt[2]), Direction.VERTICAL);
            else if (BattleSeaPlayer.battleSeaPlayers.get(1).getPlayer().equals(username))
                return run.changeShipPlayer2Coordinate(Integer.parseInt(coordinate[0]), Integer.parseInt(coordinate[1]), Integer.parseInt(inputSpilt[2]), Direction.VERTICAL);


        } else {
            throw new InvalidCommandException("Invalid command");
        }

        return "";
    }


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

    public String showCommandProcessor(String username, String string) throws InvalidCommandException {
        StringBuilder output = new StringBuilder();
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
                output.append(counter).append(". X: ").append(xInput).append(" Y: ").append(yInput).append("$");
                counter++;
            }
        } else if (inputSpilt[2].equalsIgnoreCase("correct")) {
            for (String playerBoom : battleSeaPlayerFounder(inputSpilt[1]).getCorrectPlayerBooms()) {
                int xInput = Integer.parseInt(String.valueOf(playerBoom.charAt(0))) + 1;
                int yInput = Integer.parseInt(String.valueOf(playerBoom.charAt(2))) + 1;
                output.append(counter).append(". X: ").append(xInput).append(" Y: ").append(yInput).append("$");
                counter++;
            }
        } else if (inputSpilt[2].equalsIgnoreCase("incorrect")) {
            for (String playerBoom : battleSeaPlayerFounder(inputSpilt[1]).getInCorrectPlayerBooms()) {
                int xInput = Integer.parseInt(String.valueOf(playerBoom.charAt(0))) + 1;
                int yInput = Integer.parseInt(String.valueOf(playerBoom.charAt(2))) + 1;
                output.append(counter).append(".X: ").append(xInput).append(" Y: ").append(yInput).append("$");
                counter++;
            }
        } else if (inputSpilt[2].equalsIgnoreCase("boomed")) {
            for (Ship ship : battleSeaPlayerFounder(inputSpilt[1]).getBoomedPlayerShip()) {
                output.append(counter).append(". Boomed Ships: ").append(ship.getName()).append("$");
                counter++;
            }
        } else if (inputSpilt[2].equalsIgnoreCase("unboomed")) {
            for (Ship ship : battleSeaPlayerFounder(inputSpilt[1]).getUnBoomedPlayerShip()) {
                output.append(counter).append(". Un Boomed Ships: ").append(ship.getName()).append("$");
                counter++;
            }

        }        else {
            throw new InvalidCommandException("Invalid command");
        }
        return String.valueOf(output);

//        else if (inputSpilt[3].equalsIgnoreCase("plane")) {
//            if (BattleSeaPlayer.battleSeaPlayers.get(0).getPlayer().equals(inputSpilt[1]))
//                Board.displayBoard(run.game.getFirstPlayerEnemyBoard().getGameBoard());
//            else if (BattleSeaPlayer.battleSeaPlayers.get(1).getPlayer().equals(inputSpilt[1]))
//                Board.displayBoard(run.game.getSecondPlayerOwnBoard().getGameBoard());
//
//        }

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

    public String showPlayerEnemyBoard(String player) {
        return Run.showPlayerEnemyBoard(player);
    }

        public String randomShipPlaceForPlayer1() {
        return run.randomShipPlaceForPlayer1();
    }

    public String randomShipPlaceForPlayer2() {
        return run.randomShipPlaceForPlayer2();
    }

    public void addPlayersToArrayList() {
        run.addPlayer();
    }

    public void deletePlayer() {
        run.deletePlayer();
    }


}
