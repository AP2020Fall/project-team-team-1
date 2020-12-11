package Controller.PlayerController;

import Controller.CompetencyController.Existence;
import Controller.Exception.Plato.ExistFavoriteException;
import Controller.Exception.Plato.InvalidGameNameException;
import Model.PlatoModel.Player;

public class FavoriteGames {
    public static void addGameToFavoritesGames(String userName, String gameName) throws ExistFavoriteException, InvalidGameNameException {
        Player player = FindPlayerByInfo.findByUserName(userName);
        if (!Existence.checkGameExistence(gameName))
            throw new InvalidGameNameException(gameName);

        if (checkFavoriteGameExistence(userName, gameName))
            throw new ExistFavoriteException(gameName, "THIS GAME ALREADY EXISTS");

        player.getFavoritesGamesName().add(gameName);

    }

    public static void removeFavoritesGames(String userName, String gameName) throws ExistFavoriteException {
        Player player = FindPlayerByInfo.findByUserName(userName);

        if (!checkFavoriteGameExistence(userName, gameName))
            throw new ExistFavoriteException(gameName, " THIS GAME DOESN'T EXIST IN YOUR LIST ");

        player.getFavoritesGamesName().remove(gameName);


    }

    public static void showFavoritesGames(String userName) throws ExistFavoriteException {
        Player player = FindPlayerByInfo.findByUserName(userName);

        if (player.getFavoritesGamesName().size() == 0)
            throw new ExistFavoriteException("THE FAVORITE GAMES LIST IS EMPTY");

        for (String favorite : player.getFavoritesGamesName()) {
            System.out.println(favorite);
        }
    }

    private static boolean checkFavoriteGameExistence(String userName, String gameName) {
        boolean result = false;
        Player player = FindPlayerByInfo.findByUserName(userName);
        for (String favorite : player.getFavoritesGamesName()) {
            if (favorite.equalsIgnoreCase(gameName)) {
                result = true;
                break;
            }
        }
        return result;
    }
}
