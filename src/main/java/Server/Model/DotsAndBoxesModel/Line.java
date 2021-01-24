package Server.Model.DotsAndBoxesModel;

import java.util.ArrayList;

public class Line {
    private ArrayList<Box> boxes;
    private Dot firstDot;
    private Dot secondDot;
    private Player owner;
    private static final String EMPTY = "   ";
    private static final String VERTICAL_LINE =" | ";
    private static final String HORIZONTAL_LINE ="───";

    public Line(Dot firstDot, Dot secondDot) {
        this.firstDot = firstDot;
        this.secondDot = secondDot;
        this.owner=Player.NONE;
        this.boxes=new ArrayList<>();
    }

    public void setBoxes(Box box) {
        this.boxes.add(box);
    }

    public Dot getFirstDot() {
        return this.firstDot;
    }

    public Dot getSecondDot() {
        return this.secondDot;
    }

    public Player getOwner() {
        return owner;
    }
    public boolean hasOwner(){
        return !this.owner.getLabel().equals("N");
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
    public String toString(){
        if (this.owner==Player.NONE){
            return EMPTY;
        }else if (getFirstDot().getColumn()==getSecondDot().getColumn()){
            return VERTICAL_LINE;
        }else if (getFirstDot().getRow()==getSecondDot().getRow()){
            return HORIZONTAL_LINE;
        }else return EMPTY;
    }
}
