package Controller.BattleSeaController;

import Model.BattleSeaModel.Board;
import Model.BattleSeaModel.Coordinate.Direction;
import Model.BattleSeaModel.Ships.Ship;
import Controller.Exception.BattleShipWinner;


public class BattleSeaController {

    public void changeCoordinateProcessor(String username, String string) {
        String[] inputSpilt = string.split("\\s");

        if (inputSpilt[3].equalsIgnoreCase("direction")) {
            Direction direction = null;
            int ship = Integer.parseInt(inputSpilt[2]) - 1;


            if (BattleSeaPlayer.battleSeaPlayers.get(0).getPlayer().equals(username)) {
//                if (Run.player1.getPlayerShip().get(ship).getCoordinate().getDirection() == Direction.HORIZONTAL) {
//                    direction = Direction.VERTICAL;
//                } else {
//                    direction = Direction.HORIZONTAL;
//                }

                //Run.changeShipPlayer1Coordinate(xForInput, yForInput, Integer.parseInt(inputSpilt[2]) , direction);
                int xForInput = Run.player1.getPlayerShip().get(ship).getCoordinate().getxStart() + 1;
                int yForInput = Run.player1.getPlayerShip().get(ship).getCoordinate().getyStart() + 1;
                Run.changeShipPlayer1Direction(xForInput, yForInput, Integer.parseInt(inputSpilt[2]), direction);
            } else if (BattleSeaPlayer.battleSeaPlayers.get(1).getPlayer().equals(username)) {
//                if (Run.player2.getPlayerShip().get(ship).getCoordinate().getDirection().equals(Direction.HORIZONTAL)) {
//                    direction = Direction.VERTICAL;
//                } else {
//                    direction = Direction.HORIZONTAL;
//                }
                //Run.changeShipPlayer1Coordinate(Run.player2.getPlayerShip().get(Integer.parseInt(inputSpilt[2])).getCoordinate().getxStart(), Run.player2.getPlayerShip().get(Integer.parseInt(inputSpilt[2])).getCoordinate().getyStart(), Integer.parseInt(inputSpilt[2]), direction);
                int xForInput = Run.player2.getPlayerShip().get(ship).getCoordinate().getxStart() + 1;
                int yForInput = Run.player2.getPlayerShip().get(ship).getCoordinate().getyStart() + 1;
                Run.changeShipPlayer2Direction(xForInput, yForInput, Integer.parseInt(inputSpilt[2]), direction);
            } else
                System.out.println("check you Username");

        } else if (inputSpilt[3].equalsIgnoreCase("coordinate")) {
            String[] coordinate = inputSpilt[5].split(",");
//            int ship = Integer.parseInt(inputSpilt[2]) - 1;
//            int xForInput = Run.player1.getPlayerShip().get(ship).getCoordinate().getxStart() + 1;
//            int yForInput = Run.player1.getPlayerShip().get(ship).getCoordinate().getyStart() + 1;

            if (BattleSeaPlayer.battleSeaPlayers.get(0).getPlayer().equals(username))
                Run.changeShipPlayer1Coordinate(Integer.parseInt(coordinate[0]), Integer.parseInt(coordinate[1]), Integer.parseInt(inputSpilt[2]), Direction.VERTICAL);
            else if (BattleSeaPlayer.battleSeaPlayers.get(1).getPlayer().equals(username))
                Run.changeShipPlayer2Coordinate(Integer.parseInt(coordinate[0]), Integer.parseInt(coordinate[1]), Integer.parseInt(inputSpilt[2]), Direction.VERTICAL);
            else
                System.out.println("check you Username");

        } else {
            //todo Exception
        }


    }

    public void boomOrShow(String username, String string) throws BattleShipWinner {
        String[] inputSplit = string.split("\\s");
        if (inputSplit[0].equalsIgnoreCase("boom")){
            boomProcessor(username,string);
        }else if (inputSplit[0].equalsIgnoreCase("show")){
            showCommandProcessor(username, string);
        }else {
            //todo Exception
        }
    }

    public void boomProcessor(String username, String string) throws BattleShipWinner {
        String[] inputSpilt = string.split("\\s");

        if (inputSpilt[0].equalsIgnoreCase("boom")) {
            String[] coordinate = inputSpilt[1].split(",");

            if (BattleSeaPlayer.battleSeaPlayers.get(0).getPlayer().equals(username))
                Run.boomPlayer2Ships(Integer.parseInt(coordinate[0]), Integer.parseInt(coordinate[1]));
            else if (BattleSeaPlayer.battleSeaPlayers.get(1).getPlayer().equals(username))
                Run.boomPlayer1Ships(Integer.parseInt(coordinate[0]), Integer.parseInt(coordinate[1]));

        } else {
            //todo Exception
        }


    }

    public void showCommandProcessor(String username, String string) {
        String[] inputSpilt = string.split("\\s");
        int counter = 1;

        if (inputSpilt[2].equalsIgnoreCase("ships")) {

            if (inputSpilt[1].equalsIgnoreCase("player1")){
                Board.displayBoard(Run.game.getFirstPlayerOwnBoard().getGameBoard());
            }else
                Board.displayBoard(Run.game.getSecondPlayerOwnBoard().getGameBoard());

        } else if (inputSpilt[2].equalsIgnoreCase("all")) {
            for (String playerBoom : battleSeaPlayerFounder(inputSpilt[1]).getPlayerBooms()) {
                int xInput = Integer.parseInt(String.valueOf(playerBoom.charAt(0)))+1;
                int yInput = Integer.parseInt(String.valueOf(playerBoom.charAt(2)))+1;
                System.out.println(counter + ". X: " +  xInput + " Y: " + yInput);
                counter++;
            }
        } else if (inputSpilt[2].equalsIgnoreCase("correct")) {
            for (String playerBoom : battleSeaPlayerFounder(inputSpilt[1]).getCorrectPlayerBooms()) {
                int xInput = Integer.parseInt(String.valueOf(playerBoom.charAt(0)))+1;
                int yInput = Integer.parseInt(String.valueOf(playerBoom.charAt(2)))+1;
                System.out.println(counter + ". X: " + xInput + " Y: " + yInput);
                counter++;
            }
        } else if (inputSpilt[2].equalsIgnoreCase("incorrect")) {
            for (String playerBoom : battleSeaPlayerFounder(inputSpilt[1]).getInCorrectPlayerBooms()) {
                int xInput = Integer.parseInt(String.valueOf(playerBoom.charAt(0)))+1;
                int yInput = Integer.parseInt(String.valueOf(playerBoom.charAt(2)))+1;
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
                Board.displayBoard(Run.game.getFirstPlayerEnemyBoard().getGameBoard());
            else if (BattleSeaPlayer.battleSeaPlayers.get(1).getPlayer().equals(inputSpilt[1]))
                Board.displayBoard(Run.game.getSecondPlayerOwnBoard().getGameBoard());

        } else {
            //todo Exception
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
        Run.randomShipPlaceForPlayer1();
    }

    public void randomShipPlaceForPlayer2() {
        Run.randomShipPlaceForPlayer2();
    }

    public void addPlayersToArrayList() {
        Run.addPlayer();
    }

    public void restPlayer1Board() {

    }

    public void restPlayer2Board() {

    }

    public void details() {

    }
    public void deletePlayer() {
        Run.deletePlayer();
    }

    public String tellMeWhenTheGameIsOver() {
        if (false) {
            return "NotYet";
        } else return "Over";
    }
    //todo method boolean

}
