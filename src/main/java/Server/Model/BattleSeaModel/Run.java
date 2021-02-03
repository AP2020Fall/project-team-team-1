package Server.Model.BattleSeaModel;

import Server.Controller.Exception.BattleShip.*;
import Server.Model.BattleSeaModel.Coordinate.Coordinate;
import Server.Model.BattleSeaModel.Coordinate.Direction;
import Server.Model.BattleSeaModel.Ships.Ship;

import java.util.ArrayList;
import java.util.Random;


public class Run {

    public static BattleSeaPlayer player1 ;
    public static BattleSeaPlayer player2 ;
    public static GameBattleSea game ;

    public Run() {
        player1 = new BattleSeaPlayer("player1");
        player2 = new BattleSeaPlayer("player2");
        game = new GameBattleSea(player1, player2);
    }

    public static BattleSeaPlayer getPlayer1() {
        return player1;
    }

    public static void setPlayer1(BattleSeaPlayer player1) {
        Run.player1 = player1;
    }

    public static BattleSeaPlayer getPlayer2() {
        return player2;
    }

    public static void setPlayer2(BattleSeaPlayer player2) {
        Run.player2 = player2;
    }

    public static GameBattleSea getGame() {
        return game;
    }

    public static void setGame(GameBattleSea game) {
        Run.game = game;
    }

    public static void addPlayer() {
        BattleSeaPlayer.battleSeaPlayers.add(player1);
        BattleSeaPlayer.battleSeaPlayers.add(player2);
    }
    public static void deletePlayer() {
        BattleSeaPlayer.battleSeaPlayers.clear();
    }


    /*************************** Put For First Time ***************************/
    public static boolean putShipPlayer1(int x, int y, int ship, Direction direction) {
        ship--;
        x--;
        y--;

        if (player1.getPlayerShip().get(ship).isAlreadyPlaced()) {
            return false;
        }
        if (direction == Direction.HORIZONTAL) {
            if (x + player1.getPlayerShip().get(ship).getSize() > 9) {

                return false;
            }
            if (y > 9) {
                return false;
            }
            for (int i = x; i < x + player1.getPlayerShip().get(ship).getSize(); i++) {
                if (!game.getFirstPlayerOwnBoard().getGameBoard()[i][y].equals(" ")) {
                    return false;
                }
            }
            player1.getPlayerShip().get(ship).setCoordinate(new Coordinate(player1.getPlayerShip().get(ship).getName(), x, y, x + player1.getPlayerShip().get(ship).getSize() - 1, y, direction));
            for (int i = x; i < x + player1.getPlayerShip().get(ship).getSize(); i++) {
                game.getFirstPlayerOwnBoard().getGameBoard()[i][y] = String.valueOf(player1.getPlayerShip().get(ship).getName().charAt(0));
            }
            player1.getPlayerShip().get(ship).setAlreadyPlaced(true);
        } else {

            if (y + player1.getPlayerShip().get(ship).getSize() > 9) {
                return false;
            }
            if (x > 9) {
                return false;
            }
            for (int i = y; i < y + player1.getPlayerShip().get(ship).getSize(); i++) {
                if (!game.getFirstPlayerOwnBoard().getGameBoard()[x][i].equals(" ")) {
                    return false;
                }
            }
            player1.getPlayerShip().get(ship).setCoordinate(new Coordinate(player1.getPlayerShip().get(ship).getName(), x, y, x, y + player1.getPlayerShip().get(ship).getSize() - 1, direction));
            for (int i = y; i < y + player1.getPlayerShip().get(ship).getSize(); i++) {
                game.getFirstPlayerOwnBoard().getGameBoard()[x][i] = String.valueOf(player1.getPlayerShip().get(ship).getName().charAt(0));
            }
            player1.getPlayerShip().get(ship).setAlreadyPlaced(true);
        }
        return true;

    }

    public static boolean putShipPlayer2(int x, int y, int ship, Direction direction) {
        ship--;
        x--;
        y--;

        if (player2.getPlayerShip().get(ship).isAlreadyPlaced()) {
            return false;
        }
        if (direction == Direction.HORIZONTAL) {
            if (x + player2.getPlayerShip().get(ship).getSize() > 9) {
                return false;
            }
            if (y > 9) {
                return false;
            }
            for (int i = x; i < x + player2.getPlayerShip().get(ship).getSize(); i++) {
                if (!game.getSecondPlayerOwnBoard().getGameBoard()[i][y].equals(" ")) {
                    return false;
                }
            }
            player2.getPlayerShip().get(ship).setCoordinate(new Coordinate(player2.getPlayerShip().get(ship).getName(), x, y, x + player2.getPlayerShip().get(ship).getSize() - 1, y, direction));
            for (int i = x; i < x + player2.getPlayerShip().get(ship).getSize(); i++) {
                game.getSecondPlayerOwnBoard().getGameBoard()[i][y] = String.valueOf(player2.getPlayerShip().get(ship).getName().charAt(0));
            }
            player2.getPlayerShip().get(ship).setAlreadyPlaced(true);
        } else {

            if (y + player2.getPlayerShip().get(ship).getSize() > 9) {
                return false;
            }
            if (x > 9) {
                return false;
            }
            for (int i = y; i < y + player2.getPlayerShip().get(ship).getSize(); i++) {
                if (!game.getSecondPlayerOwnBoard().getGameBoard()[x][i].equals(" ")) {
                    return false;
                }
            }
            player2.getPlayerShip().get(ship).setCoordinate(new Coordinate(player2.getPlayerShip().get(ship).getName(), x, y, x, y + player2.getPlayerShip().get(ship).getSize() - 1, direction));
            for (int i = y; i < y + player2.getPlayerShip().get(ship).getSize(); i++) {
                game.getSecondPlayerOwnBoard().getGameBoard()[x][i] = String.valueOf(player2.getPlayerShip().get(ship).getName().charAt(0));
            }
            player2.getPlayerShip().get(ship).setAlreadyPlaced(true);
        }
        return true;

    }


    /*************************** Random Pick Ships ***************************/

    public static String randomShipPlaceForPlayer1() {
        for (int i = 1; i <= 5; i++) {
            while (true) {
                int x = randomMessageId(1, 10);
                int y = randomMessageId(1, 10);
                Direction direction;
                if ((x + y) % 2 == 1) {
                    direction = Direction.HORIZONTAL;
                } else
                    direction = Direction.VERTICAL;

                if (Run.putShipPlayer1(x, y, i, direction))
                    break;

            }

        }
        return Board.displayBoard(game.getFirstPlayerOwnBoard().getGameBoard());

    }

    public static String randomShipPlaceForPlayer2() {
        for (int i = 1; i <= 5; i++) {
            while (true) {
                int x = randomMessageId(1, 10);
                int y = randomMessageId(1, 10);
                Direction direction;
                if ((x + y) % 2 == 1) {
                    direction = Direction.HORIZONTAL;
                } else
                    direction = Direction.VERTICAL;

                if (Run.putShipPlayer2(x, y, i, direction))
                    break;

            }

        }
        return Board.displayBoard(game.getSecondPlayerOwnBoard().getGameBoard());
    }

    /************************** Edit Ship Corrdinate **************************/
    public static String changeShipPlayer1Coordinate(int x, int y, int ship, Direction direction) throws PlacedShipException, NewCoordinateForShipException, CorrectCoordinateForShipException, ExistOtherShipException {
        ship--;
        x--;
        y--;

        if (!player1.getPlayerShip().get(ship).isAlreadyPlaced()) {
            throw new PlacedShipException("You should place ship first");
        }

        if (player1.getPlayerShip().get(ship).getCoordinate().getDirection() == direction) {
            if (player1.getPlayerShip().get(ship).getCoordinate().getxStart() == x && player1.getPlayerShip().get(ship).getCoordinate().getyStart() == y) {
                throw new NewCoordinateForShipException("Please enter new coordinate for your ship");
            }
        }
        if (player1.getPlayerShip().get(ship).getCoordinate().getDirection() == Direction.HORIZONTAL) {
            direction = Direction.HORIZONTAL;
            if (x + player1.getPlayerShip().get(ship).getSize() > 9) {
                throw new CorrectCoordinateForShipException("Coordinates must be inside the table");
            }
            if (y > 9) {
                throw new CorrectCoordinateForShipException("Coordinates must be inside the table");
            }
            for (int i = x; i < x + player1.getPlayerShip().get(ship).getSize(); i++) {
                if (!game.getFirstPlayerOwnBoard().getGameBoard()[i][y].equals(" ")) {
                    throw new ExistOtherShipException("You have other ship in this coordinate, Try again");
                }
            }

            for (int i = player1.getPlayerShip().get(ship).getCoordinate().getxStart(); i <= player1.getPlayerShip().get(ship).getCoordinate().getxLast(); i++) {
                game.getFirstPlayerOwnBoard().getGameBoard()[i][player1.getPlayerShip().get(ship).getCoordinate().getyStart()] = " ";
            }
        } else {

            direction = Direction.VERTICAL;

            if (y + player1.getPlayerShip().get(ship).getSize() > 9) {
                throw new CorrectCoordinateForShipException("Coordinates must be inside the table");
            }
            if (x > 9) {
                throw new CorrectCoordinateForShipException("Coordinates must be inside the table");
            }
            for (int i = y; i < y + player1.getPlayerShip().get(ship).getSize(); i++) {
                if (!game.getFirstPlayerOwnBoard().getGameBoard()[x][i].equals(" ")) {
                    throw new ExistOtherShipException("You have other ship in this coordinate, Try again");
                }
            }

            for (int i = player1.getPlayerShip().get(ship).getCoordinate().getyStart(); i <= player1.getPlayerShip().get(ship).getCoordinate().getyLast(); i++) {
                game.getFirstPlayerOwnBoard().getGameBoard()[player1.getPlayerShip().get(ship).getCoordinate().getxStart()][i] = " ";

            }
        }

        player1.getPlayerShip().get(ship).setAlreadyPlaced(false);
        player1.getPlayerShip().get(ship).setCoordinate(null);
        ship++;
        y++;
        x++;

        putShipPlayer1(x, y, ship, direction);

        return Board.displayBoard(game.getFirstPlayerOwnBoard().getGameBoard());
    }

    public static String changeShipPlayer2Coordinate(int x, int y, int ship, Direction direction) throws PlacedShipException, NewCoordinateForShipException, CorrectCoordinateForShipException, ExistOtherShipException {
        ship--;
        x--;
        y--;

        if (!player2.getPlayerShip().get(ship).isAlreadyPlaced()) {
            throw new PlacedShipException("You should place ship first");
        }

        if (player2.getPlayerShip().get(ship).getCoordinate().getDirection() == direction) {
            if (player2.getPlayerShip().get(ship).getCoordinate().getxStart() == x && player2.getPlayerShip().get(ship).getCoordinate().getyStart() == y) {
                throw new NewCoordinateForShipException("Please enter new coordinate for your ship");
            }
        }
        if (player2.getPlayerShip().get(ship).getCoordinate().getDirection() == Direction.HORIZONTAL) {
            direction = Direction.HORIZONTAL;
            if (x + player2.getPlayerShip().get(ship).getSize() > 9) {
                throw new CorrectCoordinateForShipException("Coordinates must be inside the table");
            }
            if (y > 9) {
                throw new CorrectCoordinateForShipException("Coordinates must be inside the table");
            }
            for (int i = x; i < x + player2.getPlayerShip().get(ship).getSize(); i++) {
                if (!game.getSecondPlayerOwnBoard().getGameBoard()[i][y].equals(" ")) {
                    throw new ExistOtherShipException("You have other ship in this coordinate, Try again");
                }
            }

            for (int i = player2.getPlayerShip().get(ship).getCoordinate().getxStart(); i <= player2.getPlayerShip().get(ship).getCoordinate().getxLast(); i++) {
                game.getSecondPlayerOwnBoard().getGameBoard()[i][player2.getPlayerShip().get(ship).getCoordinate().getyStart()] = " ";
            }
        } else {

            direction = Direction.VERTICAL;

            if (y + player2.getPlayerShip().get(ship).getSize() > 9) {
                throw new CorrectCoordinateForShipException("Coordinates must be inside the table");
            }
            if (x > 9) {
                throw new CorrectCoordinateForShipException("Coordinates must be inside the table");
            }
            for (int i = y; i < y + player2.getPlayerShip().get(ship).getSize(); i++) {
                if (!game.getSecondPlayerOwnBoard().getGameBoard()[x][i].equals(" ")) {
                    throw new ExistOtherShipException("You have other ship in this coordinate, Try again");
                }
            }

            for (int i = player2.getPlayerShip().get(ship).getCoordinate().getyStart(); i <= player2.getPlayerShip().get(ship).getCoordinate().getyLast(); i++) {
                game.getSecondPlayerOwnBoard().getGameBoard()[player2.getPlayerShip().get(ship).getCoordinate().getxStart()][i] = " ";

            }
        }

        player2.getPlayerShip().get(ship).setAlreadyPlaced(false);
        player2.getPlayerShip().get(ship).setCoordinate(null);
        ship++;
        y++;
        x++;

        putShipPlayer2(x, y, ship, direction);

        return Board.displayBoard(game.getSecondPlayerOwnBoard().getGameBoard());

    }

    /************************** Edit Ship Direction **************************/

    public static String changeShipPlayer1Direction(int x, int y, int ship, Direction direction) throws PlacedShipException, CorrectCoordinateForShipException, ExistOtherShipException {
        ship--;
        x--;
        y--;

        if (!player1.getPlayerShip().get(ship).isAlreadyPlaced()) {
            throw new PlacedShipException("You should place ship first");
        }


        if (player1.getPlayerShip().get(ship).getCoordinate().getDirection() == Direction.HORIZONTAL) {
            direction = Direction.VERTICAL;
            if (x + player1.getPlayerShip().get(ship).getSize() > 9) {
                throw new CorrectCoordinateForShipException("Coordinates must be inside the table");
            }
            if (y > 9) {
                throw new CorrectCoordinateForShipException("Coordinates must be inside the table");
            }

            for (int i = y + 1; i < y + player1.getPlayerShip().get(ship).getSize(); i++) {
                if (!game.getFirstPlayerOwnBoard().getGameBoard()[x][i].equals(" ")) {
                    throw new ExistOtherShipException("You have other ship in this coordinate, Try again");
                }
            }

            for (int i = player1.getPlayerShip().get(ship).getCoordinate().getxStart(); i <= player1.getPlayerShip().get(ship).getCoordinate().getxLast(); i++) {
                game.getFirstPlayerOwnBoard().getGameBoard()[i][player1.getPlayerShip().get(ship).getCoordinate().getyStart()] = " ";
            }
        } else {
            direction = Direction.HORIZONTAL;

            if (y + player1.getPlayerShip().get(ship).getSize() > 9) {
                throw new CorrectCoordinateForShipException("Coordinates must be inside the table");
            }
            if (x > 9) {
                throw new CorrectCoordinateForShipException("Coordinates must be inside the table");
            }

            for (int i = x + 1; i < x + player1.getPlayerShip().get(ship).getSize(); i++) {
                if (!game.getFirstPlayerOwnBoard().getGameBoard()[i][y].equals(" ")) {
                    throw new ExistOtherShipException("You have other ship in this coordinate, Try again");
                }
            }
            for (int i = player1.getPlayerShip().get(ship).getCoordinate().getyStart(); i <= player1.getPlayerShip().get(ship).getCoordinate().getyLast(); i++) {
                game.getFirstPlayerOwnBoard().getGameBoard()[player1.getPlayerShip().get(ship).getCoordinate().getxStart()][i] = " ";

            }
        }

        player1.getPlayerShip().get(ship).setAlreadyPlaced(false);
        player1.getPlayerShip().get(ship).setCoordinate(null);
        ship++;
        y++;
        x++;


        putShipPlayer1(x, y, ship, direction);

       return Board.displayBoard(game.getFirstPlayerOwnBoard().getGameBoard());

    }

    public static String changeShipPlayer2Direction(int x, int y, int ship, Direction direction) throws ExistOtherShipException, CorrectCoordinateForShipException, PlacedShipException {
        ship--;
        x--;
        y--;

        if (!player2.getPlayerShip().get(ship).isAlreadyPlaced()) {
            throw new PlacedShipException("You should place ship first");
        }


        if (player2.getPlayerShip().get(ship).getCoordinate().getDirection() == Direction.HORIZONTAL) {
            direction = Direction.VERTICAL;
            if (x + player2.getPlayerShip().get(ship).getSize() > 9) {
                throw new CorrectCoordinateForShipException("Coordinates must be inside the table");
            }
            if (y > 9) {
                throw new CorrectCoordinateForShipException("Coordinates must be inside the table");
            }

            for (int i = y + 1; i < y + player2.getPlayerShip().get(ship).getSize(); i++) {
                if (!game.getSecondPlayerOwnBoard().getGameBoard()[x][i].equals(" ")) {
                    throw new ExistOtherShipException("You have other ship in this coordinate, Try again");
                }
            }

            for (int i = player2.getPlayerShip().get(ship).getCoordinate().getxStart(); i <= player2.getPlayerShip().get(ship).getCoordinate().getxLast(); i++) {
                game.getSecondPlayerOwnBoard().getGameBoard()[i][player2.getPlayerShip().get(ship).getCoordinate().getyStart()] = " ";
            }
        } else {
            direction = Direction.HORIZONTAL;

            if (y + player2.getPlayerShip().get(ship).getSize() > 9) {
                throw new CorrectCoordinateForShipException("Coordinates must be inside the table");
            }
            if (x > 9) {
                throw new CorrectCoordinateForShipException("Coordinates must be inside the table");
            }

            for (int i = x + 1; i < x + player2.getPlayerShip().get(ship).getSize(); i++) {
                if (!game.getSecondPlayerOwnBoard().getGameBoard()[i][y].equals(" ")) {
                    throw new ExistOtherShipException("You have other ship in this coordinate, Try again");
                }
            }
            for (int i = player2.getPlayerShip().get(ship).getCoordinate().getyStart(); i <= player2.getPlayerShip().get(ship).getCoordinate().getyLast(); i++) {
                game.getSecondPlayerOwnBoard().getGameBoard()[player2.getPlayerShip().get(ship).getCoordinate().getxStart()][i] = " ";

            }
        }

        player2.getPlayerShip().get(ship).setAlreadyPlaced(false);
        player2.getPlayerShip().get(ship).setCoordinate(null);
        ship++;
        y++;
        x++;

        putShipPlayer2(x, y, ship, direction);

        return Board.displayBoard(game.getSecondPlayerOwnBoard().getGameBoard());

    }

    /*************************** Boom Player Ships ***************************/
    public static String boomPlayer1Ships(int x, int y) throws BattleShipWinner, BoomCheckException, CorrectCoordinateForShipException {
        x--;
        y--;
        if (player2.getPlayerBooms().contains(x + " " + y)) {
            int xInput = x+1;
            int yInput = y+1;
            throw new BoomCheckException("Select "+ xInput + "," + yInput + " has been already boomed");
        }
        player2.getPlayerBooms().add(x + " " + y);
        if (x>9 || y>9){
            if (x<=0 || y<=0){
                throw new CorrectCoordinateForShipException("Coordinates must be inside the table");
            }
        }


        char namee = game.getFirstPlayerOwnBoard().getGameBoard()[x][y].charAt(0);

        if (namee == ' ') {
            game.getSecondPlayerEnemyBoard().getGameBoard()[x][y] = "-";
            game.getFirstPlayerOwnBoard().getGameBoard()[x][y] = "-";
            Board.displayBoard(game.getSecondPlayerEnemyBoard().getGameBoard());
            player2.getInCorrectPlayerBooms().add(x + " " + y);
            return "InCorrect Boom";
        }

        Ship ship = null;
        for (Ship ships : player1.getPlayerShip()) {
            if (ships.getName().charAt(0) == namee) {
                ship = ships;
                break;
            }
        }
        if (ship != null) {
            game.getSecondPlayerEnemyBoard().getGameBoard()[x][y] = "+";
            game.getFirstPlayerOwnBoard().getGameBoard()[x][y] = "+";
            player2.getCorrectPlayerBooms().add(x + " " + y);
            ship.setSize(ship.getSize() - 1);
        } else
            return "Cant find the Ship 404";

        if (ship.getSize() == 0) {
            for (int i = ship.getCoordinate().getxStart(); i <= ship.getCoordinate().getxLast(); i++) {
                for (int j = ship.getCoordinate().getyStart(); j <= ship.getCoordinate().getyLast(); j++) {
                    game.getSecondPlayerEnemyBoard().getGameBoard()[i][j] = "*";
                    game.getFirstPlayerOwnBoard().getGameBoard()[i][j] = "*";
                    player1.getBoomedPlayerShip().add(ship);
                    player1.getUnBoomedPlayerShip().remove(ship);
                }

            }
        }

        if (winPlayer2Checker())
            throw new BattleShipWinner(player2.getPlayer());

        return "Correct Boom";
    }

    public static String boomPlayer2Ships(int x, int y) throws BattleShipWinner, BoomCheckException, CorrectCoordinateForShipException {
        x--;
        y--;
        if (player1.getPlayerBooms().contains(x + " " + y)) {
            int xInput = x+1;
            int yInput = y+1;
            throw new BoomCheckException("Select "+ xInput + "," + yInput + " has been already boomed");
        }
        player1.getPlayerBooms().add(x + " " + y);
        if (x>9 || y>9){
            if (x<=0 || y<=0){
                throw new CorrectCoordinateForShipException("Coordinates must be inside the table");
            }
        }

        char namee = game.getSecondPlayerOwnBoard().getGameBoard()[x][y].charAt(0);

        if (namee == ' ') {
            game.getFirstPlayerEnemyBoard().getGameBoard()[x][y] = "-";
            game.getSecondPlayerOwnBoard().getGameBoard()[x][y] = "-";
            Board.displayBoard(game.getFirstPlayerEnemyBoard().getGameBoard());
            player1.getInCorrectPlayerBooms().add(x + " " + y);
            return "InCorrect Boom";
        }

        Ship ship = null;
        for (Ship ships : player2.getPlayerShip()) {
            if (ships.getName().charAt(0) == namee) {
                ship = ships;
                break;
            }
        }
        if (ship != null) {
            game.getFirstPlayerEnemyBoard().getGameBoard()[x][y] = "+";
            game.getSecondPlayerOwnBoard().getGameBoard()[x][y] = "+";
            player1.getCorrectPlayerBooms().add(x + " " + y);
            ship.setSize(ship.getSize() - 1);
        } else
            return "Cant find the Ship 404";

        if (ship.getSize() == 0) {
            for (int i = ship.getCoordinate().getxStart(); i <= ship.getCoordinate().getxLast(); i++) {
                for (int j = ship.getCoordinate().getyStart(); j <= ship.getCoordinate().getyLast(); j++) {
                    game.getFirstPlayerEnemyBoard().getGameBoard()[i][j] = "*";
                    game.getSecondPlayerOwnBoard().getGameBoard()[i][j] = "*";
                    player2.getBoomedPlayerShip().add(ship);
                    player2.getUnBoomedPlayerShip().remove(ship);
                }

            }
        }
        if (winPlayer1Checker())
            throw new BattleShipWinner(player1.getPlayer());

        return "Correct Boom";
    }



    /**************************** Winner Checker *****************************/
    protected static boolean winPlayer1Checker() {
        for (Ship ship : player2.getPlayerShip()) {
            if (ship.getSize() != 0) {
                return false;
            }
        }
        return true;
    }

    protected static boolean winPlayer2Checker() {
        for (Ship ship : player1.getPlayerShip()) {
            if (ship.getSize() != 0) {
                return false;
            }
        }
        return true;
    }

    public static String showPlayerEnemyBoard(String player){
        if (player.equalsIgnoreCase("player2")){
            return Board.displayBoard(game.getFirstPlayerEnemyBoard().getGameBoard());
        }else
            return Board.displayBoard(game.getSecondPlayerEnemyBoard().getGameBoard());

    }

    public static void showHel(){
        System.out.println(Board.displayBoard(game.getFirstPlayerOwnBoard().getGameBoard()));
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(Board.displayBoard(game.getSecondPlayerOwnBoard().getGameBoard()));
    }

    private static int randomMessageId(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public ArrayList<String> getPlayer1CorrectBooms() {
        return player1.getCorrectPlayerBooms();
    }

    public ArrayList<String> getPlayer1InCorrectBooms() {
        return player1.getInCorrectPlayerBooms();
    }

    public ArrayList<String> getPlayer2CorrectBooms() {
        return player2.getCorrectPlayerBooms();
    }

    public ArrayList<String> getPlayer2InCorrectBooms() {
        return player2.getInCorrectPlayerBooms();
    }
}
