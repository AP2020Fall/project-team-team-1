package Controller.DotsAndBoxesController;

import java.util.Arrays;

public class GameBoard {

    private int rows;
    private int columns;
    private int redScores;
    private int blueScores;
    private Player player;
    private Dots[][] dots;
    private Lines lines;
    private Box[][] boxes;
    private int moves;

    public GameBoard(int rows, int columns, Dots[][] dots) {
        this.rows = rows;
        this.columns = columns;
    }

    public boolean gameOver(){
        return true;
    }

    public Player whoseTurn(){
        Player player = null;
        return  player;
    }

    public boolean isLineValid(int row1, int column1, int row2, int column2){
        return true;
    }

    public void makeMove(int row1, int column1, int row2, int column2){

    }

    @Override
    public String toString() {
        return "GameBoard{" +
                "rows=" + rows +
                ", columns=" + columns +
                ", redScores=" + redScores +
                ", blueScores=" + blueScores +
                ", player=" + player +
                ", dots=" + Arrays.toString(dots) +
                ", lines=" + lines +
                ", boxes=" + Arrays.toString(boxes) +
                ", moves=" + moves +
                '}';
    }
}
