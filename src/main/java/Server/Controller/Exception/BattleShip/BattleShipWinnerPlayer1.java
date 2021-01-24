package Server.Controller.Exception.BattleShip;

public class BattleShipWinnerPlayer1 extends Exception{
    String playerName;

    public BattleShipWinnerPlayer1(String playerName) {
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
