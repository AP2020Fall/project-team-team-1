package RegisterController;

import Server.Controller.Exception.Plato.InvalidUserNameException;
import Server.Controller.Exception.Plato.WrongPasswordException;
import Server.Controller.RegisterController.Delete;
import Server.Model.PlatoModel.Player;
import Server.Model.PlatoModel.User;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class DeleteTest {
    Delete delete = new Delete();
    @Test
    public void testDelete() throws WrongPasswordException, InvalidUserNameException, IOException {
        Player playerYasmin = new Player("yasmin", "kad", 1100, "yamsiin", "007Password","yasmiinkad@gmail.com", "09129749527");
        Player.players.add(playerYasmin);
        User.users.add(playerYasmin);
        delete.deleteUser("yamsiin 007Password");
        Assert.assertEquals(Player.players.size(),0);
    }
}
