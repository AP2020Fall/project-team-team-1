package Model.BattleSeaModel;


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
                gameBoard[i][j] = " ";
            }

        }
    }

    public static String displayBoard(String[][] strings) {
        StringBuilder board = new StringBuilder();
        board.append("     1   2   3   4   5   6   7   8   9   10$");
        for (int i = 0; i < 10 ; i++) {
            int rows= i+1;
            if (rows == 10){
                board.append(rows).append(" | ");
            }
            if (rows < 10){
                board.append(rows).append("  | ");

            }
            for (int j = 0; j < 10; j++) {
                board.append(strings[j][i]).append(" | ");
            }
            board.append("$");
        }
        board.append("»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»»$");
        return String.valueOf(board);
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