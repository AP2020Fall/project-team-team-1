package Controller.PlayerController;

import Model.PlatoModel.Admin;
import Model.PlatoModel.Player;
import junit.framework.TestCase;

public class EditTest extends TestCase {

    public void testEditField() {
        Player player = new Player("ata","rhz",1,"atarhz","11223344","ataarahimzadeh@gmail.com","09365909061");
        Player.AddNewPlayer(player);


//        Controller.PlayerController.Edit.editField("name","amir");
//        assertEquals(player.getName(),"amir");
//
//        Controller.PlayerController.Edit.editField("Lastname","Zgh");
//        assertEquals(player.getLastName(),"Zgh");
//
//        Controller.PlayerController.Edit.editField("email","amirZgh1@yahoo.com");
//        assertEquals(player.getEmail(),"amirZgh1@yahoo.com");
//
//        Controller.PlayerController.Edit.editField("phonenumber","09122222222");
//        assertEquals(player.getPhoneNum(),"09122222222");
//
//        Edit.editField("username","amirrezazgh1");
//        assertEquals(player.getUserName(),"amirrezazgh1");
    }

    public void testEditUsername() {
        Player player = new Player("ata","rhz",1,"atarhz","11223344","ataarahimzadeh@gmail.com","09365909061");
        Player.AddNewPlayer(player);
        Edit.editUsername(player,"Amir");
        assertEquals(player.getUserName(),"Amir");
    }

    public void testEditFirstName() {
    }

    public void testEditLastName() {
    }

    public void testEditPassword() {

    }

    public void testEditEmail() {
    }

    public void testEditPhoneNumber() {
    }
}
