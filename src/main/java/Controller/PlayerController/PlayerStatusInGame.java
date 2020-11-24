package Controller.PlayerController;

import Model.PlatoModel.Player;

public class PlayerStatusInGame {


//    public static void gamesHistoryInThisGame (String userName , String gameName){
//
//    }
    public static void showScoreboardInThisGame (String userName , String gameName){

    }
    public static void showGameLogInThisGame (String userName , String gameName){
        Player player = FindPlayerByInfo.findByUserName(userName);
        int index = 0 ;
        if (gameName.equalsIgnoreCase("DotsAndBoxes")){
            index = 0 ;
        }
        else if (gameName.equalsIgnoreCase("BattleSea")){
            index = 1 ;
        }
        else {
            System.out.println("not valid");
            return;
        }
        int wins = player.getPlayerLog().get(index).getNumberOfWins();
        int loses = player.getPlayerLog().get(index).getNumberOfLoses();
        int numberOfPlayed = player.getPlayerLog().get(index).getNumberOfGamePlayed();
        System.out.println("Wins : " + wins);
        System.out.println("Lose : " + loses);
        System.out.println("Number of match : " + numberOfPlayed);
    }
    public static void numberOfWinsInThisGame (String userName , String gameName){
        Player player = FindPlayerByInfo.findByUserName(userName);
        int index = 0 ;
        if (gameName.equalsIgnoreCase("DotsAndBoxes")){
            index = 0 ;
        }
        else if (gameName.equalsIgnoreCase("BattleSea")){
            index = 1 ;
        }
        else {
            System.out.println("not valid");
            return;
        }
        int wins = player.getPlayerLog().get(index).getNumberOfWins();
        System.out.println("Wins : " + wins);
    }
    public static void numberOfGamePlayedInThisGame (String userName , String gameName){
        Player player = FindPlayerByInfo.findByUserName(userName);
        int index = 0 ;
        if (gameName.equalsIgnoreCase("DotsAndBoxes")){
            index = 0 ;
        }
        else if (gameName.equalsIgnoreCase("BattleSea")){
            index = 1 ;
        }
        else {
            System.out.println("not valid");
            return;
        }
        int numberOfPlayed = player.getPlayerLog().get(index).getNumberOfGamePlayed();
        System.out.println("Number of match : " + numberOfPlayed);
    }
    public static void showPlayerPointsInThisGame (String userName , String gameName){
        Player player = FindPlayerByInfo.findByUserName(userName);
        int index = 0 ;
        if (gameName.equalsIgnoreCase("DotsAndBoxes")){
            index = 0 ;
        }
        else if (gameName.equalsIgnoreCase("BattleSea")){
            index = 1 ;
        }
        else {
            System.out.println("not valid");
            return;
        }
        long score = player.getPlayerLog().get(index).getTakenScore();
        System.out.println("Level : " + score);
    }
}
