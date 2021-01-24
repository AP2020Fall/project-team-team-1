package Server.Controller.AdminController;

import Server.Controller.Exception.Plato.ExistPlayerException;
import Server.Controller.PlayerController.FindPlayerByInfo;
import Server.Model.PlatoModel.Admin;
import Server.Model.PlatoModel.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerListsTest {

    @Test
    void showAllUsers() throws ExistPlayerException {
        Player player = new Player("ata","rhz",1,"atarhz","112233","ata@gmail.com","09121112222");
        Player.AddNewPlayer(player);
        PlayerLists.showAllUsers();
        assertEquals(Player.getPlayers().size(),1);
    }

    @Test
    void showUsersByUserName() throws ExistPlayerException {
        Player player = new Player("ata","rhz",1,"atarhz","112233","ata@gmail.com","09121112222");
        Player.AddNewPlayer(player);
        PlayerLists.showUsersByUserName("atarhz");
        assertEquals(FindPlayerByInfo.findByUserName("atarhz").getUserName(),"atarhz");
    }

    @Test
    void showAdminInfo() {
        Admin admin = new Admin("ata","rhz",1,"atarhz","112233","ata@gmail.com","09121112222");
        Admin.AddNewAdmin(admin);
        assertEquals(Admin.getAdmins().size(),1);
        System.out.println(Admin.getAdmins());

    }
}