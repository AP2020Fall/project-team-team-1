package Controller.PlayerController;

import Controller.Exception.*;
import static org.junit.jupiter.api.Assertions.*;
import Model.PlatoModel.Player;
import Model.PlatoModel.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class FavoriteGamesTest {

    @Test
    public void testAddGameToFavoriteGames() throws ExistFavoriteException, InvalidGameNameException {
        Player playerYasmin = new Player("yasmin", "kad", 1100, "yamsiin", "007Password","yasmiinkad@gmail.com", "09129749527");
        Player.players.add(playerYasmin);
        User.users.add(playerYasmin);

        FavoriteGames.addGameToFavoritesGames("yamsiin","battleShip");

        assertThrows(InvalidGameNameException.class, () -> FavoriteGames.addGameToFavoritesGames("yamsiin", "battleShiip"));
        assertThrows(ExistFavoriteException.class, () -> FavoriteGames.addGameToFavoritesGames("yamsiin","battleShip"));
        Assert.assertTrue(playerYasmin.getFavoritesGamesName().contains("battleShip"));
        Assert.assertEquals(playerYasmin.getFavoritesGamesName().size() , 1);
    }

    @Test
    public void testRemoveFavoriteGames() throws ExistFavoriteException, InvalidGameNameException {
        Player playerYasmin = new Player("yasmin", "kad", 1100, "yamsiin", "007Password","yasmiinkad@gmail.com", "09129749527");
        Player.players.add(playerYasmin);
        User.users.add(playerYasmin);

        FavoriteGames.addGameToFavoritesGames("yamsiin","battleShip");
        FavoriteGames.addGameToFavoritesGames("yamsiin","dotsAndBoxes");

        FavoriteGames.removeFavoritesGames("yamsiin", "battleShip");

        assertThrows(ExistFavoriteException.class, () -> FavoriteGames.removeFavoritesGames("yamsiin","battleShip"));
        Assert.assertFalse(playerYasmin.getFavoritesGamesName().contains("battleShip"));
        Assert.assertTrue(playerYasmin.getFavoritesGamesName().contains("dotsAndBoxes"));
        Assert.assertEquals(playerYasmin.getFavoritesGamesName().size(), 1);
    }

    @Test
    public void testShowFavoriteGames() throws ExistFavoriteException, InvalidGameNameException {
        Player playerYasmin = new Player("yasmin", "kad", 1100, "yamsiin", "007Password","yasmiinkad@gmail.com", "09129749527");
        Player.players.add(playerYasmin);
        User.users.add(playerYasmin);

        assertThrows(ExistFavoriteException.class, () -> FavoriteGames.showFavoritesGames("yamsiin"));

        FavoriteGames.addGameToFavoritesGames("yamsiin","battleShip");

        //FavoriteGames.showFavoritesGames("yamsiin");
        //Exceptiono inja test kardam okay ham mide vali vase method showfavoritegames nemishe test nevesht fek koanm chon age estebah nakonam haghe print kardane chizio tooye controller nadarim

    }
}
