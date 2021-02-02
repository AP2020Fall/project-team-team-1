package Server.Controller.AdminController;

import Server.Controller.Exception.Plato.GameActivation;
import Server.Controller.Exception.Plato.GameMaintenance;
import Server.Controller.Exception.Plato.InvalidGameID;
import Server.Model.BattleSeaModel.Details;
import Server.Model.PlatoModel.Games;
import Server.Model.PlatoModel.Player;

import java.io.IOException;
import java.util.*;

public class Game {
    public static void changeGameName(String gameID,String name) throws InvalidGameID {

        if (Integer.parseInt(gameID) > Games.getGames().size())
            throw new InvalidGameID("Invalid Game ID please Check it and try Again");

        int indexOfGameInArrayList = Integer.parseInt(gameID) - 1;

        Games game = Games.getGames().get(indexOfGameInArrayList);

        game.setGameName(name);
    }
    public static void deActiveGame(String gameID) throws InvalidGameID, GameActivation {

        if (Integer.parseInt(gameID) > Games.getGames().size())
            throw new InvalidGameID("Invalid Game ID please Check it and try Again");

        int indexOfGameInArrayList = Integer.parseInt(gameID) - 1;

        Games game = Games.getGames().get(indexOfGameInArrayList);

        if (!game.isActivation())
            throw new GameActivation("Game is Already Deactivate");

        game.setActivation(false);
    }

    public static void activeGame(String gameID) throws InvalidGameID, GameActivation {

        if (Integer.parseInt(gameID) > Games.getGames().size())
            throw new InvalidGameID("Invalid Game ID please Check it and try Again");

        int indexOfGameInArrayList = Integer.parseInt(gameID) - 1;

        Games game = Games.getGames().get(indexOfGameInArrayList);

        if (game.isActivation())
            throw new GameActivation("Game is Already Activate");

        game.setActivation(true);
    }

    public static void activeMaintenanceMode(String gameID) throws InvalidGameID, GameMaintenance {

        if (Integer.parseInt(gameID) > Games.getGames().size())
            throw new InvalidGameID("Invalid Game ID please Check it and try Again");

        int indexOfGameInArrayList = Integer.parseInt(gameID) - 1;

        Games game = Games.getGames().get(indexOfGameInArrayList);

        if (game.isUpdate())
            throw new GameMaintenance("Game Maintenance is Already Active !");

        game.setUpdate(true);
    }
    public static void deActiveMaintenanceMode(String gameID) throws InvalidGameID, GameMaintenance {

        if (Integer.parseInt(gameID) > Games.getGames().size())
            throw new InvalidGameID("Invalid Game ID please Check it and try Again");

        int indexOfGameInArrayList = Integer.parseInt(gameID) - 1;

        Games game = Games.getGames().get(indexOfGameInArrayList);

        if (!game.isUpdate())
            throw new GameMaintenance("Game Maintenance is Already De Active !");

        game.setUpdate(false);
    }
    public static String maintenanceStatus(String gameID) throws InvalidGameID {
        if (Integer.parseInt(gameID) > Games.getGames().size())
            throw new InvalidGameID("Invalid Game ID please Check it and try Again");

        int indexOfGameInArrayList = Integer.parseInt(gameID) - 1;

        Games game = Games.getGames().get(indexOfGameInArrayList);

        return String.valueOf(game.isUpdate());
    }
    public static String activationStatus(String gameID) throws InvalidGameID {
        if (Integer.parseInt(gameID) > Games.getGames().size())
            throw new InvalidGameID("Invalid Game ID please Check it and try Again");

        int indexOfGameInArrayList = Integer.parseInt(gameID) - 1;

        Games game = Games.getGames().get(indexOfGameInArrayList);

        return String.valueOf(game.isActivation());
    }

    public static void setDetails(String gameName,String string) throws IOException {
        if (gameName.equalsIgnoreCase(Server.Model.PlatoModel.Games.getGames().get(0).getGameName())){
            Details.setDetails(string);
            Details.saveInJsonFile();
        }else if (gameName.equalsIgnoreCase(Server.Model.PlatoModel.Games.getGames().get(1).getGameName())){
            Server.Model.DotsAndBoxesModel.Details.setDetails(string);
            Server.Model.DotsAndBoxesModel.Details.saveInJsonFile();
        }

    }
    
    public static String getMVPUser(){
        HashMap<String, Long> mvp = new HashMap<>();

        for (Player player : Player.getPlayers()) {
            long scores = player.getPlayerLog().get(0).getTakenScore() + player.getPlayerLog().get(1).getTakenScore();
            mvp.put(player.getUserName(),scores);
        }
        String mvpUser = "null";
        for (String sorted : sortingFunction(mvp).keySet()) {
            mvpUser = sorted;
            break;
        }

        return mvpUser;
    }
    public static String getMVPUserFirstGame(){
        HashMap<String, Long> mvp = new HashMap<>();

        for (Player player : Player.getPlayers()) {
            long scores = player.getPlayerLog().get(1).getTakenScore();
            mvp.put(player.getUserName(),scores);
        }
        String mvpUser = "null";
        String mvpScore = "null";
        for (String sorted : sortingFunction(mvp).keySet()) {
            mvpUser = sorted;
            mvpScore = String.valueOf(sortingFunction(mvp).get(sorted));
            break;
        }

        return mvpUser.concat("$").concat(mvpScore).concat("$");
    }
    public static String getMVPUserSecondGame(){
        HashMap<String, Long> mvp = new HashMap<>();

        for (Player player : Player.getPlayers()) {
            long scores = player.getPlayerLog().get(0).getTakenScore() ;
            mvp.put(player.getUserName(),scores);
        }
        String mvpUser = "null";
        String mvpScore = "null";
        for (String sorted : sortingFunction(mvp).keySet()) {
            mvpUser = sorted;
            mvpScore = String.valueOf(sortingFunction(mvp).get(sorted));
            break;
        }

        return mvpUser.concat("$").concat(mvpScore).concat("$");
    }

    public static String numberOfTotalPlayed(){
        long number = 0;
        for (Player player : Player.getPlayers()) {
            number = player.getPlayerLog().get(0).getNumberOfGamePlayed() + player.getPlayerLog().get(1).getNumberOfGamePlayed();
        }
        return String.valueOf(number);
    }
    //BattleShip
    public static String numberOfTotalPlayedFirstGame(){
        long number = 0;
        for (Player player : Player.getPlayers()) {
            number = player.getPlayerLog().get(1).getNumberOfGamePlayed();
        }
        return String.valueOf(number);
        //return "85";
    }
    //Dots
    public static String numberOfTotalPlayedSecondGame(){
        long number = 0;
        for (Player player : Player.getPlayers()) {
            number = player.getPlayerLog().get(0).getNumberOfGamePlayed();
        }
        return String.valueOf(number);
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
