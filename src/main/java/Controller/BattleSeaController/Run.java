package Controller.BattleSeaController;

import Controller.BattleSeaController.Coordinate.Coordinate;
import Controller.BattleSeaController.Coordinate.Direction;
import Controller.BattleSeaController.Ships.Ship;

import java.util.Random;


public class Run {
    public static BattleSeaPlayer player1 = new BattleSeaPlayer("hesam");
    public static BattleSeaPlayer player2 = new BattleSeaPlayer("Amir");
    public static GameBattleSea game = new GameBattleSea(player1, player2);

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
        Board.displayBoard(game.getFirstPlayerOwnBoard().getGameBoard());
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
        Board.displayBoard(game.getSecondPlayerOwnBoard().getGameBoard());
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

    private static int randomMessageId(int min , int max){
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}
