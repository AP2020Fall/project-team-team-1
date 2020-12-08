package Controller.PlayerController;

import Controller.Exception.ExistPlayerException;
import Controller.Exception.ExistSuggestionException;
import Model.PlatoModel.Player;
import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SuggestionTest {
    @BeforeEach
    public void fundamentals(){
        Player.AddNewPlayer(new Player("ata","rhz",1,"atarhz","1122334455","ataarahimzadeh@gmail.com","09365909061"));
    }

    @Test
    public void testShowSuggestion() throws ExistSuggestionException {

        Suggestion.showSuggestion(FindPlayerByInfo.findByUserName("atarhz").getUserName());
    }
    @Test
    public void testPlaySuggestedGame() throws ExistPlayerException {
    //    Model.PlatoModel.Suggestion suggestion = Controller.AdminController.Suggestion.addSuggestion();

    }
}