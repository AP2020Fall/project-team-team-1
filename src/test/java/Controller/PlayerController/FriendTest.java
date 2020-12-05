package Controller.PlayerController;

import Controller.Exception.AcceptAndDeclineFriendException;
import Controller.Exception.ExistFriendException;
import Controller.Exception.ExistPlayerException;
import Model.PlatoModel.Player;
import Model.PlatoModel.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class FriendTest {

    @Test
    public void testAcceptRequests() throws AcceptAndDeclineFriendException, ExistPlayerException, ExistFriendException {
        Player.AddNewPlayer(new Player("yasmin", "kad", 1100, "yamsiin", "007Password","yasmiinkad@gmail.com", "09129749527"));

        Player.AddNewPlayer(new Player("hesam", "asna", 1200, "hessamasna", "008Password","hesamasna@yahoo.com", "09123334455"));

        Player.AddNewPlayer(new Player("amirreza", "ghasemi", 1300, "amirzgh", "009Password","amirzgh@gmail.com", "09124445566"));

        Friend.addFriends("hessamasna","yamsiin");

        assertThrows(ExistPlayerException.class, () -> Friend.addFriends("hessamasna", "yamsin"));
//        assertThrows(AcceptAndDeclineFriendException.class, () -> Friend.acceptRequest("yamsiin", "hessamasna"));

        Friend.acceptRequest("yamsiin","hessamasna");

        Assert.assertFalse(FindPlayerByInfo.findByUserName("hessamasna").getFriendsRequests().contains(FindPlayerByInfo.findByUserName("yamsiin")));
        Assert.assertTrue(FindPlayerByInfo.findByUserName("hessamasna").getFriends().contains(FindPlayerByInfo.findByUserName("yamsiin")));
        Assert.assertTrue(FindPlayerByInfo.findByUserName("yamsiin").getFriends().contains(FindPlayerByInfo.findByUserName("hessamasna")));

    }

}
