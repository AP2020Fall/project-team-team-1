package Controller.PlayerController;

import Controller.Exception.*;
import static org.junit.jupiter.api.Assertions.*;
import Model.PlatoModel.Player;
import Model.PlatoModel.User;
import org.junit.Assert;
import org.junit.Test;

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
}
