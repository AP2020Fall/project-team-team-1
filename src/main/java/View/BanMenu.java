package View;

import Controller.Exception.Plato.AlreadyBan;
import Controller.Exception.Plato.ItsNotBan;
import Controller.Exception.Plato.StartDatesException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class BanMenu extends Menu{
    public BanMenu( Menu parentMenu) {
        super("BanMenu",parentMenu);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        //submenus.put(1, showReportsForAdmin());
        submenus.put(2,ban());
        submenus.put(3,unBan());
        this.setSubmenus(submenus);
    }
    private Menu ban() {
        return new Menu("Ban player ", this) {
            @Override
            public void show() {
                System.out.println("who do you want to ban? ");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();

                try {
                    adminGeneralController.banPlayer(input);
                    System.out.println("Player Ban Successfully");
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
        return new Menu("Un ban player", this) {
            @Override
            public void show() {
                System.out.println("who do you want to un ban ? ");
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
//    private Menu showReportsForAdmin() {
//        return new Menu("add Event", this) {
//            @Override
//            public void show() {
//                System.out.println("You Are About to add new Event Please Enter event information");
//            }
//
//            @Override
//            public void execute() {
//                ArrayList<String> input = new ArrayList<>();
//                getEventInfo(input);
//                try {
//                    adminGeneralController.addEvent(arrayListToString(input));
//                    System.out.println("Add Event Successfully");
//                    parentMenu.run();
//                } catch (StartDatesException e) {
//                    System.out.println(e.getMessage());
//                    this.run();
//                } catch (IOException e) {
//                    System.out.println(e.getMessage());
//                }
//
//            }
//        };
//    }
}
