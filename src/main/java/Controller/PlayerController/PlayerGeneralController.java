package Controller.PlayerController;

import Controller.Exception.Plato.*;
import Model.DataBase.DataBase;
import Model.DotsAndBoxesModel.Details;
import Model.PlatoModel.Player;

import java.io.File;
import java.io.IOException;

public class PlayerGeneralController {
    private static final File playerFile = new File("src\\main\\java\\Model\\Database\\Player.json");
    //private static final File userFile = new File("src\\main\\java\\Model\\Database\\User.json");
    /***********************************************EDIT***********************************************/
    public void editField(String input) throws InvalidNameException, InvalidEmailException, InvalidPhoneNumberException, ExistEmailException, InvalidFieldException, IOException {
        String[]strings=input.split("\\s");
        Edit.editField(strings[0],strings[1],strings[2]);
        DataBase.save(Player.players,playerFile);
    }

    public void editPassword(String input) throws InvalidPasswordException, WrongPasswordException, SamePasswordException, IOException, StrongerPasswordException {
        String[] inputSplit = input.split("\\s");
        Edit.editPassword(inputSplit[0], inputSplit[1], inputSplit[2]);
        DataBase.save(Player.players,playerFile);
        //DataBase.save(User.users,userFile);
    }


    /***********************************************EDIT***********************************************/
    public void addGameToFavoritesGames(String userName, String gameName) throws ExistFavoriteException, InvalidGameNameException, IOException {
        FavoriteGames.addGameToFavoritesGames(userName, gameName);
        DataBase.save(Player.players,playerFile);
    }

    public void RemoveFavoritesGames(String userName, String gameName) throws ExistFavoriteException, IOException {
        FavoriteGames.removeFavoritesGames(userName, gameName);
        DataBase.save(Player.players,playerFile);
    }

    public void showFavoritesGames(String userName) throws ExistFavoriteException, IOException {
        FavoriteGames.showFavoritesGames(userName);
        DataBase.save(Player.players,playerFile);
    }


    /***********************************************EDIT***********************************************/
    public Player findByUserName(String userName) {
        return FindPlayerByInfo.findByUserName(userName);
    }

    public Player findByUserID(String userId) {
        return FindPlayerByInfo.findByUserID(userId);
    }

    public Player findByUserEmail(String userEmail) {
        return FindPlayerByInfo.findByUserEmail(userEmail);
    }


    /***********************************************EDIT***********************************************/
    public void addFriends(String username, String friendUsername) throws ExistFriendException, ExistPlayerException, IOException {
        Friend.addFriends(username, friendUsername);
        DataBase.save(Player.players,playerFile);
    }

    public void acceptRequest(String username, String friendUsername) throws ExistPlayerException, AcceptAndDeclineFriendException, IOException {
        Friend.acceptRequest(username, friendUsername);
        DataBase.save(Player.players,playerFile);
    }

    public void declineRequest(String username, String friendUsername) throws ExistPlayerException, AcceptAndDeclineFriendException, IOException {
        Friend.declineRequest(username, friendUsername);
        DataBase.save(Player.players,playerFile);
    }

    public void removeFriend(String username, String friendUsername) throws ExistFriendException, ExistPlayerException, IOException {
        Friend.removeFriend(username, friendUsername);
        DataBase.save(Player.players,playerFile);
    }

    public void showRequests(String username) throws ExistFriendException {
        Friend.showRequests(username);
    }

    public void showFriends(String username) throws ExistFriendException {
        Friend.showFriends(username);
    }

    public void showFriendProfile(String username, String friendUsername) throws ExistFriendException {
        Friend.showFriendProfile(username, friendUsername);
    }


    /***********************************************EDIT***********************************************/
    public void playEvent(String username , String eventID) {

    }


    /***********************************************EDIT***********************************************/
    public void showBasicInformation(String userName) throws ExistPlayerException {
        PlayerInfo.showBasicInformation(userName);
    }

    public void showUserAge(String userName) throws ExistPlayerException {
        PlayerInfo.showUserAge(userName);
    }

    public void showUserGamesStatistics(String userName) throws ExistPlayerException {
        PlayerInfo.showUserGamesStatistics(userName);
    }

    public void showHistory(String userName) throws ExistPlayerException {
        PlayerInfo.showHistory(userName);
    }

    public void showUserLastPlayed(String userName) throws ExistPlayerException {
        PlayerInfo.showUserLastPlayed(userName);
    }

    public void showPoint(String userName) throws ExistPlayerException {
        PlayerInfo.showPoint(userName);
    }


    /***********************************************EDIT***********************************************/
    public void showScoreboardInThisGame(String gameName) throws InvalidGameNameException {
        PlayerStatusInGame.showScoreboardInThisGame(gameName);
    }

    public void showGameLogInThisGame(String userName, String gameName) throws InvalidGameNameException {
        PlayerStatusInGame.showGameLogInThisGame(userName, gameName);
    }

    public void showNumberOFWins(String userName, String gameName) throws InvalidGameNameException {
        PlayerStatusInGame.numberOfWinsInThisGame(userName, gameName);
    }

    public void showNumberOfGamePlayedInThisGame(String userName, String gameName) throws InvalidGameNameException {
        PlayerStatusInGame.numberOfGamePlayedInThisGame(userName, gameName);
    }

    public void showPlayerPointsInThisGame(String userName, String gameName) throws InvalidGameNameException {
        PlayerStatusInGame.showPlayerPointsInThisGame(userName, gameName);
    }


    /***********************************************EDIT***********************************************/
    public void findGameForRun(String playerUserName, String gameName, String score) {
        Game.findGameForRun(playerUserName, gameName, score);
    }
    public void giveScoreAndEditPlayerLog(String gameName,String winnerPlayerInput,String loserPlayerInput,long scoreInput) {
        Game.giveScoreAndEditPlayerLog(gameName, winnerPlayerInput, loserPlayerInput, scoreInput);
    }

    /***********************************************EDIT***********************************************/
    public void showSuggestion(String username) throws ExistSuggestionException {
        Suggestion.showSuggestion(username);
    }

    public void playSuggestedGame(String userName, String suggestionId) throws ExistSuggestionException {
        Suggestion.playSuggestedGame(userName, suggestionId);
    }


    /***********************************************EDIT***********************************************/
    public void viewBotMessages() {
        ViewPlatoBotMessages.viewBotMessages();
    }

    /***********************************************Details***********************************************/

    public String dotsDetails(){
        return Details.getDetails();
    }
    public String battleDetails(){
        return Model.BattleSeaModel.Details.getDetails();
    }

}
