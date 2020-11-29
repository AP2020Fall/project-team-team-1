package Controller.PlayerController;

import Model.PlatoModel.Player;
import Model.PlatoModel.PlayerLog;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PlayerInfo {

    public static void showBasicInformation(String userName){
        Player player = FindPlayerByInfo.findByUserName(userName);
        System.out.println("Email : " + player.getEmail());
        System.out.println("Name : " + player.getName());
        System.out.println("Last name : " + player.getLastName());
        System.out.println("User name : " + player.getUserName());
        System.out.println("Phone number : " + player.getPhoneNum());
    }
    public static void showUserAge(String userName) {
        Player player = FindPlayerByInfo.findByUserName(userName);
        LocalDate registerDate = player.getRegisterDate();
        LocalDate now = LocalDate.now();
        Long age = ChronoUnit.DAYS.between(registerDate,now);
        System.out.println(age);

    }
    public static void showUserGamesStatistics(String userName) {
        Player player = FindPlayerByInfo.findByUserName(userName);
        int wins = player.getPlayerLog().get(0).getNumberOfWins() + player.getPlayerLog().get(1).getNumberOfWins() ;
        int loses = player.getPlayerLog().get(0).getNumberOfLoses() + player.getPlayerLog().get(1).getNumberOfLoses();
        int numberOfPlayed = player.getPlayerLog().get(0).getNumberOfGamePlayed()+player.getPlayerLog().get(1).getNumberOfGamePlayed();
        float winPercentage = wins/numberOfPlayed;
        System.out.print("age: ");
        showUserAge(userName);
        System.out.println("number of Friends: "+ player.getFriends().size());
        System.out.println("Wins : " + wins);
        System.out.println("Lose : " + loses);
        System.out.println("Number of match : " + numberOfPlayed);
        System.out.println("Win Percentage : "+ winPercentage);

    }
    public static void showUserLastPlayed(String userName) {
        Player player = FindPlayerByInfo.findByUserName(userName);
        System.out.println(player.getLastPlayed());
    }
    public static void showPoint (String userName) {
        Player player = FindPlayerByInfo.findByUserName(userName);
        long score = player.getPlayerLog().get(0).getTakenScore() + player.getPlayerLog().get(1).getTakenScore();
        System.out.println("Level : " + score);
        //todo score? level? point?
    }
    public static void showHistory (String userName){

    }
}
