package Controller.DotsAndBoxesController;

import java.util.ArrayList;

public class Line extends Lines {

    public final String EMPTY = null;
    public final String HORI_LINE = null ;
    public final String VERT_LINE = null ;
    private ArrayList<Box> boxes = new ArrayList<>();
    private Player owner ;
    private Dots first ;
    private Dots second ;
    private Box box ;

    public Line(int rows, int columns, Dots[][] dots, Dots first, Dots second) {
        super(rows, columns, dots);
        this.first = first;
        this.second = second;
    }

    public ArrayList<Box> getBoxes() {
        return boxes;
    }

    public Player getOwner() {
        return owner;
    }

    public Dots getFirst() {
        return first;
    }

    public Dots getSecond() {
        return second;
    }

    public void setBox(Box box) {
        this.box = box;
    }


    public boolean hasOwner(){
        return true;
    }
    public void claim(Player player){

    }

    @Override
    public String toString() {
        return "Line{" +
                "boxes=" + boxes +
                ", owner=" + owner +
                ", first=" + first +
                ", second=" + second +
                ", box=" + box +
                '}';
    }
    public boolean equals (Object object) {
        return true ;
    }
}
