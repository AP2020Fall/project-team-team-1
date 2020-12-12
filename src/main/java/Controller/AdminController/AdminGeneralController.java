package Controller.AdminController;

import Controller.Exception.Plato.*;
import Controller.PlayerController.PlayerInfo;
import Model.DataBase.DataBase;
import Model.PlatoModel.Admin;
import Model.PlatoModel.Games;
import Model.PlatoModel.Player;

import java.io.File;
import java.io.IOException;

public class AdminGeneralController {
    private static final File adminFile = new File("src\\main\\java\\Model\\Database\\Admin.json");
    private static final File suggestionFile = new File("src\\main\\java\\Model\\Database\\Suggestion.json");
    private static final File messageFile = new File("src\\main\\java\\Model\\Database\\Message.json");
    private static final File eventFile = new File("src\\main\\java\\Model\\Database\\Event.json");
    private static final File playerFile = new File("src\\main\\java\\Model\\Database\\Player.json");
    private static final File gamesFile = new File("src\\main\\java\\Model\\Database\\Games.json");

    /***********************************************EDIT***********************************************/
    public void editField(String info) throws InvalidNameException, InvalidEmailException, InvalidPhoneNumberException, ExistEmailException, InvalidFieldException, IOException {
        String[] strings = info.split("\\s");
        Edit.editField(strings[0], strings[1]);
        DataBase.save(Admin.getAdmins(), adminFile);
        //DataBase.save(User.users,userFile);
    }

    public void editPassword(String info) throws InvalidPasswordException, ExistPlayerException, WrongPasswordException, IOException, StrongerPasswordException {
        String[] strings = info.split("\\s");
        Edit.editPassword(strings[1], strings[2]);
        DataBase.save(Admin.getAdmins(), adminFile);
    }

    /***********************************************EVENT***********************************************/
    public void addEvent(String input) throws StartDatesException, IOException {
        Event.addEvent(input);
        DataBase.save(Admin.getAdmins(), adminFile);
        DataBase.save(Player.getPlayers(), playerFile);
        DataBase.save(Model.PlatoModel.Event.getEvents(), eventFile);
    }

    public void showEvent() throws ExistEventException, IOException {
        Event.showEvent();
    }

    public void editEvent(String input) throws InvalidDateException, InvalidFieldException, StartDatesException, ExistEventException, IOException, NotNullMessageException, InvalidGameNameException {
        Event.editEvent(input);
        DataBase.save(Admin.getAdmins(), adminFile);
        DataBase.save(Player.getPlayers(), playerFile);
        DataBase.save(Model.PlatoModel.Event.getEvents(), eventFile);
    }

    public void removeEventByAdminFromView(String eventID) throws ExistEventException, IOException {
        Event.removeEventByAdminFromView(eventID);
        DataBase.save(Admin.getAdmins(), adminFile);
        DataBase.save(Player.getPlayers(), playerFile);
        DataBase.save(Model.PlatoModel.Event.getEvents(), eventFile);
    }

    /***********************************************MESSAGE***********************************************/
    public void sendMassageString(String text) throws IOException, NotNullMessageException {
        Message.sendMassage(text);
        //DataBase.save(Model.PlatoModel.Message.getMessages(),messageFile);
        Model.PlatoModel.Message.saveInJsonFile(Model.PlatoModel.Message.getMessages(), messageFile);
    }

    public void showPlayerMassage() throws ExistPlayerException {
        Message.showPlayerMassage();
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
    public void addSuggestion(String input) throws ExistPlayerException, IOException {
        Suggestion.addSuggestion(input);
        DataBase.save(Player.getPlayers(), playerFile);
        DataBase.save(Model.PlatoModel.Suggestion.getAllSuggestions(), suggestionFile);
    }

    public void showSuggestion() throws ExistSuggestionException {
        Suggestion.showSuggestion();
    }

    public void removeSuggestion(String suggestionID) throws ExistSuggestionException, IOException {
        Suggestion.removeSuggestion(suggestionID);
        DataBase.save(Player.getPlayers(), playerFile);
        DataBase.save(Model.PlatoModel.Suggestion.getAllSuggestions(), suggestionFile);
    }

    /**********************************************Games**********************************************/
    public void changeGameName(String gameID, String name) throws InvalidGameID, IOException {

        Game.changeGameName(gameID, name);
        DataBase.save(Games.getGames(), gamesFile);

    }

    public void deActiveGame(String gameID) throws InvalidGameID, GameActivation, IOException {

        Game.deActiveGame(gameID);
        DataBase.save(Games.getGames(), gamesFile);
    }

    public void activeGame(String gameID) throws InvalidGameID, GameActivation, IOException {

        Game.activeGame(gameID);
        DataBase.save(Games.getGames(), gamesFile);
    }

    public void activeMaintenanceMode(String gameID) throws InvalidGameID, GameMaintenance, IOException {

        Game.activeMaintenanceMode(gameID);
        DataBase.save(Games.getGames(), gamesFile);
    }

    public void deActiveMaintenanceMode(String gameID) throws InvalidGameID, GameMaintenance, IOException {

        Game.deActiveMaintenanceMode(gameID);
        DataBase.save(Games.getGames(), gamesFile);
    }

    public void setDetails(String gameName, String string) throws IOException {

        Game.setDetails(gameName, string);

    }

    /**********************************************Reports**********************************************/
    public void showReportListOfPlayer(String username) throws EmptyReportsList {
        PlayerInfo.showReportsList(username);

    }

    public void banPlayer(String username) throws AlreadyBan, IOException {
        PlayerInfo.banPlayer(username);
        DataBase.save(Player.getPlayers(), playerFile);

    }

    public void unBanPlayer(String username) throws ItsNotBan, AlreadyBan, IOException {
        PlayerInfo.unBanPlayer(username);
        DataBase.save(Player.getPlayers(), playerFile);

    }
    public void showBanPlayers() {
        PlayerInfo.showBanPlayers();
    }
        /*******************************************GameName************************************************/
    // first -----> BattleShip
    // index ----> 0
    public String firstGameNameGetter(){
        return Games.getGames().get(0).getGameName();
    }

    // second -----> BattleShip
    // index ----> 1
    public String secondGameNameGetter(){
        return Games.getGames().get(1).getGameName();
    }
}
