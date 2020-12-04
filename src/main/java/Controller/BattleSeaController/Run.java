package Controller.BattleSeaController;

import Controller.BattleSeaController.Coordinate.Coordinate;
import Controller.BattleSeaController.Coordinate.Direction;
import Controller.BattleSeaController.Ships.Ship;

import java.util.Random;


public class Run {
    public static BattleSeaPlayer player1 = new BattleSeaPlayer("player1");
    public static BattleSeaPlayer player2 = new BattleSeaPlayer("player2");
    public static GameBattleSea game = new GameBattleSea(player1, player2);

    public static void addPlayer(){
        BattleSeaPlayer.battleSeaPlayers.add(player1);
        BattleSeaPlayer.battleSeaPlayers.add(player2);
    }


    /*************************** Put For First Time ***************************/
    public static boolean putShipPlayer1(int x, int y, int ship, Direction direction) {
        boolean pass = true;
        ship--;
        x--;
        y--;

        if (player1.getPlayerShip().get(ship).isAlreadyPlaced()) {
            System.out.println("already Placed");
            return false;
        }
        if (direction == Direction.HORIZONTAL) {
            if (x + player1.getPlayerShip().get(ship).getSize() > 9) {
//                System.out.println("check your" + x);
                return false;
            }
            if (y > 9) {
//                System.out.println("check your" + y);
                return false;
            }
            for (int i = x; i < x + player1.getPlayerShip().get(ship).getSize(); i++) {
                if (!game.getFirstPlayerOwnBoard().getGameBoard()[i][y].equals("E")) {
//                    System.out.println("Wrong");
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
//                System.out.println("check your" + y);
                return false;
            }
            if (x > 9) {
//                System.out.println("check your" + x);
                return false;
            }
            for (int i = y; i < y + player1.getPlayerShip().get(ship).getSize(); i++) {
                if (!game.getFirstPlayerOwnBoard().getGameBoard()[x][i].equals("E")) {
//                    pass = false;
//                    System.out.println("Wrong");
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
        boolean pass = true;
        ship--;
        x--;
        y--;

        if (player2.getPlayerShip().get(ship).isAlreadyPlaced()) {
            System.out.println("already Placed");
            return false;
        }
        if (direction == Direction.HORIZONTAL) {
            if (x + player2.getPlayerShip().get(ship).getSize() > 9) {
//                System.out.println("check your" + x);
                return false;
            }
            if (y > 9) {
//                System.out.println("check your" + y);
                return false;
            }
            for (int i = x; i < x + player2.getPlayerShip().get(ship).getSize(); i++) {
                if (!game.getSecondPlayerOwnBoard().getGameBoard()[i][y].equals("E")) {
//                    pass = false;
//                    System.out.println("Wrong");
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
//                System.out.println("check your" + y);
                return false;
            }
            if (x > 9) {
//                System.out.println("check your" + x);
                return false;
            }
            for (int i = y; i < y + player2.getPlayerShip().get(ship).getSize(); i++) {
                if (!game.getSecondPlayerOwnBoard().getGameBoard()[x][i].equals("E")) {
//                    pass = false;
//                    System.out.println("Wrong");
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

    public static void randomShipPlaceForPlayer1() {
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
        Board.displayBoard(game.getFirstPlayerOwnBoard().getGameBoard());
        System.out.println("+++++++++++++++++++++++++++++");
    }

    public static void randomShipPlaceForPlayer2() {
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
        Board.displayBoard(game.getSecondPlayerOwnBoard().getGameBoard());
        System.out.println("+++++++++++++++++++++++++++++");
    }

    /************************** Edit Ship Corrdinate **************************/
    public static void changeShipPlayer1Coordinate(int x, int y, int ship, Direction direction) {
        ship--;
        x--;
        y--;

        if (!player1.getPlayerShip().get(ship).isAlreadyPlaced()) {
            System.out.println("you should place ship first");
            return;
        }
        if (player1.getPlayerShip().get(ship).getCoordinate().getxStart() == x && player1.getPlayerShip().get(ship).getCoordinate().getyStart() == y && player1.getPlayerShip().get(ship).getCoordinate().getDirection() == direction) {
            System.out.println("pls enter new Coordinate its same as your last Coordinate");
            return;
        }
        if (player1.getPlayerShip().get(ship).getCoordinate().getDirection() == Direction.HORIZONTAL) {
            if (x + player1.getPlayerShip().get(ship).getSize() > 9) {
                System.out.println("check your" + x);
                return;
            }
            if (y > 9) {
                System.out.println("check your" + y);
                return;
            }
            for (int i = x; i < x + player1.getPlayerShip().get(ship).getSize(); i++) {
                if (!game.getFirstPlayerOwnBoard().getGameBoard()[i][y].equals("E")) {
                    System.out.println("Wrong");
                    return;
                }
            }
            for (int i = player1.getPlayerShip().get(ship).getCoordinate().getxStart(); i < player1.getPlayerShip().get(ship).getCoordinate().getxStart() + player1.getPlayerShip().get(ship).getSize(); i++) {
                game.getFirstPlayerOwnBoard().getGameBoard()[i][y] = "E";
            }
        } else {

            if (y + player1.getPlayerShip().get(ship).getSize() > 9) {
                System.out.println("check your" + y);
                return;
            }
            if (x > 9) {
                System.out.println("check your" + x);
                return;
            }
            for (int i = y; i < y + player1.getPlayerShip().get(ship).getSize(); i++) {
                if (!game.getFirstPlayerOwnBoard().getGameBoard()[x][i].equals("E")) {
                    System.out.println("Wrong");
                    return;
                }
            }

            for (int i = player1.getPlayerShip().get(ship).getCoordinate().getyStart(); i < player1.getPlayerShip().get(ship).getCoordinate().getyStart() + player1.getPlayerShip().get(ship).getSize(); i++) {
                game.getFirstPlayerOwnBoard().getGameBoard()[x][i] = "E";
            }
        }
        Board.displayBoard(game.getFirstPlayerOwnBoard().getGameBoard());
        player1.getPlayerShip().get(ship).setAlreadyPlaced(false);
        player1.getPlayerShip().get(ship).setCoordinate(null);
        ship++;
        y++;
        x++;
        putShipPlayer1(x, y, ship, direction);


    }

    public static void changeShipPlayer2Coordinate(int x, int y, int ship, Direction direction) {
        ship--;
        x--;
        y--;

        if (!player2.getPlayerShip().get(ship).isAlreadyPlaced()) {
            System.out.println("you should place ship first");
            return;
        }
        if (player2.getPlayerShip().get(ship).getCoordinate().getxStart() == x && player2.getPlayerShip().get(ship).getCoordinate().getyStart() == y && player2.getPlayerShip().get(ship).getCoordinate().getDirection() == direction) {
            System.out.println("pls enter new Coordinate its same as your last Coordinate");
            return;
        }
        if (player2.getPlayerShip().get(ship).getCoordinate().getDirection() == Direction.HORIZONTAL) {
            if (x + player2.getPlayerShip().get(ship).getSize() > 9) {
                System.out.println("check your" + x);
                return;
            }
            if (y > 9) {
                System.out.println("check your" + y);
                return;
            }
            for (int i = x; i < x + player2.getPlayerShip().get(ship).getSize(); i++) {
                if (!game.getSecondPlayerOwnBoard().getGameBoard()[i][y].equals("E")) {
                    System.out.println("Wrong");
                    return;
                }
            }

            for (int i = player2.getPlayerShip().get(ship).getCoordinate().getxStart(); i < player2.getPlayerShip().get(ship).getCoordinate().getxStart() + player2.getPlayerShip().get(ship).getSize(); i++) {
                game.getFirstPlayerOwnBoard().getGameBoard()[i][y] = "E";
            }
        } else {

            if (y + player2.getPlayerShip().get(ship).getSize() > 9) {
                System.out.println("check your" + y);
                return;
            }
            if (x > 9) {
                System.out.println("check your" + x);
                return;
            }
            for (int i = y; i < y + player2.getPlayerShip().get(ship).getSize(); i++) {
                if (!game.getSecondPlayerOwnBoard().getGameBoard()[x][i].equals("E")) {
                    System.out.println("Wrong");
                    return;
                }
            }
            for (int i = player2.getPlayerShip().get(ship).getCoordinate().getyStart(); i < player2.getPlayerShip().get(ship).getCoordinate().getyStart() + player2.getPlayerShip().get(ship).getSize(); i++) {
                game.getFirstPlayerOwnBoard().getGameBoard()[x][i] = "E";
            }
        }
        Board.displayBoard(game.getFirstPlayerOwnBoard().getGameBoard());
        player2.getPlayerShip().get(ship).setAlreadyPlaced(false);
        player2.getPlayerShip().get(ship).setCoordinate(null);
        ship++;
        y++;
        x++;
        putShipPlayer2(x, y, ship, direction);
    }

    /*************************** Boom Player Ships ***************************/
    public static void boomPlayer1Ships(int x, int y) {
        x--;
        y--;
        if (player2.getPlayerBooms().contains(x + " " + y)) {
            System.out.println("selected " + x + "," + y + "has been already boomed");
            return;
        }
        player2.getPlayerBooms().add(x + " " + y);
        char namee = game.getFirstPlayerOwnBoard().getGameBoard()[x][y].charAt(0);

        if (namee == 'E') {
            game.getSecondPlayerEnemyBoard().getGameBoard()[x][y] = "-";
            game.getFirstPlayerOwnBoard().getGameBoard()[x][y] = "-";
            Board.displayBoard(game.getSecondPlayerEnemyBoard().getGameBoard());
            player2.getInCorrectPlayerBooms().add(x + " " + y);
            return;
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
            return;

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

        System.out.println("------------------------------------------");
        Board.displayBoard(game.getSecondPlayerEnemyBoard().getGameBoard());
        System.out.println("------------------------------------------");
        if (winPlayer2Checker())
            System.out.println("player 2 WIN !");

    }

    public static void boomPlayer2Ships(int x, int y) {
        x--;
        y--;
        if (player1.getPlayerBooms().contains(x + " " + y)) {
            System.out.println("selected " + x + "," + y + "has been already boomed");
            return;
        }
        player1.getPlayerBooms().add(x + " " + y);
        char namee = game.getSecondPlayerOwnBoard().getGameBoard()[x][y].charAt(0);

        if (namee == 'E') {
            game.getFirstPlayerEnemyBoard().getGameBoard()[x][y] = "-";
            game.getSecondPlayerOwnBoard().getGameBoard()[x][y] = "-";
            Board.displayBoard(game.getFirstPlayerEnemyBoard().getGameBoard());
            return;
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
            ship.setSize(ship.getSize() - 1);
        } else
            return;

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

        System.out.println("------------------------------------------");
        Board.displayBoard(game.getFirstPlayerEnemyBoard().getGameBoard());
        System.out.println("------------------------------------------");
        if (winPlayer1Checker())
            System.out.println("player 1 WIN !");

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

//    public static void showww(){
//        String[][] strings = game.getFirstPlayerOwnBoard().getGameBoard();
//        for (int i = 0; i < 10 ; i++) {
//            for (int j = 0; j < 10; j++) {
//                System.out.print(strings[j][i]+" | ");
//            }
//            System.out.println("");
//        }
//    }

    private static int randomMessageId(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}
