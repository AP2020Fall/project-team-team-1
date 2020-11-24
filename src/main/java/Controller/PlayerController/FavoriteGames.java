package Controller.PlayerController;

import Model.PlatoModel.Games;
import Model.PlatoModel.Player;

public class FavoriteGames {
    public static void addGameToFavoritesGames(String userName, String gameName) {
        Player player = FindPlayerByInfo.findByUserName(userName);
        //todo check game existence
        if (checkFavoriteGameExistence(userName, gameName)) {
            System.out.println(gameName + " Is already exist !");
            return;
        }
        player.getFavoritesGamesName().add(gameName);
    }

    public static void RemoveFavoritesGames(String userName, String gameName) {
        Player player = FindPlayerByInfo.findByUserName(userName);
        if (!checkFavoriteGameExistence(userName, gameName)) {
            System.out.println(gameName + " is not exist!");
            return;
        }
        player.getFavoritesGamesName().remove(gameName);

    }

    public static void showFavoritesGames(String userName) {
        Player player = FindPlayerByInfo.findByUserName(userName);
        if (player.getFavoritesGamesName().size() == 0) {
            System.out.println("There is no favorite game");
            return;
        }
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
