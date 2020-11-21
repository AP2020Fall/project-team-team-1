package Controller.BattleSeaController.Coordinate;

public class Coordinate {
    private String shipName;
    private int xStart;
    private int yStart;
    private int xLast;
    private int yLast;
    private Direction direction;

    public Coordinate(String shipName, int xStart, int yStart, int xLast, int yLast, Direction direction) {
        this.shipName = shipName;
        this.xStart = xStart;
        this.yStart = yStart;
        this.xLast = xLast;
        this.yLast = yLast;
        this.direction = direction;
    }

    public String getShip() {
        return shipName;
    }

    public int getxStart() {
        return xStart;
    }

    public void setxStart(int xStart) {
        this.xStart = xStart;
    }

    public int getyStart() {
        return yStart;
    }

    public void setyStart(int yStart) {
        this.yStart = yStart;
    }

    public int getxLast() {
        return xLast;
    }

    public void setxLast(int xLast) {
        this.xLast = xLast;
    }

    public int getyLast() {
        return yLast;
    }

    public void setyLast(int yLast) {
        this.yLast = yLast;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "shipName='" + shipName + '\'' +
                ", xStart=" + xStart +
                ", yStart=" + yStart +
                ", xLast=" + xLast +
                ", yLast=" + yLast +
                ", direction=" + direction +
                '}';
    }
}
