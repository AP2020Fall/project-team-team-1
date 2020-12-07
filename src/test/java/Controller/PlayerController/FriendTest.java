package Controller.PlayerController;

import Controller.Exception.AcceptAndDeclineFriendException;
import Controller.Exception.ExistFriendException;
import Controller.Exception.ExistPlayerException;
import Model.PlatoModel.Player;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sun.security.x509.FreshestCRLExtension;

import static org.junit.jupiter.api.Assertions.*;


public class FriendTest {

    @BeforeEach
    public void fundamentals() throws ExistFriendException, ExistPlayerException {

        Player.AddNewPlayer(new Player("yasmin", "kad", 1100, "yamsiin", "007Password","yasmiinkad@gmail.com", "09129749527"));

        Player.AddNewPlayer(new Player("hesam", "asna", 1200, "hessamasna", "008Password","hesamasna@yahoo.com", "09123334455"));

        Player.AddNewPlayer(new Player("amirreza", "ghasemi", 1300, "amirzgh", "009Password","amirzgh@gmail.com", "09124445566"));

        Player.AddNewPlayer(new Player("ata", "rhz", 1400, "atarhz", "0010Password","atarhz@gmail.com", "09125556677"));

        Friend.addFriends("hessamasna","yamsiin");
        Friend.addFriends("hessamasna","atarhz");
    }

    @Test
    public void testAcceptRequests() throws AcceptAndDeclineFriendException, ExistPlayerException {

        assertThrows(ExistPlayerException.class, () -> Friend.addFriends("hessamasna", "yamsin"));

        Friend.acceptRequest("yamsiin","hessamasna");

        assertThrows(AcceptAndDeclineFriendException.class, () -> Friend.acceptRequest("hessamasna", "yamsiin"));

        Assert.assertFalse(FindPlayerByInfo.findByUserName("hessamasna").getFriendsRequests().contains(FindPlayerByInfo.findByUserName("yamsiin")));
        Assert.assertTrue(FindPlayerByInfo.findByUserName("hessamasna").getFriends().contains(FindPlayerByInfo.findByUserName("yamsiin")));
        Assert.assertTrue(FindPlayerByInfo.findByUserName("yamsiin").getFriends().contains(FindPlayerByInfo.findByUserName("hessamasna")));

    }
    @Test
    public void testDeclineRequests() throws  ExistPlayerException, AcceptAndDeclineFriendException {

        Friend.declineRequest("yamsiin", "hessamasna");

        assertThrows(ExistPlayerException.class, () -> Friend.declineRequest("hessamasna","yamsin"));
        assertThrows(AcceptAndDeclineFriendException.class, () -> Friend.declineRequest("hessamasna","yamsiin"));

        Assert.assertFalse(FindPlayerByInfo.findByUserName("hessamasna").getFriends().contains("yamsiin"));

    }
    @Test
    public void testAddFriends() throws ExistFriendException, ExistPlayerException, AcceptAndDeclineFriendException {

        assertThrows(ExistPlayerException.class, () -> Friend.addFriends("hessamasna", "yamsin"));

        Friend.acceptRequest("yamsiin" , "hessamasna");

        assertThrows(ExistFriendException.class, () -> Friend.addFriends("hessamasna","yamsiin"));

        Assert.assertTrue(FindPlayerByInfo.findByUserName("hessamasna").getFriends().contains(FindPlayerByInfo.findByUserName("yamsiin")));
        Assert.assertTrue(FindPlayerByInfo.findByUserName("yamsiin").getFriends().contains(FindPlayerByInfo.findByUserName("hessamasna")));
        Assert.assertFalse(FindPlayerByInfo.findByUserName("yamsiin").getFriends().contains(FindPlayerByInfo.findByUserName("amirzgh")));

    }
    @Test
    public void testRemoveFriend() throws ExistPlayerException, AcceptAndDeclineFriendException, ExistFriendException {

        Friend.acceptRequest("yamsiin" , "hessamasna");
        Friend.removeFriend("hessamasna", "yamsiin");

        assertThrows(ExistFriendException.class, () -> Friend.removeFriend("hessamasna", "amirzgh"));
        assertThrows(ExistPlayerException.class, () -> Friend.removeFriend("hessamasna", "yamsin"));

        Assert.assertFalse(FindPlayerByInfo.findByUserName("hessamasna").getFriends().contains(FindPlayerByInfo.findByUserName("yamsiin")));
        Assert.assertFalse((FindPlayerByInfo.findByUserName("yamsiin").getFriends().contains(FindPlayerByInfo.findByUserName("hessamasna"))));

    }
    @Test
    public void testShowRequests() throws ExistFriendException, ExistPlayerException, AcceptAndDeclineFriendException {

        Friend.declineRequest("yamsiin", "hessamasna");
        //Friend.addFriends("hessamasna","atarhz");

        // Testing showFriends output

        // too in test man faghat mitoonam Exception ro handle konam chon inj adarim ye chizi ro print mikonim va tooye controller hagh nadarim chizi print konim va bayad pass dade beshe be view

        assertThrows(ExistFriendException.class, () -> Friend.showRequests("hessamasna"));
        Assert.assertEquals(FindPlayerByInfo.findByUserName("hessamasna").getFriendsRequests().size(), 0);
    }
    @Test
    public void testShowFriends() throws ExistFriendException, ExistPlayerException, AcceptAndDeclineFriendException {

        assertThrows(ExistFriendException.class, () -> Friend.showFriends("hessamasna"));

        Friend.addFriends("hessamasna","atarhz");
        Friend.acceptRequest("yamsiin", "hessamasna");
        Friend.acceptRequest("atarhz" , "hessamasna");

        //Testing showFriends output

        // too in test man faghat mitoonam Exception ro handle konam chon inj adarim ye chizi ro print mikonim va tooye controller hagh nadarim chizi print konim va bayad pass dade beshe be view
    }
    @Test
    public void testShowFriendsProfile() throws ExistPlayerException, AcceptAndDeclineFriendException {

        Friend.acceptRequest("yamsiin","hessamasna");

        assertThrows(ExistFriendException.class, () -> Friend.showFriendProfile("hessamasna","atarhz"));

        // too in test man faghat mitoonam Exception ro handle konam chon inj adarim ye chizi ro print mikonim va tooye controller hagh nadarim chizi print konim va bayad pass dade beshe be view
    }

}
