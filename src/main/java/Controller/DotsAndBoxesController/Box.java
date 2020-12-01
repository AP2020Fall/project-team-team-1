package Controller.DotsAndBoxesController;

public class Box {

    //    public Box(int rows, int columns, Dots[][] dots) {
//        super(rows, columns, dots);
//    }
    private int  row ;
    private Line bottomLine ;
    private Player owner ;
    private int column ;
    private Line topLine ;
    private Line rightLine ;
    private Line leftLine ;

    public Box(int row, int column, Lines lines) {
        this.row = row;
        this.column = column;
    }


    public void claim (Player player){

    }

    public int getRow() {
        return row;
    }

    public Line getBottomLine() {
        return bottomLine;
    }

    public Player getOwner() {
        return owner;
    }

    public int getColumn() {
        return column;
    }

    public Line getTopLine() {
        return topLine;
    }

    public Line getRightLine() {
        return rightLine;
    }

    public Line getLeftLine() {
        return leftLine;
    }

    @Override
    public String toString() {
        return "Box{" +
                "row=" + row +
                ", bottomLine=" + bottomLine +
                ", owner=" + owner +
                ", column=" + column +
                ", topLine=" + topLine +
                ", rightLine=" + rightLine +
                ", leftLine=" + leftLine +
                '}';
    }
    public boolean equals (Object object){if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Box box = (Box) object;

        if (topLine != box.topLine){
            return false;
        }
        if (bottomLine != box.bottomLine) {
            return false;
        }
        if (leftLine != box.leftLine) {
            return false;
        }
        if (rightLine != box.rightLine) {
            return false;
        }
        return true;
        // nemidunm chika krdm naporsid !
    }
}
