package Controller.AdminController;

import Controller.PlayerController.FindPlayerByInfo;
import Model.PlatoModel.Player;
import Model.PlatoModel.Suggestion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SuggestionTest {

    @Test
    void addSuggestion() {
        Player player = new Player("ata","rhz",1,"atarhz","112233","ata@gmail.com","09121112222");
        Player.AddNewPlayer(player);
        Suggestion suggestion = new Suggestion(10, FindPlayerByInfo.findByUserName("atarhz"),"Battleship");
        Suggestion.addNewSuggestion(suggestion);
        assertEquals(suggestion.getSuggestedGame(),"Battleship");
    }

    @Test
    void showSuggestion() {
        Player player = new Player("ata","rhz",1,"atarhz","112233","ata@gmail.com","09121112222");
        Player.AddNewPlayer(player);
        Suggestion suggestion = new Suggestion(10, FindPlayerByInfo.findByUserName("atarhz"),"Battleship");
        Suggestion.addNewSuggestion(suggestion);
        assertEquals(Suggestion.getAllSuggestions().size(),1);
    }

    @Test
    void removeSuggestion() {
        Player player = new Player("ata","rhz",1,"atarhz","112233","ata@gmail.com","09121112222");
        Player.AddNewPlayer(player);
        Suggestion suggestion = new Suggestion(10, FindPlayerByInfo.findByUserName("atarhz"),"Battleship");
        Suggestion.addNewSuggestion(suggestion);
        Suggestion.getAllSuggestions().remove(suggestion);
        assertEquals(Suggestion.getAllSuggestions().size(),0);
    }

    @Test
    void findSuggestionBySuggestionID() {
        Player player = new Player("ata","rhz",1,"atarhz","112233","ata@gmail.com","09121112222");
        Player.AddNewPlayer(player);
        Suggestion suggestion = new Suggestion(10, FindPlayerByInfo.findByUserName("atarhz"),"Battleship");
        Suggestion.addNewSuggestion(suggestion);
        assertEquals(Suggestion.getAllSuggestions().size(),1);
    }
}