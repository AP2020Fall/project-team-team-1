package Controller.PlayerController;

import Controller.CompetencyController.Existence;
import Controller.Exception.Plato.*;
import Model.DataBase.DataBase;
import Model.PlatoModel.Player;
import Model.PlatoModel.User;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class PlayerInfo {

    public static String showBasicInformation(String userName) throws ExistPlayerException {
        StringBuilder showBasicInformation = new StringBuilder();

        Player player = FindPlayerByInfo.findByUserName(userName);

        if (player == null)
            throw new ExistPlayerException(userName," isn't exist please make sure about Username! ");

        showBasicInformation.append(player.getEmail()).append("$").append(player.getName()).append("$").append(player.getLastName()).append("$").append(player.getUserName()).append("$").append(player.getPhoneNum()).append("$").append(player.getBio()).append("$").append(player.getProfileURL()).append("$");
        return String.valueOf(showBasicInformation);
    }

    public static String userAge(String userName) throws ExistPlayerException {
        Player player = FindPlayerByInfo.findByUserName(userName);
        if (player == null)
            throw new ExistPlayerException(userName," isn't exist please make sure about Username! ");

        LocalDate registerDate = player.getRegisterDate();
        LocalDate now = LocalDate.now();
        Long age = ChronoUnit.DAYS.between(registerDate,now);
        return String.valueOf(age);

    }

    public static String showUserGamesStatistics(String userName) throws ExistPlayerException {
        StringBuilder showUserGamesStatistics = new StringBuilder();
        Player player = FindPlayerByInfo.findByUserName(userName);

        if (player == null)
            throw new ExistPlayerException(userName," isn't exist please make sure about Username! ");

        float wins = player.getPlayerLog().get(0).getNumberOfWins() + player.getPlayerLog().get(1).getNumberOfWins() ;
        float loses = player.getPlayerLog().get(0).getNumberOfLoses() + player.getPlayerLog().get(1).getNumberOfLoses();
        float numberOfPlayed = player.getPlayerLog().get(0).getNumberOfGamePlayed()+player.getPlayerLog().get(1).getNumberOfGamePlayed();
        float winPercentage = (wins/numberOfPlayed)*100;

        showUserGamesStatistics.append("Your age in plato : ").append(userAge(userName)).append('\n').append("Number of Friends: ").append(player.getFriends().size()).append('\n').append("Wins : ").append(wins).append('\n').append("Lose : ").append(loses).append('\n').append("Number of match : ").append(numberOfPlayed).append('\n').append("Win Percentage : %").append(winPercentage).append("$");
        return String.valueOf(showUserGamesStatistics);
    }

    public static String showUserLastPlayed(String userName) throws ExistPlayerException, ExistPlayerLogException {
        Player player = FindPlayerByInfo.findByUserName(userName);
        if (player.getLastPlayed().isEmpty()){
            throw new ExistPlayerLogException("There is no log for this player yet!");
        }
        if (player == null)
            throw new ExistPlayerException(userName," isn't exist please make sure about Username! ");

        return player.getLastPlayed().get(player.getLastPlayed().size()-1);
    }
    public static String showUserLog(String userName) throws ExistPlayerException, ExistPlayerLogException {
        StringBuilder show = new StringBuilder();

        Player player = FindPlayerByInfo.findByUserName(userName);
        if (player.getLastPlayed().isEmpty()){
            throw new ExistPlayerLogException("There is no log for this player yet!");
        }
        if (player == null)
            throw new ExistPlayerException(userName," isn't exist please make sure about Username! ");

        for (String s : player.getLastPlayed()) {
            show.append(s).append("$");
        }

        return String.valueOf(show);
    }

    public static String showPoint (String userName) throws ExistPlayerException {
        Player player = FindPlayerByInfo.findByUserName(userName);
        if (player == null)
            throw new ExistPlayerException(userName," isn't exist please make sure about Username! ");

        long level = player.getPlayerLog().get(0).getTakenScore()+player.getPlayerLog().get(1).getTakenScore();
//        level = level/10 ;

        return String.valueOf(level);
    }
    public static String showHistory (String userName) throws ExistPlayerException, ExistPlayerLogException {
        StringBuilder showHistory = new StringBuilder();

        Player player = FindPlayerByInfo.findByUserName(userName);
        if (player.getLastPlayed().isEmpty()){
            throw new ExistPlayerLogException("There is no history for this player yet!");
        }
        if (player == null)
            throw new ExistPlayerException(userName," isn't exist please make sure about Username! ");

        int counter = player.getLastPlayed().size();
        for (int i = player.getLastPlayed().size() -1 ; 0 <= i; i--) {
            showHistory.append(counter).append(". ").append(player.getLastPlayed().get(i)).append("$");
            counter--;
        }
        return String.valueOf(showHistory);
    }

    public static String showReportsList(String username) throws EmptyReportsList, InvalidUserNameException {
        StringBuilder showReportsList = new StringBuilder();

        Player player = FindPlayerByInfo.findByUserName(username);
        if (player==null){
            throw new InvalidUserNameException("This user name is not exist! ");
        }
        if (player.getPlayersWhoReportMe().isEmpty())
            throw new EmptyReportsList("No one Reports this Username");

        showReportsList.append("Players who reported \"").append(username).append("\" :").append("$");


        for (String reports : player.getPlayersWhoReportMe()) {
            showReportsList.append(reports).append("$");
        }
        return String.valueOf(showReportsList);
    }

    public static void reportsPlayer(String usernameWhoLogin,String usernameWhoReported) throws ExistPlayerException {
        Player player = FindPlayerByInfo.findByUserName(usernameWhoReported);
        if (player==null){
            throw new ExistPlayerException("This username does not exist");
        }
        player.getPlayersWhoReportMe().add(usernameWhoLogin);

    }

    public static void banPlayer(String username) throws AlreadyBan {
        Player player = FindPlayerByInfo.findByUserName(username);

        if (!player.isActivation())
            throw new AlreadyBan("this Username Already BanExceptionForLogin");

        player.setActivation(false);
    }
    public static String showBanPlayers(){
        StringBuilder showBanPlayers = new StringBuilder();
        for (Player player : Player.getPlayers()) {
            if (!player.isActivation()){
                showBanPlayers.append("players who reports \"").append(player.getUserName()).append("\" : 👇").append("$");
                int counter = 1;
                for (String reports : player.getPlayersWhoReportMe()) {
                    showBanPlayers.append(counter).append(". ").append(reports).append("$");
                    counter ++;
                }
            }
        }
        return String.valueOf(showBanPlayers);
    }

    public static void unBanPlayer(String username) throws ItsNotBan {
        Player player = FindPlayerByInfo.findByUserName(username);

        if (player.isActivation())
            throw new ItsNotBan("this Username isn't ban , its already active");

        player.setActivation(true);
    }
    public static String playerActivation(String username){
        return String.valueOf(Existence.checkPlayerActivation(username));
    }

    public static void deleteUser(String input)  {
        Player player = FindPlayerByInfo.findByUserName(input);
        ArrayList<String> forDelete = new ArrayList<>();
        for (String friend : player.getFriends()) {
            forDelete.add(friend);
        }
        for (String s : forDelete) {
            try {
                Friend.removeFriend(player.getUserName(),s);
            } catch (ExistFriendException e) {
                e.printStackTrace();
            } catch (ExistPlayerException e) {
                e.printStackTrace();
            }
        }
        User user = FindPlayerByInfo.findByUserByUsername(input);
        Player.players.remove(player);
        User.users.remove(user);



//        User.saveInJsonFile();
//        Player.saveInJsonFile();
    }

}
