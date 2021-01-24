package Server.Model.PlatoModel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void addNewPlayer() {
        Player player = new Player("ata2","rhz",1,"atarhz","112233","ata@gmail.com","09121112222");
        Player.AddNewPlayer(player);
        assertEquals(Player.getPlayers().size(),1);
    }
}