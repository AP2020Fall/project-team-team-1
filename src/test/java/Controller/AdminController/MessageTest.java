package Controller.AdminController;

import Controller.Exception.ExistPlayerException;
import Controller.Exception.NotNullMessageException;
import Model.PlatoModel.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {

    @Test
    void sendMassage() throws ExistPlayerException, NotNullMessageException {

        Message.sendMassage("The Pro");
        assertEquals("The Pro", Model.PlatoModel.Message.getMessages());
    }

    @Test
    void showPlayerMassage() throws ExistPlayerException {
        Player player = new Player("ata","rhz",1,"atarhz","112233","ata@gmail.com","09121112222");
        Player.AddNewPlayer(player);
        assertEquals(Model.PlatoModel.Message.getMessages().size(),1);
    }
}