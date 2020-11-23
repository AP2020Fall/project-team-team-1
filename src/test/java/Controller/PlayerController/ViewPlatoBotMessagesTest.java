package Controller.PlayerController;

import Model.PlatoModel.Player;
import junit.framework.TestCase;

public class ViewPlatoBotMessagesTest extends TestCase {

    public void testViewBotMessages() {
        Player player = new Player("ata" , "rhz",1,"atarhz","1122334455","ataarahimzadeh@gamail.com","09365909061");
        Player.AddNewPlayer(player);
        ViewPlatoBotMessages.viewBotMessages("atarhz");
    }

    public void testShowAdminSuggestions() {
        Player player = new Player("ata" , "rhz",1,"atarhz","1122334455","ataarahimzadeh@gamail.com","09365909061");
        Player.AddNewPlayer(player);
        ViewPlatoBotMessages.showAdminSuggestions("atarhz");
    }
}