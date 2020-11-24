package Controller.PlayerController;

import Model.PlatoModel.Player;

public class PlayerGeneralController {

    /***********************************************EDIT***********************************************/
    public void editField(String username, String field, String input) {
        Edit.editField(username, field, input);
    }

    public static void editPassword(Player player, String oldPassword, String newPassword) {
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
    public static void showBasicInformation(String userName) {
        PlayerInfo.showBasicInformation(userName);
    }

    public static void showUserAge(String userName) {
        PlayerInfo.showUserAge(userName);
    }

    public static void showUserGamesStatistics(String userName, String gameName) {
        PlayerInfo.showUserGamesStatistics(userName, gameName);
    }

    public static void showUserLastPlayed(String userName) {
        PlayerInfo.showUserLastPlayed(userName);
    }

    public static void showPoint(String userName) {
        PlayerInfo.showPoint(userName);
    }
    /***************************************************************************************************/


    /***********************************************EDIT***********************************************/
    public static void showScoreboardInThisGame(String gameName) {
        PlayerStatusInGame.showScoreboardInThisGame(gameName);
    }

    public static void showGameLogInThisGame(String userName, String gameName) {
        PlayerStatusInGame.showGameLogInThisGame(userName, gameName);
    }

    public static void numberOfWinsInThisGame(String userName, String gameName) {
        PlayerStatusInGame.numberOfWinsInThisGame(userName, gameName);
    }

    public static void numberOfGamePlayedInThisGame(String userName, String gameName) {
        PlayerStatusInGame.numberOfGamePlayedInThisGame(userName, gameName);
    }

    public static void showPlayerPointsInThisGame(String userName, String gameName) {
        PlayerStatusInGame.showPlayerPointsInThisGame(userName, gameName);
    }
    /***************************************************************************************************/


    /***********************************************EDIT***********************************************/
    public static void findGameForRun(String playerUserName, String gameName, String score) {
        RunGame.findGameForRun(playerUserName, gameName, score);
    }
    /***************************************************************************************************/


    /***********************************************EDIT***********************************************/
    public static void showSuggestion(String username) {
        Suggestion.showSuggestion(username);
    }

    public static void playSuggestedGame(String userName, String suggestionId) {
        Suggestion.playSuggestedGame(userName, suggestionId);
    }
    /***************************************************************************************************/


    /***********************************************EDIT***********************************************/
    public static void viewBotMessages(String username) {
        ViewPlatoBotMessages.viewBotMessages(username);
    }
    /***************************************************************************************************/


}
