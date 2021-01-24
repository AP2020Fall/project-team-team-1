package Server.Controller.PlayerController;

import static org.junit.jupiter.api.Assertions.*;

import Server.Controller.Exception.Plato.ExistFavoriteException;
import Server.Controller.Exception.Plato.InvalidGameNameException;
import Server.Model.PlatoModel.Player;
import org.junit.Assert;
import org.junit.Test;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
public class FavoriteGamesTest {

    @Test
    public void testAddGameToFavoriteGames() throws ExistFavoriteException, InvalidGameNameException {

        Player.AddNewPlayer(new Player("yasmin", "kad", 1100, "yamsiin", "007Password", "yasmiinkad@gmail.com", "09129749527"));

        FavoriteGames.addGameToFavoritesGames("yamsiin","battleShip");

        assertThrows(InvalidGameNameException.class, () -> FavoriteGames.addGameToFavoritesGames("yamsiin", "battleShiip"));

        //FavoriteGames.addGameToFavoritesGames("yamsiin","battleShip");

        assertThrows(ExistFavoriteException.class, () -> FavoriteGames.addGameToFavoritesGames("yamsiin","battleShip"));
        Assert.assertTrue(FindPlayerByInfo.findByUserName("yamsiin").getFavoritesGamesName().contains("battleShip"));
        Assert.assertEquals(FindPlayerByInfo.findByUserName("yamsiin").getFavoritesGamesName().size() , 1);
    }

    @Test
    public void testRemoveFavoriteGames() throws ExistFavoriteException, InvalidGameNameException {

        Player.AddNewPlayer(new Player("yasmin", "kad", 1100, "yamsiin", "007Password", "yasmiinkad@gmail.com", "09129749527"));

        FavoriteGames.addGameToFavoritesGames("yamsiin","dotsAndBoxes");

        FavoriteGames.removeFavoritesGames("yamsiin", "battleShip");

        assertThrows(ExistFavoriteException.class, () -> FavoriteGames.removeFavoritesGames("yamsiin","battleShip"));
        Assert.assertFalse(FindPlayerByInfo.findByUserName("yamsiin").getFavoritesGamesName().contains("battleShip"));
        Assert.assertTrue(FindPlayerByInfo.findByUserName("yamsiin").getFavoritesGamesName().contains("dotsAndBoxes"));
        Assert.assertEquals(FindPlayerByInfo.findByUserName("yamsiin").getFavoritesGamesName().size(), 1);

        FavoriteGames.removeFavoritesGames("yamsiin", "dotsAndBoxes");

    }

    @Test
    public void testShowFavoriteGames() throws ExistFavoriteException, InvalidGameNameException {
//
//        Player.AddNewPlayer(new Player("yasmin", "kad", 1100, "yamsiin", "007Password", "yasmiinkad@gmail.com", "09129749527"));
//        FavoriteGames.removeFavoritesGames("yamsiin", "dotsAndBoxes");
//
//        assertThrows(ExistFavoriteException.class, () -> FavoriteGames.showFavoritesGames("yamsiin"));
//
//        //FavoriteGames.showFavoritesGames("yamsiin");
//        //Exceptiono inja test kardam okay ham mide vali vase method showfavoritegames nemishe test nevesht fek koanm chon age estebah nakonam haghe print kardane chizio tooye controller nadarim
    }

    @Test
    public void testCheckFavoriteGameExistence() throws ExistFavoriteException, InvalidGameNameException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Player.AddNewPlayer(new Player("yasmin", "kad", 1100, "yamsiin", "007Password", "yasmiinkad@gmail.com", "09129749527"));

        FavoriteGames favoriteGames = new FavoriteGames();

        Method reflectCheckFavoriteGameExistence = favoriteGames.getClass().getDeclaredMethod("checkFavoriteGameExistence", String.class, String.class);
        reflectCheckFavoriteGameExistence.setAccessible(true);
        boolean resultBattleShip = (Boolean) reflectCheckFavoriteGameExistence.invoke(favoriteGames, "yamsiin", "battleShip");
        boolean resultDotsAndBoxes = (Boolean) reflectCheckFavoriteGameExistence.invoke(favoriteGames, "yamsiin", "dotsAndBoxes");

        Assert.assertTrue(resultBattleShip);
        Assert.assertFalse(resultDotsAndBoxes);
    }
}