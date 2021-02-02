package Server;

import Server.Controller.BattleSeaController.BattleSeaController;
import Server.Controller.DotsAndBoxesController.DotsAndBoxesController;

import java.util.ArrayList;

public class GameMatcher {
    public static ArrayList<GameMatcher> gameMatchers = new ArrayList<>();
    private String player1;
    private String player2;
    private int score;
    private String whoseTurn;
    private BattleSeaController battleSeaController;
    private DotsAndBoxesController dotsAndBoxesController;

    public GameMatcher(String player1, String player2, int score) {
        this.player1 = player1;
        this.player2 = player2;
        this.score = score;
        this.whoseTurn = "player1";
        this.battleSeaController = new BattleSeaController();
        this.dotsAndBoxesController = new DotsAndBoxesController();
        this.battleSeaController.addPlayersToArrayList();
        this.battleSeaController.randomShipPlaceForPlayer1();
        this.battleSeaController.randomShipPlaceForPlayer2();
    }

    public static ArrayList<GameMatcher> getGameMatchers() {
        return gameMatchers;
    }

    public static void setGameMatchers(ArrayList<GameMatcher> gameMatchers) {
        GameMatcher.gameMatchers = gameMatchers;
    }

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public BattleSeaController getBattleSeaController() {
        return battleSeaController;
    }

    public void setBattleSeaController(BattleSeaController battleSeaController) {
        this.battleSeaController = battleSeaController;
    }

    public DotsAndBoxesController getDotsAndBoxesController() {
        return dotsAndBoxesController;
    }

    public void setDotsAndBoxesController(DotsAndBoxesController dotsAndBoxesController) {
        this.dotsAndBoxesController = dotsAndBoxesController;
    }

    public String getWhoseTurn() {
        return whoseTurn;
    }

    public void setWhoseTurn(String whoseTurn) {
        this.whoseTurn = whoseTurn;
    }

    public static void addNewGameMatcher(GameMatcher gameMatcher){
        gameMatchers.add(gameMatcher);
    }

    public static GameMatcher gameMatcherFinder(String username){
        for (GameMatcher gameMatcher : gameMatchers) {
            if (gameMatcher.player1.equals(username) || gameMatcher.player2.equals(username)){
                return gameMatcher;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "GameMatcher{" +
                "player1='" + player1 + '\'' +
                ", player2='" + player2 + '\'' +
                ", score=" + score +
                ", battleSeaController=" + battleSeaController +
                ", dotsAndBoxesController=" + dotsAndBoxesController +
                '}';
    }
}
