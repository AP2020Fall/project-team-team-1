package Client.OldView;

import java.util.TimerTask;

public class Timer {

    int secondPassed = 46;
    int changeTurn = -1 ;
    java.util.Timer timer = new java.util.Timer();

    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            secondPassed--;
            changeTurn++;
            if (secondPassed==0){
                System.out.println("You lost your turn! ");
                System.exit(1);
            }
//            if (changeTurn%5 == 0){
//                System.out.println("turn must be change");
//                //todo set timer in controller for each game!
//            }
            System.out.println("You have only :" + secondPassed + " second");
        }
    };

    public void start(){
        timer.scheduleAtFixedRate(timerTask,1000,1000);
    }
}
