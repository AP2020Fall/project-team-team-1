package Controller.PlayerController;

import Controller.Exception.Plato.*;
import Model.PlatoModel.Player;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PlayerInfo {

    public static void showBasicInformation(String userName) throws ExistPlayerException {
        Player player = FindPlayerByInfo.findByUserName(userName);

        if (player == null)
            throw new ExistPlayerException(userName," isn't exist please make sure about Username! ");

        System.out.println("Email : " + player.getEmail());
        System.out.println("Name : " + player.getName());
        System.out.println("Last name : " + player.getLastName());
        System.out.println("User name : " + player.getUserName());
        System.out.println("Phone number : " + player.getPhoneNum());
    }
    public static void showUserAge(String userName) throws ExistPlayerException {
        Player player = FindPlayerByInfo.findByUserName(userName);
        if (player == null)
            throw new ExistPlayerException(userName," isn't exist please make sure about Username! ");

        LocalDate registerDate = player.getRegisterDate();
        LocalDate now = LocalDate.now();
        Long age = ChronoUnit.DAYS.between(registerDate,now);
        System.out.println(age);

    }
    public static void showUserGamesStatistics(String userName) throws ExistPlayerException {
        Player player = FindPlayerByInfo.findByUserName(userName);

        if (player == null)
            throw new ExistPlayerException(userName," isn't exist please make sure about Username! ");

        float wins = player.getPlayerLog().get(0).getNumberOfWins() + player.getPlayerLog().get(1).getNumberOfWins() ;
        float loses = player.getPlayerLog().get(0).getNumberOfLoses() + player.getPlayerLog().get(1).getNumberOfLoses();
        float numberOfPlayed = player.getPlayerLog().get(0).getNumberOfGamePlayed()+player.getPlayerLog().get(1).getNumberOfGamePlayed();
        float winPercentage = (wins/numberOfPlayed)*100;

        System.out.print("Your age in plato : ");
        showUserAge(userName);

        System.out.println("Number of Friends: "+ player.getFriends().size());
        System.out.println("Wins : " + wins);
        System.out.println("Lose : " + loses);
        System.out.println("Number of match : " + numberOfPlayed);
        System.out.println("Win Percentage : %"+ winPercentage);

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
    public static void showPoint (String userName) throws ExistPlayerException {
        Player player = FindPlayerByInfo.findByUserName(userName);
        if (player == null)
            throw new ExistPlayerException(userName," isn't exist please make sure about Username! ");

        long level = player.getPlayerLog().get(0).getTakenScore()+player.getPlayerLog().get(1).getTakenScore();
        level = level/10 ;

        System.out.println("Level : " + level);
    }
    public static void showHistory (String userName) throws ExistPlayerException, ExistPlayerLogException {
        Player player = FindPlayerByInfo.findByUserName(userName);
        if (player.getLastPlayed().isEmpty()){
            throw new ExistPlayerLogException("There is no history for this player yet!");
        }
        if (player == null)
            throw new ExistPlayerException(userName," isn't exist please make sure about Username! ");

        int counter = player.getLastPlayed().size();
        for (int i = player.getLastPlayed().size() -1 ; 0 <= i; i--) {
            System.out.println(counter+". "+player.getLastPlayed().get(i));
            counter--;
        }
    }

    public static void showReportsList(String username) throws EmptyReportsList {
        Player player = FindPlayerByInfo.findByUserName(username);
        if (player.getPlayersWhoReportMe().isEmpty())
            throw new EmptyReportsList("No one Reports this Username");

        for (String reports : player.getPlayersWhoReportMe()) {
            System.out.println(reports);
        }

    }

    public static void banPlayer(String username) throws AlreadyBan {
        Player player = FindPlayerByInfo.findByUserName(username);

        if (!player.isActivation())
            throw new AlreadyBan("this Username Already Ban");

        player.setActivation(false);
    }

    public static void unBanPlayer(String username) throws AlreadyBan, ItsNotBan {
        Player player = FindPlayerByInfo.findByUserName(username);

        if (player.isActivation())
            throw new ItsNotBan("this Username isn't ban , its already active");

        player.setActivation(true);
    }

//    public static void addHistory (String userName,String history) throws ExistPlayerException, ExistPlayerLogException {
//        Player player = FindPlayerByInfo.findByUserName(userName);
//
//        if (player == null)
//            throw new ExistPlayerException(userName," isn't exist please make sure about Username! ");
//
//        player.getLastPlayed().add(history);
//    }
}
