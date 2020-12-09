package Controller.PlayerController;

import Controller.Exception.Plato.*;
import Model.PlatoModel.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;

public class EditTest{
    @BeforeEach
    public void fundamentals(){
        Player.AddNewPlayer(new Player("ata","rhz",1,"atarhz","11223344","ataarahimzadeh@gmail.com","09365909061"));

    }

    @Test
    public void testEditField() throws InvalidNameException, InvalidEmailException, InvalidPhoneNumberException, ExistEmailException, InvalidFieldException {

        Controller.PlayerController.Edit.editField("atarhz","name","amir");
        assertEquals(FindPlayerByInfo.findByUserName("atarhz").getName(),"amir");

        Controller.PlayerController.Edit.editField("atarhz","lastName","Zgh");
        assertEquals(FindPlayerByInfo.findByUserName("atarhz").getLastName(),"Zgh");

        Controller.PlayerController.Edit.editField("atarhz","email","amirZgh1@yahoo.com");
        assertEquals(FindPlayerByInfo.findByUserName("atarhz").getEmail(),"amirZgh1@yahoo.com");

        Controller.PlayerController.Edit.editField("atarhz","phonenumber","09122222222");
        assertEquals(FindPlayerByInfo.findByUserName("atarhz").getPhoneNum(),"09122222222");

//        Controller.PlayerController.Edit.editField("atarhz","username","amirrezazgh1");
//        assertEquals(player.getUserName(),"amirrezazgh1");
    }

//    public void testEditUsername() {
//        Player player = new Player("ata","rhz",1,"atarhz","11223344","ataarahimzadeh@gmail.com","09365909061");
//        Player.AddNewPlayer(player);
//        Edit.editUsername(player,"hesam");
//        assertEquals(player.getUserName(),"hesam");
//    }
    @Test
    public void testEditFirstName() throws InvalidNameException {

        Edit.editFirstName(FindPlayerByInfo.findByUserName("atarhz"),"hesam");
        assertEquals(FindPlayerByInfo.findByUserName("atarhz").getName(),"hesam");
    }
    @Test
    public void testEditLastName() throws InvalidNameException {

        Edit.editLastName(FindPlayerByInfo.findByUserName("atarhz"),"asna");
        assertEquals(FindPlayerByInfo.findByUserName("atarhz").getLastName(),"asna");
    }
    @Test
    public void testEditPassword() throws InvalidPasswordException, WrongPasswordException, SamePasswordException, StrongerPasswordException {

        Edit.editPassword("atarhz","11223344","hesamKhare22");
        assertEquals(FindPlayerByInfo.findByUserName("atarhz").getPassword(),"hesamKhare22");
    }
    @Test
    public void testEditEmail() throws InvalidEmailException, ExistEmailException {

        Edit.editEmail(FindPlayerByInfo.findByUserName("atarhz"),"hesamasna@yahoo.com");
        assertEquals(FindPlayerByInfo.findByUserName("atarhz").getEmail(),"hesamasna@yahoo.com");
    }
    @Test
    public void testEditPhoneNumber() throws InvalidPhoneNumberException {

        Edit.editPhoneNumber(FindPlayerByInfo.findByUserName("atarhz"),"09121111111");
        assertEquals(FindPlayerByInfo.findByUserName("atarhz").getPhoneNum(),"09121111111");
    }
}
