package Controller.PlayerController;

import Model.PlatoModel.Admin;
import Model.PlatoModel.Player;
import junit.framework.TestCase;

public class EditTest extends TestCase {

    public void testEditField() {
        Player player = new Player("ata","rhz",1,"atarhz","11223344","ataarahimzadeh@gmail.com","09365909061");
        Player.AddNewPlayer(player);

        Controller.PlayerController.Edit.editField("atarhz","name","amir");
        assertEquals(player.getName(),"amir");

        Controller.PlayerController.Edit.editField("atarhz","Lastname","Zgh");
        assertEquals(player.getLastName(),"Zgh");

        Controller.PlayerController.Edit.editField("atarhz","email","amirZgh1@yahoo.com");
        assertEquals(player.getEmail(),"amirZgh1@yahoo.com");

        Controller.PlayerController.Edit.editField("atarhz","phonenumber","09122222222");
        assertEquals(player.getPhoneNum(),"09122222222");

//        Controller.PlayerController.Edit.editField("atarhz","username","amirrezazgh1");
//        assertEquals(player.getUserName(),"amirrezazgh1");
    }

//    public void testEditUsername() {
//        Player player = new Player("ata","rhz",1,"atarhz","11223344","ataarahimzadeh@gmail.com","09365909061");
//        Player.AddNewPlayer(player);
//        Edit.editUsername(player,"hesam");
//        assertEquals(player.getUserName(),"hesam");
//    }

    public void testEditFirstName() {
        Player player = new Player("ata","rhz",1,"atarhz","11223344","ataarahimzadeh@gmail.com","09365909061");
        Player.AddNewPlayer(player);
        Edit.editFirstName(player,"hesam");
        assertEquals(player.getName(),"hesam");
    }

    public void testEditLastName() {
        Player player = new Player("ata","rhz",1,"atarhz","11223344","ataarahimzadeh@gmail.com","09365909061");
        Player.AddNewPlayer(player);
        Edit.editLastName(player,"asna");
        assertEquals(player.getLastName(),"asna");
    }

    public void testEditPassword() {
        Player player = new Player("ata","rhz",1,"atarhz","11223344","ataarahimzadeh@gmail.com","09365909061");
        Player.AddNewPlayer(player);
        Edit.editPassword(player,"11223344","hesamKhare22");
        assertEquals(player.getPassword(),"hesamKhare22");
    }

    public void testEditEmail() {
        Player player = new Player("ata","rhz",1,"atarhz","11223344","ataarahimzadeh@gmail.com","09365909061");
        Player.AddNewPlayer(player);
        Edit.editEmail(player,"hesamasna@yahoo.com");
        assertEquals(player.getEmail(),"hesamasna@yahoo.com");
    }

    public void testEditPhoneNumber() {
        Player player = new Player("ata","rhz",1,"atarhz","11223344","ataarahimzadeh@gmail.com","09365909061");
        Player.AddNewPlayer(player);
        Edit.editPhoneNumber(player,"09121111111");
        assertEquals(player.getPhoneNum(),"09121111111");
    }
}
