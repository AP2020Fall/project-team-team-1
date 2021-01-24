package Server.Controller.PlayerController;

import Server.Controller.Exception.Plato.InvalidGameNameException;
import Server.Model.PlatoModel.Games;
import Server.Model.PlatoModel.Player;

import java.util.*;

public class PlayerStatusInGame {

    /*** index = 0 in Games ArrayList ---> BattleShip ***/
    /*** index = 1 in Games ArrayList ---> DotsAndBoxes ***/

    public static String showScoreboardInThisGame(String gameName) throws InvalidGameNameException {
        StringBuilder showScoreboardInThisGame = new StringBuilder();
        int index = 0;
        int counter = 1;
        HashMap<String, Long> scoreBoard = new HashMap<>();
        if (gameName.equalsIgnoreCase(Games.getGames().get(1).getGameName())) {
            index = 0;
        } else if (gameName.equalsIgnoreCase(Games.getGames().get(0).getGameName())) {
            index = 1;
        } else {
            throw new InvalidGameNameException(gameName);
        }
        for (Player player : Player.getPlayers()) {
            scoreBoard.put(player.getUserName(), player.getPlayerLog().get(index).getTakenScore());
        }
        for (String sorted : sortingFunction(scoreBoard).keySet()) {
            showScoreboardInThisGame.append(counter).append(". Username : ").append(sorted).append("  ~~~~~~~~~   Score : ").append(sortingFunction(scoreBoard).get(sorted)).append("$");
            counter++;
        }
        return String.valueOf(showScoreboardInThisGame);
    }

    public static String showGameLogInThisGame(String userName, String gameName) throws InvalidGameNameException {
        StringBuilder showGameLogInThisGame = new StringBuilder();

        Player player = FindPlayerByInfo.findByUserName(userName);
        int index = 0;
        if (gameName.equalsIgnoreCase(Games.getGames().get(1).getGameName())) {
            index = 0;
        } else if (gameName.equalsIgnoreCase(Games.getGames().get(0).getGameName())) {
            index = 1;
        } else {
            throw new InvalidGameNameException(gameName);

        }
        int wins = player.getPlayerLog().get(index).getNumberOfWins();
        int loses = player.getPlayerLog().get(index).getNumberOfLoses();
        int numberOfPlayed = player.getPlayerLog().get(index).getNumberOfGamePlayed();

        showGameLogInThisGame.append("Wins : ").append(wins).append("$").append("Lose : ").append(loses).append("$").append("Number of match : ").append(numberOfPlayed).append("$");
        return String.valueOf(showGameLogInThisGame);
    }

    public static String numberOfWinsInThisGame(String userName, String gameName) throws InvalidGameNameException {
        Player player = FindPlayerByInfo.findByUserName(userName);
        int index = 0;
        if (gameName.equalsIgnoreCase(Games.getGames().get(1).getGameName())) {
            index = 0;
        } else if (gameName.equalsIgnoreCase(Games.getGames().get(0).getGameName())) {
            index = 1;
        } else {
            throw new InvalidGameNameException(gameName);

        }
        int wins = player.getPlayerLog().get(index).getNumberOfWins();
        return String.valueOf(wins);
    }
    public static String numberOfLossesInThisGame(String userName, String gameName) throws InvalidGameNameException {
        Player player = FindPlayerByInfo.findByUserName(userName);
        int index = 0;
        if (gameName.equalsIgnoreCase(Games.getGames().get(1).getGameName())) {
            index = 0;
        } else if (gameName.equalsIgnoreCase(Games.getGames().get(0).getGameName())) {
            index = 1;
        } else {
            throw new InvalidGameNameException(gameName);

        }
        int loses = player.getPlayerLog().get(index).getNumberOfLoses();
        return String.valueOf(loses);
    }

    public static String numberOfGamePlayedInThisGame(String userName, String gameName) throws InvalidGameNameException {
        Player player = FindPlayerByInfo.findByUserName(userName);
        int index = 0;
        if (gameName.equalsIgnoreCase(Games.getGames().get(1).getGameName())) {
            index = 0;
        } else if (gameName.equalsIgnoreCase(Games.getGames().get(0).getGameName())) {
            index = 1;
        } else {
            throw new InvalidGameNameException(gameName);

        }
        int numberOfPlayed = player.getPlayerLog().get(index).getNumberOfGamePlayed();
        return String.valueOf(numberOfPlayed);    }

    public static String showPlayerPointsInThisGame(String userName, String gameName) throws InvalidGameNameException {
        Player player = FindPlayerByInfo.findByUserName(userName);
        int index = 0;
        if (gameName.equalsIgnoreCase(Games.getGames().get(1).getGameName())) {
            index = 0;
        } else if (gameName.equalsIgnoreCase(Games.getGames().get(0).getGameName())) {
            index = 1;
        } else {
            throw new InvalidGameNameException(gameName);

        }
        long score = player.getPlayerLog().get(index).getTakenScore();
        return String.valueOf(score);
    }

    private static LinkedHashMap<String, Integer> sortingFunction(HashMap vorodi) {

        TreeMap<String, Integer> sortedAlphabet = new TreeMap<>(vorodi);

        Map<String, Integer> unSortedMap = sortedAlphabet;

        LinkedHashMap<String, Integer> SortedMapNumber = new LinkedHashMap<>();
        unSortedMap.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> SortedMapNumber.put(x.getKey(), x.getValue()));

        return SortedMapNumber;

    }

}
