package RegisterController;

import Controller.PlayerController.FindPlayerByInfo;
import Controller.RegisterController.Delete;
import Model.PlatoModel.Player;
import Model.PlatoModel.User;
import org.junit.Assert;
import org.junit.Test;

public class DeleteTest {
    @Test
    public void testDelete(){
        Player playerYasmin = new Player("yasmin", "kad", 1100, "yamsiin", "007Password","yasmiinkad@gmail.com", "09129749527");
        Player.players.add(playerYasmin);
        User.users.add(playerYasmin);

        Delete.deleteUser("yamsiin 007Password");

        Assert.assertFalse(Player.players.contains(playerYasmin));
        Assert.assertFalse(User.users.contains(playerYasmin));

    }
}
