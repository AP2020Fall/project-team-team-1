package Controller.PlayerController;

import Model.PlatoModel.Player;
import junit.framework.TestCase;

public class FindPlayerByInfoTest extends TestCase {

    public void testFindByUserName() {
        Player player = new Player("ata","rhz" , 1 ,"atarhz" , "1122334455" ,"ataarahimzadeh@gmail.com","09365909061");
        Player.AddNewPlayer(player);

        FindPlayerByInfo.findByUserName("atarhz");
        assertEquals("atarhz" , "atarhz");
    }

    public void testFindByUserID() {
        Player player = new Player("ata","rhz" , 1 ,"atarhz" , "1122334455" ,"ataarahimzadeh@gmail.com","09365909061");
        Player.AddNewPlayer(player);

        FindPlayerByInfo.findByUserID("1");
        assertEquals(1 , 1);
    }

    public void testFindByUserEmail() {
        Player player = new Player("ata","rhz" , 1 ,"atarhz" , "1122334455" ,"ataarahimzadeh@gmail.com","09365909061");
        Player.AddNewPlayer(player);

        FindPlayerByInfo.findByUserEmail("ataarahimzadeh@gmail.com");
        assertEquals("ataarahimzadeh@gmail.com" , "ataarahimzadeh@gmail.com");
    }
}