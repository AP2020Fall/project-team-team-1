package Model.DotsAndBoxesModel;

import java.util.ArrayList;

public class Lines {
    public static ArrayList<Line> lines;
    private int column ;
    private int row ;
    public Lines(int column,int row,Dot[][] dot) {

        ArrayList<Line> newLines = new ArrayList<>();
        this.column=column;
        this.row=row;
        for (int i = 0; i <=row-1 ; i++) {
            for (int j = 0; j <column-1 ; j++) {
                Line line = new Line(dot[i][j],dot[i][j+1]);
                newLines.add(line);
            }
        }
        for (int i = 0; i <=column-1 ; i++) {
            for (int j = 0; j <row-1 ; j++) {
                Line line = new Line(dot[j][i],dot[j+1][i]);
                newLines.add(line);
            }
        }
        lines=newLines;
    }
    public Line getLine(int row1,int column1,int row2,int column2){
        Line finalLine = null;
        for(Line line : lines){
            if (line.getFirstDot().getRow()==row1 && line.getFirstDot().getColumn()==column1 && line.getSecondDot().getRow()==row2 && line.getSecondDot().getColumn()==column2){
                finalLine = line;
            }
        }

        return finalLine;
    }
    @Override
    public String toString() {
        return "" +
                "" + column +
                "" + row +
                "";
    }
}
