package Controller.AdminController;

import Controller.Exception.*;

public class AdminGeneralController {
    /***********************************************EDIT***********************************************/
    public void editField(String info) throws InvalidNameException, InvalidEmailException, InvalidPhoneNumberException {
        String[] strings = info.split("\\s");
        Edit.editField(strings[0],strings[1]);
    }

    public void editPassword(String info) throws InvalidPasswordException {
        String[] strings = info.split("\\s");
        Edit.editPassword(strings[1],strings[2]);
    }

    /***********************************************EVENT***********************************************/
    public void addEvent(String input) throws StartDatesException {
        Event.addEvent(input);

    }

    public void showEvent() throws ExistEventException {
        Event.showEvent();
    }

    public void editEvent(String input) throws InvalidDateException, InvalidFieldException, StartDatesException, ExistEventException {
        Event.editEvent(input);
    }

    /***********************************************MESSAGE***********************************************/
    public void sendMassage(String username, String text) {
        Message.sendMassage(username, text);
    }

    public void showPlayerMassage(String username) {
        Message.showPlayerMassage(username);
    }

    /***********************************************USERS***********************************************/
    public void showAllUsers() {
        PlayerLists.showAllUsers();
    }

    public void showUsersByUserName(String userName) {
        PlayerLists.showUsersByUserName(userName);
    }
    public void showAdminInfo() {
        PlayerLists.showAdminInfo();
    }
        /**********************************************SUGGESTION**********************************************/
    public void addSuggestion(String input) throws ExistPlayerException {
        Suggestion.addSuggestion(input);
    }

    public void showSuggestion() {
        Suggestion.showSuggestion();
    }

    public void removeSuggestion(String suggestionID) {
        Suggestion.removeSuggestion(suggestionID);
    }


}
