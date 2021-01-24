package Server.Model.DotsAndBoxesModel;

import java.util.ArrayList;

public class GameBoard {
    public final ArrayList<Box> boxes;
    public final Lines lines;
    private final int columns;
    private final int rows;
    private final Dot[][] dots;
    public Player player;
    public int bluePoints;
    public int redPoints;
    public int moves;
    public ArrayList<Box> availableBoxes;
    public ArrayList<Line> usedLines;

    public GameBoard(int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
        this.moves = 1;
        dots = new Dot[rows][columns];
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                dots[i][j] = new Dot(i, j);
            }
        }
        this.lines = new Lines(columns, rows, dots);
        this.bluePoints = 0;
        this.redPoints = 0;
        this.boxes = createBoxes(rows, columns, this.lines);
        this.availableBoxes = new ArrayList<>();
        availableBoxes.addAll(boxes);
        this.usedLines = new ArrayList<>();
    }
    public Lines getLines() {
        return lines;
    }
    public int getMoves() {
        return moves;
    }

    public int getBluePoints() {
        return bluePoints;
    }

    public void setBluePoints(int bluePoints) {
        this.bluePoints = bluePoints;
    }

    public void setRedPoints(int redPoints) {
        this.redPoints = redPoints;
    }

    public int getRedPoints() {
        return redPoints;
    }
    private ArrayList<Box> createBoxes(int rows, int columns, Lines lines) {
        ArrayList<Box> listOfBoxes = new ArrayList<>();
        int counter = 0;
        for (int i = 0; i < rows - 1; i++) {
            for (int j = 0; j < columns - 1; j++) {
                Box box = new Box(i, j, lines);
                box.setId(counter);
                listOfBoxes.add(box);
                counter++;
            }
        }
        return listOfBoxes;
    }
}
