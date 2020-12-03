package Controller.BattleSeaController.Ships;



import Controller.BattleSeaController.Coordinate.Coordinate;

import java.util.ArrayList;

public class Ship {
    private static ArrayList<Ship> ships = new ArrayList<Ship>();
    private String name;
    private int size;
    private boolean alreadyPlaced;
    //private List<> coordinateList;
    private Coordinate coordinate;

    public Ship(String name, int size, boolean alreadyPlaced, Coordinate coordinate) {
        this.name = name;
        this.size = size;
        this.alreadyPlaced = alreadyPlaced;
        this.coordinate = coordinate;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public boolean isAlreadyPlaced() {
        return alreadyPlaced;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setAlreadyPlaced(boolean alreadyPlaced) {
        this.alreadyPlaced = alreadyPlaced;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "name='" + name + '\'' +
                ", size=" + size +
                ", alreadyPlaced=" + alreadyPlaced +
                ", coordinate=" + coordinate +
                '}';
    }
}
