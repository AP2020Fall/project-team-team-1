package RegisterController;

import Controller.RegisterController.LogIn;
import Model.PlatoModel.Admin;
import Model.PlatoModel.Player;
import Model.PlatoModel.User;
import org.junit.Assert;
import org.junit.Test;

public class LogInTest {
    @Test
    public void testLogInAsPlayer(){
        Player playerYasmin = new Player("yasmin", "kad", 1100, "yamsiin", "007Password","yasmiinkad@gmail.com", "09129749527");
        Player.players.add(playerYasmin);
        User.users.add(playerYasmin);
        LogIn logIn = new LogIn();
        logIn.loginAsPlayer("yamsiin 007Password");
        Assert.assertTrue(logIn.loginAsPlayer("yamsiin 007Password"));
        Player playerRosa = new Player("rosa", "kadk", 1200, "kdrosa", "009Password","yasmiinkad23@gmail.com", "09129700527");
        Player.players.add(playerRosa);
        User.users.add(playerRosa);
        logIn.loginAsPlayer("kdrosa 009Password");
        Assert.assertTrue(logIn.loginAsPlayer("kdrosa 009Password"));
    }

    @Test
    public void testLogInAsAdmin(){
        Admin adminYasmin =  new Admin("yasmin", "kad", 1100, "yamsiin", "007Password","yasmiinkad@gmail.com", "09129749527");
        LogIn login = new LogIn();
        Admin.AddNewAdmin(adminYasmin);
        login.loginAsAdmin("yamsiin 007Password");
        Assert.assertTrue(login.loginAsAdmin("yamsiin 007Password"));
        Admin adminRosa = new Admin("rosa", "kadk", 1200, "kdrosa", "009Password","yasmiinkad23@gmail.com", "09129700527");
        Admin.AddNewAdmin(adminRosa);
        login.loginAsAdmin("kdRosa 009Password");
        Assert.assertFalse(login.loginAsAdmin("kdrosa 009Password"));
    }
}
