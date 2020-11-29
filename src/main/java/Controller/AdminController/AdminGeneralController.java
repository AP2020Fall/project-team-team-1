package Controller.AdminController;

import Controller.Exception.*;

public class AdminGeneralController {
    /***********************************************EDIT***********************************************/
    public void editField(String info) throws InvalidNameException, InvalidEmailException, InvalidPhoneNumberException, ExistEmailException, InvalidFieldException {
        String[] strings = info.split("\\s");
        Edit.editField(strings[0],strings[1]);
    }

    public void editPassword(String info) throws InvalidPasswordException, ExistPlayerException, WrongPasswordException {
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
    public void sendMassage(String username, String text) throws ExistPlayerException {
        Message.sendMassage(username, text);
    }

    public void showPlayerMassage(String username) throws ExistPlayerException {
        Message.showPlayerMassage(username);
    }

    /***********************************************USERS***********************************************/
    public void showAllUsers() throws ExistPlayerException {
        PlayerLists.showAllUsers();
    }

    public void showUsersByUserName(String userName) throws ExistPlayerException {
        PlayerLists.showUsersByUserName(userName);
    }
    public void showAdminInfo() {
        PlayerLists.showAdminInfo();
    }
        /**********************************************SUGGESTION**********************************************/
    public void addSuggestion(String input) throws ExistPlayerException {
        Suggestion.addSuggestion(input);
    }

    public void showSuggestion() throws ExistSuggestionException {
        Suggestion.showSuggestion();
    }

    public void removeSuggestion(String suggestionID) throws ExistSuggestionException {
        Suggestion.removeSuggestion(suggestionID);
    }


}
