package Server.Controller.PlayerController;

import Server.Controller.Exception.Plato.ExistPlayerException;
import Server.Controller.Exception.Plato.ExistSuggestionException;
import Server.Model.PlatoModel.Player;
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
    //    Server.Model.PlatoModel.Suggestion suggestion = Server.Controller.AdminController.Suggestion.addSuggestion();

    }
}