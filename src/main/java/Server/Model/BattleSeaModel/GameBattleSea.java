package Server.Model.BattleSeaModel;

public class GameBattleSea {
    private BattleSeaPlayer player1;
    private BattleSeaPlayer player2;
    private Board firstPlayerOwnBoard;
    private Board firstPlayerEnemyBoard;
    private Board secondPlayerOwnBoard;
    private Board secondPlayerEnemyBoard;

    public GameBattleSea(BattleSeaPlayer player1, BattleSeaPlayer player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.firstPlayerOwnBoard = new Board();
        this.secondPlayerOwnBoard = new Board();
        this.firstPlayerEnemyBoard = new Board();
        this.secondPlayerEnemyBoard = new Board();
    }

    public BattleSeaPlayer getPlayer1() {
        return player1;
    }

    public BattleSeaPlayer getPlayer2() {
        return player2;
    }


    public Board getFirstPlayerOwnBoard() {
        return firstPlayerOwnBoard;
    }

    public void setFirstPlayerOwnBoard(Board firstPlayerOwnBoard) {
        this.firstPlayerOwnBoard = firstPlayerOwnBoard;
    }

    public Board getFirstPlayerEnemyBoard() {
        return firstPlayerEnemyBoard;
    }

    public void setFirstPlayerEnemyBoard(Board firstPlayerEnemyBoard) {
        this.firstPlayerEnemyBoard = firstPlayerEnemyBoard;
    }

    public Board getSecondPlayerOwnBoard() {
        return secondPlayerOwnBoard;
    }

    public void setSecondPlayerOwnBoard(Board secondPlayerOwnBoard) {
        this.secondPlayerOwnBoard = secondPlayerOwnBoard;
    }

    public Board getSecondPlayerEnemyBoard() {
        return secondPlayerEnemyBoard;
    }

    public void setSecondPlayerEnemyBoard(Board secondPlayerEnemyBoard) {
        this.secondPlayerEnemyBoard = secondPlayerEnemyBoard;
    }


    @Override
    public String toString() {
        return "GameBattleSea{" +
                "p1=" + player1 +
                ", p2=" + player1 +
                ", firstPlayerOwnBoard=" + firstPlayerOwnBoard +
                ", firstPlayerEnemyBoard=" + firstPlayerEnemyBoard +
                ", secondPlayerOwnBoard=" + secondPlayerOwnBoard +
                ", secondPlayerEnemyBoard=" + secondPlayerEnemyBoard +
                '}';
    }
}
