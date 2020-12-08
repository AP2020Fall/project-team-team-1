package View;

import Model.DataBase.DataBase;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            DataBase.loadAllDataFromJsonFiles();
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
        Menu.setScanner(new Scanner(System.in));
        Menu currentMenu = new MainMenu();
        currentMenu.run();
    }
}
