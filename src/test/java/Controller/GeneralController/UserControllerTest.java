package Controller.GeneralController;

import Controller.Exception.*;
import Controller.PlayerController.FindPlayerByInfo;
import Model.PlatoModel.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class UserControllerTest {
    @Test
    public void testShowUserInfo() throws  NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        User testUser = new User("yasmin", "kad", 1100, "yamsiin", "007Password", "yasmiinkad@gmail.com", "09129749527");
        User.users.add(testUser);
        UserController userController = new UserController();

        Method reflectFindUserByUserName = userController.getClass().getDeclaredMethod("findUserByUsername", String.class);
        reflectFindUserByUserName.setAccessible(true);

        Assert.assertEquals((reflectFindUserByUserName.invoke(userController, "yamsiin")).toString(), "User{name='yasmin', lastName='kad', UserID=1100, userName='yamsiin', password='007Password', email='yasmiinkad@gmail.com', phoneNum='09129749527'}");

        //man testesho neveshtam vali baz migam age mikhaym chizi ro sout konim bayad to view bashe na controller
    }


    @Test
    public void testFindUserByUsername() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        User testUser = new User("yasmin", "kad", 1100,"yamsiin", "007Password","yasmiinkad@gmail.com", "09129749527");
        User.users.add(testUser);

        UserController userController = new UserController();

        Method reflectFindUserByUserName = userController.getClass().getDeclaredMethod("findUserByUsername", String.class);
        reflectFindUserByUserName.setAccessible(true);

        Assert.assertNotNull(reflectFindUserByUserName.invoke(userController,"yamsiin"));
        Assert.assertNull(reflectFindUserByUserName.invoke(userController,"hessanasna"));
    }

}
