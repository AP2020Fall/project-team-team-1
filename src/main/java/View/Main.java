package View;

import Controller.AdminController.AdminGeneralController;
import Controller.AdminController.Message;
import Controller.BattleSeaController.Coordinate.Direction;
import Controller.BattleSeaController.Run;
import Controller.Exception.ExistPlayerException;
import Model.DataBase.DataBase;
import Model.PlatoModel.Admin;
import Model.PlatoModel.Player;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            DataBase.loadAllDataFromJsonFiles();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Menu.setScanner(new Scanner(System.in));
        Menu currentMenu = new MainMenu();
        currentMenu.run();
    }
}
