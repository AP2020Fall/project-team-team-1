package RegisterController;

import Model.PlatoModel.Admin;
import Model.PlatoModel.Player;
import org.junit.Assert;
import org.junit.Test;

public class SignUpTest {

    @Test
    public void testAddAdmin(){
        Admin adminYasmin = new Admin("yasmin", "kadkhodaei", 1000, "yamsiin", "007pass","yasmiinkad@gmail.com", "09129749527");
        Admin.AddNewAdmin(adminYasmin);
        Assert.assertFalse(Admin.getAdmins().isEmpty());
        Assert.assertEquals(Admin.getAdmins().size(), 1);
    }

    @Test
    public void testAddPlayer(){
        Player playerYasmin = new Player("yasmin", "kad", 1100, "yamsiin", "007pass","yasmiinkad@gmail.com", "09129749527");
        Player.players.add(playerYasmin);



    }

    @Test
    public void testRandomUsernameId(){

    }
}
