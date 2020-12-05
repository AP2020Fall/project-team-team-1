package Model.PlatoModel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void addNewUser() {
        User user = new User("ata","rhz",1,"atarhz","112233","ata@gmail.com","09121112222");
        User.addNewUser(user);
        assertEquals(User.users.get(0).getName(),"ata");
    }
}