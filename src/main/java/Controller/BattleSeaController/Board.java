package Controller.BattleSeaController;


import java.util.Arrays;


public class Board {

    private final int lines = 10;
    private final int columns= 10;

    private String[][] gameBoard;

    public Board() {
        initBoard(this.lines, this.columns);
    }

    private void initBoard(int lines, int columns) {
        gameBoard = new String[lines][columns];
        for (int i = 0; i <lines ; i++) {
            for (int j = 0; j < columns; j++) {
                gameBoard[i][j] = "E";
            }

        }
    }

    public static void displayBoard(String[][] strings) {
        for (int i = 0; i < 10 ; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(strings[j][i]+" | ");
            }
            System.out.println("");
        }
    }

    public String[][] getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(String[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

    @Override
    public String toString() {
        return "Board{" +
                "lines=" + lines +
                ", columns=" + columns +
                ", gameBoard=" + Arrays.toString(gameBoard) +
                '}';
    }
}