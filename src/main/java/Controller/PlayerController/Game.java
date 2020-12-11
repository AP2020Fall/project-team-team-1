package Controller.PlayerController;

import Model.PlatoModel.Games;
import Model.PlatoModel.Player;

import java.time.LocalDate;

public class Game {
    public static void findGameForRun(String playerUserName ,String gameName , String score){

    }
    public static void historySaver(LocalDate localDate,String winner,String loser,String gameName){
        Player winnerPlayer = FindPlayerByInfo.findByUserName(winner);
        Player loserPlayer = FindPlayerByInfo.findByUserName(loser);

        winnerPlayer.getLastPlayed().add("Date: "+String.valueOf(localDate)+" "+winner+" wins "+ loser+" At: "+gameName);
        loserPlayer.getLastPlayed().add("Date: "+String.valueOf(localDate)+" "+loser+" loser Against "+ winner +" At: "+gameName);
    }
    public static void giveScoreAndEditPlayerLog(String gameName,String winnerPlayerInput,String loserPlayerInput,long scoreInput){
        Player winnerPlayer = FindPlayerByInfo.findByUserName(winnerPlayerInput);

        Player loserPlayer = FindPlayerByInfo.findByUserName(loserPlayerInput);

        int index = 1 ;
        /*** index = 0 in Games ArrayList ---> BattleShip ***/
        /*** index = 1 in Games ArrayList ---> DotsAndBoxes ***/
        if (gameName.equalsIgnoreCase(Games.getGames().get(0).getGameName())){
            index = 1;
        }else if (gameName.equalsIgnoreCase(Games.getGames().get(1).getGameName()))
            index = 0;
        /********************************* Do Winner Works *********************************/
        winnerPlayer.getPlayerLog().get(index).setNumberOfGamePlayed(winnerPlayer.getPlayerLog().get(index).getNumberOfGamePlayed()+1);

        winnerPlayer.getPlayerLog().get(index).setTakenScore(winnerPlayer.getPlayerLog().get(index).getTakenScore()+scoreInput);

        winnerPlayer.getPlayerLog().get(index).setNumberOfWins(winnerPlayer.getPlayerLog().get(index).getNumberOfWins()+1);

        /********************************* Do loser Works *********************************/

        loserPlayer.getPlayerLog().get(index).setNumberOfGamePlayed(loserPlayer.getPlayerLog().get(index).getNumberOfGamePlayed()+1);

        loserPlayer.getPlayerLog().get(index).setNumberOfLoses(loserPlayer.getPlayerLog().get(index).getNumberOfLoses()+1);

    }
}
