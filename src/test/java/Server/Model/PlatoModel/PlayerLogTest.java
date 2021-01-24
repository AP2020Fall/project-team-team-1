package Server.Model.PlatoModel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerLogTest {

    @Test
    void addNewPlayerLog() {
        Player player = new Player("ata2","rhz",1,"atarhz","112233","ata@gmail.com","09121112222");
        Player.AddNewPlayer(player);
        PlayerLog playerLog = new PlayerLog("The pro ata","Battleship");
        PlayerLog.addNewPlayerLog(playerLog);
        assertEquals(player.getPlayerLog().get(0).getGameName(),"DotsAndBoxes");
    }
}