package RegisterController;

import Controller.Exception.*;
import Controller.PlayerController.FindPlayerByInfo;
import Controller.RegisterController.LogIn;
import Model.PlatoModel.Admin;
import Model.PlatoModel.Player;
import Model.PlatoModel.User;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Executable;

public class LogInTest {
//    public static <T extends Throwable> Class<T> assertThrows(Class<T> expectedType, Executable executable){
//        return expectedType;
//    }

//    @Test
//    public void testLogInAsPlayer() throws WrongPasswordException, InvalidUserNameException {
//        Player playerYasmin = new Player("yasmin", "kad", 1100, "yamsiin", "007Password","yasmiinkad@gmail.com", "09129749527");
//        Player.players.add(playerYasmin);
//        User.users.add(playerYasmin);
//        LogIn logIn = new LogIn();
//        logIn.loginAsPlayer("yamsiin 007Password");
//        assertThrows(InvalidUserNameException.class, FindPlayerByInfo.findByUserName("yamsiin").getUserName());
//        assertThrows(InvalidEmailException.class, FindPlayerByInfo.findByUserEmail("yasmiinkad@gmail.com").getEmail());
//        assertThrows(WrongPasswordException.class, playerYasmin.getPassword());
//        assertThrows(InvalidNameException.class, playerYasmin.getName());
//        assertThrows(InvalidPhoneNumberException.class, playerYasmin.getPhoneNum());
//
//    }
//        @Test
//        public void testLogInAsAdmin() throws ExistAdminException, InvalidUserNameException, WrongPasswordException {
//        Admin adminYasmin =  new Admin("yasmin", "kad", 1100, "yamsiin", "007Password","yasmiinkad@gmail.com", "09129749527");
//        LogIn login = new LogIn();
//        Admin.AddNewAdmin(adminYasmin);
//        login.loginAsAdmin("yamsiin 007Password");
//        //assertThrows(ExistAdminException.class, "There is no Admin Yet!" );
//            //needs to be fixed
//      //  assertThrows(InvalidUserNameException.class ,Admin.getAdmins().get(0).getUserName());
//        assertThrows(InvalidPasswordException.class,"  ");
//    }
}
