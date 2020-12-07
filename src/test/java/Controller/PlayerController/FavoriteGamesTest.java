package Controller.PlayerController;

import Controller.Exception.*;
import static org.junit.jupiter.api.Assertions.*;
import Model.PlatoModel.Player;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class FavoriteGamesTest {

    @BeforeEach
    public void fundamentals(){

        Player.AddNewPlayer(new Player("yasmin", "kad", 1100, "yamsiin", "007Password", "yasmiinkad@gmail.com", "09129749527"));
    }

    @Test
    public void testAddGameToFavoriteGames() throws ExistFavoriteException, InvalidGameNameException {

        FavoriteGames.addGameToFavoritesGames("yamsiin","battleShip");

        assertThrows(InvalidGameNameException.class, () -> FavoriteGames.addGameToFavoritesGames("yamsiin", "battleShiip"));
        assertThrows(ExistFavoriteException.class, () -> FavoriteGames.addGameToFavoritesGames("yamsiin","battleShip"));
        Assert.assertTrue(FindPlayerByInfo.findByUserName("yamsiin").getFavoritesGamesName().contains("battleShip"));
        Assert.assertEquals(FindPlayerByInfo.findByUserName("yamsiin").getFavoritesGamesName().size() , 1);
    }

    @Test
    public void testRemoveFavoriteGames() throws ExistFavoriteException, InvalidGameNameException {

        FavoriteGames.addGameToFavoritesGames("yamsiin","battleShip");
        FavoriteGames.addGameToFavoritesGames("yamsiin","dotsAndBoxes");

        FavoriteGames.removeFavoritesGames("yamsiin", "battleShip");

        assertThrows(ExistFavoriteException.class, () -> FavoriteGames.removeFavoritesGames("yamsiin","battleShip"));
        Assert.assertFalse(FindPlayerByInfo.findByUserName("yamsiin").getFavoritesGamesName().contains("battleShip"));
        Assert.assertTrue(FindPlayerByInfo.findByUserName("yamsiin").getFavoritesGamesName().contains("dotsAndBoxes"));
        Assert.assertEquals(FindPlayerByInfo.findByUserName("yamsiin").getFavoritesGamesName().size(), 1);
    }

    @Test
    public void testShowFavoriteGames() throws ExistFavoriteException, InvalidGameNameException {

        assertThrows(ExistFavoriteException.class, () -> FavoriteGames.showFavoritesGames("yamsiin"));

        FavoriteGames.addGameToFavoritesGames("yamsiin","battleShip");

        //FavoriteGames.showFavoritesGames("yamsiin");
        //Exceptiono inja test kardam okay ham mide vali vase method showfavoritegames nemishe test nevesht fek koanm chon age estebah nakonam haghe print kardane chizio tooye controller nadarim

    }

    @Test
    public void testCheckFavoriteGameExistence() throws ExistFavoriteException, InvalidGameNameException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        FavoriteGames favoriteGames = new FavoriteGames();

        FavoriteGames.addGameToFavoritesGames("yamsiin","battleShip");

        Method reflectcheckFavoriteGameExistence = favoriteGames.getClass().getDeclaredMethod("checkFavoriteGameExistence", String.class, String.class);
        reflectcheckFavoriteGameExistence.setAccessible(true);
        boolean resultBattleShip = (Boolean) reflectcheckFavoriteGameExistence.invoke(favoriteGames, "yamsiin", "battleShip");
        boolean resultDotsAndBoxes = (Boolean) reflectcheckFavoriteGameExistence.invoke(favoriteGames, "yamsiin", "dotsAndBoxes");

        Assert.assertTrue(resultBattleShip);
        Assert.assertFalse(resultDotsAndBoxes);
    }
}
