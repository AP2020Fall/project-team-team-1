package Controller.BattleSeaController;


import Controller.BattleSeaController.Ships.*;

import java.util.ArrayList;

public class BattleSeaPlayer{
    private String player;
    private int score;
    private ArrayList<Ship> playerShip ;

    public BattleSeaPlayer(String player) {
        this.player = player;
        this.score=0;
        playerShip = new ArrayList<Ship>();
        playerShip.add(new Battleship());
        playerShip.add(new Carrier());
        playerShip.add(new Cruiser());
        playerShip.add(new Destroyer());
        playerShip.add(new Submarine());

    }

    public String getPlayer() {
        return player;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public ArrayList<Ship> getPlayerShip() {
        return playerShip;
    }

    public void setPlayerShip(ArrayList<Ship> playerShip) {
        this.playerShip = playerShip;
    }

    @Override
    public String toString() {
        return "BattleSeaPlayer{" +
                "player='" + player + '\'' +
                ", score=" + score +
                ", playerShip=" + playerShip +
                '}';
    }
}
