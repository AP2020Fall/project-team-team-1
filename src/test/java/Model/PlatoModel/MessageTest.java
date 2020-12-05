package Model.PlatoModel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {

    @Test
    void addNewMessage() {
        Admin admin = new Admin("ata1","rhz",1,"atarhz","112233","ata@gmail.com","09121112222");
        Admin.AddNewAdmin(admin);
        Player player = new Player("ata2","rhz",1,"atarhz","112233","ata@gmail.com","09121112222");
        Player.AddNewPlayer(player);
        Message message = new Message("The pro ata",player);
        Message.addNewMessage(message);
        System.out.println(message.getText());
    }
}