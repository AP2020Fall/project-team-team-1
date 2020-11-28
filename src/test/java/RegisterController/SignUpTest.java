package RegisterController;

import Controller.Exception.ExistAdminException;
import Controller.Exception.ExistEmailException;
import Controller.Exception.ExistUserNameException;
import Controller.PlayerController.FindPlayerByInfo;
import Controller.RegisterController.SignUp;
import Model.PlatoModel.Admin;
import Model.PlatoModel.Player;
import org.junit.Assert;
import org.junit.Test;

public class SignUpTest {

    @Test
    public void testAddAdmin() throws ExistAdminException, ExistEmailException, ExistUserNameException {
        SignUp signUp = new SignUp();
        signUp.addAdmin("yasmin kadkhodaei yamsiin 007Password yasmiinkad@gmail.com 09129749527");
        Assert.assertFalse(Admin.getAdmins().isEmpty());
        signUp.addAdmin("hesam asnaashari hesamasna 008Password hesamasna@yahoo.com 09121112233");
        Assert.assertEquals(Admin.getAdmins().size(), 1);
        Assert.assertEquals(FindPlayerByInfo.findByUserName("yamsiin").getUserName(), "yamsiin");
//        Assert.assertEquals(, "007Password");
//        Assert.assertEquals(, "yasmiinkad@gmail.com");

    }

    @Test
    public void testAddPlayer() throws ExistEmailException, ExistUserNameException {
        SignUp signUp = new SignUp();
        signUp.addPlayer("ata rhz atarhz 008Password atarhz@gmail.com 09129709521");
        Assert.assertTrue(Player.players.contains(FindPlayerByInfo.findByUserName("atarhz")));
    }
}
