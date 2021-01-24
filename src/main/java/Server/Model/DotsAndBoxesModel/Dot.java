package Server.Model.DotsAndBoxesModel;

public class Dot {
    private int column;
    private int row;

    static String Dot;
    public Dot(int column , int row){
        this.column =column;
        this.row =row;
    }

    public int getColumn() {
        return this.column;
    }

    public int getRow() {
        return this.row;
    }

    @Override
    public String toString() {
        return ".";
    }
}
