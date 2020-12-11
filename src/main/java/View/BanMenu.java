package View;

import Controller.Exception.Plato.AlreadyBan;
import Controller.Exception.Plato.EmptyReportsList;
import Controller.Exception.Plato.ItsNotBan;
import Controller.Exception.Plato.StartDatesException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class BanMenu extends Menu {
    public BanMenu(Menu parentMenu) {
        super("BanMenu", parentMenu);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, showReportsForAdmin());
        submenus.put(2, ban());
        submenus.put(3, unBan());
        this.setSubmenus(submenus);
    }

    private Menu ban() {
        return new Menu("BanExceptionForLogin player ", this) {
            @Override
            public void show() {

                System.out.println("who do you want to ban? ");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();

                try {
                    adminGeneralController.banPlayer(input);
                    System.out.println("Player BanExceptionForLogin Successfully");
                    parentMenu.run();
                } catch (AlreadyBan alreadyBan) {
                    System.out.println(alreadyBan.getMessage());
                    this.run();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    this.run();
                }

            }
        };
    }

    private Menu unBan() {
        return new Menu("UnBan player", this) {
            @Override
            public void show() {
                adminGeneralController.showBanPlayers();
                System.out.println("Who do you want to UnBan ? ");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                System.out.println("player un ban successfully! ");
                parentMenu.run();
                try {
                    adminGeneralController.unBanPlayer(input);
                } catch (ItsNotBan itsNotBan) {
                    System.out.println(itsNotBan.getMessage());
                    this.run();
                } catch (AlreadyBan alreadyBan) {
                    System.out.println(alreadyBan.getMessage());
                    this.run();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    this.run();
                }
            }
        };
    }

    private Menu showReportsForAdmin() {
        return new Menu("Show Reports of Players", this) {
            @Override
            public void show() {
                System.out.println("Enter Player Username Who you want to see her/his reports : (for Exit Enter Back!)");
            }

            @Override
            public void execute() {
                String inputUsername = scanner.nextLine();
                if (inputUsername.equalsIgnoreCase("back")){
                    this.parentMenu.run();
                }
                try {
                    adminGeneralController.showReportListOfPlayer(inputUsername);
                    System.out.print(Color.RED);
                    System.out.println("If you want to BanExceptionForLogin \""+ inputUsername +"\" Go to BanExceptionForLogin Menu");
                    System.out.print(Color.RESET);
                    parentMenu.run();
                } catch (EmptyReportsList emptyReportsList) {
                    System.out.println(emptyReportsList.getMessage());
                    this.run();
                }



            }
        };
    }
}
