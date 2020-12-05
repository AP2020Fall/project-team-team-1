package Controller.AdminController;

import Controller.Exception.ExistPlayerException;
import Model.PlatoModel.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {

    @Test
    void sendMassage() throws ExistPlayerException {
        Player player = new Player("ata","rhz",1,"atarhz","112233","ata@gmail.com","09121112222");
        Player.AddNewPlayer(player);
        Message.sendMassage("atarhz","The Pro");
        assertEquals(player.getReceivedMessages().get(0).getText(),"The Pro");
    }

    @Test
    void showPlayerMassage() throws ExistPlayerException {
        Player player = new Player("ata","rhz",1,"atarhz","112233","ata@gmail.com","09121112222");
        Player.AddNewPlayer(player);
        assertEquals(player.getReceivedMessages().size(),0);
    }
}