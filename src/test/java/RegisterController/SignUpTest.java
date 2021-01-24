package RegisterController;

import Server.Controller.Exception.Plato.*;
import Server.Controller.PlayerController.FindPlayerByInfo;
import Server.Controller.RegisterController.SignUp;
import Server.Model.PlatoModel.Player;
import org.junit.Assert;
import org.junit.Test;

public class SignUpTest {

    @Test
    public void testAddAdmin() throws ExistAdminException, ExistEmailException, ExistUserNameException {
//        SignUp signUp = new SignUp();
//        signUp.addAdmin("hesam asnashari hessamasna Apteam@123334 hessamasana@yahoo.com 09121111111");
//        Admin admin1 = new Admin("hesam","asnashari",100,"hessamasna","Apteam@123334","hessamasna@yahoo.com","09121111111");
//        Admin.AddNewAdmin(admin1);
//        signUp.addAdmin("yasmin kadkhodaei yamsiin yasmiinkad@gmail.com 007Password  09129749527");
//        //signUp.addAdmin("hesam asnashari hessamasna Apteam@123334 hessamasana@yahoo.com 09121111111");
//        Assert.assertFalse();
//        Assert.assertFalse(Admin.getAdmins().isEmpty());
//        Assert.assertEquals(Admin.getAdmins().get(0).getUserName(), "yamsiin");
//        Assert.assertEquals(Admin.getAdmins().get(0).getPassword(), "007Password");
//        Assert.assertEquals(Admin.getAdmins().get(0).getEmail(), "yasmiinkad@gmail.com" );

    }

    @Test
    public void testAddPlayer() throws ExistEmailException, ExistUserNameException, EmptyExceptionForName, EmptyExceptionForLastName, EmptyExceptionForEmail, EmptyExceptionForUserName {
        SignUp signUp = new SignUp();
        signUp.addPlayer("ata rhz atarhz 008Password atarhz@gmail.com 09129709521");
        Assert.assertTrue(Player.players.contains(FindPlayerByInfo.findByUserName("atarhz")));
    }
}
