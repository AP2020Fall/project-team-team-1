package Controller.DotsAndBoxesController;

import java.util.ArrayList;

public class Lines extends GameBoard {
    private ArrayList<Line> lines = new ArrayList<>();

    public Lines(int rows, int columns, Dots[][] dots) {
        super(rows, columns, dots);
    }

    public Line getLine (int row1, int column1, int row2, int column2){
        Line line = null;
        return line;
    }
    public  int size(){
        return 0;
    }


}
