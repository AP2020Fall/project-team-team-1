package Controller.AdminController;

import Controller.Exception.Plato.GameActivation;
import Controller.Exception.Plato.GameMaintenance;
import Controller.Exception.Plato.InvalidGameID;
import Model.BattleSeaModel.Details;
import Model.PlatoModel.Games;

import java.io.IOException;

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
        if (gameName.equalsIgnoreCase(Model.PlatoModel.Games.getGames().get(0).getGameName())){
            Details.setDetails(string);
            Details.saveInJsonFile();
        }else if (gameName.equalsIgnoreCase(Model.PlatoModel.Games.getGames().get(1).getGameName())){
            Model.DotsAndBoxesModel.Details.setDetails(string);
            Model.DotsAndBoxesModel.Details.saveInJsonFile();
        }

    }
}
