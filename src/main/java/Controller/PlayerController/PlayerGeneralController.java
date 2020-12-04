package Controller.PlayerController;

import Controller.Exception.*;
import Model.PlatoModel.Player;

public class PlayerGeneralController {

    /***********************************************EDIT***********************************************/
    public void editField(String input) throws InvalidNameException, InvalidEmailException, InvalidPhoneNumberException, ExistEmailException, InvalidFieldException {
        String[]strings=input.split("\\s");
        Edit.editField(strings[0],strings[1],strings[2]);
    }

    public void editPassword(String input) throws InvalidPasswordException, WrongPasswordException, SamePasswordException {
        String[] inputSplit = input.split("\\s");
        Edit.editPassword(inputSplit[0], inputSplit[1], inputSplit[2]);
    }


    /***********************************************EDIT***********************************************/
    public void addGameToFavoritesGames(String userName, String gameName) throws ExistFavoriteException, InvalidGameNameException {
        FavoriteGames.addGameToFavoritesGames(userName, gameName);
    }

    public void RemoveFavoritesGames(String userName, String gameName) throws ExistFavoriteException {
        FavoriteGames.removeFavoritesGames(userName, gameName);
    }

    public void showFavoritesGames(String userName) throws ExistFavoriteException {
        FavoriteGames.showFavoritesGames(userName);
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
    public void addFriends(String username, String friendUsername) throws ExistFriendException, ExistPlayerException {
        Friend.addFriends(username, friendUsername);
    }

    public void acceptRequest(String username, String friendUsername) throws ExistPlayerException, AcceptAndDeclineFriendException {
        Friend.acceptRequest(username, friendUsername);
    }

    public void declineRequest(String username, String friendUsername) throws ExistPlayerException, AcceptAndDeclineFriendException {
        Friend.declineRequest(username, friendUsername);
    }

    public void removeFriend(String username, String friendUsername) throws ExistFriendException, ExistPlayerException {
        Friend.removeFriend(username, friendUsername);
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
    public void playEvent() {

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

    public void showHistory(String userName) {
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
        RunGame.findGameForRun(playerUserName, gameName, score);
    }


    /***********************************************EDIT***********************************************/
    public void showSuggestion(String username) throws ExistSuggestionException {
        Suggestion.showSuggestion(username);
    }

    public void playSuggestedGame(String userName, String suggestionId) throws ExistSuggestionException {
        Suggestion.playSuggestedGame(userName, suggestionId);
    }


    /***********************************************EDIT***********************************************/
    public void viewBotMessages(String username) throws ExistPlatoMessageException {
        ViewPlatoBotMessages.viewBotMessages(username);
    }


}
