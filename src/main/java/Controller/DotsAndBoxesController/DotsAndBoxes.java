package Controller.DotsAndBoxesController;

import java.util.ArrayList;

public class DotsAndBoxes {
    private static ArrayList<DotsAndBoxes> dotsAndBoxes = new ArrayList<>();
    public final String PROMPT = null ;
    private GameBoard gameBoard ;
    private int rows;
    private int columns;
    private int redScore;
    private int blueScore;
    private Player player;
    private Dots[][] dots;
    private Lines lines;
    private Box[][] boxes;
    private int moves;



    public DotsAndBoxes(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.dots = new Dots[8][8] ;
    }


    private boolean makeMove(){
        return true;
    }

    public void play(){

    }

    public void runGame(){

    }
}
