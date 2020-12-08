package Controller.BattleSeaController;


import Model.BattleSeaModel.Ships.*;

import java.util.ArrayList;

public class BattleSeaPlayer {
    public static ArrayList<BattleSeaPlayer> battleSeaPlayers = new ArrayList<>();
    private ArrayList<String> playerBooms;
    private ArrayList<String> correctPlayerBooms;
    private ArrayList<String> inCorrectPlayerBooms;
    private String player;
    private int score;
    private ArrayList<Ship> playerShip;
    private ArrayList<Ship> boomedPlayerShip;
    private ArrayList<Ship> unBoomedPlayerShip;

    public BattleSeaPlayer(String player) {
        this.player = player;
        this.score = 0;
        playerShip = new ArrayList<Ship>();
        playerShip.add(new Battleship());
        playerShip.add(new AirCarrier());
        playerShip.add(new Cruiser());
        playerShip.add(new Destroyer());
        playerShip.add(new Submarine());
        this.playerBooms = new ArrayList<>();
        this.correctPlayerBooms = new ArrayList<>();
        this.inCorrectPlayerBooms = new ArrayList<>();
        this.unBoomedPlayerShip = new ArrayList<>();
        unBoomedPlayerShip.addAll(playerShip);
        this.boomedPlayerShip = new ArrayList<>();

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

    public ArrayList<String> getPlayerBooms() {
        return playerBooms;
    }

    public ArrayList<String> getCorrectPlayerBooms() {
        return correctPlayerBooms;
    }

    public ArrayList<String> getInCorrectPlayerBooms() {
        return inCorrectPlayerBooms;
    }

    public ArrayList<Ship> getBoomedPlayerShip() {
        return boomedPlayerShip;
    }

    public ArrayList<Ship> getUnBoomedPlayerShip() {
        return unBoomedPlayerShip;
    }
    public static void addNewBattleSeaPlayer(BattleSeaPlayer player){
        battleSeaPlayers.add(player);
    }


    @Override
    public String toString() {
        return "BattleSeaPlayer{" +
                "playerBooms=" + playerBooms +
                ", correctPlayerBooms=" + correctPlayerBooms +
                ", inCorrectPlayerBooms=" + inCorrectPlayerBooms +
                ", player='" + player + '\'' +
                ", score=" + score +
                ", playerShip=" + playerShip +
                '}';
    }
}
