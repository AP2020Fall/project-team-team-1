package Model.DotsAndBoxesModel;

import java.util.ArrayList;

public class Box {
    private int id;
    private int row;
    private int column;
    private final Line top;
    private final Line bottom;
    private final Line left;
    private final Line right;
    private Player owner;
    private static ArrayList<Box> boxes = new ArrayList<>();

    public Box(int row, int column ,Lines lines) {
        this.id = boxes.size()+1;
        this.row = row;
        this.column = column;
        this.owner=Player.NONE;
        this.bottom=lines.getLine(row+1,column,row+1,column+1);
        this.bottom.setBoxes(this);
        this.left=lines.getLine(row,column,row+1,column);
        this.left.setBoxes(this);
        this.right=lines.getLine(row,column+1,row+1,column+1);
        this.right.setBoxes(this);
        this.top =lines.getLine(row,column,row,column+1);
        this.top.setBoxes(this);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Line getTop() {
        return top;
    }

    public Line getBottom() {
        return bottom;
    }

    public Line getLeft() {
        return left;
    }

    public Line getRight() {
        return right;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return
                "."+top+"."+"\n"+
                        left+" "+getOwner()+""+right+"\n"
                        +"."+bottom+".";


    }
}
