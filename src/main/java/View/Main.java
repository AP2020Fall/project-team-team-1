package View;

import Model.PlatoModel.Admin;
import Model.PlatoModel.Player;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Player player = new Player("ata","rhz",1,"atarhz","11223344","ataarahimzadeh@gmail.com","09365909061");
        Player.AddNewPlayer(player);
        Admin admin1 = new Admin("hesam","asnashari",100,"Admin","Admin11228","Admin@yahoo.com","09121111111");
        Admin.AddNewAdmin(admin1);
        Menu.setScanner(new Scanner(System.in));
        Menu currentMenu = new MainMenu();
        currentMenu.run();
    }
}
