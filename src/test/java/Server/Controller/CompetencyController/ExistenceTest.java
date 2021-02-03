package Server.Controller.CompetencyController;

import Server.Controller.Exception.Plato.AlreadyBan;
import Server.Model.PlatoModel.Admin;
import Server.Model.PlatoModel.Player;
import junit.framework.TestCase;

import java.io.IOException;

public class ExistenceTest extends TestCase {

    public void testCheckUserNameExistence() {
        Player player = new Player("hesam", "AsnaAshari", 100, "hessamasna", "ApTeam@12345", "hessamasna@yahoo.com", "0912111111");
        Player.addNewUser(player);
        assertTrue(Existence.checkUserNameExistence("hessamasna"));
        assertFalse(Existence.checkUserNameExistence("AmirZgh"));

    }

    public void testCheckEmailExistence() {
        Player player = new Player("hesam", "AsnaAshari", 100, "hessamasna", "ApTeam@12345", "hessamasna@yahoo.com", "0912111111");
        Player.addNewUser(player);
        assertTrue(Existence.checkEmailExistence("hessamasna@yahoo.com"));
        assertFalse(Existence.checkEmailExistence("AmirZgh@yahoo.com"));
    }

    public void testCheckPassword() throws IOException, AlreadyBan {
        Player player1 = new Player("hesam", "AsnaAshari", 100, "hessamasna", "ApTeam@12345", "hessamasna@yahoo.com", "0912111111");
        Player.AddNewPlayer(player1);
        Player player2 = new Player("Amir", "Zgh", 101, "amirzgh", "ApTeam12345", "amirzgh@gmail.com", "0912111111");
        Player.AddNewPlayer(player2);

        assertTrue(Existence.checkPassword("hessamasna","ApTeam@12345"));
        assertFalse(Existence.checkPassword("amirzgh","ApTeam@12345"));

    }

    public void testAdminExistence() {
        assertTrue(Existence.adminExistence());
        Admin admin = new Admin("hesam", "AsnaAshari", 100, "hessamasna", "ApTeam@12345", "hessamasna@yahoo.com", "0912111111");
        Admin.AddNewAdmin(admin);
        assertFalse(Existence.adminExistence());
    }

}