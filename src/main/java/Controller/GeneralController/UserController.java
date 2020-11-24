package Controller.GeneralController;

import Model.PlatoModel.User;

public class UserController {
    public void showUserInfo(String username){
        User user = findUserByUsername(username);
        System.out.println("Firstname : " + user.getName());
        System.out.println("Lastname : "+ user.getUserName());
        System.out.println("Username : "+username);
        System.out.println("Email : "+user.getEmail());
        System.out.println("PhoneNumber : "+user.getPhoneNum());
        System.out.println("User ID : "+user.getUserID());

    }
    private User findUserByUsername(String username){
        User wantedUser = null;
        for (User user : User.users) {
            if (user.getUserName().equals(username)){
                wantedUser = user;
            }
        }
        return wantedUser;
    }
}
