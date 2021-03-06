package Server.Controller.AdminController;

import Server.Controller.Exception.Plato.*;
import Server.Model.PlatoModel.Admin;
import junit.framework.TestCase;


public class EditTest extends TestCase {
    Edit edit = new Edit();

    public void testEditField() throws InvalidNameException, InvalidEmailException, InvalidPhoneNumberException, ExistEmailException, InvalidFieldException {
        Admin admin1 = new Admin("hesam", "asnashari", 100, "hessamasna", "Apteam@123334", "hessamasna@yahoo.com", "09121111111");
        Admin.AddNewAdmin(admin1);
        Admin admin2 = new Admin("wedwedwe", "wedari", 1010, "amirrezazgh", "Apteam@123334", "hessa11masna@yahoo.com", "09121211111");
        Admin.AddNewAdmin(admin2);

        edit.editField("name", "amir");
        assertEquals(admin1.getName(), "amir");

        edit.editField("Lastname", "Zgh");
        assertEquals(admin1.getLastName(), "Zgh");

        edit.editField("email", "amirzgh1@yahoo.com");
        assertEquals(admin1.getEmail(), "amirzgh1@yahoo.com");

        edit.editField("phonenumber", "09122222222");
        assertEquals(admin1.getPhoneNum(), "09122222222");

//        Edit.editField("username","amirrezazgh1");
//        assertEquals(admin1.getUserName(),"amirrezazgh1");
    }

    public void testEditPassword() throws InvalidPasswordException, ExistPlayerException, WrongPasswordException, StrongerPasswordException {
        Admin admin1 = new Admin("hesam", "asnashari", 100, "hessamasna", "Apteam@123334", "hessamasna@yahoo.com", "09121111111");
        Admin.AddNewAdmin(admin1);
        edit.editPassword("Apteam@123334", "Qwe3434dsd23");
        assertEquals(admin1.getPassword(), "Qwe3434dsd23");
//        Edit.editPassword("Apteam@123334","Qwe3434dsd23");
//        assertEquals(admin1.getPassword(),"Qwe3434dsd23");
    }
    public void testEditName() throws InvalidNameException {
        Admin admin1 = new Admin("hesam", "asnashari", 100, "hessamasna", "Apteam@123334", "hessamasna@yahoo.com", "09121111111");
        Admin.AddNewAdmin(admin1);
        edit.editName("ata");
        assertEquals(admin1.getName(),"ata");
    }
    public void testEditLastName() throws InvalidNameException {
        Admin admin1 = new Admin("hesam", "asnashari", 100, "hessamasna", "Apteam@123334", "hessamasna@yahoo.com", "09121111111");
        Admin.AddNewAdmin(admin1);
        edit.editLastName("rhz");
        assertEquals(admin1.getLastName(),"rhz");
    }
    public void testEditEmail() throws InvalidEmailException, ExistEmailException {
        Admin admin1 = new Admin("hesam", "asnashari", 100, "hessamasna", "Apteam@123334", "hessamasna@yahoo.com", "09121111111");
        Admin.AddNewAdmin(admin1);
        edit.editEmail("ataarahimzadeh@gmail.com");
        assertEquals(admin1.getEmail(),"ataarahimzadeh@gmail.com");
    }


}