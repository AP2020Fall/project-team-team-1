package Controller.DotsAndBoxesController;

public class Dots {
    private int row ;
    private int column ;
    //public final String DOT = null;

    public Dots(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public String toString() {
        return "Dots{" +
                "DOT='" + '\'' +
                '}';
    }

    public boolean equals (Object object){
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Dots dot = (Dots) object;

        if (row != dot.row){
            return false;
        }
        if (column != dot.column){
            return false;
        }

        return true;
     // nemidunm chika krdm naporsid !
    }

}
