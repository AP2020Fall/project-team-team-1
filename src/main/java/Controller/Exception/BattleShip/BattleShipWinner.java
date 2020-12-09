package Controller.Exception.BattleShip;

public class BattleShipWinner extends Exception {
    String playerName;

    public BattleShipWinner(String playerName) {
        super(" is the Winner 🥇 , Player Again for Fun ✨");
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
