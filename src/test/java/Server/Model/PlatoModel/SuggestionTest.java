package Server.Model.PlatoModel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SuggestionTest {

    @Test
    void addNewSuggestion() {
        Player player = new Player("ata","rhz",1,"atarhz","112233","ata@gmail.com","09121112222");
        Player.AddNewPlayer(player);
        Suggestion suggestion = new Suggestion(10,player,"Battleship");
        Suggestion.addNewSuggestion(suggestion);
        assertEquals(suggestion.getSuggestedGame(),"Battleship");
    }
}