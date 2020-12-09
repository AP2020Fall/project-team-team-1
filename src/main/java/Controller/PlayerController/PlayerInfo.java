package Controller.PlayerController;

import Controller.Exception.ExistPlayerException;
import Model.PlatoModel.Player;
import Model.PlatoModel.PlayerLog;

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

        int wins = player.getPlayerLog().get(0).getNumberOfWins() + player.getPlayerLog().get(1).getNumberOfWins() ;
        int loses = player.getPlayerLog().get(0).getNumberOfLoses() + player.getPlayerLog().get(1).getNumberOfLoses();
        int numberOfPlayed = player.getPlayerLog().get(0).getNumberOfGamePlayed()+player.getPlayerLog().get(1).getNumberOfGamePlayed();
        float winPercentage = (wins/numberOfPlayed)*100;

        System.out.print("age: ");
        showUserAge(userName);

        System.out.println("number of Friends: "+ player.getFriends().size());
        System.out.println("Wins : " + wins);
        System.out.println("Lose : " + loses);
        System.out.println("Number of match : " + numberOfPlayed);
        System.out.println("Win Percentage : %"+ winPercentage);

    }
    public static void showUserLastPlayed(String userName) throws ExistPlayerException {
        Player player = FindPlayerByInfo.findByUserName(userName);

        if (player == null)
            throw new ExistPlayerException(userName," isn't exist please make sure about Username! ");
        //todo if playerLog id empty
        System.out.println(player.getPlayerLog().get(player.getPlayerLog().size()));
    }
    public static void showPoint (String userName) throws ExistPlayerException {
        Player player = FindPlayerByInfo.findByUserName(userName);
        if (player == null)
            throw new ExistPlayerException(userName," isn't exist please make sure about Username! ");

        long score = player.getPlayerLog().get(0).getTakenScore()+player.getPlayerLog().get(1).getTakenScore();
        score = score/100 ;

        System.out.println("point : " + score);
    }
    public static void showHistory (String userName) throws ExistPlayerException {
        Player player = FindPlayerByInfo.findByUserName(userName);
        if (player == null)
            throw new ExistPlayerException(userName," isn't exist please make sure about Username! ");
        //todo if playerLog id empty
        int counter = player.getPlayerLog().size()+1;
        for (int i = player.getPlayerLog().size(); 0 < i; i--) {
            System.out.println(counter+". "+player.getPlayerLog().get(i));
            counter--;
        }
    }
}
