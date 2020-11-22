package Controller.AdminController;

import Model.PlatoModel.Admin;
import junit.framework.TestCase;

import java.util.ArrayList;

public class EditTest extends TestCase {

    public void testEditField() {
        Admin admin1 = new Admin("hesam","asnashari",100,"hessamasna","Apteam@123334","hessamasna@yahoo.com","09121111111");
        Admin.AddNewAdmin(admin1);
        Admin admin2 = new Admin("wedwedwe","wedari",1010,"amirrezazgh","Apteam@123334","hessa11masna@yahoo.com","09121211111");
        Admin.AddNewAdmin(admin2);

        Edit.editField("name","amir");
        assertEquals(admin1.getName(),"amir");

        Edit.editField("Lastname","Zgh");
        assertEquals(admin1.getLastName(),"Zgh");

        Edit.editField("email","amirZgh1@yahoo.com");
        assertEquals(admin1.getEmail(),"amirZgh1@yahoo.com");

        Edit.editField("phonenumber","09122222222");
        assertEquals(admin1.getPhoneNum(),"09122222222");

        Edit.editField("username","amirrezazgh1");
        assertEquals(admin1.getUserName(),"amirrezazgh1");
    }

    public void testEditPassword() {
    }
}