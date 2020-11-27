package Controller.PlayerController;

import Controller.Exception.InvalidNameException;
import Model.PlatoModel.Player;

public class PlayerGeneralController {

    /***********************************************EDIT***********************************************/
    public void editField(String username, String field, String input) throws InvalidNameException {
        Edit.editField(username, field, input);
    }

    public void editPassword(Player player, String oldPassword, String newPassword) {
        Edit.editPassword(player, oldPassword, newPassword);
    }
    /***************************************************************************************************/


    /***********************************************EDIT***********************************************/
    public void addGameToFavoritesGames(String userName, String gameName) {
        FavoriteGames.addGameToFavoritesGames(userName, gameName);
    }

    public void RemoveFavoritesGames(String userName, String gameName) {
        FavoriteGames.RemoveFavoritesGames(userName, gameName);
    }

    public void showFavoritesGames(String userName) {
        FavoriteGames.showFavoritesGames(userName);
    }
    /***************************************************************************************************/


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
    /***************************************************************************************************/


    /***********************************************EDIT***********************************************/
    public void addFriends(String username, String friendUsername) {
        Friend.addFriends(username, friendUsername);
    }

    public void acceptRequest(String username, String friendUsername) {
        Friend.acceptRequest(username, friendUsername);
    }

    public void declineRequest(String username, String friendUsername) {
        Friend.declineRequest(username, friendUsername);
    }

    public void removeFriend(String username, String friendUsername) {
        Friend.removeFriend(username, friendUsername);
    }

    public void showRequests(String username) {
        Friend.showRequests(username);
    }

    public void showFriends(String username) {
        Friend.showFriends(username);
    }

    public void showFriendProfile(String username, String friendUsername) {
        Friend.showFriendProfile(username, friendUsername);
    }
    /***************************************************************************************************/


    /***********************************************EDIT***********************************************/
    public void playEvent() {

    }
    /***************************************************************************************************/


    /***********************************************EDIT***********************************************/
    public void showBasicInformation(String userName) {
        PlayerInfo.showBasicInformation(userName);
    }

    public void showUserAge(String userName) {
        PlayerInfo.showUserAge(userName);
    }

    public void showUserGamesStatistics(String userName, String gameName) {
        PlayerInfo.showUserGamesStatistics(userName, gameName);
    }

    public void showUserLastPlayed(String userName) {
        PlayerInfo.showUserLastPlayed(userName);
    }

    public void showPoint(String userName) {
        PlayerInfo.showPoint(userName);
    }
    /***************************************************************************************************/


    /***********************************************EDIT***********************************************/
    public void showScoreboardInThisGame(String gameName) {
        PlayerStatusInGame.showScoreboardInThisGame(gameName);
    }

    public void showGameLogInThisGame(String userName, String gameName) {
        PlayerStatusInGame.showGameLogInThisGame(userName, gameName);
    }

    public void numberOfWinsInThisGame(String userName, String gameName) {
        PlayerStatusInGame.numberOfWinsInThisGame(userName, gameName);
    }

    public void numberOfGamePlayedInThisGame(String userName, String gameName) {
        PlayerStatusInGame.numberOfGamePlayedInThisGame(userName, gameName);
    }

    public void showPlayerPointsInThisGame(String userName, String gameName) {
        PlayerStatusInGame.showPlayerPointsInThisGame(userName, gameName);
    }
    /***************************************************************************************************/


    /***********************************************EDIT***********************************************/
    public void findGameForRun(String playerUserName, String gameName, String score) {
        RunGame.findGameForRun(playerUserName, gameName, score);
    }
    /***************************************************************************************************/


    /***********************************************EDIT***********************************************/
    public void showSuggestion(String username) {
        Suggestion.showSuggestion(username);
    }

    public void playSuggestedGame(String userName, String suggestionId) {
        Suggestion.playSuggestedGame(userName, suggestionId);
    }
    /***************************************************************************************************/


    /***********************************************EDIT***********************************************/
    public void viewBotMessages(String username) {
        ViewPlatoBotMessages.viewBotMessages(username);
    }
    /***************************************************************************************************/


}
