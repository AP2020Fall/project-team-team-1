package RegisterController;

import Model.PlatoModel.Admin;
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

    }

    @Test
    public void testRandomUsernameId(){

    }
}
