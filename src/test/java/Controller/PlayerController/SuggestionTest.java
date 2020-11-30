package Controller.PlayerController;

import Controller.Exception.ExistSuggestionException;
import Model.PlatoModel.Player;
import junit.framework.TestCase;

public class SuggestionTest extends TestCase {

    public void testShowSuggestion() throws ExistSuggestionException {
        Player player = new Player("ata","rhz",1,"atarhz","1122334455","ataarahimzadeh@gmail.com","09365909061");
        Player.AddNewPlayer(player);

        Suggestion.showSuggestion(player.getUserName());
    }

    public void testPlaySuggestedGame() {
    }
}