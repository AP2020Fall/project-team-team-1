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
    }

    public void displayBoard() {

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