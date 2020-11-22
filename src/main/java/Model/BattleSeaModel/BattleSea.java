package Model.BattleSea;

import Model.Plato.Game;
import Model.Plato.Player;

import java.util.ArrayList;

public class BattleSea extends Game {
    public BattleSea(Player player1, Player player2) {
        super(player1, player2);
    }

    public static void runGameBattleSea(){

    }
    public boolean winnerChecker(){
        return true;
    }
    public void giveScore(Player player){

    }
    public void randomSpot(){

    }
    public boolean shotChecker (){
        return true;
    }
    public void showBoard(){

    }
    public void timer(){

    }
    public void turn(){

    }
    public void ableToShot(){

    }
    public void showShot(){

    }
    public void result(){

    }
    public void changeShipPlace(){

    }
    public void changeAxis(){

    }
}

class ShotHistory{
   private static ArrayList<ShotHistory> shotHistories = new ArrayList<>();
   private ArrayList<String> allShots;
   private ArrayList<String> userHits;
   private ArrayList<String> UserMissed;
   private ArrayList<String> enemyAllShots;
   private ArrayList<String> enemyHits;
   private ArrayList<String> enemyMissed;

   public ShotHistory(){
       allShots=new ArrayList<>();
       userHits =new ArrayList<>();
       UserMissed =new ArrayList<>();
       enemyAllShots=new ArrayList<>();
       enemyHits=new ArrayList<>();
       enemyMissed=new ArrayList<>();
   }


}
