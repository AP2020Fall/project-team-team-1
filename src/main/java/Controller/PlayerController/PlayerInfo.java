package Controller.PlayerController;

import Model.PlatoModel.Player;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PlayerInfo {

    public static void showBasicInformation(String username){

    }
    public static void showUserAge(String username) {
        Player player = FindPlayerByInfo.findByUserName(username);
        LocalDate registerDate = player.getRegisterDate();
        LocalDate now = LocalDate.now();
//        LocalDate start = LocalDate.parse("2020-10-02");
//        LocalDate now = LocalDate.now();
//        System.out.println(start);
//        System.out.println(now);
//        Long between = ChronoUnit.DAYS.between(start,now);
//        System.out.println(between);
    }
    public static void showUserGamesStatistics(String username , String gameName) {

    }
    public static void showUserLastPlayed(String username) {

    }
    public static void show(String username) {

    }
    public static void showUserFavoritesGame(String username) {

    }
}
