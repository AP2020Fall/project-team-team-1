package Controller.AdminController;

import Controller.Exception.*;

public class AdminGeneralController {
    /***********************************************EDIT***********************************************/
    public void editField(String field, String input) throws InvalidNameException, InvalidEmailException, InvalidPhoneNumberException {
        Edit.editField(field, input);
    }

    public void editPassword(String oldPassword, String newPassword) throws InvalidPasswordException {
        Edit.editPassword(oldPassword, newPassword);
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
