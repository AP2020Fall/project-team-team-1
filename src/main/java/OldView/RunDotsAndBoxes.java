package OldView;

import Controller.DotsAndBoxesController.DotsAndBoxesController;
import Controller.Exception.DotsAndBoxes.ExistLineException;
import Controller.Exception.DotsAndBoxes.FindLineException;
import Controller.Exception.DotsAndBoxes.NotEmptyString;
import Controller.Exception.DotsAndBoxes.WrongFormatInDots;
import Controller.Exception.Plato.BanExceptionForLogin;
import Controller.Exception.Plato.ExistPlayerException;
import Controller.Exception.Plato.InvalidUserNameException;
import Controller.Exception.Plato.WrongPasswordException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class RunDotsAndBoxes extends Menu {
//    private static DotsAndBoxesController dotsAndBoxesController = new DotsAndBoxesController();
    private String Username1;
    private String Username2;
    private static DotsAndBoxesController dotsAndBoxesController;
    private Menu GamesMenu;
    private int score ;

    public RunDotsAndBoxes(String username1, String username2,int score,DotsAndBoxesController dotsAndBoxesController, Menu parentMenu) {
        super("Run "+adminGeneralController.secondGameNameGetter(), parentMenu);
        this.Username1 = username1;
        this.Username2 = username2;
        RunDotsAndBoxes.dotsAndBoxesController =new DotsAndBoxesController();
//        this.score=10;
        this.score=score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getUsername1() {
        return Username1;
    }

    public String getUsername2() {
        return Username2;
    }


    public void setUsername2(String username2) {
        Username2 = username2;
    }

    @Override
    public void show() {
        System.out.print(Color.YELLOW);
        System.out.println("Welcome to Dots And Boxes Game GoodLuck! ");
        System.out.print(Color.RESET);
    }

    @Override
    public void execute() {
        if (this.Username2 != null) {
            startTheGame();
        } else {
            temporaryLogin().run();
            this.run();
        }
    }

    public static void printWinner() {
        if (dotsAndBoxesController.getBluePoints() > dotsAndBoxesController.getRedPoints()) {
            System.out.print(Color.YELLOW_BOLD);
            System.out.println("The Winner is : ");
            System.out.print(Color.RESET);
            System.out.print(Color.BLUE);
            System.out.println("Blue Player ");
            System.out.println("Blue points :" + dotsAndBoxesController.getRedPoints());
            System.out.print(Color.RESET);
        } else if (dotsAndBoxesController.getRedPoints() > dotsAndBoxesController.getBluePoints()) {
            System.out.print(Color.YELLOW_BOLD);
            System.out.println("The Winner is : ");
            System.out.print(Color.RESET);
            System.out.print(Color.RED);
            System.out.println("Red Player ");
            System.out.println("Red points :" + dotsAndBoxesController.getRedPoints());
            System.out.print(Color.RESET);
        } else {
            System.out.print(Color.YELLOW_BOLD);
            System.out.println("DRAW!!!");
            System.out.println("There is no Winner");
            System.out.print(Color.RESET);
        }
    }

    public static void printBoard() {

        System.out.println("   0   1   2   3   4   5   6   7  ");


        System.out.println("0  •" + dotsAndBoxesController.getLine(0, 0, 0, 1).toString() + "•" + dotsAndBoxesController.getLine(0, 1, 0, 2).toString() + "•" + dotsAndBoxesController.getLine(0, 2, 0, 3).toString() + "•" + dotsAndBoxesController.getLine(0, 3, 0, 4).toString() + "•" + dotsAndBoxesController.getLine(0, 4, 0, 5).toString() + "•" + dotsAndBoxesController.getLine(0, 5, 0, 6).toString() + "•" + dotsAndBoxesController.getLine(0, 6, 0, 7).toString() + "•");
        System.out.println("  " + dotsAndBoxesController.getLine(0, 0, 1, 0).toString() + " " + dotsAndBoxesController.getLine(0, 1, 1, 1).toString() + " " + dotsAndBoxesController.getLine(0, 2, 1, 2).toString() + " " + dotsAndBoxesController.getLine(0, 3, 1, 3).toString() + " " + dotsAndBoxesController.getLine(0, 4, 1, 4).toString() + " " + dotsAndBoxesController.getLine(0, 5, 1, 5).toString() + " " + dotsAndBoxesController.getLine(0, 6, 1, 6).toString() + " " + dotsAndBoxesController.getLine(0, 7, 1, 7).toString());


        System.out.println("1  •" + dotsAndBoxesController.getLine(1, 0, 1, 1).toString() + "•" + dotsAndBoxesController.getLine(1, 1, 1, 2).toString() + "•" + dotsAndBoxesController.getLine(1, 2, 1, 3).toString() + "•" + dotsAndBoxesController.getLine(1, 3, 1, 4).toString() + "•" + dotsAndBoxesController.getLine(1, 4, 1, 5).toString() + "•" + dotsAndBoxesController.getLine(1, 5, 1, 6).toString() + "•" + dotsAndBoxesController.getLine(1, 6, 1, 7).toString() + "•");
        System.out.println("  " + dotsAndBoxesController.getLine(1, 0, 2, 0).toString() + " " + dotsAndBoxesController.getLine(1, 1, 2, 1).toString() + " " + dotsAndBoxesController.getLine(1, 2, 2, 2).toString() + " " + dotsAndBoxesController.getLine(1, 3, 2, 3).toString() + " " + dotsAndBoxesController.getLine(1, 4, 2, 4).toString() + " " + dotsAndBoxesController.getLine(1, 5, 2, 5).toString() + " " + dotsAndBoxesController.getLine(1, 6, 2, 6).toString() + " " + dotsAndBoxesController.getLine(1, 7, 2, 7).toString());


        System.out.println("2  •" + dotsAndBoxesController.getLine(2, 0, 2, 1).toString() + "•" + dotsAndBoxesController.getLine(2, 1, 2, 2).toString() + "•" + dotsAndBoxesController.getLine(2, 2, 2, 3).toString() + "•" + dotsAndBoxesController.getLine(2, 3, 2, 4).toString() + "•" + dotsAndBoxesController.getLine(2, 4, 2, 5).toString() + "•" + dotsAndBoxesController.getLine(2, 5, 2, 6).toString() + "•" + dotsAndBoxesController.getLine(2, 6, 2, 7).toString() + "•");
        System.out.println("  " + dotsAndBoxesController.getLine(2, 0, 3, 0).toString() + " " + dotsAndBoxesController.getLine(2, 1, 3, 1).toString() + " " + dotsAndBoxesController.getLine(2, 2, 3, 2).toString() + " " + dotsAndBoxesController.getLine(2, 3, 3, 3).toString() + " " + dotsAndBoxesController.getLine(2, 4, 3, 4).toString() + " " + dotsAndBoxesController.getLine(2, 5, 3, 5).toString() + " " + dotsAndBoxesController.getLine(2, 6, 3, 6).toString() + " " + dotsAndBoxesController.getLine(2, 7, 3, 7).toString());


        System.out.println("3  •" + dotsAndBoxesController.getLine(3, 0, 3, 1).toString() + "•" + dotsAndBoxesController.getLine(3, 1, 3, 2).toString() + "•" + dotsAndBoxesController.getLine(3, 2, 3, 3).toString() + "•" + dotsAndBoxesController.getLine(3, 3, 3, 4).toString() + "•" + dotsAndBoxesController.getLine(3, 4, 3, 5).toString() + "•" + dotsAndBoxesController.getLine(3, 5, 3, 6).toString() + "•" + dotsAndBoxesController.getLine(3, 6, 3, 7).toString() + "•");
        System.out.println("  " + dotsAndBoxesController.getLine(3, 0, 4, 0).toString() + " " + dotsAndBoxesController.getLine(3, 1, 4, 1).toString() + " " + dotsAndBoxesController.getLine(3, 2, 4, 2).toString() + " " + dotsAndBoxesController.getLine(3, 3, 4, 3).toString() + " " + dotsAndBoxesController.getLine(3, 4, 4, 4).toString() + " " + dotsAndBoxesController.getLine(3, 5, 4, 5).toString() + " " + dotsAndBoxesController.getLine(3, 6, 4, 6).toString() + " " + dotsAndBoxesController.getLine(3, 7, 4, 7).toString());


        System.out.println("4  •" + dotsAndBoxesController.getLine(4, 0, 4, 1).toString() + "•" + dotsAndBoxesController.getLine(4, 1, 4, 2).toString() + "•" + dotsAndBoxesController.getLine(4, 2, 4, 3).toString() + "•" + dotsAndBoxesController.getLine(4, 3, 4, 4).toString() + "•" + dotsAndBoxesController.getLine(4, 4, 4, 5).toString() + "•" + dotsAndBoxesController.getLine(4, 5, 4, 6).toString() + "•" + dotsAndBoxesController.getLine(4, 6, 4, 7).toString() + "•");
        System.out.println("  " + dotsAndBoxesController.getLine(4, 0, 5, 0).toString() + " " + dotsAndBoxesController.getLine(4, 1, 5, 1).toString() + " " + dotsAndBoxesController.getLine(4, 2, 5, 2).toString() + " " + dotsAndBoxesController.getLine(4, 3, 5, 3).toString() + " " + dotsAndBoxesController.getLine(4, 4, 5, 4).toString() + " " + dotsAndBoxesController.getLine(4, 5, 5, 5).toString() + " " + dotsAndBoxesController.getLine(4, 6, 5, 6).toString() + " " + dotsAndBoxesController.getLine(4, 7, 5, 7).toString());


        System.out.println("5  •" + dotsAndBoxesController.getLine(5, 0, 5, 1).toString() + "•" + dotsAndBoxesController.getLine(5, 1, 5, 2).toString() + "•" + dotsAndBoxesController.getLine(5, 2, 5, 3).toString() + "•" + dotsAndBoxesController.getLine(5, 3, 5, 4).toString() + "•" + dotsAndBoxesController.getLine(5, 4, 5, 5).toString() + "•" + dotsAndBoxesController.getLine(5, 5, 5, 6).toString() + "•" + dotsAndBoxesController.getLine(5, 6, 5, 7).toString() + "•");
        System.out.println("  " + dotsAndBoxesController.getLine(5, 0, 6, 0).toString() + " " + dotsAndBoxesController.getLine(5, 1, 6, 1).toString() + " " + dotsAndBoxesController.getLine(5, 2, 6, 2).toString() + " " + dotsAndBoxesController.getLine(5, 3, 6, 3).toString() + " " + dotsAndBoxesController.getLine(5, 4, 6, 4).toString() + " " + dotsAndBoxesController.getLine(5, 5, 6, 5).toString() + " " + dotsAndBoxesController.getLine(5, 6, 6, 6).toString() + " " + dotsAndBoxesController.getLine(5, 7, 6, 7).toString());


        System.out.println("6  •" + dotsAndBoxesController.getLine(6, 0, 6, 1).toString() + "•" + dotsAndBoxesController.getLine(6, 1, 6, 2).toString() + "•" + dotsAndBoxesController.getLine(6, 2, 6, 3).toString() + "•" + dotsAndBoxesController.getLine(6, 3, 6, 4).toString() + "•" + dotsAndBoxesController.getLine(6, 4, 6, 5).toString() + "•" + dotsAndBoxesController.getLine(6, 5, 6, 6).toString() + "•" + dotsAndBoxesController.getLine(6, 6, 6, 7).toString() + "•");
        System.out.println("  " + dotsAndBoxesController.getLine(6, 0, 7, 0).toString() + " " + dotsAndBoxesController.getLine(6, 1, 7, 1).toString() + " " + dotsAndBoxesController.getLine(6, 2, 7, 2).toString() + " " + dotsAndBoxesController.getLine(6, 3, 7, 3).toString() + " " + dotsAndBoxesController.getLine(6, 4, 7, 4).toString() + " " + dotsAndBoxesController.getLine(6, 5, 7, 5).toString() + " " + dotsAndBoxesController.getLine(6, 6, 7, 6).toString() + " " + dotsAndBoxesController.getLine(6, 7, 7, 7).toString());


        System.out.println("7  •" + dotsAndBoxesController.getLine(7, 0, 7, 1).toString() + "•" + dotsAndBoxesController.getLine(7, 1, 7, 2).toString() + "•" + dotsAndBoxesController.getLine(7, 2, 7, 3).toString() + "•" + dotsAndBoxesController.getLine(7, 3, 7, 4).toString() + "•" + dotsAndBoxesController.getLine(7, 4, 7, 5).toString() + "•" + dotsAndBoxesController.getLine(7, 5, 7, 6).toString() + "•" + dotsAndBoxesController.getLine(7, 6, 7, 7).toString() + "•");


        System.out.print(Color.BLUE);
        System.out.println("BLUE POINTS : " + dotsAndBoxesController.getBluePoints());
        System.out.print(Color.RESET);
        System.out.print(Color.RED);
        System.out.println("RED POINTS : " + dotsAndBoxesController.getRedPoints());
        System.out.print(Color.RESET);
        System.out.println();
        if (dotsAndBoxesController.turn() == dotsAndBoxesController.getBluePlayer()) {
            System.out.print(Color.BLUE);
            System.out.println("BLUE TURN : ");
            System.out.print(Color.RESET);
        } else if (dotsAndBoxesController.turn() == dotsAndBoxesController.getRedPlayer()) {
            System.out.print(Color.RED);
            System.out.println("RED TURN : ");
            System.out.print(Color.RESET);
        }
    }

    public void startTheGame() {
        System.out.println("Blue Represents " + this.Username1 + " And Red Represent " + this.Username2);
        System.out.println();
        while (true) {
            try {
                System.out.println("Please Enter Tow Dots In this Format 2-3,2-4 ");
                dotsAndBoxesController.startDotsAndBoxes();
                if (dotsAndBoxesController.whoIsWinner().equals("blue")){
                    winnerAward(getUsername1(),getUsername2());
                }else if (dotsAndBoxesController.whoIsWinner().equals("red")){
                    winnerAward(getUsername2(),getUsername1());
                }
                break;
            } catch (NotEmptyString | WrongFormatInDots | ExistLineException | FindLineException notEmptyString) {
                System.out.println(notEmptyString.getMessage());
            }
        }
        setUsername2(null);
        new DotsAndBoxesMenu(getUsername1(),this.parentMenu.parentMenu).run();
    }

    private Menu temporaryLogin() {
        return new Menu("login", this) {
            @Override
            public void show() {
                System.out.println("Login As One Of These Players");
                try {
                    String[] showPlayer = adminGeneralController.showAllUsers().split("\\$");
                    for (String out : showPlayer) {
                        if (out.equals(getUsername1())){
                            continue;
                        }
                        System.out.println(out);
                    }
                } catch (ExistPlayerException e) {
                    System.out.println(e.getMessage());
                    this.run();
                }
            }

            @Override
            public void execute() {
                ArrayList<String> info = new ArrayList<>();
                System.out.println("please Enter The Username ");
                String username = scanner.nextLine();
                if (username.equals(getUsername1())){
                    System.out.println("You Cant Play This Game With YourSelf");
                    this.run();
                }else
                info.add(username);
                System.out.println("Please Enter The password");
                info.add(scanner.nextLine());
                try {
                    try {
                        processLoginController.loginAsPlayer(arrayListToString(info));
                    } catch (BanExceptionForLogin banExceptionForLogin) {
                        System.out.println(banExceptionForLogin.getMessage());
                        this.parentMenu.run();
                    }
                    setUsername2(info.get(0));
                } catch (InvalidUserNameException | WrongPasswordException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Try Again...");
                    this.run();
                }
                this.parentMenu.run();
            }
        };
    }
    public static String getNextCommand(){
        return scanner.nextLine();
    }
    public void winnerAward(String winner , String loser){
        try {
            playerGeneralController.giveScoreAndEditPlayerLog(adminGeneralController.secondGameNameGetter(),winner,loser,getScore());
            playerGeneralController.historySaver(LocalDate.now(),winner,loser,adminGeneralController.secondGameNameGetter());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String arrayListToString(ArrayList<String> arrayList) {
        String output = "";
        for (String string : arrayList) {
            output += string + " ";
        }
        return output;
    }

}
