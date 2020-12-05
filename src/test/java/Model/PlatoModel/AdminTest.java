package Model.PlatoModel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminTest {

    @Test
    void addNewAdmin() {
        Admin admin = new Admin("ata","rhz",1,"atarhz","112233","ata@gmail.com","09121112222");
        Admin.AddNewAdmin(admin);
        assertEquals(Admin.getAdmins().get(0).getUserName(),"atarhz");
    }
}