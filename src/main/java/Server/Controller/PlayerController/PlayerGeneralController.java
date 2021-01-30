package Server.Controller.PlayerController;

import Server.Controller.Exception.Plato.*;
import Server.Model.DataBase.DataBase;
import Server.Model.DotsAndBoxesModel.Details;
import Server.Model.PlatoModel.Player;
import Server.Model.PlatoModel.User;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class PlayerGeneralController {
    private static final File playerFile = new File("src\\main\\resources\\DataBase\\Player.json");
    private static final File eventFile = new File("src\\main\\resources\\DataBase\\Event.json");
    private static final File userFile = new File("src\\main\\resources\\DataBase\\User.json");

    /***********************************************EDIT***********************************************/
    public void editField(String input) throws InvalidNameException, InvalidEmailException, InvalidPhoneNumberException, ExistEmailException, InvalidFieldException, IOException {
        String[] strings = input.split("\\s");
        Edit.editField(strings[0], strings[1], strings[2]);
        DataBase.save(Player.players, playerFile);
    }

    public void editPassword(String input) throws InvalidPasswordException, WrongPasswordException, SamePasswordException, IOException, StrongerPasswordException {
        String[] inputSplit = input.split("\\s");
        Edit.editPassword(inputSplit[0], inputSplit[1], inputSplit[2]);
        DataBase.save(Player.players, playerFile);
    }

    public void editBio(String player, String input) throws IOException {
        Edit.editBio(player, input);
        DataBase.save(Player.players, playerFile);
    }

    public void editProfileURL(String username, String input) throws IOException {
        Edit.editProfileURL(username, input);
        DataBase.save(Player.players, playerFile);

    }


    /***********************************************Favorite***********************************************/
    public void addGameToFavoritesGames(String userName, String gameName) throws ExistFavoriteException, InvalidGameNameException, IOException {
        FavoriteGames.addGameToFavoritesGames(userName, gameName);
        DataBase.save(Player.players, playerFile);
    }

    public void RemoveFavoritesGames(String userName, String gameName) throws ExistFavoriteException, IOException, InvalidGameNameException {
        FavoriteGames.removeFavoritesGames(userName, gameName);
        DataBase.save(Player.players, playerFile);
    }

    public String showFavoritesGames(String userName) throws ExistFavoriteException {
        return FavoriteGames.showFavoritesGames(userName);
    }


    /***********************************************FIND***********************************************/
    public Player findByUserName(String userName) {
        return FindPlayerByInfo.findByUserName(userName);
    }

    public Player findByUserID(String userId) {
        return FindPlayerByInfo.findByUserID(userId);
    }

    public Player findByUserEmail(String userEmail) {
        return FindPlayerByInfo.findByUserEmail(userEmail);
    }

    public User findByUserByUsername(String username) {
        return FindPlayerByInfo.findByUserByUsername(username);
    }

    /***********************************************FRIEND***********************************************/
    public void addFriends(String username, String friendUsername) throws ExistFriendException, ExistPlayerException, IOException {
        Friend.addFriends(username, friendUsername);
        DataBase.save(Player.players, playerFile);
    }

    public void acceptRequest(String username, String friendUsername) throws ExistPlayerException, AcceptAndDeclineFriendException, IOException {
        Friend.acceptRequest(username, friendUsername);
        DataBase.save(Player.players, playerFile);
    }

    public void declineRequest(String username, String friendUsername) throws ExistPlayerException, AcceptAndDeclineFriendException, IOException {
        Friend.declineRequest(username, friendUsername);
        DataBase.save(Player.players, playerFile);
    }

    public void removeFriend(String username, String friendUsername) throws ExistFriendException, ExistPlayerException, IOException {
        Friend.removeFriend(username, friendUsername);
        DataBase.save(Player.players, playerFile);
    }

    public String showRequests(String username) throws ExistFriendException {
        return Friend.showRequests(username);
    }

    public String showFriends(String username) throws ExistFriendException {
        return Friend.showFriends(username);
    }

    public String showFriendProfile(String username, String friendUsername) throws ExistFriendException {
        return Friend.showFriendProfile(username, friendUsername);
    }

    public void deleteUser(String input) throws IOException {
        PlayerInfo.deleteUser(input);
        DataBase.save(Player.players, playerFile);
        DataBase.save(User.users, userFile);
    }


    /***********************************************EVENT***********************************************/
    public void joinEvent(String username, String eventID) throws ExistEventException, IOException {
        JoinEvent.joinEvent(username, eventID);
        DataBase.save(Server.Model.PlatoModel.Event.getEvents(), eventFile);
    }

    public String activeEvent(String username) throws ExistEventException, IOException {
        return JoinEvent.activeEvent(username);
    }

    public String eventGameName(String eventId) throws ExistEventException {
        return JoinEvent.eventGameName(eventId);
    }

    public String eventActivation(String eventId) throws ExistEventException {
        return JoinEvent.eventActivation(eventId);
    }

    public String eventScore(String eventId) throws ExistEventException {
        return JoinEvent.eventScore(eventId);
    }

    public String suggestionGameName(String suggestionID) throws ExistSuggestionException {
        return Suggestion.suggestionGameName(suggestionID);
    }

    /***********************************************SHOW INFO***********************************************/
    public String showBasicInformation(String userName) throws ExistPlayerException {
        return PlayerInfo.showBasicInformation(userName);
    }

    public String showUserAge(String userName) throws ExistPlayerException {
        return PlayerInfo.userAge(userName);
    }

    public String showUserGamesStatistics(String userName) throws ExistPlayerException {
        return PlayerInfo.showUserGamesStatistics(userName);
    }

    public String showHistory(String userName) throws ExistPlayerException, ExistPlayerLogException {
        return PlayerInfo.showHistory(userName);
    }

    public void historySaver(LocalDate localDate, String winner, String loser, String gameName) throws IOException {
        Game.historySaver(localDate, winner, loser, gameName);
        DataBase.save(Player.players, playerFile);
    }


    public String showUserLastPlayed(String userName) throws ExistPlayerException, ExistPlayerLogException {
        return PlayerInfo.showUserLastPlayed(userName);
    }

    public String showPoint(String userName) throws ExistPlayerException {
        return PlayerInfo.showPoint(userName);
    }

    public String showUserLog(String userName) throws ExistPlayerLogException, ExistPlayerException {
        return PlayerInfo.showUserLog(userName);
    }


    /***********************************************SHOW LOG***********************************************/
    public String showScoreboardInThisGame(String gameName) throws InvalidGameNameException {
        return PlayerStatusInGame.showScoreboardInThisGame(gameName);
    }

    public String showGameLogInThisGame(String userName, String gameName) throws InvalidGameNameException {
        return PlayerStatusInGame.showGameLogInThisGame(userName, gameName);
    }

    public String showNumberOFWins(String userName, String gameName) throws InvalidGameNameException {
        return PlayerStatusInGame.numberOfWinsInThisGame(userName, gameName);
    }

    public String numberOfLossesInThisGame(String userName, String gameName) throws InvalidGameNameException {
        return PlayerStatusInGame.numberOfLossesInThisGame(userName, gameName);
    }

    public String showNumberOfGamePlayedInThisGame(String userName, String gameName) throws InvalidGameNameException {
        return PlayerStatusInGame.numberOfGamePlayedInThisGame(userName, gameName);
    }

    public String showPlayerPointsInThisGame(String userName, String gameName) throws InvalidGameNameException {
        return PlayerStatusInGame.showPlayerPointsInThisGame(userName, gameName);
    }


    /***********************************************SCORE***********************************************/
    public void findGameForRun(String playerUserName, String gameName, String score) {
        Game.findGameForRun(playerUserName, gameName, score);
    }

    public void giveScoreAndEditPlayerLog(String gameName, String winnerPlayerInput, String loserPlayerInput, long scoreInput) throws IOException {
        Game.giveScoreAndEditPlayerLog(gameName, winnerPlayerInput, loserPlayerInput, scoreInput);
        DataBase.save(Player.players, playerFile);
    }

    /***********************************************SUGGESTION***********************************************/
    public String showSuggestion(String username) throws ExistSuggestionException {
        return Suggestion.showSuggestion(username);
    }

    public void playSuggestedGame(String userName, String suggestionId) throws ExistSuggestionException {
        Suggestion.playSuggestedGame(userName, suggestionId);
    }

    public String findSuggestionBySuggestionIDForGameName(String suggestionID) {
        return Suggestion.findSuggestionBySuggestionIDForGameName(suggestionID);
    }


    /***********************************************MESSAGE***********************************************/
    public String viewBotMessages() {
        return ViewPlatoBotMessages.viewBotMessages();
    }

    /***********************************************Details***********************************************/

    public String dotsDetails() {
        return Details.getDetails();
    }

    public String battleDetails() {
        return Server.Model.BattleSeaModel.Details.getDetails();
    }

    /***********************************************Details***********************************************/
    public void reportsPlayer(String usernameWhoLogin, String usernameWhoReported) throws ExistPlayerException, IOException {
        PlayerInfo.reportsPlayer(usernameWhoLogin, usernameWhoReported);
        DataBase.save(Player.players, playerFile);
    }

    public String rememberPasswordStatus(String userName) {
        Player player = FindPlayerByInfo.findByUserName(userName);
        if (player == null){
            return "No player Found with this Username";
        }
        return String.valueOf(player.isRemember());
    }

    public void setRememberPasswordStatus(String userName, String bool) throws IOException {
        Player player = FindPlayerByInfo.findByUserName(userName);
        player.setRemember(Boolean.parseBoolean(bool));
        DataBase.save(Player.players, playerFile);
    }

    public String getUsernamePassword(String userName) {
        Player player = FindPlayerByInfo.findByUserName(userName);
        return player.getPassword();
    }

    public String getUserProfileUrl(String userName) {
        System.out.println(userName);
        Player player = FindPlayerByInfo.findByUserName(userName);
        System.out.println(player.getProfileURL());
        return player.getProfileURL();
    }

}
